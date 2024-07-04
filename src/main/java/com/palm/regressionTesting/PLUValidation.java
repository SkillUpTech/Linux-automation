package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.TestUtil;

public class PLUValidation implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	PLULocators PLUPageLocators;
	String sheetStatus = "Pass";
	WebDriver driver;
	OpenWebsite openWebsite;
	
	public PLUValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.sheetData = sheetData;
		
	}
	
	public String start() throws InterruptedException
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "courseTitle":
					courseTitle(row.get(1));
					break;
				case "topBannerContent":
					topBannerContent(row.get(1));
					break;
				case "description":
					description(row.get(1));
					break;
				case "techPrograms":
					techPrograms(row);
					break;
				case "PLUCourses":
					PLUCourses(row);
					break;
				case "FAQ":
					FAQ(row, i);
					break;
				case "BackGroundColor_certificateLeftContent":
				BackGroundColor_certificateLeftContent(row);
				break;
			}
		}
		Set<String> windows = driver.getWindowHandles();
		for(String win : windows)
		{
			driver.switchTo().window(win);
			if(!BaseWindow.equals(win))
			{
				driver.switchTo().window(win);
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
				else if(driver.getCurrentUrl().contains("courses"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
				else if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetStatus;
	}
	
	public void courseTitle(String titleName)
	{
		String titleStatus = this.PLUPageLocators.verifyTitle(titleName);
		if(!titleStatus.equalsIgnoreCase("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PLU").get(0).set(0, "courseTitle - failed");
		}
	}
	public void topBannerContent(String bannerData)
	{
		String bannerStatus = this.PLUPageLocators.verifyBanner(bannerData);
		if(!bannerStatus.equalsIgnoreCase("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PLU").get(1).set(0, "topBannerContent - failed");
		}

	}
	public void description(String data)
	{
		String descriptionStatus = this.PLUPageLocators.verifyDescription(data);
		if(!descriptionStatus.equalsIgnoreCase("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PLU").get(2).set(0, "description - failed");
		}
	}
	
	public void techPrograms(ArrayList<String> programs)
	{
		if(!programs.contains("NA"))
		{
			ArrayList<String> failTechPgm = this.PLUPageLocators.verifyPrograms(programs);
			if(failTechPgm.contains("fail"))
			{
				if(failTechPgm.size()>0)
				{
					for(int i = 0; i < failTechPgm.size(); i++)
					{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(3).add(i+1, (failTechPgm.get(i) + " - failed"));
					}
				}
			}
		}
	}
	public void PLUCourses(ArrayList<String> courses)
	{
		if(!courses.contains("NA"))
		{
			ArrayList<String> failedUrls = this.PLUPageLocators.verifyPLUCourse(courses);
			for(int i = 0; i < failedUrls.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(4).add(i+1, (failedUrls.get(i) + " - failed"));
			}
		}
	}
	public void FAQ(ArrayList<String> faq, int rowIndex)
	{
		try
		{
			ArrayList<String> status = this.PLUPageLocators.verifyFAQ(faq);
			int i = -1;
			if(status.contains("questionFail"))
			{
				sheetStatus = "Fail";
				i = 1;
			}
			else if(status.contains("answerFail"))
			{
				sheetStatus = "Fail";
				i = 2;
			}
			if( i > -1 )
			{
				String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(rowIndex).get(i);
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(rowIndex).set(i, (cellValue + " - failed"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void BackGroundColor_certificateLeftContent(ArrayList<String> data)
	{
		ArrayList<String> status = this.PLUPageLocators.verfyCss(data);
		if(status.contains("fail"))
		{
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(10).set(0, "BackGroundColor_certificateLeftContent - failed");
		}
	}

	public WebDriver openDriver(String browserName)
	{
		WebDriver driver = null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", RegressionTesting.driverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Hemamalini\\Downloads\\geckodriver-v0.33.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver(); 
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		return driver;
	}
	@Override
	public String call() throws Exception {
		System.out.println("PLU process started");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.PLUPageLocators = new PLULocators(driver);
		String BaseWindow = driver.getWindowHandle();
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "courseTitle":
					courseTitle(row.get(1));
					break;
				case "topBannerContent":
					topBannerContent(row.get(1));
					break;
				case "description":
					description(row.get(1));
					break;
				case "techPrograms":
					techPrograms(row);
					break;
				case "PLUCourses":
					PLUCourses(row);
					break;
				case "FAQ":
					FAQ(row, i);
					break;
				case "BackGroundColor_certificateLeftContent":
				BackGroundColor_certificateLeftContent(row);
				break;
			}
		}
		Set<String> windows = driver.getWindowHandles();
		for(String win : windows)
		{
			driver.switchTo().window(win);
			if(!BaseWindow.equals(win))
			{
				driver.switchTo().window(win);
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
				else if(driver.getCurrentUrl().contains("courses"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
				else if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(win);
					driver.close();
					driver.switchTo().window(BaseWindow);
				}
			}
		}
		driver.quit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetStatus;
	
	}
}

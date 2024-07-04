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

public class HomePageValidator implements Callable<String>
{
	private String SHEET_NAME;
	ArrayList<ArrayList<String>> sheetData;
	WebDriver driver;
	HomepageLocator homepageLocator;
	String sheetStatus;
	
	public HomePageValidator(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		
		this.sheetData = sheetData;
		
	}

	public void homePage(WebDriver driver)
	{
		if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)))
		{
			String url = OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE);
			driver.get(url);
		}
		else
		{
			System.out.println("you already in home page");
		}
	}
	public String start() throws InterruptedException
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		this.homePage(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			case "Banner":
				verifyBanner(row);
				break;
			case"learningPartners":
				verifyLearningPartners(row);
				break;
			case"humanSkills":
				verifyHumanSkills();
				break;
			case"topTechCategories":
				verifyTopTechCategories();
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

	
	public void verifyBanner(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<String> status = homepageLocator.checkSliderLink(dataFromExcel);
		{
			for(int i = 0; i < status.size(); i++)
			{
				if(status.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(0).add(i+1, (status.get(i) + "Banner - failed"));

				}
			}
		}
	}
	public void verifyLearningPartners(ArrayList<String> dataFromExcel)
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> statusOfLearningPartners = homepageLocator.checkLearningPartners(dataFromExcel);
			for(int i = 0; i < statusOfLearningPartners.size(); i++)
			{
				if(dataFromExcel.contains(statusOfLearningPartners.get(i)))
				{
					sheetStatus = "Fail";
					int position = dataFromExcel.indexOf(statusOfLearningPartners.get(i));
					String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(1).get(position);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(1).set(position, (cellValue + " - failed"));

				}
			}
		}
	}
	public void verifyLearningCatalog(String dataFromExcel) throws InterruptedException
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> statusOfLearningPartners = homepageLocator.checkLearningCatalog();
			if(statusOfLearningPartners.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(1).set(0, "learningCatalog - failed");
			}
		}
	}
	public void verifyHumanSkills()
	{
			ArrayList<String> statusOfHumanSkills = homepageLocator.checkHumanSkills();
			for(int i = 0; i < statusOfHumanSkills.size(); i++)
			{
				if(statusOfHumanSkills.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(2).add(i+1, (statusOfHumanSkills.get(i) + "humanSkills - failed"));
				}
			}
	}
	
	public void verifyTopTechCategories()
	{
			ArrayList<String> statusOfTopTechCategories = homepageLocator.checkTopTechCategories();
			for(int i = 0; i < statusOfTopTechCategories.size(); i++)
			{
				if(statusOfTopTechCategories.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(3).add(i+1, (statusOfTopTechCategories.get(i) + "topTechCategories - failed"));
				}
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
	public String call() throws Exception
	{
		System.out.println("Homepage process started");
		sheetStatus = "Pass";

		try
		{
			driver = this.openDriver(RegressionTesting.nameOfBrowser);
			this.homepageLocator = new HomepageLocator(this.driver);
			OpenWebsite.openSite(driver);
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		this.homePage(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			case "Banner":
				verifyBanner(row);
				break;
			case"learningPartners":
				verifyLearningPartners(row);
				break;
			case"humanSkills":
				verifyHumanSkills();
				break;
			case"topTechCategories":
				verifyTopTechCategories();
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

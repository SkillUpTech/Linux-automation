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

import com.palm.regressionTesting.IBMViewCourseLocator;
import com.palm.regressionTesting.OpenWebsite;
import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class verifyProgramURLValidation implements Callable<String>
{

	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	VerifyProgramURLLocator verifyProgramURLLocator;
	String sheetStatus = "Pass";
	
	public verifyProgramURLValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.sheetData = sheetData;
		
	}
	
	
	public String start() throws InterruptedException
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		com.seo.regression.testing.OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "courseURL":
					checkProgramURL(row);
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
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))
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
				else if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))
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
	
	public void checkProgramURL(ArrayList<String> data)
	{
		ArrayList<String> status = verifyProgramURLLocator.verifyProgramURL(data);
		if(status.size()>0)
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBM").get(0).add(((status.size())+1), (status.get(i) + "IBMcourses - failed"));
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
	public String call() throws Exception {
		System.out.println("ProgramURL and SLUG verification started");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.verifyProgramURLLocator = new VerifyProgramURLLocator(driver);
		String BaseWindow = driver.getWindowHandle();
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "courseURL":
					checkProgramURL(row);
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
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))
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
				else if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))
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
		driver.quit();
		return sheetStatus;
	
	}
}

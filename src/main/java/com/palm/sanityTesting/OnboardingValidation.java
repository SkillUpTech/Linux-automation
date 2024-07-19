package com.palm.sanityTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class OnboardingValidation  implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	OnboardingLocator onboardingLocator;
	String sheetStatus = "Pass";

	public OnboardingValidation(ArrayList<ArrayList<String>> sheetData)
	{
		
		this.sheetData = sheetData;
		
	}

	
	public void login(String email, String pwd)
	{
		String status = onboardingLocator.checkLogin(email, pwd);
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(0).set(0, "login - failed");
		}
	}
	
	public void personalPage()
	{
		String status = onboardingLocator.checkPersonalPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(1).set(0, "personalPage - failed");
		}
	}
	
	public void interestedPage()
	{
		String status = onboardingLocator.checkInterestedPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(2).set(0, "interestedPage	 - failed");
		}
	}
	
	public void workstatusPage()
	{
		String status = onboardingLocator.checkWorkstatusPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(3).set(0, "workstatusPage - failed");
		}
	}
	public void WorkExperience()
	{
		String status = onboardingLocator.checkWorkExperience();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(4).set(0, "WorkExperience - failed");
		}
	}
	public void aboutYouPage()
	{
		String status = onboardingLocator.checkAboutYouPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(5).set(0, "aboutYouPage - failed");
		}
	}
	
	public void educationPage()
	{
		String status = onboardingLocator.checkEducationPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(6).set(0, "educationPage - failed");
		}
	}
	
	public void jobOpportunites()
	{
		String status = onboardingLocator.checkJobOpportunites();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("OnboardingJourney").get(7).set(0, "jobOpportunites - failed");
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

		try
		{
			driver = this.openDriver(com.palm.sanityTesting.RegressionTesting.nameOfBrowser);
			com.palm.sanityTesting.OpenWebsite.openSite(driver);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			this.onboardingLocator = new OnboardingLocator(driver);
			System.out.println("Onboarding Journey Process started");
		String BaseWindow = driver.getWindowHandle();
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "login":
					login(row.get(1), row.get(2));
					break;
				case "personalPage":
					personalPage();
					break;
				case "interestedPage":
					interestedPage();
					break;
				case "workstatusPage":
					workstatusPage();
					break;
				case "WorkExperience":
					WorkExperience();
					break;
				case "aboutYouPage":
					aboutYouPage();
					break;
				case "educationPage":
					educationPage();
					break;
				case "jobOpportunites":
					jobOpportunites();
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

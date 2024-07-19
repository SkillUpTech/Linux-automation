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

import com.regression.utility.TestUtil;
import com.seo.regression.testing.RegressionTesting;
import com.seo.sanityTesting.OpenWebsite;
import com.seo.sanityTesting.PurchaseCourseLocator;

public class PurchaseCourseValidation implements Callable<String>
{
	
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	com.palm.sanityTesting.PurchaseCourseLocator purchaseCourseLocator;
	String sheetStatus = "Pass";
	
	public PurchaseCourseValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.sheetData = sheetData;
		
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
	public void url(String data)
	{
		String status = purchaseCourseLocator.launchURL(data);
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(0).set(0, "url - failed");
		}
	}
	
	public void enrollment(ArrayList<String> enrollDataFromExcel)
	{
		ArrayList<String> verifyEnrollmentProcess = new ArrayList<String>();
		try
		{
			if(!enrollDataFromExcel.contains("NA"))
			{
				verifyEnrollmentProcess = purchaseCourseLocator.enroll(enrollDataFromExcel);
				if(verifyEnrollmentProcess.contains("Fail"))
				{
					for(int i = 0; i < verifyEnrollmentProcess.size(); i++)
					{
						if(verifyEnrollmentProcess.get(1).contains("loginFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(2).contains("choosePlanFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(3).contains("razorpayFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(4).contains("paymentFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(5).contains("orderDetailFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			sheetStatus = "Fail";
		}
	}
	public void chekProfileSection(ArrayList<String> data)
	{
		ArrayList<String> checkProfile = purchaseCourseLocator.programLocator();
		if(checkProfile.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(2).set(0, "chekProfileSection - failed");
		}
	}
	@Override
	public String call() throws Exception {

		try
		{
			driver = this.openDriver(com.palm.sanityTesting.RegressionTesting.nameOfBrowser);
			com.palm.sanityTesting.OpenWebsite.openSite(driver);
			this.purchaseCourseLocator = new com.palm.sanityTesting.PurchaseCourseLocator(driver);
			System.out.println("puchase course page process started");
			String BaseWindow = driver.getWindowHandle();
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
				case "url":
					url(row.get(1));
					break;
				case "enrollment":
					enrollment(row);
					break;
				case "chekProfileSection":
					chekProfileSection(row);
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
				if(driver.getCurrentUrl().equalsIgnoreCase(com.palm.sanityTesting.OpenWebsite.setURL+"/"))
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
				else if(!driver.getCurrentUrl().equalsIgnoreCase(com.palm.sanityTesting.OpenWebsite.setURL+"/"))
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

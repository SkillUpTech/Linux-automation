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

import com.palm.regressionTesting.OpenWebsite;
import com.palm.regressionTesting.ProcessLogin;
import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class ErrorCodeValidation implements Callable<String>
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	ErrorCodeLocator errorCodeLocator;
	String sheetStatus = "Pass";
	
	public ErrorCodeValidation(ArrayList<ArrayList<String>> sheetData)
	{
		this.sheetData = sheetData;
		
	}
	
	public void geturl(ArrayList<String> codeFromExcel)
	{
		if(!codeFromExcel.contains("No"))
		{
			ArrayList<String> checkURL = errorCodeLocator.checkCourseCode(codeFromExcel);
			for(int i = 0; i < checkURL.size(); i++)
			{
				if(codeFromExcel.contains(checkURL.get(i)))
				{
					sheetStatus = "Fail";
					int position = codeFromExcel.indexOf(checkURL.get(i));
					String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(0).get(position);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(0).set(position, (cellValue + " - failed"));
				}
			}
		}
	}
		
	public void urlRedirection(ArrayList<String> codeFromExcel, int rowIndex)
	{
			ArrayList<String> checkURL = errorCodeLocator.checkURLRedirection(codeFromExcel);
			int i = -1;
			if(checkURL.size()>0)
			{
				sheetStatus = "Fail";
				i = 2;
			}
			if( i > -1 )
			{
				String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(rowIndex).get(i);
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(rowIndex).set(i, (cellValue + " - failed"));
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
		System.out.println("login process started");
		try
		{
			driver = this.openDriver(com.palm.sanityTesting.RegressionTesting.nameOfBrowser);
			com.palm.sanityTesting.OpenWebsite.openSite(driver);
			this.errorCodeLocator = new ErrorCodeLocator(driver);
			String BaseWindow = driver.getWindowHandle();
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
				case"url":
					geturl(row);
				break;
				case"urlRedirection":
					urlRedirection(row, i);
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

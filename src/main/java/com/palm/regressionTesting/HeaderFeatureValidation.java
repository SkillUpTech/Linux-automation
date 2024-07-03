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

public class HeaderFeatureValidation implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	HeaderFeatureLocators headerFeatureLocators;
	String sheetStatus = "Pass";
	
	public HeaderFeatureValidation(ArrayList<ArrayList<String>> sheetData)
	{
		this.sheetData = sheetData;
		
	}
	
	public String start()
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
				case "headerFeatureOnCategory":
					headerFeatureOnCategory(row.get(1));
					break;
				case "headerFeatureOncourse":
					headerFeatureOncourse(row.get(1));
					break;
				case "headerFeatureOnprogram":
					headerFeatureOnprogram(row.get(1));
					break;
				case "headerFeatureOnpartner":
					headerFeatureOnpartner(row.get(1));
					break;
				/*
				 * case "headerFeatureOnAnyPage": headerFeatureOnAnyPage(row.get(1)); break;
				 * case "headerFeatureOnLoginPage": headerFeatureOnLoginPage(row.get(1)); break;
				 * case "headerFeatureOnSignupPage": headerFeatureOnSignupPage(row.get(1));
				 * break;
				 */
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
	
	
	public void headerFeatureOnCategory(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnCategory(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(0).add(i+2, (getStatus.get(i) + "headerFeatureOnCategory - failed"));
			}
		}
	}
	
	public void headerFeatureOncourse(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOncourse(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(1).add(i+2, (getStatus.get(i) + "headerFeatureOncourse - failed"));
			}
		}
	}
	
	public void headerFeatureOnprogram(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnprogram(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(2).add(i+2, (getStatus.get(i) + "headerFeatureOnprogram - failed"));
			}
		}
	}
	
	public void headerFeatureOnpartner(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnpartner(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(3).add(i+2, (getStatus.get(i) + "headerFeatureOnpartner - failed"));
			}
		}
	}
	
	public void headerFeatureOnAnyPage(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnplacement(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(4).add(i+2, (getStatus.get(i) + "headerFeatureOnplacement - failed"));
			}
		}
	}
	
	public void headerFeatureOnLoginPage(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnLoginPage(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(5).add(i+2, (getStatus.get(i) + "headerFeatureOnLoginPage - failed"));
			}
		}
	}
	
	public void headerFeatureOnSignupPage(String data)
	{
		ArrayList<String> getStatus = headerFeatureLocators.headerFeatureOnSignupPage(data);
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFeature").get(6).add(i+2, (getStatus.get(i) + "headerFeatureOnSignupPage - failed"));
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
		System.out.println("HeaderFeature  validation Process started");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		this.headerFeatureLocators = new HeaderFeatureLocators(driver);
		OpenWebsite.openSite(driver);
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "headerFeatureOnCategory":
					headerFeatureOnCategory(row.get(1));
					break;
				case "headerFeatureOncourse":
					headerFeatureOncourse(row.get(1));
					break;
				case "headerFeatureOnprogram":
					headerFeatureOnprogram(row.get(1));
					break;
				case "headerFeatureOnpartner":
					headerFeatureOnpartner(row.get(1));
					break;
				/*
				 * case "headerFeatureOnAnyPage": headerFeatureOnAnyPage(row.get(1)); break;
				 * case "headerFeatureOnLoginPage": headerFeatureOnLoginPage(row.get(1)); break;
				 * case "headerFeatureOnSignupPage": headerFeatureOnSignupPage(row.get(1));
				 * break;
				 */
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

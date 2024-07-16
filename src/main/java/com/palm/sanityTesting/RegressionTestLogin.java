package com.palm.sanityTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.palm.regressionTesting.OpenWebsite;
import com.palm.regressionTesting.ProcessLogin;
import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class RegressionTestLogin
{
	WebDriver driver;
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	ProcessLogin processLogin;
	String sheetStatus = "Pass";
	String browser = "";
	public RegressionTestLogin(ArrayList<ArrayList<String>> sheetData) throws Exception
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
	public String start() throws InterruptedException
	{
		try
		{
			
			driver = this.openDriver(browser);
			OpenWebsite.openSite(this.driver);
			this.processLogin = new ProcessLogin(this.driver);
			String BaseWindow = driver.getWindowHandle();
			driver.switchTo().newWindow(WindowType.TAB);
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
					case "InvalidUsername":
						InvalidUsername();
						break;
					case "InvalidPassword":
						InvalidPassword();
						break;
					case "InvalidUserNameAndPassword":
						InvalidUserNameAndPassword();
						break;
					case "ValidCredentials":
						ValidCredentials();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return sheetStatus;
	}
	
	public void InvalidUsername() throws InterruptedException
	{
		ArrayList<String> credsRow = sheetData.get(0);
		String userName = credsRow.get(1);
		String passWord = credsRow.get(2);
		ArrayList<String> status = this.processLogin.checkInvalidUsername(userName, passWord);
		if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login").get(0).set(0, "InvalidUsername - failed");
		}
	}
	
	public void InvalidPassword() throws InterruptedException
	{
		ArrayList<String> credsRow = sheetData.get(1);
		String userName = credsRow.get(1);
		String passWord = credsRow.get(2);
		ArrayList<String> status = this.processLogin.checkInvalidPassword(userName, passWord);
		if(status.contains("Success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login").get(1).set(0, "InvalidPassword - failed");
		}
	}
	
	public void InvalidUserNameAndPassword() throws InterruptedException
	{
		ArrayList<String> credsRow = sheetData.get(2);
		String userName = credsRow.get(1);
		String passWord = credsRow.get(2);
		ArrayList<String> status = this.processLogin.checkInvalidUserNameAndPassword(userName, passWord);
		if(status.contains("Success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login").get(2).set(0, "InvalidUserNameAndPassword - failed");
		}
	}
	
	public void ValidCredentials() throws InterruptedException
	{
		ArrayList<String> credsRow = sheetData.get(3);
		String userName = credsRow.get(1);
		String passWord = credsRow.get(2);
		ArrayList<String> status = this.processLogin.checkValidCredentials(userName, passWord);
		
		if(status.contains("Failed"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login").get(3).set(0, "ValidCredentials - failed");
		}
	}
	
	private void checkLogout()throws InterruptedException
	{
		this.processLogin.logOutFunction();
	}
}

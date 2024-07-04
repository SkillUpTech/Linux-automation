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

public class LoginPageLinksValidation implements Callable<String>
{
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	String userName;
	WebDriver driver;
	LoginPageLinksLocator loginPageLinksLocator;
	String sheetStatus = "Pass";
	
	public LoginPageLinksValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
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
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			  case "LoginIcon": 
				  LoginIcon(); 
				  break; 
				
				case "ForgotPasswordLink":
					forgotPasswordLink();
					break; 
				case "SignUpLink":
					SignUpLink();
					break; 
				case "TermOfServiceLink": TermOfServiceLink(); break; case
								"PrivacyPolicyLink":
									PrivacyPolicyLink();
									break;
								case "FacebookLink":
									FacebookLink();
									break; 
											  case "GoogleLink": GoogleLink(); break; case "LinkedInLink":
											  LinkedInLink(); break; case "MicrosoftLink": MicrosoftLink(); break; case
											  "SignUpTab": SignUpTab(); break;
											 
											 
							 
							 
				 
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
	
	public void LoginIcon()
	{
		String status = loginPageLinksLocator.checkLoginIconLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(0).set(0, "LoginIcon - failed");
		}
	}
	
	public void forgotPasswordLink()
	{
		String status = loginPageLinksLocator.checkForgotPasswordLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(1).set(0, "forgotPasswordLink - failed");
		}
	}
	public void SignUpLink()
	{
		String status = loginPageLinksLocator.checkSignUpLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(2).set(0, "SignUpLink - failed");
		}
	}
	public void TermOfServiceLink()
	{
		String status = loginPageLinksLocator.checkTermOfServiceLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(3).set(0, "TermOfServiceLink - failed");
		}
	}
	public void PrivacyPolicyLink()
	{
		String status = loginPageLinksLocator.checkPrivacyPolicyLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(4).set(0, "PrivacyPolicyLink - failed");
		}
	}
	public void FacebookLink()
	{
		String status = loginPageLinksLocator.checkFacebookLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(5).set(0, "FacebookLink - failed");
		}
	}
	public void GoogleLink()
	{
		String status = loginPageLinksLocator.checkGoogleLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(6).set(0, "GoogleLink - failed");
		}
	}
	public void LinkedInLink()
	{
		String status = loginPageLinksLocator.checkLinkedInLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(7).set(0, "LinkedInLink - failed");
		}
	}
	public void MicrosoftLink()
	{
		String status = loginPageLinksLocator.checkMicrosoftLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(8).set(0, "MicrosoftLink - failed");
		}
	}
	public void SignUpTab()
	{
		String status = loginPageLinksLocator.checkSignUpTab();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginPageLinks").get(9).set(0, "SignUpTab - failed");
		}
	}
	@Override
	public String call() throws Exception 
	{
		System.out.println("Sign up validation begins");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.loginPageLinksLocator = new LoginPageLinksLocator(this.driver);
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			  case "LoginIcon": 
				  LoginIcon(); 
				  break; 
				
				case "ForgotPasswordLink":
					forgotPasswordLink();
					break; 
				case "SignUpLink":
					SignUpLink();
					break; 
				case "TermOfServiceLink": 
					TermOfServiceLink(); 
					break; 
				case "PrivacyPolicyLink":
					PrivacyPolicyLink();
					break;
				case "FacebookLink":
					FacebookLink();
					break; 
				case "GoogleLink": 
					  GoogleLink(); 
					  break; 
				case "LinkedInLink":
						LinkedInLink(); 
						break; 
				case "MicrosoftLink": 
					MicrosoftLink(); 
					break; 
				case  "SignUpTab": 
					SignUpTab(); 
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

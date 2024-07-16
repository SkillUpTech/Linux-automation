package com.palm.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class SignUpPageLinksValidation 
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	SignUpPageLinksLocator signUpPageLinksLocator;
	String sheetStatus = "Pass";
	
	public SignUpPageLinksValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.signUpPageLinksLocator = new SignUpPageLinksLocator(driver);
		System.out.println("Sign up Page links process started");
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
				case "signup":
					signup();
					break;
				case "facebook":
					facebook();
					break;
				case "google":
					google();
					break;
				case "linkedIn":
					linkedIn();
					break;
				case "microsoft":
					microsoft();
					break;
				case "LogInLink":
					LogInLink();
					break;
				case "TermsOfService":
					TermsOfService();
					break;
				case "PrivacyPolicy":
					PrivacyPolicy();
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
	
	public void signup()
	{
		String status = signUpPageLinksLocator.checkSignupLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(0).set(0, "signup - failed");
		}
	}
	public void facebook()
	{
		String status = signUpPageLinksLocator.checkFacebookLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(1).set(0, "facebook - failed");
		}
	}
	
	public void google()
	{
		String status = signUpPageLinksLocator.checkGoogleLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(2).set(0, "google - failed");
		}
	}
	
	public void linkedIn()
	{
		String status = signUpPageLinksLocator.checkLinkedInLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(3).set(0, "linkedIn - failed");
		}
	}
	
	public void microsoft()
	{
		String status = signUpPageLinksLocator.checkMicrosoftLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(4).set(0, "microsoft - failed");
		}
	}
	
	public void LogInLink()
	{
		String status = signUpPageLinksLocator.checkLogInLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(5).set(0, "LogInLink - failed");
		}
	}
	
	public void TermsOfService()
	{
		String status = signUpPageLinksLocator.checkTermsOfServiceLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(6).set(0, "TermsOfService - failed");
		}
	}
	
	public void PrivacyPolicy()
	{
		String status = signUpPageLinksLocator.checkPrivacyPolicyLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUpPageLinks").get(7).set(0, "PrivacyPolicy - failed");
		}
	}
	
	
}

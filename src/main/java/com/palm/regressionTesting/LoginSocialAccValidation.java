package com.palm.regressionTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class LoginSocialAccValidation 
{
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	LoginSocialAccLocator loginSocialAccLocator;
	String sheetStatus = "Pass";
	
	public LoginSocialAccValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		
		this.loginSocialAccLocator = new LoginSocialAccLocator(driver);
		System.out.println("login process started");
		//this.start();
	}
	public String start() throws InterruptedException
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
				case "FACEBOOK":
					FACEBOOK();
					break;
				case "GOOGLE":
					GOOGLE();
					break;
				case "LINKEDIN":
					LINKEDIN();
					break;
				case "MICROSOFT":
					MICROSOFT();
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
				else
				{
					driver.switchTo().window(win);
					driver.switchTo().window(BaseWindow);
				}
			}
			
		}
		return sheetStatus;
	}
	
	private void FACEBOOK() throws InterruptedException
	{
		ArrayList<String> statusOfLogin = new ArrayList<String>();
		ArrayList<String> statusOfFacebook = new ArrayList<String>();
		ArrayList<String> credsRow = sheetData.get(0);
		if(!credsRow.contains("NA"))
		{
			String email_mbl = credsRow.get(1);
			String pwd = credsRow.get(2);
			String userName = credsRow.get(3);
			String passWord = credsRow.get(4);
			statusOfFacebook.addAll(loginSocialAccLocator.checkFacebook(email_mbl, pwd));
			statusOfLogin.add(loginSocialAccLocator.loginFunction(userName, passWord));
			if(!statusOfFacebook.contains("success"))
			{
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).set(0, "FACEBOOK - failed");
				if(!statusOfLogin.contains("success"))
				{sheetStatus = "Fail";
					String username = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).set(3, (username + " - failed"));
					String password = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).set(4, (pwd + " - failed"));
				}
			}
		}
	}
	
	private void GOOGLE() throws InterruptedException
	{
		ArrayList<String>  statusOfLogin = new ArrayList<String>();
		ArrayList<String> statusOfGoogle = new ArrayList<String>();
		ArrayList<String> credsRow = sheetData.get(1);
		if(!credsRow.contains("NA"))
		{
			String email_mbl = credsRow.get(1);
			String pwd = credsRow.get(2);
			String userName = credsRow.get(3);
			String passWord = credsRow.get(4);
			statusOfGoogle.add(loginSocialAccLocator.checkGoogle(email_mbl, pwd));
			statusOfLogin.add(loginSocialAccLocator.loginFunction(userName, passWord));
			if(!statusOfGoogle.contains("success"))
			{
				if(!statusOfLogin.contains("success"))
				{sheetStatus = "Fail";
					String username = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(1).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(1).set(3, (username + " - failed"));
					String password = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(1).set(4, (pwd + " - failed"));
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(1).set(0, "GOOGLE - failed");
				}
			}
		}
	}
	
	private void LINKEDIN() throws InterruptedException
	{
		ArrayList<String> statusOfLogin = new ArrayList<String>();
		ArrayList<String> statusOfLinkedIn = new ArrayList<String>();
		ArrayList<String> credsRow = sheetData.get(2);
		if(!credsRow.contains("NA"))
		{
			String email_mbl = credsRow.get(1);
			String pwd = credsRow.get(2);
			String userName = credsRow.get(3);
			String passWord = credsRow.get(4);
			statusOfLinkedIn.addAll(loginSocialAccLocator.checkLinkedIn(email_mbl, pwd));
			statusOfLogin.add(loginSocialAccLocator.loginFunction(userName, passWord));
			if(!statusOfLinkedIn.contains("success"))
			{
				if(!statusOfLogin.contains("success"))
				{sheetStatus = "Fail";
					String username = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(2).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(2).set(3, (username + " - failed"));
					String password = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(0).get(3);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(2).set(4, (pwd + " - failed"));
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(2).set(0, "LINKEDIN - failed");
				}
			}
		}
	}
	
	private void MICROSOFT() throws InterruptedException
	{
		ArrayList<String> statusOfLogin = new ArrayList<String>();
		ArrayList<String> statusOfMicrosoft = new ArrayList<String>();
		ArrayList<String> credsRow = sheetData.get(3);
		if(!credsRow.contains("NA"))
		{
			statusOfMicrosoft.add(loginSocialAccLocator.checkMicrosoft());
			statusOfLogin.add(loginSocialAccLocator.checkUserAfterLoggedIn());
			if(!statusOfMicrosoft.contains("success"))
			{
				if(!statusOfLogin.contains("success"))
				{sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("LoginWithSocialAcc").get(3).set(0, "MICROSOFT - failed");
				}
			}
		}
	}
	
}

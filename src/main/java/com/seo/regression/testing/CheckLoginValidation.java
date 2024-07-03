package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class CheckLoginValidation
{
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	SignUpLocator signUpLocator;
	ProcessLogin processLogin;
	String sheetStatus = "Pass";
	
	public CheckLoginValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.signUpLocator = new SignUpLocator(this.driver);
		this.processLogin = new ProcessLogin(this.driver);
		System.out.println("Sign up validation begins");
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
			  case "validData":
					validData(row);
					break;
			  case "verifyLogin":
				  InvalidPassword();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetStatus;
	}
	
	public void validData(ArrayList<String> dataFromExcel)
	{
		this.verifyInsertedData(sheetData.get(0), 3);
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
	public void verifyInsertedData(ArrayList<String> data, int index)
	{
		try
		{
			ArrayList<String> status = new ArrayList<String>();
			ArrayList<Integer> verifyValidData = signUpLocator.checkSignupWithValidData(data);
			for(int i = 0; i < verifyValidData.size(); i++)
			{
					if(verifyValidData.get(i).equals(1))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).get(1);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(1, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(2))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).get(2);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(2, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(3))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).get(3);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(3, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(3))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).get(4);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(4, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(4))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).get(5);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(5, (cellValue + " - failed"));
						status.add("Failed");
					}	
				}
				if(status.contains("Failed"))
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Login if mail id not verified").get(index).set(0, "validData - failed"); 
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

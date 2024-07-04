package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class AccountPageValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	AccountPageLocator accountPageLocator;
	String sheetStatus = "Pass";
	
	public AccountPageValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.accountPageLocator = new AccountPageLocator(driver);
		System.out.println("Account page process started");
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
				case "checkInitial":
					checkInitial(row);
					break;
				case "checkAccount":
					checkAccount();
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
	
	public void checkInitial(ArrayList<String> data) 
	{
		ArrayList<String> status = accountPageLocator.checkInitial(data);
		
		for(int i = 0; i < status.size(); i++)
		{
			if(data.contains(status.get(i)))
			{
				sheetStatus = "Fail";
				int position = data.indexOf(status.get(i));
				String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("AccountPage").get(0).get(position);
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("AccountPage").get(0).set(position, (cellValue + " - failed"));
			}
		}
	}
	
	public void checkAccount() 
	{
		String status = accountPageLocator.checkAccount();
		
		if(status.contains("fail"))
		{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("AccountPage").get(1).set(0, "checkAccount - failed");

		}	
	}
}

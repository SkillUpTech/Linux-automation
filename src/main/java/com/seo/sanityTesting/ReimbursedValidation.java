package com.seo.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class ReimbursedValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	ReimbursedLocator reimbursedLocator;
	String sheetStatus = "Pass";
	
	public ReimbursedValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.reimbursedLocator = new ReimbursedLocator(driver);
		System.out.println("Microsoft validation Process started");
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
				case "launchCourse":
					launchCourse(row.get(1));
					break;
				case "checkReimbursedLink":
					checkReimbursedLink();
					break;
				case "checkLinkFromReimbursedPage":
					checkLinkFromReimbursedPage();
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
	
	public void launchCourse(String data)
	{
		String status = reimbursedLocator.checkLaunchCourse(data);
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ReimbursedProcess").get(0).set(0, "launchCourse - failed");
		}
	}
	
	public void checkReimbursedLink()
	{
		String status = reimbursedLocator.checkReimbursedLink();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ReimbursedProcess").get(1).set(0, "checkReimbursedLink - failed");
		}
	}
	
	public void checkLinkFromReimbursedPage()
	{
		String status = reimbursedLocator.checkLinkFromReimbursedPage();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ReimbursedProcess").get(2).set(0, "checkLinkFromReimbursedPage - failed");
		}
	}
}

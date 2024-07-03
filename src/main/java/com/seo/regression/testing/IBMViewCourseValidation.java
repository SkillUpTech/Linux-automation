package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import com.palm.regressionTesting.RegressionTesting;

public class IBMViewCourseValidation 
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	IBMViewCourseLocator ibmViewCourseLocator;
	String sheetStatus = "Pass";
	
	public IBMViewCourseValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.ibmViewCourseLocator = new IBMViewCourseLocator(driver);
		System.out.println("Ibm View Course Url verification process started");
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
			case "login":
				checkLogin(row.get(1), row.get(2));
				break;
			case "checkIBMCourse":
				verifyIBMCourse();
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
	
	public void checkLogin(String user, String password)
	{
		String status = ibmViewCourseLocator.checkLogin(user, password);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("login").get(0).set(0, "login  - failed");

		}
	}
	
	public void verifyIBMCourse()
	{
		ArrayList<String> status = ibmViewCourseLocator.verifyIBMCourse();
		if(status.contains("fail"))
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("login").get(1).add(i+1, (status.get(i) + "verifyIBMCourse - failed"));
			}

		}
	}
}

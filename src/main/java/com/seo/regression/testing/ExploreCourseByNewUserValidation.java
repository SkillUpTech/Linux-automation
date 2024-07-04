package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class ExploreCourseByNewUserValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	ExploreCourseByNewUserLocator exploreCourseByNewUserLocator;
	String sheetStatus = "Pass";
	
	public ExploreCourseByNewUserValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.exploreCourseByNewUserLocator = new ExploreCourseByNewUserLocator(driver);
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
				case "Signup":
					Signup(row);
					break;
				case "CheckExploreCourse":
					CheckExploreCourse();
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
	
	public void Signup(ArrayList<String> data)
	{
		ArrayList<String> status = exploreCourseByNewUserLocator.checkSignup(data);
		if(status.contains("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ExploreCourseByNewUser").get(0).set(0, "Signup - failed");
		}
	}
	
	public void CheckExploreCourse()
	{
		String status = exploreCourseByNewUserLocator.CheckExploreCourse();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ExploreCourseByNewUser").get(1).set(0, "CheckExploreCourse - failed");
		}
	}
}

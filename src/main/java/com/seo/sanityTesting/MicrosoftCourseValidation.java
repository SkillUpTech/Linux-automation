package com.seo.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class MicrosoftCourseValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	MicrosoftCourseLocator microsoftCourseLocator;
	String sheetStatus = "Pass";
	
	public MicrosoftCourseValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(driver);
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
				case "MicrosftPage":
					MicrosftPage();
					break;
				case "MicrosoftScourses":
					MicrosoftScourses();
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
	boolean statusOfPage = true;
	public void MicrosftPage()
	{
		ArrayList<String> getStatus = microsoftCourseLocator.verifyMicrosftPage();
		if(getStatus.contains("fail"))
		{
			if(getStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				statusOfPage = false;
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("MicrosoftPage").get(0).set(0, "MicrosftPage - failed");
			}
		}
	}
	
	public void MicrosoftScourses()
	{
		if(statusOfPage == true)
		{
			ArrayList<String> getStatus = microsoftCourseLocator.verifyMicrosoftScourses();
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("MicrosoftPage").get(1).add(i+1, (getStatus.get(i) + "MicrosoftScourses - failed"));
				}
			}
		}
	}
}

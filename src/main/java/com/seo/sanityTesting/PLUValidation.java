package com.seo.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class PLUValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	PLULocators PLUPageLocators;
	String sheetStatus = "Pass";
	WebDriver driver;
	OpenWebsite openWebsite;
	
	public PLUValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.PLUPageLocators = new PLULocators(driver);
		System.out.println("PLU process started");
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
				case "techPrograms":
					techPrograms(row);
					break;
				case "PLUCourses":
					PLUCourses(row);
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
	
	
	public void techPrograms(ArrayList<String> programs)
	{
		if(!programs.contains("NA"))
		{
			ArrayList<String> failTechPgm = this.PLUPageLocators.verifyPrograms(programs);
			if(failTechPgm.contains("fail"))
			{
				if(failTechPgm.size()>0)
				{
					for(int i = 0; i < failTechPgm.size(); i++)
					{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(0).add(i+1, (failTechPgm.get(i) + " - failed"));
					}
				}
			}
		}
	}
	public void PLUCourses(ArrayList<String> courses)
	{
		if(!courses.contains("NA"))
		{
			ArrayList<String> failedUrls = this.PLUPageLocators.verifyPLUCourse(courses);
			for(int i = 0; i < failedUrls.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Pacific").get(1).add(i+1, (failedUrls.get(i) + " - failed"));
			}
		}
	}
}

package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class CourseLevelValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	CourseLevelLocators courseLevelLocators;
	String sheetStatus = "Pass";
	
	public CourseLevelValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.courseLevelLocators = new CourseLevelLocators(driver);
		System.out.println("Course Level validation process started");
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
				case "HomePage":
					checkHomePage();
					break;
				case "categoryPages":
					checkCategoryPages(row);
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
	
	public void checkHomePage()
	{
		ArrayList<String> status = courseLevelLocators.checkSelfPacedVILTOnHomePage();
		if(status.size()>0)
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("CheckVILTSelfPacedCourse").get(0).add(i+1, (status.get(i) + "HomePage - failed"));
			}

		}
	}
	
	public void checkCategoryPages(ArrayList<String> data)
	{
		ArrayList<String> status = courseLevelLocators.checkSelfPacedVILTOnCategory(data);
		if(status.size()>0 || status.contains("fail"))
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("CheckVILTSelfPacedCourse").get(1).add(i+1, (status.get(i) + "categoryPages - failed"));
			}
		}
	}
}

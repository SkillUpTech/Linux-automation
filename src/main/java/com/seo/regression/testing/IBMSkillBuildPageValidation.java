package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class IBMSkillBuildPageValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	IBMSkillBuildPageLocator ibmSkillBuildPageLocator;
	String sheetStatus = "Pass";
	
	public IBMSkillBuildPageValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.ibmSkillBuildPageLocator = new IBMSkillBuildPageLocator(driver);
		System.out.println("HeaderFeature  validation Process started");
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
				case "headerFeatureOnPage":
					headerFeatureOnPage(row.get(1));
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
	
	public void headerFeatureOnPage(String data)
	{
		ArrayList<String> getStatus = ibmSkillBuildPageLocator.headerFeatureOnCategory(data);
		if(getStatus.contains("fail"))
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBMSkillBuildPage").get(0).add(i+1, (getStatus.get(i) + "headerFeatureOnPage - failed"));
			}
		}
	}
}

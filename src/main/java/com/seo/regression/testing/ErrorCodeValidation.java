package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class ErrorCodeValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	ErrorCodeLocator errorCodeLocator;
	String sheetStatus = "Pass";
	
	public ErrorCodeValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		this.sheetData = sheetData;
		this.driver = driver;
		
		this.errorCodeLocator = new ErrorCodeLocator(this.driver);
		System.out.println("error code validation process started");
	}
	
	public String start()
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(this.driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case"url":
					geturl(row);
				break;
				case"urlRedirection":
					urlRedirection(row, i);
				break;
			}
		}
		if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
		{
			driver.close();
			driver.switchTo().window(BaseWindow);
		}
		else
		{
			driver.switchTo().window(BaseWindow);
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
	
	
	public void geturl(ArrayList<String> codeFromExcel)
	{
		if(!codeFromExcel.contains("No"))
		{
			ArrayList<String> checkURL = errorCodeLocator.checkCourseCode(codeFromExcel);
			for(int i = 0; i < checkURL.size(); i++)
			{
				if(codeFromExcel.contains(checkURL.get(i)))
				{
					sheetStatus = "Fail";
					int position = codeFromExcel.indexOf(checkURL.get(i));
					String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(0).get(position);
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(0).set(position, (cellValue + " - failed"));
				}
			}
		}
	}
		
	public void urlRedirection(ArrayList<String> codeFromExcel, int rowIndex)
	{
			ArrayList<String> checkURL = errorCodeLocator.checkURLRedirection(codeFromExcel);
			int i = -1;
			if(checkURL.size()>0)
			{
				sheetStatus = "Fail";
				i = 2;
			}
			if( i > -1 )
			{
				String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(rowIndex).get(i);
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("URLValidation").get(rowIndex).set(i, (cellValue + " - failed"));
			}
	}
}

package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.palm.regressionTesting.IBMViewCourseLocator;
import com.palm.regressionTesting.OpenWebsite;
import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class verifyProgramURLValidation 
{

	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	VerifyProgramURLLocator verifyProgramURLLocator;
	String sheetStatus = "Pass";
	
	public verifyProgramURLValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.verifyProgramURLLocator = new VerifyProgramURLLocator(driver);
		System.out.println("ProgramURL and SLUG verification started");
	}
	
	
	public String start() throws InterruptedException
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		com.seo.regression.testing.OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "courseURL":
					checkProgramURL(row);
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
				if(driver.getCurrentUrl().equalsIgnoreCase(com.seo.regression.testing.OpenWebsite.setHost+"/"))
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
				else if(!driver.getCurrentUrl().equalsIgnoreCase(com.seo.regression.testing.OpenWebsite.setHost+"/"))
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
	
	public void checkProgramURL(ArrayList<String> data)
	{
		ArrayList<String> status = verifyProgramURLLocator.verifyProgramURL(data);
		if(status.size()>0)
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBM").get(0).add(((status.size())+1), (status.get(i) + "IBMcourses - failed"));
			}
		}
	}
}

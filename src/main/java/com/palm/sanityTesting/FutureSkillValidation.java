package com.palm.sanityTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.palm.regressionTesting.GLXLocator;
import com.palm.regressionTesting.OpenWebsite;
import com.palm.regressionTesting.RegressionTesting;
import com.regression.utility.TestUtil;

public class FutureSkillValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	FutureSkillLocator futureSkillLocator;
	String sheetStatus = "Pass";

	public FutureSkillValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		
		this.sheetData = sheetData;
		this.futureSkillLocator = new FutureSkillLocator(driver);
		OpenWebsite.openSite(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		System.out.println("Onboarding Journey Process started");
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
				case "url":
					launchSite(row.get(1));
					break;
				case "findOutMore_Button":
					findOutMore_Button();
					break;
				case "learnmore_Button":
					learnmore_Button();
					break;
				case "Cards":
					Cards();
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
				if(driver.getCurrentUrl().equalsIgnoreCase(com.palm.sanityTesting.OpenWebsite.setHost+"/"))
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
				else if(!driver.getCurrentUrl().equalsIgnoreCase(com.palm.sanityTesting.OpenWebsite.setHost+"/"))
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
	
	public void launchSite(String url)
	{
		String status = futureSkillLocator.launchSite(url);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("TechMaster").get(0).set(0, "url - failed");
		}
	}
	public void findOutMore_Button()
	{
		String status = futureSkillLocator.findOutMore_Button();
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("TechMaster").get(1).set(0, "findOutMore_Button - failed");
		}
	}
	public void learnmore_Button()
	{
		String status = futureSkillLocator.learnmore_Button();
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("TechMaster").get(2).set(0, "findOutMore_Button - failed");
		}
	}
	public void Cards()
	{
		ArrayList<String> status = futureSkillLocator.Cards();
		if(status.size()>0)
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("TechMaster").get(1).add(i+1, (status.get(i) + "Cards - failed"));
			}
		}
	}
	
	
	
}

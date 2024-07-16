package com.palm.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class HomePageValidator 
{
	private String SHEET_NAME;
	ArrayList<ArrayList<String>> sheetData;
	WebDriver driver;
	HomepageLocator homepageLocator;
	String sheetStatus;
	
	public HomePageValidator(WebDriver driver, String sheetName, ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.driver = driver;
		this.SHEET_NAME = sheetName; 
		this.sheetData = sheetData;
		this.homepageLocator = new HomepageLocator(this.driver);
		System.out.println("Homepage process started");
		sheetStatus = "Pass";
	}

	public void homePage(WebDriver driver)
	{
		if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)))
		{
			String url = OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE);
			driver.get(url);
		}
		else
		{
			System.out.println("you already in home page");
		}
	}
	public String start() throws InterruptedException
	{
		try
		{
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		this.homePage(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			case "Banner":
				verifyBanner(row);
				break;
			case"learningPartners":
				verifyLearningPartners();
				break;
			case"humanSkills":
				verifyHumanSkills();
				break;
			case"topTechCategories":
				verifyTopTechCategories();
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

	
	public void verifyBanner(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<String> status = homepageLocator.checkSliderLink(dataFromExcel);
		{
			for(int i = 0; i < status.size(); i++)
			{
				if(status.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(0).add(i+1, (status.get(i) + "Banner - failed"));

				}
			}
		}
	}
	public void verifyLearningPartners()
	{
			ArrayList<String> statusOfLearningPartners = homepageLocator.checkLearningPartners();
			if(statusOfLearningPartners.size()>0)
			{
				for(int i = 0; i < statusOfLearningPartners.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(1).add(i+1, (statusOfLearningPartners.get(i) + "MicrosoftScourses - failed"));
				}
			}
	}
	public void verifyLearningCatalog(String dataFromExcel) throws InterruptedException
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> statusOfLearningPartners = homepageLocator.checkLearningCatalog();
			if(statusOfLearningPartners.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(1).set(0, "learningCatalog - failed");
			}
		}
	}
	public void verifyHumanSkills()
	{
			ArrayList<String> statusOfHumanSkills = homepageLocator.checkHumanSkills();
			for(int i = 0; i < statusOfHumanSkills.size(); i++)
			{
				if(statusOfHumanSkills.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(2).add(i+1, (statusOfHumanSkills.get(i) + "humanSkills - failed"));
				}
			}
	}
	
	public void verifyTopTechCategories()
	{
			ArrayList<String> statusOfTopTechCategories = homepageLocator.checkTopTechCategories();
			for(int i = 0; i < statusOfTopTechCategories.size(); i++)
			{
				if(statusOfTopTechCategories.size()>0)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HomePage").get(3).add(i+1, (statusOfTopTechCategories.get(i) + "topTechCategories - failed"));
				}
			}
	}
}

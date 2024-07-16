package com.seo.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class FluidEducationValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	FluidEducationLocator fluidEducationLocator;
	String sheetStatus = "Pass";
	
	public FluidEducationValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.fluidEducationLocator = new FluidEducationLocator(driver);
		System.out.println("fluidEducation process started");
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
				case "facebook":
					facebook();
					break;
				case "instagram":
					instagram();
					break;
				case "twitter":
					twitter();
					break;
				case "CTADownloadDetails":
					CTADownloadDetails();
					break;
				case "programs":
					programs(row);
					break;
				case "ExploreCourse":
					ExploreCourse();
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
	
	public void facebook()
	{
		String status = fluidEducationLocator.facebookProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(0).set(0, "facebook - failed");
		}
	}
	public void instagram()
	{
		String status = fluidEducationLocator.instagramProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(1).set(0, "instagram - failed");
		}
	}
	public void twitter()
	{
		String status = fluidEducationLocator.twitterProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(2).set(0, "twitter - failed");
		}
	}
	public void CTADownloadDetails()
	{
		String status = fluidEducationLocator.CTADownloadingProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(3).set(0, "CTADownloadDetails - failed");
		}
	}
	public void programs(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = fluidEducationLocator.verifyFluidEducationProgram();
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(4).add(i+1, (getStatus.get(i) + "programs - failed"));
				}
			}
		}
	}
	public void ExploreCourse()
	{
		ArrayList<String> getStatus = fluidEducationLocator.verifyFluidEducationCourse();
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Fluideducation").get(5).add(i+1, (getStatus.get(i) + "programs - failed"));
			}
		}
	}
}

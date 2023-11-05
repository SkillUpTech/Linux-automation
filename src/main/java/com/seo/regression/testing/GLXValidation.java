package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class GLXValidation 
{

	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	GLXLocator glxLocator;
	String sheetStatus = "Pass";
	
	public GLXValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.glxLocator = new GLXLocator(driver);
		System.out.println("fluidEducation process started");
		//this.start();
	}
	
	public String start() throws InterruptedException
	{
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
					programs();
					break;
				case "ExploreCourse":
					  ExploreCourse(); 
					  break;
			}
		}
		return sheetStatus;
	}
	
	public void facebook()
	{
		String status = glxLocator.facebookProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(0).set(0, "facebook - failed");
		}
	}
	public void instagram()
	{
		String status = glxLocator.instagramProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(1).set(0, "instagram - failed");
		}
	}
	public void twitter()
	{
		String status = glxLocator.twitterProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(2).set(0, "twitter - failed");
		}
	}
	public void CTADownloadDetails()
	{
		String status = glxLocator.CTADownloadingProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(0).set(0, "CTADownloadDetails - failed");
		}
	}
	public void programs()
	{
		ArrayList<String> getStatus = glxLocator.verifyGLXProgram();
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(1).add(i+1, (getStatus.get(i) + "programs - failed"));
			}
		}
	}
	public void ExploreCourse() throws InterruptedException
	{
		String status = glxLocator.ExploreCourseProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(2).set(0, "ExploreCourse - failed");
		}
	}

}

package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class IBMPageValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	IBMPageLocator ibmPageLocator;
	String sheetStatus = "Pass";
	
	public IBMPageValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver)
	{
		OpenWebsite.openSite(driver);
		this.sheetData = sheetData;
		this.driver = driver;
		this.ibmPageLocator = new IBMPageLocator(driver);
		System.out.println("Microsoft validation Process started");
		//this.start();
	}
	
	public String start()
	{
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "IBMPage":
					IBMPage();
					break;
				
				
				  case "IBMCourses":
					  IBMCourses();
					  break;
				 
				 
				case"IBMPrograms":
					IBMPrograms();
					break;
			}
		}
		return sheetStatus;
	}
	
	public void IBMPage()
	{
		ArrayList<String> getStatus = ibmPageLocator.verifyIBMPage();
		if(getStatus.contains("fail"))
		{
			if(getStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBM").get(0).set(0, "IBMPage - failed");
			}
		}
	}
	
	public void IBMCourses()
	{
		ArrayList<String> getStatus = ibmPageLocator.verifyIBMcourses();
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBM").get(2).add(i+1, (getStatus.get(i) + "IBMcourses - failed"));
			}
		}
	}
	
	public void IBMPrograms()
	{
		ArrayList<String> getStatus = ibmPageLocator.verifyIBMProgram();
		if(getStatus.size()>0)
		{
			for(int i = 0; i < getStatus.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("IBM").get(1).add(i+1, (getStatus.get(i) + "IBMProgram - failed"));
			}
		}
	}
}
										
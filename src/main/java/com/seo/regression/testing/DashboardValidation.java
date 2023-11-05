package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class DashboardValidation 
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	DashboardLocator dashboardLocator;
	RegressionGenericValidator regressionGenericValidator;
	String sheetStatus = "Pass";
	
	public DashboardValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.sheetData = sheetData;
		this.dashboardLocator = new DashboardLocator(this.driver);
		System.out.println("Enrolling Flat Price and validating details in Dashboard");
	}
	
	public String start() throws InterruptedException
	{
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "url": 
					  url(row.get(1)); 
					  break;
				case "EnrollFlatPrice": 
					  EnrollFlatPrice(row); 
					  break;
				case "continueOnDashboard":
					continueOnDashboard();
					break;
			}
		}
		return sheetStatus;
	}
	
	public void url(String url)
	{
		String urlStatus = dashboardLocator.openSite(url);
		if(urlStatus.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(1).set(0, "url - failed");

		}
	}
	
	public void EnrollFlatPrice(ArrayList<String> data)
	{
		ArrayList<String> verifyEnrollmentProcess = new ArrayList<String>();
		try
		{
			if(!verifyEnrollmentProcess.contains("NA"))
			{
				verifyEnrollmentProcess = dashboardLocator.EnrollFlatPrice(data);
				for(int i = 0; i < verifyEnrollmentProcess.size(); i++)
				{
					if(verifyEnrollmentProcess.get(0).contains("fail"))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(1);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(1, (cellValue + " - failed"));
					}
					if(verifyEnrollmentProcess.get(1).contains("fail"))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(2);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(2, (cellValue + " - failed"));
					}
					if(verifyEnrollmentProcess.get(2).contains("fail"))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(3);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(3, (cellValue + " - failed"));
					}
				}
				if(verifyEnrollmentProcess.contains("fail"))
				{
					sheetStatus = "Fail";
					regressionGenericValidator.markProcessFailed();
				}
			}
			else
			{
				regressionGenericValidator.markProcessIgnored();
			}
		}
		catch(Exception e)
		{
			sheetStatus = "Fail";
			regressionGenericValidator.markProcessFailed();
		}
	}
	
	public void continueOnDashboard()
	{
		
	}
}

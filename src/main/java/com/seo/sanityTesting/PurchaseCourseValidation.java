package com.seo.sanityTesting;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import com.seo.regression.testing.RegressionTesting;
import com.seo.sanityTesting.OpenWebsite;
import com.seo.sanityTesting.PurchaseCourseLocator;

public class PurchaseCourseValidation
{
	
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	PurchaseCourseLocator purchaseCourseLocator;
	String sheetStatus = "Pass";
	
	public PurchaseCourseValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.purchaseCourseLocator = new PurchaseCourseLocator(driver);
		System.out.println("puchase course page process started");
	}
	
	public String start() throws InterruptedException
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
				case "url":
					url(row.get(1));
					break;
				case "enrollment":
					enrollment(row);
					break;
				case "chekProfileSection":
					chekProfileSection(row);
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
	public void url(String data)
	{
		String status = purchaseCourseLocator.launchURL(data);
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(0).set(0, "url - failed");
		}
	}
	
	public void enrollment(ArrayList<String> enrollDataFromExcel)
	{
		ArrayList<String> verifyEnrollmentProcess = new ArrayList<String>();
		try
		{
			if(!enrollDataFromExcel.contains("NA"))
			{
				verifyEnrollmentProcess = purchaseCourseLocator.enroll(enrollDataFromExcel);
				if(verifyEnrollmentProcess.contains("Fail"))
				{
					for(int i = 0; i < verifyEnrollmentProcess.size(); i++)
					{
						if(verifyEnrollmentProcess.get(1).contains("loginFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(2).contains("choosePlanFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(3).contains("razorpayFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(4).contains("paymentFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
						if(verifyEnrollmentProcess.get(5).contains("orderDetailFail"))
						{
							sheetStatus = "Fail";
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("purchaseCourse").get(1).add(verifyEnrollmentProcess.size()+i, (verifyEnrollmentProcess.get(i) + "enrollment - failed"));
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			sheetStatus = "Fail";
		}
	}
	public void chekProfileSection(ArrayList<String> data)
	{
		ArrayList<String> checkProfile = purchaseCourseLocator.programLocator();
		if(checkProfile.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("GLX").get(2).set(0, "chekProfileSection - failed");
		}
	}
}

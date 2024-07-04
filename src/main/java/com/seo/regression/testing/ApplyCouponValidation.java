package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class ApplyCouponValidation
{
	ArrayList<ArrayList<String>> sheetData = null;
	ApplyCouponLocator applyCouponLocator;
	RegressionGenericValidator regressionGenericValidator;
	String sheetStatus = "Pass";
	WebDriver driver;
	
	public ApplyCouponValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.applyCouponLocator = new ApplyCouponLocator(driver);
		this.regressionGenericValidator = new RegressionGenericValidator(driver);
		System.out.println("Coupon validation started");
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
				case "launchCourse": 
					launchCourse(row); 
					  break;
					  
				case "ApplyCoupon": 
					ApplyCoupon(row); 
					  break;
				case "ResultForAppliedcoupon": 
					ResultForAppliedcoupon(); 
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
	
	public void launchCourse(ArrayList<String> data)
	{
		ArrayList<String> status = applyCouponLocator.launchCourse(data);
		if(status.contains("fail"))
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				int position = data.indexOf(status.get(i));
				String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ApplyCoupon").get(0).get(position); 
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ApplyCoupon").get(0).set(position, (cellValue + " - failed"));
			}
		}
	}
	
	public void ApplyCoupon(ArrayList<String> data)
	{

		ArrayList<String> checkCoupon = applyCouponLocator.ApplyCoupon(data);
		if(checkCoupon.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ApplyCoupon").get(1).set(0, "ApplyCoupon - failed");
		}
	}
	
	public void ResultForAppliedcoupon()
	{
		ArrayList<String> checkCouponResult = applyCouponLocator.couponResult();
		for(int i = 0; i < checkCouponResult.size(); i++)
		{
			if(checkCouponResult.size()>0)
			{
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ApplyCoupon").get(2).add(i+1, (checkCouponResult.get(i) + "ResultForAppliedcoupon"));
			}
		}
	}
}

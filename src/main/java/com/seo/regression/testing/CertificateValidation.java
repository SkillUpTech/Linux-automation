package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class CertificateValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	CertificateLocators certificateLocators;
	String sheetStatus = "Pass";
	
	public CertificateValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.certificateLocators = new CertificateLocators(driver);
		System.out.println("certificate process started");
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
				case "LoginStaffUser":
					LoginStaffUser(row);
					break;
				case "checkCertificate":
					checkCertificate(row);
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
	
	public void LoginStaffUser(ArrayList<String> data)
	{
		String status = "fail";
		ArrayList<String> twitterProcess = certificateLocators.LoginStaffUser(data);
		if(twitterProcess.contains(status))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ViewCertificate").get(0).set(0, "LoginStaffUser - failed");
		}
	}
	
	public void checkCertificate(ArrayList<String> data)
	{
		String status = "fail";
		ArrayList<String> twitterProcess = certificateLocators.checkCertificate(data);
		if(twitterProcess.contains(status))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("ViewCertificate").get(1).set(0, "checkCertificate - failed");
		}
	}
}

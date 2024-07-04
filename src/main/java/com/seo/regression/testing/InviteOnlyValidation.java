package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class InviteOnlyValidation 
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	InviteOnlyLocator inviteOnlyLocator;
	String sheetStatus = "Pass";
	
	public InviteOnlyValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.inviteOnlyLocator = new InviteOnlyLocator(driver);
		System.out.println("Invite only process started");
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
			/*
			 * case "checkStatusCode_inviteOnlyCourse":
			 * checkStatusCode_inviteOnlyCourse(row); break; case
			 * "checkDate_inviteOnlyCourse": checkDate_inviteOnlyCourse(row); break;
			 */
				case "checkProgram_Courses":
					checkProgram_Courses(row);
					break;
				case "checkInviteOnly_Courses":
					checkInviteOnly_Courses(row);
					break;
				case "checkEnrollmentDateIsExpiredFuturedCurrent_Courses":
					checkEnrollmentDateIsExpiredFuturedCurrent_Courses(row);
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
	
	public void checkStatusCode_inviteOnlyCourse(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = inviteOnlyLocator.checkStatusCode_inviteOnlyCourse(data);
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
					if(data.contains(getStatus.get(i)))
					{
						sheetStatus = "Fail";
						int position = data.indexOf(getStatus.get(i));
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(0).get(position);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(0).set(position, (cellValue + " - failed"));

					}
				}
			}
		}
	}
	
	public void checkDate_inviteOnlyCourse(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = inviteOnlyLocator.checkDate_inviteOnlyCourse(data);
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(1).add(data.size()+i, (getStatus.get(i) + "checkDate_inviteOnlyCourse - failed"));

				}
			}
		}
	}
	
	public void checkProgram_Courses(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = inviteOnlyLocator.checkProgram_Courses(data);
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(2).add(data.size()+i, (getStatus.get(i)+ "checkProgram_Courses - failed"));

				}
			}
		}
	}
	
	public void checkInviteOnly_Courses(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = inviteOnlyLocator.checkInviteOnly_Courses(data);
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(3).add(data.size()+i, (getStatus.get(i) + "checkInviteOnly_Courses - failed"));

				}
			}
		}
	
		
	}
	
	public void checkEnrollmentDateIsExpiredFuturedCurrent_Courses(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			ArrayList<String> getStatus = inviteOnlyLocator.checkEnrollmentDateIsExpiredFuturedCurrent_Courses(data);
			if(getStatus.size()>0)
			{
				for(int i = 0; i < getStatus.size(); i++)
				{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("InviteOnlyCourse").get(4).add(data.size()+i, (getStatus.get(i) +"checkEnrollmentDateIsExpiredFuturedCurrent_Courses - failed"));

				}
			}
		}
	
		
	
		
	}
	
	
}

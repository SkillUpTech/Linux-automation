package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class AddUserValidation {
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	AddUserLocator addUserLocator;
	String sheetStatus = "Pass";
	
	public AddUserValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		
		this.addUserLocator = new AddUserLocator(this.driver);
	}
	
	public String start() throws InterruptedException
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
				
				  case "addUser": 
					  addUser(row, i);
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
		return sheetStatus;
	}
	
	public void addUser(ArrayList<String> dataFromExcel, int index)
	{
		this.verifyInsertedData(dataFromExcel, index);
	}
	
	public void verifyInsertedData(ArrayList<String> data, int index)
	{
		try
		{
			ArrayList<String> status = new ArrayList<String>();
			ArrayList<Integer> verifyValidData = addUserLocator.checkSignupWithValidData(data);
			for(int i = 0; i < verifyValidData.size(); i++)
			{
					if(verifyValidData.get(i).equals(1))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).get(1);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(1, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(2))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).get(2);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(2, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(3))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).get(3);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(3, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(3))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).get(4);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(4, (cellValue + " - failed"));
						status.add("Failed");
					}
					else if(verifyValidData.get(i).equals(4))
					{
						sheetStatus = "Fail";
						String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).get(5);
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(5, (cellValue + " - failed"));
						status.add("Failed");
					}	
				}
				if(status.contains("Failed"))
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SignUp").get(index).set(0, "validData - failed"); 
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class FAQValidation 
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	FAQLocator faqLocator;
	String sheetStatus = "Pass";
	
	public FAQValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		this.faqLocator = new FAQLocator(driver);
		System.out.println("FAQ process started");
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
				case "login":
					login(row);
					break;
				case "verifyFAQ":
					verifyFAQ();
					break;
				case "invalidfullname":
					invalidfullname(row);
					break;
				case "EmptyFullname":
					EmptyFullname(row);
					break;
				case "validFullname":
					validFullname(row);
					break;
				case "invalidEmail":
					invalidEmail(row); 
					  break;
				case "EmptyEmail":
					EmptyEmail(row); 
					  break;
				case "validEmail":
					validEmail(row); 
					  break;
				case "InvalidContact":
					InvalidContact(row); 
					  break;
				case "EmptyContact":
					EmptyContact(row); 
					  break;
				case "validContact":
					validContact(row); 
					  break;
				case "EmptyQuery":
					EmptyQuery(row); 
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
	public void login(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			String email = data.get(1);
			String pwd = data.get(2);
			ArrayList<String> status = faqLocator.loginProcess(email, pwd);
			if(status.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(0).set(0, "login - failed");
			}
		}
	}
	public void verifyFAQ()
	{
		ArrayList<String> status = faqLocator.FAQProcess();
		if(status.size()>0)
		{
			for(int i = 0; i < status.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(1).add(i+1, (status.get(i) + "verifyFAQ - failed"));
			}
		}
	}
	public void invalidfullname(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.verifyInvalidFullname(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(3).set(0, "invalidfullname - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(3).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(3).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(3).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(3).set(4, "Test - failed");
				}
			}
		}
	}
	public void EmptyFullname(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.EmptyFullname(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(4).set(0, "EmptyFullname - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(4).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(4).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(4).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(4).set(4, "Test - failed");
				}
			}
		}
	}
	public void validFullname(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.validFullname(data);
		if(!status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(5).set(0, "validFullname - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(5).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(5).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(5).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(5).set(4, "Test - failed");
				}
			}
		}
	}
	public void invalidEmail(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.invalidEmail(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(6).set(0, "invalidEmail - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(6).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(6).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(6).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(6).set(4, "Test - failed");
				}
			}
		}
	}
	public void EmptyEmail(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.EmptyEmail(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(7).set(0, "EmptyEmail - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(7).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(7).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(7).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(7).set(4, "Test - failed");
				}
			}
		}
	}
	public void validEmail(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.validEmail(data);
		if(!status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(8).set(0, "validEmail - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(8).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(8).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(8).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(8).set(4, "Test - failed");
				}
			}
		}
	}
	public void EmptyContact(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.InvalidContact(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(10).set(0, "EmptyContact - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(10).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(10).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(10).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(10).set(4, "Test - failed");
				}
			}
		}
	}
	public void validContact(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.validContact(data);
		if(!status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(11).set(0, "validContact - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(11).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(11).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(11).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(11).set(4, "Test - failed");
				}
			}
		}
	}
	public void InvalidContact(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.InvalidContact(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(9).set(0, "InvalidContact - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(9).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(9).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(9).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(9).set(4, "Test - failed");
				}
			}
		}
	}
	public void EmptyQuery(ArrayList<String> data)
	{
		ArrayList<String> status = faqLocator.EmptyQuery(data);
		if(status.contains("pass"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(12).set(0, "EmptyQuery - failed");
			for(int i = 0; i < status.size(); i++)
			{
				if(status.get(i).contains("name"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(12).set(1, "hema - failed");
				}
				if(status.get(i).contains("email"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(12).set(2, "hemamalini@gmail.com - failed");
				}
				if(status.get(i).contains("contact"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(12).set(3, "9876543210 - failed");
				}
				if(status.get(i).contains("query"))
				{
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("FAQ").get(12).set(4, "Test - failed");
				}
			}
		}
	}
}

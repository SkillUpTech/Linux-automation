package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.TestUtil;

public class PlacementPageValidation implements Callable<String>
{
	
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	PlacementPageLocator placementPageLocator;
	String sheetStatus = "Pass";
	
	public PlacementPageValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.sheetData = sheetData;
		
	}
	public WebDriver openDriver(String browserName)
	{
		WebDriver driver = null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", RegressionTesting.driverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Hemamalini\\Downloads\\geckodriver-v0.33.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver(); 
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		return driver;
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
					case "ArrangeAChatIcon":
						ArrangeAChatIcon();
						break;
					case "ConnectWithOurPlacementTeam":
						ConnectWithOurPlacementTeam();
						break;
					case "ArrangeAChatWithOurPlacementTeam":
						ArrangeAChatWithOurPlacementTeam();
						break;
					/*
					 * case "InvalidFirstName": InvalidFirstName(row); break; case "EmptyFirstName":
					 * EmptyFirstName(row); break; case "EmptyLastName": EmptyLastName(row); break;
					 * case "InvalidContactNo": InvalidContactNo(row); break; case "EmptyContactNo":
					 * EmptyContactNo(row); break; case "InvalidEmail": InvalidEmail(row); break;
					 * case "EmptyEmail": EmptyEmail(row); break; case "WithoutState":
					 * WithoutState(row); break; case "ValidPlacementForm": ValidPlacementForm(row);
					 * break;
					 */
					case "withoutData":
						withoutData(row);
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
	
	public void ArrangeAChatIcon()
	{
		String getStatus = placementPageLocator.verifyArrangeAChatIcon();
		if(getStatus.contains("fail"))
		{
			if(getStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(0).set(0, "ArrangeAChatIcon - failed");
			}
		}
	}
	
	public void ConnectWithOurPlacementTeam()
	{
		String getStatus = placementPageLocator.verifyConnectWithOurPlacementTeam();
		if(getStatus.contains("fail"))
		{
			if(getStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(1).set(0, "ConnectWithOurPlacementTeam - failed");
			}
		}
	}
	
	public void ArrangeAChatWithOurPlacementTeam()
	{
		String getStatus = placementPageLocator.verifyArrangeAChatWithOurPlacementTeam();
		if(getStatus.contains("fail"))
		{
			if(getStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(2).set(0, "ArrangeAChatWithOurPlacementTeam - failed");
			}
		}
	}
	
	public void InvalidFirstName(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.InvalidFirstName(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(3).set(0, "InvalidFirstName - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(3).set(0, "InvalidFirstName - failed");

		}
	}
	
	public void EmptyFirstName(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.EmptyFirstName(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(4).set(0, "EmptyFirstName - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(4).set(0, "EmptyFirstName - failed");

		}
	}
	
	public void InvalidLastName(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.InvalidLastName(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(5).set(0, "InvalidLastName - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(5).set(0, "InvalidLastName - failed");
		}
	}
	
	public void EmptyLastName(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.EmptyLastName(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(6).set(0, "EmptyLastName - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(6).set(0, "EmptyLastName - failed");
		}
	}
	
	public void InvalidContactNo(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.InvalidContactNo(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(7).set(0, "InvalidContactNo - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(7).set(0, "InvalidContactNo - failed");
		}
	}
	
	public void EmptyContactNo(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.EmptyContactNo(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(8).set(0, "EmptyContactNo - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(8).set(0, "EmptyContactNo - failed");
		}
	}
	public void InvalidEmail(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.InvalidEmail(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(9).set(0, "InvalidEmail - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(8).set(0, "EmptyContactNo - failed");
		}
	}
	public void EmptyEmail(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.EmptyEmail(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(10).set(0, "EmptyEmail - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(10).set(0, "EmptyEmail - failed");
		}
	}
	public void WithoutState(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.WithoutState(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(11).set(0, "WithoutState - failed");
		}
		else if(status.contains("success"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(11).set(0, "WithoutState - failed");
		}
	}
	public void ValidPlacementForm(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.ValidPlacementForm(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(12).set(0, "ValidPlacementForm - failed");
		}
		else if(status.contains("error"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(12).set(0, "ValidPlacementForm - failed");
		}
	}
	
	public void withoutData(ArrayList<String> data)
	{
		ArrayList<String> status = placementPageLocator.withoutData(data);
		if(status.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(13).set(0, "withoutData - failed");
		}
		else if(status.contains("error"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("PlacementPage").get(13).set(0, "withoutData - failed");

		}
	}

	@Override
	public String call() throws Exception {
		System.out.println("Placement page process started");

		try
		{
			driver = this.openDriver(RegressionTesting.nameOfBrowser);
			this.placementPageLocator = new PlacementPageLocator(driver);
			OpenWebsite.openSite(driver);
			String BaseWindow = driver.getWindowHandle();
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
					case "ArrangeAChatIcon":
						ArrangeAChatIcon();
						break;
					case "ConnectWithOurPlacementTeam":
						ConnectWithOurPlacementTeam();
						break;
					case "ArrangeAChatWithOurPlacementTeam":
						ArrangeAChatWithOurPlacementTeam();
						break;
					/*
					 * case "InvalidFirstName": InvalidFirstName(row); break; case "EmptyFirstName":
					 * EmptyFirstName(row); break; case "EmptyLastName": EmptyLastName(row); break;
					 * case "InvalidContactNo": InvalidContactNo(row); break; case "EmptyContactNo":
					 * EmptyContactNo(row); break; case "InvalidEmail": InvalidEmail(row); break;
					 * case "EmptyEmail": EmptyEmail(row); break; case "WithoutState":
					 * WithoutState(row); break; case "ValidPlacementForm": ValidPlacementForm(row);
					 * break;
					 */
					case "withoutData":
						withoutData(row);
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
		driver.quit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetStatus;
	
	}
}

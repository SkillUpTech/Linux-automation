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

public class SearchPageValidation implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	SearchPageLocator searchPageLocator;
	String sheetStatus = "Pass";
	
	
	public SearchPageValidation(ArrayList<ArrayList<String>> sheetData)
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
	public String start()
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
				case "validDataSearch":
					validDataSearch(row);
					break;
				case "invalidDataSearch":
					invalidDataSearch(row);
					break;
				case "emptySeach":
					emptySeach(row);
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
	
	public void validDataSearch(ArrayList<String> dataFromExcel)
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> getStatus = searchPageLocator.validDataSearchProcess(dataFromExcel);
			for(int i = 0; i < getStatus.size(); i++)
			{
				if(getStatus.contains("fail"))
				{
					sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(0).set(0, "validDataSearch - failed");
				}
				else if(getStatus.contains("not"))
				{
					sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(0).add(i+2, (getStatus.get(i) + " - failed"));
				}
			}
		}
	}
	
	public void invalidDataSearch(ArrayList<String> dataFromExcel)
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> getStatus = searchPageLocator.invalidDataSearchProcess(dataFromExcel);
			for(int i = 0; i < getStatus.size(); i++)
			{
				if(getStatus.contains("fail"))
				{sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(1).set(0, "invalidDataSearch - failed");
				}
				else if(getStatus.contains("not"))
				{
					sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(1).add(i+2, (getStatus.get(i) + " - failed"));
				}
			}
		}
	}
	
	public void emptySeach(ArrayList<String> dataFromExcel)
	{
		if(!dataFromExcel.contains("NA"))
		{
			ArrayList<String> getStatus = searchPageLocator.emptySearchProcess(dataFromExcel);
			for(int i = 0; i < getStatus.size(); i++)
			{
				if(getStatus.contains(""))
				{sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(2).set(0, "emptySeach - failed");
				}
				else if(getStatus.contains("not"))
				{
					sheetStatus="Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("SearchProcess").get(2).add(i+2, (getStatus.get(i) + " - failed"));
				}
			}
		}
	}

	



	@Override
	public String call() throws Exception
	{
		System.out.println("Search  process started");
		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.searchPageLocator = new SearchPageLocator(driver);
		String BaseWindow = driver.getWindowHandle();
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "validDataSearch":
					validDataSearch(row);
					break;
				case "invalidDataSearch":
					invalidDataSearch(row);
					break;
				case "emptySeach":
					emptySeach(row);
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
	
		// TODO Auto-generated method stub
	}
	
}

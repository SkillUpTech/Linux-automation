package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.TestUtil;
import com.seo.regression.testing.RegressionTesting;

public class HeaderSectionValidation implements Callable<String>
{
	ArrayList<ArrayList<String>> sheetData = null;
	HeaderSectionLocator headerSectionLocator;
	String sheetStatus = "Pass";
	WebDriver driver;
	OpenWebsite openWebsite;
	public HeaderSectionValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
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
		
		OpenWebsite.openSite(this.driver);
		String BaseWindow = driver.getWindowHandle();
		int j = 1;
		while(j<=1)
		{
			System.out.println("Time of execution : "+j);
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
					case "skillupLogo":
						checkSkillupLogo(row.get(1));
						break;
					case "contactUs":
						checkContactUs();
						break;
					case "blog":
						checkBlog(row);
						break;
					case "categories":
						checkCategories();
						break;
					case "popularCourses":
						checkPopularCourses();
						break;
					case "login":
						checkLogin(row);
						break;
					case "learningPartner":
						checkLearningPartner();
						break;
					case "signUP":
						checkSignUP(row);
						break;
				}
			}
			j++;
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
	public void checkSkillupLogo(String data) throws InterruptedException
	{
		if(!data.contains("NA"))
		{
			String skillupLogoProcess = headerSectionLocator.checkSkillupLogo();
			if(skillupLogoProcess.equalsIgnoreCase("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(0).set(0, "skillupLogo - failed");
			}
		}
	}
	
	public void checkLearningPartner() throws InterruptedException
	{
		ArrayList<String> learningPartner = headerSectionLocator.verifyLearningPartner();
		if(learningPartner.size()>0)
		{
			for(int i = 0; i < learningPartner.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(5).add(learningPartner.size()+1, (learningPartner.get(i) + "failed"));
			}
		}
	}
	public void checkContactUs() throws InterruptedException
	{
		String contactUSProcess = headerSectionLocator.checkContactUs();
		if(contactUSProcess.equalsIgnoreCase("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(1).set(0, "contactUs - failed");
		}
	}

	public void checkBlog(ArrayList<String> data) throws InterruptedException
	{
		if(!data.contains("NA"))
		{
			String blogProcess = headerSectionLocator.checkBlog();
			if(blogProcess.equalsIgnoreCase("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(2).set(0, "blog - failed");
			}
		}
	}
	public void checkCategories()
	{
		ArrayList<String> categoriesProcess = headerSectionLocator.checkCategories();
		if(categoriesProcess.size()>0)
		{
			for(int i = 0; i < categoriesProcess.size(); i++)
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(3).add(categoriesProcess.size()+1, (categoriesProcess.get(i) + "failed"));
			}
		}
	}
	public void checkPopularCourses()
	{
			
			ArrayList<String> popularCouseProcess = headerSectionLocator.checkPopularCourses();
			if(popularCouseProcess.size()>0)
			{
				for(int i = 0; i < popularCouseProcess.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(4).add(popularCouseProcess.size()+1, (popularCouseProcess.get(i) + "failed"));
				}
			}
	}
	public void checkLogin(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			
			String loginProcess = headerSectionLocator.checkLogin();
			if(loginProcess.equalsIgnoreCase("fail"))
			{
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(6).set(0, "login - failed");
			}
		}
	}
	public void checkSignUP(ArrayList<String> data) throws InterruptedException
	{
		if(!data.contains("NA"))
		{
			String signUpProcess = headerSectionLocator.checkSignUP();
			if(signUpProcess.equalsIgnoreCase("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderSection").get(7).set(0, "signUP - failed");
			}
		}
	}
	
	@Override
	public String call() throws Exception
	{
		System.out.println("header process started");
		try
		{
		driver = this.openDriver(com.palm.regressionTesting.RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.headerSectionLocator = new HeaderSectionLocator(driver);
		String BaseWindow = driver.getWindowHandle();
		int j = 1;
		while(j<=1)
		{
			System.out.println("Time of execution : "+j);
			for(int i = 0; i < this.sheetData.size(); i++)
			{
				ArrayList<String> row = this.sheetData.get(i);
				String firstColumn = row.get(0);
				switch(firstColumn)
				{
					case "skillupLogo":
						checkSkillupLogo(row.get(1));
						break;
					case "contactUs":
						checkContactUs();
						break;
					case "blog":
						checkBlog(row);
						break;
					case "categories":
						checkCategories();
						break;
					case "popularCourses":
						checkPopularCourses();
						break;
					case "login":
						checkLogin(row);
						break;
					case "learningPartner":
						checkLearningPartner();
						break;
					case "signUP":
						checkSignUP(row);
						break;
				}
			}
			j++;
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

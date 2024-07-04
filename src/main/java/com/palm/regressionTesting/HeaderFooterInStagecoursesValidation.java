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

public class HeaderFooterInStagecoursesValidation implements Callable<String>
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	HeaderFooterInStagecoursesLocator headerFooterInStagecoursesLocator;
	String sheetStatus = "Pass";
	public HeaderFooterInStagecoursesValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.sheetData = sheetData;
		
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
			  case "LoginIcon": 
				  LoginIcon(row); 
				  break; 
			  case "skillupIcon": 
				  skillupIcon(); 
				  break; 
			  case "AboutSkillupOnline": 
				  AboutSkillupOnline(row.get(1)); 
				  break; 
			  case "ContactUs": 
				  ContactUs(row.get(1)); 
				  break; 
			  case "Blog": 
				  Blog(row); 
				  break; 
			  case "twitter": 
				  twitter(row.get(1)); 
				  break; 
			  case "facebook": 
				  facebook(row.get(1)); 
				  break; 
			  case "linkedIn": 
				  linkedIn(row.get(1)); 
				  break; 
			  case "instagram": 
				  instagram(row.get(1)); 
				  break; 
			  case "youtube": 
				  youtube(row.get(1)); 
				  break; 
			  case "contactUSFooter": 
				  contactUSFooter(row.get(1)); 
				  break; 
			  case "AboutSkillupOnlineFooter": 
				  AboutSkillupOnlineFooter(row.get(1)); 
				  break; 
			  case "SkillupOnlineForBusiness": 
				  SkillupOnlineForBusiness(row.get(1)); 
				  break; 
			  case "FAQ": 
				  FAQ(row.get(1)); 
				  break; 
			  case "PrivacyPolicy": 
				  PrivacyPolicy(row.get(1)); 
				  break; 
			  case "TermsOfService": 
				  TermsOfService(row.get(1)); 
				  break; 					 
			  case "BlogFooter": 
				  BlogFooter(row); 
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
				driver.switchTo().window(BaseWindow);
			}
			driver.switchTo().window(BaseWindow);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetStatus;
	}
	
	public void LoginIcon(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			String status = headerFooterInStagecoursesLocator.loginProcess();
			if(status.equalsIgnoreCase("fail"))
			{
				sheetStatus="Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(1).set(0, "LoginIcon - failed");
			}
		}
	}
	public void FindOutMore(String data)
	{
		String status = headerFooterInStagecoursesLocator.FindOutMoreProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(2).add(2, (status + " - failed"));
		}
	}
	public void skillupIcon()
	{
		String status = headerFooterInStagecoursesLocator.skillupIconProcess();
		if(status.equalsIgnoreCase("fail"))
		{

			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(3).set(0, "skillupIcon - failed");
		
		}
	}
	public void AboutSkillupOnline(String data)
	{
		String status = headerFooterInStagecoursesLocator.AboutSkillupOnlineProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{

			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(4).add(2, (status + " - failed"));
		
		}
	}
	public void ContactUs(String data)
	{
		String status = headerFooterInStagecoursesLocator.ContactUsProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{

			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(5).add(2, (status + " - failed"));
		
		}
	}
	public void Blog(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			String status = headerFooterInStagecoursesLocator.BlogProcess(data);
			if(!status.equalsIgnoreCase("pass"))
			{
				
				sheetStatus="Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(6).add(2, (status + " - failed"));
				
			}
		}
	}
	public void twitter(String data)
	{
		String status = headerFooterInStagecoursesLocator.twitterProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(8).add(2, (status + " - failed"));

		}
		
	}
	public void facebook(String data)
	{
		String status = headerFooterInStagecoursesLocator.facebookProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(9).add(2, (status + " - failed"));

		}
	}
	public void linkedIn(String data)
	{
		String status = headerFooterInStagecoursesLocator.linkedInProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(10).add(2, (status + " - failed"));

		}
	}
	public void instagram(String data)
	{
		String status = headerFooterInStagecoursesLocator.instagramProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(11).add(2, (status + " - failed"));

		}
	}
	public void youtube(String data)
	{
		String status = headerFooterInStagecoursesLocator.youtubeProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(12).add(2, (status + " - failed"));

		}
	}
	public void contactUSFooter(String data)
	{
		String status = headerFooterInStagecoursesLocator.contactUSProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(13).add(2, (status + " - failed"));

		}
	}
	public void AboutSkillupOnlineFooter(String data)
	{
		String status = headerFooterInStagecoursesLocator.AboutSkillupOnlineFooterProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(14).add(2, (status + " - failed"));

		}
	}
	public void SkillupOnlineForBusiness(String data)
	{
		String status = headerFooterInStagecoursesLocator.SkillupOnlineForBusinessProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(15).add(2, (status + " - failed"));

		}
	}

	public void FAQ(String data)
	{
		String status = headerFooterInStagecoursesLocator.FAQProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(17).add(2, (status + " - failed"));

		}
	}
	public void PrivacyPolicy(String data)
	{
		String status = headerFooterInStagecoursesLocator.PrivacyPolicyProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(18).add(2, (status + " - failed"));

		}
	}
	public void TermsOfService(String data)
	{
		String status = headerFooterInStagecoursesLocator.TermsOfServiceProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(19).add(2, (status + " - failed"));

		}
	}
	public void BlogFooter(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			
			String status = headerFooterInStagecoursesLocator.BlogFooterProcess(data);
			if(!status.equalsIgnoreCase("pass"))
			{
				sheetStatus="Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(20).add(2, (status + " - failed"));
				
			}
		}
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
	@Override
	public String call() throws Exception
	{
		System.out.println("HeaderFooterInStagecoursesValidation process started");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		this.headerFooterInStagecoursesLocator = new HeaderFooterInStagecoursesLocator(driver);
		OpenWebsite.openSite(driver);
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			  case "LoginIcon": 
				  LoginIcon(row); 
				  break; 
			  case "skillupIcon": 
				  skillupIcon(); 
				  break; 
			  case "AboutSkillupOnline": 
				  AboutSkillupOnline(row.get(1)); 
				  break; 
			  case "ContactUs": 
				  ContactUs(row.get(1)); 
				  break; 
			  case "Blog": 
				  Blog(row); 
				  break; 
			  case "twitter": 
				  twitter(row.get(1)); 
				  break; 
			  case "facebook": 
				  facebook(row.get(1)); 
				  break; 
			  case "linkedIn": 
				  linkedIn(row.get(1)); 
				  break; 
			  case "instagram": 
				  instagram(row.get(1)); 
				  break; 
			  case "youtube": 
				  youtube(row.get(1)); 
				  break; 
			  case "contactUSFooter": 
				  contactUSFooter(row.get(1)); 
				  break; 
			  case "AboutSkillupOnlineFooter": 
				  AboutSkillupOnlineFooter(row.get(1)); 
				  break; 
			  case "SkillupOnlineForBusiness": 
				  SkillupOnlineForBusiness(row.get(1)); 
				  break; 
			  case "FAQ": 
				  FAQ(row.get(1)); 
				  break; 
			  case "PrivacyPolicy": 
				  PrivacyPolicy(row.get(1)); 
				  break; 
			  case "TermsOfService": 
				  TermsOfService(row.get(1)); 
				  break; 					 
			  case "BlogFooter": 
				  BlogFooter(row); 
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
				driver.switchTo().window(BaseWindow);
			}
			driver.switchTo().window(BaseWindow);
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

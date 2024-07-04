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

public class HeaderFooterInErrorScreenValidation implements Callable<String>
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	HeaderFooterInErrorScreenLocator headerFooterInErrorScreenLocator;
	String sheetStatus = "Pass";
	public HeaderFooterInErrorScreenValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
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
				  AboutSkillupOnline(); 
				  break; 
				  
			  case "ContactUs": 
				  ContactUs(); 
			      break; 
			      
			  case "Blog":
				  Blog();
				  break; 
				  
			  case "Categories": 
				  Categories(); 
				  break; 
				  
			   case "PartnerPage": 
				   PartnerPage(); 
				   break;
				   
			   case "PopularCourses": PopularCourses(); break; 
			   
			   case "ExploreAll":
				  ExploreAll(); break;
				  
			   case "twitter": twitter(); break;
			   
			   case "facebook":
				  facebook(); break; 
				  
			   case "linkedIn": linkedIn(); break; 
			   
			   case "instagram":
				  instagram(); break; 
				  
			   case "youtube": youtube(); break;
			   
			   case "contactUSFooter":
				   contactUSFooter(); break;
				   
			   case "AboutSkillupOnlineFooter":
				  AboutSkillupOnlineFooter(); break; 
				  
			   case "SkillupOnlineForBusiness":
				  SkillupOnlineForBusiness(); break; 
				  
			   case "Placement": Placement(); break; 
			   
			   case "FAQ": FAQ(); break; case "PrivacyPolicy": PrivacyPolicy(); break;
			   
			   case "TermsOfService": TermsOfService(); break; case "BlogFooter": BlogFooter();
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
	public void LoginIcon(ArrayList<String> data)
	{
		if(!data.contains("NA"))
		{
			String status = headerFooterInErrorScreenLocator.loginProcess();
			if(status.equalsIgnoreCase("fail"))
			{
				sheetStatus="Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterErrorScreen").get(1).set(0, "LoginIcon - failed");
			}
		}
	}
	
	  public void FindOutMore()
	  { 
		  String status =  headerFooterInErrorScreenLocator.FindOutMoreProcess();
		  if(!status.equalsIgnoreCase("pass"))
		  { 
			  sheetStatus="Fail";
			  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterErrorScreen").get(2).set(0, "FindOutMore - failed");
		  } 
	   } 
	  
	  public void skillupIcon() 
	  { 
		  String status = headerFooterInErrorScreenLocator.skillupIconProcess();
	  if(status.equalsIgnoreCase("fail"))
	  {
	  	  sheetStatus="Fail";
		  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
		  "HeaderFooterErrorScreen").get(3).set(0, "skillupIcon - failed");
	  
	  } } public void AboutSkillupOnline() { String status =
	  headerFooterInErrorScreenLocator.AboutSkillupOnlineProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(4).set(0, "AboutSkillupOnline - failed");
	  
	  } } 
	  public void ContactUs() 
	  { String status =
	  headerFooterInErrorScreenLocator.ContactUsProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(5).set(0, "ContactUs - failed");
	  
	  } } 
	  public void Blog()
	  { String status =
	  headerFooterInErrorScreenLocator.BlogProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(6).set(0, "Blog - failed");
	  
	  } } public void twitter() throws InterruptedException { String status =
	  headerFooterInErrorScreenLocator.twitterProcess();
	  if(!status.equalsIgnoreCase("pass")) 
	  { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(13).set(0, "twitter - failed");
	  
	  }
	  
	  } public void facebook() { String status =
	  headerFooterInErrorScreenLocator.facebookProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(14).set(0, "facebook - failed");
	  
	  } } public void linkedIn() { String status =
	  headerFooterInErrorScreenLocator.linkedInProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(15).set(0, "linkedIn - failed");
	  
	  } } public void instagram() { String status =
	  headerFooterInErrorScreenLocator.instagramProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(16).set(0, "instagram - failed");
	  
	  } } public void youtube() { String status =
	  headerFooterInErrorScreenLocator.youtubeProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(17).set(0, "youtube - failed");
	  
	  } } public void contactUSFooter() { String status =
	  headerFooterInErrorScreenLocator.contactUSProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(18).set(0, "contactUSFooter - failed");
	  
	  } } public void AboutSkillupOnlineFooter() { String status =
	  headerFooterInErrorScreenLocator.AboutSkillupOnlineFooterProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(19).set(0, "AboutSkillupOnlineFooter - failed");
	  
	  } } public void SkillupOnlineForBusiness() { String status =
	  headerFooterInErrorScreenLocator.SkillupOnlineForBusinessProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(20).set(0, "SkillupOnlineForBusiness - failed");
	  
	  } } public void Placement() { String status =
	  headerFooterInErrorScreenLocator.PlacementProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(21).set(0, "Placement - failed");
	  
	  } } public void FAQ() { String status =
	  headerFooterInErrorScreenLocator.FAQProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(22).set(0, "FAQ - failed");
	  
	  } } public void PrivacyPolicy() { String status =
	  headerFooterInErrorScreenLocator.PrivacyPolicyProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(23).set(0, "PrivacyPolicy - failed");
	  
	  } } public void TermsOfService() { String status =
	  headerFooterInErrorScreenLocator.TermsOfServiceProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(24).set(0, "TermsOfService - failed");
	  
	  } } public void BlogFooter() { String status =
	  headerFooterInErrorScreenLocator.BlogFooterProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(25).set(0, "BlogFooter - failed");
	  
	  } } public void Categories() {
	  
	  } public void PartnerPage() {
	  
	  } public void PopularCourses() {
	  
	  } public void ExploreAll() {
	  
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
	public String call() throws Exception {

		try
		{
			driver = this.openDriver(RegressionTesting.nameOfBrowser);
			OpenWebsite.openSite(driver);
		this.headerFooterInErrorScreenLocator = new HeaderFooterInErrorScreenLocator(driver);
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
				  AboutSkillupOnline(); 
				  break; 
				  
			  case "ContactUs": 
				  ContactUs(); 
			      break; 
			      
			  case "Blog":
				  Blog();
				  break; 
				  
			  case "Categories": 
				  Categories(); 
				  break; 
				  
			   case "PartnerPage": 
				   PartnerPage(); 
				   break;
				   
			   case "PopularCourses": PopularCourses(); break; 
			   
			   case "ExploreAll":
				  ExploreAll(); break;
				  
			   case "twitter": twitter(); break;
			   
			   case "facebook":
				  facebook(); break; 
				  
			   case "linkedIn": linkedIn(); break; 
			   
			   case "instagram":
				  instagram(); break; 
				  
			   case "youtube": youtube(); break;
			   
			   case "contactUSFooter":
				   contactUSFooter(); break;
				   
			   case "AboutSkillupOnlineFooter":
				  AboutSkillupOnlineFooter(); break; 
				  
			   case "SkillupOnlineForBusiness":
				  SkillupOnlineForBusiness(); break; 
				  
			   case "Placement": Placement(); break; 
			   
			   case "FAQ": FAQ(); break; case "PrivacyPolicy": PrivacyPolicy(); break;
			   
			   case "TermsOfService": TermsOfService(); break; case "BlogFooter": BlogFooter();
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

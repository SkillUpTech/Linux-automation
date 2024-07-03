package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.TestUtil;

public class DashboardValidation implements Callable<String>
{
	private String SHEET_NAME;
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	DashboardLocator dashboardLocator;
	RegressionGenericValidator regressionGenericValidator;
	String sheetStatus = "Pass";
	private int CURRENT_ROW = 0;
	
	public DashboardValidation(ArrayList<ArrayList<String>> sheetData) throws InterruptedException
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
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		String BaseWindow = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		OpenWebsite.openSite(driver);

		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "url": 
					  url(row); 
					  break;
					  
				case "EnrollFlatPrice": 
					  EnrollFlatPrice(row); 
					  break;
					  
				case "continueOnDashboard":
					  continueOnDashboard(row.get(1));
					  break;
					  
				case "courseSearchFeature":
					  courseSearchFeature(row.get(1));
					  break;
					  
				case "checkEnrolledCourse":
					  checkEnrolledCourse(row.get(1));
					  break;
					
				case "shareCourse": 
					shareCourseFromDashboard(row); 
					break;
					 
				case "checkSocialLinkFromCourse":
					checkSocialLinkFromCourse(row);
					break;
					
				case "checkRelatedProgram":
					checkRelatedProgram(row.get(1));
					break;
					
				case "checkSelfPacedCourse":
					checkSelfPacedCourse(row.get(1));
					break;
					
				case "checkVILTCourse":
					checkVILTCourse(row.get(1));
					break;
					
				case "checkPartnerIconRedirectionFromCourse":
					checkPartnerIconRedirectionFromCourse(row.get(1));
					break;
					
				case "checkCourseContentTabs":
					checkCourseContentTabs(row.get(1));
					break;
					
				case "checkProgramSection":
					checkProgramSection(row.get(1));
					break;
					
				case "checkEnrolledProgram":
					checkEnrolledProgram(row.get(1));
					break;
					
				case "checkProgramSocialLinks":
					checkProgramSocialLinks(row);
					break;
					
				case "checkIncludeCourses":
					checkIncludeCourses(row);
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
	
	public void url(ArrayList<String> url)
	{
		if(!url.contains("No"))
		{
			ArrayList<String> urlStatus = dashboardLocator.openSite(url);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(1).set(0, "url - failed");
			}
		}
	}
	
	public void EnrollFlatPrice(ArrayList<String> data)
	{
		if(!data.contains("No"))
		{
			ArrayList<String> verifyEnrollmentProcess = new ArrayList<String>();
			try
			{
				if(!verifyEnrollmentProcess.contains("NA"))
				{
					verifyEnrollmentProcess = dashboardLocator.EnrollFlatPrice(data);
					for(int i = 0; i < verifyEnrollmentProcess.size(); i++)
					{
						if(verifyEnrollmentProcess.get(0).contains("fail"))
						{
							sheetStatus = "Fail";
							String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(1);
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(1, (cellValue + " - failed"));
						}
						if(verifyEnrollmentProcess.get(1).contains("fail"))
						{
							sheetStatus = "Fail";
							String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(2);
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(2, (cellValue + " - failed"));
						}
						if(verifyEnrollmentProcess.get(2).contains("fail"))
						{
							sheetStatus = "Fail";
							String cellValue = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).get(3);
							RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(2).set(3, (cellValue + " - failed"));
						}
					}
					if(verifyEnrollmentProcess.contains("fail"))
					{
						sheetStatus = "Fail";
						markProcessFailed();
					}
				}
				else
				{
					markProcessIgnored();
				}
			}
			catch(Exception e)
			{
				sheetStatus = "Fail";
				markProcessFailed();
			}
		}
	}
	public void continueOnDashboard(String data)
	{
		if(!data.contains("No"))
		{
			String urlStatus = dashboardLocator.clickDashboard(data);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(3).set(0, "continueOnDashboard - failed");
			}
		}
	}
	
	public void courseSearchFeature(String data)
	{
		String urlStatus = dashboardLocator.courseSearchFeature(data);
		if(urlStatus.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(4).set(0, "courseSearchFeature - failed");
		}
	}
	
	public void checkEnrolledCourse(String data)
	{
		if(!data.contains("No"))
		{
			String urlStatus = dashboardLocator.enrolledCourse();
			if(urlStatus.contains("fail") || !urlStatus.contains("success"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(5).add(1, (urlStatus + "checkEnrolledCourse - failed"));
			}
		}
	}

	
	  public void shareCourseFromDashboard(ArrayList<String> share)
	  {
		  if(!share.contains("No"))
		  {
			  ArrayList<String> urlStatus = dashboardLocator.verfiyShareCourseFromDashboard(share);
			  if(urlStatus.contains("fail")) 
			  { 
				  for(int i = 0; i < urlStatus.size(); i++)
				  {
					  sheetStatus = "Fail";
					  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(6).add(i+1, urlStatus.get(i)+ "shareCourse - failed"); 
				  }
			  } 
		  }
	  }
	 
	
	public void checkEnrolledProgram(String data)
	{
		if(!data.contains("No"))
		{
			String urlStatus = dashboardLocator.enrolledProgram();
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(13).add(1, (urlStatus + "checkEnrolledCourse - failed"));
			}
		}
	}
	public void checkSocialLinkFromCourse(ArrayList<String> socialLinks)
	{
		if(!socialLinks.get(5).contains("No`"))
		{
			ArrayList<String> urlStatus = dashboardLocator.verfiyShareCourseFromDashboard(socialLinks);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(6).set(0, "checkSocialLinkFromCourse - failed");
			}
		}
	}
	
	public void checkRelatedProgram(String data)
	{
		if(!data.contains("No"))
		{
			String urlStatus = dashboardLocator.verfiyRelatedProgramFromDashboard(data);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(7).set(0, "checkRelatedProgram - failed");
			}
		}
	}
	
	public void checkSelfPacedCourse(String data)
	{
		if(!data.contains("No"))
		{
			ArrayList<String> urlStatus = dashboardLocator.verfiySelfPacedCourse();
			if(urlStatus.size()>0)
			{
				for(int i = 0; i < urlStatus.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(8).add(i+1, (urlStatus.get(i)+ "checkSelfPacedCourse - failed"));
				}
			}
		}
	}
	
	public void checkVILTCourse(String data)
	{
		if(!data.contains("No"))
		{
			
			ArrayList<String> urlStatus = dashboardLocator.verfiyVILTCourse();
			if(urlStatus.size()> 0)
			{
				for(int i = 0; i < urlStatus.size(); i++)
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(9).add(i+1, (urlStatus.get(i) + "verfiyVILTCourse - failed"));
				}
			}
		}
	}
	
	public void checkPartnerIconRedirectionFromCourse(String data)
	{
		if(!data.contains("No"))
		{
			ArrayList<String> urlStatus = dashboardLocator.verfiyPartnerIconRedirectionFromCourse();
			if(urlStatus.contains("fail"))
			{
				if(urlStatus.size()>= 0)
				{
					for(int i = 0; i < urlStatus.size(); i++)
					{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(10).add(i+1, (urlStatus.get(i)+ "checkPartnerIconRedirectionFromCourse - failed"));
					}
				}
			}
		}
	}
	
	public void checkCourseContentTabs(String data)
	{
		if(!data.contains("No"))
		{
			ArrayList<String> tabStatus = dashboardLocator.checkCourseContentTabs(data);
			if(tabStatus.size()>= 0)
			{
				if(tabStatus.contains("fail"))
				{
					for(int i = 0; i < tabStatus.size(); i++)
					{
						sheetStatus = "Fail";
						RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(11).add(i+1, (tabStatus.get(i)+ "checkCourseContentTabs - failed"));
					}
				}
			}
		}
	}
	
	public void checkProgramSection(String data)
	{
		if(!data.contains("No"))
		{
			
			String urlStatus = dashboardLocator.verifyProgramPage();
			
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(12).set(0, "checkProgram - failed");
			}
		}
	}

	
	public void checkProgramSocialLinks(ArrayList<String> socialLinks)
	{
		if(!socialLinks.get(5).equalsIgnoreCase("No"))
		{
			ArrayList<String> urlStatus = dashboardLocator.checkSocialLinkfromProgram(socialLinks);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(14).set(0, "checkProgramSocialLinks - failed");
			}
		}
	}
	
	public void checkIncludeCourses(ArrayList<String> data)
	{
		if(!data.contains("No"))
		{
			
			ArrayList<String> process = dashboardLocator.verifyIncludeCoursesFromProgram(data);
			{
				if(!process.contains("expanded"))
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(15).set(1, "checkIncludeCourses - failed");
				}
				if(!process.contains("unExpanded"))
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(15).set(2, "checkIncludeCourses - failed");
				}
				if(process.contains("fail"))
				{
					sheetStatus = "Fail";
					RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(15).set(3, "checkIncludeCourses - failed");
				}
			}
		}
	}
	public void markProcessFailed()
	{
		sheetStatus = "Fail";
		if(null != TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP)
		{
			String process = TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).get(0);
			TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).set(0, (process + " - failed"));
		}
		else if(null != RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP)
		{
			String process = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).get(0);
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).set(0,
					(process + " - failed"));
		}
	}
	public void markProcessIgnored()
	{
		if(null != TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP)
		{
			String process = TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).get(0);
			TestRegressionGenericProcess.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).set(0, (process + " - ignored"));
		}
		else if(null != RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP)
		{
			String process = RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).get(0);
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(SHEET_NAME).get(CURRENT_ROW).set(0,
					(process + " - ignored"));
		}
	}
	
	@Override
	public String call() throws Exception
	{
		
		System.out.println("Enrolling Flat Price and validating details in Dashboard");

		try
		{
		driver = this.openDriver(RegressionTesting.nameOfBrowser);
		OpenWebsite.openSite(driver);
		this.dashboardLocator = new DashboardLocator(this.driver);
		String BaseWindow = driver.getWindowHandle();

		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
				case "url": 
					  url(row); 
					  break;
					  
				case "EnrollFlatPrice": 
					  EnrollFlatPrice(row); 
					  break;
					  
				case "continueOnDashboard":
					  continueOnDashboard(row.get(1));
					  break;
					  
				case "courseSearchFeature":
					  courseSearchFeature(row.get(1));
					  break;
					  
				case "checkEnrolledCourse":
					  checkEnrolledCourse(row.get(1));
					  break;
					
				case "shareCourse": 
					shareCourseFromDashboard(row); 
					break;
					 
				case "checkSocialLinkFromCourse":
					checkSocialLinkFromCourse(row);
					break;
					
				case "checkRelatedProgram":
					checkRelatedProgram(row.get(1));
					break;
					
				case "checkSelfPacedCourse":
					checkSelfPacedCourse(row.get(1));
					break;
					
				case "checkVILTCourse":
					checkVILTCourse(row.get(1));
					break;
					
				case "checkPartnerIconRedirectionFromCourse":
					checkPartnerIconRedirectionFromCourse(row.get(1));
					break;
					
				case "checkCourseContentTabs":
					checkCourseContentTabs(row.get(1));
					break;
					
				case "checkProgramSection":
					checkProgramSection(row.get(1));
					break;
					
				case "checkEnrolledProgram":
					checkEnrolledProgram(row.get(1));
					break;
					
				case "checkProgramSocialLinks":
					checkProgramSocialLinks(row);
					break;
					
				case "checkIncludeCourses":
					checkIncludeCourses(row);
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

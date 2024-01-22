package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardValidation 
{
	private String SHEET_NAME;
	ArrayList<ArrayList<String>> sheetData = null;
	WebDriver driver;
	DashboardLocator dashboardLocator;
	RegressionGenericValidator regressionGenericValidator;
	String sheetStatus = "Pass";
	private int CURRENT_ROW = 0;
	
	public DashboardValidation(WebDriver driver, String sheetName, ArrayList<ArrayList<String>> sheetData) throws InterruptedException
	{
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.SHEET_NAME = sheetName; 
		this.sheetData = sheetData;
		this.dashboardLocator = new DashboardLocator(this.driver);
		this.regressionGenericValidator = new RegressionGenericValidator(this.driver);
		System.out.println("Enrolling Flat Price and validating details in Dashboard");
	}
	
	public String start() throws InterruptedException
	{
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
					  continueOnDashboard();
					  break;
				case "checkEnrolledCourse":
					  checkEnrolledCourse(row.get(1));
					 break;
				/*
				 * case "shareCourse": shareCourseFromDashboard(row.get(1)); break;
				 */
				case "checkSocialLinkFromCourse":
					checkSocialLinkFromCourse(row);
					break;
				case "checkRelatedProgram":
					checkRelatedProgram(row.get(1));
					break;
				case "checkProgramSection":
					checkProgramSection();
					break;
				case "checkEnrolledProgram":
					checkEnrolledProgram(row.get(1));
					break;
				/*
				 * case "shareProgram": shareProgram(row.get(1)); break;
				 */
				case "checkProgramSocialLinks":
					checkProgramSocialLinks(row);
					break;
				case "checkIncludeCourses":
					checkIncludeCourses(row);
					break;
			}
		}
		return sheetStatus;
	}
	
	public void url(ArrayList<String> url)
	{
		ArrayList<String> urlStatus = dashboardLocator.openSite(url);
		if(urlStatus.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(1).set(0, "url - failed");

		}
	}
	
	public void EnrollFlatPrice(ArrayList<String> data)
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
	public void continueOnDashboard()
	{
		String urlStatus = dashboardLocator.clickDashboard();
		if(urlStatus.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(3).set(0, "continueOnDashboard - failed");
		}
	}
	public void checkEnrolledCourse(String data)
	{
		if(data.equalsIgnoreCase("A"))
		{
			String urlStatus = dashboardLocator.enrolledCourse();
			if(urlStatus.contains("fail") || !urlStatus.contains("success"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(4).add(1, (urlStatus + "checkEnrolledCourse - failed"));
			}
		}
	}

	/*
	 * public void shareCourseFromDashboard(String share) { String urlStatus =
	 * dashboardLocator.verfiyShareCourseFromDashboard(share);
	 * if(urlStatus.contains("fail")) { sheetStatus = "Fail";
	 * RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get
	 * (5).set(0, "shareCourse - failed"); } }
	 */
	
	public void checkEnrolledProgram(String data)
	{
		if(data.equalsIgnoreCase("A"))
		{
			String urlStatus = dashboardLocator.enrolledProgram();
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(8).add(1, (urlStatus + "checkEnrolledCourse - failed"));
			}
		}
	}
	public void checkSocialLinkFromCourse(ArrayList<String> socialLinks)
	{
		if(socialLinks.get(5).equalsIgnoreCase("A"))
		{
			ArrayList<String> urlStatus = dashboardLocator.checkSocialLinkFromCourse(socialLinks);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(5).set(0, "checkSocialLinkFromCourse - failed");
			}
		}
	}
	
	public void checkRelatedProgram(String data)
	{
			String urlStatus = dashboardLocator.verfiyRelatedProgramFromDashboard(data);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(6).set(0, "checkRelatedProgram - failed");
			}
	}
	public void checkProgramSection()
	{
		String urlStatus = dashboardLocator.verifyProgramPage();
		
		if(urlStatus.contains("fail"))
		{
			sheetStatus = "Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(7).set(0, "checkProgram - failed");
		}
	}

	/*
	 * public void shareProgram(String share) { String urlStatus =
	 * dashboardLocator.verfiyShareProgramFromDashboard(share);
	 * if(urlStatus.contains("fail")) { sheetStatus = "Fail";
	 * RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get
	 * (8).set(0, "checkProgram - failed"); } }
	 */
	public void checkProgramSocialLinks(ArrayList<String> socialLinks)
	{
		if(socialLinks.get(5).equalsIgnoreCase("A"))
		{
			ArrayList<String> urlStatus = dashboardLocator.checkSocialLinkfromProgram(socialLinks);
			if(urlStatus.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(9).set(0, "checkProgramSocialLinks - failed");
			}
		}
	}
	public void checkIncludeCourses(ArrayList<String> data)
	{
		ArrayList<String> process = dashboardLocator.verifyIncludeCoursesFromProgram(data);
		{
			if(!process.contains("expanded"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(10).set(1, "checkIncludeCourses - failed");
			}
			if(!process.contains("unExpanded"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(10).set(2, "checkIncludeCourses - failed");
			}
			if(process.contains("fail"))
			{
				sheetStatus = "Fail";
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("Dashboard").get(10).set(3, "checkIncludeCourses - failed");
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
	
	
}


package com.seo.regression.testing;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.regression.utility.ProcessExcel;
import com.regression.utility.Utils;

public class RegressionTesting 
{
	String CURRENT_SHEET = "";
	int CURRENT_ROW = 0;
	String startTime = "";
	String endTime = "";
	String duration = "";
	
	public static LinkedHashMap<String, ArrayList<ArrayList<String>>> EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP;
	private HashMap<String, String> sheetsResult = new HashMap<String, String>();
	NewAboutCourseValidator newAboutCourseValidator;
	RegressionGenericValidator regressionGenericValidator;
	AboutProgramValidation aboutProgramValidation;
	public static String ENV_TO_USE = "";
	String getEnvironment = "";
	WebDriver driver;
	public static String  driverPath = "C:\\Users\\Hemamalini\\Downloads\\125driver\\chromedriver-win64\\chromedriver.exe";
	
	@BeforeTest
	@Parameters({"browser","env"})
	public void setup(String browserName, String env) throws Exception
	{
		System.out.println("welcome");
	    if (browserName.equalsIgnoreCase("firefox"))
	    {
	    	driver = OpenWebsite.openDriver(browserName);
	    }
	    else if (browserName.equalsIgnoreCase("Chrome"))
	    {
	    	driver = OpenWebsite.openDriver(browserName);
	    	if(env.equalsIgnoreCase("stage"))
	    	{
	    		getEnvironment = "stage";
	    	}
	    	else if(env.equalsIgnoreCase("stage-in"))
	    	{
	    		getEnvironment = "stage-in";
	    	}
	    	else if(env.equalsIgnoreCase("prod-in"))
	    	{
	    		getEnvironment = "prod-in";
	    	}
	    	else if(env.equalsIgnoreCase("prod"))
	    	{
	    		getEnvironment = "prod";
	    	}
	    	else if(env.equalsIgnoreCase("dev-in"))
	    	{
	    		getEnvironment = "dev-in";
	    	}
	    	else if(env.equalsIgnoreCase("dev"))
	    	{
	    		getEnvironment = "dev";
	    	}
	    	else if(env.equalsIgnoreCase("qa-in"))
	    	{
	    		getEnvironment = "qa-in";
	    	}
	    	else if(env.equalsIgnoreCase("qa"))
	    	{
	    		getEnvironment = "qa";
	    	}
	    }
	    else
	    {
	    	throw new Exception("Browser is not correct");
	    }
	}
	
	@Test
	public void startTest()
	{
		System.out.println(driver);
		this.startTesting();
		driver.quit();
	}
	
	public void startTesting()
	{
		String excelPath = "D:\\Doc\\RegressionTesting.xlsx";
		EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP = new LinkedHashMap<String, ArrayList<ArrayList<String>>>();
		startTime = new SimpleDateFormat(Utils.DEFAULT_DATA_FORMAT).format(Calendar.getInstance().getTime());
		try
		{
			LinkedHashMap<String, ArrayList<ArrayList<String>>> data = ProcessExcel.readExcelFileAsRows(excelPath);
			EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP = ProcessExcel.readExcelFileAsRows(excelPath);
			
			ArrayList<ArrayList<String>> master = data.get("Master");// Master sheet in excel
			ArrayList<String> environment = master.get(1);// Environment row in excel
			if(master.get(1).toString().contains(getEnvironment))
			{
				ENV_TO_USE = environment.get(1);//Use envToUse appropriately
			}
			ENV_TO_USE = getEnvironment;
			OpenWebsite.openSite(driver);
			ArrayList<String> browser = master.get(1);
			ArrayList<String> pages = master.get(0);// Pages row in excel
			for(int j = 0; j < pages.size(); j++)// iterating the pages row
			{
				String sheetName = pages.get(j);// getting the cell values of pages row eg. Pages, Login-ignore, ErrorCodeValidation, etc,.
				
				if (data.containsKey(sheetName))// checking whether the excel is having the sheet
				{
					ArrayList<ArrayList<String>> sheetData = data.get(sheetName);// reading the sheet data
					try
					{
						String sheetStatus = "Pass";
						switch(sheetName)
						{
							
							case "Login":
								sheetStatus = new RegressionTestLogin(driver, sheetData).start();
							break;
							case "AboutCourse":
							{
								newAboutCourseValidator = new NewAboutCourseValidator(driver, sheetName, sheetData);
								sheetStatus = newAboutCourseValidator.processSheetData();
							}
							break;
							
							case "AboutProgram":
							{
								aboutProgramValidation = new AboutProgramValidation(driver, sheetName, sheetData);
								sheetStatus = aboutProgramValidation.processSheetData();
							}
							break;
							case "GenericProcess":
							{
								regressionGenericValidator = new RegressionGenericValidator(driver, sheetName, sheetData);
								sheetStatus = regressionGenericValidator.processSheetData();
							}
							break;
							case "Dashboard":
								sheetStatus = new DashboardValidation(driver, sheetName, sheetData).start();
								break;
							case "URLValidation":
								sheetStatus = new ErrorCodeValidation(sheetData, driver).start();
								break;
							case"SignUp":
								sheetStatus = new SignUpValidation(sheetData, driver).start();
								break;
							case"AddUser":
								sheetStatus = new AddUserValidation(sheetData, driver).start();
								break;
							case"Login if mail id not verified":
								sheetStatus = new CheckLoginValidation(sheetData, driver).start();
								break;
							case"ForGotPwd":
								sheetStatus = new ForgotPasswordValidation(sheetData, driver).start();
								break;
							case"FooterSection":
								sheetStatus = new FooterSectionValidation(sheetData, driver).start();
								break;
							case"HeaderSection":
								sheetStatus = new HeaderSectionValidation(sheetData, driver).start();
								break;
							case"HomePage":
								sheetStatus = new HomePageValidator(driver, sheetName, sheetData).start();
								break;
							case "ContactInfo":
								sheetStatus = new ContactInfoValidation(sheetData, driver).start();
								break;
							case "LoginWithSocialAcc":
								sheetStatus = new LoginSocialAccValidation(sheetData, driver).start();
								break;
							case "ContactUSForm":
								sheetStatus = new ContactUsValidation(sheetData, driver).start();
								break;
							case "SearchProcess":
								sheetStatus = new SearchPageValidation(sheetData, driver).start();
								break;
							case "MicrosoftPage":
								sheetStatus =new MicrosoftCourseValidation(sheetData, driver).start();
								break;
							case "SignupWithSocialAcc":
								sheetStatus = new SignupSocialAccValidator(sheetData, driver).start();
								break;
							case "Pacific":
								sheetStatus = new PLUValidation(sheetData, driver).start();
								break;
							case "ExploreAll":
								sheetStatus = new ExploreAllValidator(sheetData, driver).start();
								break;
							case "EditProfile":
								sheetStatus = new EditProfileValidation(sheetData, driver).start();
								break;
							case "LoginPageLinks":
								sheetStatus = new LoginPageLinksValidation(sheetData, driver).start();
								break;
							 case "IBM":
								 sheetStatus = new IBMPageValidation(sheetData, driver).start(); 
								 break;
							 case "Fluideducation":
								 sheetStatus = new FluidEducationValidation(sheetData, driver).start(); 
								 break;
							 case "GLX":
								 sheetStatus = new GLXValidation(sheetData, driver).start(); 
								 break;
							 case "FAQ":
								 sheetStatus = new FAQValidation(sheetData, driver).start(); 
								 break;
							 case "HeaderFooterStagecourses":
								 sheetStatus = new HeaderFooterInStagecoursesValidation(sheetData, driver).start(); 
								 break;
							 case "HeaderFooterErrorScreen":
								 sheetStatus = new HeaderFooterInErrorScreenValidation(sheetData, driver).start(); 
								 break;
							 case "BlogPage":
								 sheetStatus = new BlogPageValidation(sheetData, driver).start(); 
								 break;
							 case "InviteOnlyCourse":
								 sheetStatus = new InviteOnlyValidation(sheetData, driver).start(); 
								 break;
							 case "SignUpPageLinks":
								 sheetStatus = new SignUpPageLinksValidation(sheetData, driver).start(); 
								 break;
							 case "ExploreCourseByNewUser":
								 sheetStatus = new ExploreCourseByNewUserValidation(sheetData, driver).start(); 
								 break;
							 case "ViewCertificate":
								 sheetStatus = new CertificateValidation(sheetData, driver).start(); 
								 break;
							 case "PlacementPage":
								 sheetStatus = new PlacementPageValidation(sheetData, driver).start(); 
								 break;
							 case "HeaderFeature":
								 sheetStatus = new HeaderFeatureValidation(sheetData, driver).start(); 
								 break;
							 case "ReimbursedProcess":
								 sheetStatus = new ReimbursedValidation(sheetData, driver).start(); 
								 break;
							 case "ApplyCoupon":
								 sheetStatus = new ApplyCouponValidation(sheetData, driver).start(); 
								 break;
							 case "IBMSkillBuildPage":
								 sheetStatus = new IBMSkillBuildPageValidation(sheetData, driver).start(); 
								 break;
							 case "CheckVILTSelfPacedCourse":
								 sheetStatus = new CourseLevelValidation(sheetData, driver).start(); 
								 break;
							 case "AccountPage":
								 sheetStatus = new AccountPageValidation(sheetData, driver).start(); 
								 break;
							 case "DevopsPage":
								 sheetStatus = new DevopsPageValidation(sheetData, driver).start(); 
								 break;
							 case "OnboardingJourney":
								 sheetStatus = new OnboardingValidation(sheetData, driver).start(); 
								 break;
							 case "TechMaster":
								 sheetStatus = new TechMasterValidation(sheetData, driver).start(); 
								 break;
							 case "CategoryBanner":
								 sheetStatus = new CategoryBannerValidation(sheetData, driver).start(); 
								 break;
							 case "CourseCardHover":
								 sheetStatus = new CourseCardHoverValidation(sheetData, driver).start(); 
								 break;
							 case "IBMViewCourse":
								 sheetStatus = new IBMViewCourseValidation(sheetData, driver).start(); 
								 break;
							 case "ProgramURLandSlug":
								 sheetStatus = new verifyProgramURLValidation(sheetData, driver).start(); 
								 break;
							default:
								System.out.println("Not class found to work with the sheet");
						}//end of swtich case
						sheetsResult.put(sheetName, sheetStatus);//st
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			endTime = new SimpleDateFormat(Utils.DEFAULT_DATA_FORMAT).format(Calendar.getInstance().getTime());
			duration = Utils.findDifference(startTime, endTime);
			prepareConsolidatedSheet();
			LocalDateTime currentDateTime = LocalDateTime.now();

	        // Define a custom date and time format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	        // Format the current date and time using the formatter
	        String formattedDateTime = currentDateTime.format(formatter);
	        
			if (/*
				 * driver.getCurrentUrl().contains("stage-in")||driver.getCurrentUrl().contains(
				 * "stage-in")||
				 */ENV_TO_USE.contains("stage-in"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "stage_in_result_" + formattedDateTime + ".xlsx");
			}
			else if (/* !driver.getCurrentUrl().contains("stage")|| */ENV_TO_USE.contains("stage"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "prod_result_" + formattedDateTime + ".xlsx");
			}
			else if (/* !driver.getCurrentUrl().contains("qa-in")|| */ENV_TO_USE.contains("qa-in"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "qa_India_result_" + formattedDateTime + ".xlsx");
			}
			else if (/* !driver.getCurrentUrl().contains("dev-in")|| */ENV_TO_USE.contains("dev-in"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "dev_India_result_" + formattedDateTime + ".xlsx");
			}
			else if (/* !driver.getCurrentUrl().contains("dev-in")|| */ENV_TO_USE.contains("prod-in"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "prodIndia_result_" + formattedDateTime + ".xlsx");
			}
			else if (/* !driver.getCurrentUrl().contains("dev-in")|| */ENV_TO_USE.contains("dev"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "Dev_US_result_" + formattedDateTime + ".xlsx");
			}
			else if(/* !driver.getCurrentUrl().contains("dev-in")|| */ENV_TO_USE.contains("qa"))
			{
				ProcessExcel.writeExcelFileAsRows(EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP, "D:\\", "qa_US_result_" + formattedDateTime + ".xlsx");
			}
		}
	}
	
	private void prepareConsolidatedSheet() 
	{
		ArrayList<ArrayList<String>> consolidatedSheedData = new ArrayList<ArrayList<String>>();

		ArrayList<String> testEnvRow = new ArrayList<>();
		testEnvRow.add("Test environment" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundlime"
				+ Utils.DELIMITTER + "border");
		testEnvRow.add(ENV_TO_USE + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER
				+ "border");

		ArrayList<String> executionStartsOn = new ArrayList<>();
		executionStartsOn.add("Test execution starts on" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER
				+ "backgroundlime" + Utils.DELIMITTER + "border");
		executionStartsOn.add(startTime + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> executionEndsOn = new ArrayList<>();
		executionEndsOn.add("Test execution ends on" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER
				+ "backgroundlime" + Utils.DELIMITTER + "border");
		executionEndsOn.add(endTime + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> executionDuration = new ArrayList<>();
		executionDuration.add("Execution time" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER
				+ "backgroundlime" + Utils.DELIMITTER + "border");
		executionDuration.add(duration + Utils.DELIMITTER + "backgroundLT" + Utils.DELIMITTER + "border");

		ArrayList<String> emptyRow = new ArrayList<>();
		emptyRow.add("");

		ArrayList<String> courseResultHeader = new ArrayList<>();
		courseResultHeader.add("Courses" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundLT"
				+ Utils.DELIMITTER + "border");
		courseResultHeader.add("Result" + Utils.DELIMITTER + "bold" + Utils.DELIMITTER + "backgroundLT"
				+ Utils.DELIMITTER + "border");

		consolidatedSheedData.add(testEnvRow);
		consolidatedSheedData.add(executionStartsOn);
		consolidatedSheedData.add(executionEndsOn);
		consolidatedSheedData.add(executionDuration);
		consolidatedSheedData.add(emptyRow);
		consolidatedSheedData.add(courseResultHeader);
		boolean hasFailedSheets = false;
		for (Entry<String, String> entry : sheetsResult.entrySet()) {
			String sheetName = entry.getKey();
			String sheetStatus = entry.getValue();
			ArrayList<String> sheetResult = new ArrayList<String>();
			sheetResult.add(sheetName + Utils.DELIMITTER + "backgroundlime" + Utils.DELIMITTER + "border");

			if (sheetStatus.equalsIgnoreCase("Fail")) {
				hasFailedSheets = true;
			}

			sheetResult.add(sheetStatus + Utils.DELIMITTER + "color"
					+ (sheetStatus.equalsIgnoreCase("Pass") ? "Green" : "Red") + Utils.DELIMITTER
					+ "backgroundlime" + Utils.DELIMITTER + "border");

			consolidatedSheedData.add(sheetResult);
		}
		EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.put(
				"Consolidated Result" + Utils.DELIMITTER + (hasFailedSheets ? "red" : "green"),
				consolidatedSheedData);
	}
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
	    try
	 {
	    if(result.getStatus() == ITestResult.SUCCESS)
	    {

	        //Do something here
	        System.out.println("passed **********");
	    }

	    else if(result.getStatus() == ITestResult.FAILURE)
	    {
	         //Do something here
	        System.out.println("Failed ***********");
	    }

	     else if(result.getStatus() == ITestResult.SKIP ){

	        System.out.println("Skiped***********");

	    }
	}
	   catch(Exception e)
	   {
	     e.printStackTrace();
	   }

	}
}

package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class HeaderFooterInStagecoursesValidation
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	HeaderFooterInStagecoursesLocator headerFooterInStagecoursesLocator;
	String sheetStatus = "Pass";
	public HeaderFooterInStagecoursesValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.headerFooterInStagecoursesLocator = new HeaderFooterInStagecoursesLocator(driver);
		System.out.println("HeaderFooterInStagecoursesValidation process started");
		//this.start();
	}
	
	public String start() throws InterruptedException
	{
		for(int i = 0; i < this.sheetData.size(); i++)
		{
			ArrayList<String> row = this.sheetData.get(i);
			String firstColumn = row.get(0);
			switch(firstColumn)
			{
			  case "LoginIcon": 
				  LoginIcon(); 
				  break; 
				/*
				 * case "FindOutMore": FindOutMore(row.get(1)); break;
				 */
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
				  Blog(row.get(1)); 
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
			  case "Placement": 
				  Placement(row.get(1)); 
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
				  BlogFooter(row.get(1)); 
				  break; 		 
							 
				 
			}
		}
		return sheetStatus;
	}
	
	public void LoginIcon()
	{
		String status = headerFooterInStagecoursesLocator.loginProcess();
		if(status.equalsIgnoreCase("fail"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(1).set(0, "LoginIcon - failed");
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
	public void Blog(String data)
	{
		String status = headerFooterInStagecoursesLocator.BlogProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{

			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(6).add(2, (status + " - failed"));
		
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
	public void Placement(String data)
	{
		String status = headerFooterInStagecoursesLocator.PlacementProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(16).add(2, (status + " - failed"));

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
	public void BlogFooter(String data)
	{
		String status = headerFooterInStagecoursesLocator.BlogFooterProcess(data);
		if(!status.equalsIgnoreCase("pass"))
		{
			sheetStatus="Fail";
			RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterStagecourses").get(20).add(2, (status + " - failed"));

		}
	}
}

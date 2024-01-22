package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class HeaderFooterInErrorScreenValidation {
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	HeaderFooterInErrorScreenLocator headerFooterInErrorScreenLocator;
	String sheetStatus = "Pass";
	public HeaderFooterInErrorScreenValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.headerFooterInErrorScreenLocator = new HeaderFooterInErrorScreenLocator(driver);
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
				  LoginIcon(row); 
				  break; 
				
				/*
				 * case "FindOutMore": FindOutMore(); break;
				 */
				  
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
				RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterErrorScreen").get(1).add(1, (status + " - failed"));
			}
		}
	}
	
	  public void FindOutMore()
	  { 
		  String status =  headerFooterInErrorScreenLocator.FindOutMoreProcess();
		  if(!status.equalsIgnoreCase("pass"))
		  { 
			  sheetStatus="Fail";
			  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get("HeaderFooterErrorScreen").get(2).add(1, (status + " - failed"));
		  } 
	   } 
	  
	  public void skillupIcon() 
	  { 
		  String status =
	  headerFooterInErrorScreenLocator.skillupIconProcess();
	  if(status.equalsIgnoreCase("fail")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(3).add(1, (status + " - failed"));
	  
	  } } public void AboutSkillupOnline() { String status =
	  headerFooterInErrorScreenLocator.AboutSkillupOnlineProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(4).add(1, (status + " - failed"));
	  
	  } } 
	  public void ContactUs() 
	  { String status =
	  headerFooterInErrorScreenLocator.ContactUsProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(5).add(1, (status + " - failed"));
	  
	  } } 
	  public void Blog()
	  { String status =
	  headerFooterInErrorScreenLocator.BlogProcess();
	  if(!status.equalsIgnoreCase("pass")) {
	  
	  sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(6).add(2, (status + " - failed"));
	  
	  } } public void twitter() throws InterruptedException { String status =
	  headerFooterInErrorScreenLocator.twitterProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(13).add(2, (status + " - failed"));
	  
	  }
	  
	  } public void facebook() { String status =
	  headerFooterInErrorScreenLocator.facebookProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(14).add(2, (status + " - failed"));
	  
	  } } public void linkedIn() { String status =
	  headerFooterInErrorScreenLocator.linkedInProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(15).add(2, (status + " - failed"));
	  
	  } } public void instagram() { String status =
	  headerFooterInErrorScreenLocator.instagramProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(16).add(2, (status + " - failed"));
	  
	  } } public void youtube() { String status =
	  headerFooterInErrorScreenLocator.youtubeProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(17).add(2, (status + " - failed"));
	  
	  } } public void contactUSFooter() { String status =
	  headerFooterInErrorScreenLocator.contactUSProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(18).add(2, (status + " - failed"));
	  
	  } } public void AboutSkillupOnlineFooter() { String status =
	  headerFooterInErrorScreenLocator.AboutSkillupOnlineFooterProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(19).add(2, (status + " - failed"));
	  
	  } } public void SkillupOnlineForBusiness() { String status =
	  headerFooterInErrorScreenLocator.SkillupOnlineForBusinessProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(20).add(2, (status + " - failed"));
	  
	  } } public void Placement() { String status =
	  headerFooterInErrorScreenLocator.PlacementProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(21).add(2, (status + " - failed"));
	  
	  } } public void FAQ() { String status =
	  headerFooterInErrorScreenLocator.FAQProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(22).add(2, (status + " - failed"));
	  
	  } } public void PrivacyPolicy() { String status =
	  headerFooterInErrorScreenLocator.PrivacyPolicyProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(23).add(2, (status + " - failed"));
	  
	  } } public void TermsOfService() { String status =
	  headerFooterInErrorScreenLocator.TermsOfServiceProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(24).add(2, (status + " - failed"));
	  
	  } } public void BlogFooter() { String status =
	  headerFooterInErrorScreenLocator.BlogFooterProcess();
	  if(!status.equalsIgnoreCase("pass")) { sheetStatus="Fail";
	  RegressionTesting.EXCEL_DATA_AS_SHEEET_NAME_AND_ROWS_MAP.get(
	  "HeaderFooterErrorScreen").get(25).add(2, (status + " - failed"));
	  
	  } } public void Categories() {
	  
	  } public void PartnerPage() {
	  
	  } public void PopularCourses() {
	  
	  } public void ExploreAll() {
	  
	  }
	 
}

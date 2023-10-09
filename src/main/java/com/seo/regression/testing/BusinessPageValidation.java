package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class BusinessPageValidation implements Runnable
{
	String result = "failed";
	ArrayList<ArrayList<String>> sheetData = null;
	String userName;
	WebDriver driver;
	BusinessPageLocator businessPageLocator;
	String sheetStatus = "Pass";
	
	public BusinessPageValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.sheetData = sheetData;
		this.businessPageLocator = new BusinessPageLocator(this.driver);
		System.out.println("Sign up validation begins");
		Thread thread = new Thread();
		//thread.start();
	}
	
	public String start()
	{
		return result;
		
	}
	 

	/*
	 * public String start() throws InterruptedException { for(int i = 0; i <
	 * this.sheetData.size(); i++) { ArrayList<String> row = this.sheetData.get(i);
	 * String firstColumn = row.get(0); switch(firstColumn) { case "BusinessIcon":
	 * BusinessIcon(); break; case "verifySubmit_NoData": checkWithoutData(); break;
	 * case "verifyTelecom_DataScience_Empty": checkTelecomDataScienceEmpty();
	 * break; case "verifyTelecom_DataScience_ValidData":
	 * checkTelecomDataScienceValidData(); break; case
	 * "verifyTelecom_DataScience_InvalidData":
	 * checkTelecomDataScienceInvalidData(); break; case
	 * "verifyTelecom_Big Data_Empty": checkTelecomBigDataEmpty(); break; case
	 * "verifyTelecom_Big Data_ValidData": checkTelecomBigDataValidData(); break;
	 * case "verifyTelecom_Big Data_InvalidData": checkTelecomBigDataInvalidData();
	 * break; case "verifyTelecom_Artificial Intelligence_Empty":
	 * checkTelecomArtificialIntelligenceEmpty(); break; case
	 * "verifyTelecom_Artificial Intelligence_InvalidData":
	 * checkTelecomArtificialIntelligenceInvalidData(); break; case
	 * "verifyTelecom_Artificial Intelligence_ValidData":
	 * checkTelecomArtificialIntelligenceValidData(); break; case
	 * "verifyManufacturing_DataScience_Empty":
	 * checkManufacturingDataScienceEmpty(); break; case
	 * "verifyManufacturing_DataScience_InvalidData":
	 * checkManufacturingDataScienceInvalidData(); break; case
	 * "verifyManufacturing_DataScience_ValidData":
	 * checkManufacturingDataScienceValidData(); break; case
	 * "verifyManufacturing_Big Data_Empty": checkManufacturingBigDataEmpty();
	 * break; case "verifyManufacturing_Big Data_InvalidData":
	 * checkManufacturingBigDataInvalidData(); break; case
	 * "verifyManufacturing_Big Data_ValidData": checkManufacturingBigDataValidData;
	 * break; case "verifyManufacturing_Artificial Intelligence_Empty":
	 * checkManufacturingArtificialIntelligenceEmpty(); break; case
	 * "verifyManufacturing_Artificial Intelligence_InvalidData":
	 * checkManufacturingArtificialIntelligenceInvalidData(); break; case
	 * "verifyManufacturing_Artificial Intelligence_ValidData":
	 * checkManufacturingArtificialIntelligenceValidData(); break; case
	 * "verifyIT/ITES_DataScience_Empty": checlITITESDataScienceEmpty(); break; case
	 * "verifyIT/ITES_DataScience_InvalidData": checkITITESDataScienceInvalidData();
	 * break; case "verifyIT/ITES_DataScience_ValidData":
	 * checkITITESDataScienceValidData(); break; case
	 * "verifyIT/ITES_Big Data_Empty": checkITESBigDataEmpty(); break; case
	 * "verifyIT/ITES_Big Data_InvalidData": checkITESBigDataInvalidData(); break;
	 * case "verifyIT/ITES_Big Data_ValidData": checkITESBigDataValidData(); break;
	 * case "verifyIT/ITES_Artificial Intelligence_Empty":
	 * checkITESArtificialIntelligenceEmpty(); break; case
	 * "verifyIT/ITES_Artificial Intelligence_ValidData":
	 * checkITESArtificialIntelligenceValidData(); break; case
	 * "verifyIT/ITES_Artificial Intelligence_InvalidData":
	 * checkITESArtificialIntelligenceInvalidData(); break; case
	 * "verifyE-commerce_DataScience_Empty": checkEcommerceDataScienceEmpty();
	 * break; case "verifyE-commerce_DataScience_InvalidData":
	 * checkEcommerceDataScienceInvalidData(); break; case
	 * "verifyE-commerce_DataScience_ValidData":
	 * checkEcommerceDataScienceValidData(); break; case
	 * "verifyE-commerce_Big Data_Empty": checkEcommerceBigDataEmpty(); break; case
	 * "verifyE-commerce_Big Data_InvalidData": checkEcommerceBigDataInvalidData();
	 * break; case "verifyE-commerce_Big Data_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyE-commerce_Artificial Intelligence_Empty":
	 * checkE-commerceArtificialIntelligenceEmpty(); break; case
	 * "verifyE-commerce_Artificial Intelligence_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyE-commerce_Artificial Intelligence_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_DataScience_Empty": checkEcommerceBigDataValidData();
	 * break; case "verifyHealthcare_DataScience_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_DataScience_ValidData": checkEcommerceBigDataValidData();
	 * break; case "verifyHealthcare_Big Data_Empty":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_Big Data_InvalidData": checkEcommerceBigDataValidData();
	 * break; case "verifyHealthcare_Big Data_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_Artificial Intelligence_Empty":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_Artificial Intelligence_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyHealthcare_Artificial Intelligence_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_DataScience_Empty": checkEcommerceBigDataValidData();
	 * break; case "verifyBanking&finance_DataScience_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_DataScience_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_Big Data_Empty": checkEcommerceBigDataValidData();
	 * break; case "verifyBanking&finance_Big Data_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_Big Data_ValidData": checkEcommerceBigDataValidData();
	 * break; case "verifyBanking&finance_Artificial Intelligence_Empty":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_Artificial Intelligence_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyBanking&finance_Artificial Intelligence_ValidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyOthers_DataScience_Empty": checkEcommerceBigDataValidData(); break;
	 * case "verifyOthers_DataScience_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyOthers_DataScience_ValidData": checkEcommerceBigDataValidData();
	 * break; case "verifyOthers_Big Data_Empty": checkEcommerceBigDataValidData();
	 * break; case "verifyOthers_Big Data_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyOthers_Big Data_ValidData": checkEcommerceBigDataValidData(); break;
	 * case "verifyOthers_Artificial Intelligence_Empty":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyOthers_Artificial Intelligence_InvalidData":
	 * checkEcommerceBigDataValidData(); break; case
	 * "verifyOthers_Artificial Intelligence_ValidData":
	 * checkEcommerceBigDataValidData(); break; } } return sheetStatus; }
	 */




	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
}

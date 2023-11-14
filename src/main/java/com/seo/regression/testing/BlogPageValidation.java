package com.seo.regression.testing;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class BlogPageValidation 
{
	WebDriver driver;
	ArrayList<ArrayList<String>> sheetData = null;
	BlogPageLocator blogPageLocator;
	String sheetStatus = "Pass";
	public BlogPageValidation(ArrayList<ArrayList<String>> sheetData, WebDriver driver) throws InterruptedException
	{
		this.sheetData = sheetData;
		this.driver = driver;
		OpenWebsite.openSite(driver);
		this.blogPageLocator = new BlogPageLocator(driver);
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
			  case "BlogIcon_FromStage": 
				  BlogIcon_FromStage(); 
				  break; 
			  case "BlogIcon_FromStagecourses": 
				  BlogIcon_FromStagecourses(); 
				  break; 
			}
		}
		return sheetStatus;
	}
	public void BlogIcon_FromStage()
	{
		
	}
	public void BlogIcon_FromStagecourses()
	{
		
	}
}

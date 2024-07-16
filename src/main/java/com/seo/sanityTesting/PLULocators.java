package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PLULocators
{
	WebDriver driver;
	
	public PLULocators(WebDriver driver) 
	{
		this.driver = driver;
	}
	public ArrayList<String> verifyPrograms(ArrayList<String>  programs)
	{

		ArrayList<String> processStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 600)", "");
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("section[class*='container-fluid TechPrograms_mainContainer'] div[class*='row g-3'] div[class*='TechPrograms_cardWrapper']>a"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				
				js.executeScript("arguments[0].scrollIntoView();", ListOfProgram.get(i));
				
				String url = ListOfProgram.get(i).getAttribute("href");
				
				String status = this.checkURLStatus(url);
				
				if(status.contains("fail"))
				{
					processStatus.add(status);
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
		
	
	}
	public ArrayList<String> verifyPLUCourse(ArrayList<String> course)
	{
		ArrayList<String> failedUrls = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 800)", "");
			List<WebElement> techProgram = driver.findElements(By.cssSelector("section[class*='HumanSkills_mainContainer'] div[class*='HumanSkills_mainContent'] a"));
			for(int i = 0; i < techProgram.size(); i++)
			{
				String url = techProgram.get(i).getAttribute("href");
				System.out.println("PLU course card starts execution : "+url);
				String urlLinkStatus = this.checkURLStatus(url);
				if(urlLinkStatus.contains("fail"))
				{
					failedUrls.add(urlLinkStatus);
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,1500)", "");
		return failedUrls;
	}
	public String checkURLStatus(String getURL)
	{
		String status = "fail";
		String addHosturl = getURL;
		HttpURLConnection huc = null;
		int respCode = 200;
		try
		{
			huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println("status code : "+respCode + " " +addHosturl);
			if(respCode > 200)
			{
				System.out.println("broken link"+addHosturl);
				status = "fail"+respCode;
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
}

package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IBMPageLocator 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public IBMPageLocator(WebDriver driver)
	{
		this.driver = driver;
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
				status = "fail" + respCode;
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
	public ArrayList<String> verifyIBMPage()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			WebElement  clickCourseDropdown = driver.findElement(By.cssSelector("a#navbarDropdown div[class=' Header_category__mr_e4']"));
			clickCourseDropdown.click();
			List<WebElement> learningPartners = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] ul[class='learning-Partners']>li>a"));
			for(int i = 0; i < learningPartners.size();i++)
			{
				String getLearningPartnerURL = learningPartners.get(i).getAttribute("href");
				if(getLearningPartnerURL.contains("ibm"))
				{
					String url = this.checkURLStatus(getLearningPartnerURL);
					if(url.contains("fail"))
					{
						processStatus.add(url);
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			processStatus.add("fail");
		}
		return processStatus;

		
	}
	
	
	public ArrayList<String> verifyIBMProgram()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 600)", "");
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("div[class*='LearningCatalogibm_cardRow']>div a"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", ListOfProgram.get(i));
				String url = this.checkURLStatus(ListOfProgram.get(i).getAttribute("href"));
				if(url.contains("fail"))
				{
					processStatus.add(url);	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
		
	}
	
	public ArrayList<String> verifyIBMcourses()
	{
		ArrayList<String> courseProcessStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement clickShowMoreIcon = driver.findElement(By.cssSelector("div[class='col-12 undefined d-xl-block false'] >div:nth-child(2) button"));
			
			js.executeScript("arguments[0].scrollIntoView();", clickShowMoreIcon);
			
			while(clickShowMoreIcon.isDisplayed() && clickShowMoreIcon.getText().contains("more"))
			{
				js.executeScript("arguments[0].click()", clickShowMoreIcon);
			}
			List<WebElement> ListOfCourses = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow']>div a"));
			
			for(int i = 0; i < ListOfCourses.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", ListOfCourses.get(i));
				String url = this.checkURLStatus(ListOfCourses.get(i).getAttribute("href"));
				if(url.contains("fail"))
				{
					courseProcessStatus.add(url);	
				}
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return courseProcessStatus;
	}
}

package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomepageLocator
{
	WebDriver driver;
	
	public HomepageLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public ArrayList<String> checkSliderLink(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<String> statusOfSliderScreen = new ArrayList<String>();
		try
		{
			if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				OpenWebsite.openSite(driver);
			}
			else
			{
				System.out.println("host is present");
			}
			ArrayList<String> getURL = new ArrayList<String>();
				List<WebElement> clickSlide = driver.findElements(By.cssSelector("div[class='bannersliderhome_bannerSliderH__YF848 bannersliderhome_bannerSliderHDesktOP__LpvuC'] div[class='slick-list']>div[class='slick-track']>div"));
				for(int i = 0; i < clickSlide.size(); i++)
				{
					WebElement checkVisibleSlide = driver.findElement(By.cssSelector(" div[class='slick-slide slick-active slick-current']"));
					if(checkVisibleSlide.isDisplayed())
					{
						String getSliderButtonURL = clickSlide.get(i).findElement(By.cssSelector(" a")).getAttribute("href");
						System.out.println("verify slider URL : "+getSliderButtonURL);
						getURL.add(getSliderButtonURL);
					}
				}
				for(int j = 0; j < getURL.size(); j++)
				{
					String urlLinkStatus = this.checkURLStatus(getURL.get(j));
					if(urlLinkStatus.contains("fail"))
					{
						statusOfSliderScreen.add(getURL.get(j)+urlLinkStatus);
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return statusOfSliderScreen;
	}
	
	public String checkURLStatus(String data)
	{
		String status = "fail";
			HttpURLConnection huc = null;
			int respCode = 200;
			String addHosturl = data;
			try
			{
				huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();
				System.out.println("status code : "+respCode + " " +addHosturl);
				if(respCode > 200)
				{
					System.out.println("broken link : "+addHosturl);
					System.out.println("response code : "+respCode);
					status = "fail" + respCode;
				}
				else
				{
					System.out.println("unbroken link : "+" "+addHosturl+" "+respCode);
					status = "success";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return status;
	}
	
	public ArrayList<String> checkLearningPartners()
	{
		ArrayList<String> verifyPocess = new ArrayList<String>();
		try
		{
			List<WebElement> partnerList = driver.findElements(By.cssSelector("div[class='Collaborate_excollaborationInner__0u_r2'] ul li a"));
			for(int i = 0; i < partnerList.size(); i++)
			{
					String partnerURL = partnerList.get(i).getAttribute("href");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					String urlLinkStatus = this.checkURLStatus(partnerURL);
					if(urlLinkStatus.contains("fail"))
					{
						verifyPocess.add(partnerList.get(i).getText()+urlLinkStatus);
					}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return verifyPocess;
	}
	
	public ArrayList<String> checkLearningCatalog() throws InterruptedException
	{
		ArrayList<String> verifyPocess = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");
		List<WebElement> learningCatalogCourses = driver.findElements(By.cssSelector("section[class*='Courses_mainSection']>div>div:nth-child(2) div[class='slick-track']>div[class*='slick-slide'] a"));
		for(int j = 0; j < learningCatalogCourses.size(); j++)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			System.out.println(" learning catalog courses size : " +learningCatalogCourses.size());
			String checkCourse = learningCatalogCourses.get(j).getAttribute("href");
			System.out.println(" learning catalog courses Name : " +checkCourse);
			String status = this.checkURLStatus(checkCourse);
			if(status.contains("fail"))
			{
				
				verifyPocess.add(status);
			}
			
		}
		return verifyPocess;
	}
	
	public ArrayList<String> checkHumanSkills()
	{
		System.out.println("human skill process started");
		ArrayList<String> verifyProcess = new ArrayList<String>();
		List<WebElement> humanSkillsCourses = driver.findElements(By.cssSelector("div[class*='LearningCatalog_browserCard'] a"));
		for(int i = 0; i < humanSkillsCourses.size(); i++)
		{
			if(humanSkillsCourses.get(i).isDisplayed())
			{
				String courseLink = humanSkillsCourses.get(i).getAttribute("href");
				String urlStatus = this.checkURLStatus(courseLink);
				if(urlStatus.contains("fail"))
				{
					verifyProcess.add(courseLink + urlStatus);
				}
			}
		}
		return verifyProcess;
		}
	
	public ArrayList<String> checkTopTechCategories()
	{
		System.out.println("top tech categories process started");
		ArrayList<String> verifyPocess = new ArrayList<String>();
		List<WebElement> topTechCategories = driver.findElements(By.cssSelector("div[class='TechCategories_exCollaborationInner__nW6ww'] ul li a"));
		for(int i = 0; i < topTechCategories.size(); i++)
		{
			if(topTechCategories.get(i).isDisplayed())
			{
				String getCourseLink = topTechCategories.get(i).getAttribute("href");
				String urlLink = this.checkURLStatus(getCourseLink);
				if(urlLink.contains("fail"))
				{
					verifyPocess.add(getCourseLink+urlLink);
				}
			}
		}
		return verifyPocess;
	}
}

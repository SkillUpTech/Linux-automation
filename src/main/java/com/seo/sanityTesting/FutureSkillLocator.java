package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
public class FutureSkillLocator
{
	WebDriver driver;
	
	public FutureSkillLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String launchSite(String url)
	{
		String Status = "";
		try
		{
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(OpenWebsite.setHost+url);
			Status = "pass";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
	
	public String findOutMore_Button()
	{
		String Status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(driver.findElements(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']")).size()>0)
			{
				WebElement clickFindOutMore = driver.findElement(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']"));
				js.executeScript("arguments[0].click()", clickFindOutMore);
				Status = "pass";
			}
			if(driver.findElements(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']")).size()>0)
			{
				WebElement clickFindOutMore = driver.findElement(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']"));
				js.executeScript("arguments[0].click()", clickFindOutMore);
				Status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
	public String learnmore_Button()
	{
		String Status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(driver.findElements(By.cssSelector("a[class*='EligibleForIncentive_knowMoreButton']")).size()>0)
			{
				WebElement learnmore = driver.findElement(By.cssSelector("a[class*='EligibleForIncentive_knowMoreButton']"));
				js.executeScript("arguments[0].click()", learnmore);
				Status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
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
	public ArrayList<String> Cards()
	{
		ArrayList<String> status = new ArrayList<String>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			while(driver.findElement(By.xpath("//div[contains(@class,'showMoreSection')]/button")).getText().equalsIgnoreCase("Show more"))
			{
				WebElement clickShowMore = driver.findElement(By.xpath("//div[contains(@class,'showMoreSection')]/button"));
				js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(clickShowMore.isDisplayed())
				{
					js.executeScript("arguments[0].click()", clickShowMore);
				}
			}
			List<WebElement> courseCardLocator = driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div"));
			for(int i = 0; i < courseCardLocator.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", courseCardLocator.get(i));
				WebElement locateURL = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]/div[contains(@class,'LearningCatalog_customCard')]/descendant::a"));
				String cardURL = locateURL.getAttribute("href");
				String urlStatus = this.checkURLStatus(cardURL);
				if(urlStatus.contains("fail"))
				{
					status.add(urlStatus);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
}

package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;


public class ReimbursedLocator
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	MicrosoftCourseLocator microsoftCourseLocator;
	String url = "";
	String courseTitle = "";
	public ReimbursedLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
	}
	
	public String checkLaunchCourse(String data)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			url = OpenWebsite.setURL+data;
			String urlStatus = microsoftCourseLocator.checkURLStatus(url);
			if(!urlStatus.contains("fail"))
			{
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				Set<String> allWindow = driver.getWindowHandles();
				for(String window : allWindow)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains(url))
					{
						driver.switchTo().window(window);
						System.out.println("course opened : "+url);
						courseTitle = driver.findElement(By.xpath("//h1")).getText();
						status = "pass";
						break;
					}
				}
			}
			else
			{
				status = "fail";
				System.out.println("course not opened : "+url);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	
	public String checkReimbursedLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			 WebElement reimbursedLink = driver.findElement(By.cssSelector("div[class*='CourseDescription_getreimbursed']>a"));
			 js.executeScript("arguments[0].scrollIntoView();", reimbursedLink);
			 if(reimbursedLink.isDisplayed())
			 {
				 js.executeScript("arguments[0].click()", reimbursedLink);
				 status = "pass";
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	
	public String checkLinkFromReimbursedPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			String parentWindow = driver.getCurrentUrl();
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("getreimbursed"))
				{
					driver.switchTo().window(window);
					System.out.println("reimbursed page");
					List<WebElement> linkFromReimbursed = driver.findElements(By.cssSelector("div#email-body>p>a"));
					js.executeScript("arguments[0].scrollIntoView();", linkFromReimbursed.get(0));
					if(linkFromReimbursed.get(0).isDisplayed())
					{
						 js.executeScript("arguments[0].click()", linkFromReimbursed.get(0));
						 driver.switchTo().window(window);
						 String checkTitleFromReimbursedPagelink = driver.findElement(By.xpath("//h1")).getText();
						 if(checkTitleFromReimbursedPagelink.equals(courseTitle))
						 {
							 System.out.println("course url and reimbursed page url are same");
							 status = "pass";
							 break;
						 }
						 else
						 {
							 status = "fail";
							 break;
						 }
					}
				}
				driver.switchTo().window(window);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			 status = "fail";
		}
		return status;
	}
}

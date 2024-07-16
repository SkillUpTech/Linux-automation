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
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

public class FluidEducationLocator
{
	MicrosoftCourseLocator microsoftCourseLocator;
	WebDriver driver;
	public FluidEducationLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
	}
	
	public String facebookProcess()
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(driver.getCurrentUrl().contains("skillup"))
		{
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(OpenWebsite.setURL+"/fluideducation");
		}
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='facebook']"));
			js.executeScript("arguments[0].scrollIntoView();", facebookIcon);
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("facebook"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						System.out.println("facebook verified");
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String instagramProcess()
	{
		String status = "fail";
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='instagram']"));
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("instagram"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						System.out.println("instagram verified");
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String twitterProcess()
	{

		String status = "fail";
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='twitter']"));
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("x.com"))
					{
						driver.switchTo().window(windows);
						System.out.println("twitter verified");
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	
	}
	
	public String CTADownloadingProcess()
	{
		String status = "";
		try
		{
			List<WebElement> downloadIcons = driver.findElements(By.cssSelector("div[class*='TechMasterCertificate_TechmasterMain'] div[class='TechMasterCertificate_TechmasterCert__VwWJa'] div[class='TechMasterCertificate_TechmasterDownldBtn__qPr8H']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			try
			{
				if(downloadIcons.get(1).isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					downloadIcons.get(1).sendKeys(Keys.TAB);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					Thread.sleep(1000);
					Actions action = new Actions(driver);
					action.moveToElement(downloadIcons.get(1)).build().perform();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				}
			}
			catch(Exception e)
			{
				status = "issue in mouseHover";
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			if(downloadIcons.size()>0)
			{
				downloadIcons.get(1).click();
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				WebElement iframeLocator = driver.findElement(By.cssSelector("iframe[src='https://survey.zohopublic.in/zs/D6C0lL']"));
				driver.switchTo().frame(iframeLocator);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				WebElement CTAdownloadForm = driver.findElement(By.cssSelector("div#form_page section#form_body div#form_container div#page_header p[name='descMsg']"));
				if(CTAdownloadForm.isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					System.out.println("CTA Download form located : "+CTAdownloadForm.getText());
				}
			}
		}
			
		catch(Exception e)
		{
			status = "CTADownloads not clickable";
			e.printStackTrace();
		}
		return status;
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
	
	public ArrayList<String> verifyFluidEducationProgram()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 3000)", "");
			driver.switchTo().defaultContent();
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("section[class*='DiscountSection_DataScienceAiCour'] div[class*='container-fluid DiscountSection_containerInner']>div[class='row']:nth-child(2) div[class*='DiscountSection_programcardDiv'] a"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				String URLFluidEducation = ListOfProgram.get(i).getAttribute("href");
				String status = this.checkURLStatus(URLFluidEducation);
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
	//verifyFluidEducationCourse
	public ArrayList<String> verifyFluidEducationCourse()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 3000)", "");
			driver.switchTo().defaultContent();
			List<WebElement> ListOfCourse = driver.findElements(By.cssSelector("section[class*='HumanSkills_mainContainer'] div[class*='HumanSkills_mainContent'] a"));
			for(int i = 0; i < ListOfCourse.size(); i++)
			{
				String URLFluidEducation = ListOfCourse.get(i).getAttribute("href");
				String status = this.checkURLStatus(URLFluidEducation);
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
}

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MicrosoftCourseLocator 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public MicrosoftCourseLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> verifyMicrosftPage()
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
				if(getLearningPartnerURL.contains("microsoft"))
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
	
	public ArrayList<String> verifyMicrosoftScourses()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		try
		{
			js.executeScript("window.scrollBy(0, 1100)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='ManageCardsLimit_showMoreSection'] button"));
			wait.until(ExpectedConditions.visibilityOfAllElements(clickShowMore));
			js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
			while(clickShowMore.isDisplayed() != clickShowMore.getText().equalsIgnoreCase("Show less"))
			{
				js.executeScript("arguments[0].click()", clickShowMore);
			}
			List<WebElement> listOfCourses = driver.findElements(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[@class='row'][2]/div[3]/div[contains(@class,'LearningCatalog_cardRow')]/div"));
			for(int i = 0; i < listOfCourses.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", listOfCourses.get(i));
				
				String courseURL = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class, 'RegularCourseCard_RegularcardLinks')]/a")).getAttribute("href");
				
				String urlLink = this.checkURLStatus(courseURL);
				
				if(urlLink.contains("fail"))
				{
					processStatus.add(courseURL);
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

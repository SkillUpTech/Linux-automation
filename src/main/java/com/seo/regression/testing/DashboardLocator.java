package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardLocator
{
	WebDriver driver;
	OpenWebsite openWebsite;
	MicrosoftCourseLocator microsoftCourseLocator;
	RegressionGenericLocator regressionGenericLocator;
	public DashboardLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
		this.regressionGenericLocator = new RegressionGenericLocator(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	public String openSite(String url)
	{
		String status = "";
		try
		{
			String openPage = OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+url;
			status = microsoftCourseLocator.checkCourseCode(openPage);
			if(status.contains("fail"))
			{
				System.out.println("Facing issue on site");
			}
			else
			{
				
			}
			driver.get(openPage);
		}
		catch(Exception e)
		{
			status = "fail";
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> EnrollFlatPrice(ArrayList<String> enrollDataFromExcel)
	{

		ArrayList<String> statusOfProcess = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			js.executeScript("window.scrollBy(0,200)");
			String getCurrentURL = driver.getCurrentUrl();
			if(getCurrentURL.contains("in"))// india site
			{
				WebElement checkEnrollButton = driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']"));
				if(checkEnrollButton.isDisplayed())
				{
					if(checkEnrollButton.getText().equalsIgnoreCase("Enroll Now"))
					{
						System.out.println("Enroll Button is displayed");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						wait.until(ExpectedConditions.elementToBeClickable(checkEnrollButton));
						js.executeScript("arguments[0].click()", checkEnrollButton);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					}
				}
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("register?"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						WebElement clickLoginIcon = driver.findElement(By.cssSelector("li#signinlink"));
						clickLoginIcon.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						break;
					}
				}
				statusOfProcess.add(regressionGenericLocator.loginProcess(enrollDataFromExcel.get(1)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				statusOfProcess.add(regressionGenericLocator.checkOutRazorpay(enrollDataFromExcel.get(3)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				statusOfProcess.addAll(regressionGenericLocator.indiaPaymentProcess(enrollDataFromExcel.get(4), enrollDataFromExcel.get(5)));
			}
			else
			{
				System.out.println("US Enroll Process");
			}
			WebElement clickDropdownIcon = driver.findElement(By.cssSelector("li[class='SigNUP']>a"));
			clickDropdownIcon.click();
			Thread.sleep(2000);
			WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Primary02_Blue'] li:nth-child(5) a"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			js3.executeScript("arguments[0].click()", clickSignOut);
			Thread.sleep(2000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			statusOfProcess.add("fail");
		}
		return statusOfProcess;
	
	}
}

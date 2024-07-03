package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.palm.regressionTesting.RegressionTesting;

public class IBMViewCourseLocator
{
	WebDriver driver;
	
	public IBMViewCourseLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String checkLogin(String userName, String passWord)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try
		{
			WebElement clickLogin = driver.findElement(By.cssSelector("ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li[class='Header_loginBtn__3Xv3A'] a"));
			js.executeScript("arguments[0].scrollIntoView();", clickLogin);
			wait.until(ExpectedConditions.elementToBeClickable(clickLogin));
			if(clickLogin.isDisplayed())
			{
				String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
				clickLogin.sendKeys(n);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			Set<String> nextWindow = driver.getWindowHandles();
			for(String window : nextWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
					js.executeScript("window.scrollBy(0, 200)", "");
					WebElement userNameElement = driver.findElement(By.cssSelector("input#email"));
					js.executeScript("arguments[0].scrollIntoView();", userNameElement);
					userNameElement.clear();
					userNameElement.sendKeys(userName);
					WebElement passwordElement = driver.findElement(By.cssSelector("input#password"));
					js.executeScript("arguments[0].scrollIntoView();", passwordElement);
					passwordElement.clear();
					passwordElement.sendKeys(passWord);
					js.executeScript("window.scrollBy(0, 100)", "");
					WebElement clickSubmit = driver.findElement(By.cssSelector("input[value='Log In']"));
					js.executeScript("arguments[0].scrollIntoView();", clickSubmit);
					if(clickSubmit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", clickSubmit);
					}
					status = this.ErrorMessage();
					if(status.equalsIgnoreCase("Failed"))
					{
						status = "Failed";
					}
				  else 
				  { 
					  status= this.checkDashboard(); 
				  } 
					break;
				}
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public String ErrorMessage()
	{
		String loginStatus = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> error = driver.findElements(By.xpath("//div[@class='NotificationTypeError spacing-mb16 status message submission-error is-shown']//div[@class='fiederror message-title']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(error.size()>0)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				List<WebElement> errorMsg2 = driver.findElements(By.xpath("//div[@class='NotificationTypeError spacing-mb16 status message submission-error is-shown']//div[@class='fiederror message-title']"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				if(errorMsg2.size()>0)
				{
					if(errorMsg2.get(0).getText().equalsIgnoreCase("Email or password is incorrect.") || errorMsg2.get(0).getText().equalsIgnoreCase("In order to sign in, you need to activate your account."))
					{
						loginStatus = "Failed";
					}
					else
					{
						loginStatus = "Success";
					}
				}
				else
				{
					loginStatus = "Success";
				}
			}
			else
			{
				System.out.println("no error message");
				loginStatus = "Success";
			}
			
		}
		catch(Exception e)
		{
			System.out.println("no error message");
			e.printStackTrace();
		}
		
		return loginStatus;
	}
	
	public String checkDashboard()
	{
		String status = "fail";
		try
		{
			Set<String> listOfWindow = driver.getWindowHandles();
			for(String windows : listOfWindow)
			{
				Thread.sleep(1000);
				
				driver.switchTo().window(windows);
				
				if(driver.getCurrentUrl().contains("dashboard"))
				{
					driver.switchTo().window(windows);
					System.out.println("logged in successfully");
					status = "pass";
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	
	public ArrayList<String> verifyIBMCourse()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			List<WebElement> ibmCourse = driver.findElements(By.xpath("//a[contains(text(),'IBM')]"));////a[contains(text(),'IBM')]//preceding::a[@class='dashboardCourseCards_spanLink__TuRN5'][@href]
			
			for(int i = 0; i < ibmCourse.size(); i++)
			{
				WebElement getIBMURL = ibmCourse.get(i).findElement(By.xpath("//preceding::a[@class='dashboardCourseCards_spanLink__TuRN5'][@href]"));
				String getURL = getIBMURL.getAttribute("href");
				String parentWindow = driver.getWindowHandle();
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(getURL);
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					
					if(driver.getCurrentUrl().contains("/course/"))
					{
						driver.switchTo().window(window);
						
						if(RegressionTesting.ENV_TO_USE.contains("dev"))
						{
							if(!driver.getCurrentUrl().contains("https://apps.skillup-dev.skillsnetwork.site/"))
							{
								status.add("fail"+getURL);
							}
						}
						else if(RegressionTesting.ENV_TO_USE.contains("prod"))
						{
							if(!driver.getCurrentUrl().contains("https://apps.myclass.skillup.online/"))
							{
								status.add("fail"+getURL);
							}
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
}

package com.palm.sanityTesting;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SignUpPageLinksLocator 
{
	WebDriver driver;
	MicrosoftCourseLocator microsoftCourseLocator;
	
	public SignUpPageLinksLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
	}
	
	public String checkSignupLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			final String url = OpenWebsite.setURL;
			String checkURL = url.replace("online", "online/");
			String parent = driver.getWindowHandle();
			Set<String> windows =driver.getWindowHandles();
			for(String eachWindow : windows)
			{
				driver.switchTo().window(eachWindow);
				
				if(driver.getCurrentUrl().equalsIgnoreCase(checkURL))
				{
					driver.switchTo().window(eachWindow);
					
					WebElement clickSignup = driver.findElement(By.cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons']>li:nth-child(3)>a"));
					js.executeScript("arguments[0].scrollIntoView();", clickSignup);
					if(clickSignup.isDisplayed())
					{
						js.executeScript("arguments[0].click()", clickSignup);
						if(driver.getCurrentUrl().contains("register"))
						{
							status = "pass";
						}
					}
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
	public String checkFacebookLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement facebook = driver.findElement(By.cssSelector("div[class='social-section']>a:nth-child(1)"));
			js.executeScript("arguments[0].scrollIntoView();", facebook);
			if(facebook.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				facebook.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("facebook"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
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
	
	public String checkGoogleLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement google = driver.findElement(By.cssSelector("div[class='social-section']>a:nth-child(2)"));
			js.executeScript("arguments[0].scrollIntoView();", google);
			if(google.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				google.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("google"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
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
	
	public String checkLinkedInLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement linkedIn = driver.findElement(By.cssSelector("div[class='social-section']>a:nth-child(3)"));
			js.executeScript("arguments[0].scrollIntoView();", linkedIn);
			if(linkedIn.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				linkedIn.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("linkedin"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
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
	
	public String checkMicrosoftLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement microsoftLink = driver.findElement(By.cssSelector("div[class='social-section']>a:nth-child(4)"));
			js.executeScript("arguments[0].scrollIntoView();", microsoftLink);
			if(microsoftLink.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				microsoftLink.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("microsoftonline"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
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
	
	public String checkLogInLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement clickLoginLink = driver.findElement(By.xpath("//a[contains(@href,'login') and contains(@class,'underline')]"));
			js.executeScript("arguments[0].scrollIntoView();", clickLoginLink);
			if(clickLoginLink.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				clickLoginLink.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("login"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String checkTermsOfServiceLink()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement clickTermOfServiceLink = driver.findElement(By.xpath("//a[contains(@href,'tos') and contains(@class,'underline')]"));
			js.executeScript("arguments[0].scrollIntoView();", clickTermOfServiceLink);
			if(clickTermOfServiceLink.isDisplayed())
			{
				String parentWindow = driver.getWindowHandle();
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				clickTermOfServiceLink.sendKeys(selectLinkOpeninNewTab);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("tos"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						driver.navigate().back();
						driver.switchTo().window(parentWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String checkPrivacyPolicyLink()
	{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String status = "";
			try
			{
				WebElement clickPrivacyPolicyLink = driver.findElement(By.xpath("//a[contains(@href,'privacy') and contains(@class,'underline')]"));
				js.executeScript("arguments[0].scrollIntoView();", clickPrivacyPolicyLink);
				if(clickPrivacyPolicyLink.isDisplayed())
				{
					String parentWindow = driver.getWindowHandle();
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					clickPrivacyPolicyLink.sendKeys(selectLinkOpeninNewTab);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
					Set<String> allWindows = driver.getWindowHandles();
					for(String windows : allWindows)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains("privacy"))
						{
							driver.switchTo().window(windows);
							status = "pass";
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
							driver.navigate().back();
							driver.switchTo().window(parentWindow);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						}
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

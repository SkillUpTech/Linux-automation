package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class ApplyCouponLocator
{
	WebDriver driver;
	OpenWebsite openWebsite;
	MicrosoftCourseLocator microsoftCourseLocator;
	RegressionGenericLocator regressionGenericLocator;
	String courseName = "";
	
	public ApplyCouponLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
		this.regressionGenericLocator = new RegressionGenericLocator(this.driver);
		
	}
	public ArrayList<String> launchCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			String url = OpenWebsite.setHost+data.get(1);
			String statusOfURL = microsoftCourseLocator.checkURLStatus(url);
			if(!statusOfURL.contains("fail"))
			{
				String parentWindow = driver.getWindowHandle();
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				Set<String> allWindow = driver.getWindowHandles();
				for(String window : allWindow)
				{
					driver.switchTo().window(window);
					
					if(driver.getCurrentUrl().contains("courses"))
					{
						driver.switchTo().window(window);
						WebElement clickEnroll = driver.findElement(By.cssSelector("div[class*='CourseDescription_buttonsContent']>button:nth-child(1)"));
						js.executeScript("arguments[0].scrollIntoView();", clickEnroll);
						if(clickEnroll.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickEnroll);
							
							driver.switchTo().window(window);
							
							if(driver.getCurrentUrl().contains("login?"))
							{
								driver.switchTo().window(window);
								
								status.add(regressionGenericLocator.loginProcess(data.get(2)));
								break;
							}
						}
					}
					driver.switchTo().window(window);
				}
			}
			else
			{
				status.add("fail");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	ArrayList<String> status = new ArrayList<String>();
	public ArrayList<String> ApplyCoupon(ArrayList<String> data)
	{
		ArrayList<String> Couponstatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String result = "";
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				
				if(driver.getCurrentUrl().contains("/basket/"))
				{
					driver.switchTo().window(window);
				for(int k = 1; k < data.size(); k++)
				{
					String[] couponvalue = data.get(k).split("_");
					WebElement clickPromoLink = driver.findElement(By.cssSelector("a[id*='ui-collapse']"));
					js.executeScript("arguments[0].scrollIntoView();", clickPromoLink);
					if(clickPromoLink.isDisplayed())
					{
						js.executeScript("arguments[0].click()", clickPromoLink);
					}
					
						
					WebElement enterCode = driver.findElement(By.cssSelector("input#id_code"));
					js.executeScript("arguments[0].scrollIntoView();", enterCode);
					if(enterCode.isDisplayed())
					{
						enterCode.sendKeys(couponvalue[0]);
					}	
					WebElement submitApply = driver.findElement(By.cssSelector("button#apply-voucher-button"));
					js.executeScript("arguments[0].scrollIntoView();", submitApply);
					if(submitApply.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submitApply);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(driver.findElements(By.cssSelector("div[class='alertinner wicon']")).size()>0)
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							String getCoupon = driver.findElement(By.cssSelector("div[class='alertinner wicon']")).getText();
							result = getCoupon;
							System.out.println(result);
							
						}
						else
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							ArrayList<String> statusOfAmount = new ArrayList<String>();		
							List<WebElement> checkAppliedCoupon = driver.findElements(By.xpath("//div[@class='selected-plan']/*"));
							for(int j = 0; j < checkAppliedCoupon.size(); j++)
							{
								js.executeScript("arguments[0].scrollIntoView();", checkAppliedCoupon.get(j));
								if(checkAppliedCoupon.get(j).isDisplayed())
								{
									String getContent = checkAppliedCoupon.get(j).getAttribute("textContent");
									statusOfAmount.add(getContent);
								}
							}
							result = String.join(", ", statusOfAmount);
							System.out.println(result);
							WebElement clickCloseIcon = driver.findElement(By.xpath("//button[@class='remove-voucher']/img"));
							js.executeScript("arguments[0].scrollIntoView();", clickCloseIcon);
							if(clickCloseIcon.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickCloseIcon);
							}
						}
					}
					
					
				}
			}
				driver.switchTo().window(window);
		    }
			
			status.add(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Couponstatus.add("fail");
		}
		return Couponstatus;
	}
	
	public ArrayList<String> couponResult()
	{
		try
		{
			System.out.println("coupon details : "+status);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}

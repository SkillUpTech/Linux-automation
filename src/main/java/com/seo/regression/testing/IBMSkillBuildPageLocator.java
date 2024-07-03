package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class IBMSkillBuildPageLocator 
{
	WebDriver driver;
	int respCode = 200;
	
	public IBMSkillBuildPageLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> headerFeatureOnCategory(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{	
			driver.switchTo().newWindow(WindowType.TAB);
			String url = OpenWebsite.setHost+data;
			driver.get(url);
			Set<String> windows = driver.getWindowHandles();
			for(String window : windows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains(url))
				{
					driver.switchTo().window(window);
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.cssSelector("div[class*='Header_headerRight']")).size()>0)
					{
						System.out.println("header present");
						status.add("fail");
					}
					else
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						System.out.println("header not present");
						
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.cssSelector("footer#newsletter")).size()>0)
					{
						System.out.println("footer present");
						status.add("fail");
					}
					else
					{
						System.out.println("footer not present");
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

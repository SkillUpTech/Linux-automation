package com.seo.regression.testing;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class VerifyProgramURLLocator
{
	WebDriver driver;
	
	public VerifyProgramURLLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> verifyProgramURL(ArrayList<String> data)
	{
		String URL = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String parentWindow = driver.getWindowHandle();
			for(int i = 1; i < data.size(); i++)
			{
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(OpenWebsite.setHost+data.get(i));
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					
					if(driver.getCurrentUrl().contains("/courses/"))
					{
						driver.switchTo().window(window);
						
						WebElement programURL = driver.findElement(By.xpath("//div[contains(@class,'CourseDescription_infoBoxText')]//a"));
						js.executeScript("arguments[0].scrollIntoView();", programURL);
						if(programURL.isDisplayed())
						{
							URL = programURL.getAttribute("href");
							driver.switchTo().newWindow(WindowType.TAB);
							driver.get(URL);
							Set<String> nextWindows = driver.getWindowHandles();
							for(String newWindow : nextWindows)
							{
								driver.switchTo().window(newWindow);
								
								if((!driver.getCurrentUrl().contains("courses"))&&(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/"))&&(!driver.getCurrentUrl().equalsIgnoreCase("data:,"))) 
								{
									driver.switchTo().window(newWindow);
									
									if(URL.equalsIgnoreCase(driver.getCurrentUrl()))
									{
										System.out.println("Program link from the course about page is same as program about page URL"); 
										driver.close();
										driver.switchTo().window(window);
									}
									else
									{
										System.out.println("Program link from the course about page is not same as program about page URL"); 
										status.add("program url : "+URL+ "is not same as program link mentioned on course : "+data.get(i));
										driver.close();
										driver.switchTo().window(window);
									}
								}
								
							}
						}
						driver.close();
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

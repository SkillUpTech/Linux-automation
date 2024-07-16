package com.seo.regression.testing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryBannerLocator
{
	WebDriver driver;
	
	public CategoryBannerLocator(WebDriver driver)
	{
		this.driver = driver;
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
	
	public ArrayList<String> verifyBanner(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean checkStatus = false;
		try
		{
			for(int i = 1; i < data.size(); i++)
			{
				String urlStatus = this.checkURLStatus(OpenWebsite.setHost+data.get(i));
				
				if(!urlStatus.contains("fail"))
				{
					driver.get(OpenWebsite.setHost+data.get(i));
					
					List<WebElement> banners = driver.findElements(By.xpath("//ul[@class='slick-dots']/li/button"));
					
					for(int j = 1; j <= banners.size(); j++)
					{
						
						if(driver.findElements(By.xpath("//ul[@class='slick-dots']/li["+(j)+"]/button")).size()>0)
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							
							WebElement checkActiveBanner = driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+j+"]/button"));
							
							js.executeScript("arguments[0].scrollIntoView();", checkActiveBanner);
							
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							
							js.executeScript("arguments[0].click()", checkActiveBanner);
							
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							
								
							if(driver.findElements(By.xpath("//div[@class='slick-slide slick-active slick-current']//button[contains(text(),'Download Whitepaper')]")).size()>0)
							{
								checkStatus = true;
								Thread.sleep(1000);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								WebElement clickWhitepaper = driver.findElement(By.xpath("//div[@class='slick-slide slick-active slick-current']//button[contains(text(),'Download Whitepaper')]"));
								js.executeScript("arguments[0].scrollIntoView();", clickWhitepaper);
								js.executeScript("arguments[0].click()", clickWhitepaper);
								String parent = driver.getWindowHandle();
								Set<String> windows = driver.getWindowHandles();
								for(String window : windows)
								{
									driver.switchTo().window(window);
									
									if(driver.getCurrentUrl().contains("whitepaper"))
									{
										driver.switchTo().window(window);
										
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										System.out.println("whitepaper page : "+driver.getCurrentUrl());
										status.add("pass");
										driver.navigate().back();
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										driver.switchTo().window(parent);
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
									}
								}
									driver.switchTo().window(parent);
							}
							else if(driver.findElements(By.xpath("//div[@class='slick-slide slick-active slick-current']//button[contains(text(),'Download Brochure')]")).size()>0)
							{
								Thread.sleep(1000);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								WebElement clickBrochureButton = driver.findElement(By.xpath("//div[@class='slick-slide slick-active slick-current']//button[contains(text(),'Download Brochure')]"));
								js.executeScript("arguments[0].scrollIntoView();", clickBrochureButton);
								js.executeScript("arguments[0].click()", clickBrochureButton);
								String parent = driver.getWindowHandle();
								Set<String> windows = driver.getWindowHandles();
								for(String window : windows)
								{
									driver.switchTo().window(window);
									
									if(driver.getCurrentUrl().contains("techmaster"))
									{
										driver.switchTo().window(window);
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										System.out.println("Broucher page : "+driver.getCurrentUrl());
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										status.add("pass");
										driver.navigate().back();
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										driver.switchTo().window(parent);
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
									}
								}
								driver.switchTo().window(parent);
							}
						}
					}	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		return status;
	}
}

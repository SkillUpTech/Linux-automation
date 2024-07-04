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
		boolean process = false;
		try
		{
			for(int i = 1; i < data.size(); i++)
			{
				String urlStatus = this.checkURLStatus(OpenWebsite.setHost+data.get(i));
				if(!urlStatus.contains("fail"))
				{
					driver.get(OpenWebsite.setHost+data.get(i));
					
					List<WebElement> banners = driver.findElements(By.xpath("//ul[@class='slick-dots']/li/button"));
					
					for(int j = 0; j < banners.size(); j++)
					{
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						Thread.sleep(100);
						int attempts = 0;
						 while (attempts < 3) {
						        try {
						        	driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+(j+1)+"]/button"));
						            break;
						        } catch (StaleElementReferenceException e) {
						        	driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+(j+1)+"]/button")); // re-locate the element
						        }
						        attempts++;
						    }
						
						
						if(driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+(j+1)+"]/button")).isDisplayed())
						{
							WebElement checkActiveBanner = driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+(j+1)+"]/button"));
							
							js.executeScript("arguments[0].scrollIntoView();", checkActiveBanner);
							
							if(process == true)
							{
								js.executeScript("arguments[0].click()", checkActiveBanner);

								WebElement clickButton = driver.findElement(By.xpath("//div[@class='slick-slide slick-active slick-current'][@data-index='1']//button[contains(text(),'Download Brochure')]"));
								if(clickButton.getText().equalsIgnoreCase("Download Brochure"))
								{
									js.executeScript("arguments[0].click()", clickButton);
									String parent = driver.getWindowHandle();
									Set<String> windows = driver.getWindowHandles();
									for(String window : windows)
									{
										driver.switchTo().window(window);
										if(driver.getCurrentUrl().contains("techmaster"))
										{
											driver.switchTo().window(window);
											System.out.println("Broucher page : "+driver.getCurrentUrl());
											status.add("pass");
											Thread.sleep(1000);
											driver.navigate().back();
											Thread.sleep(1000);
											driver.switchTo().window(parent);
											Thread.sleep(1000);
										}
									}
									driver.switchTo().window(parent);
									Thread.sleep(1000);
									break;
								}
							
							}
							else if(process == false)
							{
								List<WebElement> clickButton = driver.findElements(By.xpath("//button[contains(@class,'CareerUpdate_downloadButton')]"));
								for(int k = 0; k < clickButton.size(); k++)
								{
									if(clickButton.get(k).getText().equalsIgnoreCase("Download Whitepaper"))
									{
										js.executeScript("arguments[0].click()", clickButton.get(k));
										String parent = driver.getWindowHandle();
										Set<String> windows = driver.getWindowHandles();
										for(String window : windows)
										{
											driver.switchTo().window(window);
											if(driver.getCurrentUrl().contains("whitepaper"))
											{
												driver.switchTo().window(window);
												System.out.println("whitepaper page : "+driver.getCurrentUrl());
												process = true;
												status.add("pass");
												Thread.sleep(500);
												driver.navigate().back();
												Thread.sleep(2000);
												driver.switchTo().window(parent);
												Thread.sleep(1000);
											}
										}
										driver.switchTo().window(parent);
										Thread.sleep(1000);
										break;
									}
									
								}
							}
						}
						Thread.sleep(1000);
					}
				}
				else
				{
					System.out.println("issue in url"+data.get(i+1));
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

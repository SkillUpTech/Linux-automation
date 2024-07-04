package com.seo.regression.testing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

public class CourseCardHoverLocator
{
	WebDriver driver;
	
	public CourseCardHoverLocator(WebDriver driver)
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
	public ArrayList<String> checkCourseCard(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			if(data.size()==1)
			{
				List<WebElement> cards = driver.findElements(By.xpath("//div[@class='col-12 undefined d-xl-block false']//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_RegularcardLinks')]/a"));
				for(int i = 0; i < cards.size(); i++)
				{
					WebElement eachCard = cards.get(i);
					Actions action = new Actions(driver);
					action.moveToElement(eachCard).build().perform();
					String toolTip = cards.get(i).getAttribute("href");
					System.out.println("card hover text : "+toolTip);
				}
			}
			else if(data.size()>1)
			{
				String parent = driver.getWindowHandle();
				for(int j = 1; j < data.size(); j++)
				{
					String getURL = OpenWebsite.setHost+data.get(j);
					String checkSite = this.checkURLStatus(getURL);
					if(!checkSite.contains("fail"))
					{
						driver.switchTo().newWindow(WindowType.TAB);
						driver.get(getURL);
						Set<String> windows = driver.getWindowHandles();
						for(String window : windows)
						{
							driver.switchTo().window(window);
							if(driver.getCurrentUrl().contains("?utm")||driver.getCurrentUrl().contains("-courses-"))
							{
								driver.switchTo().window(window);
								List<WebElement> cards = driver.findElements(By.xpath("//div[@class='col-12 undefined d-xl-block false']//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_RegularcardLinks')]/a"));
								for(int i = 0; i < cards.size(); i++)
								{
									WebElement eachCard = cards.get(i);
									Actions action = new Actions(driver);
									action.moveToElement(eachCard).build().perform();
									String toolTip = cards.get(i).getAttribute("href");
									System.out.println("card hover text : "+toolTip);
									if(i == cards.size()-1)
									{
										driver.close();
										driver.switchTo().window(parent);
										break;
									}
								}
							}
						}
					}
					else
					{
						System.out.println("issue in site");
						status.add("fail - "+data.get(j));
					}
					driver.switchTo().window(parent);
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

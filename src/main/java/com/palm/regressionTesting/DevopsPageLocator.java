package com.palm.regressionTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DevopsPageLocator
{
	WebDriver driver;
	
	public DevopsPageLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String checkDownloadForm()
	{
		String data = "";
		
		try
		{
			String url = OpenWebsite.setHost;
			driver.get(url+"/devops/");
			if(driver.findElements(By.cssSelector("div[class*='TopSection_TopSecRgt'] div[class*='DownloadForm_downloadFromBottom']>iframe")).size()>0)
			{
				System.out.println("Form is present on Devops Engg page");
				data = "true";
			}
			else
			{
				data = "false";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
	public String checkContent(String dataFromExcel)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String data = "";
		
		try
		{
			WebElement topContent = driver.findElement(By.xpath("//div[contains(@class,'TopSection_DevopsMain')]//parent::*"));
			System.out.println(topContent.getText());
			if(topContent.getText().equalsIgnoreCase(dataFromExcel))
			{
				data = "true";
			}
			else
			{
				data = "false";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
}

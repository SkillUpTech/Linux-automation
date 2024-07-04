package com.palm.regressionTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BusinessPageLocator
{
	String url ;
	WebDriver driver;
	OpenWebsite openWebsite;
	
	public BusinessPageLocator(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}

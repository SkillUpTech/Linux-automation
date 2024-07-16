package com.seo.sanityTesting;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.*;

public class OpenWebsite
{
	static String setURL;
	public static WebDriver openDriver(String browserName)
	{
		WebDriver driver = null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", RegressionTesting.driverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Hemamalini\\Downloads\\geckodriver-v0.33.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver(); 
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		}
		return driver;
	}
	
	static String setHost = null;
	public static String setEnvironment(String host)
	{
		if(host.equalsIgnoreCase("prod-in"))
		{
			setHost = "https://in.skillup.online";
		}
		else if(host.equalsIgnoreCase("stagecourses-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("stage-in"))
		{
			setHost = "https://stage-in.skillup.online";
		}
		else if(host.equalsIgnoreCase("qa-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("qa"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("stage"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("prod"))
		{
			setHost = "https://skillup.online";
		}
		else if(host.equalsIgnoreCase("dev-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("dev"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		return setHost;
	}
	
	public static String openSite(WebDriver driver)
	{
		setURL = setEnvironment(RegressionTesting.ENV_TO_USE);
		driver.get(setURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return setURL;
	}
	
}

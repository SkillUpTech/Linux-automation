
package com.seo.regression.testing;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.regression.utility.*;

public class OpenWebsite
{
	static String setURL;
	public static WebDriver openDriver(String browserName) throws InterruptedException
	{
		WebDriver driver = null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			ChromeOptions options = new ChromeOptions();
			/*
			 * options.addArguments("--remote-allow-origins=*");
			 * options.addArguments("--disable notifications");
			 */
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			Thread.sleep(5000);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","/usr/local/bin/geckodriver");
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
			String convertURL = "in";
			setHost = "https://"+convertURL+".skillup.online";
		}
		else if(host.equalsIgnoreCase("stagecourses-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("stage-in"))
		{
			String converturl = "stage-in";
			setHost = "https://"+converturl+".skillup.online";
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
		return setHost;
	}
	
	public static String launchCourse(WebDriver driver, String urlFromExcel)
	{
		String setURL = setEnvironment(RegressionTesting.ENV_TO_USE)+urlFromExcel;
		driver.get(setURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		return setURL;
	}
	public static String openSite(WebDriver driver)
	{
		String setURL;
		setURL = setEnvironment(RegressionTesting.ENV_TO_USE);
		driver.get(setURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return setURL;
	}
	
}

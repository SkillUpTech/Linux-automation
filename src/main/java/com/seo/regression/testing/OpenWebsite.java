
package com.seo.regression.testing;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
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
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			 
		    // Explicitly set the ChromeDriver port
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--start-maximized");
		    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		    options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--ignore-ssl-errors=yes");
		    options.addArguments("--ignore-certificate-errors");
		    // Specify the port (default is 9515)
		    options.addArguments("--remote-debugging-port=9515");
		 
		    driver = new ChromeDriver(options);
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
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

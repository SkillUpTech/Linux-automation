
package com.seo.regression.testing;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.regression.utility.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenWebsite
{
	static String setURL;
	public static WebDriver openDriver(String browserName)
	{
		WebDriver driver = null;
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			WebDriverManager.chromedriver().setup();
		   ChromeOptions options = new ChromeOptions();
		   options.addExtensions(new File("/home/edx-root/eclipse-workspace/Linux-automation/GIGHMMPIOBKLFEPJOCNAMGKKBIGLIDOM_5_15_0_0"));
		   options.setCapability("browserVersion", "119.0.6045.199-1");
		 // options.setBinary(chromeBinaryPath);
		    options.addArguments("--headless");
//		    options.addArguments("--no-sandbox");
		    options.addArguments("--remote-allow-origins=*");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--headless");
			/*
			 * options.addArguments("start-maximized");
			 * options.addArguments("disable-infobars");
			 * options.addArguments("--disable-gpu");
			 * options.addArguments("--disable-dev-shm-usage");
			 * options.addArguments("--disable-extensions");
			 */
		    DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
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
			setHost = "https://stage.skillup.online";
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
		System.out.println("environment for regression is" +RegressionTesting.ENV_TO_USE);
		setURL = setEnvironment(RegressionTesting.ENV_TO_USE);
		driver.get(setURL);
		System.out.println("checking host url : "+driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return setURL;
	}
	
}

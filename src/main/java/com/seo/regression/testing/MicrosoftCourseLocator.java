package com.seo.regression.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverAction;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MicrosoftCourseLocator 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public MicrosoftCourseLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> verifyMicrosftPage()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			WebElement  clickCourseDropdown = driver.findElement(By.cssSelector("a#navbarDropdown div[class=' Header_category__mr_e4']"));
			clickCourseDropdown.click();
			List<WebElement> learningPartners = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] ul[class='learning-Partners']>li>a"));
			for(int i = 0; i < learningPartners.size();i++)
			{
				String getLearningPartnerURL = learningPartners.get(i).getAttribute("href");
				if(getLearningPartnerURL.contains("microsoft"))
				{
					String url = this.checkCourseCode(getLearningPartnerURL);
					String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					learningPartners.get(i).sendKeys(n);
					if(url.equalsIgnoreCase("fail"))
					{
						processStatus.add("fail");
					}
					String parentWindow = driver.getWindowHandle();
					Set<String> childWnidow = driver.getWindowHandles();
					for(String windows : childWnidow)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains("microsoft"))
						{
							driver.switchTo().window(windows);
							System.out.println("microsoft page : "+driver.getCurrentUrl());
							processStatus.add("pass");
							break;
						}
					}
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			processStatus.add("fail");
		}
		return processStatus;
	}

	/*
	 * public String checkURLStatus(String getURL) { String status = "fail"; String
	 * addHosturl = getURL; HttpURLConnection huc = null; int respCode = 200; try {
	 * huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
	 * huc.setRequestMethod("HEAD"); huc.connect(); respCode =
	 * huc.getResponseCode(); System.out.println("status code : "+respCode + " "
	 * +addHosturl); if(respCode > 200) {
	 * System.out.println("broken link"+addHosturl); status = "fail" + respCode; }
	 * else { System.out.println("un broken link"+addHosturl); status = "pass"; } }
	 * catch(Exception e) { e.printStackTrace(); } return status; }
	 */
	public String checkCourseCode(String getURL)
	{
		String getstatus = "pass";
		String url="";
		int respCode = 200;
		HttpURLConnection huc = null;
		String addHosturl = "";
			String endURL = getURL;
			if(endURL.contains("enterprise"))
			{	
				if(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE).contains("stage"))
				{
					url = /* "https://stagecourses-in.skillup.online"+ */endURL;
					addHosturl = url;
				}
			}
			else
			{
				url = /* OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+ */endURL;
				addHosturl = url;
			}
			try
			{

				URL obj = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
				conn.setReadTimeout(5000);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");

				System.out.println("Request URL ... " + addHosturl);

				boolean redirect = false;

				// normally, 3xx is redirect
				int status = conn.getResponseCode();
				if (status != HttpURLConnection.HTTP_OK) {
					if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
							|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
				}

				System.out.println("Response Code ... " + status);

				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = conn.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = conn.getHeaderField("Set-Cookie");

					conn = (HttpURLConnection) new URL(newUrl).openConnection();
					conn.setRequestProperty("Cookie", cookies);
					conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					conn.addRequestProperty("User-Agent", "Mozilla");
					conn.addRequestProperty("Referer", "google.com");
											
					System.out.println("Redirect to URL : " + newUrl);

				}

				BufferedReader in = new BufferedReader(
			                              new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer html = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					html.append(inputLine);
				}
				in.close();

				System.out.println("Done");
				if(status>200)
				{
					getstatus = "fail" + respCode;
				}
			    } 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			
		return getstatus;
	}
	
	
	public ArrayList<String> verifyMicrosoftScourses()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 1100)", "");
			//Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='ManageCardsLimit_showMoreSection'] button"));
			while(clickShowMore.isDisplayed() != clickShowMore.getText().equalsIgnoreCase("Show less"))
			{
				js.executeScript("arguments[0].click()", clickShowMore);
			}
			List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='LearningCatalog_customCard']"));
			for(int i = 0; i < listOfCourses.size(); i++)
			{
				String courseURL = listOfCourses.get(i).findElement(By.cssSelector(" div a[href]")).getAttribute("href");
				String urlLink = this.checkCourseCode(courseURL);
				if(urlLink.contains("fail"))
				{
					processStatus.add(courseURL+urlLink);
				}
				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1. executeScript("window. open('"+urlLink+"');" );
				String parentWindow = driver.getWindowHandle();
				Set<String> childWnidow = driver.getWindowHandles();
				for(String windows : childWnidow)
				{
					driver.switchTo().window(windows);
					if(!parentWindow.equalsIgnoreCase(windows))
					{
						driver.switchTo().window(windows);
						driver.close();
						driver.switchTo().window(parentWindow);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			processStatus.add("MicrosoftScourses");
		}
		return processStatus;
	}
}

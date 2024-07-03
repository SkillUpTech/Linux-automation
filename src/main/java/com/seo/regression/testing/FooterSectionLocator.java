package com.seo.regression.testing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FooterSectionLocator
{
	WebDriver driver;
	public FooterSectionLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public String getDriverDetails()
	{
		String driverName =	OpenWebsite.openSite(driver)+"/";
		return driverName;
	}
	public String verifySkillupLogo() throws InterruptedException
	{
		String status = "failed";
		try
		{
			driver.switchTo().newWindow(WindowType.TAB);
			OpenWebsite.openSite(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			js.executeScript("window.scrollBy(0, -300)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			//String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
			WebElement clickLogo = driver.findElement(By.cssSelector("div[class*='Footer_footertopmenu'] a[class*='Footer_FTLogo']"));
			wait.until(ExpectedConditions.elementToBeClickable(clickLogo));
		//	clickLogo.click();
			js.executeScript("arguments[0].click()", clickLogo);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			status = "pass";
		}
		catch(Exception e)
		{
			status="fail";
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String verifyTwitter() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		//Thread.sleep(2000);
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
	//	Thread.sleep(1000);
		WebElement clickTwitter = driver.findElement(By.cssSelector("ul[class*=' Footer_socialIconsSection'] li:nth-child(1) a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickTwitter));
		js.executeScript("arguments[0].click()", clickTwitter);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("x")||driver.getCurrentUrl().contains("intent"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("twitter window");
					status = "success";
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}
			driver.switchTo().window(parentWindow);
		}
		driver.switchTo().window(parentWindow);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return status;
	}
	
	public String verifyInstagram() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -100)", "");
		WebElement clickInstagram = driver.findElement(By.cssSelector("ul[class*=' Footer_socialIconsSection'] li:nth-child(4) a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickInstagram));
		js.executeScript("arguments[0].click()", clickInstagram);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("instagram"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("instagram window");
					status = "success";
					driver.close();
					
					driver.switchTo().window(parentWindow);
					break;
				}
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return status;
	}
	
	public String verifyFacebook() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -160)", "");
		WebElement clickFacebook = driver.findElement(By.cssSelector("ul[class*=' Footer_socialIconsSection'] li:nth-child(2) a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickFacebook));
		js.executeScript("arguments[0].click()", clickFacebook);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("https://www.facebook.com/"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("facebook window");
					status = "success";
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
			}
			driver.switchTo().window(parentWindow);
		}
		driver.switchTo().window(parentWindow);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return status;
	}
	
	public String verifyLinkedIn() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		WebElement clickLinkedIn = driver.findElement(By.cssSelector("ul[class*=' Footer_socialIconsSection'] li:nth-child(3) a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickLinkedIn));
		js.executeScript("arguments[0].click()", clickLinkedIn);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("https://www.linkedin.com/"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("linkedIn window");
					status = "success";
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
			}
			driver.switchTo().window(parentWindow);
		}
		driver.switchTo().window(parentWindow);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return status;
	}
	
	public String verifyContactUs() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		WebElement clickContactUs = driver.findElement(By.cssSelector("div[class='Footer_ContActUsIn__ywIhS'] span img[alt='icon']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickContactUs));
		js.executeScript("arguments[0].click()", clickContactUs);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("contact window");
					String getURL = driver.getCurrentUrl();//https://stage-in.skillup.online/
					String subString = StringUtils.substringBefore(getURL, "online/");
					subString = subString+"online";
					driver.get(subString);
					if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
					{
						status = "success";
						break;
					}
				}	
			}
			else if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("contact window");
					driver.close();
					driver.switchTo().window(parentWindow);
					if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
					{
						status = "success";
						break;
					}
				}
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return status;
	}
	
	public String verifyAboutSkillupOnline() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0, 500)", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		List<WebElement> clickAboutSkillupOnline= driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
		for(int i = 0; i < clickAboutSkillupOnline.size(); i++)
		{
			String getText = clickAboutSkillupOnline.get(i).getText();
			if(getText.contains("About"))
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
				wait.until(ExpectedConditions.elementToBeClickable(clickAboutSkillupOnline.get(i)));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				js.executeScript("arguments[0].click()", clickAboutSkillupOnline.get(i));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				String parentWindow = driver.getWindowHandle();
				Set<String> nextWindow = driver.getWindowHandles();
				Iterator<String> iterator = nextWindow.iterator();
				while (iterator.hasNext()) 
				{
					String childWindow = iterator.next();
					if(parentWindow.equalsIgnoreCase(childWindow))
					{
						if(driver.getCurrentUrl().contains("about"))
						{
							System.out.println("about window");
							String getURL = driver.getCurrentUrl();//https://stage-in.skillup.online/
							String subString = StringUtils.substringBefore(getURL, "online/");
							subString = subString+"online";
							driver.get(subString);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
							if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
							{
								status = "success";
							}
						}	
					}
				}
				break;
			}
		}
		return status;
	}
	
	public String verifyBusiness() throws InterruptedException
	{
		String status = "failed";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		List<WebElement> clickBusiness= driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
		for(int i = 0; i < clickBusiness.size(); i++)
		{
			js.executeScript("arguments[0].scrollIntoView();", clickBusiness.get(i));
			String getText = clickBusiness.get(i).getText();
			if(getText.contains("Business"))
			{
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
				wait.until(ExpectedConditions.elementToBeClickable(clickBusiness.get(i)));
				js.executeScript("arguments[0].click()", clickBusiness.get(i));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				String parentWindow = driver.getWindowHandle();
				Set<String> nextWindow = driver.getWindowHandles();
				Iterator<String> iterator = nextWindow.iterator();
				while (iterator.hasNext()) 
				{
					String childWindow = iterator.next();
					if(parentWindow.equalsIgnoreCase(childWindow))
					{
						if(driver.getCurrentUrl().contains("enterprise"))
						{
							System.out.println("business window");
							String getURL = driver.getCurrentUrl();//https://stage-in.skillup.online/
							String subString = StringUtils.substringBefore(getURL, "online/");
							subString = subString+"online";
							driver.get(subString);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
							if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
							{
								status = "success";
							}
						}	
					}
				}
				break;
				
			}
		}
		return status;
	}
	
	public String verifyFaq() throws InterruptedException
	{
		String status = "failed";
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			List<WebElement> clickFaq= driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
			for(int i = 0; i < clickFaq.size(); i++)
			{
				WebElement element = wait.until(ExpectedConditions.visibilityOf(clickFaq.get(i)));
				String getText = element.getText();
				if(getText.contains("FAQ"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(clickFaq.get(i)));
					String parentwindow = driver.getWindowHandle();
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
					clickFaq.get(i).sendKeys(selectLinkOpeninNewTab);
					for(String winHandle : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle);
					    if(driver.getCurrentUrl().contains("faq"))
					    {
					    	System.out.println("faq page verification done");
					    	status = "success";
					    	driver.close();
					    	break;
					    }
					}
					driver.switchTo().window(parentwindow);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String verifyPrivacyPolicy() throws InterruptedException
	{
		String status = "failed";
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			System.out.println("privacy page verification");
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			List<WebElement> clickBusiness= driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
			for(int i = 0; i < clickBusiness.size(); i++)
			{
				WebElement element = wait.until(ExpectedConditions.visibilityOf(clickBusiness.get(i)));
				String getText = element.getText();
				if(getText.contains("Privacy"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(clickBusiness.get(i)));
					String parentwindow = driver.getWindowHandle();
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
					clickBusiness.get(i).sendKeys(selectLinkOpeninNewTab);
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);
					    if(driver.getCurrentUrl().contains("privacy"))
					    {
					    	System.out.println("Privacy page verification done");
					    	status = "success";
					    	driver.close();
					    	break;
					    }
					}
					driver.switchTo().window(parentwindow);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String verifyTermsofService() throws InterruptedException
	{
		
		String status = "failed";
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			System.out.println("Terms page verification");
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			List<WebElement> clickTerms = driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
			for(int i = 0; i < clickTerms.size(); i++)
			{
				WebElement element = wait.until(ExpectedConditions.visibilityOf(clickTerms.get(i)));
				String getText = element.getText();
				if(getText.contains("Terms"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(clickTerms.get(i)));
					String parentwindow = driver.getWindowHandle();
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
					clickTerms.get(i).sendKeys(selectLinkOpeninNewTab);
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);
					    if(driver.getCurrentUrl().contains("tos"))
					    {
					    	System.out.println("Terms page verification done");
					    	status = "success";
					    	driver.close();
					    	break;
					    }
					}
					driver.switchTo().window(parentwindow);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String verifyBlog() throws InterruptedException
	{
		String status = "failed";
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			System.out.println("Blog page verification");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			List<WebElement> clickBlog= driver.findElements(By.cssSelector("div[class*='Footer_FootMenu']>ul>li>a"));
			for(int i = 0; i < clickBlog.size(); i++)
			{
				WebElement element = wait.until(ExpectedConditions.visibilityOf(clickBlog.get(i)));
				String getText = element.getText();
				if(getText.contains("Blog"))
				{
					wait.until(ExpectedConditions.elementToBeClickable(clickBlog.get(i)));
					String parentwindow = driver.getWindowHandle();
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
					clickBlog.get(i).sendKeys(selectLinkOpeninNewTab);
					for(String winHandle : driver.getWindowHandles())
					{
						driver.switchTo().window(winHandle);
					    if(driver.getCurrentUrl().contains("blog"))
					    {
					    	System.out.println("Blog page verification done");
					    	status = "success";
					    	driver.close();
					    	break;
					    }
					}
					driver.switchTo().window(parentwindow);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ArrayList<String> verifyPopularCategories(ArrayList<String> data) throws InterruptedException
	{
		ArrayList<String> status = new ArrayList<String>();
		String getHost = OpenWebsite.setHost;
		try
		{
			if(!driver.getCurrentUrl().equalsIgnoreCase(getHost))
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.open()");
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				OpenWebsite.openSite(driver);
			}
			else
			{
				System.out.println("host is present");
			}
			((JavascriptExecutor) driver)
		     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0, 500)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
			List<WebElement> popularCategories = driver.findElements(By.cssSelector("div[class='Footer_PopularCategories__23uL0'] ul li a"));
			for(int i = 0; i < popularCategories.size(); i++)
			{
				if(popularCategories.size() == 14)
				{
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					String categoriesName = popularCategories.get(i).getText();
					if(popularCategories.get(i).isDisplayed())
					{
						String getCatagoriesURL =  popularCategories.get(i).getAttribute("href");
						String urlstatus=this.checkURLStatus(getCatagoriesURL);
						if(urlstatus.equalsIgnoreCase("failed"))
						{
							status.add(categoriesName);
						}
						else
						{
							status.add("pass");
						}
						String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
						popularCategories.get(i).sendKeys(n);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						String parentWindow = driver.getWindowHandle();
						Set<String> windows = driver.getWindowHandles();
						for(String allWindows : windows)
						{
							if(!parentWindow.equalsIgnoreCase(allWindows)&&!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
							{
								driver.switchTo().window(allWindows);
								System.out.println(driver.getCurrentUrl());
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
								driver.close();
								driver.switchTo().window(parentWindow);
								((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
							}
						}
					}
				}
			}	
		}
		catch(StaleElementReferenceException e)
		{
			e.printStackTrace();
			status.add("failed");
		}
		return status;
	}
	
	public String checkURLStatus(String getURL)
	{
		String URLStatus = "failed";
		String addHosturl = getURL;
		HttpURLConnection huc = null;
		int respCode = 200;
		try
		{
			huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println("status code : "+respCode + " " +addHosturl);
			if(respCode > 200)
			{
				System.out.println("broken link"+addHosturl);
				URLStatus = "failed";
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				URLStatus = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			URLStatus = "failed";
		}
		return URLStatus;
	}
	public ArrayList<String> verifyPopularCourses(ArrayList<String> data) throws InterruptedException
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
		if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost))
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.open()");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			OpenWebsite.openSite(driver);
		}
		else
		{
			System.out.println("host is present");
		}
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0, 500)", "");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		List<WebElement> popularCourses = driver.findElements(By.cssSelector("div[class*='Footer_PopularCourses'] ul li a"));
		for(int i = 0; i < popularCourses.size(); i++)
		{
			if(popularCourses.get(i).isDisplayed())
			{
				String URLStatus=this.checkURLStatus(popularCourses.get(i).getAttribute("href")); 
				if(URLStatus.equalsIgnoreCase("failed"))
				{
					status.add(popularCourses.get(i).getText());
				}
				else
				{
					status.add("pass");
				}
				String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
				popularCourses.get(i).sendKeys(n);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				String parentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for(String allWindows : windows)
				{
					if(!parentWindow.equalsIgnoreCase(allWindows) && !driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
					{
						driver.switchTo().window(allWindows);
						System.out.println(driver.getCurrentUrl());
						if(i == 3)
						{
							ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
							driver.switchTo().window(tabs.get(1));
							OpenWebsite.openSite(driver); 
							driver.switchTo().window(tabs.get(0));
							driver.close();
							driver.switchTo().window(tabs.get(1));
							((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)");
							break;
						}
						else
						{
							driver.close();
							driver.switchTo().window(parentWindow);
							((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)");
						}
					}
				}
			}
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		status.add("failed");
		}
		return status;
	}
	
	public ArrayList<String> verifyLatestBlogs(ArrayList<String> data) throws InterruptedException
	{
		ArrayList<String> status = new ArrayList<String>();
		String getHost = OpenWebsite.setHost;
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for(int i = 0; i < tabs.size(); i++)
		{
			if(tabs.size()>1)
			{
				if(driver.getCurrentUrl().contains("about:blank"))
				{
					driver.close();
					driver.switchTo().window(tabs.get(0));
				}
				else
				{
					System.out.println(driver.getCurrentUrl());
				}
			}
			else if(driver.getCurrentUrl().equalsIgnoreCase(getHost+"/"))
			{
				driver.switchTo().window(tabs.get(i));
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(getHost+"/");
			}
			else
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.open()");
				driver.switchTo().newWindow(WindowType.TAB);
				OpenWebsite.openSite(driver);
			}
		}
		
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0, 500)", "");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
		List<WebElement> blogs = driver.findElements(By.cssSelector("div[class*='Footer_LatestBlogsRepT']"));
		for(int i = 0; i < blogs.size(); i++)
		{
			if(blogs.get(i).isDisplayed())
			{
				String urlStatus = this.checkURLStatus(blogs.get(i).findElement(By.cssSelector("div[class*='Footer_LatestBlogsRepT'] a")).getAttribute("href"));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				if(urlStatus.equalsIgnoreCase("failed"))
				{
					status.add(data.get(i+1));
				}
				else
				{
					status.add("pass");
				}
				String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
				blogs.get(i).findElement(By.cssSelector(" a")).sendKeys(n);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				String parentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for(String allWindows : windows)
				{
					if(!parentWindow.equalsIgnoreCase(allWindows) && !driver.getCurrentUrl().equalsIgnoreCase(getHost+"/"))
					{
						driver.switchTo().window(allWindows);
						System.out.println(driver.getCurrentUrl());
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
						driver.close();
						driver.switchTo().window(parentWindow);
						((JavascriptExecutor) driver)
						.executeScript("window.scrollTo(0, document.body.scrollHeight)");
						}
					}
				}
			}
		return status;
	}
}

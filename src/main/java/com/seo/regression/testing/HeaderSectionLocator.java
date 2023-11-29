package com.seo.regression.testing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderSectionLocator
{
	WebDriver driver;
	public HeaderSectionLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public String getDriverDetails()
	{
		String driverName =	OpenWebsite.openSite(driver)+"/";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		return driverName;
	}
	public String checkSkillupLogo() throws InterruptedException
	{
		String status = "fail";
		try
		{
			//clickLogo.click();
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				    .withTimeout(Duration.ofSeconds(100))
				    .pollingEvery(Duration.ofSeconds(5))
				    .ignoring(NoSuchElementException.class);
			WebElement clickLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='Header_headerLeft'] a img[alt='logo']")));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			if(clickLogo.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickLogo);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				System.out.println("Skill up logo verified");
				status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		//String getHost = OpenWebsite.setURL;
		
		
		return status;
	}

	public String checkContactUs() throws InterruptedException 
	{
		String status = "fail";
		try
		{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				    .withTimeout(Duration.ofSeconds(50))
				    .pollingEvery(Duration.ofSeconds(5))
				    .ignoring(NoSuchElementException.class);
				WebElement clickContactUs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='Header_headerRight']>ul[class*='Header_navLinks'] li:nth-child(2) a")));
				wait.until(ExpectedConditions.elementToBeClickable(clickContactUs));
		if(clickContactUs.isDisplayed())
		{
			
			String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
			clickContactUs.sendKeys(n);
			String parentWindow = driver.getWindowHandle();
			Set<String> nextWindow = driver.getWindowHandles();
			for(String window : nextWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact/"))
				{
					driver.switchTo().window(window);
					System.out.println("contact window");
					status = "pass";
					driver.close();
					status = "success";
					break;
				}
				else if(driver.getCurrentUrl().contains("data"))
				{
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
			if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
			{
				status = "success";
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return status;
	}

	public String checkBusiness() 
	{
		String status = "fail";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement clickBusiness = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navLinks'] li:nth-child(1) a"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		wait.until(ExpectedConditions.elementToBeClickable(clickBusiness));
		if(clickBusiness.isDisplayed())
		{
			
			String getBusinessURL = clickBusiness.getAttribute("href");
			this.checkURLStatus(getBusinessURL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			clickBusiness.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			String parentWindow = driver.getWindowHandle();
			Set<String> nextWindow = driver.getWindowHandles();
			Iterator<String> iterator = nextWindow.iterator();
			while (iterator.hasNext()) 
			{
				String childWindow = iterator.next();
				driver.switchTo().window(childWindow);
				if(parentWindow.equalsIgnoreCase(childWindow))
				{
					driver.switchTo().window(childWindow);
					if(driver.getCurrentUrl().contains("enterprise"))
					{
						status = "success";
						driver.get(getDriverDetails());
						System.out.println("business page");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
						break;
					}
				}
				else if(!parentWindow.equalsIgnoreCase(childWindow))
				{
					driver.switchTo().window(childWindow);
					if(driver.getCurrentUrl().contains("enterprise"))
					{
						driver.switchTo().window(childWindow);
						System.out.println("business window");
						status = "success";
						driver.close();
						driver.switchTo().window(parentWindow);
					}
					break;
				}
			}
			if(driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
			{
				status = "success";
			}

		}
				return status;
	}

	public String checkBlog() throws InterruptedException 
	{
		String status = "fail";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(Duration.ofSeconds(100))
			    .pollingEvery(Duration.ofSeconds(5))
			    .ignoring(NoSuchElementException.class);
		WebElement clickBlog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navLinks'] li:nth-child(3) a")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		wait.until(ExpectedConditions.elementToBeClickable(clickBlog));
		if(clickBlog.isDisplayed())
		{
			String getBlogURL = clickBlog.getAttribute("href");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			this.checkURLStatus(getBlogURL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			clickBlog.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			String parentWindow = driver.getWindowHandle();
			Set<String> nextWindow = driver.getWindowHandles();
			Iterator<String> iterator = nextWindow.iterator();
			while (iterator.hasNext()) 
			{
				String childWindow = iterator.next();
				if(parentWindow.equalsIgnoreCase(childWindow))
				{
					driver.switchTo().window(childWindow);
					if(driver.getCurrentUrl().contains("blog"))
					{
						driver.switchTo().window(childWindow);
						System.out.println("blog window");
						if(driver.getCurrentUrl().contains("blog"))
						{
							System.out.println("In blog window, url changed as stage to blog");
							status = "success";
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
							driver.get(getDriverDetails());
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
							break;
						}
					}	
				}
				else if(!parentWindow.equalsIgnoreCase(childWindow))
				{
					driver.switchTo().window(childWindow);
					if(driver.getCurrentUrl().contains("blog"))
					{
						driver.switchTo().window(childWindow);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
						System.out.println("blog window");
						status = "success";
						driver.close();
						driver.switchTo().window(parentWindow);
					}
					else if(driver.getCurrentUrl().contains("data"))
					{
						driver.close();
					}
				}
			}
		}
		
		return status;
	
	}

	public ArrayList<String> checkCategories(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		System.out.println("categories validation started");
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				    .withTimeout(Duration.ofSeconds(50))
				    .pollingEvery(Duration.ofSeconds(5))
				    .ignoring(NoSuchElementException.class);
				WebElement clickCourseDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[class='navbar-nav nav '] a#navbarDropdown")));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			if(clickCourseDropdown.isDisplayed())
			{
				
				clickCourseDropdown.click();
				//Thread.sleep(4000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			/*
			 * if(clickCourseDropdown.getAttribute("aria-expanded").equalsIgnoreCase("false"
			 * )) { System.out.println("drop down tryinh to click again");
			 * clickCourseDropdown.click(); Thread.sleep(3000); }
			 */
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			List<WebElement> selectCourse = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='MainCatE catcolumn divbox1']>ul[class='categorylist customscroll dropdown-submenu']>li"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			for(int i = 0; i < selectCourse.size(); i++)
			{
				if(i>0)
				{
					driver.findElement(By.cssSelector("a#navbarDropdown")).click();
					Thread.sleep(3000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					if(driver.findElement(By.cssSelector("a#navbarDropdown")).getAttribute("aria-expanded").equalsIgnoreCase("false"))
					{
						clickCourseDropdown.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					//	Thread.sleep(3000);
					}
				}
				String categoryName = selectCourse.get(i).findElement(By.cssSelector(" a")).getText();
				if(categoryName.equalsIgnoreCase(data.get(i+1)))
				{
					//driver.findElement(By.cssSelector("div[class=' Header_category__mr_e4']")).click();
					String getCatagoriesURL = selectCourse.get(i).findElement(By.cssSelector(" a")).getAttribute("href");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					String urlLinkStatus = this.checkURLStatus(getCatagoriesURL);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
				//	Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					if(urlLinkStatus.equalsIgnoreCase("fail"))
					{
						status.add(selectCourse.get(i).getText());
					}
					else
					{
						status.add("pass");
					}
					String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					selectCourse.get(i).findElement(By.cssSelector(" a")).sendKeys(n);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				//	Thread.sleep(1000);
					String parentWindow = driver.getWindowHandle();
					Set<String> windows = driver.getWindowHandles();
					for(String allWindows : windows)
					{
						driver.switchTo().window(allWindows);
						if(!parentWindow.equalsIgnoreCase(allWindows))
						{
							driver.switchTo().window(allWindows);
							System.out.println(driver.getCurrentUrl());
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
							driver.close();
							driver.switchTo().window(parentWindow);
						}
						else if(driver.getCurrentUrl().contains("data"))
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
							driver.close();
						}
					}
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

	public ArrayList<String> checkPopularCourses(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			System.out.println("popular course validation started");
			if(!driver.getCurrentUrl().equalsIgnoreCase(getDriverDetails()))
			{
			//	driver.close();
				driver.get(getDriverDetails());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			}
			else
			{
				System.out.println("host is present");
			}
			WebElement clickDropdown = driver.findElement(By.cssSelector("a#navbarDropdown"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			clickDropdown.click();
			//Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			if(clickDropdown.getAttribute("aria-expanded").equalsIgnoreCase("false"))
			{
				clickDropdown.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
				//Thread.sleep(3000);
			}
			List<WebElement> popularCourses = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='PolularCourSE catcolumn divbox3'] ul[class='MegaMenu_PopularCourse'] li"));
			for(int i = 0; i < popularCourses.size(); i++)
			{
				if(i>0)
				{
					driver.findElement(By.cssSelector("a#navbarDropdown")).click();
					if(driver.findElement(By.cssSelector("a#navbarDropdown")).getAttribute("aria-expanded").equalsIgnoreCase("false"))
					{
						clickDropdown.click();
					}
					//Thread.sleep(3000);
				}
				String popularCourseName = popularCourses.get(i).findElement(By.cssSelector(" p")).getText();
				if(popularCourseName.equalsIgnoreCase(data.get(i+1)))
				{
					
					String getPopularCourseURL = popularCourses.get(i).findElement(By.cssSelector(" a")).getAttribute("href");
					String urlLinkStatus = this.checkURLStatus(getPopularCourseURL);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					if(urlLinkStatus.equalsIgnoreCase("fail"))
					{
						status.add(popularCourses.get(i).getText());
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					}
					else
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
						status.add("pass");
					}
					 String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					 popularCourses.get(i).findElement(By.cssSelector(" a")).sendKeys(n);
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				//	 Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					 String parentWindow = driver.getWindowHandle();
					 Set<String> windows = driver.getWindowHandles();
					 for(String allWindows : windows)
					 {
						 if(!parentWindow.equalsIgnoreCase(allWindows))
						 {
							 driver.switchTo().window(allWindows);
							 System.out.println(driver.getCurrentUrl());
							 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
							 driver.close();
							 driver.switchTo().window(parentWindow);
						 }
					 }
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

	public String checkLogin()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement clickLogin = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navButtons'] li[class*='Header_loginBtn'] a"));
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navButtons'] li[class*='Header_loginBtn'] a")));
		wait.until(ExpectedConditions.elementToBeClickable(clickLogin));
		String getLoginURL = clickLogin.getAttribute("href");
		this.checkURLStatus(getLoginURL);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", clickLogin);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(parentWindow.equalsIgnoreCase(childWindow))
			{
				if(driver.getCurrentUrl().contains("login"))
				{
					System.out.println("login window");
					OpenWebsite.openSite(driver);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
				}	
				break;
			}
			else if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("login"))
				{
					driver.switchTo().window(childWindow);
					System.out.println("login window");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					driver.close();
					driver.switchTo().window(parentWindow);
				}
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return driver.getCurrentUrl();
	}

	public String checkSignUP() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement clickSignUp = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navButtons'] li[class*='Header_signupBtn'] a"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(clickSignUp));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		clickSignUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
		//Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		Set<String> nextWindow = driver.getWindowHandles();
		Iterator<String> iterator = nextWindow.iterator();
		while (iterator.hasNext()) 
		{
			String childWindow = iterator.next();
			if(parentWindow.equalsIgnoreCase(childWindow))
			{
				if(driver.getCurrentUrl().contains("register"))
				{
					System.out.println("signup window");
					driver.get(getDriverDetails());
				}	
				break;
			}
			else if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				if(driver.getCurrentUrl().contains("register"))
				{
					driver.switchTo().window(childWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					System.out.println("contact window");
					driver.close();
					driver.switchTo().window(parentWindow);
				}
				break;
			}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return driver.getCurrentUrl();
	}
	
	public String checkURLStatus(String getURL)
	{
		String urlStatus = "fail";
		String addHosturl = getURL;
		HttpURLConnection huc = null;
		int respCode = 200;
		try
		{
			huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
			System.out.println("status code : "+respCode + " " +addHosturl);
			if(respCode > 200)
			{
				System.out.println("broken link"+addHosturl);
				urlStatus = "fail";
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				urlStatus = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			urlStatus = "fail";
		}
		return urlStatus;
	}
	public ArrayList<String> verifyLearningPartner(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			System.out.println("learning partner validation started");
			WebElement clickDropdown = driver.findElement(By.cssSelector("a#navbarDropdown"));
			clickDropdown.click();//Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			if(clickDropdown.getAttribute("aria-expanded").equalsIgnoreCase("false"))
			{
				clickDropdown.click();
				//Thread.sleep(3000);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			List<WebElement> learningPartners = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] li a"));
			for(int i = 0; i < learningPartners.size();i++)
			{
				if(i>0)
				{
					WebElement clickDropdown1 = driver.findElement(By.cssSelector("a#navbarDropdown"));
					clickDropdown1.click();
					//Thread.sleep(3000);
					if(clickDropdown.getAttribute("aria-expanded").equalsIgnoreCase("false"))
					{
						clickDropdown.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					//	Thread.sleep(3000);
					}
				}
				String learningPartnerName = learningPartners.get(i).getAttribute("href");
					if(learningPartnerName.contains(data.get(i+1)))
					{
						String getLearningPartnerURL = learningPartners.get(i).getAttribute("href");
						String urlLinkStatus = this.checkURLStatus(getLearningPartnerURL);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						//Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
						if(urlLinkStatus.equalsIgnoreCase("fail"))
						{
							status.add(data.get(i+1));
						}
						else
						{
							status.add("pass");
						}
						String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
						learningPartners.get(i).sendKeys(n);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					//	Thread.sleep(1000);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						String parentWindow = driver.getWindowHandle();
						Set<String> childWnidow = driver.getWindowHandles();
						for(String windows : childWnidow)
						{
							if(!parentWindow.equalsIgnoreCase(windows))
							{
								driver.switchTo().window(windows);
								System.out.println(driver.getCurrentUrl());
								driver.close();
								driver.switchTo().window(parentWindow);
								break;
							}
						}
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

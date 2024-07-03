package com.palm.regressionTesting;

import java.net.HttpURLConnection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderFeatureLocators 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public HeaderFeatureLocators(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> headerFeatureOnCategory(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		try
		{
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				String url = OpenWebsite.setHost+data;
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				if(driver.getCurrentUrl().contains(url))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					
					List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
					if(checkMegaMenuDropDown.size()>0)
					{
						System.out.println("mega menu field is present");
						
						WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
						
						wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
						js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
						
						if(openmegaMenu.isDisplayed())
						{
							js.executeScript("arguments[0].click()", openmegaMenu);
							
							List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
							if(category.size()>0)
							{
								System.out.println("category present");
							}
							else
							{
								datastatus.add("category not present");
							}
							List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
							if(partners.size()>0)
							{
								System.out.println("partners present");
							}
							else
							{
								datastatus.add("partners not present");
							}
							List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
							if(popularCourses.size()>0)
							{
								System.out.println("popularCourses present");
							}
							else
							{
								datastatus.add("popularCourses not present");
							}
							List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
							if(services.size()>0)
							{
								System.out.println("services present");
							}
							else
							{
								datastatus.add("services not present");
							}
							openmegaMenu.click();
						}
					}
					else
					{
						datastatus.add("megaMenu not present");
					}
					
					List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
					if(checkSearchBox.size()>0)
					{
						System.out.println("SearchBox drop down is present");
					}
					else
					{
						datastatus.add("SearchBox not present");
					}
					
					List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
					if(checkAboutSkillup.size()>0)
					{
						System.out.println("About skillup online icon is present");
					}
					else
					{
						datastatus.add("About skillup online icon not present");
					}
					
					if(driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a")).size()>0)
					{
					
						WebElement checkContactUs = driver.findElement(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs));
						js.executeScript("arguments[0].scrollIntoView();", checkContactUs);
						System.out.println("Contact Us icon is present");
					}
						else
						{
							datastatus.add("Contact Us icon not present");
					}
					if(driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a")).size()>0)
					{
					WebElement checkBlog = driver.findElement(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog));
					js.executeScript("arguments[0].scrollIntoView();", checkBlog);
					
						System.out.println("Blog icon is present");
					}
					else
					{
						datastatus.add("Blog icon not present");
					}
					
					if(driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a")).size()>0)
					{
						WebElement checkLogin = driver.findElement(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
						js.executeScript("arguments[0].scrollIntoView();", checkLogin);
						System.out.println("Login icon is present");
					}
					else
					{
						datastatus.add("Login icon not present");
					}
					if(driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a")).size()>0)
					{
					WebElement checkSignUp = driver.findElement(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp));
					js.executeScript("arguments[0].scrollIntoView();", checkSignUp);
						System.out.println("Sign up icon is present");
					}
					else
					{
						datastatus.add("Sign up icon not present");
					}
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
				driver.switchTo().window(parentWindow);
				
			}
			driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return datastatus;
	}
	public ArrayList<String> headerFeatureOncourse(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			try
			{
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					String url = OpenWebsite.setHost+data;
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(url);
					if(driver.getCurrentUrl().contains(url))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						
						List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
						if(checkMegaMenuDropDown.size()>0)
						{
							System.out.println("mega menu field is present");
							
							WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
							
							wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
							js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
							
							if(openmegaMenu.isDisplayed())
							{
								js.executeScript("arguments[0].click()", openmegaMenu);
								
								List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
								if(category.size()>0)
								{
									System.out.println("category present");
								}
								else
								{
									datastatus.add("category not present");
								}
								List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
								if(partners.size()>0)
								{
									System.out.println("partners present");
								}
								else
								{
									datastatus.add("partners not present");
								}
								List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
								if(popularCourses.size()>0)
								{
									System.out.println("popularCourses present");
								}
								else
								{
									datastatus.add("popularCourses not present");
								}
								List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
								if(services.size()>0)
								{
									System.out.println("services present");
								}
								else
								{
									datastatus.add("services not present");
								}
							}
							
						}
						else
						{
							datastatus.add("megaMenu not present");
						}
						
						List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
						if(checkSearchBox.size()>0)
						{
							System.out.println("SearchBox drop down is present");
						}
						else
						{
							datastatus.add("SearchBox not present");
						}
						
						List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
						if(checkAboutSkillup.size()>0)
						{
							System.out.println("About skillup online icon is present");
						}
						else
						{
							datastatus.add("About skillup online icon not present");
						}
						
						List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
						if(checkContactUs.size()>0)
						{
							System.out.println("Contact Us icon is present");
						}
						else
						{
							datastatus.add("Contact Us icon not present");
						}
						
						List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
						if(checkBlog.size()>0)
						{
							System.out.println("Blog icon is present");
						}
						else
						{
							datastatus.add("Blog icon not present");
						}
						
						List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
						if(checkLogin.size()>0)
						{
							System.out.println("Login icon is present");
						}
						else
						{
							datastatus.add("Login icon not present");
						}
						
						List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
						if(checkSignUp.size()>0)
						{
							System.out.println("Sign up icon is present");
						}
						else
						{
							datastatus.add("Sign up icon not present");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
						break;
					}
					driver.switchTo().window(parentWindow);
				}
				driver.switchTo().window(parentWindow);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return datastatus;
	}
	public ArrayList<String> headerFeatureOnprogram(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			try
			{
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					String url = OpenWebsite.setHost+data;
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(url);
					if(driver.getCurrentUrl().contains(url))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						
						List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
						if(checkMegaMenuDropDown.size()>0)
						{
							System.out.println("mega menu field is present");
							
							WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
							
							wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
							js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
							
							if(openmegaMenu.isDisplayed())
							{
								js.executeScript("arguments[0].click()", openmegaMenu);
								
								List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
								if(category.size()>0)
								{
									System.out.println("category present");
								}
								else
								{
									datastatus.add("category not present");
								}
								List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
								if(partners.size()>0)
								{
									System.out.println("partners present");
								}
								else
								{
									datastatus.add("partners not present");
								}
								List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
								if(popularCourses.size()>0)
								{
									System.out.println("popularCourses present");
								}
								else
								{
									datastatus.add("popularCourses not present");
								}
								List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
								if(services.size()>0)
								{
									System.out.println("services present");
								}
								else
								{
									datastatus.add("services not present");
								}
							}
						}
						else
						{
							datastatus.add("megaMenu not present");
						}
						
						List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
						if(checkSearchBox.size()>0)
						{
							System.out.println("SearchBox drop down is present");
						}
						else
						{
							datastatus.add("SearchBox not present");
						}
						
						List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
						if(checkAboutSkillup.size()>0)
						{
							System.out.println("About skillup online icon is present");
						}
						else
						{
							datastatus.add("About skillup online icon not present");
						}
						
						List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
						if(checkContactUs.size()>0)
						{
							System.out.println("Contact Us icon is present");
						}
						else
						{
							datastatus.add("Contact Us icon not present");
						}
						
						List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
						if(checkBlog.size()>0)
						{
							System.out.println("Blog icon is present");
						}
						else
						{
							datastatus.add("Blog icon not present");
						}
						
						List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
						if(checkLogin.size()>0)
						{
							System.out.println("Login icon is present");
						}
						else
						{
							datastatus.add("Login icon not present");
						}
						
						List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
						if(checkSignUp.size()>0)
						{
							System.out.println("Sign up icon is present");
						}
						else
						{
							datastatus.add("Sign up icon not present");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
						break;
					}
					driver.switchTo().window(parentWindow);
				}
				driver.switchTo().window(parentWindow);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return datastatus;
		
			
	}
	public ArrayList<String> headerFeatureOnpartner(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			try
			{
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					String url = OpenWebsite.setHost+data;
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(url);
					if(driver.getCurrentUrl().contains(url))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						
						List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
						if(checkMegaMenuDropDown.size()>0)
						{
							System.out.println("mega menu field is present");
							
							WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
							
							wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
							js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
							
							if(openmegaMenu.isDisplayed())
							{
								js.executeScript("arguments[0].click()", openmegaMenu);
								
								List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
								if(category.size()>0)
								{
									System.out.println("category present");
								}
								else
								{
									datastatus.add("category not present");
								}
								List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
								if(partners.size()>0)
								{
									System.out.println("partners present");
								}
								else
								{
									datastatus.add("partners not present");
								}
								List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
								if(popularCourses.size()>0)
								{
									System.out.println("popularCourses present");
								}
								else
								{
									datastatus.add("popularCourses not present");
								}
								List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
								if(services.size()>0)
								{
									System.out.println("services present");
								}
								else
								{
									datastatus.add("services not present");
								}
							}
						}
						else
						{
							datastatus.add("megaMenu not present");
						}
						
						List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
						if(checkSearchBox.size()>0)
						{
							System.out.println("SearchBox drop down is present");
						}
						else
						{
							datastatus.add("SearchBox not present");
						}
						
						List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
						if(checkAboutSkillup.size()>0)
						{
							System.out.println("About skillup online icon is present");
						}
						else
						{
							datastatus.add("About skillup online icon not present");
						}
						
						List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
						if(checkContactUs.size()>0)
						{
							System.out.println("Contact Us icon is present");
						}
						else
						{
							datastatus.add("Contact Us icon not present");
						}
						
						List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
						if(checkBlog.size()>0)
						{
							System.out.println("Blog icon is present");
						}
						else
						{
							datastatus.add("Blog icon not present");
						}
						
						List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
						if(checkLogin.size()>0)
						{
							System.out.println("Login icon is present");
						}
						else
						{
							datastatus.add("Login icon not present");
						}
						
						List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
						wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
						js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
						if(checkSignUp.size()>0)
						{
							System.out.println("Sign up icon is present");
						}
						else
						{
							datastatus.add("Sign up icon not present");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
						break;
					}
					driver.switchTo().window(parentWindow);
				}
				driver.switchTo().window(parentWindow);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return datastatus;
	}
	
	public ArrayList<String> headerFeatureOnplacement(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				String url = OpenWebsite.setHost+data;
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				if(driver.getCurrentUrl().contains(url))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					
					List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
					if(checkMegaMenuDropDown.size()>0)
					{
						System.out.println("mega menu field is present");
						
						WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
						
						wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
						js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
						
						if(openmegaMenu.isDisplayed())
						{
							js.executeScript("arguments[0].click()", openmegaMenu);
							
							List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
							if(category.size()>0)
							{
								System.out.println("category present");
							}
							else
							{
								datastatus.add("category not present");
							}
							List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
							if(partners.size()>0)
							{
								System.out.println("partners present");
							}
							else
							{
								datastatus.add("partners not present");
							}
							List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
							if(popularCourses.size()>0)
							{
								System.out.println("popularCourses present");
							}
							else
							{
								datastatus.add("popularCourses not present");
							}
							List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
							if(services.size()>0)
							{
								System.out.println("services present");
							}
							else
							{
								datastatus.add("services not present");
							}
						}
					}
					else
					{
						datastatus.add("megaMenu not present");
					}
					
					List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
					if(checkSearchBox.size()>0)
					{
						System.out.println("SearchBox drop down is present");
					}
					else
					{
						datastatus.add("SearchBox not present");
					}
					
					List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
					if(checkAboutSkillup.size()>0)
					{
						System.out.println("About skillup online icon is present");
					}
					else
					{
						datastatus.add("About skillup online icon not present");
					}
					
					List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
					if(checkContactUs.size()>0)
					{
						System.out.println("Contact Us icon is present");
					}
					else
					{
						datastatus.add("Contact Us icon not present");
					}
					
					List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
					if(checkBlog.size()>0)
					{
						System.out.println("Blog icon is present");
					}
					else
					{
						datastatus.add("Blog icon not present");
					}
					
					List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
					if(checkLogin.size()>0)
					{
						System.out.println("Login icon is present");
					}
					else
					{
						datastatus.add("Login icon not present");
					}
					
					List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
					if(checkSignUp.size()>0)
					{
						System.out.println("Sign up icon is present");
					}
					else
					{
						datastatus.add("Sign up icon not present");
					}
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
				driver.switchTo().window(parentWindow);
			}
			driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return datastatus;
	}
	public ArrayList<String> headerFeatureOnLoginPage(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{

			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				String url = OpenWebsite.setHost+data;
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				if(driver.getCurrentUrl().contains(url))
				{
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					
					List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
					if(checkMegaMenuDropDown.size()>0)
					{
						System.out.println("mega menu field is present");
						
						WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
						
						wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
						js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
						
						if(openmegaMenu.isDisplayed())
						{
							js.executeScript("arguments[0].click()", openmegaMenu);
							
							List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
							if(category.size()>0)
							{
								System.out.println("category present");
							}
							else
							{
								datastatus.add("category not present");
							}
							List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
							if(partners.size()>0)
							{
								System.out.println("partners present");
							}
							else
							{
								datastatus.add("partners not present");
							}
							List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
							if(popularCourses.size()>0)
							{
								System.out.println("popularCourses present");
							}
							else
							{
								datastatus.add("popularCourses not present");
							}
							List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
							if(services.size()>0)
							{
								System.out.println("services present");
							}
							else
							{
								datastatus.add("services not present");
							}
						}
					}
					else
					{
						datastatus.add("megaMenu not present");
					}
					
					List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
					if(checkSearchBox.size()>0)
					{
						System.out.println("SearchBox drop down is present");
					}
					else
					{
						datastatus.add("SearchBox not present");
					}
					
					List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
					if(checkAboutSkillup.size()>0)
					{
						System.out.println("About skillup online icon is present");
					}
					else
					{
						datastatus.add("About skillup online icon not present");
					}
					
					List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
					if(checkContactUs.size()>0)
					{
						System.out.println("Contact Us icon is present");
					}
					else
					{
						datastatus.add("Contact Us icon not present");
					}
					
					List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
					if(checkBlog.size()>0)
					{
						System.out.println("Blog icon is present");
					}
					else
					{
						datastatus.add("Blog icon not present");
					}
					
					List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
					if(checkLogin.size()>0)
					{
						System.out.println("Login icon is present");
					}
					else
					{
						datastatus.add("Login icon not present");
					}
					
					List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
					if(checkSignUp.size()>0)
					{
						System.out.println("Sign up icon is present");
					}
					else
					{
						datastatus.add("Sign up icon not present");
					}
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
				driver.switchTo().window(parentWindow);
			}
			driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return datastatus;
	}
	
	public ArrayList<String> headerFeatureOnSignupPage(String data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{

			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				String url = OpenWebsite.setHost+data;
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(url);
				if(driver.getCurrentUrl().contains(url))
				{
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					
					List<WebElement> checkMegaMenuDropDown = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class='navbar-nav nav '] a[id='navbarDropdown']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkMegaMenuDropDown.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkMegaMenuDropDown.get(0));
					if(checkMegaMenuDropDown.size()>0)
					{
						System.out.println("mega menu field is present");
						
						WebElement openmegaMenu = driver.findElement(By.xpath("//ul[@class='navbar-nav nav ']/li/a"));
						
						wait.until(ExpectedConditions.visibilityOfAllElements(openmegaMenu));
						js.executeScript("arguments[0].scrollIntoView();", openmegaMenu);
						
						if(openmegaMenu.isDisplayed())
						{
							js.executeScript("arguments[0].click()", openmegaMenu);
							
							List<WebElement> category = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Categories')]"));
							if(category.size()>0)
							{
								System.out.println("category present");
							}
							else
							{
								datastatus.add("category not present");
							}
							List<WebElement> partners = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Courses by')]"));
							if(partners.size()>0)
							{
								System.out.println("partners present");
							}
							else
							{
								datastatus.add("partners not present");
							}
							List<WebElement> popularCourses = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Popular Courses')]"));
							if(popularCourses.size()>0)
							{
								System.out.println("popularCourses present");
							}
							else
							{
								datastatus.add("popularCourses not present");
							}
							List<WebElement> services = driver.findElements(By.xpath("//ul[@class='navbar-nav nav ']//h2[contains(text(),'Services')]"));
							if(services.size()>0)
							{
								System.out.println("services present");
							}
							else
							{
								datastatus.add("services not present");
							}
						}
					}
					else
					{
						datastatus.add("megaMenu not present");
					}
					
					List<WebElement> checkSearchBox = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='nav navbar-nav Header_headSearch'] form[id='searchForm']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSearchBox.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSearchBox.get(0));
					if(checkSearchBox.size()>0)
					{
						System.out.println("SearchBox drop down is present");
					}
					else
					{
						datastatus.add("SearchBox not present");
					}
					
					List<WebElement> checkAboutSkillup = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(1)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkAboutSkillup.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkAboutSkillup.get(0));
					if(checkAboutSkillup.size()>0)
					{
						System.out.println("About skillup online icon is present");
					}
					else
					{
						datastatus.add("About skillup online icon not present");
					}
					
					List<WebElement> checkContactUs = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkContactUs.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkContactUs.get(0));
					if(checkContactUs.size()>0)
					{
						System.out.println("Contact Us icon is present");
					}
					else
					{
						datastatus.add("Contact Us icon not present");
					}
					
					List<WebElement> checkBlog = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navLinks']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkBlog.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkBlog.get(0));
					if(checkBlog.size()>0)
					{
						System.out.println("Blog icon is present");
					}
					else
					{
						datastatus.add("Blog icon not present");
					}
					
					List<WebElement> checkLogin = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(2)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkLogin.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkLogin.get(0));
					if(checkLogin.size()>0)
					{
						System.out.println("Login icon is present");
					}
					else
					{
						datastatus.add("Login icon not present");
					}
					
					List<WebElement> checkSignUp = driver.findElements(By.cssSelector("div[class*='Header_headerRight']>ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li:nth-child(3)>a"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkSignUp.get(0)));
					js.executeScript("arguments[0].scrollIntoView();", checkSignUp.get(0));
					if(checkSignUp.size()>0)
					{
						System.out.println("Sign up icon is present");
					}
					else
					{
						datastatus.add("Sign up icon not present");
					}
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
				}
				driver.switchTo().window(parentWindow);
			}
			driver.switchTo().window(parentWindow);
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return datastatus;
	}
}

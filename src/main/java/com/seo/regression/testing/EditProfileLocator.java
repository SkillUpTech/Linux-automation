package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditProfileLocator
{
	WebDriver driver;
	
	public EditProfileLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> checkLogin(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement clickLogin = driver.findElement(By.cssSelector("li[class*='Header_loginBtn'] a"));
			js.executeScript("arguments[0].scrollIntoView();", clickLogin);
			if(clickLogin.isDisplayed())
			{
				try
				{
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[class*='Header_loginBtn'] a")));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
		        } 
				catch (StaleElementReferenceException e)
				{
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[class*='Header_loginBtn'] a")));
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			String loginURL = clickLogin.getAttribute("href");
			
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(loginURL);
			
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					WebElement uname = driver.findElement(By.cssSelector("input#email"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#email")));
					js.executeScript("arguments[0].scrollIntoView();", uname);
					uname.sendKeys(data.get(1));
					WebElement pwd = driver.findElement(By.cssSelector("input#password"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#password")));
					js.executeScript("arguments[0].scrollIntoView();", pwd);
					pwd.sendKeys(data.get(2));
					WebElement submit = driver.findElement(By.cssSelector("input#login_in"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#login_in")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						try
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#login_in")));
							js.executeScript("arguments[0].click()", submit);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);Thread.sleep(1000);
				        } 
						catch (StaleElementReferenceException e)
						{
							submit = wait.until(ExpectedConditions.elementToBeClickable(submit));
							submit.click(); 
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						}
						status.add("pass");
					}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(150));
						Set<String> allWindows1 = driver.getWindowHandles();
						for(String window1 : allWindows1)
						{
							driver.switchTo().window(window1);
							
							if(driver.getCurrentUrl().contains("dashboard"))
							{
								driver.switchTo().window(window1);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
								status.add("pass");
								System.out.println("dashboard page");
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(150));
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
			status.add("fail");
			System.out.println("issue in login page");
		}
		return status;
	}
	public String checkProfile()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String dataFromExcel = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
					WebElement clickDropDown = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navButtons']>li:nth-child(2) img[alt='icon']"));
					js.executeScript("arguments[0].scrollIntoView();", clickDropDown);
					if(clickDropDown.isDisplayed())
					{
						try
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Header_headerRight'] ul[class*='Header_navButtons']>li:nth-child(2) img[alt='icon']")));
							js.executeScript("arguments[0].click()", clickDropDown);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickDropDown = wait.until(ExpectedConditions.elementToBeClickable(clickDropDown));
							clickDropDown.click(); 
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						}
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(150));
					
					WebElement clickProfile = driver.findElement(By.cssSelector("ul[class*='dropdown-menu']>li:nth-child(3)>a"));
					js.executeScript("arguments[0].scrollIntoView();", clickProfile);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					if(clickProfile.isDisplayed())
					{
						try
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class*='dropdown-menu']>li:nth-child(3)>a")));
							js.executeScript("arguments[0].click()", clickProfile);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickProfile = wait.until(ExpectedConditions.elementToBeClickable(clickProfile));
							clickProfile.click(); 
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						}
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(150));
					Set<String> allWindows = driver.getWindowHandles();
					for(String window : allWindows)
					{
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("/u/"))
						{
							driver.switchTo().window(window);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							System.out.println("profile page");
							dataFromExcel = "pass";
							System.out.println("profile page navigated ");
							break;
						}
						
					}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dataFromExcel = "fail";
			System.out.println("issue in profile page ");
		}
		
		return dataFromExcel;
	}
	public String checkUpdateIcon() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class='ProfileContent_main'] div[class='EditUpdateButton'] a button"));
				js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
				if(clickUpdateFromContacts.isDisplayed())
				{
					try
					{
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='ProfileContent_main'] div[class='EditUpdateButton'] a button")));
						js.executeScript("arguments[0].click()", clickUpdateFromContacts);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
						clickUpdateFromContacts.click(); 
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					}
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				
				Set<String> allWindows1 = driver.getWindowHandles();
				for(String window1 : allWindows1)
				{
					driver.switchTo().window(window1);
					if(driver.getCurrentUrl().contains("update/"))
					{
						driver.switchTo().window(window1);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						System.out.println("update page");
						status = "pass";
						System.out.println("contact update page process done ");
						break;
						
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkSubmitWithoutDataForMobile() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				jse1.executeScript("window.scrollBy(0, 100)","");
				
				WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("button#update_profile"));
				js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				if(clickUpdateFromContacts.isDisplayed())
				{
					try
					{
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button#update_profile")));
						js.executeScript("arguments[0].click()", clickUpdateFromContacts);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
						clickUpdateFromContacts.click(); 
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					}
				}
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div#mobileErr")));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(driver.findElements(By.cssSelector("div#mobileErr")).size()>0)
				{
					System.out.println("error shown for mobile number");
					status = "pass";
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					System.out.println("contact without data process done");
				}
				else
				{
					status = "fail";
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkSubmitInvalidDataForMobile(String data) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				
				WebElement enterMbl = driver.findElement(By.cssSelector("input#mobile_number"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#mobile_number")));
				js.executeScript("arguments[0].scrollIntoView();", enterMbl);
				if(enterMbl.isDisplayed())
				{
					enterMbl.sendKeys(data);
				}
				
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				JavascriptExecutor jse1 = (JavascriptExecutor) driver;
				jse1.executeScript("window.scrollBy(0, 100)","");
				
				WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("button#update_profile"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button#update_profile")));
				js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
				if(clickUpdateFromContacts.isDisplayed())
				{
					try
					{
						js.executeScript("arguments[0].click()", clickUpdateFromContacts);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
						clickUpdateFromContacts.click(); 
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					}
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				
				if(driver.findElements(By.cssSelector("div#mobileErr")).size()>0)
				{
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					System.out.println("error shown for mobile number");
					status = "pass";
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					System.out.println("contact with invalid data process done");
				}
				else
				{
					status = "fail";
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkCancelIcon() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				
				WebElement clickCancel = driver.findElement(By.cssSelector("p[class='CancelButton']"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("p[class='CancelButton']")));
				js.executeScript("arguments[0].scrollIntoView();", clickCancel);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				if(clickCancel.isDisplayed())
				{
					try
					{
						js.executeScript("arguments[0].click()", clickCancel);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
						clickCancel.click(); 
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					}
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				
				Set<String> a = driver.getWindowHandles();
				for(String win : a)
				{
					driver.switchTo().window(win);
					if(driver.getCurrentUrl().contains("/update/"))
					{
						
						driver.switchTo().window(win);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						WebElement getTextFromAlert = driver.findElement(By.cssSelector("div[class='modal-body']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='modal-body']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						System.out.println(getTextFromAlert.getText());
						status = "pass";
						System.out.println("cancel icon process done for contact");
						break;
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkContactsAlertClose()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			WebElement closeAlert = driver.findElement(By.cssSelector("button[class='close']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='close']")));
			js.executeScript("arguments[0].scrollIntoView();", closeAlert);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
			if(closeAlert.isDisplayed())
			{
				try
				{
					js.executeScript("arguments[0].click()", closeAlert);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					status = "pass";
				} 
				catch (StaleElementReferenceException e)
				{
					closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
					closeAlert.click(); 
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					status = "pass";
					System.out.println("contact alert close process done");
				}
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkSubmitValidDataForMobile(String data) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					
					WebElement enterMobileNum = driver.findElement(By.cssSelector("input#mobile_number"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#mobile_number")));
					js.executeScript("arguments[0].scrollIntoView();", enterMobileNum);
					enterMobileNum.clear();
					enterMobileNum.sendKeys(data);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					
					WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("button#update_profile"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button#update_profile")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					if(clickUpdateFromContacts.isDisplayed())
					{
						try
						{
							js.executeScript("arguments[0].click()", clickUpdateFromContacts);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
							clickUpdateFromContacts.click();
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
						}
					}
					
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					
					Set<String> allWindow = driver.getWindowHandles();
					for(String window : allWindow)
					{
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("/u/"))
						{
							driver.switchTo().window(window);
							
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							WebElement mblNum = driver.findElement(By.cssSelector("div[class='UserProfilemain'] div[class='userProfileDetails'] p:nth-child(3)"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='UserProfilemain'] div[class='userProfileDetails'] p:nth-child(3)")));
							js.executeScript("arguments[0].scrollIntoView();", mblNum);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
							String modifiedData = mblNum.getText();
							if(modifiedData.replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data))
							{
								System.out.println("mbl number updated correctly");	
								Thread.sleep(1000);
								status = "pass";
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
								Thread.sleep(1000);
								System.out.println("contact with valid process done");
								break;
							}
						}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkAlertYesButton() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		try
		{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				js.executeScript("window.scrollBy(0, -100)","");
				
				WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class='ProfileContent_main'] div[class='EditUpdateButton'] a button"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='ProfileContent_main'] div[class='EditUpdateButton'] a button")));
				js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
				if(clickUpdateFromContacts.isDisplayed())
				{
					Thread.sleep(1000);
					try
					{
						js.executeScript("arguments[0].click()", clickUpdateFromContacts);
						Thread.sleep(1000);
			        }
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
						clickUpdateFromContacts.click(); 
					}
					Thread.sleep(1000);
				}
				
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				
				Set<String> allWindows1 = driver.getWindowHandles();
				for(String window1 : allWindows1)
				{
					driver.switchTo().window(window1);
					
					if(driver.getCurrentUrl().contains("/update/"))
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						js.executeScript("window.scrollBy(0, 100)","");
						
						WebElement clickCancel = driver.findElement(By.cssSelector("p[class='CancelButton']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("p[class='CancelButton']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						if(clickCancel.isDisplayed())
						{
							Thread.sleep(1000);
							try
							{
								js.executeScript("arguments[0].click()", clickCancel);
								Thread.sleep(1000);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
								clickCancel.click(); 
							}
							Thread.sleep(1000);
						}
						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						
						WebElement clickYesFromAlert = driver.findElement(By.cssSelector("button[class='btn updateButton']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn updateButton']")));
						js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
						if(clickYesFromAlert.isDisplayed())
						{
							Thread.sleep(1000);
							try
							{
								js.executeScript("arguments[0].click()", clickYesFromAlert);
								Thread.sleep(1000);
								status = "pass";
					        } 
							catch (StaleElementReferenceException e)
							{
								clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
								clickYesFromAlert.click(); 
								status = "pass";
								System.out.println("alert yes process done for contact");
								break;
							}
							Thread.sleep(1000);
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkAlertGoBackButton()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			jse1.executeScript("window.scrollBy(0, 100)","");
			
			WebElement clickCancel = driver.findElement(By.cssSelector("p[class='CancelButton']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("p[class='CancelButton']")));
			js.executeScript("arguments[0].scrollIntoView();", clickCancel);
			if(clickCancel.isDisplayed())
			{
				Thread.sleep(1000);
				try
				{
					js.executeScript("arguments[0].click()", clickCancel);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
					clickCancel.click(); 
				}
				Thread.sleep(1000);
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			WebElement clickGoBackFromAlert = driver.findElement(By.cssSelector("a[class='btn cancelButton']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("a[class='btn cancelButton']")));
			js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
			if(clickGoBackFromAlert.isDisplayed())
			{
				Thread.sleep(1000);
				try
				{
					js.executeScript("arguments[0].click()", clickGoBackFromAlert);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickGoBackFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickGoBackFromAlert));
					clickGoBackFromAlert.click(); 
				}
				Thread.sleep(1000);
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					System.out.println("Profile page");
					status = "pass";
					System.out.println("alert go back process done for conatct");
					Thread.sleep(1000);
					break;
				}
	     }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public String checkAreasOfInterestUpdateIcon()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		try
		{
			js.executeScript("window.scrollBy(0, 100)","");
			WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a")));
			js.executeScript("arguments[0].scrollIntoView();", updateIcon);
			if(updateIcon.isDisplayed())
			{
				Thread.sleep(1000);
				try
				{
					js.executeScript("arguments[0].click()", updateIcon);
		        } 
				catch (StaleElementReferenceException e)
				{
					updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
					updateIcon.click(); 
				}
				Thread.sleep(1000);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			Set<String> allWindows1 = driver.getWindowHandles();
			for(String window1 : allWindows1)
			{
				driver.switchTo().window(window1);
				if(driver.getCurrentUrl().contains("interestedUpdate/"))
				{
					driver.switchTo().window(window1);
					Thread.sleep(1000);
					System.out.println("update page");
					status = "pass";
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		System.out.println("AreasOfInterestUpdateIcon process done");
		return status;
	}
	public String checkAreasOfInterestCancelIcon()
	{
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		try
		{
			js.executeScript("window.scrollBy(0, 600)","");
			WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button")));
			js.executeScript("arguments[0].scrollIntoView();", clickCancel);
			if(clickCancel.isDisplayed())
			{
				Thread.sleep(1000);
				try
				{
					js.executeScript("arguments[0].click()", clickCancel);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
					clickCancel.click(); 
					Thread.sleep(1000);
				}
			}
				
			WebElement getTextFromAlert = driver.findElement(By.cssSelector("div[class='modelPopup_popupmain__Rs7vT'] div[class='modelPopup_popupTop__yPF_N'] p"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='modelPopup_popupmain__Rs7vT'] div[class='modelPopup_popupTop__yPF_N'] p")));
			js.executeScript("arguments[0].scrollIntoView();", getTextFromAlert);
			System.out.println(getTextFromAlert.getText());
			System.out.println("Alert from Area of interest");
			status = "pass";
			System.out.println("AreasOfInterestCancelIcon process done");
		}
		catch(Exception e)
		{
			status = "fail";
		}
		return status;
	}
	public String checkAreasOfInterestAlertClose()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			WebElement closeAlert = driver.findElement(By.cssSelector("button[class='btn-close']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn-close']")));
			js.executeScript("arguments[0].scrollIntoView();", closeAlert);
			if(closeAlert.isDisplayed())
			{
				try
				{
					js.executeScript("arguments[0].click()", closeAlert);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					System.out.println(driver.getCurrentUrl());
					status = "pass";
				}
				catch(StaleElementReferenceException e)
				{
					
					closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
					closeAlert.click(); 
					System.out.println(driver.getCurrentUrl());
					status = "pass";
					Thread.sleep(1000);
				}
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public ArrayList<String> checkAreasOfInterestSubmitValidData(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, -200)","");
			List<WebElement> selectInterestedTopics = driver.findElements(By.cssSelector("div[class='Interested_navmenuDiv__5amle']>ul>li>input"));
			for(int i = 0; i < selectInterestedTopics.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", selectInterestedTopics.get(i));
				String getDataFromBrowser = selectInterestedTopics.get(i).getAttribute("id");
				Thread.sleep(1000);
				for(int k = 0 ; k < data.size(); k++)
				{
					String getDataFromExcel = data.get(k);
					Thread.sleep(1000);
					if(getDataFromBrowser.equalsIgnoreCase(getDataFromExcel))
					{
						js.executeScript("arguments[0].click()", selectInterestedTopics.get(i));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						System.out.println(selectInterestedTopics.get(i)+" is selected");
						if(k == data.size()-1)
						{
							break;
						}
					}
				}
			}
			Thread.sleep(1000);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			js.executeScript("window.scrollBy(0, 400)","");
			Thread.sleep(1000);
			
			WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] button[type='submit']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] button[type='submit']")));
			js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
			if(clickUpdateFromContacts.isDisplayed())
			{
				try
				{
					js.executeScript("arguments[0].click()", clickUpdateFromContacts);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				}
				catch(StaleElementReferenceException e)
				{
					
					clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
					clickUpdateFromContacts.click(); 
				}
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					js.executeScript("window.scrollBy(0, 200)","");
					List<WebElement> selectInterestedTopicsFromHome = driver.findElements(By.cssSelector("div[class='profileheadLeft']>div:nth-child(2) div[class='ProfileUserDetails']>ul>li>a"));
					for(int j = 0; j < selectInterestedTopicsFromHome.size(); j++)
					{
						for(int k = 0; k < data.size(); k++)
						{
							if(selectInterestedTopicsFromHome.get(j).getText().replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data.get(k).replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim()))
							{
								Thread.sleep(1000);
								status.add(data.get(k));
								Thread.sleep(1000);
							}
						}
					}
				}
			}
		
		}
		catch(Exception e)
		{
			status.add("fail");
		}
		System.out.println("AreasOfInterestSubmitValidData process done");
		return status;
	}
	public String checkAreasOfInterestAlertGoBackButton()
	{
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		try
		{
			WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a")));

			js.executeScript("arguments[0].scrollIntoView();", updateIcon);
			if(updateIcon.isDisplayed())
			{
				try
				{
					js.executeScript("arguments[0].click()", updateIcon);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					Thread.sleep(1000);
				}
				catch(StaleElementReferenceException e)
				{
					
					updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
					updateIcon.click(); 
					Thread.sleep(1000);
				}
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("interestedUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				
					js.executeScript("window.scrollBy(0, 100)","");
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						try
						{
							js.executeScript("arguments[0].click()", clickCancel);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							Thread.sleep(1000);
						}
						catch(StaleElementReferenceException e)
						{
							
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
							Thread.sleep(1000);
						}
					}

					WebElement clickGoBackFromAlert = driver.findElement(By.cssSelector("div[class*='modelPopup_popupBottom'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='modelPopup_popupBottom'] a")));
					js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
					if(clickGoBackFromAlert.isDisplayed())
					{
						try
						{
							js.executeScript("arguments[0].click()", clickGoBackFromAlert);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							Thread.sleep(1000);
						}
						catch(StaleElementReferenceException e)
						{
							
							clickGoBackFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickGoBackFromAlert));
							clickGoBackFromAlert.click(); 
						}
					}
					
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("/u/"))
						{
							driver.switchTo().window(window);
							Thread.sleep(1000);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							System.out.println("Profile page");
							status = "pass";
							Thread.sleep(1000);
							driver.switchTo().window(window);
							System.out.println("AreasOfInterest_Alert_Back Button process done");
							Thread.sleep(1000);
							break;
						}
					}
				}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	}
	public String checkAreasOfInterestAlertyesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor jse1 = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		String status = "";
		try
		{
					
			jse1.executeScript("window.scrollBy(0, -100)","");
			
			WebElement clickUpdateFromAreaOfInterest = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(2) div[class='EditUpdate'] a")));
			jse1.executeScript("arguments[0].scrollIntoView();", clickUpdateFromAreaOfInterest);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if(clickUpdateFromAreaOfInterest.isDisplayed())
			{
				Thread.sleep(1000);
				try
				{
					js.executeScript("arguments[0].click()", clickUpdateFromAreaOfInterest);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickUpdateFromAreaOfInterest = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromAreaOfInterest));
					clickUpdateFromAreaOfInterest.click(); 
				}
				Thread.sleep(1000);
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Set<String> allWindows1 = driver.getWindowHandles();
			for(String window1 : allWindows1)
			{
				driver.switchTo().window(window1);
				if(driver.getCurrentUrl().contains("interestedUpdate"))
				{
					driver.switchTo().window(window1);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					jse1.executeScript("window.scrollBy(0, 100)","");
					Thread.sleep(1000);
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3']>div[class='col-md-12']:nth-child(2) div[class*='Interested_buttonBottom'] div[class='Interested_skipButonDesk__sc5lk']>button")));
					jse1.executeScript("arguments[0].scrollIntoView();", clickCancel);
					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						js2.executeScript("arguments[0].click()", clickCancel);
						Thread.sleep(1000);
					}
					WebElement clickYesFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a")));
					js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
					if(clickYesFromAlert.isDisplayed())
					{
						Thread.sleep(1000);
						js2.executeScript("arguments[0].click()", clickYesFromAlert);
						Thread.sleep(1000);
						break;
					}
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window1 : allWindows)
			{
				driver.switchTo().window(window1);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					status = "pass";
					Thread.sleep(1000);
					System.out.println("AreasOfInterest_Alert_yes Button process done");
					Thread.sleep(1000);
					break;
				}
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	
	}
	public String checkCurrentWorkUpdateIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");

		String status = "";
		try
		{
			WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a")));
			js.executeScript("arguments[0].scrollIntoView();", updateIcon);
			if(updateIcon.isDisplayed())
			{
				try 
				{
					js.executeScript("arguments[0].click()", updateIcon);
					Thread.sleep(1000);
		        } 
				catch (StaleElementReferenceException e)
				{
		        	updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
		        	updateIcon.click(); // Attempt to click again
		        	
		        }
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			Set<String> allWindows1 = driver.getWindowHandles();
			for(String window1 : allWindows1)
			{
				driver.switchTo().window(window1);
				if(driver.getCurrentUrl().contains("workstatusUpdate/"))
				{
					driver.switchTo().window(window1);
					Thread.sleep(1000);
					System.out.println("workstatusUpdate page");
					status = "pass";
					System.out.println("current work UpdateIcon process done");
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	
	}
	public String checkCurrentWorkCancelIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 500)","");
			WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']")));
			js.executeScript("arguments[0].scrollIntoView();", clickCancel);
			if(clickCancel.isDisplayed())
			{
				try 
				{
					js.executeScript("arguments[0].click()", clickCancel);
					Thread.sleep(1000);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
					clickCancel.click(); 
					Thread.sleep(1000);
		        }
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			WebElement getTextFromAlert = driver.findElement(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]")));
			if(getTextFromAlert.isDisplayed())
			{
				status = "pass";
				System.out.println(getTextFromAlert.getText());
				Thread.sleep(1000);
				System.out.println("Alert from work experience");
				System.out.println("current work cancelIcon process done");
			}
		}
		catch(Exception e)
		{
			status = "fail";
		}
		return status;
	}
	
	public String checkCurrentWorkAlertClose()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			WebElement closeAlert = driver.findElement(By.cssSelector("button[class='btn-close']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn-close']")));
			js.executeScript("arguments[0].scrollIntoView();", closeAlert);
			if(closeAlert.isDisplayed())
			{
				Thread.sleep(1000);
				try 
				{
					js.executeScript("arguments[0].click()", closeAlert);
					Thread.sleep(1000);
					status = "pass";
					System.out.println("Alert close process done");
					System.out.println("alert close from current work");
		        } 
				catch (StaleElementReferenceException e)
				{
					closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
					closeAlert.click(); 
					status = "pass";
		        }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public ArrayList<String> checkCurrentWorkSubmitValidData(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			js.executeScript("window.scrollBy(0, -200)","");
			Thread.sleep(1000);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			List<WebElement> selectWorkExperience = driver.findElements(By.cssSelector("div[class='Workstatus_currentWork__9e8wr'] ul li input"));
			for(int i = 0; i < selectWorkExperience.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", selectWorkExperience.get(i));
				String getDataFromBrowser = selectWorkExperience.get(i).getAttribute("id");
				for(int k = 0 ; k < data.size(); k++)
				{
					String getDataFromExcel = data.get(k);
					if(getDataFromBrowser.equalsIgnoreCase(getDataFromExcel))
					{
						Thread.sleep(1000);
						js.executeScript("arguments[0].click()", selectWorkExperience.get(i));
						System.out.println("current work data selected");
						Thread.sleep(1000);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						System.out.println(selectWorkExperience.get(i).getText()+" is selected");
						if(k == data.size()-1)
						{
							Thread.sleep(1000);
							break;
						}
					}
				}
		}
				Thread.sleep(1000);
				js.executeScript("window.scrollBy(0, 500)","");
				Thread.sleep(1000);
				WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] button[type='submit']"));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] button[type='submit']")));
				js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
				if(clickUpdateFromContacts.isDisplayed())
				{
					Thread.sleep(1000);
					try 
					{
						js.executeScript("arguments[0].click()", clickUpdateFromContacts);
						System.out.println("updated the data");
						Thread.sleep(1000);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
						clickUpdateFromContacts.click(); 
			        }
				}
				Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					Thread.sleep(1000);
					Set<String> allwindows = driver.getWindowHandles();
					for(String window : allwindows)
					{
						driver.switchTo().window(window);
						Thread.sleep(1000);
						if(driver.getCurrentUrl().contains("/u/"))
						{
							driver.switchTo().window(window);
							Thread.sleep(1000);
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							js.executeScript("window.scrollBy(0, 200)","");
							Thread.sleep(1000);
							List<WebElement> selectInterestedTopicsFromHome = driver.findElements(By.cssSelector("div[class='profileheadLeft'] div[class='ProfileJourny_main']:nth-child(3) div[class='ProfileUserDetails'] ul>li a"));
							for(int j = 0; j < selectInterestedTopicsFromHome.size(); j++)
							{
								Thread.sleep(1000);
								for(int k = 0; k < data.size(); k++)
								{
									Thread.sleep(1000);
									if(selectInterestedTopicsFromHome.get(j).getText().replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data.get(k).replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim()))
									{
										Thread.sleep(1000);
										status.add(data.get(k));
										System.out.println("saving data");
										Thread.sleep(1000);
										System.out.println("current work SubmitValidData process done");
										Thread.sleep(1000);
										System.out.println("submit process done for current work status");
										break;
									}
								}
							}
						}
					}
		
		}
		catch(Exception e)
		{
			status.add("fail");
		}
		return status;
	}
	public String checkCurrentWorkAlertGoBackButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			jse1.executeScript("window.scrollBy(0, 400)","");
			Thread.sleep(1000);
			jse1.executeScript("window.scrollBy(0, -200)","");
			Thread.sleep(1000);
			WebElement clickUpdateFromCurrentWork = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a")));
			js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromCurrentWork);
			if(clickUpdateFromCurrentWork.isDisplayed())
			{
				Thread.sleep(1000);
				try 
				{
					js.executeScript("arguments[0].click()", clickUpdateFromCurrentWork);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickUpdateFromCurrentWork = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromCurrentWork));
					clickUpdateFromCurrentWork.click(); 
		        }
				Thread.sleep(1000);
			}
			Set<String> allWindows2 = driver.getWindowHandles();
			for(String window : allWindows2)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("workstatusUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					jse1.executeScript("window.scrollBy(0, 100)","");
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
							Thread.sleep(1000);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
					}

					WebElement clickGoBackFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button")));
					js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
					if(clickGoBackFromAlert.isDisplayed())
					{
						Thread.sleep(1000);
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickGoBackFromAlert);
							status = "pass";
							break;
				        } 
						catch (StaleElementReferenceException e)
						{
							clickGoBackFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickGoBackFromAlert));
							clickGoBackFromAlert.click(); 
				        }
						Thread.sleep(1000);
						System.out.println("go back  icon clicked from work status page");
						Thread.sleep(1000);
					}
						
				}

			}
			WebElement clickCancel = driver.findElement(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Workstatus_buttonBottom__gah5Y'] div[class='Workstatus_skipButonDesk__2yAQW']>button[class='Workstatus_skipButton__vZu4F']")));
			js.executeScript("arguments[0].scrollIntoView();", clickCancel);
			if(clickCancel.isDisplayed())
			{
				try 
				{
					js.executeScript("arguments[0].click()", clickCancel);
					Thread.sleep(1000);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
					clickCancel.click(); 
		        }
			}
			WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));

			js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
			if(clickYesFromAlert.isDisplayed())
			{
				try 
				{
					js.executeScript("arguments[0].click()", clickYesFromAlert);
					Thread.sleep(1000);
					System.out.println("Current Work process done");
		        } 
				catch (StaleElementReferenceException e)
				{
					clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
					clickYesFromAlert.click(); 
		        }
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	}
	public String checkCurrentWorkAlertYesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					JavascriptExecutor jse1 = (JavascriptExecutor) driver;
					jse1.executeScript("window.scrollBy(0, 400)","");
					Thread.sleep(1000);
					jse1.executeScript("window.scrollBy(0, -200)","");
					Thread.sleep(1000);
					WebElement clickUpdateFromCurrentWork = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(3) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromCurrentWork);
					if(clickUpdateFromCurrentWork.isDisplayed())
					{
						Thread.sleep(1000);
						js.executeScript("arguments[0].click()", clickUpdateFromCurrentWork);
						Thread.sleep(1000);
						break;
					}
				}
			}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
					driver.switchTo().window(window1);
					if(driver.getCurrentUrl().contains("workstatusUpdate"))
					{
						driver.switchTo().window(window1);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 100)","");
						WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Workstatus_skipButonDesk']>button[class*='Workstatus_skipButton']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workstatus_skipButonDesk']>button[class*='Workstatus_skipButton']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						if(clickCancel.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickCancel);
								Thread.sleep(1000);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
								clickCancel.click(); 
					        }
							Thread.sleep(1000);
						}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
							WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));
							js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
							if(clickYesFromAlert.isDisplayed())
							{
								Thread.sleep(1000);
								try 
								{
									js.executeScript("arguments[0].click()", clickYesFromAlert);
									Thread.sleep(1000);
									status = "pass";
									System.out.println("Alert yes from current work process done");
									break;
						        } 
								catch (StaleElementReferenceException e)
								{
									clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
									clickYesFromAlert.click(); 
						        }
								Thread.sleep(1000);
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
						}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	
	}
	public String checkworkExperienceUpdateIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");

		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", updateIcon);
					if(updateIcon.isDisplayed())
					{
						Thread.sleep(1000);
						js.executeScript("arguments[0].click()", updateIcon);
						System.out.println("update icon clicked to land work experience ");
						Thread.sleep(1000);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
						{
							Thread.sleep(1000);
							driver.switchTo().window(window1);
							System.out.println("workstatusUpdate page");
							status = "pass";
							Thread.sleep(1000);
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
			status = "fail";
		}
		System.out.println("work experience UpdateIcon process done");
		return status;
	
	}
	public String checkworkExperienceCancelIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
				{
					driver.switchTo().window(window);
					js.executeScript("window.scrollBy(0, 400)","");
					Thread.sleep(1000);
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Workexperience_skipButonDesk']>button[class*='Workexperience_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_skipButonDesk']>button[class*='Workexperience_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Set<String> a = driver.getWindowHandles();
					for(String win : a)
					{
						driver.switchTo().window(win);
						if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
						{
							driver.switchTo().window(win);
							Thread.sleep(1000);
							WebElement getTextFromAlert = driver.findElement(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]")));
							System.out.println(getTextFromAlert.getText());
							System.out.println("Alert from work experience");
							System.out.println("WorkExperience cancelIcon process done");
						}
					}
					status = "pass";
				}
			}
		}
		catch(Exception e)
		{
			status = "fail";
		}
		return status;
	}
	
	public String checkworkExperienceAlertClose()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("window.scrollBy(0, 100)", "");
					WebElement closeAlert = driver.findElement(By.cssSelector("button[class='btn-close']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn-close']")));
					js.executeScript("arguments[0].scrollIntoView();", closeAlert);
					if(closeAlert.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", closeAlert);
				        } 
						catch (StaleElementReferenceException e)
						{
							closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
							closeAlert.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					status = "pass";
					System.out.println(driver.getCurrentUrl());
				}
			}
			System.out.println("alert close from work experience");
			Thread.sleep(1000);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}
	public ArrayList<String> checkworkExperienceSubmitValidData(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("workexperienceUpdate"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					js.executeScript("window.scrollBy(0, -200)","");
					Thread.sleep(1000);
					List<WebElement> selectWorkExperience = driver.findElements(By.cssSelector("div[class*='Workexperience_currentWork']>ul>li input"));
					for(int i = 0; i < selectWorkExperience.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectWorkExperience.get(i));
						String getDataFromBrowser = selectWorkExperience.get(i).getAttribute("value");
						for(int k = 0 ; k < data.size(); k++)
						{
							String getDataFromExcel = data.get(k);
							if(getDataFromBrowser.equalsIgnoreCase(getDataFromExcel))
							{
								Thread.sleep(1000);
								js.executeScript("arguments[0].click()", selectWorkExperience.get(i));
								Thread.sleep(1000);
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
								Thread.sleep(1000);
								System.out.println(selectWorkExperience.get(i).getText()+" is selected");
								if(k == data.size()-1)
								{
									Thread.sleep(1000);
									break;
								}
							}
						}
					}
					Thread.sleep(1000);
					js.executeScript("window.scrollBy(0, 200)","");
					WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("button[class*='Workexperience_button']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class*='Workexperience_button']")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
					if(clickUpdateFromContacts.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickUpdateFromContacts);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
							clickUpdateFromContacts.click(); 
				        }
						System.out.println("work experience selected");
						Thread.sleep(1000);
					}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("/u/"))
					{
						driver.switchTo().window(window);
						Thread.sleep(1000);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 400)","");
						Thread.sleep(1000);
						List<WebElement> selectInterestedTopicsFromHome = driver.findElements(By.cssSelector("div[class='profileheadLeft'] div[class='ProfileJourny_main']:nth-child(4) div[class='ProfileUserDetails'] ul>li a"));
						for(int j = 0; j < selectInterestedTopicsFromHome.size(); j++)
						{
							for(int k = 0; k < data.size(); k++)
							{
								Thread.sleep(1000);
								if(selectInterestedTopicsFromHome.get(j).getText().replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data.get(k).replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim()))
								{
									System.out.println("work experience updates verified on Profile page");
									Thread.sleep(1000);
									status.add(data.get(k));
									Thread.sleep(1000);
								}
							}
					    }
						System.out.println("work experience SubmitValidData process done");
						Thread.sleep(1000);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						Thread.sleep(1000);
				  }
			}
		
		}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			status.add("fail");
		}
		return status;
	}
	public String checkworkExperienceAlertGoBackButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			if(driver.getCurrentUrl().contains("/u/"))
			{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			WebElement clickUpdateFromHome = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a"));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a")));
			js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromHome);
			if(clickUpdateFromHome.isDisplayed())
			{
				Thread.sleep(1000);
				try 
				{
					js.executeScript("arguments[0].click()", clickUpdateFromHome);
		        } 
				catch (StaleElementReferenceException e)
				{
					clickUpdateFromHome = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromHome));
					clickUpdateFromHome.click(); 
		        }
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					
					js.executeScript("window.scrollBy(0, 100)","");
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}

					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("workexperienceUpdate/"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						WebElement clickGoBackFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button")));
						js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
						if(clickGoBackFromAlert.isDisplayed())
						{
							Thread.sleep(1000);
							js.executeScript("arguments[0].click()", clickGoBackFromAlert);
							status = "pass";
							System.out.println("WorkExperience_Alert_goBackButton process done");
							Thread.sleep(1000);
							WebElement clickCancel1 = driver.findElement(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']")));
							js.executeScript("arguments[0].scrollIntoView();", clickCancel1);
							if(clickCancel1.isDisplayed())
							{
								Thread.sleep(1000);
								try 
								{
									js.executeScript("arguments[0].click()", clickCancel1);
						        } 
								catch (StaleElementReferenceException e)
								{
									clickCancel1 = wait.until(ExpectedConditions.elementToBeClickable(clickCancel1));
									clickCancel1.click(); 
						        }
								Thread.sleep(1000);
							}
							driver.switchTo().window(window);
							if(driver.getCurrentUrl().contains("workexperienceUpdate"))
							{
								driver.switchTo().window(window);
								Thread.sleep(1000);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
								Thread.sleep(1000);
								WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
								wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));
								js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
								if(clickYesFromAlert.isDisplayed())
								{
									Thread.sleep(1000);
									try 
									{
										js.executeScript("arguments[0].click()", clickYesFromAlert);
							        } 
									catch (StaleElementReferenceException e)
									{
										clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
										clickYesFromAlert.click(); 
							        }
									status = "pass";
									Thread.sleep(1000);
								}
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						
						 
					}
					}
				}
			
		}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	}
	public String checkWorkExperienceAlertYesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				Thread.sleep(1000);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					JavascriptExecutor jse1 = (JavascriptExecutor) driver;
					jse1.executeScript("window.scrollBy(0, -100)","");
					
					WebElement clickUpdateFromAreaOfInterest = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(4) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromAreaOfInterest);
					if(clickUpdateFromAreaOfInterest.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickUpdateFromAreaOfInterest);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromAreaOfInterest = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromAreaOfInterest));
							clickUpdateFromAreaOfInterest.click(); 
				        }
						Thread.sleep(1000);
					}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
					driver.switchTo().window(window1);
					if(driver.getCurrentUrl().contains("workexperienceUpdate"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 100)","");
						WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_skipButonDesk'] button[class*='Workexperience_skipButton']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						if(clickCancel.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickCancel);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
								clickCancel.click(); 
					        }
							Thread.sleep(1000);
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("workexperienceUpdate"))
						{
							driver.switchTo().window(window);
							Thread.sleep(1000);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
							WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));
							js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
							if(clickYesFromAlert.isDisplayed())
							{
								Thread.sleep(1000);
								try 
								{
									js.executeScript("arguments[0].click()", clickYesFromAlert);
						        } 
								catch (StaleElementReferenceException e)
								{
									clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
									clickYesFromAlert.click(); 
						        }
								status = "pass";
								Thread.sleep(1000);
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
							if(driver.getCurrentUrl().contains("workexperienceUpdate"))
							{
								Thread.sleep(1000);
								status = "pass";
								Thread.sleep(1000);
								System.out.println("Alert yes from work experience process done");
							}
						}
					}
				}
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	
	}
	public String checkPersonalDetailsUpdateIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");

		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", updateIcon);
					if(updateIcon.isDisplayed())
					{
						js.executeScript("window.scrollBy(0, 100)","");
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", updateIcon);
				        } 
						catch (StaleElementReferenceException e)
						{
							updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
							updateIcon.click(); 
				        }
						System.out.println("update icon clicked from personal Details section");
						Thread.sleep(1000);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
						{
							Thread.sleep(1000);
							driver.switchTo().window(window1);
							System.out.println("personal details update page");
							status = "pass";
							Thread.sleep(1000);
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
			status = "fail";
		}
		System.out.println("personal detail UpdateIcon process done");
		return status;
	
	}
	public String checkPersonalDetailsCancelIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
				{
					driver.switchTo().window(window);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0, 500)","");
					Thread.sleep(1000);
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Set<String> a = driver.getWindowHandles();
					for(String win : a)
					{
						driver.switchTo().window(win);
						if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
						{
							driver.switchTo().window(win);
							Thread.sleep(1000);
							WebElement getTextFromAlert = driver.findElement(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]")));
							js.executeScript("arguments[0].scrollIntoView();", getTextFromAlert);
							System.out.println(getTextFromAlert.getText());
							status = "pass";
							System.out.println("Alert from personal detail");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			status = "fail";
		}
		System.out.println("personal details cancelIcon process done");
		return status;
	}
	public String checkPersonalDetailsAlertClose()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					WebElement closeAlert = driver.findElement(By.cssSelector("button[class='btn-close']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn-close']")));
					js.executeScript("arguments[0].scrollIntoView();", closeAlert);
					if(closeAlert.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", closeAlert);
				        } 
						catch (StaleElementReferenceException e)
						{
							closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
							closeAlert.click(); 
				        }
						status = "pass";
						Thread.sleep(1000);
					}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					System.out.println(driver.getCurrentUrl());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		System.out.println("alert close from personal detail");
		return status;
	}
	public ArrayList<String> checkPersonalDetailsSubmitValidData(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("aboutyouUpdate"))
				{
					driver.switchTo().window(window);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0, -200)","");
					Thread.sleep(1000);
					List<WebElement> selectPersonalDetails = driver.findElements(By.cssSelector("div[class='Aboutyou_currentWork__RD6fb'] ul li input"));
					for(int i = 0; i < selectPersonalDetails.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectPersonalDetails.get(i));
						String getDataFromBrowser = selectPersonalDetails.get(i).getAttribute("value");
						for(int k = 0 ; k < data.size(); k++)
						{
							String getDataFromExcel = data.get(k);
							if(getDataFromBrowser.equalsIgnoreCase(getDataFromExcel))
							{
								Thread.sleep(1000);
								js.executeScript("arguments[0].click()", selectPersonalDetails.get(i));
								if(getDataFromExcel.equalsIgnoreCase("f"))
								{
									Thread.sleep(1000);
									status.add("f");
								}
								if(k == data.size()-1)
								{
									Thread.sleep(1000);
									break;
								}
							}
						}
					}
					Thread.sleep(1000);
					Select select = new Select(driver.findElement(By.cssSelector("select#year_of_birth")));
					select.selectByValue(data.get(2));
					js.executeScript("window.scrollBy(0, 400)","");
					Thread.sleep(1000);
					WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Aboutyou_buttonBottom___Uhqg'] button[type='submit']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='row gy-3'] div[class='col-md-12']:nth-child(2) div[class='Aboutyou_buttonBottom___Uhqg'] button[type='submit']")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromContacts);
					if(clickUpdateFromContacts.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickUpdateFromContacts);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
							clickUpdateFromContacts.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("/u/"))
					{
						driver.switchTo().window(window);
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 600)","");
						Thread.sleep(1000);
						List<WebElement> selectInterestedTopicsFromHome = driver.findElements(By.cssSelector("div[class='profileheadLeft'] div[class='ProfileJourny_main']:nth-child(5) div[class='PersonalDetails'] ul li span"));
						for(int j = 0; j < selectInterestedTopicsFromHome.size(); j++)
						{
							for(int k = 0; k < data.size(); k++)
							{
								if(selectInterestedTopicsFromHome.get(j).getText().replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data.get(k).replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim()))
								{
									Thread.sleep(1000);
									status.add(data.get(k));
									Thread.sleep(1000);
								}
							}
					    }
						System.out.println("personal details SubmitValidData process done");
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						Thread.sleep(1000);
				  }
			}
		
		}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			status.add("fail");
		}
		return status;
	}
	public String checkPersonalDetailsAlertGoBackButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", updateIcon);
					if(updateIcon.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", updateIcon);
				        } 
						catch (StaleElementReferenceException e)
						{
							updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
							updateIcon.click(); 
				        }
						Thread.sleep(1000);
					}
				}
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			Thread.sleep(1000);
			Set<String> allWindows1 = driver.getWindowHandles();
			for(String window : allWindows1)
			{
				driver.switchTo().window(window);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				Thread.sleep(1000);
				if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					JavascriptExecutor jse1 = (JavascriptExecutor) driver;
					jse1.executeScript("window.scrollBy(0, 300)","");
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}

					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("aboutyouUpdate/"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						WebElement clickGoBackFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button")));
						js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
						if(clickGoBackFromAlert.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickGoBackFromAlert);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickGoBackFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickGoBackFromAlert));
								clickGoBackFromAlert.click(); 
					        }
							System.out.println("personal details_Alert_goBackButton process done");
							status = "pass";
							Thread.sleep(1000);
						}
					}
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	}
	public String checkPersonalDetailsAlertYesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					
					js.executeScript("window.scrollBy(0, 500)","");
					Thread.sleep(1000);
					WebElement clickUpdateFromPersonalDetail = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(5) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", clickUpdateFromPersonalDetail);
					if(clickUpdateFromPersonalDetail.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickUpdateFromPersonalDetail);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromPersonalDetail = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromPersonalDetail));
							clickUpdateFromPersonalDetail.click(); 
				        }
						Thread.sleep(1000);
					}
				}
			}
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
					driver.switchTo().window(window1);
					if(driver.getCurrentUrl().contains("aboutyouUpdate"))
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 300)","");
						WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						if(clickCancel.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickCancel);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
								clickCancel.click(); 
					        }
							Thread.sleep(1000);
						}
						driver.switchTo().window(window1);
						if(driver.getCurrentUrl().contains("aboutyouUpdate"))
						{
							driver.switchTo().window(window1);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
							WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));
							js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
							if(clickYesFromAlert.isDisplayed())
							{
								Thread.sleep(1000);
								try 
								{
									js.executeScript("arguments[0].click()", clickYesFromAlert);
						        } 
								catch (StaleElementReferenceException e)
								{
									clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
									clickYesFromAlert.click(); 
						        }
								status = "pass";
								Thread.sleep(1000);
								System.out.println("Alert yes from personal details process done");
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
						}
					}
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	
	}
	
	public String checkEducation_updateIcon()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/aboutyouUpdate/"))
				{
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_skipButonDesk'] button[class*='Aboutyou_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("aboutyouUpdate"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						WebElement clickYesFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a")));
						js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
						if(clickYesFromAlert.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickYesFromAlert);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
								clickYesFromAlert.click(); 
					        }
							status = "pass";
							Thread.sleep(1000);
							System.out.println("Alert yes from personal details process done");
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
					}
				}
				if(driver.getCurrentUrl().contains("/u/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					WebElement updateIcon = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(6) div[class='EditUpdate'] a"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(6) div[class='EditUpdate'] a")));
					js.executeScript("arguments[0].scrollIntoView();", updateIcon);
					Thread.sleep(1000);
					if(updateIcon.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", updateIcon);
				        } 
						catch (StaleElementReferenceException e)
						{
							updateIcon = wait.until(ExpectedConditions.elementToBeClickable(updateIcon));
							updateIcon.click(); 
				        }
						status = "pass";
						Thread.sleep(1000);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						if(driver.getCurrentUrl().contains("educationUpdate/"))
						{
							Thread.sleep(1000);
							driver.switchTo().window(window1);
							System.out.println("education details update page");
							status = "pass";
							Thread.sleep(1000);
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
			status = "fail";
		}
		System.out.println("personal detail UpdateIcon process done");
		return status;
	
	}
	public String checkEducation_cancelIcon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("educationUpdate/"))
				{
					driver.switchTo().window(window);
					
					js.executeScript("window.scrollBy(0, 400)","");
					Thread.sleep(1000);
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Education_skipButonDesk'] button[class*='Education_skipButton']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_skipButonDesk'] button[class*='Education_skipButton']")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					Thread.sleep(1000);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					Set<String> a = driver.getWindowHandles();
					for(String win : a)
					{
						driver.switchTo().window(win);
						if(driver.getCurrentUrl().contains("educationUpdate/"))
						{
							driver.switchTo().window(win);
							Thread.sleep(1000);
							WebElement getTextFromAlert = driver.findElement(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Are you sure to cancel the updates?')]")));
							System.out.println(getTextFromAlert.getText());
							status = "pass";
							System.out.println("Alert from personal detail");
							Thread.sleep(1000);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			status = "fail";
		}
		System.out.println("education details cancelIcon process done");
		return status;
	}
	public String checkEducation_Alert_close()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("educationUpdate/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					WebElement closeAlert = driver.findElement(By.cssSelector("button[class='btn-close']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn-close']")));
					js.executeScript("arguments[0].scrollIntoView();", closeAlert);
					Thread.sleep(1000);
					if(closeAlert.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", closeAlert);
				        } 
						catch (StaleElementReferenceException e)
						{
							closeAlert = wait.until(ExpectedConditions.elementToBeClickable(closeAlert));
							closeAlert.click(); 
				        }
						Thread.sleep(1000);
					}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					Thread.sleep(1000);
					status = "pass";
					System.out.println(driver.getCurrentUrl());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		System.out.println("alert close from education detail");
		return status;
	}
	public ArrayList<String> checkEducation_submitValidData(ArrayList<String> data)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("educationUpdate"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollBy(0, -200)","");
					Thread.sleep(1000);
					Select select = new Select(driver.findElement(By.cssSelector("select#year_of_birth")));
					select.selectByVisibleText(data.get(1));
					Thread.sleep(1000);
					js.executeScript("window.scrollBy(0, 100)","");
					WebElement clickUpdateFromContacts = driver.findElement(By.cssSelector("div[class*='Education_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_buttonBottom']>button")));
					Thread.sleep(1000);
					if(clickUpdateFromContacts.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickUpdateFromContacts);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickUpdateFromContacts = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromContacts));
							clickUpdateFromContacts.click(); 
				        }
						Thread.sleep(1000);
					}
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("/u/"))
					{
						driver.switchTo().window(window);
						Thread.sleep(1000);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 500)","");
						Thread.sleep(1000);
						List<WebElement> selectInterestedTopicsFromHome = driver.findElements(By.cssSelector("div[class='ProfileJourny_main']:nth-child(6) div[class='ProfileUserDetails']>ul>li>a"));
						for(int j = 0; j < selectInterestedTopicsFromHome.size(); j++)
						{
							for(int k = 0; k < data.size(); k++)
							{
								if(!selectInterestedTopicsFromHome.get(j).getText().replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim().equalsIgnoreCase(data.get(k).replaceAll("[^a-zA-Z0-9]", " ").replace(" ","").trim()))
								{
									Thread.sleep(1000);
									status.add(data.get(k));
									Thread.sleep(1000);
									break;
								}
							}
					    }
				  }
			}
		
		}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			status.add("fail");
		}
		System.out.println("education details SubmitValidData process done");
		return status;
	}
	public String checkEducation_Alert_goBackButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/u/"))
				{
					WebElement updateFromProfile = driver.findElement(By.cssSelector("div[class='ProfileJourny_main']:nth-child(6) button[class='btn Updatebtn']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='ProfileJourny_main']:nth-child(6) button[class='btn Updatebtn']")));
					js.executeScript("arguments[0].scrollIntoView();", updateFromProfile);
					if(updateFromProfile.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", updateFromProfile);
				        } 
						catch (StaleElementReferenceException e)
						{
							updateFromProfile = wait.until(ExpectedConditions.elementToBeClickable(updateFromProfile));
							updateFromProfile.click(); 
				        }
						Thread.sleep(1000);
					}
				}
				if(driver.getCurrentUrl().contains("educationUpdate/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					JavascriptExecutor jse1 = (JavascriptExecutor) driver;
					jse1.executeScript("window.scrollBy(0, 300)","");
					Thread.sleep(1000);
					
					Thread.sleep(1000);
					WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Education_buttonBottom']>div[class*='Education_skipButonDesk']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_buttonBottom']>div[class*='Education_skipButonDesk']>button")));
					js.executeScript("arguments[0].scrollIntoView();", clickCancel);
					Thread.sleep(1000);
					if(clickCancel.isDisplayed())
					{
						Thread.sleep(1000);
						try 
						{
							js.executeScript("arguments[0].click()", clickCancel);
				        } 
						catch (StaleElementReferenceException e)
						{
							clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
							clickCancel.click(); 
				        }
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
					Thread.sleep(1000);
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("educationUpdate/"))
					{
						driver.switchTo().window(window);
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						WebElement clickGoBackFromAlert = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]/button")));
						js.executeScript("arguments[0].scrollIntoView();", clickGoBackFromAlert);
						if(clickGoBackFromAlert.isDisplayed())
						{
							Thread.sleep(1000);
							try 
							{
								js.executeScript("arguments[0].click()", clickGoBackFromAlert);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickGoBackFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickGoBackFromAlert));
								clickGoBackFromAlert.click(); 
					        }
							status = "pass";
							Thread.sleep(1000);
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						System.out.println("personal details_Alert_back Button process done");
						WebElement clickCancelButton = driver.findElement(By.cssSelector("div[class*='Education_skipButonDesk'] button[class='Education_skipButton__AN_lA']"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_skipButonDesk'] button[class='Education_skipButton__AN_lA']")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancelButton);
						if(clickCancelButton.isDisplayed())
						{
							try 
							{
								js.executeScript("arguments[0].click()", clickCancelButton);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickCancelButton = wait.until(ExpectedConditions.elementToBeClickable(clickCancelButton));
								clickCancelButton.click(); 
					        }
						}
						WebElement clickContinue = driver.findElement(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'modelPopup_popupBottom')]//a")));
						js.executeScript("arguments[0].scrollIntoView();", clickContinue);
						if(clickContinue.isDisplayed())
						{
							try 
							{
								js.executeScript("arguments[0].click()", clickContinue);
					        } 
							catch (StaleElementReferenceException e)
							{
								clickContinue = wait.until(ExpectedConditions.elementToBeClickable(clickContinue));
								clickContinue.click(); 
					        }
						}
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("/u/"))
						{
							driver.switchTo().window(window);
							WebElement clickDropdown = driver.findElement(By.cssSelector("li[class='SigNUP']>a"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[class='SigNUP']>a")));
							js.executeScript("arguments[0].scrollIntoView();", clickDropdown);
							if(clickDropdown.isDisplayed())
							{
								try 
								{
									js.executeScript("arguments[0].click()", clickDropdown);
						        } 
								catch (StaleElementReferenceException e)
								{
									clickDropdown = wait.until(ExpectedConditions.elementToBeClickable(clickDropdown));
									clickDropdown.click(); 
						        }
								WebElement signout = driver.findElement(By.cssSelector("ul[class='dropdown-menu Primary02_Blue']>li:nth-child(5)>a"));
								wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[class='dropdown-menu Primary02_Blue']>li:nth-child(5)>a")));
								if(signout.isDisplayed())
								{
									try 
									{
										js.executeScript("arguments[0].click()", signout);
							        } 
									catch (StaleElementReferenceException e)
									{
										signout = wait.until(ExpectedConditions.elementToBeClickable(signout));
										signout.click(); 
							        }
								}
							}
						}
								
					}
					}
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	}
	public String checkEducation_Alert_yesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 800)","");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			
			  Set<String> allWindows = driver.getWindowHandles();
			  for(String window : allWindows)
			  {
				  driver.switchTo().window(window);
				  Thread.sleep(1000);
			  if(driver.getCurrentUrl().contains("/u/")) 
			  {
			  driver.switchTo().window(window);
			  Thread.sleep(1000);
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			  Thread.sleep(1000); 
			  WebElement clickUpdateFromEducation = driver.findElement(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(6) div[class='EditUpdate'] a")); 
			  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='profileheadLeft']>div[class='ProfileJourny_main']:nth-child(6) div[class='EditUpdate'] a")));
			  Thread.sleep(1000);
			  if(clickUpdateFromEducation.isDisplayed())
			  {
				  Thread.sleep(1000);
				  js.executeScript("window.scrollBy(0, -100)","");
				  try 
					{
						js.executeScript("arguments[0].click()", clickUpdateFromEducation);
			        } 
					catch (StaleElementReferenceException e)
					{
						clickUpdateFromEducation = wait.until(ExpectedConditions.elementToBeClickable(clickUpdateFromEducation));
						clickUpdateFromEducation.click(); 
			        }
				  Thread.sleep(1000);
			  }
			  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			  Thread.sleep(1000);
			 
					Set<String> allWindows1 = driver.getWindowHandles();
					for(String window1 : allWindows1)
					{
					driver.switchTo().window(window1);
					if(driver.getCurrentUrl().contains("educationUpdate"))
					{
						driver.switchTo().window(window1);
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0, 300)","");
						WebElement clickCancel = driver.findElement(By.cssSelector("div[class*='Education_buttonBottom']>div[class*='Education_skipButonDesk']>button"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_buttonBottom']>div[class*='Education_skipButonDesk']>button")));
						js.executeScript("arguments[0].scrollIntoView();", clickCancel);
						Thread.sleep(1000);
						if(clickCancel.isDisplayed())
						{
							 try 
								{
									js.executeScript("arguments[0].click()", clickCancel);
						        } 
								catch (StaleElementReferenceException e)
								{
									clickCancel = wait.until(ExpectedConditions.elementToBeClickable(clickCancel));
									clickCancel.click(); 
						        }
						}
						driver.switchTo().window(window1);
						if(driver.getCurrentUrl().contains("educationUpdate"))
						{
							driver.switchTo().window(window1);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
							WebElement clickYesFromAlert = driver.findElement(By.xpath("//a[contains(text(),'Yes, continue')]"));
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Yes, continue')]")));
							js.executeScript("arguments[0].scrollIntoView();", clickYesFromAlert);
							Thread.sleep(1000);
							if(clickYesFromAlert.isDisplayed())
							{
								Thread.sleep(1000);
								 try 
									{
										js.executeScript("arguments[0].click()", clickYesFromAlert);
							        } 
									catch (StaleElementReferenceException e)
									{
										clickYesFromAlert = wait.until(ExpectedConditions.elementToBeClickable(clickYesFromAlert));
										clickYesFromAlert.click(); 
							        }
								status = "pass";
								Thread.sleep(1000);
								System.out.println("Alert yes from education details process done");
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
							Thread.sleep(1000);
						}
					}
					}
			  }
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
		
		return status;
	
	}
	
	
}

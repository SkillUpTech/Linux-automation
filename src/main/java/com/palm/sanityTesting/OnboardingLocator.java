package com.palm.sanityTesting;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingLocator
{
WebDriver driver;
	
	public OnboardingLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String checkLogin(String email, String pwd)
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement loginPage = driver.findElement(By.cssSelector("li[class*='Header_loginBtn']>a"));
			String loginURL = loginPage.getAttribute("href");
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(loginURL);
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					
					WebElement enterEmail = driver.findElement(By.cssSelector("input#email"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#email")));
					js.executeScript("arguments[0].scrollIntoView();", enterEmail);
					if(enterEmail.isDisplayed())
					{
						enterEmail.sendKeys(email);
					}
					WebElement enterPwd = driver.findElement(By.cssSelector("input#password"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#password")));
					js.executeScript("arguments[0].scrollIntoView();", enterPwd);
					if(enterEmail.isDisplayed())
					{
						enterPwd.sendKeys(pwd);
					}
					WebElement submit = driver.findElement(By.cssSelector("input#login_in"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input#login_in")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
						status = "pass";
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String checkPersonalPage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/personalized/"))
				{
					driver.switchTo().window(window);
					WebElement checkPage = driver.findElement(By.xpath("//a[contains(text(),'Begin your profile')]"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Begin your profile')]")));
					js.executeScript("arguments[0].scrollIntoView();", checkPage);
					if(checkPage.isDisplayed())
					{
						System.out.println(checkPage.getText());
						WebElement submit = driver.findElement(By.cssSelector("div[class*='Personalized_PersContent']>a"));
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Personalized_PersContent']>a")));
						js.executeScript("arguments[0].scrollIntoView();", submit);
						if(submit.isDisplayed())
						{
							js.executeScript("arguments[0].click()", submit);
						}
						status = "pass";
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
	
	public String checkInterestedPage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/interested/"))
				{
					driver.switchTo().window(window);
					List<WebElement> selectData = driver.findElements(By.cssSelector("div[class='Interested_navmenuDiv__5amle']>ul>li input"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='Interested_navmenuDiv__5amle']>ul>li input")));
					for(int i = 0; i < selectData.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectData.get(i));
						if(selectData.get(i).getAttribute("id").equalsIgnoreCase("Artificial Intelligence"))
						{
							System.out.println(selectData.get(i).getText());
							js.executeScript("arguments[0].click()", selectData.get(i));
							
							status = "pass";
							break;
						}
						
					}
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Interested_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Interested_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
						Thread.sleep(1000);
					}
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
	
	public String checkWorkstatusPage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/workstatus/"))
				{
					driver.switchTo().window(window);
					List<WebElement> selectData = driver.findElements(By.cssSelector("div[class*='Workstatus_currentWork']>ul>li>input"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workstatus_currentWork']>ul>li>input")));
					for(int i = 0; i < selectData.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectData.get(i));
						if(selectData.get(i).getAttribute("id").equalsIgnoreCase("Working full time"))
						{
							System.out.println(selectData.get(i).getText());
							js.executeScript("arguments[0].click()", selectData.get(i));
							
							status = "pass";
							break;
						}
						
					}
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Workstatus_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workstatus_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
					}
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
	public String checkWorkExperience()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/workexperience/"))
				{
					driver.switchTo().window(window);
					List<WebElement> selectData = driver.findElements(By.cssSelector("div[class*='Workexperience_currentWork']>ul>li>input"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_currentWork']>ul>li>input")));
					for(int i = 0; i < selectData.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectData.get(i));
						if(selectData.get(i).getAttribute("value").equalsIgnoreCase("2-5 Years"))
						{
							System.out.println(selectData.get(i).getText());
							js.executeScript("arguments[0].click()", selectData.get(i));
							status = "pass";
							break;
						}
						
					}
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Workexperience_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Workexperience_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
					}
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
	public String checkAboutYouPage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/aboutyou/"))
				{
					driver.switchTo().window(window);
					List<WebElement> selectData = driver.findElements(By.cssSelector("div[class*='Aboutyou_currentWork']>ul>li>input"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_currentWork']>ul>li>input")));
					for(int i = 0; i < selectData.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectData.get(i));
						if(selectData.get(i).getAttribute("value").equalsIgnoreCase("f"))
						{
							System.out.println(selectData.get(i).getText());
							js.executeScript("arguments[0].click()", selectData.get(i));
							
							status = "pass";
							break;
						}
						
					}
					WebElement selectYear = driver.findElement(By.cssSelector("select#year_of_birth"));
					Select dropdown = new Select(selectYear);
					dropdown.selectByVisibleText("1991");
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Aboutyou_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Aboutyou_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
					}
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
	
	public String checkEducationPage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/education/"))
				{
					driver.switchTo().window(window);
					 WebElement dropdownElement = driver.findElement(By.id("year_of_birth"));
			        Select dropdown = new Select(dropdownElement);

			        dropdown.selectByVisibleText("Master's or professional degree");
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Education_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Education_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String checkJobOpportunites()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/jobopportunities/"))
				{
					driver.switchTo().window(window);
					WebElement selectData = driver.findElement(By.cssSelector("div[class*='Jobopportunities_currentWork']>ul>li>input[value='1']"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Jobopportunities_currentWork']>ul>li>input[value='1']")));
					js.executeScript("arguments[0].scrollIntoView();", selectData);
					if(selectData.getAttribute("value").equalsIgnoreCase("1"))
					{
						System.out.println(selectData.getText());
						js.executeScript("arguments[0].click()", selectData);
						status = "pass";
					}
					WebElement submit = driver.findElement(By.cssSelector("div[class*='Jobopportunities_buttonBottom']>button"));
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='Jobopportunities_buttonBottom']>button")));
					js.executeScript("arguments[0].scrollIntoView();", submit);
					if(submit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", submit);
						status = "pass";
					}
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
}

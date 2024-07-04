package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExploreCourseByNewUserLocator
{
	WebDriver driver;
	SignUpLocator signUpLocator;
	
	public ExploreCourseByNewUserLocator(WebDriver driver)
	{
		this.driver = driver;
		this.signUpLocator = new SignUpLocator(this.driver);
	}
	
	public ArrayList<String> checkSignup(ArrayList<String> data)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> statusOfTestCase = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
			{
			
			Set<String> windows = driver.getWindowHandles();
			
			for(String eachWindow : windows)
			{
				driver.switchTo().window(eachWindow);
				
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(eachWindow);
					
					WebElement clickSignup = driver.findElement(By.cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons']>li:nth-child(3)>a"));
					js.executeScript("arguments[0].scrollIntoView();", clickSignup);
					
					if(clickSignup.isDisplayed())
					{
						String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						clickSignup.sendKeys(selectLinkOpeninNewTab);
						break;
					}
				}
			}
						String parent1 = driver.getWindowHandle();
						
						Set<String> windows1 =driver.getWindowHandles();
						
						for(String eachWindow1 : windows1)
						{
							driver.switchTo().window(eachWindow1);
							
							if(driver.getCurrentUrl().contains("register"))
							{
								driver.switchTo().window(eachWindow1);
								
								signUpLocator.signUpFunction(data);
								
								
								if(driver.getCurrentUrl().contains("/verify/"))
								{
									driver.switchTo().window(eachWindow1);
									
									System.out.println("Now we are in Let Get started screen");
									
									driver.navigate().back();
									
									if(driver.getCurrentUrl().contains("dashboard"))
									{
										driver.switchTo().window(eachWindow1);
										
										System.out.println("Dashboard screen");
										
										WebElement clicExploreCourse = driver.findElement(By.xpath("//button[contains(text(),'Explore our catalog!')]"));
										wait.until(ExpectedConditions.visibilityOfAllElements(clicExploreCourse));
										js.executeScript("arguments[0].scrollIntoView();", clicExploreCourse);
										
										try
										{
											driver.findElement(By.xpath("//button[contains(text(),'Explore our catalog!')]"));
										}
										catch(StaleElementReferenceException e)
										{
											driver.findElement(By.xpath("//button[contains(text(),'Explore our catalog!')]"));
										}
										if(clicExploreCourse.isDisplayed())
										{
											js.executeScript("arguments[0].click()", clicExploreCourse);
											
											driver.switchTo().window(eachWindow1);
											
											if(driver.getCurrentUrl().contains("explore-our-catalog"))
											{
												driver.switchTo().window(eachWindow1);
												
												System.out.println("Explore Our catalog page");
												
												driver.close();
												
												driver.switchTo().window(parent1);

												break;
												
											}
											driver.switchTo().window(eachWindow1);
										}
									}
								}
								driver.switchTo().window(parent1);
							}
							driver.switchTo().window(parent1);
						}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfTestCase;
	}
	
	public String CheckExploreCourse()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}

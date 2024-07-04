package com.seo.regression.testing;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlacementPageLocator
{
	
	WebDriver driver;
	
	public PlacementPageLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public String verifyArrangeAChatIcon()
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			List<WebElement> clickPlacementIcon = driver.findElements(By.cssSelector("div[class*='Footer_footertopmenu'] li[class='nav-item ']>a"));
			for(int i = 0; i < clickPlacementIcon.size(); i++)
			{
				wait.until(ExpectedConditions.visibilityOfAllElements(clickPlacementIcon.get(i)));
				js.executeScript("arguments[0].scrollIntoView();", clickPlacementIcon.get(i));
				String url = clickPlacementIcon.get(i).getAttribute("href");
				if(url.contains("placement"))
				{
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(url);
					Set<String> allWindows = driver.getWindowHandles();
					for(String window : allWindows)
					{
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("placement"))
						{
							driver.switchTo().window(window);
							System.out.println("we are in placement page");
							
							WebElement clickFocusForm = driver.findElement(By.xpath("//button[contains(text(),'Arrange a Chat')]"));
							wait.until(ExpectedConditions.visibilityOfAllElements(clickFocusForm));
							js.executeScript("arguments[0].scrollIntoView();", clickFocusForm);
							
							if(clickFocusForm.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickFocusForm);
								WebElement frameElement = driver.findElement(By.cssSelector("iframe[allow='geolocation']"));
								driver.switchTo().frame(frameElement);
								WebElement checkFormTitle = driver.findElement(By.cssSelector("div#live_survey_view h3[name='headerMsg']"));
								wait.until(ExpectedConditions.visibilityOfAllElements(checkFormTitle));
								System.out.println("form title : "+checkFormTitle.getText());
								status = "pass";
								driver.switchTo().defaultContent();
								break;
							}
						}
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
	
	public String verifyConnectWithOurPlacementTeam()
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			WebElement clickButton = driver.findElement(By.xpath("//button[contains(text(),'Connect with our Placement Team')]"));
			wait.until(ExpectedConditions.visibilityOfAllElements(clickButton));
			js.executeScript("arguments[0].scrollIntoView();", clickButton);
			
			if(clickButton.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickButton);
				WebElement frameElement = driver.findElement(By.cssSelector("iframe[allow='geolocation']"));
				driver.switchTo().frame(frameElement);
				WebElement checkFormTitle = driver.findElement(By.cssSelector("h3[name='headerMsg']"));
				wait.until(ExpectedConditions.visibilityOfAllElements(checkFormTitle));
				System.out.println("form title : "+checkFormTitle.getText());
				status = "pass";
				driver.switchTo().defaultContent();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String verifyArrangeAChatWithOurPlacementTeam()
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			List<WebElement> clickButton = driver.findElements(By.xpath("//button[contains(text(),'Arrange a chat with our Placement Team')]"));
			for(int i = 0; i < clickButton.size(); i++)
			{
				wait.until(ExpectedConditions.visibilityOfAllElements(clickButton.get(i)));
				js.executeScript("arguments[0].scrollIntoView();", clickButton.get(i));
				if(clickButton.get(i).isDisplayed())
				{
					js.executeScript("arguments[0].click()", clickButton.get(i));
					WebElement frameElement = driver.findElement(By.cssSelector("iframe[allow='geolocation']"));
					driver.switchTo().frame(frameElement);
					WebElement checkFormTitle = driver.findElement(By.cssSelector("h3[name='headerMsg']"));
					wait.until(ExpectedConditions.visibilityOfAllElements(checkFormTitle));
					System.out.println("form title : "+checkFormTitle.getText());
					status = "pass";
					driver.switchTo().defaultContent();
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> form(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			driver.navigate().refresh();
			
			WebElement frameElement = driver.findElement(By.cssSelector("iframe[allow='geolocation']"));
			driver.switchTo().frame(frameElement);
			
			WebElement firstName = driver.findElement(By.cssSelector("ul[class='choicelist clearfix']>li:nth-child(1) input[class=' inTextbox']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(firstName));
			js.executeScript("arguments[0].scrollIntoView();", firstName);
			if(firstName.isDisplayed())
			{
				firstName.clear();
				if(!data.get(1).equalsIgnoreCase("empty"))
				{
					js.executeScript("arguments[0].value='" + data.get(1) + "';", firstName);
				}
				else
				{
					firstName.sendKeys("");
				}
			}
			
			WebElement lastName = driver.findElement(By.cssSelector("ul[class='choicelist clearfix']>li:nth-child(2) input[class=' inTextbox']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(lastName));
			js.executeScript("arguments[0].scrollIntoView();", lastName);
			if(lastName.isDisplayed())
			{
				lastName.clear();
				if(!data.get(2).equalsIgnoreCase("empty"))
				{
					js.executeScript("arguments[0].value='" + data.get(2) + "';", lastName);
				}
				else
				{
					lastName.sendKeys("");
				}
				
			}
			
			WebElement contactNo = driver.findElement(By.cssSelector("ul[class='choicelist clearfix']>li:nth-child(3) input[class=' inTextbox']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(contactNo));
			js.executeScript("arguments[0].scrollIntoView();", contactNo);
			if(contactNo.isDisplayed())
			{
				
				contactNo.clear();
				if(!data.get(2).equalsIgnoreCase("empty"))
				{
					js.executeScript("arguments[0].value='" + data.get(3) + "';", contactNo);
				}
				else
				{
					contactNo.sendKeys("");
				}
			}
			
			WebElement email = driver.findElement(By.cssSelector("ul[class='choicelist clearfix']>li:nth-child(4) input[class=' inTextbox']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(email));
			js.executeScript("arguments[0].scrollIntoView();", email);
			if(email.isDisplayed())
			{
				
				email.clear();
				if(!data.get(2).equalsIgnoreCase("empty"))
				{
					js.executeScript("arguments[0].value='" + data.get(4) + "';", email);
				}
				else
				{
					email.sendKeys("");
				}
			}
			
			
			WebElement dropDownforState = driver.findElement(By.cssSelector("div[name='dropdown_closest']>section div[class='zsSelector dropdown']>button[class='btn dropdown-toggle']"));
			//wait.until(ExpectedConditions.visibilityOfAllElements(dropDownforState));
			js.executeScript("arguments[0].scrollIntoView();", dropDownforState);
			js.executeScript("arguments[0].click()", dropDownforState);
			
			List<WebElement> selectState = driver.findElements(By.cssSelector("ul[id='dropdownlist'] li>a[data]"));
			//wait.until(ExpectedConditions.visibilityOfAllElements(selectState));
			for(int j = 0; j < selectState.size(); j++)
			{
				
				js.executeScript("arguments[0].scrollIntoView();", selectState.get(j));
				if(selectState.get(j).getAttribute("data").equalsIgnoreCase("Tamil Nadu"))
				{
					js.executeScript("arguments[0].click()", selectState.get(j));
					break;
				}
			}
			
			
			List<WebElement> skills = driver.findElements(By.cssSelector("ul[id='simplechoicelist']>li span[class='choiceLabel']"));
			for(int i = 0; i < skills.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", skills.get(i));
				
				if(skills.get(i).getText().contains("Big Data"))
				{
					js.executeScript("arguments[0].click()", skills.get(i));
				}
				else if(skills.get(i).getText().contains("Data Science"))
				{
					js.executeScript("arguments[0].click()", skills.get(i));
				}
				else if(skills.get(i).getText().contains("Cybersecurity"))
				{
					js.executeScript("arguments[0].click()", skills.get(i));
				}
				
				else if(skills.get(i).getText().contains("Returning to Work"))//current status option
				{
					js.executeScript("arguments[0].click()", skills.get(i));
					break;
				}
			}
			
			WebElement submit = driver.findElement(By.cssSelector("button[class='btnFormAction submit']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(submit));
			js.executeScript("arguments[0].scrollIntoView();", submit);
			if(submit.isDisplayed())
			{
				js.executeScript("arguments[0].click()", submit);
				
				if(driver.findElements(By.cssSelector("span[class='error-text'][name='error_content']")).size()>0)
				{
					System.out.println("error message displayed");
					status.add("error");
				}
				else
				{
					System.out.println("error message not displayed");
					status.add("success");
				}
				
			}
			driver.switchTo().defaultContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		return status;
	}
	
	public ArrayList<String> errorMessageForEmptyFields()
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			WebElement submit = driver.findElement(By.cssSelector("button[class='btnFormAction submit']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(submit));
			js.executeScript("arguments[0].scrollIntoView();", submit);
			if(submit.isDisplayed())
			{
				js.executeScript("arguments[0].click()", submit);
				
				if(driver.findElements(By.cssSelector("span[class='error-text'][name='error_content']")).size()>0)
				{
					System.out.println("error message displayed");
					status.add("success");
				}
				else
				{
					System.out.println("error message not displayed");
					status.add("fail");
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> InvalidFirstName(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> EmptyFirstName(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> InvalidLastName(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> EmptyLastName(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> InvalidContactNo(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> EmptyContactNo(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> InvalidEmail(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> EmptyEmail(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> WithoutState(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> ValidPlacementForm(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> withoutData(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			status.addAll(this.form(data));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
}

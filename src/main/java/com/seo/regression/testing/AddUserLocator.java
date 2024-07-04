package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserLocator 
{

	String url ;
	WebDriver driver;
	
	public AddUserLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void signUpPage() throws InterruptedException
	{
		WebElement clickSignUp = driver.findElement(By.cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li:nth-child(3) a"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
		clickSignUp.sendKeys(n);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
	}
	
	public ArrayList<Integer> signUpFunction(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<Integer> statusOfTestCase = new ArrayList<Integer>();
		try
		{
			WebElement fullName = driver.findElement(By.cssSelector("input#name"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,100)", "");
			fullName.clear();
			fullName.sendKeys(dataFromExcel.get(1));
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)", "");
			WebElement email = driver.findElement(By.cssSelector("input#email"));
			email.clear();
			email.sendKeys(dataFromExcel.get(2));
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,100)", "");
			WebElement password = driver.findElement(By.cssSelector("input#password"));
			password.clear();
			password.sendKeys(dataFromExcel.get(3));
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			Select select = new Select(driver.findElement(By.cssSelector("select#country"))); 
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)","");
			select.selectByVisibleText(dataFromExcel.get(4));
			Thread.sleep(2000);
			WebElement phoneNumber = driver.findElement(By.cssSelector("input#mobile_number"));
			phoneNumber.clear();
			phoneNumber.sendKeys(dataFromExcel.get(5));
			WebElement clickCheckbox = driver.findElement(By.cssSelector("label[class='cbx'] svg"));
			if(clickCheckbox.isEnabled())
			{
				System.out.println("check box is enabled");
			}
			else
			{
				System.out.println("check box is not enabled");
				clickCheckbox.click();
			}
			Thread.sleep(2000);
			WebElement clickSignUp = driver.findElement(By.cssSelector("input#register_in"));
			js.executeScript("arguments[0].scrollIntoView();", clickSignUp);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.elementToBeClickable(clickSignUp));
			js.executeScript("arguments[0].click()", clickSignUp);
			if(driver.findElements(By.cssSelector("div[role='alert'][class*='status message submission-error ']")).size()>0)
			{
				System.out.println(" error msg showing while doing form submission");
			}
			else
			{
				System.out.println("no error msg");
				statusOfTestCase.add(0);
			}
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return statusOfTestCase;
	}
	
	public ArrayList<Integer> checkValidationMessage()
	{
		ArrayList<Integer> errorMsgStatus = new ArrayList<Integer>();
		try
		{
				List<WebElement> errorMessage = driver.findElements(By.cssSelector("div[role='alert'][class*='status message submission-error ']"));
				
				if(errorMessage.size()>0)
				{
					for(int i = 0; i < errorMessage.size(); i++)
					{	
						switch(i)
						{
						case 0:
							System.out.println("Error message in name field");
							String getNameMessage = errorMessage.get(i).getAttribute("class");
							if(getNameMessage.contains("error-name is-shown"))
							{
								errorMsgStatus.add(1);
							}
							else
							{
								System.out.println("no validation message for name");
							}
							break;
						case 1:
							System.out.println("Error message in email field");
							String getEmailMessage = errorMessage.get(i).getAttribute("class");
							if(getEmailMessage.contains("error-email is-shown") && getEmailMessage.contains("is-shown"))
							{
								errorMsgStatus.add(2);
							}
							else
							{
								System.out.println("no validation message for email");
							}
							break;
						case 2:
							System.out.println("Error message in password field");
							String getPasswordMessage = errorMessage.get(i).getAttribute("class");
							if(getPasswordMessage.contains("error-password is-shown") && getPasswordMessage.contains("is-shown"))
							{
								errorMsgStatus.add(3);
							}
							else
							{
								System.out.println("no validation message for password");
							}
							break;
						case 3:
							System.out.println("Error message in country field");
							String getCountryMessage = errorMessage.get(i).getAttribute("class");
							if(getCountryMessage.contains("error-country is-shown")&& getCountryMessage.contains("is-shown"))
							{
								errorMsgStatus.add(4);
							}
							else
							{
								System.out.println("no validation message for country");
							}
							break;
						case 4:
							System.out.println("Error message in mobile field");
							String getMobileMessage = errorMessage.get(i).getAttribute("class");
							if(getMobileMessage.contains("error-mobile is-shown"))
							{
								errorMsgStatus.add(5);
							}
							else
							{
								System.out.println("no validation message for mobile");
							}
							break;
						}
					}
				}
				else
				{
					errorMsgStatus.add(0);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return errorMsgStatus;
	}
	public ArrayList<Integer> checkSignupWithValidData(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<Integer> statusOfTestCase = new ArrayList<Integer>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		  try { System.out.println("sign up validation started with valid data");
		  
		  String parentWindow = driver.getWindowHandle();
		  
		  Set<String> allWindows = driver.getWindowHandles();
		  
		  for(String window : allWindows) {
		  
		  driver.switchTo().window(window);
		  
		  if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setEnvironment(
		  RegressionTesting.ENV_TO_USE)+"/")) {
		  
		  driver.switchTo().window(window);
		  
		  WebElement clickSignUp = driver.findElement(By.
		  cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li:nth-child(3) a"
		  ));
		  
		  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		  
		  String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
		  
		  clickSignUp.sendKeys(n);
		  
		  String registerWindow = driver.getWindowHandle();
		  
		  Set<String> Windows = driver.getWindowHandles();
		  
		  for(String tab : Windows) { driver.switchTo().window(tab);
		  if(driver.getCurrentUrl().contains("register")) {
		  driver.switchTo().window(tab);
		  
		  statusOfTestCase.addAll(this.signUpFunction(dataFromExcel));//enter all data
		  
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		  
		  driver.switchTo().window(tab);
		  
		  if(driver.getCurrentUrl().contains("verify")) {
		  driver.switchTo().window(tab);
		  
		  if(driver.findElement(By.xpath("//h3[contains(text(),'Let’s get started')]"))
		  .isDisplayed()) { System.out.println("Let’s get started page displayed");
		  driver.close(); driver.switchTo().window(parentWindow); break; } else
		  if(driver.getCurrentUrl().contains("data:,")) {
		  driver.switchTo().window(window); driver.close();
		  driver.switchTo().window(parentWindow); } else
		  if(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setEnvironment(
		  RegressionTesting.ENV_TO_USE)+"/")) { driver.switchTo().window(window);
		  driver.close(); driver.switchTo().window(parentWindow); }
		  driver.switchTo().window(parentWindow); } }
		  
		  driver.switchTo().window(registerWindow); }
		  
		  } driver.switchTo().window(parentWindow);
		  
		  } if(driver.findElement(By.
		  cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li:nth-child(3) a"
		  )).isDisplayed()) { driver.findElement(By.
		  cssSelector("ul[class*='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li:nth-child(3) a"
		  )).click(); String baseWindow = driver.getWindowHandle(); Set<String>
		  newWindows = driver.getWindowHandles(); for(String nextWindow : newWindows) {
		  driver.switchTo().window(nextWindow);
		  if(driver.getCurrentUrl().contains("dashboard")) {
		  driver.switchTo().window(nextWindow);
		  
		  WebElement clickdropdown = driver.findElement(By.
		  cssSelector("li[class*='Header_SigNUP'] img[alt='icon']")); WebDriverWait
		  wait = new WebDriverWait(driver, Duration.ofSeconds(0, 40));
		  wait.until(ExpectedConditions.elementToBeClickable(clickdropdown));
		  if(clickdropdown.isDisplayed()) { js.executeScript("arguments[0].click()",
		  clickdropdown);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		  Thread.sleep(1000); WebElement clickSignOut = driver.findElement(By.
		  cssSelector("ul[class*='dropdown-menu Header_Primary'] li:nth-child(5) a"));
		  if(clickSignOut.isDisplayed()) { js.executeScript("arguments[0].click()",
		  clickSignOut); } } } driver.switchTo().window(baseWindow); } } else { String
		  baseWindow = driver.getWindowHandle(); Set<String> newWindows =
		  driver.getWindowHandles(); for(String nextWindow : newWindows) {
		  driver.switchTo().window(nextWindow);
		  if(driver.getCurrentUrl().contains("dashboard")) {
		  driver.switchTo().window(nextWindow); WebElement clickdropdown =
		  driver.findElement(By.
		  cssSelector("li[class*='Header_SigNUP'] img[alt='icon']")); WebDriverWait
		  wait = new WebDriverWait(driver, Duration.ofSeconds(0, 40));
		  wait.until(ExpectedConditions.elementToBeClickable(clickdropdown));
		  if(clickdropdown.isDisplayed()) { js.executeScript("arguments[0].click()",
		  clickdropdown);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		  Thread.sleep(1000); WebElement clickSignOut = driver.findElement(By.
		  cssSelector("ul[class*='dropdown-menu Header_Primary'] li:nth-child(5) a"));
		  if(clickSignOut.isDisplayed()) { js.executeScript("arguments[0].click()",
		  clickSignOut); } } } driver.switchTo().window(baseWindow); }
		  
		  } } catch(Exception e) { e.printStackTrace(); }
		 
		
		/*
		 * try { System.out.println("sign up validation started with valid data");
		 * 
		 * String parentWindow = driver.getWindowHandle();
		 * 
		 * Set<String> allWindows = driver.getWindowHandles();
		 * 
		 * for(String window : allWindows) {
		 * 
		 * driver.switchTo().window(window);
		 * 
		 * if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setEnvironment(
		 * RegressionTesting.ENV_TO_USE)+"/")) {
		 * 
		 * driver.switchTo().window(window);
		 * 
		 * driver.switchTo().newWindow(WindowType.TAB);
		 * 
		 * driver.get(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+"/");
		 * 
		 * Thread.sleep(500);
		 * 
		 * driver.get("https://stagecourses-in.skillup.online/register");
		 * 
		 * Thread.sleep(500);
		 * 
		 * statusOfTestCase.addAll(this.signUpFunction(dataFromExcel));//enter all data
		 * in text box
		 * 
		 * if(driver.getCurrentUrl().contains("dashboard")) {
		 * driver.switchTo().window(window);
		 * 
		 * driver.close();
		 * 
		 * driver.switchTo().window(parentWindow);
		 * 
		 * break; } else if(driver.getCurrentUrl().contains("verify")) {
		 * 
		 * driver.close();
		 * 
		 * driver.switchTo().window(parentWindow);
		 * 
		 * break; } else {
		 * 
		 * driver.close();
		 * 
		 * driver.switchTo().window(parentWindow);
		 * 
		 * break; } } driver.switchTo().window(parentWindow); } } catch(Exception e) {
		 * e.printStackTrace(); }
		 */
		return statusOfTestCase;
	}
}

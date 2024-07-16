package com.palm.sanityTesting;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProcessLogin
{
	WebDriver driver;
	String url = "";
	public ProcessLogin(WebDriver driver) 
	{
		this.driver = driver;
		
	}
	public String loginFunction(String userName, String passWord) throws InterruptedException
	{
		String loginStatus="success";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try
		{
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			WebElement clickLogin = driver.findElement(By.cssSelector("ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li[class='Header_loginBtn__3Xv3A'] a"));
			js.executeScript("arguments[0].scrollIntoView();", clickLogin);
			wait.until(ExpectedConditions.elementToBeClickable(clickLogin));
			if(clickLogin.isDisplayed())
			{
				String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
				clickLogin.sendKeys(n);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			}
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			String parentWindow = driver.getWindowHandle();
			Set<String> nextWindow = driver.getWindowHandles();
			for(String window : nextWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
					js.executeScript("window.scrollBy(0, 200)", "");
					WebElement userNameElement = driver.findElement(By.cssSelector("input#email"));
					js.executeScript("arguments[0].scrollIntoView();", userNameElement);
					userNameElement.clear();
					userNameElement.sendKeys(userName);
					WebElement passwordElement = driver.findElement(By.cssSelector("input#password"));
					js.executeScript("arguments[0].scrollIntoView();", passwordElement);
					passwordElement.clear();
					passwordElement.sendKeys(passWord);
					js.executeScript("window.scrollBy(0, 100)", "");
					WebElement clickSubmit = driver.findElement(By.cssSelector("input[value='Log In']"));
					js.executeScript("arguments[0].scrollIntoView();", clickSubmit);
					if(clickSubmit.isDisplayed())
					{
						js.executeScript("arguments[0].click()", clickSubmit);
					}
					loginStatus = this.ErrorMessage();
					if(loginStatus.equalsIgnoreCase("Failed"))
					{
						loginStatus = "Failed";
					}
				  else 
				  { 
					  loginStatus= this.checkUserAfterLoggedIn(); 
				  } 
					driver.close();
					 
					break;
				}
			}
		driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			loginStatus="failed";
		}
		return loginStatus;
	}
	public String ErrorMessage()
	{
		String loginStatus = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> error = driver.findElements(By.xpath("//div[@class='NotificationTypeError spacing-mb16 status message submission-error is-shown']//div[@class='fiederror message-title']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(error.size()>0)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				List<WebElement> errorMsg2 = driver.findElements(By.xpath("//div[@class='NotificationTypeError spacing-mb16 status message submission-error is-shown']//div[@class='fiederror message-title']"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				if(errorMsg2.size()>0)
				{
					if(errorMsg2.get(0).getText().equalsIgnoreCase("Email or password is incorrect.") || errorMsg2.get(0).getText().equalsIgnoreCase("In order to sign in, you need to activate your account."))
					{
						loginStatus = "Failed";
					}
					else
					{
						loginStatus = "Success";
					}
				}
				else
				{
					loginStatus = "Success";
				}
			}
			else
			{
				System.out.println("no error message");
				loginStatus = "Success";
			}
			
		}
		catch(Exception e)
		{
			System.out.println("no error message");
			e.printStackTrace();
		}
		
		return loginStatus;
	}
	
	public String checkUserAfterLoggedIn()
	{
		String loginStatus=null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			try
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				Thread.sleep(2000);
				String parentWindow = driver.getWindowHandle();
				Set<String> listOfWindow = driver.getWindowHandles();
				for(String windows : listOfWindow)
				{
					Thread.sleep(1000);
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("personalized/"))
					{
						driver.switchTo().window(windows);
						WebElement clickBeginProfile = driver.findElement(By.cssSelector("div[class='col-md-12']:nth-child(2) div[class*='Personalized_PersContent'] a"));
						js.executeScript("arguments[0].scrollIntoView();", clickBeginProfile);
						WebDriverWait wait2 =  new WebDriverWait(driver, Duration.ofSeconds(30));
						wait2.until(ExpectedConditions.elementToBeClickable(clickBeginProfile));
						if(clickBeginProfile.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickBeginProfile);
						}
						Thread.sleep(3000);
						WebElement clickDropdownIcon = driver.findElement(By.cssSelector("li[class='Header_SigNUP__cUzCw'] img[alt='icon']"));
						js.executeScript("arguments[0].scrollIntoView();", clickDropdownIcon);
						if(clickDropdownIcon.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickDropdownIcon);
						}
						Thread.sleep(2000);
						WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header_Primary'] li:nth-child(5) a"));
						js.executeScript("arguments[0].scrollIntoView();", clickSignOut);
						if(clickSignOut.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickSignOut);
						}
						Thread.sleep(2000);
						
					 }
					else if(driver.getCurrentUrl().contains("dashboard"))
					{
						Thread.sleep(1000);
						driver.switchTo().window(windows);
						WebElement clickDropDown = driver.findElement(By.cssSelector("li[class*='SigNUP'] img[alt='icon']"));
						js.executeScript("arguments[0].scrollIntoView();", clickDropDown);
						if(clickDropDown.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickDropDown);
						}
						Thread.sleep(1000);
						WebElement checkLoggedName = driver.findElement(By.cssSelector("li[class*='SigNUP'] ul[class*='dropdown-menu'] li:nth-child(1) a"));
						js.executeScript("arguments[0].scrollIntoView();", checkLoggedName);
						String checkText = checkLoggedName.getText();
						if(checkText.contains("Hello"))
						{
							loginStatus = "Success";
							System.out.println("logged in successfully");
							Thread.sleep(2000);
							WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header_Primary'] li:nth-child(5) a"));
							js.executeScript("arguments[0].scrollIntoView();", clickSignOut);
							if(clickSignOut.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickSignOut);
							}
							System.out.println("log out successfully");
							Thread.sleep(1000);
							
						}
						else
						{
							loginStatus = "Failed";
							System.out.println("not logged in ");
						}
					}
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
			loginStatus = "Failed";
		}
		return loginStatus;
	}
	public void logOutFunction() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			WebElement clickDropdown = driver.findElement(By.cssSelector("li[class*='SigNUP'] img[class='dPaRoW']"));
			js.executeScript("arguments[0].scrollIntoView();", clickDropdown);
			if(clickDropdown.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickDropdown);
			}
			wait.until(ExpectedConditions.elementToBeClickable(clickDropdown));
			WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header_Primary'] li:nth-child(5) a"));
			js.executeScript("arguments[0].scrollIntoView();", clickSignOut);
			if(clickSignOut.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickSignOut);
			}
			wait.until(ExpectedConditions.elementToBeClickable(clickSignOut));
			System.out.println("logout successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> checkInvalidUsername(String uName, String pwd) throws InterruptedException
	{
		ArrayList<String> InvalidUsername = new ArrayList<String>();
		try
		{
			System.out.println("Invalid Email Process started");
			String parentWindow = driver.getWindowHandle();
			String checkURLEnvironment = OpenWebsite.setURL+"/";
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().equalsIgnoreCase(checkURLEnvironment))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.switchTo().newWindow(WindowType.TAB);
					driver.navigate().to(checkURLEnvironment);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					InvalidUsername.add(this.loginFunction(uName, pwd));
					driver.close();
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return InvalidUsername;
	}
	public ArrayList<String> checkInvalidPassword(String uName, String pwd) throws InterruptedException
	{
		ArrayList<String> InvalidPassword = new ArrayList<String>();
		try
		{
			System.out.println("Invalid password Process started");
			String parentWindow = driver.getWindowHandle();
			String checkURLEnvironment = OpenWebsite.setURL+"/";
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().equalsIgnoreCase(checkURLEnvironment))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.switchTo().newWindow(WindowType.TAB);
					driver.navigate().to(checkURLEnvironment);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					InvalidPassword.add(this.loginFunction(uName, pwd));
					driver.close();
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			InvalidPassword.add("failed");
		}
		return InvalidPassword;
	}
	public ArrayList<String> checkInvalidUserNameAndPassword(String uName, String pwd) throws InterruptedException
	{
		ArrayList<String> InvalidUserNameAndPassword = new ArrayList<String>();
		try
		{
			System.out.println("InvalidEmail and Password process started");
			String parentWindow = driver.getWindowHandle();
			String checkURLEnvironment = OpenWebsite.setURL+"/";
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().equalsIgnoreCase(checkURLEnvironment))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.switchTo().newWindow(WindowType.TAB);
					driver.navigate().to(checkURLEnvironment);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					InvalidUserNameAndPassword.add(this.loginFunction(uName, pwd));
					driver.close();
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return InvalidUserNameAndPassword;
	}
	public ArrayList<String> checkValidCredentials(String uName, String pwd) throws InterruptedException
	{
		ArrayList<String> ValidCredentials = new ArrayList<String>();
		try
		{
			System.out.println("valid data process started");
			String parentWindow = driver.getWindowHandle();
			String checkURLEnvironment = OpenWebsite.setURL+"/";
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().equalsIgnoreCase(checkURLEnvironment))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.switchTo().newWindow(WindowType.TAB);
					driver.navigate().to(checkURLEnvironment);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					ValidCredentials.add(this.loginFunction(uName, pwd));
					driver.close();
				}
				driver.switchTo().window(parentWindow);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("login process done");
		return ValidCredentials;
	}
}

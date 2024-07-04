package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQLocator
{
	WebDriver driver;
	ProcessLogin processLogin;
	
	public FAQLocator(WebDriver driver)
	{
		this.driver = driver;
		this.processLogin = new ProcessLogin(this.driver);
	}
	
	public ArrayList<String> loginProcess(String email, String pwd)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			WebElement clickLogin = driver.findElement(By.cssSelector("ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp'] li[class='Header_loginBtn__3Xv3A'] a"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(clickLogin));
			if(clickLogin.isDisplayed())
			{
				String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
				clickLogin.sendKeys(n);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			}
			String parentWindow = driver.getWindowHandle();
			Set<String> nextWindow = driver.getWindowHandles();
			for(String window : nextWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
					Thread.sleep(2000);
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("window.scrollBy(0, 200)", "");
					Thread.sleep(2000);
					WebElement userNameElement = driver.findElement(By.cssSelector("input#email"));
					userNameElement.clear();
					userNameElement.sendKeys(email);
					WebElement passwordElement = driver.findElement(By.cssSelector("input#password"));
					passwordElement.clear();
					passwordElement.sendKeys(pwd);
					js.executeScript("window.scrollBy(0, 100)", "");
					WebElement clickSubmit = driver.findElement(By.cssSelector("input[value='Log In']"));
					clickSubmit.click();
				}
			}		
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> FAQProcess()
	{
		ArrayList<String> failedFAQ = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement focusFooterCompany = driver.findElement(By.xpath("//div[@class='Footer_FootMenuHeDinG__tseE0']//h2[contains(text(),'Company')]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", focusFooterCompany);
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-200)", "");
			Thread.sleep(1000);
			List<WebElement> clickFAQ = driver.findElements(By.cssSelector("div[class*='Footer_FootMenuiNNr'] ul>li>a"));
			for(int k = 0; k < clickFAQ.size(); k++)
			{
				js.executeScript("arguments[0].scrollIntoView();", clickFAQ.get(k));
				if(clickFAQ.get(k).getText().contains("FAQ"))
				{
					js.executeScript("arguments[0].click()", clickFAQ.get(k));
					break;
				}
			}
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("faq"))
				{
					driver.switchTo().window(window);
					
					List<WebElement> faqLocator = driver.findElements(By.cssSelector("div[class*='container-fluid Card_containerInner'] div[class='row gx-3 gy-2'] div[class*='undefined']"));
					for(int i = 0; i < faqLocator.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", faqLocator.get(i));
						String faqHeading = faqLocator.get(i).findElement(By.cssSelector(" h2")).getText();
						
						WebElement faqLink = faqLocator.get(i).findElement(By.cssSelector(" a:not([href])"));
						js.executeScript("arguments[0].scrollIntoView();", faqLink);
						if(faqLink.isDisplayed())
						{
							js.executeScript("arguments[0].click()", faqLink);
						}
						List<WebElement> faqVerification = driver.findElements(By.cssSelector("div[class*='container-fluid Accordion_containerInner']>div>div[class='col-lg-7 col-12']>div[class='col-12']"));
						for(int j = 0; j < faqVerification.size(); j++)
						{
							if(i == j)
							{
								String checkFaqHeading = faqVerification.get(j).findElement(By.cssSelector(" h2[class*='Accordion_FAQtopHeading']")).getText();
								if(!faqHeading.equals(checkFaqHeading))
								{
									failedFAQ.add(faqHeading.concat(" is not same when compare heading"));
								}
								else
								{
									System.out.println("FAQ : "+faqHeading+ "working fine");
								}
								List<WebElement> checkFaq =faqVerification.get(j).findElements(By.cssSelector(" div>div[class='border-0 my-1 accordion-item']>h2>button"));
								for(int k = 0; k < checkFaq.size(); k++)
								{
									js.executeScript("arguments[0].click()", checkFaq.get(k));
									if(faqVerification.get(j).findElement(By.cssSelector(" div[class='accordion-collapse collapse show']")).isDisplayed())
									{
										JavascriptExecutor js1 = (JavascriptExecutor) driver;
										js1.executeScript("arguments[0].click()", checkFaq.get(k));
										//checkFaq.get(k).click();
									}
									if(k==checkFaq.size()-1)
									{
										((JavascriptExecutor) driver)
									    .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
										break;
									}
								}
								break;
							}
						}
						
					}
				}
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return failedFAQ;
	}
	public ArrayList<String> verifyInvalidFullname(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Invalid full name process done");
		return datastatus;
	}
	 
	public ArrayList<String> verifyFAQFormProcess(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			WebElement focusForm = driver.findElement(By.cssSelector("div[class*='Form_formContainer']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(focusForm));
			js.executeScript("arguments[0].scrollIntoView();", focusForm);
			WebElement fullname = driver.findElement(By.cssSelector("form>div[class='row gy-3']>div[class*='col-12 ']>input[name='fullname']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(fullname));
			js.executeScript("arguments[0].scrollIntoView();", fullname);
			fullname.clear();
			if(!data.get(1).equalsIgnoreCase("empty"))
			{
				fullname.sendKeys(data.get(1));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			}
			else
			{
				fullname.sendKeys("");
			}
			WebElement email = driver.findElement(By.cssSelector("form>div[class='row gy-3']>div[class*='col-12 ']>input[name='email']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(email));
			js.executeScript("arguments[0].scrollIntoView();", email);
			email.clear();
			if(!data.get(2).equalsIgnoreCase("empty"))
			{
				email.sendKeys(data.get(2));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			}
			else
			{
				email.sendKeys("");
			}
			
		  WebElement country = driver.findElement(By.cssSelector("div[class*='col-12 ']>select[name='country']"));
		  wait.until(ExpectedConditions.visibilityOfAllElements(country));
		  js.executeScript("arguments[0].scrollIntoView();", country);
		  Select countryName = new Select(country);
		  countryName.selectByVisibleText("India");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement contact = driver.findElement(By.cssSelector("form>div[class='row gy-3']>div[class*='col-12'] input[name='contactnumber']"));
			 wait.until(ExpectedConditions.visibilityOfAllElements(contact));
			js.executeScript("arguments[0].scrollIntoView();", contact);
			contact.clear();
			contact.sendKeys(data.get(3));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement category = driver.findElement(By.cssSelector("form>div[class='row gy-3']>div[class*='col-12']>select[name='userpersona'],[name='additionalinfo']"));
			 wait.until(ExpectedConditions.visibilityOfAllElements(category));
			js.executeScript("arguments[0].scrollIntoView();", category);
			Select categoryName = new Select(category);
			try
			{
				categoryName.selectByVisibleText("Invoices, refunds, & how to pay");
				System.out.println("Option selected successfully!");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Option not found: " + e.getMessage());
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement queryMsg = driver.findElement(By.cssSelector("form>div[class='row gy-3']>div[class*='col-12']>textarea[name='message']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(queryMsg));
			js.executeScript("arguments[0].scrollIntoView();", queryMsg);
			queryMsg.clear();
			if(!data.get(4).equalsIgnoreCase("empty"))
			{
				queryMsg.sendKeys(data.get(4));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			}
			else
			{
				queryMsg.sendKeys("");
			}
			
			WebElement locateFooter = driver.findElement(By.cssSelector("footer#newsletter, div[class='Footer_footertopmenu__gu_Hf']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(locateFooter));
			js.executeScript("arguments[0].scrollIntoView();", locateFooter);
			Thread.sleep(500);
			js.executeScript("window.scrollBy(0,-500)");
			Thread.sleep(500);
			WebElement submit = driver.findElement(By.cssSelector("div[class='col-12']>button[type='submit']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(submit));
			js.executeScript("arguments[0].scrollIntoView();", submit);
			if(submit.isDisplayed())
			{
				js.executeScript("arguments[0].click()", submit);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			datastatus.addAll(this.errorMsg());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return datastatus;
	}
	public ArrayList<String> EmptyFullname(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Empty Fullname process done");
		return datastatus;
	}
	public ArrayList<String> validFullname(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("valid Fullname process done");
		return datastatus;
	}
	public ArrayList<String> invalidEmail(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Invalid Email process done");
		return datastatus;
	}
	public ArrayList<String> EmptyEmail(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Empty Email process done");
		return datastatus;
	}
	public ArrayList<String> validEmail(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("valid Email process done");
		return datastatus;
	}
	public ArrayList<String> InvalidContact(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Invalid contact process done");
		return datastatus;
	}
	public ArrayList<String> EmptyContact(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Empty contact process done");
		return datastatus;
	}
	public ArrayList<String> validContact(ArrayList<String> data)
	{
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("valid contact process done");
		return datastatus;
	}
	public ArrayList<String> EmptyQuery(ArrayList<String> data)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> datastatus = new ArrayList<String>();
		datastatus.addAll(this.verifyFAQFormProcess(data));
		System.out.println("Empty query process done");
		
		  WebElement dropdownIcon =
		  driver.findElement(By.cssSelector("li[class*='Header_SigNUP']>a"));
		  js.executeScript("arguments[0].scrollIntoView();", dropdownIcon);
		  if(dropdownIcon.isDisplayed()) { js.executeScript("arguments[0].click()",
		  dropdownIcon); WebElement clickSignOut = driver.findElement(By.
		  cssSelector("ul[class*='dropdown-menu Header']>li:nth-child(5)>a"));
		  js.executeScript("arguments[0].scrollIntoView();", clickSignOut);
		  if(clickSignOut.isDisplayed()) { js.executeScript("arguments[0].click()",
		  clickSignOut); } }
		 
		
		
		return datastatus;
	}
	
	public ArrayList<String> errorMsg()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(driver.findElements(By.cssSelector("p[class='text-danger mb-0 mt-2']")).size()>0)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				WebElement errorMsgLocator = driver.findElement(By.cssSelector("p[class='text-danger mb-0 mt-2']"));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				js.executeScript("arguments[0].scrollIntoView();", errorMsgLocator);
				if(errorMsgLocator.getText().contains("name"))
				{
					System.out.println("full name error");
					status.add("name");
				}
				else if(errorMsgLocator.getText().contains("email"))
				{
					System.out.println("email error");
					status.add("email");
				}
				else if(errorMsgLocator.getText().contains("contact"))
				{
					System.out.println("contact error");
					status.add("contact");
				}
				else if(errorMsgLocator.getText().contains("category"))
				{
					System.out.println("category error");
					status.add("category");
				}
				else if(errorMsgLocator.getText().contains("query"))
				{
					System.out.println("query error");
					status.add("query");
				}
			}
			else if(driver.findElements(By.cssSelector("div[class*='Form_successMessageSection'] h2")).size()>0)
			{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					System.out.println("success msg present");
					Thread.sleep(1000);
					status.add("pass");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		System.out.println("status of process : "+status);
		return status;
	}
}

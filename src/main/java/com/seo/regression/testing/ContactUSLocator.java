package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUSLocator
{
	
	WebDriver driver;
	String url;
	
	public ContactUSLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public ArrayList<String> clickContactUs()
	{
		ArrayList<String> statusOfFunction = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement contactUs = driver.findElement(By.cssSelector("div[class='Header_headerRight__RJT1X'] ul[class='list-unstyled navbar-nav nav Header_navLinks__aS6_P'] li:nth-child(2) a"));
			js.executeScript("arguments[0].scrollIntoView();", contactUs);
			if(contactUs.isDisplayed())
			{
				String contactUSURL = contactUs.getAttribute("href");
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(contactUSURL);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("contact"))
					{
						driver.switchTo().window(window);
						break;
					}
					driver.switchTo().window(window);
				}
				statusOfFunction.add("success");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfFunction;
	}
	
	public ArrayList<String> contactUsFunction(ArrayList<String> dataFromExcel) throws InterruptedException
	{
		ArrayList<String> statusOfFunction = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try
		{
			js.executeScript("window.scrollBy(0,100)", "");
			WebElement contactAboutLocator = driver.findElement(By.cssSelector("select[name='enquirytype']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(contactAboutLocator));
			js.executeScript("arguments[0].scrollIntoView();", contactAboutLocator);
			Select selectContact = new Select(contactAboutLocator);
			if(!dataFromExcel.get(1).equalsIgnoreCase("empty"))
			{
				selectContact.selectByVisibleText(dataFromExcel.get(1));
			}
			else
			{
				selectContact.selectByVisibleText("Business Enquiry");
			}
			WebElement enterFullname = driver.findElement(By.cssSelector("input[name='fullname']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(enterFullname));
			js.executeScript("arguments[0].scrollIntoView();", enterFullname);
			if(!dataFromExcel.get(2).equalsIgnoreCase("empty"))
			{
				enterFullname.clear();
				enterFullname.sendKeys(dataFromExcel.get(2));
			}
			else
			{
				enterFullname.sendKeys("");
			}
			WebElement enterEmail = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='col-12 contactform_phnNumLeft']:nth-child(2)>input"));
			wait.until(ExpectedConditions.visibilityOfAllElements(enterEmail));
			js.executeScript("arguments[0].scrollIntoView();", enterEmail);
			if(!dataFromExcel.get(3).equalsIgnoreCase("empty"))
			{
				enterEmail.clear();
				enterEmail.sendKeys(dataFromExcel.get(3));
			}
			else
			{
				enterEmail.sendKeys("");
			}
			
			WebElement countryLocator = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(3)>select[name='country']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(countryLocator));
			js.executeScript("arguments[0].scrollIntoView();", countryLocator);
			Select selectCountry = new Select(countryLocator);
			if(!dataFromExcel.get(4).equalsIgnoreCase("empty"))
			{
				selectCountry.selectByVisibleText(dataFromExcel.get(4));
			}
			else
			{
				selectCountry.selectByVisibleText("India");
			}
			
			WebElement mblLocator = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(4) input[name='contactnumber']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(mblLocator));
			js.executeScript("arguments[0].scrollIntoView();", mblLocator);
			if(!dataFromExcel.get(5).equalsIgnoreCase("empty"))
			{
				mblLocator.clear();
				mblLocator.sendKeys(dataFromExcel.get(5));
			}
			else
			{
				mblLocator.sendKeys("");
			}
			WebElement orgLocator = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(5) input[name='organization']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(orgLocator));
			js.executeScript("arguments[0].scrollIntoView();", orgLocator);
			if(!dataFromExcel.get(6).equalsIgnoreCase("empty"))
			{
				orgLocator.clear();
				orgLocator.sendKeys(dataFromExcel.get(6));
			}
			else
			{
				orgLocator.sendKeys("");
			}
			
			js.executeScript("window.scrollBy(0,900)", "");
			WebElement jobTitleLocator = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(6) input[name='jobtitle']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(jobTitleLocator));
			js.executeScript("arguments[0].scrollIntoView();", jobTitleLocator);
			if(!dataFromExcel.get(7).equalsIgnoreCase("empty"))
			{
				jobTitleLocator.clear();
				jobTitleLocator.sendKeys(dataFromExcel.get(7));
			}
			else
			{
				jobTitleLocator.sendKeys("");
			}
			
			List<WebElement> selectSkills = driver.findElements(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(8) div[class*='col-md-6 contactform_CheckboxMain']>input[name='skills']"));
			if(!dataFromExcel.get(8).equalsIgnoreCase("empty"))
			{
				String getData[] = dataFromExcel.get(8).split("_");
				for(String skillFromExcel : getData)
				{
					for(int i = 0; i < selectSkills.size(); i++)
					{
						js.executeScript("arguments[0].scrollIntoView();", selectSkills.get(i));
						String skillFromBrowser = selectSkills.get(i).getAttribute("id");
						if(skillFromExcel.equalsIgnoreCase(skillFromBrowser))
						{
							System.out.println("skill to be select : "+skillFromBrowser);
							wait.until(ExpectedConditions.elementToBeClickable(selectSkills.get(i)));
							js.executeScript("arguments[0].click()", selectSkills.get(i));
							if(!selectSkills.get(i).isEnabled())
							{
								selectSkills.get(i).click();
							}
						}
					}
				}
			}
			else
			{
				System.out.println("skill not selected");
			}
			js.executeScript("window.scrollBy(0,300)", "");
			WebElement subject = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(9)>input[name='subject']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(subject));
			js.executeScript("arguments[0].scrollIntoView();", subject);
			if(!dataFromExcel.get(9).equalsIgnoreCase("empty"))
			{
				subject.clear();
				subject.sendKeys(dataFromExcel.get(9));
			}
			else
			{
				subject.sendKeys("");
			}
			WebElement message = driver.findElement(By.cssSelector("div[class='undefined'] div[class*='contactform_phnNumLeft']:nth-child(10)>textarea#message"));
			wait.until(ExpectedConditions.visibilityOfAllElements(message));
			js.executeScript("arguments[0].scrollIntoView();", message);
			if(!dataFromExcel.get(9).equalsIgnoreCase("empty"))
			{
				message.clear();
				message.sendKeys(dataFromExcel.get(10));
			}
			else
			{
				message.sendKeys("");
			}
			js.executeScript("window.scrollBy(0,300)", "");
			statusOfFunction.add("success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return statusOfFunction;
	}
	
	public ArrayList<String> termsAndService()
	{
		
		ArrayList<String> statusOfFunction = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			List<WebElement> links = driver.findElements(By.cssSelector("div[class='undefined'] div[class*='col-12 contactform_bySharing__PW8_Y']>a"));
			for(int i = 0; i < links.size(); i++)
			{
				
				js.executeScript("arguments[0].scrollIntoView();", links.get(i));
				if(links.get(i).isDisplayed())
				{
					js.executeScript("arguments[0].click()", links.get(i));
					statusOfFunction.add("success");
					//Thread.sleep(2000);
				}
				String parentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("privacy"))
					{
						driver.switchTo().window(window);
						System.out.println("privacy policy window");
						statusOfFunction.add("success");
						driver.close();
						//Thread.sleep(2000);
						driver.switchTo().window(parentWindow);
						//Thread.sleep(2000);
					}
					if(driver.getCurrentUrl().contains("tos"))
					{
						driver.switchTo().window(window);
						System.out.println("terms of service window");
						statusOfFunction.add("success");
						driver.close();
						//Thread.sleep(2000);
						driver.switchTo().window(parentWindow);
						//Thread.sleep(2000);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfFunction;
		
	}
	
	public ArrayList<String> validationMessage()
	{
		ArrayList<String> nameOfError = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> getErrorMessage = driver.findElements(By.cssSelector("p[class='text-danger mb-0 mt-2']"));//p[class='ContactForm_Selectup__nCwVP ContactForm_Hint__2QivB'],
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(getErrorMessage.size()>0)
			{
				for(int i = 0; i < getErrorMessage.size(); i++)
				{
					js.executeScript("arguments[0].scrollIntoView();", getErrorMessage.get(i));
					if(getErrorMessage.get(i).getText().contains("value")||getErrorMessage.get(i).getText().contains("choose"))
					{
						System.out.println("Please select value displayed for contacting us");
						nameOfError.add("value");
					}
					if(getErrorMessage.get(i).getText().contains("full name"))
					{
						System.out.println("Please enter your full name.");
						nameOfError.add("full name");
					}
					if(getErrorMessage.get(i).getText().contains("email"))
					{
						System.out.println("Please enter an email address.");
						nameOfError.add("email");
					}
					if(getErrorMessage.get(i).getText().contains("contact"))
					{
						System.out.println("Please enter contact number.");
						nameOfError.add("contact");
					}
					if(getErrorMessage.get(i).getText().contains("skills"))
					{
						System.out.println("Please select atleast 1 skills.");
						nameOfError.add("skills");
					}
					
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return nameOfError;
	}
	
	public void clickSubmit()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			
			WebElement clickSubmit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			js.executeScript("arguments[0].scrollIntoView();", clickSubmit);
			if(clickSubmit.isDisplayed())
			{
				
				js.executeScript("arguments[0].click()", clickSubmit);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> checkInvalidFullname(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfInvalidFullname = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("Invalid fullname validation process started");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			 statusOfInvalidFullname.addAll(this.termsAndService());
			this.clickSubmit();
			statusOfInvalidFullname.addAll(this.validationMessage());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifySuccessbutton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifySuccessbutton.size()>0)
			{
				System.out.println("success Msg : ");
				statusOfInvalidFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidFullname;
	}
	public ArrayList<String> checkInvalidEmail(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfInvalidEmail = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("Invalid email validation");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidEmail = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifySuccessMsg = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifySuccessMsg.size()>0)
			{
				System.out.println("success Msg : ");
				statusOfInvalidEmail.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidEmail;
	}
	public ArrayList<String> checkInvalidMobile(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfInvalidMobile = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("Invalid mobile validation");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidMobile = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifySuccessMsg = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifySuccessMsg.size()>0)
			{
				System.out.println("success Msg : ");
				statusOfInvalidMobile.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidMobile;
	}
	public ArrayList<String> checkWithoutData(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfInvalidFullname = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("without Data validation");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidFullname = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifySuccessMsg = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifySuccessMsg.size()>0)
			{
				System.out.println("success Msg : ");
				statusOfInvalidFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidFullname;
	}
	public ArrayList<String> checkWithoutContactAbout(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfContactAbout = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("without enquiry validation");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfContactAbout = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifySuccessMsg = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifySuccessMsg.size()>0)
			{
				System.out.println("success Msg : ");
				statusOfContactAbout.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfContactAbout;
	}
	public ArrayList<String> checkWithoutFullname(ArrayList<String> dataFromExcel)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> statusOfWithoutFullname = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("without Fullname validation");
			this.clickContactUs();
			this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfWithoutFullname = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifyContinueButton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifyContinueButton.size()>0)
			{
				System.out.println("success Msg pop up: ");
				js.executeScript("arguments[0].click()", verifyContinueButton);
				statusOfWithoutFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfWithoutFullname;
	}
	public ArrayList<String> checkWithoutEmail(ArrayList<String> dataFromExcel)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> statusOfInvalidFullname = new ArrayList<String>();
		try
		{
			System.out.println("without email validation");
			String HomePage = driver.getWindowHandle();
			this.clickContactUs();
			this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidFullname = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifyContinueButton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifyContinueButton.size()>0)
			{
				System.out.println("success Msg pop up: ");
				WebElement clickButton = driver.findElement(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
				js.executeScript("arguments[0].click()", clickButton);
					statusOfInvalidFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidFullname;
	}
	public ArrayList<String> checkWithoutMobile(ArrayList<String> dataFromExcel)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> statusOfInvalidFullname = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("without mobile validation");
			this.clickContactUs();
			this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidFullname = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifyContinueButton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifyContinueButton.size()>0)
			{
				System.out.println("success Msg pop up: ");
				WebElement clickButton = driver.findElement(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
				js.executeScript("arguments[0].click()", clickButton);
				statusOfInvalidFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidFullname;
	}
	public ArrayList<String> checkWithoutSkills(ArrayList<String> dataFromExcel)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> statusOfInvalidFullname = new ArrayList<String>();
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("without skills validation");
			this.clickContactUs();
			this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfInvalidFullname = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifyContinueButton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifyContinueButton.size()>0)
			{
				System.out.println("success Msg pop up: ");
				WebElement clickButton = driver.findElement(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
				js.executeScript("arguments[0].click()", clickButton);
				statusOfInvalidFullname.add("Failed");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusOfInvalidFullname;
	}
	public ArrayList<String> checkValidData(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfValidData = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			String HomePage = driver.getWindowHandle();
			System.out.println("valid data validation");
			this.clickContactUs();
			 this.contactUsFunction(dataFromExcel);
			this.termsAndService();
			this.clickSubmit();
			statusOfValidData = this.validationMessage();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> verifyContinueButton = driver.findElements(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(verifyContinueButton.size()>0)
			{
					System.out.println("success Msg pop up: ");
					WebElement clickButton = driver.findElement(By.cssSelector("div[class*='contactform_ModalFooter']>button"));
					js.executeScript("arguments[0].click()", clickButton);
					statusOfValidData.add("success");
			}
			Set<String> allScreen = driver.getWindowHandles();
			for(String window : allScreen)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("contact"))
				{
					driver.switchTo().window(window);
					driver.close();
					driver.switchTo().window(HomePage);
					break;
				}
				driver.switchTo().window(HomePage);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			statusOfValidData.add("Failed");
		}
		return statusOfValidData;
	}
}

package com.palm.sanityTesting;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageLinksLocator {

	String url ;
	WebDriver driver;
	//OpenWebsite openWebsite;
	
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li[class='Header_loginBtn__3Xv3A']>a")
	private WebElement clickLoginIcon;
	
	@FindBy(css = "div[class='form-group spacing-mb40'] a")
	private WebElement clickForgotLink;
	
	@FindBy(css = "div[class='col-md-8 col-sm-12'] div[class='singupV2-form']>p>a")
	private WebElement clickSignUpLink;
	
	@FindBy(css = "span[class='Bodycopy-Link Neutral_01-text spacing-mt20']>a:nth-child(1)")
	private WebElement clickTermAndService;
	
	@FindBy(css = "span[class='Bodycopy-Link Neutral_01-text spacing-mt20']>a:nth-child(2)")
	private WebElement clickPrivacyAndPolicy;
	
	@FindBy(css = "div[class='social-section']>a:nth-child(1)")
	private WebElement clickFacebookLink;
	
	@FindBy(css = "div[class='social-section']>a:nth-child(2)")
	private WebElement clickGoogleLink;
	
	@FindBy(css = "div[class='social-section']>a:nth-child(3)")
	private WebElement clickLinkedInLink;
	
	@FindBy(css = "div[class='social-section']>a:nth-child(4)")
	private WebElement clickMicrosoftLink;
	
	@FindBy(css = "ul[class='nav sinup-tabs']>li[id='signuplink']>a")
	private WebElement clickSignUpTab;
	
	public LoginPageLinksLocator(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String checkLoginIconLink()
	{
		String status = "fail";
		try
		{
			clickLoginIcon.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login?"))
				{
					driver.switchTo().window(window);
					System.out.println("login page opened");
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
	
	public String checkForgotPasswordLink()
	{
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 500)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickForgotLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("password_reset"))
				{
					driver.switchTo().window(window);
					System.out.println("forgot page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.close();
					driver.switchTo().window(parentWindow);
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
	public String checkSignUpLink()
	{
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 600)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickSignUpLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("register"))
				{
					driver.switchTo().window(window);
					System.out.println("sign up page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkTermOfServiceLink()
	{
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 600)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickTermAndService.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("tos"))
				{
					driver.switchTo().window(window);
					System.out.println("Terms and service page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.close();
					driver.switchTo().window(parentWindow);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkPrivacyPolicyLink()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 600)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickPrivacyAndPolicy.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("privacy"))
				{
					driver.switchTo().window(window);
					System.out.println("privacy policy page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.close();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkFacebookLink()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -300)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			clickFacebookLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("facebook"))
				{
					driver.switchTo().window(window);
					System.out.println("facebook page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.navigate().back();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkGoogleLink()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 200)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickGoogleLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("google"))
				{
					driver.switchTo().window(window);
					System.out.println("google page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.navigate().back();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkLinkedInLink()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 200)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickLinkedInLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("linkedin"))
				{
					driver.switchTo().window(window);
					System.out.println("linkedin page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.navigate().back();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkMicrosoftLink()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 200)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickMicrosoftLink.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("microsoftonline"))
				{
					driver.switchTo().window(window);
					System.out.println("Microsoft page opened");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.navigate().back();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
	public String checkSignUpTab()
	{
		
		String status = "fail";
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			Thread.sleep(2000);
			String parentWindow = driver.getWindowHandle();
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			clickSignUpTab.sendKeys(selectLinkOpeninNewTab);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			Set<String> allWindow = driver.getWindowHandles();
			for(String window : allWindow)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("register"))
				{
					driver.switchTo().window(window);
					System.out.println("sign up page opened from sign up tab");
					status = "pass";
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.navigate().back();
					driver.switchTo().window(parentWindow);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			// TODO: handle exception
		}
		return status;
	}
}

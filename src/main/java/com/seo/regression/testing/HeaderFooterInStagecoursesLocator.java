package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderFooterInStagecoursesLocator 
{
	WebDriver driver;
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li[class='Header_loginBtn__3Xv3A']>a")
	private WebElement clickLoginIcon;
	
	@FindBy(css = "div[class='TickerButton']>a") //Find out more
	private WebElement clickFindOutMoreIcon;
	
	@FindBy(css = "a[class='navbar-brand'] img") //skillup icon
	private WebElement clickSkillupIcon;
	
	@FindBy(css = "div[class='navbar-right NAVRiGhT']>ul[class='nav navbar-nav MobIlE_MenU']>li:nth-child(1)>a")
	private WebElement clickAboutSkillupIcon;
	
	@FindBy(css = "div[class='navbar-right NAVRiGhT']>ul[class='nav navbar-nav MobIlE_MenU']>li:nth-child(2)>a")
	private WebElement clickContactUSIcon;
	
	@FindBy(css = "div[class='navbar-right NAVRiGhT']>ul[class='nav navbar-nav MobIlE_MenU']>li:nth-child(3)>a")
	private WebElement clickBlogIcon;
	
	@FindBy(css = "ul[class=' socialIconsSection d-flex']>li:nth-child(1)>a")
	private WebElement clickTwitter;
	
	@FindBy(css = "ul[class=' socialIconsSection d-flex']>li:nth-child(2)>a")
	private WebElement clickFacebook;
	
	@FindBy(css = "ul[class=' socialIconsSection d-flex']>li:nth-child(3)>a")
	private WebElement clickLinkedIn;
	
	@FindBy(css = "ul[class=' socialIconsSection d-flex']>li:nth-child(4)>a")
	private WebElement clickInstagram;
	
	@FindBy(css = "ul[class=' socialIconsSection d-flex']>li:nth-child(5)>a")
	private WebElement clickYoutube;
	
	@FindBy(css = "div[class='ContActUsIn'] span")
	private WebElement clickContactUSFooter;
	
	@FindBy(css = "div[class='FootMenu'] li[class*='nav']>a")
	private List<WebElement> clickCompanyFooterIcons;
	
	
	public HeaderFooterInStagecoursesLocator(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String loginProcess()
	{
		String status = "fail";
		try
		{
			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
			clickLoginIcon.sendKeys(selectLinkOpeninNewTab);
			Set<String> windows = driver.getWindowHandles();
			for(String window : windows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("login"))
				{
					driver.switchTo().window(window);
					status = "pass";
					System.out.println("stagecourses site opened");
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
	public String FindOutMoreProcess(String data)
	{
		String status = "fail";
		try
		{
			if(clickFindOutMoreIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickFindOutMoreIcon).keyUp(Keys.CONTROL).build().perform();
				String ParentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("futureskills"))
					{
						driver.switchTo().window(windows);
						String hostURL[] = driver.getCurrentUrl().split("online/");
						String getCurrentURLAfterHost = hostURL[1];
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						
						driver.switchTo().window(ParentWindow);
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
	public String skillupIconProcess()
	{
		String status = "fail";
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickSkillupIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickSkillupIcon).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("login"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						System.out.println("skillup icon verified on header");
						driver.switchTo().window(parentWindow);
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
	public String AboutSkillupOnlineProcess(String data)
	{
		String status = "fail";
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			if(clickAboutSkillupIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickAboutSkillupIcon).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("about"))
					{
						driver.switchTo().window(windows);
						String[] hostURL = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" About skillup icon verified on header is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" About skillup icon verified on header is fails");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	
	public String ContactUsProcess(String data)
	{
		String status = "fail";
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			if(clickContactUSIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickContactUSIcon).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("contact"))
					{
						driver.switchTo().window(windows);
						String[] hostURL = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" contact icon verified on header is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" contact icon verified on header is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String BlogProcess(ArrayList<String> data)
	{
		String status = "fail";
		try
		{
			String stageSiteURL = "";
			String parentWindow = driver.getWindowHandle();
			if(clickBlogIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickBlogIcon).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("blog"))
					{
						driver.switchTo().window(windows);
						if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("qa-in"))
						{
							stageSiteURL = "https://dev.skillup.online/"+data.get(1);
						}
						else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("dev-in"))
						{
							stageSiteURL = "https://dev.skillup.online/"+data.get(1);
						}
						else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("prod-in"))
						{
							stageSiteURL = "https://skillup.online/"+data.get(1);
						}
						String[] hostURL = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" Blog icon verified on header is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" Blog icon verified on header is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	
	public String twitterProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickTwitter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickTwitter).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					Thread.sleep(1000);
					if(driver.getCurrentUrl().contains("x.com"))
					{
						Thread.sleep(1000);
						driver.switchTo().window(windows);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
						Thread.sleep(1000);
						if(data.equals(driver.getCurrentUrl()))
						{
							Thread.sleep(1000);
							status = "pass";
							System.out.println(" twitter icon verified on footer is pass");
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
						}
						else
						{
							status = driver.getCurrentUrl();
							System.out.println(" twitter icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String facebookProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickFacebook.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickFacebook).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("facebook"))
					{
						driver.switchTo().window(windows);
						String getCurrentURLAfterHost = driver.getCurrentUrl();
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" facebook icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" facebook icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String linkedInProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickLinkedIn.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickLinkedIn).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("linked"))
					{
						driver.switchTo().window(windows);
						String getCurrentURLAfterHost = driver.getCurrentUrl();
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" linkedIn icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" linkedIn icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String instagramProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickInstagram.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickInstagram).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("instagram"))
					{
						driver.switchTo().window(windows);
						String getCurrentURLAfterHost = driver.getCurrentUrl();
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" instagram icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" instagram icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String youtubeProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(clickYoutube.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickYoutube).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("youtube"))
					{
						driver.switchTo().window(windows);
						String getCurrentURLAfterHost = driver.getCurrentUrl();
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" youtube icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" youtube icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String contactUSProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			if(clickContactUSFooter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickContactUSFooter).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("contact"))
					{
						driver.switchTo().window(windows);
						String[] hostURL = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" contact icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" contact icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
	public String AboutSkillupOnlineFooterProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("About"))
				{
					Actions action = new Actions(driver);
					action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
					Set<String> allWindows = driver.getWindowHandles();
					for(String windows : allWindows)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains("about"))
						{
							driver.switchTo().window(windows);
							String hostURL[] = driver.getCurrentUrl().split("\\?utm");
							String getCurrentURLAfterHost = hostURL[0];
							if(stageSiteURL.equals(getCurrentURLAfterHost))
							{
								status = "pass";
								System.out.println(" About Skillup icon verified on footer is pass");
							}
							else
							{
								status = getCurrentURLAfterHost;
								System.out.println(" About Skillup icon verified on footer is fail");
							}
							driver.close();
							driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	public String SkillupOnlineForBusinessProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		String stageSiteURL ="";
		try
		{
			if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("qa-in"))
			{
				stageSiteURL = "https://qacourses-in.skillup.online"+data;
			}
			else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("dev-in"))
			{
				stageSiteURL = "https://devcourses-in.skillup.online"+data;
			}
			else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("prod-in"))
			{
				stageSiteURL = "https://in.skillup.online"+data;
			}
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("SkillUp Online for Business"))
				{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("enterprise"))
					{
						driver.switchTo().window(windows);
						String[] hostURL = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" business icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" business icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	
	public String FAQProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("FAQ"))
				{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("faq"))
					{
						driver.switchTo().window(windows);
						String hostURL[] = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" FAQ icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" FAQ icon verified on footer is fail");
						}
						
						driver.close();
						driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	public String PrivacyPolicyProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("Privacy"))
				{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("privacy"))
					{
						driver.switchTo().window(windows);
						String hostURL[] = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" Privacy icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" Privacy icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	public String TermsOfServiceProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			String stageSiteURL = OpenWebsite.setHost+"/"+data;
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("Terms"))
				{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("tos"))
					{
						driver.switchTo().window(windows);
						String hostURL[] = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" Terms of Service icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" Terms of Service icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	public String BlogFooterProcess(ArrayList<String> data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		String stageSiteURL = "";
		try
		{
			
			String parentWindow = driver.getWindowHandle();
			for(int i = 0; i < clickCompanyFooterIcons.size(); i++)
			{
				if(clickCompanyFooterIcons.get(i).getText().contains("Blog"))
				{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickCompanyFooterIcons.get(i)).keyUp(Keys.CONTROL).build().perform();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("blog"))
					{
						driver.switchTo().window(windows);
						String hostURL[] = driver.getCurrentUrl().split("\\?utm");
						String getCurrentURLAfterHost = hostURL[0];
						if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("qa-in"))
						{
							stageSiteURL = "https://dev.skillup.online/"+data.get(1);
						}
						else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("dev-in"))
						{
							stageSiteURL = "https://dev.skillup.online/"+data.get(1);
						}
						else if(RegressionTesting.ENV_TO_USE.equalsIgnoreCase("prod-in"))
						{
							stageSiteURL = "https://skillup.online/"+data.get(1);
						}
						if(stageSiteURL.equals(getCurrentURLAfterHost))
						{
							status = "pass";
							System.out.println(" Blog icon verified on footer is pass");
						}
						else
						{
							status = getCurrentURLAfterHost;
							System.out.println(" Blog icon verified on footer is fail");
						}
						driver.close();
						driver.switchTo().window(parentWindow);
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
		}
		return status;
	}
	
}

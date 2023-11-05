package com.seo.regression.testing;

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
	
	@FindBy(css = "a[class='navbar-brand']") //skillup icon
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
	
	@FindBy(css = "li[class='nav-item ']:nth-child(1)>a")
	private WebElement clickAboutSkillupOnlineFooter;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item']>a[class='yellTxt']")
	private WebElement clickSkillupOnlineForBusiness;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item ']:nth-child(3)>a")
	private WebElement clickPlacement;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item ']:nth-child(4)>a")
	private WebElement clickFAQ;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item ']:nth-child(5)>a")
	private WebElement clickPrivacyPolicy;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item ']:nth-child(6)>a")
	private WebElement clickTermsOfService;
	
	@FindBy(css = "div[class='FootMenu'] li[class='nav-item ']:nth-child(7)>a")
	private WebElement clickBlogFooter;
	
	
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
			clickLoginIcon.click();
			if(driver.getCurrentUrl().contains("stagecourses"))
			{
				status = "pass";
				System.out.println("stagecourses site opened");
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
				String parentWindow = driver.getWindowHandle();
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickSkillupIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickSkillupIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(!driver.getCurrentUrl().contains("stagecourses") && !driver.getCurrentUrl().contains("data:"))
					{
						driver.switchTo().window(windows);
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickAboutSkillupIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickAboutSkillupIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("about"))
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
						break;
					}
					else if(driver.getCurrentUrl().contains("utm"))
					{
						driver.switchTo().window(windows);
						status = driver.getCurrentUrl();
					}
					
				}
				driver.switchTo().window(parentWindow);
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
			if(clickContactUSIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickContactUSIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("contact"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public String BlogProcess(String data)
	{
		String status = "fail";
		try
		{
			if(clickBlogIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickBlogIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("blog"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickTwitter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickTwitter).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("twitter"))
					{
						driver.switchTo().window(windows);
						String getCurrentURLAfterHost = driver.getCurrentUrl();
						if(data.equals(getCurrentURLAfterHost))
						{
							status = "pass";
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickFacebook.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickFacebook).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
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
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickLinkedIn.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickLinkedIn).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
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
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickInstagram.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickInstagram).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
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
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickYoutube.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickYoutube).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
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
						}
						else
						{
							status = getCurrentURLAfterHost;
						}
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickContactUSFooter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickContactUSFooter).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("contact"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickAboutSkillupOnlineFooter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickAboutSkillupOnlineFooter).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("about"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
		try
		{
			if(clickSkillupOnlineForBusiness.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickSkillupOnlineForBusiness).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("enterprise"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public String PlacementProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			if(clickPlacement.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickPlacement).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("placement"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickFAQ.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickFAQ).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("faq"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickPrivacyPolicy.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickPrivacyPolicy).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("privacy"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
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
			if(clickTermsOfService.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickTermsOfService).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("tos"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public String BlogFooterProcess(String data)
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		try
		{
			if(clickBlogFooter.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickBlogFooter).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("blog"))
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
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
}

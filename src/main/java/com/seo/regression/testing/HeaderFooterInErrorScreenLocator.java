package com.seo.regression.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderFooterInErrorScreenLocator
{
	WebDriver driver;
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navButtons__3h4Rp']>li[class='Header_loginBtn__3Xv3A']>a")
	private WebElement clickLoginIcon;
	
	@FindBy(css = "div[class='TickerBanner_TickerButton__AIZB6']>a") //Find out more
	private WebElement clickFindOutMoreIcon;
	
	@FindBy(css = "div[class='navbar-brand']>a") //skillup icon
	private WebElement clickSkillupIcon;
	
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navLinks__aS6_P']>li:nth-child(1)>a")
	private WebElement clickAboutSkillupIcon;
	
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navLinks__aS6_P']>li:nth-child(2)>a")
	private WebElement clickContactUSIcon;
	
	@FindBy(css = "ul[class='list-unstyled navbar-nav nav Header_navLinks__aS6_P']>li:nth-child(3)>a")
	private WebElement clickBlogIcon;
	
	@FindBy(css = "ul[class=' Footer_socialIconsSection__5DztA d-flex']>li:nth-child(1)>a")
	private WebElement clickTwitter;
	
	@FindBy(css = "ul[class=' Footer_socialIconsSection__5DztA d-flex']>li:nth-child(2)>a")
	private WebElement clickFacebook;
	
	@FindBy(css = "ul[class=' Footer_socialIconsSection__5DztA d-flex']>li:nth-child(3)>a")
	private WebElement clickLinkedIn;
	
	@FindBy(css = "ul[class=' Footer_socialIconsSection__5DztA d-flex']>li:nth-child(4)>a")
	private WebElement clickInstagram;
	
	@FindBy(css = "ul[class=' Footer_socialIconsSection__5DztA d-flex']>li:nth-child(5)>a")
	private WebElement clickYoutube;
	
	@FindBy(css = "div[class='Footer_ContActUs__bZ1xZ']>a")
	private WebElement clickContactUSFooter;
	
	@FindBy(css = "li[class='nav-item ']:nth-child(1)>a")
	private WebElement clickAboutSkillupOnlineFooter;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li[class='nav-item']>a")
	private WebElement clickSkillupOnlineForBusiness;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li:nth-child(3)>a")
	private WebElement clickPlacement;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li:nth-child(4)>a")
	private WebElement clickFAQ;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li:nth-child(5)>a")
	private WebElement clickPrivacyPolicy;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li:nth-child(6)>a")
	private WebElement clickTermsOfService;
	
	@FindBy(css = "div[class='Footer_FootMenu__4fwEE'] ul>li:nth-child(7)>a")
	
	private WebElement clickBlogFooter;
	public HeaderFooterInErrorScreenLocator(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.get("https://stage-in.skillup.online/courses/deep/");
	}
	
	public String checkURL(String link)
	{
		String url = link;
		String urlStatus = "";
		try
		{
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");

			System.out.println("Request URL ... " + url);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}

			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");

				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");
										
				System.out.println("Redirect to URL : " + newUrl);

			}

			BufferedReader in = new BufferedReader(
		                              new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();

			System.out.println("Done");
			if(status>200)
			{
				urlStatus = "fail" + status;
			}
			else
			{
				urlStatus = "pass";
			}
		    } 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return urlStatus;
		
	}
	public String loginProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickLoginIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickLoginIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(driver.getCurrentUrl().contains("login"))
					{
						driver.switchTo().window(windows);
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String FindOutMoreProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickFindOutMoreIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
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
		String status = "";
		try
		{
			String checkURLStatus = clickSkillupIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(clickSkillupIcon).keyUp(Keys.CONTROL).build().perform();
				String parentWindow = driver.getWindowHandle();
				Set<String> allWindows = driver.getWindowHandles();
				for(String windows : allWindows)
				{
					driver.switchTo().window(windows);
					if(!driver.getCurrentUrl().contains("deep/") && !driver.getCurrentUrl().contains("data:"))
					{
						driver.switchTo().window(windows);
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String AboutSkillupOnlineProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickAboutSkillupIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String ContactUsProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickContactUSIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String BlogProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickBlogIcon.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String twitterProcess() throws InterruptedException
	{
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0,-400)", "");
		Thread.sleep(500);
		try
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
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String facebookProcess()
	{
		String status = "";
		try
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
						status = "pass";
						driver.close();
						
						break;
					}
				}
				driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String linkedInProcess()
	{
		String status = "";
		try
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
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String instagramProcess()
	{
		String status = "";
		try
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
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String youtubeProcess()
	{
		String status = "";
		try
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
						status = "pass";
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String contactUSProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickContactUSFooter.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String AboutSkillupOnlineFooterProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickAboutSkillupOnlineFooter.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String SkillupOnlineForBusinessProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickSkillupOnlineForBusiness.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String PlacementProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickPlacement.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String FAQProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickFAQ.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String PrivacyPolicyProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickPrivacyPolicy.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String TermsOfServiceProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickTermsOfService.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String BlogFooterProcess()
	{
		String status = "";
		try
		{
			String checkURLStatus = clickBlogFooter.getAttribute("href");
			String getURLStatus = this.checkURL(checkURLStatus);
			if(getURLStatus.equalsIgnoreCase("pass"))
			{
				status = "pass";
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
						driver.close();
						break;
					}
				}
				driver.switchTo().window(parentWindow);
			}
			else
			{
				status = "login Icon gets"+getURLStatus;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}

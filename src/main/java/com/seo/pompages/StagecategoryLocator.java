package com.seo.pompages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.regression.utility.TestUtil;
import com.seo.dataProvider.ConfigFileReader;
import com.seo.regression.testing.OpenWebsite;
import com.seo.regression.testing.RegressionTesting;

public class StagecategoryLocator
{

	WebDriver driver;
	WebDriverWait wait;
	URL parentURL;
	String setHost;
	String setLoginURL;
	String setMetaHost;
	public WebDriver getDriver()
	{
		return driver;
	}
	public void openDriver()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver123\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
	}
	
	public String setEnvironment(String host)
	{
		if(host.equalsIgnoreCase("prod-in"))
		{
			setHost = "https://in.skillup.online";
		}
		else if(host.equalsIgnoreCase("stage-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("qa-in"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("qa"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("stage"))
		{
			setHost = "https://"+host+".skillup.online";
		}
		else if(host.equalsIgnoreCase("prod"))
		{
			setHost = "https://skillup.online";
		}
		return setHost;
	}
	public String getCourseCodeText(String code)
	{
		String courseIDFromBrowser = "";
		String CourseCodeStatus = "false";
		HttpURLConnection huc = null;
		int respCode = 200;
		String addHosturl = this.setHost+code;
		try
		{
			huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println("status code : "+respCode + " " +addHosturl);
			if(respCode > 200 && !(respCode == 308))
			{
				System.out.println("broken link");
				System.exit(0);
			}
			else
			{
				System.out.println("un broken link");
				driver.get(addHosturl);
				WebElement checkCourseCode = driver.findElement(By.xpath("(//*[contains(@href,'"+code+"')])[1]"));
				courseIDFromBrowser = checkCourseCode.getAttribute("href");
				int index = courseIDFromBrowser.lastIndexOf("/");
				String getCourseID = courseIDFromBrowser.substring(index+0);
				getCourseID = getCourseID.replace("/", "");
				System.out.println("course ID from Browser : "+getCourseID);
				System.out.println("courseIDFrom Excel: "+code);
				if(getCourseID.contains(code))
				{
					CourseCodeStatus = "true";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return CourseCodeStatus;
	}
	public String setMetaHostURL(String metaHost)
	{
		setMetaHost = "https://skillup.online";
		setLoginURL = setHost;
		return setMetaHost;
	}
	
	String launchURL;
	
	public String getCourseURL(String currentURL)
	{
		return driver.getCurrentUrl();
	}

	public String launchCourseURL(String currentURL)
	{
		try
		{
			getCourseCodeText(currentURL);
			System.out.println("launched url : "+driver.getCurrentUrl());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver.getCurrentUrl();
	}
	
	public String getCanonicalURL(String canonicalURL)
	{
		String checkVPNStatus = "Fail";
		try
		{
			if(!driver.getCurrentUrl().contains("in."))
			{
				String addHost = this.setHost+canonicalURL;
				WebElement canonicalLocator = driver.findElement(By.cssSelector("link[rel='canonical']"));
				String getCanonicalURLText = canonicalLocator.getAttribute("href");
				if(addHost.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").equalsIgnoreCase(getCanonicalURLText.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "")))
				{
					System.out.println(getCanonicalURLText);
					checkVPNStatus = "success";
				}
				else
				{
					System.out.println("It is not CanonicalURL");
				}
			}
			else
			{
				checkVPNStatus = "success";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return checkVPNStatus;
	}
	
	public String getAttributeOfTag(String selector, String attribute)
	{
		String attributeValue = null;
		try
		{
			List<WebElement> tags = driver.findElements(By.cssSelector(selector));
			if(tags.size() > 1)
			{
				for(WebElement element: tags)
				{
					String value = element.getAttribute(attribute).replaceAll("\\s", "").replaceAll("\u00A0", "").trim();
					if(value != null && value != "")
					{
						attributeValue = value;
						break;
					}
				}
			}
			else if(tags.size() == 1)
			{
				attributeValue = tags.get(0).getAttribute(attribute).replaceAll("\\s", "").replaceAll("\u00A0", "").trim();
			}
			
			System.out.println(attributeValue);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return attributeValue;
	}
	
	public WebElement getTagWithInnerText(String htmlTag, String innerText)
	{
		WebElement tagToReturn = null;
		try
		{
			List<WebElement> tags = driver.findElements(By.cssSelector(htmlTag));
			for(WebElement tag: tags)
			{
				String text = tag.getAttribute("textContent").replaceAll("\\s", "").replaceAll("\u00A0", "");
				if(innerText.equalsIgnoreCase(text))
				{
					tagToReturn = tag;
					System.out.println(innerText);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tagToReturn;
	}	
	
	public ArrayList<String> getAnswersForFAQQuestion(String questionFromExcel) throws InterruptedException
	{
		ArrayList<String> ans = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement checkFAQ =  driver.findElement(By.xpath("//h2[contains(text(),'FAQ')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", checkFAQ);
		WebElement HeadOfFAQSelector = driver.findElement(By.cssSelector("div[class='container-fluid Accordion_containerInner__lXdjS'] div[class='col']>h2"));
		String getFAQHeadingText = HeadOfFAQSelector.getText();
		System.out.println(getFAQHeadingText);
		List<WebElement> listOfFAQ = driver.findElements(By.cssSelector("div[class='border-0 my-1 accordion-item']"));
		if(listOfFAQ.size()>0)
		{
			for(int i = 0; i < listOfFAQ.size(); i++)
			{
				WebElement question = listOfFAQ.get(i).findElement(By.cssSelector(" h2[class*='Accordion_accordionTitle']"));
				js.executeScript("arguments[0].scrollIntoView();", question);
				String questionText = question.getText();
				System.out.println("Question from browser : "+questionText);
				System.out.println("Question from Excel : "+questionFromExcel);
				if(questionText.replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\u00A0", "").equalsIgnoreCase(questionFromExcel.replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\u00A0", "")))
				{
					//question.click();
					List<WebElement> FAQAnswers = listOfFAQ.get(i).findElements(By.cssSelector("div[class*='accordion-collapse'] div[class*='Accordion_accordionBody'] div"));
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", question);
					for(int j = 0; j < FAQAnswers.size(); j++)
					{
							String answer = FAQAnswers.get(j).getAttribute("textContent");
							ans.add(answer);
							break;
					}
				}
			}
		}
		else
		{
			System.out.println("Excel Data of FAQ is not same as UI");
		}
		return ans;
	}
	public String checkLink(String data)
	{
		String CourseStatus = "fail";
		int respCode = 200;
		String endURL = data;
		HttpURLConnection huc;
		String addHosturl = endURL;
		try
		{
			huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println("status code : "+respCode + " " +addHosturl);
			if(respCode > 200)
			{
				System.out.println("broken link"+addHosturl);
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				
				  ((JavascriptExecutor) driver).executeScript("window.open('"+addHosturl+"')");
				  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70)); 
				  String parentWindow = driver.getWindowHandle(); 
				  ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles()); 
				  for(String windows : w)
				  {
					  driver.switchTo().window(windows);
						  if(driver.getCurrentUrl().contains(endURL))
						  {
						  driver.switchTo().window(windows);
						  System.out.println("current url : "+driver.getCurrentUrl()); 
						  CourseStatus =  "pass"; 
						  driver.close(); 
						  } 
						  else if(driver.getCurrentUrl().contains("data"))
						  { 
							  driver.close(); 
						  } 
				 } 
				  driver.switchTo().window(parentWindow);
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
				CourseStatus = "fail";
			}
		return CourseStatus;
	}
	
	String addPgmIcon;
	ArrayList<String> pgmCardType = new ArrayList<String>();
	String enrollStatus;
	String enrollAmount;
	
	
	String getPgmIcon;
	ArrayList<String> getPgmLevels = new ArrayList<String>();
	String pgmEnrollStatus;
	public ArrayList<String> verifycourseCardDetailsInProgramPage(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String programName[] = dataFromExcel.get(1).split("\\?");
			String pgmName = programName[1];
			System.out.println(pgmName);
			
			List<WebElement> pgms = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='LearningCatalog_customCard'] div[class*='FlatCourseCard_FlatcardLinks'] a"));
			for(int i = 0; i < pgms.size(); i++)
			{
				String programLink = pgms.get(i).getAttribute("href");
				if(pgmName.contains(pgms.get(i).findElement(By.cssSelector(" h2")).getText()))
				{
					status.add(this.checkLink(programLink));
					
					Actions a= new Actions(driver);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					a.sendKeys(Keys.HOME).build().perform();
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					
					
					WebElement pgmCardIconLocator = pgms.get(i).findElement(By.cssSelector(" img[alt='Course-Image']"));
					if(pgmCardIconLocator.isDisplayed())
					{
						addPgmIcon = "present";
					}
					
					List<WebElement> pgmCardTypeLocator = pgms.get(i).findElements(By.cssSelector(" div[class*='FlatCourseCard_propertiesList'] ul li"));
					for(int j = 0; j < pgmCardTypeLocator.size(); j++)
					{
						pgmCardType.add(pgmCardTypeLocator.get(j).getText());
					}
					
					WebElement enrollStatusLocator = pgms.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_courseStartSection'] h4"));
					enrollStatus = enrollStatusLocator.getText();
					
					WebElement enrollAmountLocator = pgms.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_priceSection'] h3"));
					enrollAmount = enrollAmountLocator.getText().replaceAll("[^0-9]", " ").replaceAll("\\s", "").trim();
					System.out.println("program card amount : "+enrollAmount);
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					
					((JavascriptExecutor) driver).executeScript("window.open('"+programLink+"')");
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					
					String parentWindow = driver.getWindowHandle();
					ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());
					for(String windows : w)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains(programLink))
						{
							driver.switchTo().window(windows);
							System.out.println("current url : "+driver.getCurrentUrl());
							
							if(driver.findElements(By.cssSelector("img[alt='course-icon']")).size()>=1)
							{
								WebElement prgIcon = driver.findElement(By.cssSelector("img[alt='course-icon']"));
								if(prgIcon.isDisplayed())
								{
									getPgmIcon = "present";
								}
							}
							
							List<WebElement> programLevel = driver.findElements(By.cssSelector("div[class*='CourseDescription_levelSection'] [class*='text-uppercase CourseDescription_regular']"));
							for(int k = 0; k < programLevel.size(); k++)
							{
								getPgmLevels.add(programLevel.get(k).getText());
							}
							
							if(driver.findElements(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']")).size() != 0)
							{
								WebElement enrollButtonLocator = driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']"));
								pgmEnrollStatus = enrollButtonLocator.getText();
								System.out.println("program enroll status : "+pgmEnrollStatus);
							}
							else if(driver.findElements(By.cssSelector("div[class*='CourseDescription_buttonsContent'] h6")).size() != 0)
							{
								WebElement enrollButtonStatus = driver.findElement(By.cssSelector("div[class*='CourseDescription_buttonsContent'] h6"));
								pgmEnrollStatus = enrollButtonStatus.getText();
								System.out.println("program enroll status : "+pgmEnrollStatus);
							}
							WebElement amountLocator = driver.findElement(By.cssSelector("div[class*='CourseDescription_durationAndPriceSection'] div[class='d-flex gap-2']:nth-child(3) div[class*='CourseDescription_courseAboutTextSection'] p"));
							String amount = amountLocator.getText().replaceAll("[^0-9]", " ").replaceAll("\\s", "").trim();
							
							System.out.println("program page enroll amount :"+amount);
							if(addPgmIcon.contains(getPgmIcon))
							{
								status.add("pass");
								System.out.println("prgm icon is same in both prg card and prg page for "+programLink);	
							}
							else
							{
								status.add("icon");
								System.out.println("prgm icon is not same in both prg card and prg page for "+programLink);	
							}
							if(pgmCardType.toString().toUpperCase().equalsIgnoreCase(getPgmLevels.toString().toUpperCase()))
							{
								status.add("pass");
								System.out.println("prgm level type is same in both prg card and prg page for "+programLink);
							}
							else
							{
								status.add("level");
								System.out.println("prgm level type is not same in both prg card and prg page for "+programLink);
							}
							if((enrollStatus.contains("Open") && pgmEnrollStatus.contains("Enroll Now")))
							{
								status.add("pass");
								System.out.println("prgm enroll status is same in both prg card and prg page for "+programLink);
							}
							else
							{
								status.add("enrollStatus");
								System.out.println("prgm enroll is not same in both prg card and prg page for "+programLink);
							}
							if(enrollAmount.equalsIgnoreCase(amount))
							{
								status.add("pass");
								System.out.println("prgm enroll amount is same in both prg card and prg page for "+programLink);
								driver.close();
								break;
								
							}
							else
							{
								status.add("amount");
								System.out.println("prgm enroll amount is not same in both prg card and prg page for "+programLink);
								break;
							}
							}
							else if(driver.getCurrentUrl().contains("data"))
							{
								driver.close();
							}
							
						}
						driver.switchTo().window(parentWindow);
						break;
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		return status;
	}
	
	
	public ArrayList<String> verifyCourseCardDetailsInCoursePage(ArrayList<String> data)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		ArrayList<String> status = new ArrayList<String>();
		ArrayList<String> courseCardType = new ArrayList<String>();
		ArrayList<String> getCourseLevels = new ArrayList<String>();
		
		String courseCardIcon = null, getCourseIcon = null, courseCardenrollStatus, courseEnrollStatus = null, courseCardEnrollAmount;
		try
		{
			String courseFromExcel[] = data.get(1).split("\\?");
			String courseName = courseFromExcel[1];
			List<WebElement> course = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks'] a"));
			for(int i = 0; i < course.size(); i++)
			{
				System.out.println("courses : "+course.get(i).getAttribute("href"));
				if(courseName.contains(course.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading'] p")).getText()))
				{
					String courseLink = course.get(i).getAttribute("href");
					status.add(this.checkLink(courseLink));
					Actions a= new Actions(driver);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					a.sendKeys(Keys.HOME).build().perform();
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					
					WebElement courseCardIconLocator = course.get(i).findElement(By.cssSelector(" img[alt='icon']"));
					if(courseCardIconLocator.isDisplayed())
					{
						courseCardIcon = "present";
					}
					
					List<WebElement> courseCardTypeLocator = course.get(i).findElements(By.cssSelector(" div[class*='RegularCourseCard_courseHeading'] ul li"));
					for(int j = 0; j < courseCardTypeLocator.size(); j++)
					{
						courseCardType.add(courseCardTypeLocator.get(j).getText());
					}
					
					WebElement enrollStatusLocator = course.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceLeft'] p"));
					courseCardenrollStatus = enrollStatusLocator.getText();
					
					WebElement enrollAmountLocator = course.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceRight'] p"));
					courseCardEnrollAmount = enrollAmountLocator.getText().replaceAll("[^0-9]", " ").replaceAll("\\s", "").trim();
					System.out.println("course card amount : "+courseCardEnrollAmount);
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					
					((JavascriptExecutor) driver).executeScript("window.open('"+courseLink+"')");
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					
					String parentWindow = driver.getWindowHandle();
					ArrayList<String> w = new ArrayList<String>(driver.getWindowHandles());
					for(String windows : w)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains(courseLink))
						{
							driver.switchTo().window(windows);
							System.out.println("current url : "+driver.getCurrentUrl());
							
							if(driver.findElements(By.cssSelector("img[alt='course-icon']")).size()>=1)
							{
								WebElement courseIcon = driver.findElement(By.cssSelector("img[alt='course-icon']"));
								if(courseIcon.isDisplayed())
								{
									getCourseIcon = "present";
								}
							}
							
							List<WebElement> courseLevel = driver.findElements(By.cssSelector("div[class*='CourseDescription_levelSection'] [class*='text-uppercase CourseDescription_regularTheme']"));
							for(int k = 0; k < courseLevel.size(); k++)
							{
								getCourseLevels.add(courseLevel.get(k).getText());
							}
							
							if(driver.findElements(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']")).size() != 0)
							{
								WebElement enrollButtonLocator = driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']"));
								courseEnrollStatus = enrollButtonLocator.getText();
								System.out.println("course page enroll status : "+courseEnrollStatus);
							}
							else if(driver.findElements(By.cssSelector("div[class*='CourseDescription_buttonsContent'] h6")).size() != 0)
							{
								WebElement enrollButtonLocator = driver.findElement(By.cssSelector("div[class*='CourseDescription_buttonsContent'] h6"));
								courseEnrollStatus = enrollButtonLocator.getText();
								System.out.println("course page enroll status : "+courseEnrollStatus);
							}
							String courseAmount = null;
							WebElement amountLocator = driver.findElement(By.xpath("//div[@class='d-flex gap-2']//h2[contains(text(),'Fee')]/following-sibling::p"));
							if(amountLocator.getText().contains("-"))
							{
								String temp[] = amountLocator.getText().split("\\-");//7399-14999
								courseAmount = temp[0].replaceAll("[^0-9]", " ").replaceAll("\\s", "").trim();
							}
							else
							{
								courseAmount = amountLocator.getText().replaceAll("[^0-9]", " ").replaceAll("\\s", "").trim();
							}
							System.out.println("course page enroll amount :"+courseAmount);
							if(courseCardIcon.equalsIgnoreCase(getCourseIcon))
							{
								status.add("pass");
								System.out.println("course icon is same in both prg card and prg page for "+courseLink);	
							}
							else
							{
								status.add("icon");
								System.out.println("course icon is not same in both prg card and prg page for "+courseLink);	
							}
							if(courseCardType.toString().toUpperCase().equalsIgnoreCase(getCourseLevels.toString().toUpperCase()))
							{
								status.add("pass");
								System.out.println("course level type is same in both prg card and prg page for "+courseLink);
							}
							else
							{
								status.add("level");
								System.out.println("course level type is not same in both prg card and prg page for "+courseLink);
							}
							if((courseCardenrollStatus.contains("Open") && courseEnrollStatus.contains("Enroll Now")))
							{
								status.add("pass");
								System.out.println("course enroll status is same in both prg card and prg page for "+courseLink);
							}
							else
							{
								status.add("enrollStatusclosed");
								System.out.println("course enroll is not same in both prg card and prg page for "+courseLink);
							}
							if(courseCardEnrollAmount.equalsIgnoreCase(courseAmount))
							{
								status.add("pass");
								System.out.println("prgm enroll amount is same in both prg card and prg page for "+courseLink);
								driver.close();
								break;
								
							}
							else
							{
								status.add("amount");
								System.out.println("prgm enroll amount is not same in both prg card and prg page for "+courseLink);
								break;
							}
							}
							else if(driver.getCurrentUrl().contains("data"))
							{
								driver.close();
							}
							
						}
						driver.switchTo().window(parentWindow);
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
	
	public ArrayList<String> verifyProgram()
	{
		ArrayList<String> status = new ArrayList<String>();
		ArrayList<String> cardDataStatus = new ArrayList<String>();
		ArrayList<String> PageDataStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement clickShowMore = driver.findElement(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[3]//button[contains(text(),'Show more')]"));
		while(clickShowMore.isDisplayed() != clickShowMore.getText().equalsIgnoreCase("Show less"))
		{
			js.executeScript("arguments[0].click()", clickShowMore);
		}
		
		try
		{
			
			List<WebElement> programCards = driver.findElements(By.cssSelector("div[class='row']:nth-child(3) div[class*='LearningCatalog_cardRow']>div"));
			for(int i = 0; i < programCards.size(); i++)
			{
				Thread.sleep(1000);
				js.executeScript("arguments[0].scrollIntoView();", programCards.get(i));
				
				WebElement programCardIcon = programCards.get(i).findElement(By.cssSelector(" img[alt='Course-Image']"));
				js.executeScript("arguments[0].scrollIntoView();", programCardIcon);
				if(programCardIcon.isDisplayed())
				{
					cardDataStatus.add("Program");
				}
				else
				{
					cardDataStatus.add("noIcon");
				}
				Thread.sleep(200);
				WebElement cardType = programCards.get(i).findElement(By.cssSelector(" h3[class*='FlatCourseCard_courseType']"));
				js.executeScript("arguments[0].scrollIntoView();", cardType);
				if(cardType.isDisplayed())
				{
					
					cardDataStatus.add(cardType.getText().toLowerCase().replaceAll("\\s", "").trim());
				}
				else
				{
					cardDataStatus.add("noCardType");
				}
				Thread.sleep(200);
				WebElement programCardName = programCards.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_courseDescription']"));
				js.executeScript("arguments[0].scrollIntoView();", programCardName);
				if(programCardName.isDisplayed())
				{
					cardDataStatus.add(programCardName.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
				}
				else
				{
					cardDataStatus.add("noName");
				}
				Thread.sleep(200);
				WebElement programCardLevel = programCards.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_propertiesList']"));
				js.executeScript("arguments[0].scrollIntoView();", programCardLevel);
				if(programCardLevel.isDisplayed())
				{
					
					cardDataStatus.add(programCardLevel.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
				}
				else
				{
					cardDataStatus.add("noLevel");
				}
				Thread.sleep(200);
				WebElement programCardPartner = programCards.get(i).findElement(By.cssSelector(" h6[class*='FlatCourseCard_companyTitle']"));
				
				if(programCardPartner.isDisplayed())
				{
					cardDataStatus.add("partnerPresent");
				}
				else
				{
					cardDataStatus.add("noPartner");
				}
				Thread.sleep(200);
				ArrayList<String> getEnrollFromCard = new ArrayList<String>();
				
				WebElement programCardEnrollStatus = programCards.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_courseStartSection']>h4"));
				js.executeScript("arguments[0].scrollIntoView();", programCardEnrollStatus);
				if(programCardEnrollStatus.isDisplayed())
				{
					getEnrollFromCard.add(programCardEnrollStatus.getText().replaceAll("\\s", "").trim());
					cardDataStatus.add(programCardEnrollStatus.getText().replaceAll("\\s", "").trim());
				}
				else
				{
					cardDataStatus.add("noEnroll");
				}
				Thread.sleep(200);
				
				WebElement programCardAmount = programCards.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_priceSection']>h3"));
				js.executeScript("arguments[0].scrollIntoView();", programCardAmount);
				if(programCardAmount.isDisplayed())
				{
					
					String val = programCardAmount.getText();
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(val);
					StringBuilder build = new StringBuilder();
					while(matcher.find())
					{
						build.append(matcher.group());
					}
					String result = build.toString();
					
					cardDataStatus.add(result);
				}
				else
				{
					cardDataStatus.add("noAmount");
				}
				Thread.sleep(200);
				String parentWindow = driver.getWindowHandle();
				
				WebElement programCardLink = programCards.get(i).findElement(By.cssSelector(" a"));
				js.executeScript("arguments[0].scrollIntoView();", programCardLink);
					
				String programCardLinkText = programCardLink.getAttribute("href");
				
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(programCardLinkText);
				String currentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(window.contains(currentWindow))
					{
						driver.switchTo().window(window);
						ArrayList<String> getPartner = new ArrayList<String>();
						WebElement baseLocator = driver.findElement(By.cssSelector("section[class*='CourseDescription_mainSection']"));
						js.executeScript("arguments[0].scrollIntoView();", baseLocator);
						
						Thread.sleep(200);
						WebElement programPageIcon = baseLocator.findElement(By.cssSelector(" img[alt='Loader'],img[alt='course-icon']"));
						js.executeScript("arguments[0].scrollIntoView();", programPageIcon);
						if(programPageIcon.isDisplayed())
						{
							PageDataStatus.add("Program");
						}
						else
						{
							PageDataStatus.add("noIcon");
						}
						
						Thread.sleep(200);
						WebElement programPageType = baseLocator.findElement(By.cssSelector(" h4[class*='CourseDescription_courseLabel']"));
						js.executeScript("arguments[0].scrollIntoView();", programPageType);
						if(programPageType.isDisplayed())
						{
							PageDataStatus.add(programPageType.getText().toLowerCase().replaceAll("\\s", "").trim());
						}
						else
						{
							PageDataStatus.add("noType");
						}
						
						Thread.sleep(200);
						WebElement programPageName = baseLocator.findElement(By.cssSelector(" h1"));
						js.executeScript("arguments[0].scrollIntoView();", programPageName);
						if(programPageName.isDisplayed())
						{
							PageDataStatus.add(programPageName.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
						}
						else
						{
							PageDataStatus.add("noName");
						}
						Thread.sleep(200);
						WebElement programPageLevel = baseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
						js.executeScript("arguments[0].scrollIntoView();", programPageLevel);
						if(programPageLevel.isDisplayed())
						{
							PageDataStatus.add(programPageLevel.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
						}
						else
						{
							PageDataStatus.add("noLevel");
						}
						Thread.sleep(200);
						WebElement programPagePartner = baseLocator.findElement(By.cssSelector(" img[alt='org-logo'],img[alt='Skillup']"));
							
						if(programPagePartner.isDisplayed())
						{
							PageDataStatus.add("partnerPresent");
						}
						else
						{
							PageDataStatus.add("noPartner");
						}
						Thread.sleep(200);
						List<WebElement> programPageEnrollStatus = baseLocator.findElements(By.cssSelector(" button[class*='CourseDescription_enrollNowBtn']"));
						if(programPageEnrollStatus.size() == 1)
						{
							String enrollStatus = programPageEnrollStatus.get(0).getText().replaceAll("\\s", "").trim();
							if(enrollStatus.equalsIgnoreCase("EnrollNow"))
							{
								PageDataStatus.add("Open");
							}
							else
							{
								PageDataStatus.add("close");
							}
						}
						else
						{
							ArrayList<String> getEnrollFromPage = new ArrayList<String>();
							ArrayList<String> getEnrollStatus = new ArrayList<String>();
							List<WebElement> programPageCohort = driver.findElements(By.cssSelector("div#Cohort>div[class*='CourseDescription_CohortBoxDiv']>button"));
							if(programPageCohort.size()>0)
							{
								for(int j = 0; j< programPageCohort.size(); j++ )
								{
									getEnrollFromPage.add(programPageCohort.get(j).getText());//store enroll status in one variable
								}
								for(int r = 0; r < getEnrollFromPage.size(); r++) // check all enroll status
								{
									if(getEnrollFromPage.get(r).equalsIgnoreCase("Enroll now"))
									{
										getEnrollStatus.add("Open");
									}
									else if(getEnrollFromPage.get(r).contains("close"))
									{
										getEnrollStatus.add("closed");
									}
								}
								
								if(getEnrollStatus.contains("closed"))
								{
									PageDataStatus.add("close");
								}
								else
								{
									PageDataStatus.add("Open");
								}
									
						    }
						}
							Thread.sleep(200);
						WebElement programPageAmount = baseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_durationAndPriceSection']>div:last-child p"));
						js.executeScript("arguments[0].scrollIntoView();", programPageAmount);
						if(programPageAmount.isDisplayed())
						{
							if(programPageAmount.getText().contains("-"))
							{
								Thread.sleep(1000);
								String price[] = programPageAmount.getText().split("-");
								String val = price[0];
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
									Thread.sleep(1000);
								}
								String result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
								PageDataStatus.add(result);
							
							}
							else
							{

								Thread.sleep(1000);
								String val = programPageAmount.getText();
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
									Thread.sleep(1000);
								}
								String result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
								PageDataStatus.add(result);
								Thread.sleep(1000);
							
							}
						}
						else
						{
							PageDataStatus.add("noAmount");
						}
						Thread.sleep(200);
						if(!cardDataStatus.equals(PageDataStatus))
						{
							for(int k = 0; k < cardDataStatus.size(); k++)
							{
								if(k == 0)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Icon mismatch in "+programCardLinkText);
									}
								}
								if(k == 1)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Type mismatch in "+programCardLinkText);
									}
								}
								if(k == 2)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Name mismatch in "+programCardLinkText);
									}
								}
								if(k == 3)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Level mismatch in "+programCardLinkText);
									}
								}
								if(k == 4)
								{
									
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" partner not present "+programCardLinkText);
									}
								}
								if(k == 5)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Enroll mismatch in "+programCardLinkText);
									}
								}
								if(k == 6)
								{
									if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
									{
										status.add(" Amount mismatch in "+programCardLinkText);
									}
								}
							}
						}
						Thread.sleep(200);
						cardDataStatus.clear();
						PageDataStatus.clear();
						driver.close();
						driver.switchTo().window(parentWindow);
					}
					driver.switchTo().window(parentWindow);
				}
				driver.switchTo().window(parentWindow);
					
				Thread.sleep(200);
				System.out.println("process done for : "+programCardLinkText);
				Thread.sleep(200);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String checkCourseCode(String getURL)
	{
		int status = 0;
		String getstatus = "pass";
		String url="";
		int respCode = 200;
		HttpURLConnection huc = null;
		String addHosturl = "";
			String endURL = getURL;
			if(endURL.contains("enterprise"))
			{	
				if(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE).contains("stage"))
				{
					url = /* "https://stagecourses-in.skillup.online"+ */endURL;
					addHosturl = url;
				}
			}
			else
			{
				url = /* OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+ */endURL;
				addHosturl = url;
			}
			try
			{

				URL obj = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
				conn.setReadTimeout(5000);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");

				System.out.println("Request URL ... " + addHosturl);

				boolean redirect = false;

				// normally, 3xx is redirect
			status = conn.getResponseCode();
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

					while ((inputLine = in.readLine()) != null) 
					{
						html.append(inputLine);
					}
					in.close();
	
					System.out.println("Done");
					if(status>200)
					{
						getstatus = addHosturl+"fail" + status;
					}
			    } 
			catch (Exception e) 
			{
				e.printStackTrace();
				getstatus = "fail" + status;
			}

			
		return getstatus;
	}
	public ArrayList<String> verifyCourses()
	{
		ArrayList<String> status = new ArrayList<String>();
		ArrayList<String> cardDataStatus = new ArrayList<String>();
		ArrayList<String> PageDataStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement clickShowMore = driver.findElement(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[5]//button[contains(text(),'Show more')] | //div[@class='container-fluid Courses_containerInner__mZQhO']/div[2]//button[contains(text(),'Show more')]"));
		js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
		while(clickShowMore.isDisplayed())
		{
			js.executeScript("arguments[0].click()", clickShowMore);
			
			if(clickShowMore.getText().equalsIgnoreCase("Show less"))
			{
				break;
			}
		}
		
		try
		{
			String value = "";
			String result = "";
			List<WebElement> courseCards = driver.findElements(By.xpath("//div[contains(@class,'row')][2]//div[contains(@class,'LearningCatalog_cardRow')]/div | //div[contains(@class,'row')][5]//div[contains(@class,'LearningCatalog_cardRow')]/div"));
			for(int i = 0; i < courseCards.size(); i++)
			{
				Thread.sleep(1000);
				js.executeScript("arguments[0].scrollIntoView();", courseCards.get(i));
				
				WebElement courseCardLink = courseCards.get(i).findElement(By.cssSelector(" a"));
				js.executeScript("arguments[0].scrollIntoView();", courseCardLink);
					
				String courseCardLinkText = courseCardLink.getAttribute("href");
				
				String urlLink = this.checkCourseCode(courseCardLinkText);
				
				if(urlLink.contains("fail"))
				{
					status.add(courseCardLinkText + "fail");
				}
				else
				{
					WebElement courseCardIcon = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseType'] img[alt='icon']"));
					js.executeScript("arguments[0].scrollIntoView();", courseCardIcon);
					if(courseCardIcon.isDisplayed())
					{
						cardDataStatus.add("CourseIcon");
					}
					else
					{
						cardDataStatus.add("noIcon");
					}
					Thread.sleep(200);
					WebElement cardType = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseType'] p"));
					js.executeScript("arguments[0].scrollIntoView();", cardType);
					if(cardType.isDisplayed())
					{
						
						cardDataStatus.add(cardType.getText().toLowerCase().replaceAll("\\s", "").trim());
					}
					else
					{
						cardDataStatus.add("noCardType");
					}
					Thread.sleep(200);
					WebElement courseCardName = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>p"));
					js.executeScript("arguments[0].scrollIntoView();", courseCardName);
					if(courseCardName.isDisplayed())
					{
						cardDataStatus.add(courseCardName.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
					}
					else
					{
						cardDataStatus.add("noName");
					}
					Thread.sleep(200);
					WebElement courseCardLevel = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>ul"));
					js.executeScript("arguments[0].scrollIntoView();", courseCardLevel);
					if(courseCardLevel.isDisplayed())
					{
						
						cardDataStatus.add(courseCardLevel.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
					}
					else
					{
						cardDataStatus.add("noLevel");
					}
					Thread.sleep(200);
					WebElement courseCardPartner = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseCompany']"));
					
					if(courseCardPartner.isDisplayed())
					{
						cardDataStatus.add("partnerPresent");
					}
					else
					{
						cardDataStatus.add("noPartner");
					}
					Thread.sleep(200);
					ArrayList<String> getEnrollFromCard = new ArrayList<String>();
					
					WebElement courseCardEnrollStatus = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceLeft']>p"));
					js.executeScript("arguments[0].scrollIntoView();", courseCardEnrollStatus);
					
					if(courseCardEnrollStatus.isDisplayed())
					{
						value = courseCardEnrollStatus.getText().replaceAll("\\s", "").trim();
						//getEnrollFromCard.add(courseCardEnrollStatus.getText().replaceAll("\\s", "").trim());
						//cardDataStatus.add(courseCardEnrollStatus.getText().replaceAll("\\s", "").trim());
						LocalDate date = parseDate(value);
						if(date != null)
						{
							cardDataStatus.add(value.replaceAll("\\s", "").trim());
							getEnrollFromCard.add("date");
						}
						else
						{
							if("Open".equalsIgnoreCase(value))
							{
								System.out.println("open status");
								cardDataStatus.add(value);
								getEnrollFromCard.add("nodate");
							}
							else if(value.contains("close"))
							{
								cardDataStatus.add(value);
								getEnrollFromCard.add("nodate");
							}
							else
							{
								cardDataStatus.add(value.replaceAll("\\s", "").trim());
								getEnrollFromCard.add("date");
							}
							
						}
					}
					else
					{
						cardDataStatus.add("noEnroll");
						getEnrollFromCard.add("nodate");
					}
					Thread.sleep(200);
					
					WebElement courseCardAmount = courseCards.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceRight']>p"));
					js.executeScript("arguments[0].scrollIntoView();", courseCardAmount);
					
					if(courseCardAmount.isDisplayed())
					{
						if(courseCardAmount.getText().contains("-"))
						{
							Thread.sleep(1000);
							String price[] = courseCardAmount.getText().split("-");
							String val = price[0];
							Pattern pattern = Pattern.compile("\\d+");
							Matcher matcher = pattern.matcher(val);
							StringBuilder build = new StringBuilder();
							while(matcher.find())
							{
								build.append(matcher.group());
								Thread.sleep(1000);
							}
							result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
							cardDataStatus.add(result);
						
						}
						else
						{

							Thread.sleep(1000);
							String val = courseCardAmount.getText();
							Pattern pattern = Pattern.compile("\\d+");
							Matcher matcher = pattern.matcher(val);
							StringBuilder build = new StringBuilder();
							while(matcher.find())
							{
								build.append(matcher.group());
								Thread.sleep(1000);
							}
							result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
							cardDataStatus.add(result);
							Thread.sleep(1000);
						
						}
					}
					else
					{
						cardDataStatus.add("noAmount");
					}
					
					Thread.sleep(200);
					String parentWindow = driver.getWindowHandle();
					
					
					
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(courseCardLinkText);
					String currentWindow = driver.getWindowHandle();
					Set<String> windows = driver.getWindowHandles();
					for(String window : windows)
					{
						driver.switchTo().window(window);
						if(window.contains(currentWindow))
						{
							driver.switchTo().window(window);
							
							ArrayList<String> getPartner = new ArrayList<String>();
							
							WebElement baseLocator = driver.findElement(By.cssSelector("section[class*='CourseDescription_mainSection']"));
							js.executeScript("arguments[0].scrollIntoView();", baseLocator);
							
							Thread.sleep(200);
							WebElement coursePageIcon = baseLocator.findElement(By.cssSelector(" img[alt='Loader'],img[alt='course-icon']"));
							js.executeScript("arguments[0].scrollIntoView();", coursePageIcon);
							if(coursePageIcon.isDisplayed())
							{
								PageDataStatus.add("CourseIcon");
							}
							else
							{
								PageDataStatus.add("noIcon");
							}
							
							Thread.sleep(200);
							WebElement coursePageType = baseLocator.findElement(By.cssSelector(" h4[class*='CourseDescription_courseLabel']"));
							js.executeScript("arguments[0].scrollIntoView();", coursePageType);
							if(coursePageType.isDisplayed())
							{
								PageDataStatus.add(coursePageType.getText().toLowerCase().replaceAll("\\s", "").trim());
							}
							else
							{
								PageDataStatus.add("noType");
							}
							
							Thread.sleep(200);
							WebElement coursePageName = baseLocator.findElement(By.cssSelector(" h1"));
							js.executeScript("arguments[0].scrollIntoView();", coursePageName);
							if(coursePageName.isDisplayed())
							{
								PageDataStatus.add(coursePageName.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
							}
							else
							{
								PageDataStatus.add("noName");
							}
							Thread.sleep(200);
							WebElement coursePageLevel = baseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
							js.executeScript("arguments[0].scrollIntoView();", coursePageLevel);
							if(coursePageLevel.isDisplayed())
							{
								PageDataStatus.add(coursePageLevel.getText().toLowerCase().replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "").trim());
							}
							else
							{
								PageDataStatus.add("noLevel");
							}
							Thread.sleep(200);
							WebElement coursePagePartner = baseLocator.findElement(By.cssSelector(" img[alt='org-logo'],img[alt='Skillup']"));
								
							if(coursePagePartner.isDisplayed())
							{
								PageDataStatus.add("partnerPresent");
							}
							else
							{
								PageDataStatus.add("noPartner");
							}
							Thread.sleep(200);
							if(getEnrollFromCard.contains("date"))
							{
								List<WebElement> startDate = driver.findElements(By.cssSelector("div[class*='CourseDescription_durationAndPriceSection']>div:nth-child(1) p"));
								if(startDate.size()>0)
								{
									WebElement dateStatus = driver.findElement(By.cssSelector("div[class*='CourseDescription_durationAndPriceSection']>div:nth-child(1) p"));
									PageDataStatus.add(dateStatus.getText().replaceAll("\\s", "").trim());

								}
								else
								{
									System.out.println("VILT course so date not shown");
									PageDataStatus.add(value.replaceAll("\\s", "").trim());
								}
							}
							else
							{
								List<WebElement> coursePageEnrollStatus = baseLocator.findElements(By.cssSelector(" button[class*='CourseDescription_enrollNowBtn'],div[class*='CourseDescription_buttonsContent']>h6"));
								if(coursePageEnrollStatus.size() == 1)
								{
									String enrollStatus = baseLocator.findElement(By.cssSelector(" button[class*='CourseDescription_enrollNowBtn'],div[class*='CourseDescription_buttonsContent']>h6")).getText().replaceAll("\\s", "").trim();
									if(enrollStatus.equalsIgnoreCase("EnrollNow"))
									{
										PageDataStatus.add("Open");
									}
									else if(enrollStatus.equalsIgnoreCase("Enrollment is Closed".replaceAll("\\s", "").trim()))
									{
										PageDataStatus.add("close");
									}
								}
								else
								{
									ArrayList<String> getEnrollFromPage = new ArrayList<String>();
									ArrayList<String> getEnrollStatus = new ArrayList<String>();
									List<WebElement> coursePageCohort = driver.findElements(By.cssSelector("div#Cohort>div[class*='CourseDescription_CohortBoxDiv']>button"));
									if(coursePageCohort.size()>0)
									{
										for(int j = 0; j< coursePageCohort.size(); j++ )
										{
											getEnrollFromPage.add(coursePageCohort.get(j).getText());//store enroll status in one variable
										}
										for(int r = 0; r < getEnrollFromPage.size(); r++) // check all enroll status
										{
											if(getEnrollFromPage.get(r).equalsIgnoreCase("Enroll now"))
											{
												getEnrollStatus.add("Open");
											}
											else if(getEnrollFromPage.get(r).contains("close"))
											{
												getEnrollStatus.add("closed");
											}
										}
										
										if(getEnrollStatus.contains("closed"))
										{
											PageDataStatus.add("close");
										}
										else
										{
											PageDataStatus.add("Open");
										}
											
								    }
								}
							}
							
								Thread.sleep(200);
								if(!getEnrollFromCard.contains("date"))
								{
									WebElement coursePageAmount = baseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_durationAndPriceSection']>div:last-child p"));
									js.executeScript("arguments[0].scrollIntoView();", coursePageAmount);
									if(coursePageAmount.isDisplayed())
									{
										if(coursePageAmount.getText().contains("-"))
										{
											Thread.sleep(1000);
											String price[] = coursePageAmount.getText().split("-");
											String val = price[0];
											Pattern pattern = Pattern.compile("\\d+");
											Matcher matcher = pattern.matcher(val);
											StringBuilder build = new StringBuilder();
											while(matcher.find())
											{
												build.append(matcher.group());
												Thread.sleep(1000);
											}
											String result1 = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
											PageDataStatus.add(result1);
										
										}
										else
										{

											Thread.sleep(1000);
											String val = coursePageAmount.getText();
											Pattern pattern = Pattern.compile("\\d+");
											Matcher matcher = pattern.matcher(val);
											StringBuilder build = new StringBuilder();
											while(matcher.find())
											{
												build.append(matcher.group());
												Thread.sleep(1000);
											}
											String result1 = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
											PageDataStatus.add(result1);
											Thread.sleep(1000);
										
										}
									}
									else
									{
										PageDataStatus.add("noAmount");
									}
								}
								else
								{
									PageDataStatus.add(result);
								}
							Thread.sleep(200);
							if(!cardDataStatus.equals(PageDataStatus))
							{
								for(int k = 0; k < cardDataStatus.size(); k++)
								{
									if(k == 0)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Icon mismatch in "+courseCardLinkText);
										}
									}
									if(k == 1)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Type mismatch in "+courseCardLinkText);
										}
									}
									if(k == 2)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Name mismatch in "+courseCardLinkText);
										}
									}
									if(k == 3)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Level mismatch in "+courseCardLinkText);
										}
									}
									if(k == 4)
									{
										
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" partner not present "+courseCardLinkText);
										}
									}
									if(k == 5)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Enroll mismatch in "+courseCardLinkText);
										}
									}
									if(k == 6)
									{
										if(!cardDataStatus.get(k).equals(PageDataStatus.get(k)))
										{
											status.add(" Amount mismatch in "+courseCardLinkText);
										}
									}
								}
							}
							Thread.sleep(200);
							cardDataStatus.clear();
							PageDataStatus.clear();
							driver.close();
							driver.switchTo().window(parentWindow);
						}
						driver.switchTo().window(parentWindow);
					}
						
					driver.switchTo().window(parentWindow);
						
					Thread.sleep(200);
					System.out.println("process done for : "+courseCardLinkText);
					Thread.sleep(200);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	private LocalDate parseDate(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean isInteger(String text) {
		// TODO Auto-generated method stub
		return false;
	}
	public void launchValidator()
	{
		try
		{
			String currentURL = driver.getCurrentUrl();
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get("https://validator.schema.org/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			String getCaptchaURL = driver.getCurrentUrl();
			String addTextCaptchaURL = getCaptchaURL+"?invisible=true";
			driver.get(addTextCaptchaURL);
			Set<String> s1 = driver.getWindowHandles();
	        System.out.println("Clicked the checkbox");
			Iterator<String> itr = s1.iterator();
			while(itr.hasNext())
			{
				String childwindowOfTableValidator = itr.next();
				if(!childwindowOfTableValidator.equalsIgnoreCase(tabs.get(1)))
				{
					String runUrlPopupText = driver.findElement(By.xpath("//span[contains(text(),\"Test your structured data\")]")).getText();
					System.out.println(runUrlPopupText);
					WebElement enterURLInRunTest = driver.findElement(By.cssSelector("input#new-test-url-input"));
					enterURLInRunTest.sendKeys(currentURL);
					WebElement clickRunTest = driver.findElement(By.cssSelector("button#new-test-submit-button"));
					clickRunTest.click();
					String checkTablePageURL = driver.findElement(By.cssSelector("div#fetch-url")).getText();
					System.out.println(checkTablePageURL);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String validateHeading(String heading)
	{
		String status = "Success";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			WebElement tableElement = driver.findElement(By.cssSelector("li.mdl-list__item:first-child"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", tableElement);
			if(tableElement.isDisplayed())
			{
				try
				{
					tableElement.click();
					WebElement headingFromTableElement = driver.findElement(By.cssSelector("table.mdl-data-table tr:last-child td:last-child > div"));
					String headingFromTable = headingFromTableElement.getText();
					if(!headingFromTable.equalsIgnoreCase(heading))
					{
						status = "Failed";
					}
					WebElement back = driver.findElement(By.cssSelector("#results-cell button.mdl-button:not([id])"));
					wait.until(ExpectedConditions.visibilityOf(back)).click();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Heading is not available in schema validator");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public HashMap<String, String> getFAQFromValidator()
	{
		HashMap<String, String> faqFromValidator = new HashMap<String, String>();
		try
		{
		WebElement faqElement = driver.findElement(By.cssSelector("li.mdl-list__item:last-child"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqElement);
		if(faqElement.isDisplayed())
		{
			faqElement.click();
			List<WebElement> tableRows = driver.findElements(By.cssSelector("table.mdl-data-table tr"));
			String questionFromValidator = "";
			for(WebElement row: tableRows)
			{
				List<WebElement> rowCells = row.findElements(By.cssSelector(" td div"));
				if(rowCells.size() == 2)
				{
						WebElement firstColumn = rowCells.get(0);
						((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", firstColumn);
						String key = firstColumn.getText().replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").trim();
						if(key.equals("name"))
						{
							questionFromValidator = rowCells.get(1).getText().replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").trim();
						}
						else if(key.equals("text") && !questionFromValidator.isEmpty())
						{
							String answerFromValidtor = rowCells.get(1).getText().replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").trim();
							faqFromValidator.put(questionFromValidator, answerFromValidtor);
							questionFromValidator = "";
						}
					}
			}
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", faqElement);
		}
		else
		{
			System.out.println("FAQ not available in SchemaValidator");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return faqFromValidator;
		}
		
	public String checkTableValidator(String tableValidatorURL, String tableHeading) throws MalformedURLException
	{
		String currentURL = driver.getCurrentUrl();
		String checkTablePageURL = null;
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		driver.get("https://validator.schema.org/");
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> itr = s1.iterator();
		while(itr.hasNext())
		{
			String childwindowOfTableValidator = itr.next();
			if(!childwindowOfTableValidator.equalsIgnoreCase(tabs.get(1)))
			{
				String runUrlPopupText = driver.findElement(By.xpath("//span[contains(text(),\"Test your structured data\")]")).getText();
				System.out.println(runUrlPopupText);
				WebElement enterURLInRunTest = driver.findElement(By.cssSelector("input#new-test-url-input"));
				enterURLInRunTest.sendKeys(currentURL);
				WebElement clickRunTest = driver.findElement(By.cssSelector("button#new-test-submit-button"));
				clickRunTest.click();
				checkTablePageURL = driver.findElement(By.cssSelector("div#fetch-url")).getText();
				System.out.println(checkTablePageURL);
				WebElement detectedItems = driver.findElement(By.cssSelector("div[class=\"sKfxWe-BeDmAc sKfxWe-BeDmAc-AHe6Kc\"] div[class=\"sKfxWe-BeDmAc-qJTHM-haAclf\"] ul"));
				List<WebElement> listOfDetectedItem = detectedItems.findElements(By.cssSelector(" li"));
				for(int i = 0; i < listOfDetectedItem.size(); i++)
				{
					WebElement getTextFromDetectedItem = listOfDetectedItem.get(i).findElement(By.cssSelector(" span"));
					System.out.println(getTextFromDetectedItem.getText());
					if(getTextFromDetectedItem.getText().equalsIgnoreCase("Table"))//1.table 2. faq
					{
						getTextFromDetectedItem.click();//1.table 2. faq 
						WebElement table = driver.findElement(By.cssSelector("table[class=\"mdl-data-table mdl-js-data-table aVTXAb-BeDmAc-LJTIlf-jyrRxf\"]"));
						List<WebElement> tableCell = table.findElements(By.cssSelector(" tbody tr td div"));
						for(int j = 0; j < tableCell.size(); j++)
						{
							if(tableCell.get(j).getText().replaceAll("\\s", "").equalsIgnoreCase(tableHeading.replaceAll("\\s", "")))
							{
								System.out.println("Table heading is same :"+tableCell.get(j).getText().replaceAll("\\s", ""));
							}
						}
						driver.findElement(By.cssSelector("button[class=\"mdl-button mdl-js-button mdl-js-ripple-effect \"]")).click();
					}
					else if(getTextFromDetectedItem.getText().equalsIgnoreCase("FAQPage"))
					{
						getTextFromDetectedItem.click();
						
					}
				}
			}
		}
		return tableHeading;
	}
	
	public ArrayList<String> checkProgramOutline(ArrayList<String> coursesFromExcel)
	{
		ArrayList<String> statusOfCoursesFromProgramOutline = null;
		List<WebElement> programs = driver.findElements(By.cssSelector("div[class='FlatCourseCard_topRightSide__5_fpH'] h2"));
		for(int i = 0; i < programs.size(); i++)
		{
			programs.get(i).click();
			String parentWindow = driver.getWindowHandle();
			Set<String> listOfWindows = driver.getWindowHandles();
			{
				for(String window : listOfWindows)
				{
					if(!driver.getCurrentUrl().equalsIgnoreCase(parentWindow))
					{
						driver.switchTo().window(window);
						JavascriptExecutor js = (JavascriptExecutor) driver;
						List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class='CourseMain_courseDetailsAndCertificateContentSection__qrjLi'] div[class='CourseOutline_accordionItem__nd75l accordion-item']"));
						for(int j = 0; j < listOfCourses.size(); j++)
						{
							WebElement course = listOfCourses.get(j);
							WebElement courseID = course.findElement(By.cssSelector(" div[class='CourseOutline_courseIdBox__XpnJi']"));
							String clickLink = Keys.chord(Keys.CONTROL, Keys.ENTER);
							course.findElement(By.cssSelector(" div[class='CourseOutline_infoBox__HaL2o mt-3 d-flex align-items-center'] a")).sendKeys(clickLink);
							if(driver.getCurrentUrl().contains(courseID.getText()))
							{
								System.out.println("course id is same");
								statusOfCoursesFromProgramOutline.add("success");
							}
							else
							{
								System.out.println("course id is not same");
								statusOfCoursesFromProgramOutline.add("failed");
							}
						}
						driver.close();
						driver.switchTo().window(parentWindow);
					}
				}
			}
			driver.close();
		}
		return statusOfCoursesFromProgramOutline;
	}
	public String[] checkRedirectStatus(String url, String statusCode1, String statusCode2)
	{
		String[] status = new String[2];
		status[0] = "success";
		status[1] = "success";
		
		String host = "";
		try 
		{
			parentURL = new URL(driver.getCurrentUrl());
			host = parentURL.getHost();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://www.redirect-checker.org/index.php");
		WebElement CheckRedirect = driver.findElement(By.id("redirecturl"));
		CheckRedirect.sendKeys(host + url);
		WebElement clickAnalysisButton = driver.findElement(By.id("sitemapsubmit"));
		clickAnalysisButton.click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 200)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String statusResult1 = checkStatusCode(statusCode1);
		if(!statusResult1.equalsIgnoreCase("Success"))
		{
			status[0] = "failed";
			//driver.switchTo().window(tabs.get(1));
		}
		
		String statusResult2 = checkStatusCode(statusCode2);
		if(!statusResult2.equalsIgnoreCase("Success"))
		{
			status[1] = "failed";
			//driver.switchTo().window(tabs.get(0));
		}
		driver.close();
		driver.switchTo().window(tabs.get(0));
		return status;
	}
	
	private String checkStatusCode(String statusCode)
	{
		String status = "Failure";
		
		try
		{
			driver.findElement(By.xpath("//p//strong[contains(text(),'"+statusCode+"')]"));
			status = "Success";
		}
		catch(Exception e)
		{
			System.out.println("status of Redirected URL: " + status);
		}
		return status;
	}
	
}

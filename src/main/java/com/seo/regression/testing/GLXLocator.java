package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
public class GLXLocator {
	MicrosoftCourseLocator microsoftCourseLocator;
	WebDriver driver;
	public GLXLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
	}
	
	public String facebookProcess()
	{
		String status = "fail";
		if(driver.getCurrentUrl().contains("skillup"))
		{
			driver.get(driver.getCurrentUrl()+"/glx");
		}
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='facebook']"));
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String instagramProcess()
	{
		String status = "fail";
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='instagram']"));
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String twitterProcess()
	{

		String status = "fail";
		try
		{
			WebElement facebookIcon = driver.findElement(By.cssSelector("img[alt='twitter']"));
			if(facebookIcon.isDisplayed())
			{
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL).click(facebookIcon).keyUp(Keys.CONTROL).build().perform();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	
	}
	
	public String CTADownloadingProcess()
	{
		String status = "";
		try
		{
			driver.get(driver.getCurrentUrl()+"/glx");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 2000)", "");
			List<WebElement> downloadIcons = driver.findElements(By.cssSelector("div[class*='TechMasterCertificate_TechmasterMain'] div[class*='TechMasterCertificate_TechmasterCert'] div[class*='TechMasterCertificate_TechmasterDownldBtn']>button"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			try
			{
				if(downloadIcons.get(1).isDisplayed())
				{
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					//downloadIcons.get(1).sendKeys(Keys.TAB);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					Thread.sleep(1000);
					Actions action = new Actions(driver);
					action.moveToElement(downloadIcons.get(1)).build().perform();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				}
			}
			catch(Exception e)
			{
				status = "issue in mouseHover";
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			if(downloadIcons.size()>0)
			{
				downloadIcons.get(1).click();
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				WebElement iframeLocator = driver.findElement(By.cssSelector("iframe[src='https://survey.zohopublic.in/zs/HXDwQB']"));
				driver.switchTo().frame(iframeLocator);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				WebElement CTAdownloadForm = driver.findElement(By.cssSelector("div#form_page section#form_body div#form_container div#page_header p[name='descMsg']"));
				if(CTAdownloadForm.isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					System.out.println("CTA Download form located : "+CTAdownloadForm.getText());
				}
			}
		}
			
		catch(Exception e)
		{
			status = "CTADownloads not clickable";
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> verifyGLXProgram()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		ArrayList<String> cardData = new ArrayList<String>();
		ArrayList<String> pageData = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 3000)", "");
			driver.switchTo().defaultContent();
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("section[class*='DiscountSection_DataScienceAiCour'] div[class*='container-fluid DiscountSection_containerInner']>div[class='row']:nth-child(2) div[class*='DiscountSection_programcardDiv']"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				
				String programCardName = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_ProgramHeading']")).getText();
				js.executeScript("arguments[0].scrollIntoView();", ListOfProgram.get(i));
				
				try
				{
					WebElement programCardIcon = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_programcardTop'] div[class*='DiscountSection_ProgramBadGE']>img"));
					if(!programCardIcon.isDisplayed())
					{
						processStatus.add(programCardName.concat(" Program Icon not present"));
					}
					else
					{
						cardData.add("ProgramIcon");// getting ibm icon from card
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("ProgramIcon not present");
					//processStatus.add(programCardName.concat(" Program Icon not present"));
				}
				
				try
				{
					WebElement programCardPartnerName = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_programcardTop'] div[class*='DiscountSection_companySection']>h6"));
					
					if(!programCardPartnerName.isDisplayed())
					{
						processStatus.add(programCardName.concat(" programCard PartnerName not present"));
					}
					else
					{
						cardData.add("partnerNamePresent");// partner name from card
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("partnerName not Present");
					//processStatus.add(programCardName.concat(" programCard PartnerName not present"));
				}
				
				try
				{
					WebElement programCardTitle = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_ProgramHeading']"));
					
					if(!programCardTitle.isDisplayed())
					{
						processStatus.add(programCardName.concat(" programCard Title not present"));
					}
					else
					{
						cardData.add(programCardTitle.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim()); // getting ibm card title
					}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("program card title not present");
					//processStatus.add(programCardName.concat(" programCard Title not present"));
				}
				
				try
				{
					WebElement programCardLevel = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_programcardTop'] div[class*='DiscountSection_ProgramList']>ul"));
					
					if(!programCardLevel.isDisplayed())
					{
						processStatus.add(programCardName.concat(" programCard Level not present"));
					}
					else
					{
						cardData.add(programCardLevel.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());// levels from card 
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("programCardLevel not present");
					//processStatus.add(programCardName.concat(" programCard Level not present"));
				}
				
				try
				{
					WebElement programCardEnrollmentStatus = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_programcardBott']>div[class*='DiscountSection_programEnroll']>span"));
					
					if(!programCardEnrollmentStatus.isDisplayed())
					{
						processStatus.add(programCardName.concat(" programCard EnrollmentStatus not present"));
					}
					else
					{
						String enrollStatus = programCardEnrollmentStatus.getText();
						if(enrollStatus.contains("Open"))
						{
							cardData.add("EnrollOpen");//enrollment status from card
						}
						else if(enrollStatus.contains("Coming Soon"))
						{
							cardData.add("EnrollClose");
						}
						else if(enrollStatus.contains("Close"))
						{
							cardData.add("EnrollClose");
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("programCard Enrollment is not present");
					//processStatus.add(programCardName.concat(" programCard EnrollmentStatus not present"));
				}
				
				try
				{
					WebElement programCardPrice = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='DiscountSection_programcardBott']>div[class*='DiscountSection_programPrice']>span"));
					
					if(!programCardPrice.isDisplayed())
					{
						processStatus.add(programCardName.concat(" programCard Price not present"));
					}
					else
					{
						String val = programCardPrice.getText();
						Pattern pattern = Pattern.compile("\\d+");
						Matcher matcher = pattern.matcher(val);
						StringBuilder build = new StringBuilder();
						while(matcher.find())
						{
							build.append(matcher.group());
						}
						String result = build.toString();
						cardData.add(result);// card price
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cardData.add("programCardPrice not available");
					//processStatus.add(programCardName.concat(" programCard Price not present"));
				}
				
				WebElement urlLink = ListOfProgram.get(i).findElement(By.cssSelector(" a"));
				String statusOfURL = microsoftCourseLocator.checkCourseCode(urlLink.getAttribute("href"));
				if(!statusOfURL.contains("fail"))
				{
					String parentwindow = driver.getWindowHandle();
					
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
					urlLink.sendKeys(selectLinkOpeninNewTab);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					for(String winHandle : driver.getWindowHandles())
					{
					    driver.switchTo().window(winHandle);
					}
					
					WebElement programPageLocator = driver.findElement(By.cssSelector("section[class*='CourseDescription_mainSection']"));
					
					
					String programPageName = programPageLocator.findElement(By.cssSelector(" h1")).getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
					
					
					try
					{
						WebElement programPageIcon = programPageLocator.findElement(By.cssSelector(" div[class='col d-flex align-items-center'] img[alt='course-icon'],img[alt='Loader']"));
						if(!programPageIcon.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program page has no icon"));
						}
						else
						{
							pageData.add("ProgramIcon"); // icon from page
						}
					}
					catch(Exception e)
					{
						//processStatus.add(programPageName.concat(" program page has no icon"));
						pageData.add("ProgramIcon not present");
						e.printStackTrace();
					}
					
					try
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						WebElement programPagePartnerName = programPageLocator.findElement(By.cssSelector(" img[alt='org-logo'],img[alt='Skillup']"));
						JavascriptExecutor js3 = (JavascriptExecutor) driver;
						js3.executeScript("arguments[0].scrollIntoView();", programPagePartnerName);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						if(!programPagePartnerName.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program page has no partner name "));
						}
						else
						{
							pageData.add("partnerNamePresent");
						}
					}
					catch(Exception e)
					{
						//processStatus.add(programPageName.concat(" program page has no  partner name"));
						pageData.add("partnerNamePresent not present");
						e.printStackTrace();
					}
					
					try
					{
						WebElement programNameLocator = programPageLocator.findElement(By.cssSelector(" h1"));
						if(!programNameLocator.isDisplayed())
						{
							processStatus.add(programPageName.concat(" name not available in program"));
						}
						else
						{
							pageData.add(programPageName);// title name from page
						}
					}
					catch(Exception e)
					{
						//processStatus.add(programPageName.concat(" name not available in program"));
						pageData.add("programPageName not present");
						e.printStackTrace();
					}
					
					try
					{
						WebElement programPageLevels = programPageLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
						
						if(!programPageLevels.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program has no Ibm levels page"));
						}
						else
						{
							pageData.add(programPageLevels.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());// levels from program page
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						pageData.add("programPageLevels not present");
						//processStatus.add(programPageName.concat(" program has no Ibm levels page"));
					}
					ArrayList<String> cohortData = new ArrayList<String>();
					try
					{
						List<WebElement> checkEnrollmentStatus = programPageLocator.findElements(By.cssSelector(" div[class*='CourseDescription_buttonsContent'],  div#Cohort>div[class*='CourseDescription_CohortBoxDiv']>button"));
						if(checkEnrollmentStatus.size() == 1)
						{
							if(checkEnrollmentStatus.get(0).findElements(By.cssSelector(" div[class*='CourseDescription_buttonsContent']>button:nth-child(1)")).size()>0)
							{
								pageData.add("EnrollOpen");
							}
							else if(checkEnrollmentStatus.get(0).findElements(By.cssSelector(" div[class*='CourseDescription_buttonsContent']>h6")).size()>0)
							{
								pageData.add("EnrollClose");
							}
							else if(checkEnrollmentStatus.get(0).getText().equalsIgnoreCase("Enroll now"))
							{
								pageData.add("EnrollOpen");
							}
							else
							{
								pageData.add("EnrollClose");
							}
						}
						else if(checkEnrollmentStatus.size()>1)
						{
							System.out.println("Enroll cohort status displayed");
							for(int k = 0; k < checkEnrollmentStatus.size(); k++)
							{
								cohortData.add(checkEnrollmentStatus.get(k).getText());
							}
							System.out.println("Enroll cohort data displayed : "+cohortData);
							if(cohortData.contains("Enroll close"))
							{
								System.out.println("Enroll cohort status has close");
								pageData.add("EnrollClose");
							}
							else
							{
								pageData.add("EnrollOpen");
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						pageData.add("Enroll is not present");
						//processStatus.add(programPageName.concat(" program has no Enroll status"));
					}
					
					try
					{
						WebElement programPagePrice = driver.findElement(By.xpath("//section[@class='CourseDescription_mainSection__WrO9h']//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2']//div[@class='CourseDescription_courseAboutTextSection__8_6ac']//h2[contains(text(),'Fee')]/following-sibling::p"));
						if(!programPagePrice.isDisplayed())
						{
							processStatus.add(programPageName.concat(" Issue in price section"));
						}
						else
						{
							if(programPagePrice.getText().contains("-"))
							{
								String price[] = programPagePrice.getText().split("-");
								String val = price[0];
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
								pageData.add(result);
							}
							else
							{
								String val = programPagePrice.getText();
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
								pageData.add(result);
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						pageData.add("program page has no price");
					//	processStatus.add(programPageName.concat(" program page has no Price"));
					}
					
					try
					{
						if(!cardData.equals(pageData))
						{
							System.out.println("issue in data match from Program card");
							for(int j = 0; j < cardData.size(); j++)
							{
								if(j == 0)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("Program or course Icon mismatch "+programPageName);
									}
								}
								if(j == 1)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("partnerName mismatch in "+programPageName);
									}
								}
								if(j == 2)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("programName mismatch in "+programPageName);
									}
								}
								if(j == 3)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("level mismatch in "+programPageName);
									}
								}
								if(j == 4)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("enroll status mismatch in "+programPageName);
									}
								}
								if(j == 5)
								{
									if(!cardData.get(j).equals(pageData.get(j)))
									{
										processStatus.add("price mismatch in "+programPageName);
									}
								}
							}
							
						}
						cardData.clear();
						pageData.clear();
						driver.close();
						driver.switchTo().window(parentwindow);
						System.out.println("tech program card verification done for "+programPageName);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("facing error in "+urlLink.getAttribute("href"));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
		
	}
	
	public String ExploreCourseProcess() throws InterruptedException
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0, 400);");
		WebElement clickExploreAll = driver.findElement(By.cssSelector("div[class*='DiscountSection_ExploreOther']>a"));
		js.executeScript("arguments[0].scrollIntoView();", clickExploreAll);
		if(clickExploreAll.isDisplayed())
		{
			//Actions actions = new Actions(driver);
			WebElement clickExploreAll1 = driver.findElement(By.cssSelector("div[class*='DiscountSection_ExploreOther']>a"));
			js.executeScript("arguments[0].scrollIntoView();", clickExploreAll1);
			js.executeScript("window.scrollBy(0, -100);");
			//actions.keyDown(Keys.CONTROL).click(clickExploreAll1).keyUp(Keys.CONTROL).build().perform();
			clickExploreAll1.click();
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String windows : allWindows)
			{
				driver.switchTo().window(windows);
				if(driver.getCurrentUrl().contains("explore"))
				{
					driver.switchTo().window(windows);
					status = "pass";
					driver.close();
					break;
				}
			}
			driver.switchTo().window(parentWindow);
		}
		return status;
	}
}

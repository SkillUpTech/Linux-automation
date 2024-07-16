package com.palm.regressionTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TechMasterLocator
{
	WebDriver driver;
	MicrosoftCourseLocator microsoftCourseLocator;
	
	public TechMasterLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String launchSite(String url)
	{
		String Status = "";
		try
		{
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(OpenWebsite.setHost+url);
			Status = "pass";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
	
	public String findOutMore_Button()
	{
		String Status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(driver.findElements(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']")).size()>0)
			{
				WebElement clickFindOutMore = driver.findElement(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']"));
				js.executeScript("arguments[0].click()", clickFindOutMore);
				Status = "pass";
			}
			if(driver.findElements(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']")).size()>0)
			{
				WebElement clickFindOutMore = driver.findElement(By.cssSelector("a[class*='btn shadow-none TopBanner_requestButton']"));
				js.executeScript("arguments[0].click()", clickFindOutMore);
				Status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
	public String learnmore_Button()
	{
		String Status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(driver.findElements(By.cssSelector("a[class*='EligibleForIncentive_knowMoreButton']")).size()>0)
			{
				WebElement learnmore = driver.findElement(By.cssSelector("a[class*='EligibleForIncentive_knowMoreButton']"));
				js.executeScript("arguments[0].click()", learnmore);
				Status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
	
	public String checkURLStatus(String data)
	{
		String status = "fail";
			HttpURLConnection huc = null;
			int respCode = 200;
			String addHosturl = data;
			try
			{
				huc = (HttpURLConnection)(new URL(addHosturl).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();
				System.out.println("status code : "+respCode + " " +addHosturl);
				if(respCode > 200)
				{
					System.out.println("broken link : "+addHosturl);
					System.out.println("response code : "+respCode);
					status = "fail" + respCode;
				}
				else
				{
					System.out.println("unbroken link : "+" "+addHosturl+" "+respCode);
					status = "success";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return status;
	}
	public ArrayList<String> Cards()
	{
		ArrayList<String> status = new ArrayList<String>();
		ArrayList<String> cardStatus = new ArrayList<String>();
		ArrayList<String> pageStatus = new ArrayList<String>();
		ArrayList<String> cardLevelData = new ArrayList<String>();
		ArrayList<String> pageLevelData = new ArrayList<String>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			while(driver.findElement(By.xpath("//div[contains(@class,'showMoreSection')]/button")).getText().equalsIgnoreCase("Show more"))
			{
				WebElement clickShowMore = driver.findElement(By.xpath("//div[contains(@class,'showMoreSection')]/button"));
				js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(clickShowMore.isDisplayed())
				{
					js.executeScript("arguments[0].click()", clickShowMore);
				}
			}
			List<WebElement> courseCardLocator = driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div"));
			for(int i = 0; i < courseCardLocator.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", courseCardLocator.get(i));
				WebElement locateURL = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]/div[contains(@class,'LearningCatalog_customCard')]/descendant::a"));
				String cardURL = locateURL.getAttribute("href");
				String urlStatus = this.checkURLStatus(cardURL);
				if(!urlStatus.contains("fail"))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'RegularCourseCard_customCard')]/span/img")).size()>0)
					{
						cardStatus.add("Image");//1
					}
					else
					{
						cardStatus.add("nocardImage");
						status.add("no image in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[@class='undefined']//div[contains(@class,'RegularCourseCard_courseType')]//img[@alt='icon']")).size()>0)
					{
						cardStatus.add("Icon");//2
					}
					else
					{
						cardStatus.add("nocardIcon");
						//status.add("no icon in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_courseHeading')]/p")).size()>0)
					{
						WebElement cardTitle = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_courseHeading')]/p"));
						
						cardStatus.add(cardTitle.getText().replace(" ", "").trim());
					}
					else
					{
						cardStatus.add("nocardTitle");//3
						//status.add("no title in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'RegularCourseCard_courseDes')]//ul/li")).size()>0)
					{
						List<WebElement> level = driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'RegularCourseCard_courseDes')]//ul/li"));
						for(int j = 0; j < level.size(); j++)
						{
							cardLevelData.add(level.get(j).getText().replace(" ", "").trim());//4
						}
						String levelData = Arrays.toString(cardLevelData.toArray());
						
						String checkLevelData = levelData.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replace(" ", "").trim();
						if(checkLevelData.contains("vILT")||checkLevelData.contains("Instructor"))
						{
							cardStatus.add("vILTorInstructor");
						}
						else
						{
							cardStatus.add(levelData.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replace(" ", "").trim());
						}
					}
					else
					{
						cardStatus.add("nocardLevel");//4
						//status.add("no level in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_orgGoi')]/div")).size()>0)
					{
						cardStatus.add("partnerPresent");//5
					}
					else
					{
						cardStatus.add("noPartner");
						//status.add("no partner in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_priceLeft')]/p")).size()>0)
					{
						WebElement checkEnrollmentIsOpen = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_priceLeft')]/p"));
						WebElement checkEnrollmentIsClose = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'LearningCatalog_customCard')]//div[contains(@class,'RegularCourseCard_priceLeft')]/h2"));
						if(checkEnrollmentIsOpen.getText().equalsIgnoreCase("Open") && checkEnrollmentIsClose.getText().equalsIgnoreCase("Enrollment Status"))
						{
							cardStatus.add("Open");//6
						}
						else if(!checkEnrollmentIsClose.getText().equalsIgnoreCase("Enrollment Status"))
						{
							if(checkEnrollmentIsClose.getText().equalsIgnoreCase("Coming Soon"))//6
							{
								cardStatus.add("Close");
							}
						}
						else if(checkEnrollmentIsClose.getText().equalsIgnoreCase("None"))
						{
								cardStatus.add("Close");
						}
					}
					else
					{
						cardStatus.add("noEnrollStatus");
						status.add("no enroll status in card "+cardURL);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'RegularCourseCard_priceRight')]/p[not(text()='RegularCourseCard_priceStrick')]")).size()>0)
					{
						WebElement cardPrice = driver.findElement(By.xpath("//div[contains(@class,'LearningCatalog_cardRow')]/div[" + (i + 1) + "]//div[contains(@class,'RegularCourseCard_priceRight')]/p[not(text()='RegularCourseCard_priceStrick')]"));
						
						if(cardPrice.getText().equalsIgnoreCase("null"))
						{
							status.add("price mentioned as null " + " in " +cardURL);
						}
						else
						{
							cardStatus.add(cardPrice.getText().replaceAll("[^\\d.]", "").trim());
						}
						
					}
					else 
					{
						cardStatus.add("noPrice");
						//status.add("no price in card "+cardURL);
					}
					
					String parentWindow = driver.getWindowHandle();
					driver.switchTo().newWindow(WindowType.TAB);
					
					driver.get(cardURL);
					
					Set<String> allWindow = driver.getWindowHandles();
					
					for(String window : allWindow)
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(driver.getCurrentUrl().contains("/courses/"))
						{
							driver.switchTo().window(window);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							if(driver.findElements(By.xpath("//div[contains(@class,'row CourseMain_mainRow')]/div[2]//a[contains(@class,'videosPopup_videoPlayButton')]|//a[contains(@class,'CourseMain_videoPlayButton')]//img")).size()>0)
							{
								pageStatus.add("Image");//1
							}
							else
							{
								pageStatus.add("noImage");
								//status.add("no image in page"+cardURL);
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							if(driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//img[@alt='course-icon']")).size()>0)
							{
								pageStatus.add("Icon");//2
							}
							else
							{
								pageStatus.add("noIcon");
							//	status.add("no Icon in page "+cardURL);
							}
							if(driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//div[contains(@class,'CourseDescription_courseText')]/h1")).size()>0)
							{
								WebElement pageTitle = driver.findElement(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//div[contains(@class,'CourseDescription_courseText')]/h1"));
								
								pageStatus.add(pageTitle.getText().replace(" ", "").trim());//3
							}
							else
							{
								pageStatus.add("noTitle");
								status.add("no title in page "+cardURL);
							}
							
							if(driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//div[contains(@class,'CourseDescription_levelSection')]")).size()>0)
							{
								List<WebElement> pageLevels = driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//div[contains(@class,'CourseDescription_levelSection')]"));
								for(int k = 0; k < pageLevels.size(); k++)
								{
									pageLevelData.add(pageLevels.get(k).getText());
								}
								String levelInfo = Arrays.toString(pageLevelData.toArray());
								String checkLevelData = levelInfo.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replace(" ", "").trim();
								if(checkLevelData.contains("vILT")||checkLevelData.contains("Instructor"))
								{
									pageStatus.add("vILTorInstructor");
								}
								else
								{
									
									pageStatus.add(levelInfo.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replace(" ", "").trim());//4
								}
							}
							else
							{
								pageStatus.add("noLevel");
							//	status.add("no level in page "+cardURL);
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							if(driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//img[@alt='org-logo']")).size()>0)
							{
								pageStatus.add("partnerPresent");//5
							}
							else
							{
								pageStatus.add("noPartner");
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							if(driver.findElements(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//button[contains(@class,'CourseDescription_enrollNowBtn')]")).size()>0)
							{
								WebElement enrollStatus = driver.findElement(By.xpath("//section[contains(@class,'CourseDescription_mainSection')]//button[contains(@class,'CourseDescription_enrollNowBtn')]"));
								
								if(enrollStatus.getText().equalsIgnoreCase("Enroll Now"))
								{
									pageStatus.add("Open");//6
								}
							}
							else if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_buttonsContent')]//h6[contains(text(),'Enrollment is Closed')]")).size()>0)
							{
								WebElement enrollStatus = driver.findElement(By.xpath("//div[contains(@class,'CourseDescription_buttonsContent')]//h6[contains(text(),'Enrollment is Closed')]"));

								if(enrollStatus.getText().equalsIgnoreCase("Enrollment is Closed"))
								{
									pageStatus.add("Close");//6
								}
							}
							else
							{
								pageStatus.add("noEnrollStatus");
								//status.add("no enroll status in page "+cardURL);
							}
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							
							if(driver.findElements(By.xpath("//div[@class='d-flex gap-2'][2]//div[contains(@class,'CourseDescription_courseAboutTextSection')]//p|//div[@class='d-flex gap-2'][3]//div[contains(@class,'CourseDescription_courseAboutTextSection')]//p")).size()>0)
							{
								WebElement price = driver.findElement(By.xpath("//div[@class='d-flex gap-2'][2]//div[contains(@class,'CourseDescription_courseAboutTextSection')]//p|//div[@class='d-flex gap-2'][3]//div[contains(@class,'CourseDescription_courseAboutTextSection')]//p"));
								if(price.getText().replaceAll("[^\\d.]", "").trim().contains("null"))
								{
									status.add(price.getText().replaceAll("[^\\d.]", "").trim() +" in " +"cardURL");
								}
								else
								{
									pageStatus.add(price.getText().replaceAll("[^\\d.]", "").trim());//7
								}
							}
							else
							{
								pageStatus.add("noPrice");
								//status.add("no price in page "+cardURL);
							}
							if(!cardStatus.equals(pageStatus))
							{
								System.out.println("card and page data are not same");
								for(int k = 0; k < cardStatus.size(); k++)
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 0)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Image issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 1)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Icon issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 2)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Title issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 3)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Level issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 4)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Partner issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 5)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("EnrollStatus issue compare with card and page in "+cardURL);
										}
									}
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									if(k == 6)
									{
										if(!cardStatus.get(k).equals(pageStatus.get(k)))
										{
											status.add("Price issue compare with card and page in "+cardURL);
										}
									}
								}
							}
							else
							{
								System.out.println("card and page data are same");
							}
							cardLevelData.clear();
							pageLevelData.clear();
							cardStatus.clear();
							pageStatus.clear();
							driver.close();
							driver.switchTo().window(parentWindow);
						}
					}
					driver.switchTo().window(parentWindow);
				}
				else
				{
					System.out.println("Issue in course status");
				}
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public String CheckFAQFocus()
	{
		String Status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement focusFAQ = driver.findElement(By.xpath("//a[contains(text(),'Check our FAQs section for detail')]"));
			js.executeScript("arguments[0].scrollIntoView();", focusFAQ);
			if(focusFAQ.isDisplayed())
			{
				js.executeScript("arguments[0].click()", focusFAQ);
				if(driver.findElements(By.xpath("Section#go-to-Faq")).size()>0)
				{
					Status = "pass";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Status = "fail";
		}
		return Status;
	}
}

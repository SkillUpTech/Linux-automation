package com.seo.regression.testing;

import java.net.HttpURLConnection;
import java.net.URL;
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
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MicrosoftCourseLocator 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public MicrosoftCourseLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String> verifyMicrosftPage()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		try
		{
			WebElement  clickCourseDropdown = driver.findElement(By.cssSelector("a#navbarDropdown div[class=' Header_category__mr_e4']"));
			clickCourseDropdown.click();
			List<WebElement> learningPartners = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] ul[class='learning-Partners']>li>a"));
			for(int i = 0; i < learningPartners.size();i++)
			{
				String getLearningPartnerURL = learningPartners.get(i).getAttribute("href");
				if(getLearningPartnerURL.contains("microsoft"))
				{
					String url = this.checkURLStatus(getLearningPartnerURL);
					String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					learningPartners.get(i).sendKeys(n);
					if(url.equalsIgnoreCase("fail"))
					{
						processStatus.add(getLearningPartnerURL);
						break;
					}
					else
					{
						Set<String> childWnidow = driver.getWindowHandles();
						for(String windows : childWnidow)
						{
							driver.switchTo().window(windows);
							if(driver.getCurrentUrl().contains("microsoft"))
							{
								driver.switchTo().window(windows);
								System.out.println("microsoft page : "+driver.getCurrentUrl());
								break;
							}
						}
					}
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			processStatus.add("fail");
		}
		return processStatus;
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
	
	public ArrayList<String> verifyMicrosoftScourses()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		ArrayList<String> courseProcessStatus = new ArrayList<String>();
		ArrayList<String> pageProcessStatus = new ArrayList<String>();
		ArrayList<String> courseCardData = new ArrayList<String>();
		String statusOfURL = "pass";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String enrollmentStatusOncard = "";
		
		try
		{
			String courseName = "";
			js.executeScript("window.scrollBy(0, 1100)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='ManageCardsLimit_showMoreSection'] button"));
			wait.until(ExpectedConditions.visibilityOfAllElements(clickShowMore));
			js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
			while(clickShowMore.isDisplayed() != clickShowMore.getText().equalsIgnoreCase("Show less"))
			{
				js.executeScript("arguments[0].click()", clickShowMore);
			}
			String parentWindow = driver.getWindowHandle();
			List<WebElement> listOfCourses = driver.findElements(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[@class='row'][2]/div[3]/div[contains(@class,'LearningCatalog_cardRow')]/div"));
			for(int i = 0; i < listOfCourses.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", listOfCourses.get(i));
				
				String courseURL = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class, 'RegularCourseCard_RegularcardLinks')]/a")).getAttribute("href");
				
				courseName = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/p")).getText();

				String urlLink = this.checkURLStatus(courseURL);
				
				
				if(urlLink.contains("fail"))
				{
					statusOfURL = "fail";
					processStatus.add(courseURL);
				}
				
				else
				{
				
					WebElement imageOnCards = listOfCourses.get(i).findElement(By.xpath(".//img[@alt='Course Banner']"));
					js.executeScript("arguments[0].scrollIntoView();", imageOnCards);
					
					if(imageOnCards.isDisplayed())
					{
						courseCardData.add("ImagePresent");
					}
					else
					{
						courseCardData.add("noImage");
					}
				
					WebElement iconOnCards = listOfCourses.get(i).findElement(By.xpath(".//img[@alt='icon']"));
					js.executeScript("arguments[0].scrollIntoView();", iconOnCards);
					
					if(iconOnCards.isDisplayed())
					{
						courseCardData.add("CourseIcon");
					}
					else
					{
						courseCardData.add("noCourseIcon");
					}
				
					WebElement cardType = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseType')]//p"));
					js.executeScript("arguments[0].scrollIntoView();", cardType);
					
					if(cardType.isDisplayed())
					{
						courseCardData.add(cardType.getText());
					}
					else
					{
						courseCardData.add("noCourseType");
					}
				
					WebElement nameOnCards = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/p"));
					js.executeScript("arguments[0].scrollIntoView();", nameOnCards);
					
					if(nameOnCards.isDisplayed())
					{
						courseCardData.add(nameOnCards.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
					}
					else
					{
						courseCardData.add("noName");
					}
				
				String courseCardName = nameOnCards.getText();
				
				WebElement levelsOnCards = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/ul"));
				js.executeScript("arguments[0].scrollIntoView();", levelsOnCards);
				
				if(levelsOnCards.isDisplayed())
				{
					courseCardData.add(levelsOnCards.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
				}
				else
				{
					courseCardData.add("noLevel");
				}
				
				WebElement cardPartner = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseCompany')]"));
				js.executeScript("arguments[0].scrollIntoView();", cardPartner);
				if(cardPartner.isDisplayed())
				{
					courseCardData.add(cardPartner.getText());
				}
				else
				{
					courseCardData.add("noPartner");
				}
				boolean checkVILTCourse = false;
				
				List<WebElement> enrollStatusOnCards = listOfCourses.get(i).findElements(By.xpath(".//div[contains(@class,'RegularCourseCard_priceLeft')]/h2"));
				if(enrollStatusOnCards.size()==1)
				{
					if(enrollStatusOnCards.get(0).getText().contains("Course starts on"))
					{
						enrollmentStatusOncard = "Open";
						courseCardData.add(enrollmentStatusOncard);
						
						checkVILTCourse = true;
						
						WebElement courseStartsOn = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_priceLeft')]/p"));
						courseCardData.add(courseStartsOn.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
					}
					else if(enrollStatusOnCards.get(0).getText().contains("Coming Soon"))
					{
						enrollmentStatusOncard = "Close";
						courseCardData.add(enrollmentStatusOncard);
						checkVILTCourse = false;
					}
				}
				else if(listOfCourses.get(i).findElements(By.xpath(".//div[contains(@class,'RegularCourseCard_priceLeft')]/p")).size()>0)
				{
					if(listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_priceLeft')]/p")).getText().contains("Open"))
					{
						enrollmentStatusOncard = "Open";
					}
					courseCardData.add(enrollmentStatusOncard);
				}
				else
				{
					System.out.println("noEnrollmentStatus");
					courseCardData.add("noEnrollStatus");
				}
				if(checkVILTCourse == false)
				{
					courseCardData.add("noStartDate");
				}
				
				WebElement courseCardPrice = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_priceRight')]/p"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardPrice);
				
				if(!courseCardPrice.isDisplayed())
				{
					courseCardData.add(courseCardName.concat("noPrice"));
				}
				else
				{
					String val = courseCardPrice.getText();
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(val);
					StringBuilder build = new StringBuilder();
					while(matcher.find())
					{
						build.append(matcher.group());
					}
					String result = build.toString();
					courseCardData.add(result);// card price
				}
				
					driver.switchTo().newWindow(WindowType.TAB);
					driver.get(courseURL);
					Set<String> childWnidow = driver.getWindowHandles();
					for(String windows : childWnidow)
					{
						driver.switchTo().window(windows);
							
							if(driver.getCurrentUrl().contains("/courses/"))
							{
								driver.switchTo().window(windows);
								
								String checkCourseTab = driver.getTitle();
								
								if(checkCourseTab.contains("undefined"))
								{
									processStatus.add(courseURL+" undefined word on tab");
								}
								else if(checkCourseTab.contains("null"))
								{
									processStatus.add(courseURL+" null word on tab");
								}
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
								
								if(driver.findElements(By.cssSelector("div[class='error-content'] p")).size()>0)
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
									
									if(driver.findElement(By.cssSelector("div[class='error-content'] p")).getText().equalsIgnoreCase("404"))
									{
										processStatus.add(courseURL+" 404 ERROR");
									}
								}
								
								WebElement coursePageBaseLocator = driver.findElement(By.cssSelector("section[class*='CourseDescription_mainSection']"));
								
								
								WebElement pageImage = coursePageBaseLocator.findElement(By.cssSelector(" img[alt='course-icon']"));
								
								if(pageImage.isDisplayed())
								{
									pageProcessStatus.add("ImagePresent");
								}
								else
								{
									pageProcessStatus.add("noImage");
								}
								
								
								WebElement pageCourseIcon = coursePageBaseLocator.findElement(By.cssSelector(" h4"));
								if(pageCourseIcon.isDisplayed())
								{
									pageProcessStatus.add(pageCourseIcon.getText()+"Icon");
								}
								else
								{
									pageProcessStatus.add("noCourseIcon");
								}
								
								WebElement pageType = coursePageBaseLocator.findElement(By.cssSelector(" h4[class*='CourseDescription_courseLabel']"));
								if(pageType.isDisplayed())
								{
									pageProcessStatus.add(pageType.getText());
								}
								else
								{
									pageProcessStatus.add("noCourseType");
								}
								
								
								WebElement pageName = coursePageBaseLocator.findElement(By.cssSelector(" h1"));
								if(pageName.isDisplayed())
								{
									pageProcessStatus.add(pageName.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
								}
								else
								{
									pageProcessStatus.add("noName");
								}
								
								WebElement pageLevel = coursePageBaseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
								if(pageLevel.isDisplayed())
								{
									pageProcessStatus.add(pageLevel.getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
								}
								else
								{
									pageProcessStatus.add("noLevel");
								}
								
								List<WebElement> pagePartner = coursePageBaseLocator.findElements(By.cssSelector(" img[alt='org-logo']"));
								
								if(pagePartner.size()>0)
								{
									pageProcessStatus.add("Microsoft");
								}
								else
								{
									pageProcessStatus.add("noPartner");
								}
								
								WebElement pageEnrollStatus = coursePageBaseLocator.findElement(By.cssSelector(" div[class*='CourseDescription_buttonsContent'] h6, button[class*='CourseDescription_enrollNowBtn']"));
								if(pageEnrollStatus.isDisplayed())
								{
									if(pageEnrollStatus.getText().contains("Closed"))
									{
										pageProcessStatus.add("Close");
									}
									else if(pageEnrollStatus.getText().contains("Enroll Now"))
									{
										pageProcessStatus.add("Open");
									}
									else if(pageEnrollStatus.getText().contains("Go to the program"))
									{
										pageProcessStatus.add("Open");
									}
								}
								else
								{
									pageProcessStatus.add("noEnrollStatus");
								}
								if(checkVILTCourse == true)
								{
									List<WebElement> pageStartDate = coursePageBaseLocator.findElements(By.cssSelector(" div[class='d-flex gap-2']:nth-child(1) p"));
									if(pageStartDate.size()>0)
									{
										js.executeScript("arguments[0].scrollIntoView();", pageStartDate.get(0));
										if(pageStartDate.get(0).isDisplayed())
										{
											pageProcessStatus.add(pageStartDate.get(0).getText().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim());
										}
									}
									else
									{
										pageProcessStatus.add("noStartDate");
									}
								}
								else
								{
									pageProcessStatus.add("noStartDate");
								}
								List<WebElement> pagePrice = coursePageBaseLocator.findElements(By.cssSelector(" div[class*='d-flex gap-2']:nth-child(3) p, div[class*='d-flex gap-2']:nth-child(2) p"));
								if(pagePrice.size()>0)
								{
									if(pagePrice.get(0).getText().contains("-"))
									{
										Thread.sleep(1000);
										String price[] = pagePrice.get(0).getText().split("-");
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
										pageProcessStatus.add(result);
									}
									else
									{
										Thread.sleep(1000);
										String val = pagePrice.get(0).getText();
										Pattern pattern = Pattern.compile("\\d+");
										Matcher matcher = pattern.matcher(val);
										StringBuilder build = new StringBuilder();
										while(matcher.find())
										{
											build.append(matcher.group());
											Thread.sleep(1000);
										}
										String result = build.toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
										pageProcessStatus.add(result);
										Thread.sleep(1000);
									}	
								}
								else
								{
									pageProcessStatus.add("noPrice");
								}
								
								if(!pageProcessStatus.equals(courseCardData))
								{
									for(int j = 0; j < courseProcessStatus.size(); j++)
									{
										if(j == 0)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("Program or course Icon mismatch "+courseURL);
											}
										}
										if(j == 1)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("course type mismatch in "+courseURL);
											}
										}
										if(j == 2)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("course Name mismatch in "+courseURL);
											}
										}
										if(j == 3)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("level mismatch in "+courseURL);
											}
										}
										if(j == 4)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("partner mismatch in "+courseURL);
											}
										}
										if(j == 5)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("Enroll status mismatch in "+courseURL);
											}
										}
										if(j == 6)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("start date mismatch in "+courseURL);
											}
										}
										if(j == 7)
										{
											if(!courseProcessStatus.get(j).equals(pageProcessStatus.get(j)))
											{
												processStatus.add("Price mismatch in "+courseURL);
											}
										}
									}
								}
								
								driver.close();
								Thread.sleep(500);
								driver.switchTo().window(parentWindow);
								
							}
						}
						driver.switchTo().window(parentWindow);
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
	}
}

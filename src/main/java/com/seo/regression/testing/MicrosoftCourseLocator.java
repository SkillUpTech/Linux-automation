package com.seo.regression.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverAction;
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
					String url = this.checkCourseCode(getLearningPartnerURL);
					String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					learningPartners.get(i).sendKeys(n);
					if(url.equalsIgnoreCase("fail"))
					{
						processStatus.add("fail");
					}
					Set<String> childWnidow = driver.getWindowHandles();
					for(String windows : childWnidow)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains("microsoft"))
						{
							driver.switchTo().window(windows);
							System.out.println("microsoft page : "+driver.getCurrentUrl());
							processStatus.add("pass");
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
			processStatus.add("fail");
		}
		return processStatus;
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
		String courseCardStartsDate = "";
		
		String courseName = "";
		try
		{
			js.executeScript("window.scrollBy(0, 1100)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='ManageCardsLimit_showMoreSection'] button"));
			wait.until(ExpectedConditions.visibilityOfAllElements(clickShowMore));
			js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
			while(clickShowMore.isDisplayed() != clickShowMore.getText().equalsIgnoreCase("Show less"))
			{
				js.executeScript("arguments[0].click()", clickShowMore);
			}
			List<WebElement> listOfCourses = driver.findElements(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[@class='row'][2]/div[3]//div[@class='col-lg-3 col-md-4 col-sm-6 col-12']"));
			for(int i = 0; i < listOfCourses.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", listOfCourses.get(i));
				
				String courseURL = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class, 'RegularCourseCard_RegularcardLinks')]/a")).getAttribute("href");
				
				String urlLink = this.checkCourseCode(courseURL);
				
				courseName = listOfCourses.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/p")).getText();
				
				if(urlLink.contains("fail"))
				{
					statusOfURL = "fail";
					processStatus.add(courseURL);
				}
				if(!statusOfURL.equalsIgnoreCase("fail"))
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
				
					js. executeScript("window. open('"+courseURL+"');" );
					String parentWindow = driver.getWindowHandle();
					Set<String> childWnidow = driver.getWindowHandles();
					for(String windows : childWnidow)
					{
						driver.switchTo().window(windows);
						if(!parentWindow.equalsIgnoreCase(windows))
						{
							driver.switchTo().window(windows);
							if(driver.getCurrentUrl().contains(courseURL))
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
								driver.switchTo().window(parentWindow);
								
							}
						}
						driver.switchTo().window(parentWindow);
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

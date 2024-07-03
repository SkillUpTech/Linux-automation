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

public class IBMPageLocator 
{
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;
	
	public IBMPageLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public String checkURLStatus(String getURL)
	{
		String status = "fail";
		String addHosturl = getURL;
		HttpURLConnection huc = null;
		int respCode = 200;
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
				status = "fail" + respCode;
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> verifyIBMPage()
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
				if(getLearningPartnerURL.contains("ibm"))
				{
					String url = this.checkURLStatus(getLearningPartnerURL);
					String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
					learningPartners.get(i).sendKeys(n);
					if(url.equalsIgnoreCase("fail"))
					{
						processStatus.add("fail");
					}
					String parentWindow = driver.getWindowHandle();
					Set<String> childWnidow = driver.getWindowHandles();
					for(String windows : childWnidow)
					{
						driver.switchTo().window(windows);
						if(driver.getCurrentUrl().contains("ibm"))
						{
							driver.switchTo().window(windows);
							System.out.println("IBM page : "+driver.getCurrentUrl());
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
	
	
	public ArrayList<String> verifyIBMProgram()
	{
		ArrayList<String> processStatus = new ArrayList<String>();
		ArrayList<String> cardData = new ArrayList<String>();
		ArrayList<String> pageData = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 600)", "");
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("div[class*='LearningCatalogibm_cardRow']>div a"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				
				js.executeScript("arguments[0].scrollIntoView();", ListOfProgram.get(i));
				
				String programCardName = ListOfProgram.get(i).findElement(By.cssSelector(" h2")).getText();
				
				WebElement programCardIcon = ListOfProgram.get(i).findElement(By.cssSelector(" img[alt='Course-Image']"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardIcon);
				
				if(!programCardIcon.isDisplayed())
				{
					processStatus.add(programCardName.concat(" Program Icon not present from Program card"));
				}
				else
				{
					cardData.add("ProgramIcon");// getting ibm icon from card
				}
				
				WebElement programCardPartnerName = ListOfProgram.get(i).findElement(By.cssSelector(" h6[class*='FlatCourseCard_companyTitle']"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardPartnerName);
				
				if(!programCardPartnerName.isDisplayed())
				{
					processStatus.add(programCardName.concat(" programCardPartnerName not present from Program card"));
				}
				else
				{
					cardData.add(programCardPartnerName.getText());// partner name from card
				}
				
				WebElement programCardTitle = ListOfProgram.get(i).findElement(By.cssSelector(" h2"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardTitle);
				
				if(!programCardTitle.isDisplayed())
				{
					processStatus.add(programCardName.concat(" programCardTitle not present from Program card"));
				}
				else
				{
					cardData.add(programCardTitle.getText()); // getting ibm card title
				}
				
				WebElement programCardLevel = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_propertiesList']"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardLevel);
				
				if(!programCardLevel.isDisplayed())
				{
					processStatus.add(programCardName.concat(" programCardLevel not present from Program card"));
				}
				else
				{
					cardData.add(programCardLevel.getText());// levels from card 
				}
				
				WebElement programCardEnrollmentStatus = ListOfProgram.get(i).findElement(By.cssSelector(" div[class='d-flex justify-content-start align-items-center FlatCourseCard_courseStartSection__Fsatd']"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardEnrollmentStatus);
				
				if(!programCardEnrollmentStatus.isDisplayed())
				{
					processStatus.add(programCardName.concat(" programCardEnrollmentStatus not present from Program card"));
				}
				else
				{
					String enrollStatus = programCardEnrollmentStatus.findElement(By.cssSelector(" h4")).getText();
					if(enrollStatus.contains("Open"))
					{
						cardData.add("EnrollOpen");//enrollment status from card
					}
					else if(enrollStatus.contains("Coming Soon"))
					{
						cardData.add("EnrollClose");
					}
				}
				
				WebElement programCardPrice = ListOfProgram.get(i).findElement(By.cssSelector(" div[class*='FlatCourseCard_priceSection'] h3"));
				
				js.executeScript("arguments[0].scrollIntoView();", programCardPrice);
				
				if(!programCardPrice.isDisplayed())
				{
					processStatus.add(programCardName.concat(" programCardPrice not present from Program card"));
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
				
				WebElement urlLink = ListOfProgram.get(i);
				
				String urlText = urlLink.getAttribute("href");
				
				js.executeScript("arguments[0].scrollIntoView();", urlLink);
				
				String parentwindow = driver.getWindowHandle();
				
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
				
				urlLink.sendKeys(selectLinkOpeninNewTab);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				for(String winHandle : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle);
				    
				    
				    if(!(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/")) && driver.getCurrentUrl().contains(urlText))
					{
				    	driver.switchTo().window(winHandle);
				    	
				    	WebElement programPageLocator = driver.findElement(By.cssSelector("section[class*='CourseDescription_mainSection']"));
						
				    	WebElement programNameLocator = programPageLocator.findElement(By.cssSelector(" h1"));
				    	
				    	String programPageName = programPageLocator.findElement(By.cssSelector(" h1")).getText();

				    	WebElement programPageIcon = programPageLocator.findElement(By.cssSelector(" img[alt='course-icon']"));
				    	
				    	js.executeScript("arguments[0].scrollIntoView();", programPageIcon);
						
						if(!programPageIcon.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program page has no icon"));
						}
						else
						{
							pageData.add("ProgramIcon"); // icon from page
						}
						
						WebElement programPagePartnerName = programPageLocator.findElement(By.cssSelector(" img[alt='org-logo']"));
						
						js.executeScript("arguments[0].scrollIntoView();", programPagePartnerName);
						
						if(!programPagePartnerName.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program has no Ibm partner page"));
						}
						else
						{
							pageData.add("IBM");
						}
						
						if(!programNameLocator.isDisplayed())
						{
							processStatus.add(programPageName.concat(" name not available in program"));
						}
						else
						{
							pageData.add(programPageName);// title name from page
						}
						
						WebElement programPageLevels = programPageLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
						
						js.executeScript("arguments[0].scrollIntoView();", programPageLevels);
						
						if(!programPageLevels.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program has no Ibm levels page"));
						}
						else
						{
							pageData.add(programPageLevels.getText().toLowerCase());// levels from program page
						}
						
						WebElement programPageEnroll = programPageLocator.findElement(By.cssSelector(" button[class*='CourseDescription_enrollNowBtn']"));
						
						js.executeScript("arguments[0].scrollIntoView();", programPageEnroll);
						
						if(!programPageEnroll.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program has no Enroll status"));
						}
						else
						{
							if(programPageEnroll.getText().equalsIgnoreCase("Enroll Now"))
							{
								pageData.add("EnrollOpen");//price from page
							}
							else if(programPageEnroll.getText().contains("Enrollment is Closed"))
							{
								pageData.add("EnrollClose");
							}
						}
						
						WebElement programPagePrice = driver.findElement(By.xpath("//section[@class='CourseDescription_mainSection__WrO9h']//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2']//div[@class='CourseDescription_courseAboutTextSection__8_6ac']//h2[contains(text(),'Fee')]/following-sibling::p"));
						
						js.executeScript("arguments[0].scrollIntoView();", programPagePrice);
						
						String val = "";
						
						if(!programPagePrice.isDisplayed())
						{
							processStatus.add(programPageName.concat(" program has no Price"));
						}
						else
						{
							val = programPagePrice.getText();
							Pattern pattern = Pattern.compile("\\d+");
							if(val.contains("-"))
							{
								String getPrice[] = val.split("-");
								Matcher matcher = pattern.matcher(getPrice[0]);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString();
								pageData.add(result);
							}
							else
							{
								String getPrice[] = val.split("-");
								Matcher matcher = pattern.matcher(getPrice[0]);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString();
								pageData.add(result);
							
							}
						}
						System.out.println("tech program card verification done for "+programPageName);
						driver.close();
						driver.switchTo().window(parentwindow);
						if(!cardData.equals(pageData))
						{
							System.out.println("issue in data match from IBM Program card");
						}
					}
				driver.switchTo().window(parentwindow);
			}
				driver.switchTo().window(parentwindow);
				
			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
		
	}
	
	public ArrayList<String> verifyIBMcourses()
	{
		ArrayList<String> courseProcessStatus = new ArrayList<String>();
		ArrayList<String> courseCardData = new ArrayList<String>();
		ArrayList<String> coursePageData = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			WebElement clickShowMoreIcon = driver.findElement(By.cssSelector("div[class='col-12 undefined d-xl-block false'] >div:nth-child(2) button"));
			
			js.executeScript("arguments[0].scrollIntoView();", clickShowMoreIcon);
			
			while(clickShowMoreIcon.isDisplayed() && clickShowMoreIcon.getText().contains("more"))
			{
				js.executeScript("arguments[0].click()", clickShowMoreIcon);
			}
			List<WebElement> ListOfCourses = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow']>div a"));
			
			for(int i = 0; i < ListOfCourses.size(); i++)
			{
				js.executeScript("arguments[0].scrollIntoView();", ListOfCourses.get(i));
				
				String courseCardName = ListOfCourses.get(i).findElement(By.cssSelector(" div[class='RegularCourseCard_courseDes__0h9WE'] p")).getText();
				
				WebElement courseCardIcon = ListOfCourses.get(i).findElement(By.cssSelector(" div[class='RegularCourseCard_courseType__5tOn7'] img[alt='icon']"));
				
				if(!courseCardIcon.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" course Icon not present from course card"));
				}
				else
				{
					courseCardData.add("CourseIcon");// getting ibm icon from card
				}
				
				WebElement courseCardPartnerName = ListOfCourses.get(i).findElement(By.cssSelector(" div[class='RegularCourseCard_courseCompany__cW3re']"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardPartnerName);
				
				if(!courseCardPartnerName.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" courseCardPartnerName not present from course card"));
				}
				else
				{
					courseCardData.add(courseCardPartnerName.getText());// partner name from card
				}
				
				WebElement courseCardTitle = ListOfCourses.get(i).findElement(By.cssSelector(" div[class='RegularCourseCard_courseDes__0h9WE'] p"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardTitle);
				
				if(!courseCardTitle.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" courseCardTitle not present from Program card"));
				}
				else
				{
					courseCardData.add(courseCardTitle.getText()); // getting ibm card title
				}
				
				WebElement courseCardLevel = ListOfCourses.get(i).findElement(By.cssSelector(" div[class='RegularCourseCard_courseDes__0h9WE'] ul"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardLevel);
				
				if(!courseCardLevel.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" courseCardLevel not present from Program card"));
				}
				else
				{
					courseCardData.add(courseCardLevel.getText().toLowerCase());// levels from card 
				}
				
				WebElement courseCardEnrollmentStatus = ListOfCourses.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceLeft'] p"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardEnrollmentStatus);
				
				if(!courseCardEnrollmentStatus.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" courseCardEnrollmentStatus not present from Program card"));
				}
				else
				{
					String enrollStatus = courseCardEnrollmentStatus.getText();
					if(enrollStatus.contains("Open"))
					{
						courseCardData.add("EnrollOpen");//enrollment status from card
					}
					else if(enrollStatus.contains("Coming Soon"))
					{
						courseCardData.add("EnrollClose");
					}
				}
				
				WebElement courseCardPrice = ListOfCourses.get(i).findElement(By.cssSelector(" div[class*='RegularCourseCard_priceRight'] p"));
				
				js.executeScript("arguments[0].scrollIntoView();", courseCardPrice);
				
				if(!courseCardPrice.isDisplayed())
				{
					courseProcessStatus.add(courseCardName.concat(" courseCardPrice not present from course card"));
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
				WebElement urlLink = ListOfCourses.get(i);
				js.executeScript("arguments[0].scrollIntoView();", urlLink);
				
				String urlText = urlLink.getAttribute("href");
				
				String parentwindow = driver.getWindowHandle();
				
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); //Keys.chord(Keys.CONTROL,Keys.RETURN)
				urlLink.sendKeys(selectLinkOpeninNewTab);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				for(String winHandle : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle);
				 
				    if(!(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/")) && driver.getCurrentUrl().contains(urlText))
					{
				    
				    	driver.switchTo().window(winHandle);
				    	
						WebElement coursePageLocator = driver.findElement(By.cssSelector("section[class='CourseDescription_mainSection__WrO9h']>div"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePageLocator);
				
						WebElement courseNameLocator = coursePageLocator.findElement(By.cssSelector(" h1"));
						
						js.executeScript("arguments[0].scrollIntoView();", courseNameLocator);
				
						String coursePageName = coursePageLocator.findElement(By.cssSelector(" h1")).getText();
						
						WebElement coursePageIcon = coursePageLocator.findElement(By.cssSelector(" img[alt='course-icon']"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePageIcon);
				
						if(!coursePageIcon.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" course page has no icon"));
						}
						else
						{
							coursePageData.add("CourseIcon"); // icon from page
						}
				
						WebElement coursePagePartnerName = coursePageLocator.findElement(By.cssSelector(" img[alt='org-logo']"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePagePartnerName);
						
						if(!coursePagePartnerName.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" course has no Ibm partner page"));
						}
						else
						{
							coursePageData.add("IBM");
						}
				
						if(!courseNameLocator.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" name not available in course"));
						}
						else
						{
							coursePageData.add(coursePageName);// title name from page
						}
				
						WebElement coursePageLevels = coursePageLocator.findElement(By.cssSelector(" div[class*='CourseDescription_levelSection']"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePageLevels);
						
						if(!coursePageLevels.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" course has no Ibm levels page"));
						}
						else
						{
							coursePageData.add(coursePageLevels.getText().toLowerCase());// levels from program page
						}
				
						WebElement coursePageEnroll = coursePageLocator.findElement(By.cssSelector(" button[class*='CourseDescription_enrollNowBtn']"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePageEnroll);
						
						if(!coursePageEnroll.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" course has no Enroll status"));
						}
						else
						{
							if(coursePageEnroll.getText().equalsIgnoreCase("Enroll Now"))
							{
								coursePageData.add("EnrollOpen");//price from page
							}
							else if(coursePageEnroll.getText().contains("Enrollment is Closed"))
							{
								coursePageData.add("EnrollClose");
							}
						}
							
						WebElement coursePagePrice = coursePageLocator.findElement(By.cssSelector(" div[class='d-flex gap-2'] div[class*='CourseDescription_courseAboutTextSection'] p"));
						
						js.executeScript("arguments[0].scrollIntoView();", coursePagePrice);
						
						if(!coursePagePrice.isDisplayed())
						{
							courseProcessStatus.add(coursePageName.concat(" course has no Price"));
						}
						else
						{
							String getPrice = "";
							getPrice = coursePagePrice.getText();
							if(getPrice.contains("-"))
							{
								String getFirstPrice[] = getPrice.split("-");
								String val = getFirstPrice[0];
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString();
								coursePageData.add(result);
							}
							else
							{
								String val = getPrice;
								Pattern pattern = Pattern.compile("\\d+");
								Matcher matcher = pattern.matcher(val);
								StringBuilder build = new StringBuilder();
								while(matcher.find())
								{
									build.append(matcher.group());
								}
								String result = build.toString();
								coursePageData.add(result);
							}
							
						}
						if(!courseCardData.equals(coursePageData))
						{
							System.out.println("issue in data match from IBM Program card");
						}
						driver.close();
						driver.switchTo().window(parentwindow);
				System.out.println("course card verification done for "+coursePageName);
			}
				    
				    driver.switchTo().window(parentwindow);
				    
		}
				
				 driver.switchTo().window(parentwindow);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return courseProcessStatus;
	}
}

package com.palm.regressionTesting;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class DashboardLocator
{
	WebDriver driver;
	OpenWebsite openWebsite;
	MicrosoftCourseLocator microsoftCourseLocator;
	RegressionGenericLocator regressionGenericLocator;
	String courseName = "";
	public DashboardLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
		this.regressionGenericLocator = new RegressionGenericLocator(this.driver);
		
		PageFactory.initElements(driver, this);
	}
	public ArrayList<String> openSite(ArrayList<String> urgetURLl)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String parent  = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			for(String window : windows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(window);
					for(int i = 1; i < urgetURLl.size(); i++)
					{
						String openPage = OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+urgetURLl.get(1);
						
						status.add(microsoftCourseLocator.checkURLStatus(openPage));
						if(status.contains("fail"))
						{
							System.out.println("Facing issue on site");
						}
						driver.switchTo().newWindow(WindowType.TAB);
						driver.get(openPage);
						WebElement getTitle = driver.findElement(By.xpath("//h1"));
						courseName = getTitle.getText().replaceAll("[-+.^:,]","").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim();
						System.out.println("course or  program  name : "+courseName);
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
	public ArrayList<String> EnrollFlatPrice(ArrayList<String> enrollDataFromExcel)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		ArrayList<String> statusOfProcess = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			js.executeScript("window.scrollBy(0,400)");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			String getCurrentURL = driver.getCurrentUrl();
			if(getCurrentURL.contains("in"))// india site
			{
				Thread.sleep(400);
				js.executeScript("window.scrollBy(0,700)");
				Thread.sleep(400);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				WebElement checkEnrollButton = driver.findElement(By.xpath("//div[contains(@class,'FixedContentBar_buttonsContent')]/button[contains(text(),'Enroll Now')]"));
				js.executeScript("arguments[0].scrollIntoView();", checkEnrollButton);
				Thread.sleep(400);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				if(checkEnrollButton.isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					if(checkEnrollButton.getText().equalsIgnoreCase("Enroll Now"))
					{
						System.out.println("Enroll Button is displayed");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						wait.until(ExpectedConditions.elementToBeClickable(checkEnrollButton));
						js.executeScript("arguments[0].click()", checkEnrollButton);
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						String parentWindow = driver.getWindowHandle();
						Set<String> allWindows = driver.getWindowHandles();
						for(String window : allWindows)
						{
							driver.switchTo().window(window);
							if(driver.getCurrentUrl().contains("login?"))
							{
								driver.switchTo().window(window);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
								break;
							}
						}
					}
				}
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				statusOfProcess.add(regressionGenericLocator.loginProcess(enrollDataFromExcel.get(1)));
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				Thread.sleep(500);
				statusOfProcess.add(regressionGenericLocator.checkOutRazorpay(enrollDataFromExcel.get(3)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				statusOfProcess.addAll(regressionGenericLocator.indiaPaymentProcess(enrollDataFromExcel.get(2), enrollDataFromExcel.get(3)));
			}
			else
			{
				System.out.println("US Enroll Process");
			}
			String className = this.getClass().getSimpleName();
			if(!className.contains("Dashboard"))
			{
				WebElement clickDropdownIcon = driver.findElement(By.cssSelector("li[class='SigNUP']>a"));
				clickDropdownIcon.click();
				Thread.sleep(2000);
				WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Primary02_Blue'] li:nth-child(5) a"));
				JavascriptExecutor js3 = (JavascriptExecutor) driver;
				js3.executeScript("arguments[0].click()", clickSignOut);
				Thread.sleep(2000);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			statusOfProcess.add("fail");
		}
		return statusOfProcess;
	
	}
	public String clickDashboard(String data)
	{
		String status = "fail";
		String parentWindow = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			if(data.contains("Yes"))
			{
			try
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				js.executeScript("window.scrollBy(0,400)");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				WebElement clickDashboard = driver.findElement(By.xpath("//a[contains(text(),'Continue to your Dashboard')]"));
				js.executeScript("arguments[0].scrollIntoView();", clickDashboard);
				Thread.sleep(1000);
				if(clickDashboard.isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					js.executeScript("arguments[0].click()", clickDashboard);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					parentWindow = driver.getWindowHandle();
					Set<String> windows = driver.getWindowHandles();
					for(String window : windows)
					{
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("dashboard"))
						{
							driver.switchTo().window(window);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
							js.executeScript("window.scrollBy(0,200)");
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
							System.out.println("Landed to Dashboard Page");
							status = "success";
							Thread.sleep(1000);
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
				
			}
			else
			{
				driver.get("https://stage-in.skillup.online/");
				WebElement login = driver.findElement(By.cssSelector("li[class*='Header_loginBtn']>a"));
				 String n = Keys.chord(Keys.CONTROL, Keys.ENTER); 
				 login.sendKeys(n);
				regressionGenericLocator.loginProcess("hemamalini@skillup.tech-split-Test@123");
				status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	String getEnrolledCourseOrProgramName = "";
	String modifiedcourseName = "";//.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim()
	
	public String courseSearchFeature(String data)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String status = "";
		try
		{
			if(driver.findElements(By.xpath("//span[contains(@class,'Header_NOLoginDSKTXT')]")).size()>0)
			{

				WebElement focusSearchField = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] form#searchForm input#contentSearch"));
				if(focusSearchField.isDisplayed())
				{
					js.executeScript("arguments[0].click()", focusSearchField);
					focusSearchField.sendKeys(data);
					WebElement clickSearch = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] form#searchForm button#btnCheck"));
					if(clickSearch.isDisplayed())
					{
						js.executeScript("arguments[0].click()", clickSearch);
						WebElement clickCourseCard = driver.findElement(By.cssSelector("div[class*='RegularCourseCard_RegularcardLinks']>a"));
						if(clickCourseCard.isDisplayed())
						{
							String courseURL = clickCourseCard.getAttribute("href");
							String parentWindow = driver.getWindowHandle();
							driver.switchTo().newWindow(WindowType.TAB);
							driver.get(courseURL);
							Set<String> nextWindow = driver.getWindowHandles();
							for(String window : nextWindow)
							{
								driver.switchTo().window(window);
								if(driver.getCurrentUrl().contains(courseURL))
								{
									driver.switchTo().window(window);
									
									if(driver.getCurrentUrl().contains(OpenWebsite.setHost))
									{
										status = "pass";
										driver.close();
										driver.switchTo().window(parentWindow);
										break;
									}
								}
								driver.switchTo().window(window);
							}
							driver.switchTo().window(parentWindow);
						}
						WebElement clickDropDown = driver.findElement(By.cssSelector("li[class*='Header_SigNUP'] img[alt='icon']"));
						if(clickDropDown.isDisplayed())
						{
							js.executeScript("arguments[0].click()", clickDropDown);
							WebElement clickDashboard = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header_Primary']>li:nth-child(2)>a"));
							if(clickDashboard.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickDashboard);
								if(driver.getCurrentUrl().contains("dashboard"))
								{
									System.out.println("dashboard page");
								}
							}
						}
					}
				}
			
			}
			else
			{
				WebElement clickLogin = driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]"));
				if(clickLogin.isDisplayed())
				{
					js.executeScript("arguments[0].click()", clickLogin);
				}
				WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
				email.sendKeys("hemamalini@skillup.tech");
				WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
				password.sendKeys("Test@123");
				WebElement submit = driver.findElement(By.xpath("//input[@id='login_in']"));
				submit.click();
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("/dashboard/"))
					{
						driver.switchTo().window(window);


						WebElement focusSearchField = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] form#searchForm input#contentSearch"));
						if(focusSearchField.isDisplayed())
						{
							js.executeScript("arguments[0].click()", focusSearchField);
							focusSearchField.sendKeys(data);
							WebElement clickSearch = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] form#searchForm button#btnCheck"));
							if(clickSearch.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickSearch);
								WebElement clickCourseCard = driver.findElement(By.cssSelector("div[class*='RegularCourseCard_RegularcardLinks']>a"));
								if(clickCourseCard.isDisplayed())
								{
									String courseURL = clickCourseCard.getAttribute("href");
									String parentWindow = driver.getWindowHandle();
									driver.switchTo().newWindow(WindowType.TAB);
									driver.get(courseURL);
									Set<String> nextWindow = driver.getWindowHandles();
									for(String win : nextWindow)
									{
										driver.switchTo().window(win);
										if(driver.getCurrentUrl().contains(courseURL))
										{
											driver.switchTo().window(win);
											
											if(driver.getCurrentUrl().contains(OpenWebsite.setHost))
											{
												status = "pass";
												driver.close();
												driver.switchTo().window(parentWindow);
												break;
											}
										}
									}
									driver.switchTo().window(parentWindow);
								}
								WebElement clickDropDown = driver.findElement(By.cssSelector("li[class*='Header_SigNUP'] img[alt='icon']"));
								if(clickDropDown.isDisplayed())
								{
									js.executeScript("arguments[0].click()", clickDropDown);
									WebElement clickDashboard = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header_Primary']>li:nth-child(2)>a"));
									if(clickDashboard.isDisplayed())
									{
										js.executeScript("arguments[0].click()", clickDashboard);
										if(driver.getCurrentUrl().contains("dashboard"))
										{
											System.out.println("dashboard page");
										}
									}
								}
							}
						}
					
					
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
	
	
	public String enrolledCourse()
	{
		String status = "fail";
		String getOrderNumber = regressionGenericLocator.orderNumber;
		getEnrolledCourseOrProgramName = regressionGenericLocator.getEnrolledCourseName;
		String getCourseName[] = getEnrolledCourseOrProgramName.split("in", 2);
		try
		{
			if(courseName.replaceAll("\\s", "").trim().contains(getCourseName[1].replaceAll("[^a-zA-Z0-9]", " ").replaceAll("with professional certificate", "").replaceAll("\\s", "").trim()))
			{
				System.out.println("course enrolled for "+getCourseName[1].replaceAll("[^a-zA-Z0-9]", " ").replaceAll("with professional certificate", "").replaceAll("\\s", "").trim());
				status = "success";
				modifiedcourseName = getCourseName[1].replaceAll("[^a-zA-Z0-9]", " ").replaceAll("with professional certificate", "").replaceAll("\\s", "").trim();
			}
			else
			{
				status = courseName;
				status = "success";
				modifiedcourseName = courseName.replaceAll("\\s", "").trim();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
			status = courseName;
		}
		return status;
	}
	public String verfiyRelatedProgramFromDashboard(String data)
	{
		String status = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("Related Program Function validation");
			try
			{
				String courseName = regressionGenericLocator.getEnrolledCourseName;
				System.out.println("course name : "+modifiedcourseName);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				List<WebElement> titles = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
				for(int i = 0; i < titles.size(); i++)
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					if(modifiedcourseName.equalsIgnoreCase(titles.get(i).getAttribute("id").replaceAll("[-+.^:,]","").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim()))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						System.out.println("let click related program for this course : "+courseName);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						List<WebElement> relatedPgm = titles.get(i).findElements(By.cssSelector(" section[class*='dashboardCourseCards_containerBottom'] p[class*='dashboardCourseCards_relatedProgramsLink']"));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						if(relatedPgm.size()>0)
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							System.out.println("Related Program is present : " + relatedPgm.get(0).getText());
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							js.executeScript("arguments[0].click()", relatedPgm.get(0));
							status = "pass";
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							WebElement checkProgramSection = driver.findElement(By.cssSelector("ul[class*='navigation_containerList']>li[class*='navigation_selected']"));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							if(checkProgramSection.isDisplayed())
							{
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
								if(checkProgramSection.getText().equalsIgnoreCase("Programs"))
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
									System.out.println("Landed on program section");
									List<WebElement> courseSectionLocator = driver.findElements(By.cssSelector("ul[class*='navigation_containerList']>li"));
									for(int j = 0; j < courseSectionLocator.size(); j++)
									{
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
										if(courseSectionLocator.get(j).getText().contains("Courses"))
										{
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
											js.executeScript("arguments[0].click()", courseSectionLocator.get(j));
											System.out.println("Redirected to course section");
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
											break;
										}
									}
								}
							}
							break;
						}
						else
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
							System.out.println("Related Program is not present : " );
							status = "pass";
							break;
						}
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
	
	public ArrayList<String> verfiySelfPacedCourse()
	{
		ArrayList<String> getStatus = new ArrayList<String>();
		String courseName = "";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			List<WebElement>  selfPacedLocator = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			for(int i = 0; i < selfPacedLocator.size(); i++)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				String checkSelfPacedcourse = selfPacedLocator.get(i).findElement(By.cssSelector(" div[class*='dashboardCourseCards_dataOthers'] p[class*='dashboardCourseCards_otherBright']")).getText();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				if(checkSelfPacedcourse.contains("Self-paced"))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(selfPacedLocator.get(i).findElements(By.cssSelector(" div[class*='dashboardCourseCards_dataOthers'] p[class*='dashboardCourseCards_otherBright']>:not([span])")).size()!=1)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						courseName = selfPacedLocator.get(i).findElement(By.cssSelector(" p[class*='dashboardCourseCards_dataCourseTitle']")).getText();
						System.out.println("self paced course has no date details" + courseName);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					}
					else
					{
						WebElement checkSelfPacedDate = selfPacedLocator.get(i).findElement(By.cssSelector(" div[class*='dashboardCourseCards_dataOthers'] p[class*='dashboardCourseCards_otherBright']>:not([span])"));
						if(!checkSelfPacedDate.getText().replaceAll("\\s", "").trim().contains(" | Course Archived".replaceAll("\\s", "").trim()))
						{
							getStatus.add(courseName+" : date present for self paced. Its fail");
						}
						else
						{
							System.out.println("course archeived ");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getStatus;
	}
	public ArrayList<String> verfiyVILTCourse()
	{
		ArrayList<String> getStatus = new ArrayList<String>();
		String courseName = "";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			List<WebElement>  selfPacedLocator = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			for(int i = 0; i < selfPacedLocator.size(); i++)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				String checkSelfPacedcourse = selfPacedLocator.get(i).findElement(By.cssSelector(" div[class*='dashboardCourseCards_dataOthers'] p[class*='dashboardCourseCards_otherBright']")).getText();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				if(checkSelfPacedcourse.contains("vILT"))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(selfPacedLocator.get(i).findElements(By.cssSelector(" span")).size() > 0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						courseName = selfPacedLocator.get(i).findElement(By.cssSelector(" p[class*='dashboardCourseCards_dataCourseTitle']")).getText();
						System.out.println("VILT course has date details" + courseName);
					}
					else
					{
						getStatus.add(courseName + "date not present for VILT. Its fail.");
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getStatus;
	}
	
	public ArrayList<String> checkBlendedCourse()
	{
		ArrayList<String> getStatus = new ArrayList<String>();
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			List<WebElement>  selfPacedLocator = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			for(int i = 0; i < selfPacedLocator.size(); i++)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				String checkSelfPacedcourse = selfPacedLocator.get(i).findElement(By.cssSelector(" div[class*='dashboardCourseCards_dataOthers'] p[class*='dashboardCourseCards_otherBright']")).getText();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				if(checkSelfPacedcourse.contains("Blended"))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					if(selfPacedLocator.get(i).findElements(By.cssSelector(" span")).size() <= 0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						courseName = selfPacedLocator.get(i).findElement(By.cssSelector(" p[class*='dashboardCourseCards_dataCourseTitle']")).getText();
						System.out.println("Blended course has date details" + courseName);
						getStatus.add(courseName);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getStatus;
	}
	public ArrayList<String> verfiyPartnerIconRedirectionFromCourse()
	{
		ArrayList<String> getURLStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			String parentWindow = driver.getWindowHandle();
			List<WebElement> basicLocator = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id] a[class*='dashboardCourseCards_dataTagsDark']"));
			for(int i = 0; i < basicLocator.size(); i++)
			{
				String getPartnerURL = basicLocator.get(i).getAttribute("href");
				String getPartnerName = basicLocator.get(i).getText();
				
				if(!getPartnerName.equalsIgnoreCase("Google"))
				{
					String url = microsoftCourseLocator.checkURLStatus(getPartnerURL);
					if(url.equalsIgnoreCase("fail"))
					{
						getURLStatus.add(getPartnerURL);
					}
					else
					{
						String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
						basicLocator.get(i).sendKeys(n);
						
						
						Set<String> childWnidow = driver.getWindowHandles();
						for(String windows : childWnidow)
						{
							driver.switchTo().window(windows);
							if(driver.getCurrentUrl().contains(getPartnerURL))
							{
								driver.switchTo().window(windows);
								driver.close();
								driver.switchTo().window(parentWindow);
								break;
							}
						}
						driver.switchTo().window(parentWindow);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getURLStatus;
	}
	public String enrolledProgram()
	{
		String status = "fail";
		try
		{
			List<WebElement> programs = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			for(int i = 0; i < programs.size(); i++)
			{
				if(programs.get(i).getAttribute("id").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim().equalsIgnoreCase(courseName.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim()))
				{
					System.out.println("enrolled program is available ");
					status = "pass";
				}
			}
		}
		catch(Exception e)
		{
			status = "fail";
		}
		return status;
	}

	
	  public ArrayList<String> verfiyShareCourseFromDashboard(ArrayList<String> share) 
	  {
		  ArrayList<String> status = new ArrayList<String>(); 
		  JavascriptExecutor js = (JavascriptExecutor) driver; 
		  try 
		  {
			  List<WebElement> checkcourseName = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			  for(int j = 0; j < checkcourseName.size(); j++)
			  {
				  if(checkcourseName.get(j).getAttribute("id").replaceAll("[^a-zA-Z0-9]"," ").replaceAll("\\s", "").trim().equalsIgnoreCase(modifiedcourseName))
				  {
					  js.executeScript("window.scrollBy(0,200)");
					  WebElement shareIcons = checkcourseName.get(j).findElement(By.cssSelector(" [class*='dashboardCourseCards_containerBottom'] div[href]>p"));
						 if(shareIcons.isDisplayed())
						 {
							 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
							 js.executeScript("arguments[0].click()", shareIcons);
							 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
						 }
					  WebElement clickCopy = driver.findElement(By.cssSelector("button[class*='btn shadow-none shareSocialMedia_copyButton']"));
					 if(clickCopy.isDisplayed())
					 {
						 js.executeScript("arguments[0].click()", clickCopy);
					 }
					  List<WebElement> socialLink = driver.findElements(By.cssSelector("div[class*='shareSocialMedia_sociallist']>ul>li>a"));
					  for(int k = 0; k < socialLink.size(); k++) 
					  {
						  String parentWindow = driver.getWindowHandle();
						  if(socialLink.get(k).getAttribute("href").contains(share.get(3)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  {
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("linkedin"))
								  {
									  driver.switchTo().window(window);
									  System.out.println("Linked In page opened");
									  driver.close();
									  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
									  driver.switchTo().window(parentWindow);
									  break;
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(1)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("whatsapp"))
								  {
									  driver.switchTo().window(window);
									  System.out.println("whatsapp page opened");
									  driver.close();
									  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
									  driver.switchTo().window(parentWindow);
									  break;
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(4)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("facebook"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("facebook page opened");
									  driver.close();
									  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
									  driver.switchTo().window(parentWindow);
									  break;
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(2)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("x.com"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("twitter page opened");
									  driver.close();
									  driver.switchTo().window(parentWindow);
									  break;
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
					  } 
					  WebElement clickCloseFromPopup = driver.findElement(By.cssSelector("div[class='modal-dialog modal-dialog-centered'] button[class*='close']"));
					  if(clickCloseFromPopup.isDisplayed())
					  {
						  clickCloseFromPopup.click();
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
	 
	public ArrayList<String> checkSocialLinkFromCourse(ArrayList<String> socialLinks)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		try
		{
			List<WebElement> checkcourseName = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			for(int j = 0; j < checkcourseName.size(); j++)
			{
				if(checkcourseName.get(j).getAttribute("id").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim().equalsIgnoreCase(modifiedcourseName))
				{
					WebElement shareIcons = checkcourseName.get(j).findElement(By.cssSelector(" [class*='dashboardCourseCards_containerBottom'] div[href]>p"));
					js.executeScript("arguments[0].scrollIntoView();", shareIcons);	
					if(shareIcons.isDisplayed())
					{
						//shareIcons.click();
						js.executeScript("arguments[0].click()", shareIcons);
					}
					WebElement clickCopy = driver.findElement(By.cssSelector("button[class*='btn shadow-none shareSocialMedia_copyButton']"));
					clickCopy.click();
					
					List<WebElement> socialLink = driver.findElements(By.cssSelector("div[class*='shareSocialMedia_sociallist']>ul>li>a"));
					for(int k = 0; k < socialLink.size(); k++)
					{
						for(int l = 1; l < socialLinks.size(); l++)
						{
							if(socialLink.get(k).getAttribute("href").contains("mailto:"))
							{
								System.out.println("microsoft mail not open ");
							}
						
								if(socialLink.get(k).getAttribute("href").contains(socialLinks.get(l)))
								{
									socialLink.get(k).click();
									String parentWindow = driver.getWindowHandle();
									Set<String> listOfWindow = driver.getWindowHandles();
									for(String window : listOfWindow)
									{
										driver.switchTo().window(window);
										if(driver.getCurrentUrl().contains(socialLinks.get(l)))
										{
											driver.switchTo().window(window);
											System.out.println("open social links : "+socialLinks.get(l));
											status.add("pass");
											driver.close();
											  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
											break;
										}
									}
									driver.switchTo().window(parentWindow);
								}
							}
						}
						WebElement closePopup = driver.findElement(By.cssSelector("button[class*='btn-close shadow-none  shareSocialMedia_modalCloseBtn']"));
						closePopup.click();
						  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
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
	public String verifyProgramPage()
	{
		String status = "fail";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			String parentWindow = driver.getWindowHandle();
			if(driver.getCurrentUrl().contains("dashboard"))
			{
				js.executeScript("window.scrollBy(0,-500)");
				WebElement clickProgram = driver.findElement(By.xpath("//ul[contains(@class,'navigation_containerList')]/li[contains(text(),'Programs') and not(@class='navigation_selected')]"));
				if(!clickProgram.isDisplayed())
				{
					js.executeScript("arguments[0].scrollIntoView();", clickProgram);
					WebElement clickCourses = driver.findElement(By.xpath("//ul[contains(@class,'navigation_containerList')]/li[contains(text(),'Programs') and not(@class='navigation_selected')]"));
					js.executeScript("arguments[0].click()", clickCourses);
					System.out.println("redirected to program section");
					status = "pass";
					List<WebElement> programs = driver.findElements(By.xpath("//section[contains(@class,'dashboardProgramCards_cardContainer')]"));
					for(int i = 0; i < programs.size(); i++)
					{
						WebElement checkImage = programs.get(i).findElement(By.xpath(".//div[contains(@class,'dashboardProgramCards_containerTopImage')]//img"));
						if(checkImage.isDisplayed())
						{
							checkImage.click();
							if(driver.getCurrentUrl().contains("/dashboard/"))
							{
								System.out.println("same page");
							}
							else
							{
								System.out.println("page is differents");
							}
						}
						WebElement checkTitle = driver.findElement(By.xpath(".//div[contains(@class,'dashboardProgramCards_dataCourseTitle')]"));
						checkTitle.click();
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
						}
						WebElement checkPartner = driver.findElement(By.xpath(".//a[contains(@class,'dashboardProgramCards_dataTagsDark')]"));
						checkPartner.click();
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
							driver.navigate().back();
							if(driver.getCurrentUrl().contains("/dashboard/"))
							{
								System.out.println("landed to dashboard");
							}
						}
						WebElement checkCategory = driver.findElement(By.xpath(".//a[contains(@class,'dashboardProgramCards_dataTagsBright')]"));
						checkCategory.click();
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
						}
					}
				}
				else
				{
					System.out.println("It already in course section");
					WebElement clickProgramSection = driver.findElement(By.xpath("//ul[contains(@class,'navigation_containerList')]/li[contains(text(),'Programs') and not(@class='navigation_selected')]"));
					js.executeScript("arguments[0].click()", clickProgramSection);
					List<WebElement> programs = driver.findElements(By.xpath("//section[contains(@class,'dashboardProgramCards_cardContainer')]"));
					for(int i = 0; i < programs.size(); i++)
					{
						WebElement checkImage = programs.get(i).findElement(By.xpath(".//div[contains(@class,'dashboardProgramCards_containerTopImage')]//img"));
						if(checkImage.isDisplayed())
						{
							js.executeScript("arguments[0].click()", checkImage);
							if(driver.getCurrentUrl().contains("/dashboard/"))
							{
								System.out.println("same page");
							}
							else
							{
								System.out.println("page is differents");
							}
						}
						WebElement checkTitle = programs.get(i).findElement(By.xpath(".//div[contains(@class,'dashboardProgramCards_dataCourseTitle')]"));
						js.executeScript("arguments[0].click()", checkTitle);
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
						}
						WebElement checkCategory = programs.get(i).findElement(By.xpath(".//a[contains(@class,'dashboardProgramCards_dataTagsBright')]"));
						js.executeScript("arguments[0].click()", checkCategory);
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
						}
						WebElement checkPartner = driver.findElement(By.xpath(".//a[contains(@class,'dashboardProgramCards_dataTagsDark')]"));
						js.executeScript("arguments[0].click()", checkPartner);
						if(driver.getCurrentUrl().contains("/dashboard/"))
						{
							System.out.println("same page");
						}
						else
						{
							System.out.println("page is differents");
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
							driver.navigate().back();
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
							if(driver.getCurrentUrl().contains("/dashboard/"))
							{
								System.out.println("landed to dashboard");
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));

							}
						}
						
					}
					status = "pass";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> checkSocialLinkfromProgram(ArrayList<String> share)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			List<WebElement> programs = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
			for(int i = 0; i < programs.size(); i++)
			{
				if(programs.get(i).getAttribute("id").replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").trim().equalsIgnoreCase(courseName))
				{
					System.out.println("enrolled program is available ");
					WebElement clickShareIcon = programs.get(i).findElement(By.cssSelector(" section[class*='dashboardProgramCards_containerBottom']>div>div[class*='dashboardProgramCards_actionShare']"));
					js.executeScript("arguments[0].scrollIntoView();", clickShareIcon);	
					if(clickShareIcon.isDisplayed())
					{
						clickShareIcon.click();
					}
					WebElement clickCopy = driver.findElement(By.cssSelector("button[class*='btn shadow-none shareSocialMedia_copyButton']"));
					if(clickCopy.isDisplayed())
					{
						
						clickCopy.click();
					}
					String parentWindow = driver.getWindowHandle();
					List<WebElement> socialLink = driver.findElements(By.cssSelector("div[class*='shareSocialMedia_sociallist']>ul>li>a"));
					for(int k = 0; k < socialLink.size(); k++)
					{
						 if(socialLink.get(k).getAttribute("href").contains(share.get(3)))
						 {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("linkedin"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("Linked In page opened");
									 status.add(microsoftCourseLocator.checkURLStatus(driver.getCurrentUrl()));
									  driver.close();
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(1)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("whatsapp"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("whatsapp page opened");
									  status.add(microsoftCourseLocator.checkURLStatus(driver.getCurrentUrl()));
									  driver.close();
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(4)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("facebook"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("facebook page opened");
									  status.add(microsoftCourseLocator.checkURLStatus(driver.getCurrentUrl()));;
									  driver.close();
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
						  else if(socialLink.get(k).getAttribute("href").contains(share.get(2)))
						  {
							  socialLink.get(k).click(); 
							  Set<String> windows = driver.getWindowHandles(); 
							  for(String window : windows)
							  { 
								  driver.switchTo().window(window);
								  if(driver.getCurrentUrl().contains("twitter"))
								  { 
									  driver.switchTo().window(window);
									  System.out.println("whatsapp page opened");
									  status.add(microsoftCourseLocator.checkURLStatus(driver.getCurrentUrl()));;
									  driver.close();
								  }
							  }
							  driver.switchTo().window(parentWindow);
						  }
					}
						WebElement closePopup = driver.findElement(By.cssSelector("button[class*='btn-close shadow-none  shareSocialMedia_modalCloseBtn']"));
						if(closePopup.isDisplayed())
						{
							closePopup.click();
						}
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

	public ArrayList<String> checkExpiredCourseOnDashboard(String data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			Set<String> windows = driver.getWindowHandles();
			
			for(String window : windows)
			{
				driver.switchTo().window(window);
				
				if(driver.getCurrentUrl().contains("dashboard"))
				{
					driver.switchTo().window(window);
					
					List<WebElement> checkcourseCards = driver.findElements(By.xpath("//section[contains(@class,'dashboardCourseCards_cardContainer')]//*[contains(text(),' | Course Archived')]/ancestor::section[contains(@class,'dashboardCourseCards_cardContainer')]/a"));
					for(int i = 0; i < checkcourseCards.size(); i++)
					{
						//WebElement checkExpireCourse = checkcourseCards.get(i).findElement(By.xpath("//*[contains(text(),' | Course Archived')]/ancestor::section[contains(@class,'dashboardCourseCards_cardContainer')]/a"));
						if(checkcourseCards.get(i).getAttribute("href") == null || checkcourseCards.get(i).getAttribute("href").isEmpty() || checkcourseCards.get(i).getAttribute("href").isBlank())
						{
							System.out.println("link not available on expired courses");
						}
						else
						{
							String title = checkcourseCards.get(i).getAttribute("id");
							System.out.println("Able to access expired course");
							status.add("fail "+title);
						}
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
	
	
	public ArrayList<String> checkCourseContentTabs(String data)
	{
		ArrayList<String> getStatus = new ArrayList<String>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		
		
		try
		{
			String parent = driver.getWindowHandle();
			
			Set<String> windows = driver.getWindowHandles();
			
			for(String win : windows)
			{
				driver.switchTo().window(win);
				
				if(driver.getCurrentUrl().contains("dashboard"))
				{
					driver.switchTo().window(win); //dashboard page landed

					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
					
						 List<WebElement> clickSkillupPartner = driver.findElements(By.xpath("//section[contains(@class,'dashboardCourseCards_cardContainer')]//following::a[contains(@class,'dashboardCourseCards_dataTagsDark')]"));
						  {
							  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							  
							  wait.until(ExpectedConditions.visibilityOfAllElements(clickSkillupPartner));
							  
							  for(int l = 0; l < clickSkillupPartner.size(); l++)
							  {
								  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
								  
								  wait.until(ExpectedConditions.visibilityOfAllElements(clickSkillupPartner.get(l)));
								  
								  String checkPartner = clickSkillupPartner.get(l).getText();
								  
								  
								  if(checkPartner.contains("SKillUp") || checkPartner.contains("Microsoft"))
								  {
									  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
									  
									  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
									  
									  WebElement clickCourse = clickSkillupPartner.get(l).findElement(By.xpath("//preceding::a[contains(@class,'dashboardCourseCards_spanLink')]"));
									  
									  js.executeScript("arguments[0].scrollIntoView();", clickCourse);	
									  
									  String courseContentWindow = driver.getWindowHandle();
									  
									  if(clickCourse.isDisplayed())
									  {
										  
											js.executeScript("arguments[0].scrollIntoView();", clickCourse);
											
											wait.until(ExpectedConditions.elementToBeClickable(clickCourse));
											
											 ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", clickCourse.getAttribute("href"));
											
											 Set<String> windowHandles = driver.getWindowHandles();
											 
										        for (String windowHandle : windowHandles)
										        {
										            if (!windowHandle.equals(win))
										            {
										                driver.switchTo().window(windowHandle);
										                
										                if(driver.getCurrentUrl().contains("/course/"))
														{
															
															driver.switchTo().window(windowHandle);
															
															System.out.println("Course Content view tab Page");
															
															List<WebElement> courseViewTabs= driver.findElements(By.cssSelector("div#courseTabsNavigation nav[class='nav flex-nowrap nav-underline-tabs']>a, div#content li[class*='nav-item']>a"));
															
															for(int i = 1; i < courseViewTabs.size(); i++)
															{
																driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
																
																js.executeScript("arguments[0].scrollIntoView();", courseViewTabs.get(i));
																
																if(courseViewTabs.get(i).isDisplayed())
																 {
																	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
																	 
																	 js.executeScript("arguments[0].scrollIntoView();", courseViewTabs.get(i));
																	 
																	 String contentOnNewPage = Keys.chord(Keys.CONTROL, Keys.ENTER); 
																	 
																	 courseViewTabs.get(i).sendKeys(contentOnNewPage);
																	 
																	 String courseContentTabs = driver.getWindowHandle();
																	 
																		Set<String> allTabs = driver.getWindowHandles();
																		
																		for(String tab : allTabs)
																		{
																			
																			driver.switchTo().window(tab);
																			
																			if(!driver.getCurrentUrl().contains("/course/") && !tab.equals(win) && !driver.getCurrentUrl().contains("dashboard") && !driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
																			{
																				driver.switchTo().window(tab);
																				
																				System.out.println("course content page : "+driver.getCurrentUrl());
																				
																				driver.close();
																				
																				driver.switchTo().window(courseContentTabs);
																			}
																			
																			driver.switchTo().window(courseContentTabs);
																			getStatus.add("pass");
																		}
																  }
																
																if(i == (courseViewTabs.size()-1))
																{
																	break;
																}
															}
															driver.close();
															}
										            }
										            driver.switchTo().window(courseContentWindow);
										        }
											
											}
									  
										
									  }  
										
								  driver.switchTo().window(parent);
								  }
							  }
					 }
				}
			
			
		}	
			
		catch(Exception e)
		{
			e.printStackTrace();
			getStatus.add("fail");
		}
		
		return getStatus;
	}
	public ArrayList<String> verifyIncludeCoursesFromProgram(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{

				js.executeScript("window.scrollBy(0,-300)");
				WebElement clickProgram = driver.findElement(By.cssSelector("ul[class*='navigation_containerList']>li[class='navigation_selected__Kzs2R']"));
				js.executeScript("arguments[0].scrollIntoView();", clickProgram);
				if(!clickProgram.getText().contains("Programs"))
				{
					WebElement clickProgramSection = driver.findElement(By.xpath("//li[contains(text(),'Programs')]"));
					js.executeScript("arguments[0].click()", clickProgramSection);
					System.out.println("program section is available");
					List<WebElement> programs = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
					for(int j = 0; j < programs.size(); j++)
					{
						if(j == 0)
						{
							WebElement expandCourse = programs.get(j).findElement(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses']>div[class*='dashboardProgramCards_coursesAccordionNav'] img[alt='expand icon']"));
							js.executeScript("arguments[0].scrollIntoView();", expandCourse);	
							if(expandCourse.isDisplayed())
							{
								js.executeScript("arguments[0].click()", expandCourse);
								if(driver.findElement(By.cssSelector("section[class='dashboardProgramCards_coursesAccordionList__NfQfc']")).isDisplayed())
								{
								
									System.out.println("Expanded Program");
									status.add("expanded");
									
									List<WebElement> clickCourses = programs.get(j).findElements(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses'] section[class*='dashboardProgramCards_coursesAccordionList']>div a"));
									System.out.println("Include courses from program : "+clickCourses.size());
									String baseWindow = driver.getWindowHandle();
									for(int k = 0; k < clickCourses.size(); k++)
									{
										if(clickCourses.get(k).isDisplayed())
										{
											String openNewTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
											clickCourses.get(k).sendKeys(openNewTab);
											Set<String> allWindows = driver.getWindowHandles();
											for(String windows : allWindows)
											{
												driver.switchTo().window(windows);
												if(driver.getCurrentUrl().contains("/course/") && !driver.getCurrentUrl().contains("dashboard") && !driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
												{
													driver.switchTo().window(windows);
													System.out.println("opened course : "+driver.getCurrentUrl());
													driver.close();
												}
											}
											driver.switchTo().window(baseWindow);
										}
									}
								}	
								WebElement unexpandCourse = programs.get(j).findElement(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses']>div[class*='dashboardProgramCards_coursesAccordionNav'] img[alt='collapse icon']"));
								if(unexpandCourse.isDisplayed())
								{
									System.out.println("un Expanded enrolled course");
									status.add("unExpanded");
								}
							}
						}
					}
					
				
				}
				else
				{
							System.out.println("program section is available");
							List<WebElement> programs = driver.findElements(By.cssSelector("section[class*='contentDashboardSection_contentCardsFall']>section[id]"));
							for(int j = 0; j < programs.size(); j++)
							{
								if(j == 0)
								{
									WebElement expandCourse = programs.get(j).findElement(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses']>div[class*='dashboardProgramCards_coursesAccordionNav'] img[alt='expand icon']"));
									js.executeScript("arguments[0].scrollIntoView();", expandCourse);	
									if(expandCourse.isDisplayed())
									{
										js.executeScript("arguments[0].click()", expandCourse);
										if(driver.findElement(By.cssSelector("section[class='dashboardProgramCards_coursesAccordionList__NfQfc']")).isDisplayed())
										{
										
											System.out.println("Expanded Program");
											status.add("expanded");
											
											List<WebElement> clickCourses = programs.get(j).findElements(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses'] section[class*='dashboardProgramCards_coursesAccordionList']>div a"));
											System.out.println("Include courses from program : "+clickCourses.size());
											String baseWindow = driver.getWindowHandle();
											for(int k = 0; k < clickCourses.size(); k++)
											{
												if(clickCourses.get(k).isDisplayed())
												{
													String openNewTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
													clickCourses.get(k).sendKeys(openNewTab);
													Set<String> allWindows = driver.getWindowHandles();
													for(String windows : allWindows)
													{
														driver.switchTo().window(windows);
														if(driver.getCurrentUrl().contains("course/"))
														{
															driver.switchTo().window(windows);
															System.out.println("opened course : "+driver.getCurrentUrl());
															driver.close();
														}
													}
													driver.switchTo().window(baseWindow);
												}
											}
										}	
										WebElement unexpandCourse = programs.get(j).findElement(By.cssSelector(" section[class*='dashboardProgramCards_containerCourses']>div[class*='dashboardProgramCards_coursesAccordionNav'] img[alt='collapse icon']"));
										if(unexpandCourse.isDisplayed())
										{
											System.out.println("un Expanded enrolled course");
											status.add("unExpanded");
										}
									}
								}
							}
							
						}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		WebElement clickDropdown = driver.findElement(By.cssSelector("li[class*='Header_SigNUP'] img[alt='icon']"));
		js.executeScript("arguments[0].scrollIntoView();", clickDropdown);
		if(clickDropdown.isDisplayed())
		{
			js.executeScript("arguments[0].click()", clickDropdown);
			
			WebElement clickSignout = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Header']>li:nth-child(5)>a"));
			if(clickSignout.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickSignout);
			}
		}
		String dashboardPage = driver.getWindowHandle();
		Set<String> allTabs = driver.getWindowHandles();
		for(String tab : allTabs)
		{
			driver.switchTo().window(tab);
			if(driver.getCurrentUrl().contains("dasboard"))
			{
				driver.switchTo().window(tab);
				driver.close();
				driver.switchTo().window(tab);
				if(driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setURL+"/"))
				{
					driver.switchTo().window(tab);
				}
			}
			driver.switchTo().window(dashboardPage);
		}
		return status;
	}
}

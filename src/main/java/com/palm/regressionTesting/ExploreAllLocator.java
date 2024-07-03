package com.palm.regressionTesting;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExploreAllLocator
{
	WebDriver driver;
	public ExploreAllLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ArrayList<String>  checkExploreAll()
	{
		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 800)", "");
			WebElement clickExploreAllIcon = driver.findElement(By.cssSelector("div[class*='Courses_circleBgContainer'] button[class='btn shadow-none LearningCatalog_exploreAllButton__KElSJ false']"));
			js.executeScript("arguments[0].scrollIntoView();", clickExploreAllIcon);
			
			wait.until(ExpectedConditions.visibilityOf(clickExploreAllIcon));
			js.executeScript("arguments[0].click()", clickExploreAllIcon);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			
			String parentWindow = driver.getWindowHandle();
			Set<String> window = driver.getWindowHandles();
			for(String allwindows : window)
			{
				driver.switchTo().window(allwindows);
				if(driver.getCurrentUrl().contains("explore"))
				{
					driver.switchTo().window(allwindows);
					System.out.println("Explore all from home page");
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("home");
		}
		try
		{
			WebElement clickMegaMenu = driver.findElement(By.cssSelector("div[class*='Header_headerRight'] a[id='navbarDropdown'] div[class*=' Header_category'] img[alt='icon']"));
			js.executeScript("arguments[0].scrollIntoView();", clickMegaMenu);
			if(clickMegaMenu.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickMegaMenu);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			//Thread.sleep(2000);
			WebElement clickExploreAll = driver.findElement(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='MainCatE'] li[class='exploreAll'] a"));
			js.executeScript("arguments[0].scrollIntoView();", clickExploreAll);
			if(clickExploreAll.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickExploreAll);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			String parentWindow = driver.getWindowHandle();
			Set<String> window = driver.getWindowHandles();
			for(String allwindows : window)
			{
				driver.switchTo().window(allwindows);
				if(driver.getCurrentUrl().contains("explore"))
				{
					driver.switchTo().window(allwindows);
					System.out.println("Explore all from mega menu");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("mega");
		}
		return status;
	}
	
	public String verifyActiveCategoriesOnHomePage()
	{
		String status = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		try
		{
			OpenWebsite.openSite(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 800)", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement activeCategory = driver.findElement(By.cssSelector("button[class*='LearningCatalog_activeButton']"));//section#scrollToTop div[class*='CourseSection_containerInnerFilter']>div:nth-child(4) div[class*='CourseSection_checkbox']>input[checked]
			js.executeScript("arguments[0].scrollIntoView();", activeCategory);
			String activeCategoryName = activeCategory.getText().trim().replace(" ", "");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			WebElement clickExploreAllIcon = driver.findElement(By.xpath("//div[contains(@class,'container-fluid Courses_containerInner')]/div[2]//button[contains(text(),'Explore All')]"));
			js.executeScript("arguments[0].scrollIntoView();", clickExploreAllIcon);
			wait.until(ExpectedConditions.visibilityOf(clickExploreAllIcon));
			if(clickExploreAllIcon.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickExploreAllIcon);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));;
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String windows : allWindows)
			{
				driver.switchTo().window(windows);
				if(driver.getCurrentUrl().contains("/explore-our-catalog/"))
				{
					driver.switchTo().window(windows);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					System.out.println("Explore all catalog page");
					WebElement baseLocatorForResult = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer'] section[class*='CourseSection_topFilterSection']"));
					js.executeScript("arguments[0].scrollIntoView();", baseLocatorForResult);
					
					WebElement locateSelectedCategory = baseLocatorForResult.findElement(By.cssSelector(" div[class*='CourseSection_resultsItemsBox'] p"));
					js.executeScript("arguments[0].scrollIntoView();", locateSelectedCategory);
					
					String getCategoryName = locateSelectedCategory.getText().trim().replace(" ", "");
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					if(getCategoryName.equalsIgnoreCase(activeCategoryName))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
						System.out.println("active category is same as in Explore All page");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
						status = "pass";
					}
					else
					{
						status = "fail";
					}
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status = "fail";
		}
			
		return status;
	}
		
	public ArrayList<String> nextPageCourses()
	{
		ArrayList<String> status = new ArrayList<String>();
		int repeat = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String getURL = null;
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			List<WebElement> clickNextPage = driver.findElements(By.cssSelector("div[class='col-12 d-flex justify-content-center mt-5'] ul[class='pagination justify-content-center'] li[class='page-item false'] a[aria-label*='Page']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			if(clickNextPage.size()>0)
			{
				for(int i = 0; i < clickNextPage.size(); i++)
				{
					js.executeScript("arguments[0].scrollIntoView();", clickNextPage.get(i));
					if(clickNextPage.get(i).isDisplayed())
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
						js.executeScript("arguments[0].click()", clickNextPage.get(i));
						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						
						List<WebElement> listOfNextPageCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class='CourseSection_courseResult__byBMX ps-3'] a"));
						for(int k = 0; k < listOfNextPageCourses.size(); k++)
						{
							while(repeat <= 3)
							try
							{
									getURL = listOfNextPageCourses.get(k).getAttribute("href");
									break;
							}
							catch(StaleElementReferenceException e)
							{
								e.printStackTrace();
							}
							String getURLLinkStatus = this.checkURLStatus(getURL);
							if(getURLLinkStatus.equalsIgnoreCase("fail"))
							{
								status.add(listOfNextPageCourses.get(k).getAttribute("href"));
							}
						}
					}
				}
				
			}
			else
			{
				System.out.println("next page is not shown");
			}
		}
		catch(Exception e)
		{
			System.out.println("Next Page not availables");
		}
		return status;
	}
	
	public String checkURLStatus(String getURL) throws IOException
	{
		String status = "fail";
		String addHosturl = getURL;
		HttpURLConnection huc = null;
		int respCode = 200;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
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
				status = "fail";
			}
			else
			{
				//System.out.println("un broken link"+addHosturl);
				status = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> verifyClearAllAtExploreAllPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			WebElement clickClearAll = driver.findElement(By.xpath("//div[contains(@class,'container-fluid CourseSection_containerInner')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
			js.executeScript("arguments[0].scrollIntoView();", clickClearAll);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			
			if(clickClearAll.isDisplayed())
			{
				System.out.println("clear All icon is shown");
				js.executeScript("arguments[0].click()", clickClearAll);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				WebElement baseLocatorForResult = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer']"));
				js.executeScript("arguments[0].scrollIntoView();", baseLocatorForResult);
				WebElement checkResult = baseLocatorForResult.findElement(By.cssSelector(" div[class*='CourseSection_topFilterResults'] p"));
				js.executeScript("arguments[0].scrollIntoView();", checkResult);
				if(checkResult.isDisplayed())
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					String getTextFromResult = checkResult.getText();
					System.out.println("REsult : "+getTextFromResult);
					if(getTextFromResult.contains("Results"))
					status.add("pass");
				}
				else
				{
					status.add("result");
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
				try
				{
					 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			            // Wait for the alert content to be present and visible
			            WebElement alertContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Help us find the right course for you.')]")));

			            // Scroll into view
			            js.executeScript("arguments[0].scrollIntoView();", alertContent);

			            // Re-check the visibility to ensure it's still there
			            alertContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Help us find the right course for you.')]")));

					
					
					if(alertContent.isDisplayed())
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
						String getTextFromAlertContent = alertContent.getText();
						System.out.println("help content : "+getTextFromAlertContent);
						if(getTextFromAlertContent.contains("Help"))
						{
							status.add("pass");
						}
					}
					else
					{
						status.add("content");
					}	
				}
				catch(Exception e)
				{
					try {
		                WebElement alertContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Help us find the right course for you.')]")));
		                js.executeScript("arguments[0].scrollIntoView();", alertContent);

		                if (alertContent.isDisplayed()) {
		                    String getTextFromAlertContent = alertContent.getText();
		                    System.out.println("Help content: " + getTextFromAlertContent);
		                    if (getTextFromAlertContent.contains("Help")) {
		                        status.add("pass");
		                    }
		                } else {
		                    status.add("content");
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        } 
				
				 
				System.out.println("clear all icon is clicked");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		return status;
	}
	
	public ArrayList<String> verify_CategoriesFromExploreAllPage()
	{
		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		try
		{
			try
			{
				String categoryName = "";
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
				WebElement baseLocatorForClearAll = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
				js.executeScript("arguments[0].scrollIntoView();", baseLocatorForClearAll);
				List<WebElement> listOfCategories = baseLocatorForClearAll.findElements(By.cssSelector(" div[class='accordion']:nth-child(1) div[class='accordion-collapse collapse show'] div[class*='CourseSection_checkbox'] input"));
				wait.until(ExpectedConditions.visibilityOfAllElements(listOfCategories));
				
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(" div[class='accordion']:nth-child(1) div[class='accordion-collapse collapse show'] div[class*='CourseSection_checkbox'] input"))));
				for(int i = 0; i < listOfCategories.size(); i++)
				{
					WebElement category = listOfCategories.get(i);
					js.executeScript("arguments[0].scrollIntoView();", category);
					categoryName = category.getAttribute("id");
					if(i >= 4)
					{
						js.executeScript("window.scrollBy(0, 200)", "");
					}
					Point position = category.getLocation();
					
					js.executeScript("window.scrollBy("+position.x+", "+(position.y -100)+")","");
					System.out.println(position.x + " -- " + (position.y-100));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					
					while(!category.isDisplayed())
					{
						js.executeScript("window.scrollBy(0, 100)","");
						wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(category)));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
						wait.until(ExpectedConditions.elementToBeClickable(category));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					js.executeScript("arguments[0].click()", category);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
					System.out.println("category Name : "+categoryName);
					
					if(!category.isSelected())
					{
						js.executeScript("arguments[0].click()", category);
						System.out.println("selected category Name is : "+categoryName);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					System.out.println(category.getAttribute("id"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					
					try
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
						String categoryCourseName = null;
						if(driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_RegularcardLinks']>a")).size()>0)
						{
						List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_RegularcardLinks']>a"));
						listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_RegularcardLinks']>a"));
						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
						
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
							for(int j = 0; j < listOfCourses.size(); j++)
							{
								try 
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
									categoryCourseName = listOfCourses.get(j).getAttribute("href");
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
								} 
								catch(StaleElementReferenceException e)
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
									System.out.println("facing stale element exception");
									listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_RegularcardLinks']>a"));
									categoryCourseName = listOfCourses.get(j).getAttribute("href");
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
								}
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
								wait.until(ExpectedConditions.visibilityOf(listOfCourses.get(j)));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								System.out.println("course name :   "+categoryCourseName);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								if(j == listOfCourses.size()-1)
								{
									driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
									if(driver.findElements(By.cssSelector("div[class*='content-center']>ul[class='pagination justify-content-center'] li[class='page-item false'] a[aria-label*='Page']")).size()>0)
									{
										List<WebElement> clickNextPage = driver.findElements(By.cssSelector("div[class*='content-center']>ul[class='pagination justify-content-center'] li[class='page-item false'] a[aria-label*='Page']"));
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
									
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
										System.out.println("next page is available");
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
										js.executeScript("window.scrollBy(0, 1700)","");
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									//	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='col-12 d-flex justify-content-center mt-5'] ul[class='pagination justify-content-center'] li[class='page-item false'] a[aria-label*='Page']")));
										for(int l = 0; l < clickNextPage.size(); l++)
										{
											js.executeScript("arguments[0].scrollIntoView(true);", clickNextPage.get(l));
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
											while(!clickNextPage.get(l).isDisplayed())
											{
												wait.until(ExpectedConditions.elementToBeClickable(clickNextPage.get(l)));
												js.executeScript("window.scrollBy(0,150)","");
												js.executeScript("arguments[0].scrollIntoView(true);", clickNextPage.get(l));
												driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
											}
											js.executeScript("arguments[0].click()", clickNextPage.get(l));
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
											System.out.println("next page : " +l);
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
											
											List<WebElement> listOfNextPageCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class*='RegularCourseCard_RegularcardLinks']>a"));
											for(int k = 0; k < listOfNextPageCourses.size(); k++)
											{
												js.executeScript("arguments[0].scrollIntoView(true);", listOfNextPageCourses.get(k));
												wait.until(ExpectedConditions.visibilityOf(listOfNextPageCourses.get(k)));
												
												System.out.println("next page courses : "+listOfNextPageCourses.get(k).getAttribute("href"));
											}
										}
										
									}
									else
									{
										System.out.println("no next page");
									}
							    }
						  }
						}
						else
						{
							status.add(categoryName);
							System.out.println("when select" +categoryName+"   courses not shown");
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("issue in courses");
						status.add(category.getAttribute("id"));
					}
					js.executeScript("window.scrollBy(0, -document.body.scrollHeight)","");
					
					if(driver.findElements(By.xpath("//div[contains(@class,'container-fluid CourseSection_containerInner')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]")).size()>0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						WebElement click_ClearAll = driver.findElement(By.xpath("//div[contains(@class,'container-fluid CourseSection_containerInner')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
						js.executeScript("arguments[0].scrollIntoView();", click_ClearAll);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						if(click_ClearAll.isDisplayed())
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
							wait.until(ExpectedConditions.elementToBeClickable(click_ClearAll));
							js.executeScript("arguments[0].click()", click_ClearAll);
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						}
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("issue in category");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> verify_LevelFromExploreAll()
	{
		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			try
			{	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				WebElement baseLocator = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
				js.executeScript("arguments[0].scrollIntoView();", baseLocator);
				js.executeScript("window.scrollBy(0, 700)", "");
				WebElement clickLevels = baseLocator.findElement(By.cssSelector(" div[class='accordion']:nth-child(2) button"));
				js.executeScript("arguments[0].click()", clickLevels);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				while(!clickLevels.isDisplayed())
				{
					js.executeScript("arguments[0].click()", clickLevels);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				}
				
				List<WebElement> listOfLevels = baseLocator.findElements(By.cssSelector(" div[class='accordion']:nth-child(2) div[class*='CourseSection_checkbox'] input"));
				wait.until(ExpectedConditions.visibilityOfAllElements(listOfLevels));
				for(int i = 0; i < listOfLevels.size(); i++)
				{
					WebElement level = listOfLevels.get(i);
					js.executeScript("arguments[0].scrollIntoView();", level);
					Point position = level.getLocation();
					
					js.executeScript("window.scrollBy("+position.x+", "+(position.y -100)+")","");
					System.out.println(position.x + " -- " + (position.y-100));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					
					js.executeScript("arguments[0].click()", level);
					
					
					if(!level.isSelected())
					{
						Actions actions = new Actions(driver);
						actions.moveToElement(level).click().perform();
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					System.out.println(level.getAttribute("id"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					status.add("pass");
					try
					{
						List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_courseHeading'] p"));
						System.out.println("number of courses from levels : "+listOfCourses.size());
						
						List<WebElement> clickNextPage = driver.findElements(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li"));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(clickNextPage.size()>0)
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li")));
							
							for(int l = 0; l < clickNextPage.size(); l++)
							{
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("arguments[0].scrollIntoView(true);", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("window.scrollBy(0,-200)","");
								js.executeScript("arguments[0].click()", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								System.out.println("next page : " +l);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								List<WebElement> listOfNextPageCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class='CourseSection_courseResult__byBMX ps-3'] a"));
								System.out.println("next page number of courses : "+listOfNextPageCourses.size());
							}
									
						}
						else
						{
							System.out.println("no next page");
						}
						status.add("pass");
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("issue in courses");
						status.add("course");
					}
					JavascriptExecutor jse2 = (JavascriptExecutor) driver;
					jse2.executeScript("window.scrollBy(0, -document.body.scrollHeight)","");
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
					
					//WebElement baseLocator_ClearAll = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
					//js.executeScript("arguments[0].scrollIntoView();", baseLocator_ClearAll);
					
					WebElement click_ClearAll = driver.findElement(By.xpath("//section[contains(@class,'CourseSection_mainContent')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
					js.executeScript("arguments[0].scrollIntoView();", click_ClearAll);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					
					if(click_ClearAll.isDisplayed())
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						wait.until(ExpectedConditions.elementToBeClickable(click_ClearAll));

						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						jse2.executeScript("arguments[0].click()", click_ClearAll);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("issue in levels");
				status.add("level");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<String> verify_learningPartnersFromExploreAll()
	{

		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		try
		{
			try
			{	
				WebElement baseLocator = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
				js.executeScript("arguments[0].scrollIntoView();", baseLocator);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				js.executeScript("window.scrollBy(0, 700)", "");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				WebElement clickLevels = baseLocator.findElement(By.cssSelector(" div[class='accordion']:nth-child(3) button"));
				js.executeScript("arguments[0].scrollIntoView();", clickLevels);
				if(clickLevels.isDisplayed())
				{
					
					js.executeScript("arguments[0].click()", clickLevels);
				}
				while(!clickLevels.isDisplayed())
				{
					clickLevels.click();
				}
				
				List<WebElement> listOfLevels = baseLocator.findElements(By.cssSelector(" div[class='accordion']:nth-child(3) div[class*='CourseSection_checkbox'] input"));
				wait.until(ExpectedConditions.visibilityOfAllElements(listOfLevels));
				for(int i = 0; i < listOfLevels.size(); i++)
				{
					WebElement level = listOfLevels.get(i);
					js.executeScript("arguments[0].scrollIntoView();", level);
					Point position = level.getLocation();
					
					js.executeScript("window.scrollBy("+position.x+", "+(position.y -100)+")","");
					System.out.println(position.x + " -- " + (position.y-100));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					
					js.executeScript("arguments[0].click()", level);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					
					if(!level.isSelected())
					{
						Actions actions = new Actions(driver);
						actions.moveToElement(level).click().perform();
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					System.out.println(level.getAttribute("id"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					status.add("pass");
					try
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_courseHeading'] p"));
						System.out.println("number of courses from levels : "+listOfCourses.size());
						
						List<WebElement> clickNextPage = driver.findElements(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li"));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(clickNextPage.size()>0)
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li")));
							
							for(int l = 0; l < clickNextPage.size(); l++)
							{
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("arguments[0].scrollIntoView(true);", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("window.scrollBy(0,-200)","");
								js.executeScript("arguments[0].click()", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								System.out.println("next page : " +l);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								List<WebElement> listOfNextPageCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class='CourseSection_courseResult__byBMX ps-3'] a"));
								System.out.println("next page number of courses : "+listOfNextPageCourses.size());
							}
									
						}
						else
						{
							System.out.println("no next page");
						}
						status.add("pass");
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("issue in courses");
						status.add("course");
					}
					js.executeScript("window.scrollBy(0, -document.body.scrollHeight)","");
					
					//section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])
				//	WebElement baseLocator_ClearAll = driver.findElement(By.cssSelector("//section[contains(@class,'CourseSection_mainContent')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
				//	js.executeScript("arguments[0].scrollIntoView();", baseLocator_ClearAll);
					WebElement click_ClearAll = driver.findElement(By.xpath("//section[contains(@class,'CourseSection_mainContent')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
					js.executeScript("arguments[0].scrollIntoView();", click_ClearAll);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					
					if(click_ClearAll.isDisplayed())
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						wait.until(ExpectedConditions.elementToBeClickable(click_ClearAll));

						js.executeScript("arguments[0].click()", click_ClearAll);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("issue in partner");
				status.add("partner");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	
		
	}
	public ArrayList<String> verify_learningStylesFromExploreAll()
	{

		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		try
		{
			try
			{	
				WebElement baseLocator = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
				js.executeScript("arguments[0].scrollIntoView();", baseLocator);
				js.executeScript("window.scrollBy(0, 700)", "");
				Thread.sleep(2000);
				WebElement clickLevels = baseLocator.findElement(By.cssSelector(" div[class='accordion']:nth-child(4) button"));
				js.executeScript("arguments[0].scrollIntoView();", clickLevels);
				js.executeScript("arguments[0].click()", clickLevels);
			//	Thread.sleep(2000);
				while(!clickLevels.isDisplayed())
				{
					clickLevels.click();
				}
				
				List<WebElement> listOfLevels = baseLocator.findElements(By.cssSelector(" div[class='accordion']:nth-child(4) div[class*='CourseSection_checkbox'] input"));
				wait.until(ExpectedConditions.visibilityOfAllElements(listOfLevels));
				for(int i = 0; i < listOfLevels.size(); i++)
				{
					WebElement level = listOfLevels.get(i);
					
					Point position = level.getLocation();
					
					js.executeScript("window.scrollBy("+position.x+", "+(position.y -100)+")","");
					System.out.println(position.x + " -- " + (position.y-100));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					
					js.executeScript("arguments[0].click()", level);
					
					
					if(!level.isSelected())
					{
						js.executeScript("arguments[0].click()", level);
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					System.out.println(level.getAttribute("id"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					status.add("pass");
					try
					{
						List<WebElement> listOfCourses = driver.findElements(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] div[class*='RegularCourseCard_courseHeading'] p"));
						System.out.println("number of courses from levels : "+listOfCourses.size());
						
						List<WebElement> clickNextPage = driver.findElements(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li"));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(clickNextPage.size()>0)
						{
							wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class*='col-12 d-flex justify-content-center'] ul[class='pagination justify-content-center'] li")));
							
							for(int l = 0; l < clickNextPage.size(); l++)
							{
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("arguments[0].scrollIntoView(true);", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								js.executeScript("window.scrollBy(0,-200)","");
								js.executeScript("arguments[0].click()", clickNextPage.get(l));
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
								System.out.println("next page : " +l);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
								List<WebElement> listOfNextPageCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class='CourseSection_courseResult__byBMX ps-3'] a"));
								System.out.println("next page number of courses : "+listOfNextPageCourses.size());
							}
									
						}
						else
						{
							System.out.println("no next page");
						}
						status.add("pass");
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("issue in courses");
						status.add("course");
					}
					js.executeScript("window.scrollBy(0, -document.body.scrollHeight)","");
					
					
					//WebElement baseLocator_ClearAll = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
					//js.executeScript("arguments[0].scrollIntoView(true);", baseLocator_ClearAll);
					WebElement click_ClearAll = driver.findElement(By.xpath("//section[contains(@class,'CourseSection_mainContent')]//div[contains(@class,'CourseSection_containerInnerFilter')]/div[3]//button[contains(text(),'Clear All')]"));
					js.executeScript("arguments[0].scrollIntoView(true);", click_ClearAll);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					
					if(click_ClearAll.isDisplayed())
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						wait.until(ExpectedConditions.elementToBeClickable(click_ClearAll));

						js.executeScript("arguments[0].click()", click_ClearAll);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("issue in style");
				status.add("style");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	
		
	}
	public ArrayList<String> verify_sortByFromExploreAll()
	{
		ArrayList<String> status = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js =  (JavascriptExecutor) driver;
		try
		{
			WebElement baseLocatorForClearAll = driver.findElement(By.cssSelector("section#scrollToTop div[class*='CourseSection_filterMain']:not([id='mobileFilter'])"));
			js.executeScript("arguments[0].scrollIntoView(true);", baseLocatorForClearAll);
			List<WebElement> listOfCategories = baseLocatorForClearAll.findElements(By.cssSelector(" div[class='accordion']:nth-child(1) div[class='accordion-collapse collapse show'] div[class*='CourseSection_checkbox'] input"));
			
			wait.until(ExpectedConditions.visibilityOfAllElements(listOfCategories));
			
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(" div[class='accordion']:nth-child(1) div[class='accordion-collapse collapse show'] div[class*='CourseSection_checkbox'] input"))));

			for(int i = 0; i < listOfCategories.size(); i++)
			{
				if(i == 0)
				{
					//listOfCategories.get(i).click();
					js.executeScript("arguments[0].click()", listOfCategories.get(i));
					break;
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			WebElement clickSortBy = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='dropdown CourseSection_topFilterSorting'] button"));
			js.executeScript("arguments[0].scrollIntoView(true);", clickSortBy);
			js.executeScript("arguments[0].click()", clickSortBy);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			try
			{
				WebElement selectHigh = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='dropdown CourseSection_topFilterSorting'] ul[class*='dropdown-menu'] li[value='DESC']"));
				js.executeScript("arguments[0].scrollIntoView(true);", selectHigh);
				js.executeScript("arguments[0].click()", selectHigh);
				List<WebElement> listOfCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] a"));
				for(int j = 0; j < listOfCourses.size(); j++)
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					System.out.println("list of courses from high to low : "+listOfCourses.get(j).getAttribute("href"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				}
			}
			catch(Exception e)
			{
				status.add("high");
			}
			try
			{
				WebElement clickSortByIcon = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='dropdown CourseSection_topFilterSorting'] button"));
				js.executeScript("arguments[0].scrollIntoView(true);", clickSortByIcon);
				js.executeScript("arguments[0].click()", clickSortByIcon);
				WebElement selectHigh = driver.findElement(By.cssSelector("div[class*='CourseSection_courseResultContainer'] div[class*='dropdown CourseSection_topFilterSorting'] ul[class*='dropdown-menu'] li[value='ASC']"));
				js.executeScript("arguments[0].scrollIntoView(true);", selectHigh);
				js.executeScript("arguments[0].click()", selectHigh);
				List<WebElement> listOfCourses = driver.findElements(By.cssSelector("section#scrollToTop div[class*='CourseSection_courseResultContainer'] div[class*='CourseSection_courseResult'] a"));
				for(int j = 0; j < listOfCourses.size(); j++)
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					System.out.println("list of courses from low to high : "+listOfCourses.get(j).getAttribute("href"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				}
			}
			catch(Exception e)
			{
				status.add("low");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}

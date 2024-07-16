package com.seo.regression.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourseLevelLocators
{
	WebDriver driver;
	IBMPageLocator ibmPageLocator;
	
	public CourseLevelLocators(WebDriver driver)
	{
		this.driver = driver;
		this.ibmPageLocator = new IBMPageLocator(this.driver);
	}
	
	public ArrayList<String> checkSelfPacedVILTOnHomePage()
	{
		ArrayList<String> status = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		try
		{
			if(driver.findElements(By.xpath("//section[contains(@class,'Courses_mainSection')]/div/div[@class='row'][2]//div[contains(@class,'LearningCatalog_browserCard')]")).size()>0)
			{
				List<WebElement> courseCards = driver.findElements(By.xpath("//section[contains(@class,'Courses_mainSection')]/div/div[@class='row'][2]//div[contains(@class,'LearningCatalog_browserCard')]"));
				for(int i = 0; i < courseCards.size(); i++)
				{
					if(courseCards.size()>0)
					{
						WebElement checkCourseCardLevel = courseCards.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/ul/li[1]"));
						js.executeScript("arguments[0].scrollIntoView();", checkCourseCardLevel);
						if(checkCourseCardLevel.getText().contains("Self-Paced"))
						{
							System.out.println("self paced course card");
							
							String parentWindow = driver.getWindowHandle();
							WebElement cardLink = checkCourseCardLevel.findElement(By.xpath(".//ancestor::a"));
							String url = cardLink.getAttribute("href");
							js.executeScript("arguments[0].scrollIntoView();", cardLink);
							if(cardLink.isDisplayed())
							{
								String urlStatus = ibmPageLocator.checkURLStatus(url);
								if(!urlStatus.contains("fail"))
								{
									driver.switchTo().newWindow(WindowType.TAB);
									driver.get(url);
									Set<String> allWindow = driver.getWindowHandles();
									for(String window : allWindow)
									{
										driver.switchTo().window(window);
										if(driver.getCurrentUrl().contains("courses"))
										{
											driver.switchTo().window(window);
											List<WebElement> checkStartDate = driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2']//h2"));
											for(int j = 0; j < checkStartDate.size(); j++)
											{
												if(checkStartDate.size()>0)
												{
													js.executeScript("arguments[0].scrollIntoView();", checkStartDate.get(j));
													if(checkStartDate.get(j).getText().contains("Starts on"))//self paced  level data validation
													{
														status.add(url);
														System.out.println("starts on date presented for Self paced course");
														driver.close();
														driver.switchTo().window(parentWindow);
														break;
													}
													else
													{
														System.out.println("starts on date not presented for Self paced course");
														driver.close();
														driver.switchTo().window(parentWindow);
														break;
													}
												}
												else
												{
													System.out.println("no levels on page");
													driver.close();
													driver.switchTo().window(parentWindow);
													break;
												}
												
											}
										}
									}
								}
								else
								{
									System.out.println("url facing issue : "+url);
									status.add(url + ": Issue in url");
									driver.close();
									driver.switchTo().window(parentWindow);
									break;
								}
							}
						}
					}
					else
					{
						System.out.println("no course card on homepage");
					}
					
				}
			}
		
			
			List<WebElement> courseCardsVILT = driver.findElements(By.xpath("//section[contains(@class,'Courses_mainSection')]/div/div[@class='row'][2]//div[contains(@class,'LearningCatalog_browserCard')]"));
			for(int i = 0; i < courseCardsVILT.size(); i++)
			{
				if(courseCardsVILT.size()>0)
				{
					WebElement checkCourseCardLevelVILT = driver.findElement(By.xpath("//div[contains(@class,'RegularCourseCard_courseHeading')]/ul/li[1]"));
					js.executeScript("arguments[0].scrollIntoView();", checkCourseCardLevelVILT);
					if(checkCourseCardLevelVILT.getText().contains("vILT")||checkCourseCardLevelVILT.getText().contains("Instructor-Led"))
					{
						System.out.println("VILT paced course card");
						String parentWindow = driver.getWindowHandle();
						WebElement VILTcardLink = checkCourseCardLevelVILT.findElement(By.xpath(".//ancestor::a"));
						String url = VILTcardLink.getAttribute("href");
						js.executeScript("arguments[0].scrollIntoView();", VILTcardLink);
						if(VILTcardLink.isDisplayed())
						{
							String urlStatus = ibmPageLocator.checkURLStatus(url);
							
							if(!urlStatus.contains("fail"))
							{
							driver.switchTo().newWindow(WindowType.TAB);
							driver.get(url);
							Set<String> allWindow = driver.getWindowHandles();
							for(String window : allWindow)
							{
								driver.switchTo().window(window);
								if(driver.getCurrentUrl().contains("courses"))
								{
									driver.switchTo().window(window);
									if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2'][1]//h2")).size()>0)
									{
										System.out.println("starts on date not presented for Blended course may be invite only vourse");
										driver.close();
										driver.switchTo().window(parentWindow);
										break;
									}
									else
									{
										WebElement checkStartDate = driver.findElement(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2'][1]//h2"));
										js.executeScript("arguments[0].scrollIntoView();", checkStartDate);
										
										if(!checkStartDate.getText().contains("Starts on"))
										{
											System.out.println("starts on date not presented for VILT course");
											status.add(url+" : starts on date not presented for VILT course");
											driver.close();
											driver.switchTo().window(parentWindow);
											break;
										}
									}
									}
								}
							}
						}
						else
						{
							System.out.println("url facing issue : "+url);
							status.add(url+" : issue on url");
							driver.close();
							driver.switchTo().window(parentWindow);
							break;
						}
						}
					}
				}

			List<WebElement> courseCardsBlended = driver.findElements(By.xpath("//section[contains(@class,'Courses_mainSection')]/div/div[@class='row'][2]//div[contains(@class,'LearningCatalog_browserCard')]"));
			for(int i = 0; i < courseCardsBlended.size(); i++)
			{
				if(courseCardsBlended.size()>0)
				{
					WebElement checkCourseCardLevelBlended = driver.findElement(By.xpath("//div[contains(@class,'RegularCourseCard_courseHeading')]/ul/li[1]"));
					js.executeScript("arguments[0].scrollIntoView();", checkCourseCardLevelBlended);
					if(checkCourseCardLevelBlended.getText().contains("BLENDED"))
					{
						System.out.println("Blended  course card");
						String parentWindow = driver.getWindowHandle();
						WebElement BlendedcardLink = checkCourseCardLevelBlended.findElement(By.xpath(".//ancestor::a"));
						String url = BlendedcardLink.getAttribute("href");
						js.executeScript("arguments[0].scrollIntoView();", BlendedcardLink);
						if(BlendedcardLink.isDisplayed())
						{
							String urlStatus = ibmPageLocator.checkURLStatus(url);
							
							if(!urlStatus.contains("fail"))
							{
							driver.switchTo().newWindow(WindowType.TAB);
							driver.get(url);
							Set<String> allWindow = driver.getWindowHandles();
							for(String window : allWindow)
							{
								driver.switchTo().window(window);
								if(driver.getCurrentUrl().contains("courses"))
								{
									driver.switchTo().window(window);
									if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2'][1]//h2")).size()>0)
									{
										System.out.println("starts on date not presented for Blended course may be invite only vourse");
										driver.close();
										driver.switchTo().window(parentWindow);
										break;
									}
									else
									{
										WebElement checkStartDate = driver.findElement(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2'][1]//h2"));
										js.executeScript("arguments[0].scrollIntoView();", checkStartDate);
										
										if(!checkStartDate.getText().contains("Starts on")||checkStartDate.getText().contains("Starts on"))
										{
											System.out.println("starts on date not presented for Blended course");
											status.add(url+" : starts on date not presented for Blended course");
											driver.close();
											driver.switchTo().window(parentWindow);
											break;
										}
									}
									}
								}
							}
						}
						else
						{
							System.out.println("url facing issue : "+url);
							status.add(url+" : issue on url");
							driver.close();
							driver.switchTo().window(parentWindow);
							break;
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
		return status;
	}
	
	
	
	public ArrayList<String> checkSelfPacedVILTOnCategory(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			
			WebElement clickDropdown = driver.findElement(By.xpath("//li[contains(@class,'nav-item dropdown Header_dropdown')]//img[@alt='icon']"));
			
			js.executeScript("arguments[0].scrollIntoView();", clickDropdown);
			
			if(clickDropdown.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickDropdown);
			}
				
			List<WebElement> clickCategory = driver.findElements(By.xpath("//div[contains(@class,'Header_headerRight')]//ul[contains(@class,'dropdown-menu dropdown-cat Header_dropdownMenu')]//div[@class='MainCatE catcolumn divbox1']/ul/li/a"));
		//list of categories 
			for(int k = 0 ; k < clickCategory.size(); k++)
			{
				js.executeScript("arguments[0].scrollIntoView();", clickCategory.get(k));
					
				if(k == 12)
				{
					continue;
				}
				
				if(clickCategory.get(k).isDisplayed())
				{
					String url = clickCategory.get(k).getAttribute("href");
					
					String categoryName = clickCategory.get(k).getText();
						
					System.out.println("category name : "+categoryName);
					
					if(categoryName.equalsIgnoreCase("Azure"))
					{
						continue;
					}
					
					if(!categoryName.equalsIgnoreCase("Modern Workplace"))
					{
						String parentWindow = driver.getWindowHandle(); // mega menu section from drop down list
						
						driver.switchTo().newWindow(WindowType.TAB);
							
						driver.get(url);//opening a category page
							
						Set<String> allWindow = driver.getWindowHandles();
							
						for(String window : allWindow)
						{
								
							driver.switchTo().window(window);
								
							if(driver.getCurrentUrl().contains("?utm"))
							{
								driver.switchTo().window(window);
									
								String categoryWindow = driver.getWindowHandle(); //category page
									
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									//program cards verification
								if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='ManageCardsLimit_showMoreSection']>button")).size()>0)
								{
									System.out.println("Program card verification started");
										
									if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='ManageCardsLimit_showMoreSection']>button")).size()>0)
									{
										
										WebElement clickShowMoreIcon = driver.findElement(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='ManageCardsLimit_showMoreSection']>button"));
										
										js.executeScript("arguments[0].scrollIntoView();", clickShowMoreIcon);
										
										while(clickShowMoreIcon.isDisplayed() && clickShowMoreIcon.getText().contains("more"))
										{
											js.executeScript("arguments[0].click()", clickShowMoreIcon);
										}
									}
									
										
									List<WebElement> programLinks = driver.findElements(By.cssSelector("section#scrollToTop>div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='LearningCatalog_cardRow'] div[class*='FlatCourseCard_FlatcardLinks']"));
									
									for(int a = 0; a < programLinks.size(); a++)
									{
										
										//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
										
										 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
										 
							             WebElement label = wait.until(ExpectedConditions.visibilityOf(programLinks.get(a)));
										
										js.executeScript("arguments[0].scrollIntoView();", label);
										
										//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
										
										WebElement urlLink = label.findElement(By.cssSelector(" a"));
										
										WebElement programCardNameLocator = label.findElement(By.cssSelector(" h2"));
										
										String programCardName = programCardNameLocator.getText();
										
										String programURL = urlLink.getAttribute("href");
										
										//program card 1st level
										
										String checkProgramCardLevel = label.findElement(By.cssSelector(" div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1)")).getText();
										
										driver.switchTo().newWindow(WindowType.TAB);
										
										driver.get(programURL);
											
										Set<String> allWindows = driver.getWindowHandles();
										
										for(String nextWindow: allWindows)
										{
											driver.switchTo().window(nextWindow);
												
											if(!driver.getCurrentUrl().equalsIgnoreCase("data:,")&&!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")&&!driver.getCurrentUrl().contains("?utm_source=websiteinternal"))
											{
												driver.switchTo().window(nextWindow);
												
												WebElement checkLevel = driver.findElement(By.cssSelector("div[class*='CourseDescription_TooltipAboutSection']>p:nth-child(1), div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1), div[class*='CourseDescription_levelSection']>h2"));
												
												if(checkLevel.getText().equalsIgnoreCase(checkProgramCardLevel))
												{
													System.out.println("card and page level same");
													
													if(checkLevel.getText().equalsIgnoreCase("SELF-PACED"))
													{
														if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
														{
															
															WebElement courseDuration1 = driver.findElement(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) div[class*='CourseDescription_courseAboutTextSection']>h2"));
															
															if(courseDuration1.getText().equalsIgnoreCase("Starts on"))
															{
																status.add("starts on date is presented for self paced level : "+programCardName);
																driver.close();
																driver.switchTo().window(categoryWindow);
															}
															else
															{
																driver.close();
																driver.switchTo().window(categoryWindow);
															}
														}
														else
														{
															status.add("level is not presented for self paced : "+programCardName);
															driver.close();
															driver.switchTo().window(categoryWindow);
														}
														
													}
													else if(checkLevel.getText().equalsIgnoreCase("vILT")||checkLevel.getText().equalsIgnoreCase("Instructor-Led"))
													{
														
														if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)//VERIFYING starts on date is available or not
														{
															WebElement courseDuration = driver.findElement(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) div[class*='CourseDescription_courseAboutTextSection']>h2"));
															if(!courseDuration.getText().equalsIgnoreCase("Starts on"))
															{
																status.add("starts on date is not presented for VILT and Instructor : "+programCardName);
																driver.close();
																driver.switchTo().window(categoryWindow);
															}
															else
															{
																driver.close();
																driver.switchTo().window(categoryWindow);
															}
														}
														else
														{
															status.add("level is not presented for VILT and Instructor : "+programCardName);
															driver.close();
															driver.switchTo().window(categoryWindow);
														}
													}
													else
													{
														System.out.println("Blended level");
														driver.close();
														driver.switchTo().window(categoryWindow);
													}
												}
												else
												{
													System.out.println("card and page level not same");
													status.add("card and page level not same " +programCardName+ " in "+ url +" category.");
													driver.close();
													driver.switchTo().window(categoryWindow);
												}
											
											}
											
										}
											
											
										if(a == programLinks.size()-1)
										{
											break;
										}
									}
									
								}
								else
								{
									System.out.println("program card not available");
									
								}
									
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
									
								if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(5) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='container-fluid Courses_containerInner']>div:nth-child(2) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks']")).size()>0)
								{
									System.out.println("Course card verification started");
										
									if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(5) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='container-fluid Courses_containerInner']>div:nth-child(2) div[class*='ManageCardsLimit_showMoreSection']>button")).size()>0)
									{
										
										WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(5) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='container-fluid Courses_containerInner']>div:nth-child(2) div[class*='ManageCardsLimit_showMoreSection']>button"));
										
										js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
										
										while(clickShowMore.isDisplayed() && clickShowMore.getText().contains("more"))
										{
											js.executeScript("arguments[0].click()", clickShowMore);
										}
									}
										
									List<WebElement> courseLinks = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks']"));
									
									for(int e = 0; e < courseLinks.size(); e++)
									{
										//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
										
										if(courseLinks.get(e).isDisplayed())
										{
											js.executeScript("arguments[0].scrollIntoView();", courseLinks.get(e));
											
											WebElement courseUrlLink = courseLinks.get(e).findElement(By.cssSelector(" a"));
											
											String CourseURL = courseUrlLink.getAttribute("href");
											
											WebElement courseCardNameLocator = courseLinks.get(e).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>p"));
											
											String courseCardName = courseCardNameLocator.getText();
												
											String CourseCardLevel = courseLinks.get(e).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>ul>li:nth-child(1)")).getText();
												
											String checkURL = ibmPageLocator.checkURLStatus(CourseURL);
											
											if(!checkURL.contains("fail"))
											{
												driver.switchTo().newWindow(WindowType.TAB);
												
												driver.get(CourseURL);
												
												Set<String> allScreen = driver.getWindowHandles();
												
												for(String nextScreen : allScreen)
												{
													driver.switchTo().window(nextScreen);
														
													if(!driver.getCurrentUrl().equalsIgnoreCase("data:,")&&!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")&&!driver.getCurrentUrl().contains("?utm"))
													{
														driver.switchTo().window(nextScreen);
														
														if(!(driver.getTitle().contains("undefined - SkillUp Online") ||
															      driver.getTitle().contains("null") ||
															      driver.findElements(By.cssSelector("div[class*='PageNotFound_textSection']")).size() > 0))
														{
															WebElement checkLevelFromPage = driver.findElement(By.cssSelector("div[class*='CourseDescription_TooltipAboutSection']>p:nth-child(1), div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1), div[class*='CourseDescription_levelSection']>h2"));
															
															if(checkLevelFromPage.getText().toUpperCase().equalsIgnoreCase(CourseCardLevel))
															{
																System.out.println("card and page level same");
																
																if(checkLevelFromPage.getText().toUpperCase().equalsIgnoreCase("SELF-PACED"))
																{
																	if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
																	{
																		
																		WebElement courseDuration = driver.findElement(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) div[class*='CourseDescription_courseAboutTextSection']>h2"));
																		
																		if(courseDuration.getText().equalsIgnoreCase("Starts on"))
																		{
																			status.add("starts on date is presented for self paced level - "+courseCardName);
																			driver.close();
																			driver.switchTo().window(categoryWindow);
																		}
																		else
																		{
																			driver.close();
																			driver.switchTo().window(categoryWindow);
																		}
																	}
																	else
																	{
																		status.add("level is not presented - "+courseCardName);
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																	}
																}
																else if(checkLevelFromPage.getText().toUpperCase().equalsIgnoreCase("vILT")||checkLevelFromPage.getText().equalsIgnoreCase("INSTRUCTOR"))
																{
																	if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
																	{
																		WebElement courseDuration = driver.findElement(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) div[class*='CourseDescription_courseAboutTextSection']>h2"));
																		
																		if(!courseDuration.getText().equalsIgnoreCase("Starts on"))
																		{
																			status.add("starts on date is not presented for VILT level - "+courseCardName);
																			driver.close();
																			driver.switchTo().window(categoryWindow);
																		}
																		else
																		{
																			driver.close();
																			driver.switchTo().window(categoryWindow);
																		}
																	}
																	else
																	{
																		status.add(" level is not presented - "+courseCardName);
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																	}
																}
																else
																{
																	System.out.println("course level is : "+checkLevelFromPage.getText().toUpperCase());
																	driver.close();
																	driver.switchTo().window(categoryWindow);
																}
															}
															else
															{
																System.out.println("card and page level not same");
																status.add(CourseURL+"card and page level not same "+ courseCardName +" cardname and page url is "+url);
																driver.close();
																driver.switchTo().window(categoryWindow);
															}
														}
														else
														{
															status.add("page contains undefined or null or page not found in course url : "+CourseURL+" card name : "+courseCardName);
															driver.close();
															driver.switchTo().window(categoryWindow);
														}
													}
													}
													
													
													if(e == courseLinks.size()-1)
													{
														break;
													}
											}
											else
											{
												status.add(CourseURL+" issue in page");
												driver.close();
												driver.switchTo().window(categoryWindow);
											}
										}
										
									
									}
								}
								else
								{
									System.out.println("course card not available");
									driver.close();
									driver.switchTo().window(parentWindow);
									break;
								}
								driver.close();
								driver.switchTo().window(parentWindow);
							}
						}
						driver.switchTo().window(parentWindow);
					}
				
				}
			}//category = for loop completion 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("fail");
		}
		return status;
	}
}

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
											js.executeScript("arguments[0].scrollIntoView();", checkStartDate.get(j));
											if(checkStartDate.get(j).getText().contains("Starts on"))
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
											}
										}
										break;
									}
									driver.switchTo().window(parentWindow);
								}
								driver.switchTo().window(parentWindow);
							}
							else
							{
								System.out.println("url facing issue : "+url);
								status.add(url);
							}
						}
					}
				}
			}
		
			
			List<WebElement> courseCardsVILT = driver.findElements(By.xpath("//section[contains(@class,'Courses_mainSection')]/div/div[@class='row'][2]//div[contains(@class,'LearningCatalog_browserCard')]"));
			for(int i = 0; i < courseCardsVILT.size(); i++)
			{
				WebElement checkCourseCardLevelVILT = courseCardsVILT.get(i).findElement(By.xpath(".//div[contains(@class,'RegularCourseCard_courseHeading')]/ul/li[1]"));
				js.executeScript("arguments[0].scrollIntoView();", checkCourseCardLevelVILT);
				if(checkCourseCardLevelVILT.getText().contains("vILT"))
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
								List<WebElement> checkStartDate = driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_durationAndPriceSection')]/div[@class='d-flex gap-2']//h2"));
								for(int j = 0; j < checkStartDate.size(); j++)
								{
									js.executeScript("arguments[0].scrollIntoView();", checkStartDate.get(j));
									if(checkStartDate.get(j).getText().contains("Starts on"))
									{
										System.out.println("starts on date  presented for VILT course");
										driver.close();
										driver.switchTo().window(parentWindow);
										break;
									}
									else
									{
										status.add(url);
										System.out.println("starts on date not presented for VILT course");
									}
								}
								break;
							}
							driver.switchTo().window(parentWindow);
						}
						driver.switchTo().window(parentWindow);
					}
						else
						{
							System.out.println("url facing issue : "+url);
							status.add(url);
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
			ArrayList<String> programDurationStatus= new ArrayList<String>();
			ArrayList<String> courseDurationStatus= new ArrayList<String>();
			
			WebElement clickDropdown = driver.findElement(By.xpath("//li[contains(@class,'nav-item dropdown Header_dropdown')]//img[@alt='icon']"));
			
			js.executeScript("arguments[0].scrollIntoView();", clickDropdown);
			
			if(clickDropdown.isDisplayed())
			{
				js.executeScript("arguments[0].click()", clickDropdown);
			}
				
			List<WebElement> clickCategory = driver.findElements(By.xpath("//div[contains(@class,'Header_headerRight')]//ul[contains(@class,'dropdown-menu dropdown-cat Header_dropdownMenu')]//div[@class='MainCatE catcolumn divbox1']/ul/li/a"));
		
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
						
					System.out.println("category url : "+url);
					
					String parentWindow = driver.getWindowHandle(); // mega menu window
						
					driver.switchTo().newWindow(WindowType.TAB);
						
					driver.get(url);
						
					Set<String> allWindow = driver.getWindowHandles();
						
					for(String window : allWindow)
					{
							
						driver.switchTo().window(window);
							
						if(driver.getCurrentUrl().contains("?utm"))
						{
							driver.switchTo().window(window);
								
							String categoryWindow = driver.getWindowHandle();
								
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
								
							if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='ManageCardsLimit_showMoreSection']>button")).size()>0)
							{
								System.out.println("Program card verification started");
									
								WebElement clickShowMoreIcon = driver.findElement(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(3) div[class*='ManageCardsLimit_showMoreSection']>button"));
									
								js.executeScript("arguments[0].scrollIntoView();", clickShowMoreIcon);
									
								while(clickShowMoreIcon.isDisplayed() && clickShowMoreIcon.getText().contains("more"))
								{
									js.executeScript("arguments[0].click()", clickShowMoreIcon);
								}
									
								List<WebElement> programLinks = driver.findElements(By.cssSelector("div[class*='LearningCatalog_customCard']>div[class*='FlatCourseCard_FlatcardLinks']"));
								
								for(int a = 0; a < programLinks.size(); a++)
								{
									js.executeScript("arguments[0].scrollIntoView();", programLinks.get(a));
									
									WebElement urlLink = programLinks.get(a).findElement(By.cssSelector(" a"));
									
									String programURL = urlLink.getAttribute("href");
									
									String checkCourseLevel = programLinks.get(a).findElement(By.cssSelector(" div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1)")).getText();
									
									driver.switchTo().newWindow(WindowType.TAB);
									
									driver.get(programURL);
										
									Set<String> allWindows = driver.getWindowHandles();
									
									for(String nextWindow: allWindows)
									{
										driver.switchTo().window(nextWindow);
											
										if(!driver.getCurrentUrl().equalsIgnoreCase("data:,")&&!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")&&!driver.getCurrentUrl().contains("?utm"))
										{
											driver.switchTo().window(nextWindow);
											
											WebElement checkLevel = driver.findElement(By.cssSelector("div[class*='CourseDescription_TooltipAboutSection']>p:nth-child(1), div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1), div[class*='CourseDescription_levelSection']>h2"));
											
											if(checkLevel.getText().equalsIgnoreCase(checkCourseLevel))
											{
												System.out.println("card and page level same");
												
												if(checkLevel.getText().equalsIgnoreCase("BLENDED"))
												{
													if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
													{
														List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
														
														for(int f = 0 ; f < courseDuration.size(); f++)
														{
															if(!courseDuration.get(f).getText().equalsIgnoreCase("Starts on"))
															{
																programDurationStatus.add("true");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
																
															}
														}
													}
													else
													{
														programDurationStatus.add("false");
														driver.close();
														driver.switchTo().window(categoryWindow);
														break;
													}
												}
												else if(checkLevel.getText().equalsIgnoreCase("SELF-PACED"))
												{
													if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
													{
														
														List<WebElement> courseDuration1 = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
														for(int i = 0 ; i < courseDuration1.size(); i++)
														{
															if(!courseDuration1.get(i).getText().equalsIgnoreCase("Starts on"))
															{
																programDurationStatus.add("true");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
													}
													else
													{
														programDurationStatus.add("false");
														driver.close();
														driver.switchTo().window(categoryWindow);
														break;
													}
													
												}
												else if(checkLevel.getText().equalsIgnoreCase("vILT"))
												{
													
													if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
													{
														List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
														for(int f = 0 ; f < courseDuration.size(); f++)
														{
															if(courseDuration.get(f).getText().equalsIgnoreCase("Starts on"))
															{
																programDurationStatus.add("true");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
													}
													else
													{
														programDurationStatus.add("false");
														driver.close();
														driver.switchTo().window(categoryWindow);
														break;
													}
												}
											}
											else
											{
												System.out.println("card and page level not same");
												status.add("card and page level not same " +programURL+ " in "+ url);
												driver.close();
												driver.switchTo().window(categoryWindow);
												break;
											}
											if(!programDurationStatus.contains("true"))
											{
												status.add(programURL +" in "+url);
											}
										}
										driver.switchTo().window(categoryWindow);
										
									}
										
									driver.switchTo().window(categoryWindow);
										
									if(a == programLinks.size()-1)
									{
										break;
									}
								}
								
							}
								
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
								
							if(driver.findElements(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(5) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='container-fluid Courses_containerInner']>div:nth-child(2) div[class*='ManageCardsLimit_showMoreSection']>button")).size()>0)
							{
								System.out.println("Course card verification started");
									
								WebElement clickShowMore = driver.findElement(By.cssSelector("div[class*='container-fluid Courses_containerInner']>div:nth-child(5) div[class*='ManageCardsLimit_showMoreSection']>button, div[class*='container-fluid Courses_containerInner']>div:nth-child(2) div[class*='ManageCardsLimit_showMoreSection']>button"));
								
								js.executeScript("arguments[0].scrollIntoView();", clickShowMore);
									
								while(clickShowMore.isDisplayed() && clickShowMore.getText().contains("more"))
								{
									js.executeScript("arguments[0].click()", clickShowMore);
								}
									
								List<WebElement> courseLinks = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks']"));
								
								for(int e = 0; e < courseLinks.size(); e++)
								{
									js.executeScript("arguments[0].scrollIntoView();", courseLinks.get(e));
									
									WebElement courseUrlLink = courseLinks.get(e).findElement(By.cssSelector(" a"));
									
									String CourseURL = courseUrlLink.getAttribute("href");
										
									String CourseLevel = courseLinks.get(e).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>ul>li:nth-child(1)")).getText();
										
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
													WebElement checkLevel = driver.findElement(By.cssSelector("div[class*='CourseDescription_TooltipAboutSection']>p:nth-child(1), div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1), div[class*='CourseDescription_levelSection']>h2"));
													
													if(checkLevel.getText().equalsIgnoreCase(CourseLevel))
													{
														System.out.println("card and page level same");
														
														if(checkLevel.getText().equalsIgnoreCase("BLENDED"))
														{
															
															if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
															{
																List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																
																for(int c = 0 ; c < courseDuration.size(); c++)
																{
																	if(!courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																	{
																		courseDurationStatus.add("true");
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																		break;
																		
																	}
																}
															}
															else
															{
																courseDurationStatus.add("false");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
														else if(checkLevel.getText().equalsIgnoreCase("SELF-PACED"))
														{
															if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
															{
																
																List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																for(int c = 0 ; c < courseDuration.size(); c++)
																{
																	if(!courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																	{
																		courseDurationStatus.add("true");
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																		break;
																	}
																}
															}
															else
															{
																courseDurationStatus.add("false");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
														else if(checkLevel.getText().equalsIgnoreCase("vILT"))
														{
															if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
															{
																List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																for(int c = 0 ; c < courseDuration.size(); c++)
																{
																	if(courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																	{
																		courseDurationStatus.add("true");
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																		break;
																	}
																}
															}
															else
															{
																courseDurationStatus.add("false");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
													}
													else
													{
														System.out.println("card and page level not same");
														status.add(CourseURL+"card and page level not same"+url);
														driver.close();
														driver.switchTo().window(categoryWindow);
														break;
													}
													if(!courseDurationStatus.contains("true"))
													{
														status.add(CourseURL +" fail on category"+url);
													}
												}
												else
												{
													driver.close();
													driver.switchTo().window(categoryWindow);
												}
											}
												driver.switchTo().window(categoryWindow);
											}
											
											driver.switchTo().window(categoryWindow);
											
											if(e == courseLinks.size()-1)
											{
												break;
											}
									}
									else
									{
										status.add(CourseURL+" issue in page");
									}
									
								
								}
							}
							
							else
							{
								if(driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks']")).size()>0)
								{
									List<WebElement> courseLinks = driver.findElements(By.cssSelector("div[class*='LearningCatalog_cardRow'] div[class*='RegularCourseCard_RegularcardLinks']"));
										
									for(int d = 0; d < courseLinks.size(); d++)
									{
										js.executeScript("arguments[0].scrollIntoView();", courseLinks.get(d));
										
										WebElement courseUrlLink = courseLinks.get(d).findElement(By.cssSelector(" a"));
										
										String CourseURL = courseUrlLink.getAttribute("href");
										
										String CourseLevel = courseLinks.get(d).findElement(By.cssSelector(" div[class*='RegularCourseCard_courseHeading']>ul>li:nth-child(1)")).getText();
											
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
												WebElement checkLevel = driver.findElement(By.cssSelector("div[class*='CourseDescription_TooltipAboutSection']>p:nth-child(1), div[class*='FlatCourseCard_propertiesList']>ul>li:nth-child(1), div[class*='CourseDescription_levelSection']>h2"));
													
												if(checkLevel.getText().equalsIgnoreCase(CourseLevel))
												{
													System.out.println("card and page level same");
														
													if(checkLevel.getText().equalsIgnoreCase("BLENDED"))
													{
														if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
														{
															List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																
															for(int c = 0 ; c < courseDuration.size(); c++)
															{
																if(!courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																{
																	courseDurationStatus.add("true");
																	driver.close();
																	driver.switchTo().window(categoryWindow);
																	break;
																	
																}
															}
														}
														else
														{
															courseDurationStatus.add("false");
															driver.close();
															driver.switchTo().window(categoryWindow);
															break;
														}
													}
													else if(checkLevel.getText().equalsIgnoreCase("SELF-PACED"))
													{
															if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
															{
																
																List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																for(int c = 0 ; c < courseDuration.size(); c++)
																{
																	if(!courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																	{
																		courseDurationStatus.add("true");
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																		break;
																	}
																}
															}
															else
															{
																courseDurationStatus.add("false");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
													}
													else if(checkLevel.getText().equalsIgnoreCase("vILT"))
													{
															if(driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2")).size()>0)
															{
																List<WebElement> courseDuration = driver.findElements(By.cssSelector("div[class*='CourseDescription_courseAboutTextSection']>h2"));
																for(int c = 0 ; c < courseDuration.size(); c++)
																{
																	if(courseDuration.get(c).getText().equalsIgnoreCase("Starts on"))
																	{
																		courseDurationStatus.add("true");
																		driver.close();
																		driver.switchTo().window(categoryWindow);
																		break;
																	}
																}
															}
															else
															{
																courseDurationStatus.add("false");
																driver.close();
																driver.switchTo().window(categoryWindow);
																break;
															}
														}
													}
													else
													{
														System.out.println("card and page level not same");
														status.add(CourseURL+" card and page level not same "+url);
														driver.close();
														driver.switchTo().window(categoryWindow);
														break;
													}
													if(!courseDurationStatus.contains("true"))
													{
														status.add(CourseURL+" in "+url);
													}
											}
											else
											{
												driver.close();
												driver.switchTo().window(categoryWindow);
											}
										}
												driver.switchTo().window(categoryWindow);
											}
											
											driver.switchTo().window(categoryWindow);
											
											if(d == courseLinks.size()-1)
											{
												break;
											}
									}
									
							}
						}
							
							driver.close();
							driver.switchTo().window(parentWindow);
						}
						driver.switchTo().window(parentWindow);
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
}

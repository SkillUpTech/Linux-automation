package com.seo.regression.testing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

public class InviteOnlyLocator {
	MicrosoftCourseLocator microsoftCourseLocator;
	WebDriver driver;
	public InviteOnlyLocator(WebDriver driver)
	{
		this.driver = driver;
		this.microsoftCourseLocator = new MicrosoftCourseLocator(this.driver);
	}
	
	public String checkCourseCode(String codeFromExcel)
	{
		String url ="";
		String statuscode = "";
		int responseCode = 0;
		try
		{
			url = "https://in.skillup.online/pacific-lutheran-university/ai-applications-with-watson/"; // Specify the URL you want to check

	        try {
	            URL obj = new URL(url);
	            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

	            connection.setRequestMethod("GET");

	           responseCode = connection.getResponseCode();

	            System.out.println("Response Code: " + responseCode);

	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                System.out.println("URL is reachable and returns HTTP OK (200)");
	            } else {
	                System.out.println("URL is not reachable or returns an error: " + responseCode);
	            }
	        } catch (IOException e) {
	            System.err.println("Error occurred while checking URL: " + e.getMessage());
	        }
	        statuscode = String.valueOf(responseCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statuscode;
	}
	
	public ArrayList<String> checkStatusCode_inviteOnlyCourse(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			for(int i = 1; i < data.size(); i++)
			{
				
				String urlStatus=this.checkCourseCode(data.get(i));
				if(!urlStatus.equalsIgnoreCase("200")||urlStatus.equalsIgnoreCase("308"))
				{
					System.out.println("invite only status code for this course is "+data.get(i)+"  : " + urlStatus);
				}
				else
				{
					status.add(data.get(i));
					System.out.println("invite only status code for this course is "+data.get(i)+"  : " + urlStatus);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> checkDate_inviteOnlyCourse(ArrayList<String> data)
	{

		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String parentWindow = driver.getWindowHandle();
			for(int i = 1; i < data.size(); i++)
			{
				driver.switchTo().newWindow(WindowType.TAB);
				driver.get(OpenWebsite.setHost+data.get(i));
				Set<String> handle = driver.getWindowHandles();
            	for(String allPage : handle)
            	{
            		driver.switchTo().window(allPage);
            		if(driver.getCurrentUrl().contains("/courses/"))
        			{
            			driver.switchTo().window(allPage);
	            		String newTabTitle = driver.getTitle();
	                	System.out.println("Title of the new tab: " + newTabTitle+ " url of the course : "+driver.getCurrentUrl());
	                	if(driver.findElements(By.cssSelector("div[class*='CourseDescription_levelSection'] h2")).size()>0)
	                	{
	                		WebElement checkSelfpaced = driver.findElement(By.cssSelector("div[class*='CourseDescription_levelSection'] h2"));
	                		if(checkSelfpaced.getText().equalsIgnoreCase("vILT"))
	                		{
	                			System.out.println("It is VILT course");
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(newTabTitle.contains("null"))
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("tab ststus is : "+newTabTitle);
                					status.add("null word on tab in this course " +data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(newTabTitle.contains("undefined"))
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("undefined word on tab in this course " +newTabTitle);
                					status.add("undefined word on tab in this course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.cssSelector("div[class*='PageNotFound_textSection']")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("404 issue");
                					status.add("404 issue in this course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.xpath("//*[contains(text(),'Starts on')]|//*[contains(text(),'Duration')]|//*[contains(text(),'Fee')]")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("course level described on invite only course ");
                					status.add("course level described on invite only course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_warningBoxText')]//parent::*")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("Invite only course");
                				}
                				else
                				{
                					System.out.println("It is not invite only course");
                					status.add("yellow box is not presented for invite only course "+data.get(i));
                				}
                			}
	                		else if(checkSelfpaced.getText().equalsIgnoreCase("Self-Paced"))
	                		{
	                			System.out.println("It is Self-Paced course");
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(newTabTitle.contains("null"))
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("tab ststus is : "+newTabTitle);
                					status.add("null word on tab in this course " +data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(newTabTitle.contains("undefined"))
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("undefined word on tab in this course " +newTabTitle);
                					status.add("undefined word on tab in this course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.cssSelector("div[class*='PageNotFound_textSection']")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("404 issue");
                					status.add("404 issue in this course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.xpath("//*[contains(text(),'Starts on')]|//*[contains(text(),'Duration')]|//*[contains(text(),'Fee')]")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("course level described on invite only course ");
                					status.add("course level described on invite only course "+data.get(i));
                				}
                				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                				if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_warningBoxText')]//parent::*")).size()>0)
                				{
                					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                					System.out.println("Invite only course");
                				}
                				else
                				{
                					System.out.println("It is not invite only course");
                					status.add("yellow box is not presented for invite only course "+data.get(i));
                				}
                			}
                		}
	                	driver.close();
	                	driver.switchTo().window(parentWindow);
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

public ArrayList<String> checkProgram_Courses(ArrayList<String> data)
{
	ArrayList<String> status = new ArrayList<String>();
	String URLStatus = "";
	try
	{
		String parentWindow = driver.getWindowHandle();
		
		for(int k = 1; k < data.size(); k++)
		{
			driver.switchTo().newWindow(WindowType.TAB);
			
			driver.get(OpenWebsite.setHost+data.get(k));
			
			Set<String> handle = driver.getWindowHandles();
			
			for(String allPage : handle)
			{
				driver.switchTo().window(allPage);
				
				if((!driver.getCurrentUrl().equalsIgnoreCase("about:blank"))&&(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")) && (!driver.getCurrentUrl().equalsIgnoreCase("data:,")))
    			{
					driver.switchTo().window(allPage);
					
					URLStatus = this.checkCourseCode(data.get(k));
					
					String newTabTitle = driver.getTitle();
					
					if(URLStatus.contains("200") || URLStatus.contains("308"))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						if(driver.findElements(By.cssSelector("div[class='PageNotFound_textSection__3ecIh']")).size()>0)
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							System.out.println("404 error found on course page : "+data.get(k));
							status.add(data.get(k)+" This course has 404 error");
						}
						else
						{
							System.out.println("valid course page");
							
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
							
							if(driver.findElements(By.cssSelector("div[class*='CourseDescription_warningBox'] div[class*='CourseDescription_warningBoxText']")).size()>0)
							{
								System.out.println("yellow section box on invite only course");
								
								if(driver.findElements(By.cssSelector("div[class*='CourseDescription_warningBox'] div[class*='CourseDescription_warningBoxText'] ul>li>a")).size()>0)
								{
									System.out.println("program link available on this course");
									
									WebElement progrmaOnCourse = driver.findElement(By.cssSelector("div[class*='CourseDescription_warningBox'] div[class*='CourseDescription_warningBoxText']"));
									
									List<WebElement> listOfPrograms = progrmaOnCourse.findElements(By.cssSelector(" ul>li>a"));
									

									for(int i = 0; i < listOfPrograms.size(); i++)
									{
										String programLinkonCourse = listOfPrograms.get(i).getAttribute("href");
										
										System.out.println("Progrma link is : "+programLinkonCourse);

										String statusOfURL = this.checkCourseCode(programLinkonCourse);
										
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
										
										System.out.println("Program link status code : "+statusOfURL);
										
										String coursePageTitle = driver.getTitle();
										
										String coursePage = driver.getWindowHandle(); //course page

										if(statusOfURL.equalsIgnoreCase("200")||statusOfURL.equalsIgnoreCase("308"))
										{
											driver.switchTo().newWindow(WindowType.TAB);
											
											driver.get(programLinkonCourse);

											Set<String> allWindow = driver.getWindowHandles();

											for(String window : allWindow)
											{
												driver.switchTo().window(window);
												
												if((!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")) && (!driver.getCurrentUrl().equalsIgnoreCase("data:,")) && (!coursePageTitle.equalsIgnoreCase(driver.getTitle())))
												{
													driver.switchTo().window(window);
													
													if(newTabTitle.equalsIgnoreCase("null"))
													{
														driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
														System.out.println("tab ststus is : "+newTabTitle);
														status.add(data.get(k));
													}
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
													if(newTabTitle.equalsIgnoreCase("undefined"))
													{
														driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
														System.out.println("tab ststus is : "+newTabTitle);
														status.add(data.get(k));
													}
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
													if(driver.findElements(By.cssSelector("div[class*='PageNotFound_textSection']")).size()>0)
					                				{
														driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					                					System.out.println("404 issue");
					                					status.add("404 issue in this course "+data.get(i));
					                				}
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
													driver.close();
													break;
												}
												driver.switchTo().window(coursePage); // course page
											}
											driver.switchTo().window(coursePage); //course page
										}
										else
										{
											System.out.println("Program link status code : "+statusOfURL);
										}
									}
								}
								else
								{
									System.out.println("program link not available on this course");
								}
																
								
							}
							else
							{
								System.out.println("yellow section box not available on invite only course");
							}
						}
					}
					else
					{
						System.out.println(" url status : "+URLStatus);
						status.add(" "+URLStatus+" for this url : "+data.get(k));
					}
					driver.close();
					driver.switchTo().window(parentWindow);
					break;
    			}
				driver.switchTo().window(parentWindow);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return status;
}

public ArrayList<String> checkInviteOnly_Courses(ArrayList<String> data)
{
	ArrayList<String> status = new ArrayList<String>();
	try
	{
		String originalHandle = driver.getWindowHandle();
		for(int k = 1; k < data.size(); k++)
		{
			driver.switchTo().newWindow(WindowType.TAB);
        	driver.get(OpenWebsite.setHost+data.get(k));
        	Set<String> allWindows = driver.getWindowHandles();
			for (String handle : allWindows)
			{
				driver.switchTo().window(handle);
				if((!driver.getCurrentUrl().equalsIgnoreCase("about:blank"))&&(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")) && (!driver.getCurrentUrl().equalsIgnoreCase("data:,")))
				{
					driver.switchTo().window(handle);
					if(driver.findElements(By.cssSelector("div[class='PageNotFound_textSection__3ecIh']")).size()>0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						System.out.println("404 error : "+data.get(k));
						status.add("showing 404 in "+data.get(k));
						driver.close();
						driver.switchTo().window(originalHandle);
						break;
					}
					else
					{
						if(driver.findElements(By.cssSelector("div[class*='CourseDescription_levelSection'] h2")).size()>0)
            			{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            				WebElement checkSelfpaced = driver.findElement(By.cssSelector("div[class*='CourseDescription_levelSection'] h2"));
            				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            				if(checkSelfpaced.getText().contains("Self-Paced"))
            				{
            					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            					System.out.println("It is selfpaced course");
            					if(driver.findElements(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) h2")).size()>0)
            					{
            						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            						System.out.println("It is self paced course should not show date");
            						status.add("invite only and self paced course should not display date : "+data.get(k));
            					}
            				}
            				else if(checkSelfpaced.getText().equalsIgnoreCase("VILT"))
            				{
            					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            					if(driver.findElements(By.cssSelector("div[class='d-flex gap-2']:nth-child(1) h2")).size()>0)
            					{
            						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            						System.out.println("It is VILT course");
            						status.add("VILT and invite only course should not show date for this url : "+data.get(k));
            					}
            				}
            			}
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        			if(driver.getTitle().equalsIgnoreCase("null"))
					{
        				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        				System.out.println("tab ststus is : "+driver.getTitle());
						status.add("null word on tab "+ data.get(k));
					}
        			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.getTitle().equalsIgnoreCase("undefined"))
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						System.out.println("undefined word on tab "+driver.getTitle());
						status.add("undefined word on tab "+data.get(k));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//*[contains(text(),'Starts on')]|//*[contains(text(),'Duration')]|//*[contains(text(),'Fee')]")).size()>0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						System.out.println("course level described on invite only course "+driver.getTitle());
						status.add("course level described on invite only course "+data.get(k));
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					if(driver.findElements(By.xpath("//div[contains(@class,'CourseDescription_warningBoxText')]//parent::*")).size()>0)
    				{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    					System.out.println("Invite only course");
    				}
    				else
    				{
    					System.out.println("It is not invite only course");
    					status.add("yellow box is not presented for invite only course "+data.get(k));
    				}
					driver.close();
					driver.switchTo().window(originalHandle);
				}
				driver.switchTo().window(originalHandle);
			}
			driver.switchTo().window(originalHandle);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return status;
}

public ArrayList<String> checkEnrollmentDateIsExpiredFuturedCurrent_Courses(ArrayList<String> data)
{
	ArrayList<String> status = new ArrayList<String>();
	try
	{

		for(int k = 1; k < data.size(); k++)
		{
			String originalHandle = driver.getWindowHandle();
			
			driver.switchTo().newWindow(WindowType.TAB);
			
        	driver.get(OpenWebsite.setHost+data.get(k));
			
			for (String handle : driver.getWindowHandles())
			{
				driver.switchTo().window(handle);
				
				if((!driver.getCurrentUrl().equalsIgnoreCase("about:blank"))&&(!driver.getCurrentUrl().equalsIgnoreCase(OpenWebsite.setHost+"/")) && (!driver.getCurrentUrl().equalsIgnoreCase("data:,")))
				{
					driver.switchTo().window(handle);
                	
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
					
					if(driver.findElements(By.cssSelector("div[class='PageNotFound_textSection__3ecIh']")).size()>0)
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						System.out.println("404 error : "+data.get(k));
						status.add(data.get(k));
						driver.close();
						break;
					}
					else
					{
                		if(driver.findElements(By.cssSelector("div[class*='CourseDescription_buttonsContent']>h6")).size()>0)
                		{
                			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                			WebElement checkEnrollmentStatus = driver.findElement(By.cssSelector("div[class*='CourseDescription_buttonsContent']>h6"));
        					if(checkEnrollmentStatus.isDisplayed())
        					{
        						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        						if(checkEnrollmentStatus.getText().contains("Enrollment is Closed"))
        						{
        							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        							System.out.println("enrollment closed");
        							driver.close();
        							driver.switchTo().window(originalHandle);
        							break;
        						}
        						else if(driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']")).getText().contains("Enroll Now"))
        						{
        							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        							System.out.println("enrollment currently open");
        							driver.close();
        							driver.switchTo().window(originalHandle);
        							break;
        						}
        						else if(driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']")).getText().contains("Start Now"))
        						{
        							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        							System.out.println("enrollment is on Startnow status");
        							driver.close();
        							driver.switchTo().window(originalHandle);
        							break;
        						}
    	                	}
	                	}
					}
				}
				driver.switchTo().window(originalHandle);
			}
			driver.switchTo().window(originalHandle);
		}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return status;
}

}

package com.seo.regression.testing;

import java.awt.Image;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageLocator {
	WebDriver driver;
	int respCode = 200;
	HttpURLConnection huc = null;

	public SearchPageLocator(WebDriver driver) {
		this.driver = driver;
	}

	public void checkURL(String urlLink) {
		try {
			String addHosturl = urlLink;
			huc = (HttpURLConnection) (new URL(addHosturl).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			System.out.println("status code : " + respCode + " " + addHosturl);
			if (respCode > 200) {
				System.out.println("broken link" + addHosturl);
				System.exit(0);
			} else {
				System.out.println("un broken link" + addHosturl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> validDataSearchProcess(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfProcess = new ArrayList<String>();
		try
		{
			System.out.println("valid data search started");
			for(int i = 1; i < dataFromExcel.size(); i++)
			{
				if(i == 1)
				{
					
				WebElement searchBox = driver.findElement(By.cssSelector("ul[class*='Header_headSearch_'] input#contentSearch[value]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", searchBox);
				
				WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(30));
				wb.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				//Thread.sleep(2000);
				searchBox.sendKeys(dataFromExcel.get(i));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				if(driver.findElements(By.xpath("//ul[contains(@class,'Header_headSearch_')]/descendant::ul/li")).size()>0)
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					List<WebElement> checkListOfCourse = driver.findElements(By.xpath("//ul[contains(@class,'Header_headSearch_')]/descendant::ul/li"));
					if(checkListOfCourse.size()>0)
					{
						for(int j = 0; j < checkListOfCourse.size(); j++)
						{
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
							if(checkListOfCourse.get(j).getText().equalsIgnoreCase(dataFromExcel.get(i)))
							{
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
								System.out.println("Entered course available");
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
								checkListOfCourse.get(j).click();
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
								
								String parent = driver.getWindowHandle();
								Set<String> windows = driver.getWindowHandles();
								for(String window : windows)
								{
									driver.switchTo().window(window);
									if(driver.getCurrentUrl().contains("search="))
									{
										driver.switchTo().window(window);
										System.out.println("results found window");
										ArrayList<Integer> getHeaderStatus = new ArrayList<Integer>();
										ArrayList<Integer> getFooterStatus = new ArrayList<Integer>();
										//getProcessStatus.addAll(this.verifyHeader());
										getHeaderStatus = this.verifyHeader();
										driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
										
										//getProcessStatus.addAll(this.verifyFooter());
										getFooterStatus = this.verifyFooter();
										for(int k = 0; k < getHeaderStatus.size(); k++)
										{
											if(getHeaderStatus.get(0) != getFooterStatus.get(0))
											{
												statusOfProcess.add("category not same");	
											}
											if(getHeaderStatus.get(2) != getFooterStatus.get(2))
											{
												statusOfProcess.add("popular courses not same");
											}
											if(getHeaderStatus.get(1) != 5)
											{
												statusOfProcess.add("popular learning partner count not correct in header");
											}
											if(getFooterStatus.get(1) != 3)
											{
												statusOfProcess.add("blog count not correct in footer");
											}
										}
										JavascriptExecutor js = (JavascriptExecutor) driver;
										js.executeScript("window.scrollBy(0, -700)", "");
										WebElement getCourse = driver.findElement(By.cssSelector("div[class*='RegularCourseCard_courseHeading__1Ohrn'] p"));
										if(getCourse.getText().equalsIgnoreCase(dataFromExcel.get(i)))
										{
											//getCourse.click();
											js.executeScript("arguments[0].click()", getCourse);
											driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
											Thread.sleep(1000);
											String parent1 = driver.getWindowHandle();
											Set<String> windows1 = driver.getWindowHandles();
											for(String window1 : windows1)
											{
												driver.switchTo().window(window1);
												Thread.sleep(3000);
												if(driver.getCurrentUrl().contains("/courses/") && driver.getCurrentUrl().contains(RegressionTesting.ENV_TO_USE))
												{
													Thread.sleep(3000);
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
													driver.switchTo().window(window1);
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
													System.out.println("course is opened");
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
													Thread.sleep(1000);
													String courseURL = driver.getCurrentUrl();
													System.out.println("course Link : "+courseURL);
													String result = OpenWebsite.setHost;//.substring(0, courseURL.indexOf("/Courses"));
													System.out.println("homepage url : "+result);
													driver.get(result);
													Thread.sleep(1000);
													driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
													break;
												}
											}
											
										}
									}
								}
							}
							else
							{
								System.out.println("course is not available");
								statusOfProcess.add("fail");
							}
						}
					}
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				}
			System.out.println("valid data search completed");
			break;
			}
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
				statusOfProcess.add("fail");
			}
		return statusOfProcess;
	}

	public ArrayList<String> invalidDataSearchProcess(ArrayList<String> dataFromExcel) {
		ArrayList<String> statusOfProcess = new ArrayList<String>();
		try {
			System.out.println("In valid data search started");
			for (int i = 1; i < dataFromExcel.size(); i++)
			{
				if(i == 1)
				{
					
				WebElement searchBox = driver.findElement(By.cssSelector(
						"ul[class='nav navbar-nav Header_headSearch___BeK7'] form#searchForm input#contentSearch"));
				WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(30));
				wb.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
				searchBox.sendKeys(dataFromExcel.get(i));
				String getValue = searchBox.getAttribute("value");
				System.out.println(getValue);
				WebElement clickSearchIcon = driver.findElement(
						By.cssSelector("ul[class='nav navbar-nav Header_headSearch___BeK7'] button#btnCheck"));
				clickSearchIcon.click();
				String parentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for (String window : windows) {
					driver.switchTo().window(window);
					if (driver.getCurrentUrl().contains("?search")) {
						driver.switchTo().window(window);
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						if (driver.findElements(By.cssSelector("div[class='Course_NotiFicaTIon__Anb5F'] li"))
								.size() > 0) {
							WebElement checkListOfCourse = driver
									.findElement(By.cssSelector("div[class='Course_NotiFicaTIon__Anb5F'] li"));
							if (checkListOfCourse.isDisplayed()) {
								System.out.println(checkListOfCourse.getText());
							}
						} else {
							System.out.println("zero result found");
							break;
						}
					}
				}
				ArrayList<Integer> getHeaderStatus = new ArrayList<Integer>();
				ArrayList<Integer> getFooterStatus = new ArrayList<Integer>();
				//getProcessStatus.addAll(this.verifyHeader());
				getHeaderStatus = this.verifyHeader();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				
				//getProcessStatus.addAll(this.verifyFooter());
				getFooterStatus = this.verifyFooter();
				for(int k = 0; k < getHeaderStatus.size(); k++)
				{
					if(getHeaderStatus.get(0) != getFooterStatus.get(0))
					{
						statusOfProcess.add("category not same");	
					}
					if(getHeaderStatus.get(2) != getFooterStatus.get(2))
					{
						statusOfProcess.add("popular courses not same");
					}
					if(getHeaderStatus.get(1) != 5)
					{
						statusOfProcess.add("popular learning partner count not correct in header");
					}
					if(getFooterStatus.get(1) != 3)
					{
						statusOfProcess.add("blog count not correct in footer");
					}
				}
				Thread.sleep(1000);
				// String courseURL = driver.getCurrentUrl();
				String result = OpenWebsite.setHost;// courseURL.substring(0, courseURL.indexOf("/Courses"));
				System.out.println("homepage url : " + result);
				driver.get(result);
				Thread.sleep(1000);
			}
			System.out.println("Invalid data search completed");
			break;
			}
		} 
			catch (Exception e) {
			e.printStackTrace();
		}
		return statusOfProcess;
	}

	public ArrayList<String> emptySearchProcess(ArrayList<String> dataFromExcel)
	{
		ArrayList<String> statusOfProcess = new ArrayList<String>();
		try {
			System.out.println("empty search started");
			for(int i = 0; i < dataFromExcel.size(); i++)
			{
				if(i == 1)
				{
					WebElement searchBox = driver.findElement(By.cssSelector(
							"ul[class='nav navbar-nav Header_headSearch___BeK7'] form#searchForm input#contentSearch"));
					WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(30));
					wb.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
					Thread.sleep(3000);
					searchBox.sendKeys(dataFromExcel.get(i));
					String getValue = searchBox.getAttribute("value");
					System.out.println(getValue);
					if (dataFromExcel.get(i).equalsIgnoreCase("empty")) {
						searchBox.sendKeys("");
						WebElement clickSearchIcon = driver.findElement(
								By.cssSelector("ul[class='nav navbar-nav Header_headSearch___BeK7'] button#btnCheck"));
						clickSearchIcon.click();
						String parentWindow = driver.getWindowHandle();
						Set<String> windows = driver.getWindowHandles();
						for (String window : windows) {
							driver.switchTo().window(window);
							if (driver.getCurrentUrl().contains("?search")) {
								driver.switchTo().window(window);
								if (driver.findElements(By.cssSelector("div[class='Course_NotiFicaTIon__Anb5F'] li"))
										.size() > 0) {
									WebElement checkListOfCourse = driver
											.findElement(By.cssSelector("div[class='Course_NotiFicaTIon__Anb5F'] li"));
									if (checkListOfCourse.isDisplayed()) {
										System.out.println(checkListOfCourse.getText());
									}
								} else {
									System.out.println("zero result found");
									break;
								}
							}
						}
					}
					ArrayList<Integer> getHeaderStatus = new ArrayList<Integer>();
					ArrayList<Integer> getFooterStatus = new ArrayList<Integer>();
					//getProcessStatus.addAll(this.verifyHeader());
					getHeaderStatus = this.verifyHeader();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					
					//getProcessStatus.addAll(this.verifyFooter());
					getFooterStatus = this.verifyFooter();
					for(int k = 0; k < getHeaderStatus.size(); k++)
					{
						if(getHeaderStatus.get(0) != getFooterStatus.get(0))
						{
							statusOfProcess.add("category not same");	
						}
						if(getHeaderStatus.get(2) != getFooterStatus.get(2))
						{
							statusOfProcess.add("popular courses not same");
						}
						if(getHeaderStatus.get(1) != 5)
						{
							statusOfProcess.add("popular learning partner count not correct in header");
						}
						if(getFooterStatus.get(1) != 3)
						{
							statusOfProcess.add("blog count not correct in footer");
						}
					}
					Thread.sleep(1000);
					// String courseURL = driver.getCurrentUrl();
					String result = OpenWebsite.setHost;// courseURL.substring(0, courseURL.indexOf("/Courses"));
					System.out.println("homepage url : " + result);
					driver.get(result);
					Thread.sleep(1000);
					System.out.println("empty data search completed");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusOfProcess;
	}

	public ArrayList<Integer> verifyHeader()
	{
		ArrayList<Integer> process = new ArrayList<Integer>();
		try
		{
			WebElement dropDown = driver.findElement(By.cssSelector("a#navbarDropdown img[alt=icon]"));
			dropDown.click();
			List<WebElement> categories = driver.findElements(By.cssSelector(
					"ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] ul[class='categorylist customscroll dropdown-submenu']>li"));
			int sizeOfCategories = categories.size();
			process.add(sizeOfCategories);
			
			List<WebElement> learningPartners = driver.findElements(By.cssSelector(
					"ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] ul[class='learning-Partners']>li"));
			int sizeOfLearningPartner = learningPartners.size();
			process.add(sizeOfLearningPartner);

			List<WebElement> popularCourses = driver.findElements(By.cssSelector(
					"ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='PolularCourSE catcolumn divbox3'] ul[class='MegaMenu_PopularCourse']>li"));
			int sizeOfPopularCourse = popularCourses.size();
			process.add(sizeOfPopularCourse);
			dropDown.click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return process;
	}

	public ArrayList<Integer> verifyFooter() 
	{
		ArrayList<Integer> getStatus = new ArrayList<Integer>();
		try
		{
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, -200)", "");
			
			//List<WebElement> shareSocialLink = driver.findElements(By.cssSelector("ul[class=' Footer_socialIconsSection__5DztA d-flex'] li a"));

		//	List<WebElement> company = driver.findElements(By.cssSelector("div[class='Footer_FootMenu__4fwEE'] ul li a"));
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			List<WebElement> popularCategory = driver
					.findElements(By.cssSelector("div[class='Footer_PopularCategories__23uL0'] ul li a"));
			getStatus.add(popularCategory.size());
			
						List<WebElement> blog = driver.findElements(
					By.cssSelector("div[class='Footer_LatestBlogs__QZ7i4'] div[class='Footer_LatestBlogsRepT__F2CHs'] a"));
			getStatus.add(blog.size());
			
			List<WebElement> popularCoursesLink = driver
					.findElements(By.cssSelector("div[class='Footer_PopularCourses__Yc9Ft'] ul li"));
			
			getStatus.add(popularCoursesLink.size());


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getStatus;
	}
}

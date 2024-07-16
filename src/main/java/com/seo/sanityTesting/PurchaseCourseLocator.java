package com.seo.sanityTesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchaseCourseLocator
{
	WebDriver driver;
	String checkPaymentProcess;
	String amountWithTax;
	String amountWithOutTax;
	String orderNumber = "";
	String getEnrolledCourseName = "";
	ArrayList<Integer> errorCells;
	boolean checkEnrollStatus;
	boolean checkcountry = false;
	
	public PurchaseCourseLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	public String checkURLStatus(String getURL)
	{
		String URLStatus = "failed";
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
				URLStatus = "failed";
			}
			else
			{
				System.out.println("un broken link"+addHosturl);
				URLStatus = "pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			URLStatus = "failed";
		}
		return URLStatus;
	}
	public String launchURL(String data)
	{
		String urlStatus = "";
		try
		{
			String checkURL = this.checkURLStatus(OpenWebsite.setHost+data);
			if(!checkURL.contains("fail"))
			{
				driver.get(OpenWebsite.setHost+data);
			}
			else
			{
				urlStatus = checkURL;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return urlStatus;
	}
	public ArrayList<String> enroll(ArrayList<String> enrollDataFromExcel)
	{
		ArrayList<String> statusOfProcess = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			js.executeScript("window.scrollBy(0,200)");
			String getCurrentURL = driver.getCurrentUrl();
			if(getCurrentURL.contains("-in.skillup")||getCurrentURL.contains("in.skillup"))// india site
			{
				WebElement checkEnrollButton = driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']"));
				if(checkEnrollButton.isDisplayed())
				{
					if(checkEnrollButton.getText().equalsIgnoreCase("Enroll Now"))
					{
						System.out.println("Enroll Button is displayed");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						wait.until(ExpectedConditions.elementToBeClickable(checkEnrollButton));
						js.executeScript("arguments[0].click()", checkEnrollButton);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					}
				}
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("register?"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						WebElement clickLoginIcon = driver.findElement(By.cssSelector("li#signinlink"));
						clickLoginIcon.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						break;
					}
				}
				statusOfProcess.add(this.loginProcess(enrollDataFromExcel.get(1)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				statusOfProcess.add(this.choosePlan(enrollDataFromExcel.get(2)));
				Thread.sleep(500);
				statusOfProcess.add(this.checkOutRazorpay(enrollDataFromExcel.get(3)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				statusOfProcess.addAll(this.indiaPaymentProcess(enrollDataFromExcel.get(4), enrollDataFromExcel.get(5)));
				Thread.sleep(100);	
			}
			else
			{
				System.out.println("US Enroll Process");
				checkcountry = true;
				WebElement checkEnrollButton = driver.findElement(By.cssSelector("button[class*='CourseDescription_enrollNowBtn']"));
				if(checkEnrollButton.isDisplayed())
				{
					if(checkEnrollButton.getText().equalsIgnoreCase("Enroll Now"))
					{
						System.out.println("Enroll Button is displayed");
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						wait.until(ExpectedConditions.elementToBeClickable(checkEnrollButton));
						js.executeScript("arguments[0].click()", checkEnrollButton);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					}
				}
				
				Set<String> allWindows = driver.getWindowHandles();
				for(String window : allWindows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("register?"))
					{
						driver.switchTo().window(window);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						WebElement clickLoginIcon = driver.findElement(By.cssSelector("li#signinlink"));
						clickLoginIcon.click();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
						driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
						break;
					}
				}
				statusOfProcess.add(this.loginProcess(enrollDataFromExcel.get(1)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				
				statusOfProcess.add(this.choosePlan(enrollDataFromExcel.get(2)));
				Thread.sleep(500);
				
				statusOfProcess.add(this.checkOutRazorpay(enrollDataFromExcel.get(3)));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				statusOfProcess.addAll(this.indiaPaymentProcess(enrollDataFromExcel.get(4), enrollDataFromExcel.get(5)));
				Thread.sleep(100);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			statusOfProcess.add("fail");
		}
		return statusOfProcess;
	}
	public String loginProcess(String getData)
	{
		String status = "loginFail";
		try
		{
			String[] splitData = getData.split("-split-", 2);
			Set<String> allWindows1 = driver.getWindowHandles();
			for (String window1 : allWindows1) 
			{
				driver.switchTo().window(window1);
				if (driver.getCurrentUrl().contains("login?")) 
				{
					driver.switchTo().window(window1);
					status = "loginPass";
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					WebElement email = driver.findElement(By.cssSelector("input#email"));
					email.sendKeys(splitData[0]);
					WebElement pwd = driver.findElement(By.cssSelector("input#password"));
					pwd.sendKeys(splitData[1]);
					WebElement loginButton = driver.findElement(By.cssSelector("input#login_in"));
					loginButton.click();
					Thread.sleep(1000);
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(400));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
				}
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
			status = "loginFail";
		}
		return status;
	}
	
	public String choosePlan(String plan)
	{
		String checkPlanStatus = "choosePlanFail";
		boolean checkProcess = false;
		try 
		{
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			Set<String> allWindows = driver.getWindowHandles();
			for (String windows : allWindows)
			{
				driver.switchTo().window(windows);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				if (driver.getCurrentUrl().contains("choose-subscription"))
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					List<WebElement> listOfPlan = driver
							.findElements(By.cssSelector("div[id='custom-plans1'] div[class*='owl-item']"));// div[class=\"bttn\"]
																											// a
					for (int i = 0; i < listOfPlan.size(); i++)
					{
						WebElement selectPlan = listOfPlan.get(i)
								.findElement(By.cssSelector(" div[class*='plan_heading']"));
						String getPlanText = selectPlan.getText();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						if (getPlanText.contains(plan))
						{
							System.out.println("Plan is : " + getPlanText);
							WebElement clickPlan = listOfPlan.get(i)
									.findElement(By.cssSelector(" div[class='bttn'] a"));
							if (clickPlan.isDisplayed())
							{
								js.executeScript("arguments[0].click()", clickPlan);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(400));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(800));
								checkPlanStatus = "choosePlanPass";
								checkProcess = true;
								break;
							} 
							else
							{
								if (listOfPlan.get(i).findElement(By.cssSelector(" button[disabled]")).isDisplayed())
								{
									System.out.println("button is disabled");
									checkPlanStatus = "choosePlanFail";
									break;
								}
							}
						}
					}
					if(checkProcess != false)
					{
						checkPlanStatus = "choosePlanPass";
						break;
					}
				}
				else
				{
					System.out.println("Plan is not available");
					checkPlanStatus = "choosePlanPass";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkPlanStatus= "choosePlanFail";
		}

		return checkPlanStatus;
	}
	public String checkOutRazorpay(String amountFromExcel)
	{
		
		System.out.println("payment gateway process started");
		String checkRazorpay = "razorpayFail";
		try 
		{
		String amountWithTax[] = amountFromExcel.split("=");
		String amt = amountWithTax[1].replaceAll("\\s", "");
		String value = amt.replace(",", "").toString();
		int excelValue = Integer.parseInt(value);
		
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(500));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			
			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows)
			{
				driver.switchTo().window(window);
				if(driver.getCurrentUrl().contains("/basket/"))
				{
					driver.switchTo().window(window);
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(500));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					try
					{
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						
						WebElement checkoutAmount = driver.findElement(By.cssSelector("div[class='selected-plan'] span"));
						
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						
						if(checkoutAmount.isDisplayed())
						{
							String getCheckoutAmount = checkoutAmount.getText();
							Thread.sleep(1000);
							System.out.println("checkout Razorpay amount from Browser :" + getCheckoutAmount);
							String val[] = getCheckoutAmount.split("\\.");
							String editVal = val[0].replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").toString();
							
							int browserValue = Integer.parseInt(editVal);
							
							System.out.println("amountWithGST From Browser in Razorpay : " + browserValue);
							if (browserValue == excelValue) 
							{
								System.out.println("both Razorpay amount and excel amount are same");
								checkRazorpay = "razorpayPass";
							} 
							else 
							{
								System.out.println(" amount with GST from Excel : " + amountWithTax[1]);
								checkRazorpay = "razorpayFail";
							}
							try
							{
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("window.scrollBy(0,200)");
								Thread.sleep(400);
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
								if(driver.findElements(By.cssSelector("button#stripecheckout")).size()>0)
								{
									WebElement clickStripeButton = driver.findElement(By.cssSelector("button#stripecheckout"));
									js.executeScript("arguments[0].click()", clickStripeButton);
									checkRazorpay = "razorpayPass";
								}
								else
								{
									WebElement completePayment = driver
											.findElement(By.cssSelector("div[class='payment-buttons']>button[id='razorpay']"));
									js.executeScript("arguments[0].click()", completePayment);
									checkRazorpay = "razorpayPass";
								}
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
								checkRazorpay = "razorpayFail";
							}
							
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(700));
						}
						else
						{
							System.out.println("amount not shown in Razorpay screen");
							try
							{
								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("window.scrollBy(0,200)");
								Thread.sleep(400);
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
								WebElement completePayment = driver
										.findElement(By.cssSelector("div[class='payment-buttons']>button[id='razorpay']"));
								js.executeScript("arguments[0].click()", completePayment);
								checkRazorpay = "razorpayPass";
							}
							catch(Exception e)
							{
								e.printStackTrace();
								checkRazorpay = "razorpayFail";
							}
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(700));
						}
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkRazorpay = "razorpayFail";
		}
		
		return checkRazorpay;
	}
	public ArrayList<String> indiaPaymentProcess(String paymentModeFromExcel, String orderDetailsInfo)
	{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		ArrayList<String> getPaymentStatusFromIndia = new ArrayList<String>();
		String getData[] = paymentModeFromExcel.split("_");
		String getOrderAmount[] = orderDetailsInfo.split("=");
		if(checkcountry == true)
		{
			
		
		try
		{
			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows)
			{
				driver.switchTo().window(window);
				if (driver.getCurrentUrl().contains("basket")||driver.getCurrentUrl().contains("https://checkout.stripe.com/"))
				{
					driver.switchTo().window(window);
					switch (getData[0]) 
					{
					case "card":
						getPaymentStatusFromIndia.addAll(card(paymentModeFromExcel,getOrderAmount[1]));
						break;
					case "netBanking":
						getPaymentStatusFromIndia.addAll(netBanking(getData[1], getOrderAmount[1]));
						break;
					case "wallet":
						wallet();
						break;
					case "upi":
						upi();
						break;
					case "emi":
						emi(getData[1]);
						break;
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			getPaymentStatusFromIndia.add("fail");
		}
		}
		else
		{
			try
			{
				Set<String> allWindows = driver.getWindowHandles();
				for (String window : allWindows)
				{
					driver.switchTo().window(window);
					if (driver.getCurrentUrl().contains("basket"))
					{
						driver.switchTo().window(window);
						switch (getData[0]) 
						{
						case "card":
							getPaymentStatusFromIndia.addAll(card(getData[1], getOrderAmount[1]));
							break;
						}
					}
				}
			}	
			catch (Exception e)
			{
				e.printStackTrace();
				getPaymentStatusFromIndia.add("fail");
			}
		} 
		return getPaymentStatusFromIndia;
	}
	
	public ArrayList<String> netBanking(String bankName, String orderAmount)
	{
		ArrayList<String> paymentAndOrderStatus = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		try 
		{
			WebElement iFrame = driver.findElement(By.cssSelector(".razorpay-checkout-frame"));
			driver.switchTo().frame(iFrame);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			WebElement label = driver.findElement(By.xpath("//*[contains(text(),'Netbanking')]"));////label[@class='relative cursor-pointer']//span[@class='truncate font-medium']
			System.out.println(label.getText());
			if(label.isDisplayed())
			{
				label.click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			}
			boolean statusOfbankSelection = false;
			List<WebElement> listOfNetBanking = driver.findElements(By.xpath("//form[@class='flex flex-col gap-2 py-3'][2]//label[@class='relative cursor-pointer']"));
			for(int j = 0; j < listOfNetBanking.size(); j++)
			{
				//to select bank
				WebElement selectBankName = listOfNetBanking.get(j).findElement(By.xpath("//span[contains(normalize-space(text()),'IDBI')]"));
				String netbankingName = selectBankName.getText();
				System.out.println("net banking name : "+netbankingName);
				if(listOfNetBanking.get(j).getText().equalsIgnoreCase(bankName))
				{
					System.out.println("bank is selected : "+netbankingName);
					statusOfbankSelection = true;
					paymentAndOrderStatus.add("paymentPass");
					WebElement clickBank = listOfNetBanking.get(j);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
					wait.until(ExpectedConditions.elementToBeClickable(clickBank)).click();
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(370));
					System.out.println("amount paid through netbanking");
					String parentWindow = driver.getWindowHandle();//https://stage-ecomm-in.skillup.online/basket/
					Set<String> nextWindow = driver.getWindowHandles();
					for(String window : nextWindow)
					{
						 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
						 driver.switchTo().window(window);
						 Thread.sleep(1000);
							if(driver.getCurrentUrl().contains("https://api.razorpay.com/"))
							{
								 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
								driver.switchTo().window(window);
								driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));
								driver.findElement(By.cssSelector("button[class='success']")).click();
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
								driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(370));
								//paymentAndOrderStatus.add("pass");
								System.out.println("success button clicked");
							}
					}
					driver.switchTo().window(parentWindow);
					System.out.println("parent window : "+driver.getCurrentUrl());
					if(driver.findElements(By.xpath("//h4[contains(text(),'Payment successful. You are now enrolled!')]")).size()>0)
					{
						System.out.println("order details window");
					}
					paymentAndOrderStatus.add(this.indiaOrderDetails(orderAmount));
					break;
				}
			}
			if(!(statusOfbankSelection == true))
			{
				paymentAndOrderStatus.add(this.diffBank(bankName));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				paymentAndOrderStatus.add(this.indiaOrderDetails(orderAmount));
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			checkPaymentProcess = "fail";
			System.out.println("netbanking is fail");
			paymentAndOrderStatus.add("paymentFail");
		}
		return paymentAndOrderStatus;
	}
	
	public String indiaOrderDetails(String priceWOGSTFromExcel)
	{
		String checkOrderDetails = "orderDetailFail";
		try {
			int digit = Integer.parseInt(priceWOGSTFromExcel.replace(",", "").toString());
			  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(570));
			  System.out.println("checkout page");
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
			
			  WebElement getOrderDetails = driver.findElement(By.cssSelector("table[class=' spacing-pl8 spacing-pr8 spacing-pl20 spacing-pr20 mb table_border'] tr td"));
			  
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
			  System.out.println("order details : " + getOrderDetails.getText());
			  String amountOrderDetails = driver.findElement(By.cssSelector("table[class=' spacing-pl8 spacing-pr8 spacing-pl20 spacing-pr20 mb table_border'] tr[class='first_3_rows'] td[class='row_text spacing-pr20 spacing-pl20  spacing-pb8 spacing-pt8  table_border_bottom text_table']"))
					.getText().replace(",", "");
			  double n = Double.parseDouble(amountOrderDetails);
			  int v = (int) n;
			  System.out.println("amount order details : " + amountOrderDetails.toString());
			 
			  WebElement getOrderNumber = driver.findElement(By.cssSelector("div[class*='order_info']:nth-child(2) div:nth-child(3) h4"));
			 
			  orderNumber = getOrderNumber.getText();
			  System.out.println("OrderNumber: " + orderNumber);
			  
			  WebElement nameOfcourse = driver.findElement(By.cssSelector("table[class=' spacing-pl8 spacing-pr8 spacing-pl20 spacing-pr20 mb table_border'] tr td>p"));
			  getEnrolledCourseName = nameOfcourse.getText();
			  System.out.println("Enrolled course name : "+getEnrolledCourseName);
			  if(digit == v)
			  {
				checkOrderDetails = "orderDetailPass";
				System.out.println("both price without GST from excel and ordered amount values are same : " + amountOrderDetails);
			  } 
			  else 
			  {
				checkOrderDetails = "orderDetailFail";
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			checkOrderDetails = "orderDetailFail";
		}
		return checkOrderDetails;
	}
	public String diffBank(String bank)
	{
		try
		{
			WebElement selectBank = driver.findElement(By.cssSelector("button#bank-select"));
			selectBank.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(170));
			List<WebElement> enterBank = driver.findElements(By.cssSelector("div[id='netbanking_bank_select_search_results'] div[class='list svelte-15q0kle'] img[alt]"));
			for(int i = 0; i < enterBank.size(); i++) 
			{
				if(enterBank.get(i).getAttribute("alt").equalsIgnoreCase(bank))
				{
					System.out.println("bank name is : " + enterBank.get(i).getAttribute("alt"));
					enterBank.get(i).click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(370));
					WebElement clickPayNow = driver.findElement(By.cssSelector("button[id*='redesign']"));
					clickPayNow.click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(370));
					break;
				} 
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			checkPaymentProcess = "fail";
			System.out.println("problem in diffBank selection in the netbanking ");
		}
		return bank;
	}
	public ArrayList<String> card(String data, String orderAmount)
    {
		ArrayList<String> paymentAndOrderStatus = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try 
		{
			if(driver.findElements(By.xpath("//*[contains(text(),'Pay without Link')]")).size()>0)
			{
				WebElement payWithoutLink = driver.findElement(By.xpath("//*[contains(text(),'Pay without Link')]"));
				js.executeScript("arguments[0].click()", payWithoutLink);
				Set<String> allWindows = driver.getWindowHandles();
				for (String window : allWindows)
				{
					driver.switchTo().window(window);
					if (driver.getCurrentUrl().contains("basket")||driver.getCurrentUrl().contains("https://checkout.stripe.com/"))
					{
						driver.switchTo().window(window);
						if(driver.getCurrentUrl().contains("https://checkout.stripe.com/"))
						{
							driver.switchTo().window(window);
							WebElement cardNumber = driver.findElement(By.cssSelector("input#cardNumber"));
							cardNumber.sendKeys("4242 4242 4242 4242");
							WebElement cardExpiryDate = driver.findElement(By.cssSelector("input#cardExpiry"));
							cardExpiryDate.sendKeys("12/26");
							WebElement cardCVC = driver.findElement(By.cssSelector("input#cardCvc"));
							cardCVC.sendKeys("234");
							WebElement cardFullName = driver.findElement(By.cssSelector("input#billingName"));
							cardFullName.sendKeys("Hemamalini");
							WebElement cardZIP = driver.findElement(By.cssSelector("input#billingPostalCode"));
							cardZIP.sendKeys("37882");
							WebElement clickPayButton = driver.findElement(By.xpath("//div[@class='SubmitButton-IconContainer']"));
							clickPayButton.click();
							paymentAndOrderStatus.add("cardPass");
						}
					}
				}
				Set<String> allWindow = driver.getWindowHandles();
				for (String window : allWindow)
				{
					driver.switchTo().window(window);
					if (driver.getCurrentUrl().contains("checkout/receipt"))
					{
						driver.switchTo().window(window);
						if(driver.findElements(By.xpath("//h4[contains(text(),'Payment successful. You are now enrolled!')]")).size()>0)
						{
							System.out.println("order details window");
							checkPaymentProcess = "cardPass";
						}
						paymentAndOrderStatus.add(this.indiaOrderDetails(orderAmount));
						break;
					}
				}
			}
			else
			{
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				WebElement iFrame = driver.findElement(By.cssSelector(".razorpay-checkout-frame"));
				driver.switchTo().frame(iFrame);
				List<WebElement> buttons = driver.findElements(By.cssSelector(".border-list button"));
				for (WebElement button : buttons)
				{
					WebElement label = button.findElement(By.cssSelector("div.title > div"));
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					System.out.println(label.getText());
					if(label.getText().equalsIgnoreCase("Pay using Card"))
					{
						System.out.println("card name is : " + label.getText());
						label.click();
						if (driver.findElement(By.cssSelector("button#otp-sec")).isDisplayed()) 
						{
							WebElement skipOTP = driver.findElement(By.cssSelector("button#otp-sec"));
							skipOTP.click();
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
							WebElement cardNumber = driver.findElement(By.cssSelector("input#card_number"));
							cardNumber.sendKeys("4242 4242 4242 4242");
							WebElement cardExpiry = driver.findElement(By.cssSelector("input#card_expiry"));
							cardExpiry.sendKeys("12/26");
							WebElement cardName = driver.findElement(By.cssSelector("input#card_name"));
							cardName.sendKeys("testing");
							WebElement cvv = driver.findElement(By.cssSelector("input#card_cvv"));
							cvv.sendKeys("234");
							WebElement payNowButton = driver
									.findElement(By.cssSelector("form#form button[id='redesign-v15-cta']"));
							payNowButton.click();
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(270));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(370));
							System.out.println("pay now button clicked");
							WebElement payWOSavingCard = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
							payWOSavingCard.click();
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(470));
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
							checkPaymentProcess = "cardPass";
						} 
						else 
						{
							WebElement cardNumber = driver.findElement(By.cssSelector("input#card_number"));
							cardNumber.sendKeys("4242 4242 4242 4242");
							WebElement cardExpiry = driver.findElement(By.cssSelector("input#card_expiry"));
							cardExpiry.sendKeys("12/26");
							WebElement cardName = driver.findElement(By.cssSelector("input#card_name"));
							cardName.sendKeys("testing");
							WebElement cvv = driver.findElement(By.cssSelector("input#card_cvv"));
							cvv.sendKeys("234");
							WebElement payNowButton = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
							payNowButton.click();
							System.out.println("Pay now button is clicked");
							driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
							driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(470));
							checkPaymentProcess = "cardPass";
						}
					}
				}
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			checkPaymentProcess = "cardFail";
			System.out.println("card payment is fail");
		}

		return paymentAndOrderStatus;
	}
	public void USOrderDetails() 
	{
		try {
			Set<String> windows = driver.getWindowHandles();
			for (String win : windows) {
				driver.switchTo().window(win);
				System.out.println(driver.getCurrentUrl());
				System.out.println("checkout page");
				WebElement clickDashBoard = driver
						.findElement(By.xpath("//a[contains(text(),'Continue to your Dashboard')]"));
				clickDashBoard.click();
				WebElement getOrderDetails = driver.findElement(By.cssSelector("table[class=' spacing-pl8 spacing-pr8 spacing-pl20 spacing-pr20 mb table_border'] tr td"));
				System.out.println(getOrderDetails.getText());
				String amountOrderDetails = driver.findElement(By.cssSelector(
						"table[class=' spacing-pl8 spacing-pr8 spacing-pl20 spacing-pr20 mb table_border'] tr td[class='spacing-pl8 spacing-pr20 spacing-pl20  spacing-pt8 table_lastrow last_prize table_border_bottom text_table']"))
						.getText();
				System.out.println(amountOrderDetails);
				if (amountWithTax.equalsIgnoreCase(amountOrderDetails)) {
					System.out.println("both are same amount ");
				} else {
					errorCells.add(5);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String wallet() {
		try {
			WebElement iFrame = driver.findElement(By.cssSelector(".razorpay-checkout-frame"));
			driver.switchTo().frame(iFrame);
			List<WebElement> buttons = driver.findElements(By.cssSelector(".border-list button"));
			for (WebElement button : buttons) {
				WebElement label = button.findElement(By.cssSelector("div.title > div"));
				System.out.println(label.getText());
				if (label.getText().equalsIgnoreCase("Pay using Wallet")) {
					label.click();
					WebElement phonePe = driver.findElement(By.cssSelector("div#wallet-radio-phonepe"));
					phonePe.click();
					WebElement payNowButton = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
					payNowButton.click();
					checkPaymentProcess = "true";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkPaymentProcess = "false";
		}
		return checkPaymentProcess;
	}

	public String emi(String bankName)
	{
		String getData[] = bankName.split("_");
		try {
			WebElement iFrame = driver.findElement(By.cssSelector(".razorpay-checkout-frame"));
			driver.switchTo().frame(iFrame);
			List<WebElement> buttons = driver.findElements(By.cssSelector(".border-list button"));
			for (WebElement button : buttons) {
				WebElement label = button.findElement(By.cssSelector("div.title > div"));
				System.out.println(label.getText());
				if (label.getText().equalsIgnoreCase("Pay using EMI")) {
					label.click();
					List<WebElement> clickEMIBank = driver.findElements(By.cssSelector("div[id*='bank-item']"));
					for (int i = 0; i < clickEMIBank.size(); i++) {
						if (clickEMIBank.get(i).getText().contains(getData[0])) {
							clickEMIBank.get(i).click();
							WebElement clickContinue = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
							clickContinue.click();
							checkPaymentProcess = "pass";
							break;
						}
					}
					if (checkPaymentProcess != "pass") {
						this.moreBank(getData[0]);
					}
					List<WebElement> selectEMIPlan = driver
							.findElements(By.cssSelector("div[class='plan-amount svelte-1ooipi6']"));
					for (int j = 0; j < selectEMIPlan.size(); j++) 
					{
						selectEMIPlan.get(j).click();
						break;
					}

					WebElement selectPlanButton = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
					selectPlanButton.click();
					WebElement enterCardNumber = driver.findElement(By.cssSelector(
							"div#container div[tab='emi'] div[id='root'] div[id='add-card-container'] input#card_number"));
					enterCardNumber.sendKeys("5241 8100 0000 0000");
					WebElement enterExpiry = driver.findElement(By.cssSelector(
							"div#container div[tab='emi'] div[id='root'] div[id='add-card-container'] input#card_expiry"));
					enterExpiry.sendKeys("12/23");
					WebElement enterCardName = driver.findElement(By.cssSelector(
							"div#container div[tab='emi'] div[id='root'] div[id='add-card-container'] input#card_name"));
					enterCardName.sendKeys("hemamalini-murugesan");
					WebElement enterCVV = driver.findElement(By.cssSelector(
							"div#container div[tab='emi'] div[id='root'] div[id='add-card-container'] input#card_cvv"));
					enterCVV.sendKeys("234");
					WebElement payViaEMI = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
					payViaEMI.click();
					WebElement clickContinue = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
					clickContinue.click();
					checkPaymentProcess = "pass";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkPaymentProcess = "fail";
			System.out.println("problem in emi process");
		}
		return checkPaymentProcess;
	}
	public String moreBank(String bank)
	{
		try
		{
			WebElement clickMoreBank = driver.findElement(By.cssSelector("div[class='emi-bank-item'] div[class*='more-bank-icon']"));
			clickMoreBank.click();
			List<WebElement> bankName = driver.findElements(By.cssSelector("div[id='emi_bank_select_search_results'] div[id*='emi_bank'] div[class*='search-bank-name']"));
			for (int i = 0; i < bankName.size(); i++)
			{
				if (bankName.get(i).getText().contains(bank))
				{
					System.out.println("bank name is : " + bankName.get(i).getText());
					bankName.get(i).click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bank;
	}
	public String upi() {
		try {
			WebElement iFrame = driver.findElement(By.cssSelector(".razorpay-checkout-frame"));
			driver.switchTo().frame(iFrame);
			List<WebElement> buttons = driver.findElements(By.cssSelector(".border-list button"));
			for (WebElement button : buttons) {
				WebElement label = button.findElement(By.cssSelector("div.title > div"));
				System.out.println(label.getText());
				if (label.getText().equalsIgnoreCase("Pay using UPI")) {
					label.click();
					WebElement clickUPITab = driver.findElement(By.cssSelector("div#new-vpa-field-upi"));
					clickUPITab.click();
					WebElement upiID = driver.findElement(By.cssSelector("input#vpa-upi"));
					upiID.sendKeys("success@razorpay");
					WebElement payNow = driver.findElement(By.cssSelector("button#redesign-v15-cta"));
					payNow.click();
					checkPaymentProcess = "pass";
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
						checkPaymentProcess = "fail";
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkPaymentProcess = "fail";
			System.out.println("UPI payment fail");
		}
		return checkPaymentProcess;
	}

	public ArrayList<String> programLocator()
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			String getInitialsOnDropDown = driver.findElement(By.cssSelector("li[class='SigNUP'] span[class='NOLoginDSKTXT']")).getText();
			System.out.println("Initial on DropDown : "+getInitialsOnDropDown);
			WebElement clickDropdownIcon = driver.findElement(By.cssSelector("li[class='SigNUP']>a"));
			if(clickDropdownIcon.isDisplayed())
			{
				clickDropdownIcon.click();
				status.add("pass");
			}
			
			WebElement clickDashboard = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Primary']>li:nth-child(2)>a"));
			if(clickDashboard.isDisplayed())
			{
				clickDashboard.click();
				
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("dashboard"))
					{
						driver.switchTo().window(window);
						System.out.println("dashboard page");
						status.add("pass");
					}
				}
			}
			
			WebElement clickDropdownIcon2 = driver.findElement(By.cssSelector("li[class*='Header_SigNUP'] img[alt='icon']"));
			if(clickDropdownIcon2.isDisplayed())
			{
				clickDropdownIcon2.click();
				status.add("pass");
			}
			
			
			WebElement clickProfile = driver.findElement(By.cssSelector("ul[class*='dropdown-menu']>li:nth-child(3)>a"));
			if(clickProfile.isDisplayed())
			{
				clickProfile.click();
				
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("/u/"))
					{
						driver.switchTo().window(window);
						System.out.println("profile page");
						status.add("pass");
					}
				}
			}
			
			WebElement clickDropdownIcon3 = driver.findElement(By.cssSelector("li[class='SigNUP'] img[class='dPaRoW']"));
			if(clickDropdownIcon3.isDisplayed())
			{
				clickDropdownIcon3.click();
				status.add("pass");
			}
			
			WebElement clickAccount = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Primary']>li:nth-child(4)>a"));
			if(clickAccount.isDisplayed())
			{
				clickAccount.click();
				
				Set<String> windows = driver.getWindowHandles();
				for(String window : windows)
				{
					driver.switchTo().window(window);
					if(driver.getCurrentUrl().contains("settings"))
					{
						driver.switchTo().window(window);
						System.out.println("dashboard page");
						status.add("pass");
					}
				}
			}
			WebElement clickDropdownIcon4 = driver.findElement(By.cssSelector("li[class*='SigNUP'] img[class='dPaRoW']"));
			if(clickDropdownIcon4.isDisplayed())
			{
				clickDropdownIcon4.click();
				status.add("pass");
			}
			Thread.sleep(2000);
			WebElement clickSignOut = driver.findElement(By.cssSelector("ul[class*='dropdown-menu Primary02_Blue'] li:nth-child(5) a"));
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			if(clickSignOut.isDisplayed())
			{
				js3.executeScript("arguments[0].click()", clickSignOut);
				status.add("pass");
			}
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add("Fail");
		}
		return status;
	}
}

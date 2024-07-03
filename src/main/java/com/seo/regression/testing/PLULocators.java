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
import org.openqa.selenium.interactions.Actions;

public class PLULocators
{
	WebDriver driver;
	
	public PLULocators(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public String verifyTitle(String titleName)
	{
		String status="";
		try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
			WebElement clickDropdown = driver.findElement(By.cssSelector("li[class*='nav-item dropdown Header_dropdown']>a[id='navbarDropdown']"));
			clickDropdown.click();
			List<WebElement> learningPartners = driver.findElements(By.cssSelector("ul[class='dropdown-menu dropdown-cat Header_dropdownMenu__oDZ7V show'] div[class='LearningPartners catcolumn divbox2'] ul[class='learning-Partners']>li>a"));
			for(int i = 0; i < learningPartners.size(); i++)
			{
				if(learningPartners.get(i).getAttribute("href").contains("pacific"))
				{
					learningPartners.get(i).click();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
				}
			}
			WebElement name = driver.findElement(By.cssSelector("h1[class*='TopBanner_universityName']"));
			String nameContent = name.getText().trim();
			if(nameContent.equalsIgnoreCase(titleName.trim()))
			{
				status = "pass";
			}
			else
			{
				status = "fail";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	public String verifyBanner(String bannerData)
	{
		String status="";
		WebElement banner = driver.findElement(By.cssSelector("p[class*='TopBanner_coursesName']"));
		String bannerText = banner.getText().trim();
		if(bannerText.equalsIgnoreCase(bannerData.trim()))
		{
			status = "pass";
		}
		else
		{
			status = "fail";
		}
		return status;
	}
	
	public String verifyDescription(String data)
	{
		String status="";
		WebElement description = driver.findElement(By.cssSelector("div[class*='PluIntroduction_introductionContainer'] p"));
		String descriptionText = description.getText().trim();
		if(data.equalsIgnoreCase(descriptionText.trim()))
		{
			status = "pass";
		}
		else
		{
			status = "fail";
		}
		return status;
	}
	public ArrayList<String> verifyPrograms(ArrayList<String>  programs)
	{

		ArrayList<String> processStatus = new ArrayList<String>();
		ArrayList<String> cardData = new ArrayList<String>();
		ArrayList<String> pageData = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{
			js.executeScript("window.scrollBy(0, 600)", "");
			List<WebElement> ListOfProgram = driver.findElements(By.cssSelector("section[class*='container-fluid TechPrograms_mainContainer'] div[class*='row g-3'] div[class*='TechPrograms_cardWrapper']>a"));
			for(int i = 0; i < ListOfProgram.size(); i++)
			{
				
				js.executeScript("arguments[0].scrollIntoView();", ListOfProgram.get(i));
				
				String url = ListOfProgram.get(i).getAttribute("href");
				
				processStatus.add(this.checkURLStatus(url));
				
				
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return processStatus;
		
	
	}
	public ArrayList<String> verifyPLUCourse(ArrayList<String> course)
	{
		ArrayList<String> failedUrls = new ArrayList<String>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try
		{

			js.executeScript("window.scrollBy(0, 800)", "");
			List<WebElement> techProgram = driver.findElements(By.cssSelector("section[class*='HumanSkills_mainContainer'] div[class*='HumanSkills_mainContent'] a"));
			for(int i = 0; i < techProgram.size(); i++)
			{
				String url = techProgram.get(i).getAttribute("href");
				System.out.println("PLU course card starts execution : "+url);
				String urlLinkStatus = this.checkURLStatus(url);
				if(urlLinkStatus.contains("fail"))
				{
					failedUrls.add(url+urlLinkStatus);
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,1500)", "");
		return failedUrls;
	}
	public ArrayList<String> verifyFAQ(ArrayList<String>  faq)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			boolean checkQuestionStatus = false;
			String answerStatus = "answerPass";
			List<WebElement> question = driver.findElements(By.cssSelector("div[class='accordion'] div[class*='accordion-item'] button[class*='accordion-button']"));
			if(question.size()>0)
			{
				for(int i = 0; i < question.size(); i++)
				{
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					if(faq.get(1).trim().equalsIgnoreCase(question.get(i).getText().trim()))
					{
						checkQuestionStatus = true;
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click()", question.get(i));
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
						System.out.println("question is correct");
						List<WebElement> answer = driver.findElements(By.cssSelector("div[class='accordion'] div[class*='accordion-item'] div[class*='accordion-collapse'] div[class*='Accordion_accordionBody']"));
						for(int j = 0; j < answer.size(); j++)
						{
							if(answer.get(j).getText().equalsIgnoreCase(faq.get(2)))
							{
								System.out.println("answer is correct");
								answerStatus = "answerPass";
								break;
							}
						}
					  }
				}
				if(!answerStatus.equalsIgnoreCase("answerPass"))
				{
					status.add("answerFail");
				}
				if(!checkQuestionStatus == true)
				{
					status.add("questionFail");
					System.out.println("question is not correct");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
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
				status = "fail"+respCode;
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
	public ArrayList<String> programContent()
	{
		ArrayList<String> cardContentStatus = new ArrayList<String>();
		ArrayList<String> cardContent = new ArrayList<String>();
		
		try
		{
			List<WebElement> pgmCards = driver.findElements(By.cssSelector("div[class*='TechPrograms_cardWrapper'] div[class*='TechPrograms_cardContent']"));
			for(int i = 0; i < pgmCards.size(); i++)
			{
				WebElement prgIcon = pgmCards.get(i).findElement(By.cssSelector(" img[alt='PLU Medal']"));
				WebElement prgPartner = pgmCards.get(i).findElement(By.cssSelector(" div[class*='TechPrograms_plu']"));
				WebElement prgType = pgmCards.get(i).findElement(By.cssSelector(" p[class*='TechPrograms_mentoredBe']"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cardContentStatus;
	}
	
	public ArrayList<String> verfyCss(ArrayList<String> data)
	{
		ArrayList<String> status = new ArrayList<String>();
		try
		{
			WebElement BackGroundColor_certificateLeftContent = driver.findElement(By.cssSelector("div[class='CertificatesEarn_leftContent__t2X6r']"));
			String bgColor = BackGroundColor_certificateLeftContent.getCssValue("background-color");
			if(bgColor.equalsIgnoreCase(data.get(1)))
			{
				System.out.println("PLU bg color is yellow");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();;
		}
		return status;
	}
}

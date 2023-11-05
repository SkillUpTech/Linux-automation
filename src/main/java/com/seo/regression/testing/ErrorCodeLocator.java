package com.seo.regression.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ErrorCodeLocator
{
	WebDriver driver;
	public ErrorCodeLocator(WebDriver driver)
	{
		this.driver = driver;
	}
	
	ArrayList<String> statusOfErrorCode = new ArrayList<String>();
	
	public ArrayList<String> checkCourseCode(ArrayList<String> codeFromExcel)
	{
		String url="";
		int respCode = 200;
		HttpURLConnection huc = null;
		String addHosturl = "";
		for(int i = 1; i < codeFromExcel.size(); i++)
		{
			String endURL = codeFromExcel.get(i);
			if(endURL.contains("enterprise"))
			{	
				if(OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE).contains("stage"))
				{
					url = "https://stagecourses-in.skillup.online"+endURL;
					addHosturl = url;
				}
			}
			else
			{
				url = OpenWebsite.setEnvironment(RegressionTesting.ENV_TO_USE)+endURL;
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
				int status = conn.getResponseCode();
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

				while ((inputLine = in.readLine()) != null) {
					html.append(inputLine);
				}
				in.close();

				System.out.println("Done");
				if(status>200)
				{
					statusOfErrorCode.add(endURL);
				}
			    } 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			  }
			
		return statusOfErrorCode;
	}
}

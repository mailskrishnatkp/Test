/*package com.oaknorth.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.oaknorth.pageobjects.HomePage;
import com.oaknorth.reports.Email_SMS_Report;

public class SetUp extends CommonMethods{
	public  HomePage hPage=new HomePage(driver);
	@BeforeSuite
	public void browseropen() throws InterruptedException {
		if (prop.getProperty("browser").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDirver"));
			driver = new ChromeDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.internetexplore.driver", prop.getProperty("IEDriver"));
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		driver.manage().window().maximize();
		//logger.log(LogStatus.INFO, "Browser opened");
	}


	// Close the browser and stop the recording and send mailSMs
	@AfterSuite
	public void browserclose() throws InterruptedException ATUTestRecorderException {

		changeUnknowToSkip();
		flush();
		results();
		driver.quit();
		//recorder.stop();
		Email_SMS_Report.mailSending();
		
	}

}
*/
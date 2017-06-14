package com.oaknorth.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import jxl.Sheet;
import jxl.Workbook;

public class DriverScript {
	// Webdriver initiate
	public static WebDriver driver=null;//= new FirefoxDriver();
	// public static WebDriver driver;

	// Record
	// Test data: Defining values for Test data objects
	public static Properties prop = new Properties();
	public static FileInputStream fisprop = null;
	public static FileInputStream fisTestData = null;
	public static Workbook wb = null;
	public static Sheet sloc = null;
	public static Sheet logIn = null;
	// Recording method initiate
	// public static ATUTestRecorder recorder;
	// public static ATUTestRecorder recorder = null;

	// Extent Reports
	public static String report = null;
	public static ExtentReports extent = null;
	public static ExtentTest logger = null;
	public static String pdate = new SimpleDateFormat("yyyy_MM_dd_HHmm").format(Calendar.getInstance().getTime());
	public static String passCount = null;
	public static String failCount = null;
	public static String skipCount = null;
	public static String totalCount = null;
	public static List<ExtentTest> extentTestListInstances = new ArrayList<ExtentTest>();

	public static Connection con = null;
	public static Statement stmt = null;

	// Loading Properties file
	static {

		try {
			fisprop = new FileInputStream(
					"C:\\Users\\LENOVO\\Downloads\\com.oaknorth\\src\\main\\java\\com\\oaknorth\\properties\\prop.properties");
			prop.load(fisprop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Loading Test data
	static {
		try {
			fisTestData = new FileInputStream(prop.getProperty("testData"));
			wb = Workbook.getWorkbook(fisTestData);
			sloc = wb.getSheet("location");
			logIn = wb.getSheet("login");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Initiate record
	/*
	 * static { try { recorder = new
	 * ATUTestRecorder(prop.getProperty("recordPath"),
	 * " Mart_Regression_Recording-" + pdate, false); recorder.start(); } catch
	 * (ATUTestRecorderException e) { e.printStackTrace(); } }
	 */
	// Extent Report initiating
	static {
		String scrFolder = prop.getProperty("screenShotsPath") + pdate;
		new File(scrFolder).mkdir();

		report = prop.getProperty("reportPath") + "OakNorth_Automation_Execution_Report_" + pdate + ".html";
		extent = new ExtentReports(report, true, DisplayOrder.OLDEST_FIRST);
		// extent = new ExtentReports(prop.getProperty("reportPath"), true,
		// DisplayOrder.OLDEST_FIRST);
		// report = scrFolder;
		//logger = extent.startTest("testing", "sample");

	}

	public void reportConfigLoad() {
		extent.loadConfig(
				new File("C:\\Users\\LENOVO\\Downloads\\com.oaknorth\\extent-config.xml"));
		extent.addSystemInfo("AutomationTeam", "Produciton");

	}

	// Get results by opening extent reports and read from that html page
	public void results() throws InterruptedException {
		driver.get(report);
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("menuSlider"))).click();
		Thread.sleep(3000);

		totalCount = driver.findElement(By.xpath(prop.getProperty("reportTotalCount"))).getText();
		failCount = driver.findElement(By.xpath(prop.getProperty("reportFailCount"))).getText();
		passCount = driver.findElement(By.xpath(prop.getProperty("reportPassCount"))).getText();
		skipCount = driver.findElement(By.xpath(prop.getProperty("reportSkipCount"))).getText();
		System.out.println("Total cases=" + totalCount + " Pass count=" + passCount + " fail count= " + failCount
				+ " skip count= " + skipCount);
	}

	// Get Present Time Stamp (used at screenshot name and reports folder names)
	public static String getTimeStamp() {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timestamp;

	}

	// Add screenshot
	public static String screenshot(WebDriver driver) throws IOException {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(
				(prop.getProperty("screenShotsPath") + pdate + File.separator) + e + "-" + getTimeStamp() + ".png"));
		return (prop.getProperty("screenShotsPath") + pdate + File.separator) + e + "-" + getTimeStamp() + ".png";
	}

	
}

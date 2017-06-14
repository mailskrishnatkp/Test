package com.oaknorth.config;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.base.Function;
import com.oaknorth.reports.Email_SMS_Report;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CommonMethods extends DriverScript {

	// Assert True with condition and Message
	public static void assertTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}

	// assert equal with actual expected and message
	public static void assertEquals(String actual, String expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	// Assert Equals with integers compare
	public static void assertEqualsInt(int actual, int expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	// click on element
	public static void clickEle(WebElement element) {
		element.click();
	}

	// enter text on a element
	public static void enterTxt(WebElement element, String text) {
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	// Enter a value on a element
	public static void enterKeys(WebElement element, Keys key) {

		element.sendKeys(key);
	}

	// getText for a element
	public static String getText(WebElement element) {

		return element.getText();
	}

	// getAtrribute for a element
	public static String getAttr(WebElement element, String attributeValue) {

		return element.getAttribute(attributeValue);

	}

	// isDisplay for a element
	public boolean display(WebElement ele) {
		return ele.isDisplayed();

	}

	// isEnable for a element
	public boolean enable(WebElement ele) {
		return ele.isEnabled();

	}

	// Found an element
	public boolean availablity(By locaor) {
		return driver.findElements(locaor).size() != 0;
	}

	// load Properties from Pro file
	public static Properties getProperties() {
		return prop;
	}

	// Implicit Wait
	public void iwait(int timeInSec) {
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
	}

	// Explicit wait
	public void ewait(WebElement ele) {
		(new WebDriverWait(DriverScript.driver, 30)).until(ExpectedConditions.elementToBeClickable(ele));
	}

	// Explicit wait
	public void pwait(WebElement ele) {
		(new WebDriverWait(DriverScript.driver, 20)).until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) ele));
	}

	// Fluent Wait
	public void fluentWait(WebElement ele) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(DriverScript.driver)
				.withTimeout(30, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		// fluentWait.until(ExpectedConditions.presenceOfElementLocated(ele));
		fluentWait.until(ExpectedConditions.visibilityOf(ele));

		FluentWait<ChromeDriver> wait = new FluentWait<ChromeDriver>((ChromeDriver) driver);
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		List<WebElement> targetElements = wait.until(new Function<ChromeDriver, List<WebElement>>() {
			public List<WebElement> apply(ChromeDriver driver) {
				List<WebElement> elements = driver.findElements(By.id("periodicElement"));
				int length = elements.size();
				System.out.println("current Length is" + length);
				if (length == 5) {
					System.out.println("Target Length is hit" + length);
					return elements;
				}
				return null;
			}
		});

	}

	// Thread.sleep method
	public void sleep(int timeInMSec) {
		try {
			Thread.sleep(timeInMSec);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	// Print error message
	public void printE(Object text) {
		System.err.println(text);
	}

	// Refresh page
	public void refresh() {

		driver.navigate().refresh();
		sleep(2000);
	}

	// To get screenshot
	public void screenshot() throws IOException {
		logger.log(LogStatus.FAIL, "failed", logger.addScreenCapture(screenshot(driver)));
		// logger.log(LogStatus.FAIL, "failed", "Snapshot below: " +
		// logger.addScreenCapture(screenshot(driver)));

	}

	// Get Log info
	public void info(String message) {
		logger.log(LogStatus.INFO, message);

	}

	// Get log info and print
	public void log(Object text) {
		System.out.println(text);
		logger.log(LogStatus.INFO, text.toString());
	}

	// Get fail and take screenshot
	public void fail(String message) {
		try {
			logger.log(LogStatus.FAIL, message);
			// logger.log(LogStatus.FAIL,
			// message,logger.addScreenCapture(screenshot(driver)));
			// logger.log(LogStatus.FAIL, "failed", "Failed Screenshot: " +
			// logger.addScreenCapture(screenshot(driver)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get pass
	public void pass(String message) {
		logger.log(LogStatus.PASS, message);
	}

	// homePage
	public void clickLogo() {
		clickEle(DriverScript.driver.findElement(By.xpath("html/body/div[1]/div/header/div/div[1]/a/img")));
		System.out.println("Clicked on Home (Refresh Page)");
		sleep(5000);
	}

	// extend report start for a test case
	public static void startR(String functionality) {
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();

		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		logger = extent.startTest(functionality + "-" + e.getMethodName());
		extentTestListInstances.add(logger);

	}

	// extend rport stop for a test case
	public static void stopR() {
		extent.endTest(logger);

	}

	public void flush() {
		extent.flush();

	}

	public void removeExcelRow(String sheetName, int rowNO) {
		try {
			Workbook wb1 = Workbook.getWorkbook(new File(prop.getProperty("testData")));
			WritableWorkbook w1 = Workbook.createWorkbook(new File(prop.getProperty("testData")), wb1);
			WritableSheet s1 = w1.getSheet(sheetName);
			s1.removeRow(rowNO);
			w1.write();
			w1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Change unknown status to skip
	public static void changeUnknowToSkip() {
		System.out.println("test cases: " + extentTestListInstances);
		if (extentTestListInstances != null) {
			System.out.println("test cases size: " + extentTestListInstances.size());
			for (ExtentTest extentTest : extentTestListInstances) {
				if (extentTest != null && extentTest.getRunStatus() == LogStatus.UNKNOWN) {
					extentTest.log(LogStatus.SKIP, extentTest.getDescription());
				}
			}
		}
	}

	public void actionClick(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele);
		action.click();
		sleep(2000);
		action.build().perform();
		sleep(2000);
	}

	public void scrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		sleep(2000);
	}

	@BeforeTest
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
		//log("browser opened");
		// logger.log(LogStatus.INFO, "Browser opened");
	}

	@AfterSuite
	public void browserclose() throws InterruptedException {

		changeUnknowToSkip();
		flush();
		results();
		driver.quit();
		Email_SMS_Report.mailSending();

	}

}

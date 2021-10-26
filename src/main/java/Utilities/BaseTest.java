package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public FileInputStream fileinp;
	public static ExtentHtmlReporter htmlReporter;
	public static String Extentreportpath;
	public static ExtentReports extent;
	public static ExtentTest test;

	@Parameters({ "OS", "browser" })
	@BeforeSuite
	public void loadhtml(String OS, String browser) {
		htmlReporter = new ExtentHtmlReporter("E:\\Sample\\TestNGPrac\\test-output\\testReport.html");
		Extentreportpath="E:\\Sample\\TestNGPrac\\test-output\\Screenshots\\";
		Log.info("*******Start*********");
		TestProperties.loadAllPropertie();
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", browser);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		Log.info("Loaded all flies");
	}

	@BeforeMethod(alwaysRun = true)
	public void loadData() throws IOException {
		try {
			setDriver();
		} catch (Exception e) {
			Log.error("*******Failed to load files *********");
		}

	}

	public void setDriver() {
		try {
		if (TestProperties.getProperty("browser") != null) {
			switch (TestProperties.getProperty("browser").toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
				Log.info("Chrome Driver launched");
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				Log.info("FireFox Driver launched");
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				Log.info("Edge Driver launched");
				break;
			default:
				WebDriverManager.chromedriver().setup();
				Log.info("Default chrome Driver launched");
				break;
			}

		}
		driver.get(TestProperties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//BaseTest.test.log(Status.PASS, TestProperties.getProperty("browser")+"browser launched successfully");
		} catch (Exception e) {
			Log.error("*******Unable to launch browser "+TestProperties.getProperty("browser")+"*********");
			/*
			 * BaseTest.test.log(Status.FAIL,
			 * TestProperties.getProperty("browser")+" browser failed launch");
			 * BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
			 */
		}

		}

	public static WebDriver getDriver() {
		
			if(driver==null) {
				BaseTest.test.log(Status.PASS,"driver is null");	
			}
		return driver;
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}

		getDriver().close();

	}
	
	
	
	public static void reportStep(String message,String status,int scFlag) {
		scFlag=1;
		try {
			MediaEntityModelProvider mediaModel;
			if(status.toUpperCase().equals("PASS")) {
				if(scFlag>0) {
					mediaModel=MediaEntityBuilder.createScreenCaptureFromPath("Screenshots/"+getScreenShot(driver)+".jpg").build();
				    test.log(Status.PASS,message, mediaModel);
				   // testStatusDetail.put(getTestName(),"-");
				}
				else {
					test.log(Status.PASS,message);
				}
			}
			else if(status.toUpperCase().equals("FAIL")) {
				if(scFlag>0) {
					mediaModel=MediaEntityBuilder.createScreenCaptureFromPath("Screenshots/"+getScreenShot(driver)+".jpg").build();
				    test.log(Status.FAIL,message, mediaModel);
				    //testStatusDetail.put(getTestName(),"-");
				}
				else {
					test.log(Status.FAIL,message);
				}
			}
			else if(status.toUpperCase().equals("INFO")) {
				if(scFlag>0) {
					mediaModel=MediaEntityBuilder.createScreenCaptureFromPath("Screenshots/"+getScreenShot(driver)+".jpg").build();
				    test.log(Status.INFO,message, mediaModel);
				    //testStatusDetail.put(getTestName(),"-");
				}
				else {
					test.log(Status.INFO,message);
				}
			}
			else if(status.toUpperCase().equals("ERROR")) {
				    test.log(Status.FAIL,message);
				    //testStatusDetail.put(getTestName(),"-");
			}
			else if(status.toUpperCase().equals("WARNING")) {
				if(scFlag>0) {
					mediaModel=MediaEntityBuilder.createScreenCaptureFromPath("Screenshots/"+getScreenShot(driver)+".jpg").build();
				    test.log(Status.WARNING,message, mediaModel);
				    //testStatusDetail.put(getTestName(),"-");
				}
				else {
					test.log(Status.WARNING,message);
				}
			}
			else if(status.toUpperCase().equals("SKIP")) {
				if(scFlag>0) {
					mediaModel=MediaEntityBuilder.createScreenCaptureFromPath("Screenshots/"+getScreenShot(driver)+".jpg").build();
				    test.log(Status.SKIP,message, mediaModel);
				    //testStatusDetail.put(getTestName(),"-");
				}
				else {
					test.log(Status.SKIP,message);
				}
			}
			
		}catch(Exception e) {
			
		}
	}
	
	private static long getScreenShot(WebDriver driver) {
		long number=(long)Math.floor(Math.random()*900000000L)+10000000L;
	try {
		 WebDriverWait wait=new WebDriverWait(getDriver(),30);
		 wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=\"complete\";"));
	FileUtils.copyFile((((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE)),new File(Extentreportpath+number+".jpg"));
	}catch(Exception e) {
		
	}
		return number;	
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
		extent.flush();
	}
}

package Utilities;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;



public class CommonUtil extends BaseTest {
	public static Actions actions;
  public static Select select;
	public static boolean Click(WebElement element) throws IOException {
		boolean status=false;
		try {
			element.click();
			Log.info("Clicked on element");
			status=true;
		} catch (Exception e) {
			Log.error("Error Messsgae : "+e.getMessage());
		//	BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
			status=false;
		}
		return status;
	}

	public static boolean sendKeys(WebElement element, String value) throws IOException {
		boolean status=false;
		try {
			element.clear();
			element.sendKeys(value);
			Log.info("Passed value "+value);
			status=true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
			status=false;
		}
		return status;
	}

	public static boolean hover(WebElement element) {
		boolean status=false;
		try {
			actions = new Actions(getDriver());
			actions.moveToElement(element).perform();
			Log.info("Passed value ");
			status=true;
		} catch (Exception e) {
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
			status=false;
		}
		return status;
	}

	public void jsClick(WebElement element) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)getDriver();
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}

	public void selectByVisibleText(WebElement element,String value) {
		try {
			select = new Select(element);
			select.selectByVisibleText(value);
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	

	public void selectByValue(WebElement element,String value) {
		try {
			select = new Select(element);
			select.selectByValue(value);
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		
	}
	
	public void getSelectedOptions(WebElement element,String value) {
		try {
			select = new Select(element);
			List<WebElement> selectedOptions = select.getOptions();
			for(WebElement selectedOption: selectedOptions)
                System.out.println(selectedOption.getText());
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		
	}
	
	public void selectByIndex(WebElement element,int value) {
		try {
			select = new Select(element);
			select.selectByIndex(3);
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	
	public void verifyRadioButtonIsSelected(WebElement element) {
		try {
			boolean select = element.isSelected();
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	
	public boolean verifyRadioButtonIsDisplayed(WebElement element) {
		boolean sel =false;
		try {
			 sel = element.isDisplayed();
			 BaseTest.test.log(Status.PASS, "Verified  the element ");
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		return sel;
	}

	public void alerthandle(String actionValue) throws IOException {
		try {
			switch (actionValue.toLowerCase()) {
			case "accept":
				driver.switchTo().alert().accept();
				Log.info("clicked on alert accepted");
				
				break;
			case "dismiss":
				driver.switchTo().alert().dismiss();
				Log.info("clicked on alert dismiss");
				
				break;
			default:
				driver.switchTo().alert().dismiss();
				Log.info("By default clicked on  alert accept");
				
				break;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	
	public static  boolean verifyElement(WebElement element) throws IOException {
		boolean value=false;
		try {
			value=element.isDisplayed();
			Log.info("Element is displayed");	
		}catch(Exception e) {
			System.out.println("Message : "+e.getMessage());
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		return value;
	}
	
	public void refreshPage() {
		try {
			getDriver().navigate().refresh();
		} catch (Exception e) {
			
			Log.error("Error Messsgae : "+e.getMessage());
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	
	public static void checkPageIsReady() {
		try {
			for(int i=0;i<15;i++) {
				pause(1);
				if(((JavascriptExecutor)getDriver()).executeScript("return document.readyState").equals("complete"))break;
			}
		}catch(Exception e) {
			
		}
	}
	
	public static boolean switchToFrame(WebElement element) {
		boolean status = false;
		try {
            WebDriverWait wait=new WebDriverWait(getDriver(),20);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            status=true;
		} catch (Exception e) {
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		
		return status;
	}
	
	public void scrollTo(WebElement element) {
		try {
			if(element.isDisplayed()!=true) {
				//JavascriptExecutor executor = (JavascriptExecutor)getDriver();
				((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollToView();", element);
				((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,-300)");
			}
		}
		catch (Exception e) {
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
	}
	
	public static boolean wiatforElementToBeClickable(WebElement element,int timeout) {
		boolean status = false;
		try {
            WebDriverWait wait=new WebDriverWait(getDriver(),timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            status=true;
		} catch (Exception e) {
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		
		return status;
	}
	public static boolean wiatforElementToBeVisible(WebElement element,int timeout) {
		boolean status = false;
		try {
            WebDriverWait wait=new WebDriverWait(getDriver(),timeout);
            wait.until(ExpectedConditions.invisibilityOf(element));
            status=true;
		} catch (Exception e) {
			BaseTest.test.log(Status.FAIL, "Error Message : "+e.getMessage());
		}
		
		return status;
	}
	
	public static void softAssertion(boolean status,String message) {
		if(status) {
			BaseTest.reportStep(message, "Pass", 0);
		}else {
			BaseTest.reportStep(message, "Fail", 1);
		}
	}
	
	public static void hardAssertion(boolean status,String message) {
		if(status) {
			Assert.assertTrue(status);
			BaseTest.reportStep(message, "Pass", 0);
		}else {
			BaseTest.reportStep(message, "Fail", 1);
			Assert.assertTrue(status);
		}
	}
	
	public static void AssertEquals(String string1,String string2,String message) {
		if(string1.equalsIgnoreCase(string2)) {
			BaseTest.reportStep(message, "Pass", 0);
		}else {
			BaseTest.reportStep(message, "Fail", 1);
			Assert.assertEquals(string1, string2);
		}
	}
	
	public static void pause(int timeunitSeconds) {
		try {
			Thread.sleep(1000*timeunitSeconds);
		}catch(Exception e) {
			
		}
		
	}
}

package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseTest;
import Utilities.CommonUtil;

public class SignInPage {
	@FindBy(xpath = "//h1[contains(text(),'Sign-In')]")
	WebElement signTitle;
	@FindBy(xpath = "//input[@id='continu']")
	WebElement continueBtn;
	
	

	public SignInPage(WebDriver driver) {
		BaseTest.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifySignInPageTitle() throws IOException {
		CommonUtil.hardAssertion(CommonUtil.verifyElement(signTitle), "Verify Sign-In title is displayed");
	}
	
	public void verifyContinueBtn() throws IOException {
		CommonUtil.hardAssertion(CommonUtil.verifyElement(continueBtn), "Verify continue button is displayed");
	}
}

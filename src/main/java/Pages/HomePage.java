package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import Utilities.BaseTest;
import Utilities.CommonUtil;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//div[@id='a-page']")
	WebElement HomepageTitile;
	@FindBy(xpath = "//div[@class='nav-search-field ']/input")
	WebElement searchBar;
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement searchBtn;
	@FindBy(xpath = "(//a[@data-nav-role='signin'])[1]")
	WebElement signhover;
	@FindBy(xpath = "(//span[@class='nav-action-inner'][text()='Sign in'])[1]")
	WebElement signBtn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchproduct(String value) throws IOException {
		CommonUtil.hardAssertion(CommonUtil.sendKeys(searchBar,value), "Passed the product value as "+value);
		CommonUtil.hardAssertion(CommonUtil.Click(searchBtn), "Clicked on search button");
	}

	public void verifyHomePageTitle() throws IOException {
		CommonUtil.hardAssertion(CommonUtil.verifyElement(HomepageTitile), "Home page title is verified");
	}

	public void selectSign() throws IOException {
		CommonUtil.hardAssertion(CommonUtil.hover(signhover), "Hover on sign text");
		CommonUtil.hardAssertion(CommonUtil.Click(signBtn), "Clicked on signin button");
	}

}

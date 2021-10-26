package Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseTest;
import Utilities.CommonUtil;

public class ProductDetailPage extends BaseTest {
	@FindBy(xpath = "//*[text()='Filter by category']")
	WebElement FilterByCategory;
	@FindBy(xpath = "(//div[@class='a-section a-spacing-none'])[3]/div/h2")
	WebElement firstproduct;

	public ProductDetailPage(WebDriver driver) {
		BaseTest.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyProductTitle() throws IOException {
		CommonUtil.hardAssertion(CommonUtil.verifyElement(FilterByCategory), "Verify Filter By Category displayed");
	}
}


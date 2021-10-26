package Tests;

import java.io.IOException;

import org.apache.hc.core5.http.Method;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.ProductDetailPage;
import Pages.SignInPage;
import Utilities.BaseTest;
import Utilities.TestProperties;

public class LoginTest extends BaseTest {

	public HomePage homePage;
	public ProductDetailPage productDetailPage;
    public SignInPage signInPage;
	@BeforeMethod(alwaysRun = true)
	public void HomePageTestLoad() {
		homePage = new HomePage(BaseTest.getDriver());
		productDetailPage = new ProductDetailPage(BaseTest.getDriver());
		signInPage=new SignInPage(BaseTest.getDriver());
	}

	@Test
	public void Searchproduct() throws IOException {
		String productName = TestProperties.getProperty("AppleProducts");
		test = extent.createTest("Search product ", "This Testcase is used to verify the " + productName + "product");
		homePage.verifyHomePageTitle();
		homePage.searchproduct(productName);
		productDetailPage.verifyProductTitle();
	}

	@Test
	public void verifyProductPage() throws IOException {
		String productName = TestProperties.getProperty("Mac");
		test = extent.createTest("Verify Product Page",
				"This Testcase is used to verify the " + productName + " product");
		homePage.verifyHomePageTitle();
		homePage.searchproduct(productName);

	}

	@Test
	public void SignInAtHomePage() throws IOException, InterruptedException {
		test = extent.createTest("Sign", "This Testcase is used to signin");
		homePage.verifyHomePageTitle();
		homePage.selectSign();
		signInPage.verifySignInPageTitle();
	}
	
	@Test
	public void verifyContinueBtnIsPresent() throws IOException, InterruptedException {
		test = extent.createTest("Verify Continue Button", "This Testcase is used to verify contniue button");
		homePage.verifyHomePageTitle();
		homePage.selectSign();
		signInPage.verifySignInPageTitle();
		signInPage.verifyContinueBtn();
	}
}

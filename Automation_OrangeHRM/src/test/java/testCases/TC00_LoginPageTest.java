package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC00_LoginPageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	
//	@Test(priority = 0)
//	public void loginWithInvalidCredentials() {
//		try {
//			logger.info("**** Starting loginWithInvalidCredentials ***");
//			loginPage = new LoginPage(driver);
//			doLogin(loginPage, "xyz", "abc");
//			String actualMessage = loginPage.getInvalidAlertMessage();
//			String expectedMessage = "Invalid credentials";
//			logger.info("Validating");
//			Assert.assertEquals(actualMessage, expectedMessage);
//		} catch (Exception e) {
//			logger.error("Test failed.");
//			logger.debug("Debug logs.");
//			Assert.fail();
//		}
//	}
	
	@Test(priority = 0, dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
	public void loginWithValidCredentials(String username, String password) {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			logger.info("**** Starting loginWithValidCredentials ***");
			doLogin(loginPage, username, password);
			logger.info("Validating");
			
			Assert.assertTrue(homePage.isUserDropDownPresent());
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail();
		}
	}
	
	@Test(priority = 1)
	public void clickAdminButton() {
		try{
			loginPage.clickAdmin();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void logout() {
		logger.info("Logging out.");
		homePage.clickUserDropdownButton();
		loginPage.clickLogout();
	}
	
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC02_LoginDDT extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	
	@Test(groups = {"master", "datadriven"}, dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
	public void login(String username, String password, String result) {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			logger.info("**** Starting TC03_LoginDDT ***");
			doLogin(loginPage, username, password);
			logger.info("Validating");
			boolean loginStatus = homePage.isUserDropDownPresent();
			if(loginStatus) {
				logger.info("Logged in.");
				logout();
				Assert.assertTrue(loginStatus);
			}
			else
				Assert.fail("Invalid credentials.");
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail(e.getMessage());
		}
	}
	
	public void logout() {
		logger.info("Logging out.");
		homePage.clickUserDropdownButton();
		loginPage.clickLogout();
	}
	
}

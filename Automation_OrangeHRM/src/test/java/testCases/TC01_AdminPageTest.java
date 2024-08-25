package testCases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC01_AdminPageTest extends BaseClass{

	LoginPage loginPage;
	HomePage homePage;
	
	@Test(groups = {"master", "regression"}, priority = 0)
	public void loginWithValidCredentials() {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			logger.info("**** Starting loginWithValidCredentials ***");
			String username = property.readProperty("username");
			String password = property.readProperty("password");
			System.out.println(username + "," + password);
			doLogin(loginPage, username, password);
			logger.info("Validating");
			
			Assert.assertTrue(homePage.isUserDropDownPresent());
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail();
		}
	}
	
	@Test(groups = {"master", "regression"}, priority = 1)
	public void clickAdminButton() {
		try{
			loginPage.clickAdmin();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(groups = {"master", "regression"}, priority = 2, dataProvider = "searchTerms", dataProviderClass = DataProviders.class)
	public void testSearchSystemUsers(String searchTerm) {
		AdminPage adminPage = new AdminPage(driver);
		adminPage.setUsername(searchTerm);
		adminPage.clickSearch();
		String results = adminPage.getRecordsFound();
		if(results.equals("(Exception) No records found."))
			Assert.fail(results);
		else {
			String regex = "\\d+";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(results);
			Assert.assertTrue(matcher.find(), "No records found.");
		}
	}
	
	@Test(groups = {"master", "regression"}, priority = 3)
	public void logout() {
		logger.info("Logging out.");
		homePage.clickUserDropdownButton();
		loginPage.clickLogout();
	}
	
}

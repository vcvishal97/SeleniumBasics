package testCases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC01_AdminPageTest extends BaseClass{

	@Test(dataProvider = "searchTerms", dataProviderClass = DataProviders.class)
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
	
}

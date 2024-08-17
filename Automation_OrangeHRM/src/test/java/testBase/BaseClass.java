package testBase;

import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.LoginPage;
import utilities.PropertyReader;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	PropertyReader property;
	
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browser) throws IOException {
		property = new PropertyReader();
		logger = LogManager.getLogger(this.getClass());
		
		switch (browser.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default: logger.error("Invalid brower."); return;
		}
		driver.manage().window().maximize();
		driver.get(property.readProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public void doLogin(LoginPage loginPage, String username, String password) {
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}
	
}

package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;
import utilities.PropertyReader;

public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
	public PropertyReader property;
	
	@BeforeClass(groups = {"sanity", "regression", "master", "datadriven"})
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
	
	@AfterClass(groups = {"sanity", "regression", "master", "datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public void doLogin(LoginPage loginPage, String username, String password) {
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		loginPage.clickLogin();
	}
	
	public String captureScreen(String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String targetFilePath = ".\\screenshots\\" + testName + "_" + timeStamp + ".png";
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
}

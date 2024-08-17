package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;

public class AdminPage extends BasePage {
	
	public AdminPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[contains(@class,'topbar-header')]/h6[1]")
	WebElement headerFromPage;
	
	@FindBy(xpath = "//label[text()='Username']/parent::div/parent::div//input")
	WebElement username;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement search;
	
	@FindBy(xpath = "//span[contains(normalize-space(),'Record Found')]")
	WebElement recordsFound;
	
	@FindBy(xpath = "//span[contains(normalize-space(),'Records Found')]")
	WebElement noRecordsFound;
	
	public String getRecordsFound() {
		try {
			return recordsFound.getText();
		} catch (Exception e) {
			return "(Exception) No records found.";
		}
		
	}
	
	public void clickSearch() {
		this.search.click();
	}
	
	public void clearUsername() {
		Actions actions = new Actions(BaseClass.driver);
		this.username.click();
		actions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
		actions.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE);
	}
	
	public void setUsername(String username) {
		clearUsername();
		this.username.sendKeys(username);
	}
	
	public String getHeaderFromPage() {
		return headerFromPage.getText();
	}
	
}

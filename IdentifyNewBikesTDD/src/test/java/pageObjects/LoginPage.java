package pageObjects;

import java.time.Duration;
import java.util.ArrayList;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{

	
//	WebDriver driver;
	WebDriverWait wait;
	String parentWindowhandleString;
	
	// calling the parent class constructor
	public LoginPage(WebDriver driver, WebDriverWait wait){
		super(driver,wait);
		this.wait = wait;
	}
	
	
	// Locating the webElements using @FindBy annotation
	
	@FindBy(xpath = "//*[@id='forum_login_title_lg']") WebElement loginBtnElement;
	
	@FindBy(xpath = "//*[@id='myModal3-modal-content']/div[1]/div/div[3]/h4/span[1]") WebElement loginMessagElement;
	
	@FindBy(xpath = "//div[@data-track-label='Popup_Login/Register_with_Google']") WebElement googleLoginBtnElement;
	
	@FindBy(id="identifierId")  WebElement emailElement;

	@FindBy(xpath="//*[text()='Next']") WebElement nextElement;
	
	@FindBy(id = "report_submit_close_login") WebElement loginModelElement;
	
	
	public void clickLoginBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(loginBtnElement));
		loginBtnElement.click();
	}
	
	public String getLoginMessage() {
		return loginMessagElement.getText();
	}
	
	public void clickGoogleBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(googleLoginBtnElement));
		googleLoginBtnElement.click();
		parentWindowhandleString = driver.getWindowHandle();
		driver.switchTo().window((new ArrayList<>(driver.getWindowHandles())).get(1));
	}
	
	public String setMailAndNext(String gmail) throws InterruptedException  {
		emailElement.sendKeys(gmail);
	    nextElement.click();
	    Thread.sleep(2000); 
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("Sign in - Google Account")));
	   // temporary pause to inspect behavior
	   String titleMessageString = driver.getTitle();
	   
		return titleMessageString;
	}
	
	public void closeGooleLogin() {
		 driver.close();
	}
	
	public void closeLoginModel() {
		
		driver.switchTo().window(parentWindowhandleString);
		loginModelElement.click();
	}
	
	
}
 
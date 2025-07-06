package testCases;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.LaunchBrowser;

public class TC001_LoginTest {
   
	WebDriver driver;
	WebDriverWait wait;
	LoginPage lPage;
	String baseUrl= "https://zigwheels.com";
	
	
	
	@BeforeClass
	public void getDriver() {
		driver = LaunchBrowser.getBrowser("chrome", baseUrl);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		lPage = new LoginPage(driver, wait);
	}
	
	@Test(priority = 1)
	public void testPageTitle() {
		String pageTitleString = driver.getTitle();
		String expectedTittleString = "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com";
		Assert.assertEquals(pageTitleString, expectedTittleString, "Page title mismatch");
	}
	
	
	@Test(priority = 2)
	public void clcikOnLoginBtn() {
		lPage.clickLoginBtn();
	}
	
	@Test(priority = 3)
	public void testLoginPopUp() {
	    String message = 	lPage.getLoginMessage();
	    Assert.assertEquals(message, "Login/Register to ZigWheels");
	}
	
	@Test(priority = 4, dataProvider = "gmailData")
	public void testGoogleLogin(String gmail) throws InterruptedException {
		lPage.clickGoogleBtn();
		String errorMessage =lPage.setMailAndNext(gmail);
		System.out.println(errorMessage);
	}
	
	@Test(priority = 5)
	public void closegoogleLogin() {
		lPage.closeGooleLogin();
	}
	
	@Test(priority = 6)
	public void closeLoginModel() {
      lPage.closeLoginModel();
//      driver.quit();
	}
	
	@DataProvider(name = "gmailData")
	public Object[][] setGmailData() {
	    return new Object[][] {
	        { "demo1@gmail.com" },
	    };
	}

}

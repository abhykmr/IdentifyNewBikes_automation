package testBase;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.BikePage;
import pageObjects.UsedCarsPage;
import pageObjects.LoginPage;
import utilities.LaunchBrowser;
import utilities.ScreenshotUtil;

public class Basetest {

    private static final Logger log = LogManager.getLogger(Basetest.class);

    WebDriver driver;
    WebDriverWait wait;
    BikePage bikePage;
    UsedCarsPage usedCarsPage;
    LoginPage loginPage;
    String baseUrl = "https://zigwheels.com";

    @BeforeClass
    public void setup() {
        log.info("Launching browser and initializing page objects...");
        driver = LaunchBrowser.getBrowser("chrome", baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        bikePage = new BikePage(driver, wait);
        usedCarsPage = new UsedCarsPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        log.info("Setup complete. Base URL: {}", baseUrl);
        ScreenshotUtil.captureScreenshot(driver, "setup");
    }

    @Test(priority = 1)
    public void testNavigateToNewBikes() {
        log.info("Navigating to New Bikes section...");
        bikePage.clickOnNewBikes();
        ScreenshotUtil.captureScreenshot(driver, "testNavigateToNewBikes");
        Assert.assertTrue(driver.getCurrentUrl().contains("newbikes"));
    }

    @Test(priority = 2)
    public void testViewAllUpcomingBikes() {
        log.info("Clicking on All Upcoming Bikes...");
        bikePage.clickOnAllUpcoming();
        ScreenshotUtil.captureScreenshot(driver, "testViewAllUpcomingBikes");
        Assert.assertTrue(driver.getCurrentUrl().contains("upcoming-bikes"));
    }

    @Test(priority = 3)
    public void testClickBrandHonda() {
        log.info("Selecting Honda brand...");
        bikePage.clickOnBrandHonda();
        ScreenshotUtil.captureScreenshot(driver, "testClickBrandHonda");
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("honda"));
    }

    @Test(priority = 4)
    public void testFetchBikeDetails() {
        log.info("Fetching top Honda bike details...");
        List<String[]> bikeDetails = bikePage.getTopBikeDetails();
        Assert.assertTrue(bikeDetails.size() > 0);
        for (String[] bike : bikeDetails) {
            log.info("Bike: Name={}, Price={}, Launch Date={}", bike[0], bike[1], bike[2]);
            Assert.assertNotNull(bike[0]);
            Assert.assertNotNull(bike[1]);
            Assert.assertNotNull(bike[2]);
        }
        ScreenshotUtil.captureScreenshot(driver, "testFetchBikeDetails");
        driver.get(baseUrl);
    }

    @Test(priority = 5)
    public void testMenuButtonClick() throws IOException {
        log.info("Opening Used Cars section...");
        usedCarsPage.openUsedCarsSection();
        ScreenshotUtil.captureScreenshot(driver, "testMenuButtonClick");
    }

    @Test(priority = 6)
    public void testSetChennaiLocation() throws IOException {
        log.info("Setting location to Chennai...");
        usedCarsPage.selectChennaiLocation();
        ScreenshotUtil.captureScreenshot(driver, "testSetChennaiLocation");
    }

    @Test(priority = 7)
    public void testValidateLocation() throws IOException {
        String location = usedCarsPage.getUsedCarLocation();
        Assert.assertEquals(location, "Chennai");
        ScreenshotUtil.captureScreenshot(driver, "testValidateLocation");
    }

    @Test(priority = 8)
    public void getModelNameList() throws IOException {
        log.info("Fetching popular used car models...");
        List<String> modelNameList = usedCarsPage.getPopularCarModels();
        for (String model : modelNameList) {
            log.debug("Popular Model: {}", model);
        }
        ScreenshotUtil.captureScreenshot(driver, "getModelNameList");
        driver.get(baseUrl);
    }

    @Test(priority = 9)
    public void testPageTitle() {
        log.info("Validating homepage title...");
        String actualTitle = driver.getTitle();
        String expectedTitle = "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com";
        Assert.assertEquals(actualTitle, expectedTitle);
        ScreenshotUtil.captureScreenshot(driver, "testPageTitle");
    }

    @Test(priority = 10)
    public void clickOnLoginBtn() {
        log.info("Clicking login button...");
        loginPage.clickLoginBtn();
        ScreenshotUtil.captureScreenshot(driver, "clickOnLoginBtn");
    }

    @Test(priority = 11)
    public void testLoginPopUp() {
        String message = loginPage.getLoginMessage();
        Assert.assertEquals(message, "Login/Register to ZigWheels");
        ScreenshotUtil.captureScreenshot(driver, "testLoginPopUp");
    }

    @Test(priority = 12, dataProvider = "gmailData")
    public void testGoogleLogin(String gmail) throws InterruptedException {
        log.info("Attempting login with Gmail: {}", gmail);
        loginPage.clickGoogleBtn();
        String errorMsg = loginPage.setMailAndNext(gmail);
        log.warn("Login Error Message: {}", errorMsg);
        ScreenshotUtil.captureScreenshot(driver, "testGoogleLogin");
    }

    @Test(priority = 13)
    public void closeGoogleLogin() {
        log.info("Closing Google login window...");
        loginPage.closeGooleLogin();
        ScreenshotUtil.captureScreenshot(driver, "closeGoogleLogin");
    }

    @Test(priority = 14)
    public void closeLoginModal() {
        log.info("Closing login modal and quitting browser...");
        loginPage.closeLoginModel();
        ScreenshotUtil.captureScreenshot(driver, "closeLoginModal");
        driver.quit();
    }

    @DataProvider(name = "gmailData")
    public Object[][] setGmailData() {
        return new Object[][] {
            { "demo1@gmail.com" }
        };
    }
}

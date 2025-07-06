package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.BikePage;
//import utilities.LaunchBrowser;
import utilities.LaunchBrowser;

public class TC002__IdentifyUpcomingBikes {

    WebDriver driver;
    WebDriverWait wait;
    BikePage bikePage;
    String baseUrl= "https://zigwheels.com";

    @BeforeClass
    public void setup() {
        driver = LaunchBrowser.getBrowser("chrome", baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        bikePage = new BikePage(driver, wait);
    }

    @Test(priority = 1)
    public void testNavigateToNewBikes() {
        bikePage.clickOnNewBikes();
        Assert.assertTrue(driver.getCurrentUrl().contains("newbikes"), "Failed to navigate to New Bikes section");
    }

    @Test(priority = 2)
    public void testViewAllUpcomingBikes() {
        bikePage.clickOnAllUpcoming();
        Assert.assertTrue(driver.getCurrentUrl().contains("upcoming-bikes"), "All Upcoming Bikes page not opened");
    }
    


    @Test(priority = 3)
    public void testClickBrandHonda() {
        bikePage.clickOnBrandHonda();
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("honda"), "Honda brand page not opened");
    }

    @Test(priority = 4)
    public void testFetchBikeDetails() {
        List<String[]> bikeDetails = bikePage.getTopBikeDetails();
        Assert.assertTrue(bikeDetails.size() > 0, "Bike details not found");

        System.out.println("\nFetched Bike Details:");
        for (String[] bike : bikeDetails) {
            System.out.println("Name: " + bike[0] + " | Price: " + bike[1] + " | Launch Date: " + bike[2]);
            Assert.assertNotNull(bike[0], "Bike name is missing");
            Assert.assertNotNull(bike[1], "Bike price is missing");
            Assert.assertNotNull(bike[2], "Bike launch date is missing");
        }
    }
}

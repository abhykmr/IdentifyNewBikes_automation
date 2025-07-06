package testCases;import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.UsedCarsPage;
import utilities.LaunchBrowser;

public class TC003_UsedCars{

    WebDriver driver;
    WebDriverWait wait;
    UsedCarsPage usedCarsPage;
    String baseUrl= "https://zigwheels.com";

    @BeforeClass
    public void setUp() {
        driver = LaunchBrowser.getBrowser("chrome", baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        usedCarsPage = new UsedCarsPage(driver, wait);
    }
    

    @Test(priority=1)
    public void testMenuButtonClick() throws IOException {
        usedCarsPage.openUsedCarsSection();
        
    }
    
  //  @Test(priority = 2)
//    public void testUsedCarClicked() {
//    	String textString = usedCarsPage.getUsedCarText();
//    	String actualString = "Used Cars";
//    	Assert.assertEquals(textString, actualString, "Used Cars mistach!");
//    }

    @Test(priority=3)
    public void testSetChennaiLocation() throws IOException {
        usedCarsPage.selectChennaiLocation();
    }
    
    @Test(priority=4)
    public void testValidateLocation() throws IOException {
        String location= usedCarsPage.getUsedCarLocation();
        String actualString = "Chennai";
    	Assert.assertEquals(location, actualString, "Used Cars Location mistach!");
    }
    
    @Test(priority=5)
    public void getModelNameList() throws IOException {
      List<String> modelNameList =   usedCarsPage.getPopularCarModels();
    }
    
    
    

}

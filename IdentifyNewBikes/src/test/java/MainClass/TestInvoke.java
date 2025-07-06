package MainClass;



import org.openqa.selenium.WebDriver;
import TestClass.LoginTest;
import TestClass.UpcomingBikes;
import Utils.LaunchBrowser;

public class TestInvoke {
    public static WebDriver driver;
    public static String baseUrl = "https://zigwheels.com/";

    public static void main(String[] args) {
        try {
            // Launch browser and navigate to base URL
            driver = LaunchBrowser.getBrowser("chrome", baseUrl);
            

            
            
             
            // Bikes
            UpcomingBikes upBikes = new UpcomingBikes(driver);
            upBikes.clickOnNewBikes();
            upBikes.clickOnUpcoming();
            upBikes.clickOnAllUpcoming();
            upBikes.clickOnBrand();
            upBikes.getBikeDetails();

//            // Create an instance of LoginTest and perform login actions
//            LoginTest loginTest = new LoginTest(driver);
//            loginTest.clickLoginBtn();
//            
//            // click on the Google Icon Button
//            loginTest.clickGoogleBtn();
//            
//            loginTest.setEmailField();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//			driver.quit();
		}
    }
}

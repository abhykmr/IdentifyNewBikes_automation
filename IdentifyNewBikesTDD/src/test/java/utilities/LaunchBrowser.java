package utilities;

import org.openqa.selenium.WebDriver;

public class LaunchBrowser {

    public static  WebDriver getBrowser(String browser, String baseUrl) {
        WebDriver driver = null;
        try {
            driver = DriverSetup.getDriver(browser);
            driver.manage().window().maximize();
            driver.get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }
}

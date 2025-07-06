package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

    public static WebDriver getDriver(String browser) throws WebDriverException {
        browser = browser.toLowerCase().trim();
        WebDriver driver;
     
        switch (browser) {
            case "chrome":
             
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-geolocation");// disables the prompt
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                break;
            case "edge":
                driver = new EdgeDriver();
                break; 
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new WebDriverException("Undefined browser name: " + browser);
        }

        return driver;
    }
}

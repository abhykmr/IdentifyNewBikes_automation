package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String methodName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File dest = new File("screenshots/" + methodName + "_" + timestamp + ".png");
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println("Screenshot capture failed for " + methodName + ": " + e.getMessage());
        }
    }
}

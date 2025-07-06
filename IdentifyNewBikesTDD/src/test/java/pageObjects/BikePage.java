package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BikePage extends BasePage {

    private JavascriptExecutor js;

    WebDriverWait wait;
    public BikePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.js = (JavascriptExecutor) driver;
        this.wait = wait;
	}

    @FindBy(xpath = "//*[@id='headerNewVNavWrap']/nav/ul/li[3]/a")
    private WebElement newBikesTab;

    @FindBy(xpath = "//*[@id=\"main-tabs\"]/li[4]")
    private WebElement upcomingTab;

    @FindBy(xpath = "//a[normalize-space()='Upcoming Bikes']")
    private WebElement allUpcomingBikesLink;

    @FindBy(xpath = "//a[normalize-space()='Honda']")
    private WebElement hondaBrandLink;

    @FindBy(xpath = "//ul[@id='modelList']//li")
    private List<WebElement> bikeItems;

    public void clickOnNewBikes() {
        newBikesTab.click();
    }

    public void clickOnUpcoming() {
        scrollToAndClick(upcomingTab);
    }

    public void clickOnAllUpcoming() {
        scrollToAndClick(allUpcomingBikesLink);
    }

    public void clickOnBrandHonda() {
        scrollToAndClick(hondaBrandLink);
    }

    public List<String[]> getTopBikeDetails() {
        List<String[]> bikeDetails = new ArrayList<>();

        for (int i = 0; i < 3 && i < bikeItems.size(); i++) {
            WebElement item = bikeItems.get(i);
            WebElement nameElement = item.findElement(By.xpath(".//div[3]//a//strong"));
            WebElement priceElement = item.findElement(By.xpath(".//div[3]//div[1]"));
            WebElement launchDateElement = item.findElement(By.xpath(".//div[3]//div[2]"));

            scrollToElement(nameElement);
            scrollToElement(priceElement);
            scrollToElement(launchDateElement);

            bikeDetails.add(new String[] {
                nameElement.getText(),
                priceElement.getText(),
                launchDateElement.getText()
            });
        }

        return bikeDetails;
    }


    private void scrollToAndClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
        js.executeScript("arguments[0].click();", element);
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
    }
}



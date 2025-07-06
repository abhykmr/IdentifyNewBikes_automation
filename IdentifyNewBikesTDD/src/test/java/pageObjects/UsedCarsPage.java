package pageObjects;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsedCarsPage extends BasePage {

	private WebDriverWait wait;
	private JavascriptExecutor jsExecutor;

	public UsedCarsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.wait = wait;
		this.jsExecutor = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//span[@class='c-p icon-down-arrow']")
	private WebElement menuButton;

	@FindBy(xpath = "//a[text()='Used Cars']")
	private WebElement usedCarsLink;

	@FindBy(xpath = "//a[@data-city_id='280']")
	private WebElement chennaiLink;

	@FindBy(id = "usedCarCity")
	private WebElement selectLocation;

	@FindBy(xpath = "//*[contains(@class,'usedCarMakeModelList popularModel')]//li//label")
	private List<WebElement> popularCarModels;

	@FindBy(xpath = "//*[@id='data-set-body']//div")
	private List<WebElement> carsListElement;

	public void openUsedCarsSection() {
		menuButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(usedCarsLink));
		usedCarsLink.click();
	}

	public String getUsedCarText() {
		return usedCarsLink.getText();
	}

	public void selectChennaiLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(chennaiLink));
		chennaiLink.click();
	}

	public String getUsedCarLocation() {
		return selectLocation.getAttribute("value");
	}

	public List<String> getPopularCarModels() {
		List<String> modelNames = new ArrayList<>();
		String baseXpathString = "//*[@id='data-set-body']/div";

		for (int i = 1; i <= carsListElement.size(); i += 2) {

			if (i == 13) {
				continue;
			} else if (i == 27) {
				break;
			} else {

				String fullXPath = baseXpathString + "[" + i + "]/div[1]/div[3]/div[2]/a";

				WebElement modelelement = driver.findElement(By.xpath(fullXPath));

				jsExecutor.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
						modelelement);

				modelNames.add(modelelement.getText());
			}
		}

		System.out.println("\nPopular Used Car Models in Chennai:");
		modelNames.forEach(System.out::println);
		return modelNames;
	}
}

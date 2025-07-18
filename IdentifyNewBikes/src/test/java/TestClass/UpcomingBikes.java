package TestClass;

import java.util.List;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;


public class UpcomingBikes {

	 WebDriver driver;
	 JavascriptExecutor js; 
	 public UpcomingBikes(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}
	 
	 public void clickOnNewBikes() {
		 // locate and click on the new Bike 
		 driver.findElement(By.xpath("//*[@id='headerNewVNavWrap']/nav/ul/li[3]/a")).click();
	 }
	 
	 public void clickOnUpcoming() {
		 // locate and click on the new Bike 
		WebElement upcomingElement = driver.findElement(By.xpath("//*[@id=\"main-tabs\"]/li[4]"));
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", upcomingElement);
		js.executeScript("arguments[0].click();", upcomingElement);
	 } 
	 public void clickOnAllUpcoming() { 
		 // locate and click on the new Bike 
		 WebElement allUpcomingElement = driver.findElement(By.xpath("//a[normalize-space()='Upcoming Bikes']"));
		 js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", allUpcomingElement);
		 js.executeScript("arguments[0].click();", allUpcomingElement);
	 }
	 public void clickOnBrand() {
		 WebElement brandElement = driver.findElement(By.xpath("//a[normalize-space()='Honda']"));
		 js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", brandElement);
		 js.executeScript("arguments[0].click();", brandElement);
	 }
	 
	 public void getBikeDetails() {
		    List<WebElement> bikesList = driver.findElements(By.xpath("//ul[@id='modelList']//li"));
		    String[][] bikesDetailStrings = new String[3][3];

		    for (int i = 0; i < 3 && i < bikesList.size(); i++) {
		        String baseXPath = "//ul[@id='modelList']//li[" + (i + 1) + "]";
		        
		        WebElement nameElement = driver.findElement(By.xpath(baseXPath + "//div[3]//a//strong"));
		        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", nameElement);

		        WebElement priceElement = driver.findElement(By.xpath(baseXPath + "//div[3]//div[1]"));
		        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", priceElement);

		        WebElement lauchDate = driver.findElement(By.xpath(baseXPath + "//div[3]//div[2]"));
		        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", lauchDate);

		        
		        bikesDetailStrings[i][0] = nameElement.getText();
		        bikesDetailStrings[i][1] = priceElement.getText();
		        bikesDetailStrings[i][2] = lauchDate.getText();
		    }

		    System.out.println("\nTop 3 Bike Details:");
		    for (String[] bike : bikesDetailStrings) {
		        System.out.println("Name: " + bike[0] + " | Price: " + bike[1] + " | Lauch Date: " + bike[2]);
		    }
		}
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Example locators (adjust based on actual fields in the form)
    private By header = By.tagName("h1");
    private By startButton = By.cssSelector("button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public void clickStart() {
        driver.findElement(startButton).click();
    }
}

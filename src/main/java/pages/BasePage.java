package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestDataManager;

import java.time.Duration;

// BasePage - Contains common methods used across all page objects
// This helps avoid code duplication and makes maintenance easier
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Constructor for BasePage
    // @param driver WebDriver instance
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestDataManager.getTimeout()));
    }
    
    // Click an element after waiting for it to be ready
    // @param element WebElement to click
    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    
    // Get text from an element (waits for it to show up first)
    // @param element WebElement to get text from
    // @return Text content of the element
    protected String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
    
    // Type text into an input field
    // @param element WebElement to send keys to
    // @param text Text to send
    protected void sendKeysToElement(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }
    
    // Check if an element is visible on the page
    // @param element WebElement to wait for
    // @return True if element is visible, false otherwise
    protected boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Get the page title
    // @return Page title
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    // Get the current URL
    // @return Current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    // Go to a specific URL
    // @param url URL to navigate to
    public void navigateTo(String url) {
        driver.get(url);
    }
}

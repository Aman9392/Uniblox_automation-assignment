package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import org.openqa.selenium.JavascriptExecutor;
import utils.TestDataManager;
import utils.WebDriverManagerUtil;

import java.time.Duration;

// BaseTest - Common setup and teardown for all test classes
// Ensures all tests start with a clean browser and proper configuration
public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Setup before the suite: initialize WebDriver and navigate to the app
    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(@Optional String browser) {
        // Use parameter if provided, otherwise use default from config
        String browserName = (browser != null && !browser.isEmpty()) ? browser : TestDataManager.getBrowser();
        
        // Initialize WebDriver
        driver = WebDriverManagerUtil.createDriver(browserName);
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestDataManager.getImplicitWait()));
        // Set page load timeout to prevent long hangs
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        } catch (Exception ignored) {}
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestDataManager.getTimeout()));
        
        // Navigate to the application
        driver.get(TestDataManager.getAppUrl());

        // Wait for document ready state
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(Math.max(10, TestDataManager.getTimeout())))
                .until(d -> {
                    try {
                        String readyState = (String) ((JavascriptExecutor) d).executeScript("return document.readyState");
                        return "complete".equals(readyState) || "interactive".equals(readyState);
                    } catch (Exception e) {
                        return false;
                    }
                });
        } catch (Exception ignored) {
            // proceed; element waits in pages will handle remaining readiness
        }

        // Basic diagnostics: log current URL and title
        try {
            System.out.println("Navigated to URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
        } catch (Exception ignored) {}
    }
    
    // Teardown after the suite: close the WebDriver instance
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Get the current WebDriver instance
    protected WebDriver getDriver() {
        return driver;
    }
    
    // Get the WebDriverWait instance
    protected WebDriverWait getWait() {
        return wait;
    }
}

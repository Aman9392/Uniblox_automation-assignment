package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.TestDataManager;
import utils.WebDriverManagerUtil;

import java.time.Duration;

/**
 * BaseTest - Common setup and teardown for all test classes
 * This ensures all tests start with a clean browser and proper configuration
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Setup method that runs before each test method
     * Initializes WebDriver and navigates to the application URL
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        // Use parameter if provided, otherwise use default from config
        String browserName = (browser != null && !browser.isEmpty()) ? browser : TestDataManager.getBrowser();
        
        // Initialize WebDriver
        driver = WebDriverManagerUtil.createDriver(browserName);
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestDataManager.getImplicitWait()));
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestDataManager.getTimeout()));
        
        // Navigate to the application
        driver.get(TestDataManager.getAppUrl());
    }
    
    /**
     * Setup method without parameters (for tests that don't specify browser)
     */
    @BeforeMethod
    public void setUp() {
        setUp(null);
    }
    
    /**
     * Teardown method that runs after each test method
     * Closes the WebDriver instance
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Gets the current WebDriver instance
     * @return WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Gets the WebDriverWait instance
     * @return WebDriverWait instance
     */
    protected WebDriverWait getWait() {
        return wait;
    }
}

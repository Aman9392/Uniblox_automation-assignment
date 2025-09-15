package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

// WebDriverManager utility for managing browser instances
// Supports Chrome, Firefox, and Edge
public class WebDriverManagerUtil {
    
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    
    /**
     Creates and returns a WebDriver instance based on the specified browser
     @param browserName Name of the browser (chrome, firefox, edge)
     @return WebDriver instance
     */
    public static WebDriver createDriver(String browserName) {
        WebDriver driver;
        
        switch (browserName.toLowerCase()) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                // Headless for speed, controlled via config
                if (Boolean.parseBoolean(TestDataManager.getProperty("headless", "true"))) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1366,768");
                }
                // Faster page load: disable images and use eager strategy
                chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
                chromeOptions.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        
        return driver;
    }
    
    /**
     Creates a Chrome driver with default settings
     @return Chrome WebDriver instance
     */
    public static WebDriver createChromeDriver() {
        return createDriver(CHROME);
    }
}

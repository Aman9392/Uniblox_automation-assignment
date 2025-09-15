package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TestDataManager: loads test data from properties files
public class TestDataManager {
    
    private static Properties properties;
    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     Loads properties from the configuration file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
            // Set default values if file not found
            setDefaultProperties();
        }
    }
    
    /**
     Sets default properties if config file is not found
     */
    private static void setDefaultProperties() {
        properties.setProperty("app.url", "https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg/app-selector");
        properties.setProperty("browser", "chrome");
        properties.setProperty("timeout", "10");
        properties.setProperty("implicit.wait", "5");
    }
    
    /**
     Gets a property value by key
     @param key Property key
     @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     Gets a property value with default value
     @param key Property key
     @param defaultValue Default value if key not found
     @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     Gets the application URL
     @return Application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }
    
    /**
     Gets the browser name
     @return Browser name
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    /**
     Gets the timeout value
     @return Timeout in seconds
     */
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout", "10"));
    }
    
    /**
     Gets the implicit wait value
     @return Implicit wait in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "5"));
    }
}


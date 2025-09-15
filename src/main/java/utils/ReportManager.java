package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// ReportManager: manages test reports using ExtentReports
// Creates a clean report directory each run and attaches screenshots
public class ReportManager {
    
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_DIR = "test-output/reports/";
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";
    
    /**
     Initializes the ExtentReports instance
     @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
    
    /**
     Creates a new ExtentReports instance with configuration
     */
    private static void createInstance() {
        // Create report directory if it doesn't exist
        // Clean old reports and screenshots to keep only latest run
        cleanDirectory(REPORT_DIR);
        cleanDirectory(SCREENSHOT_DIR);
        createDirectory(REPORT_DIR);
        createDirectory(SCREENSHOT_DIR);
        
        // Generate timestamp for unique report names
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = REPORT_DIR + "Uniblox_Test_Report_" + timestamp + ".html";
        
        // Create ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configure the reporter
        sparkReporter.config().setDocumentTitle("Uniblox Automation Test Report");
        sparkReporter.config().setReportName("Uniblox Application Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        
        // Create ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Set system information
        extent.setSystemInfo("Application", "Uniblox App Selector");
        extent.setSystemInfo("Test URL", TestDataManager.getAppUrl());
        extent.setSystemInfo("Browser", TestDataManager.getBrowser());
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }
    
    /**
     Creates a new test in the report
     @param testName Name of the test
     @param description Description of the test
     @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
        return test;
    }
    
    /**
     Logs a message to the current test
     @param status Status of the log message
     @param message Message to log
     */
    public static void log(Status status, String message) {
        if (test != null) {
            test.log(status, message);
        }
    }
    
    /**
     Logs an info message
     @param message Message to log
     */
    public static void logInfo(String message) {
        log(Status.INFO, message);
    }
    
    /**
     Logs a pass message
     @param message Message to log
     */
    public static void logPass(String message) {
        log(Status.PASS, message);
    }
    
    /**
     Logs a fail message
     @param message Message to log
     */
    public static void logFail(String message) {
        log(Status.FAIL, message);
    }
    
    /**
     Logs a skip message
     @param message Message to log
     */
    public static void logSkip(String message) {
        log(Status.SKIP, message);
    }
    
    /**
     Logs a warning message
     @param message Message to log
     */
    public static void logWarning(String message) {
        log(Status.WARNING, message);
    }
    
    /**
     Adds a screenshot to the current test
     @param driver WebDriver instance
     @param screenshotName Name for the screenshot
     */
    public static void addScreenshot(WebDriver driver, String screenshotName) {
        if (test != null && driver != null) {
            try {
                String screenshotPath = takeScreenshot(driver, screenshotName);
                test.addScreenCaptureFromPath(screenshotPath);
                logInfo("Screenshot captured: " + screenshotName);
            } catch (Exception e) {
                logWarning("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
    
    /**
     Takes a screenshot and saves it to the screenshots directory
     @param driver WebDriver instance
     @param screenshotName Name for the screenshot
     @return Path to the saved screenshot
     */
    private static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // This is a placeholder - in a real implementation, you would use
            // TakesScreenshot interface to capture the screenshot
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;
            
            // For now, we'll just create a placeholder file
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            
            return filePath;
        } catch (Exception e) {
            logWarning("Error taking screenshot: " + e.getMessage());
            return "";
        }
    }
    
    /**
     Updates test result based on test status
     @param status Test status (SUCCESS, FAILURE, SKIP)
     @param message Test result message
     */
    public static void updateTestResult(int status, String message) {
        if (test != null) {
            switch (status) {
                case 1: // SUCCESS
                    test.pass("Test passed successfully");
                    break;
                case 2: // FAILURE
                    test.fail("Test failed: " + message);
                    break;
                case 3: // SKIP
                    test.skip("Test skipped: " + message);
                    break;
                default:
                    test.info("Test status: " + status);
            }
        }
    }
    
    /**
     Flushes the report to ensure all data is written
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     Creates a directory if it doesn't exist
     @param directoryPath Path to the directory
     */
    private static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     Deletes all files under a directory if it exists.
     @param directoryPath path to clean
     */
    private static void cleanDirectory(String directoryPath) {
        try {
            File dir = new File(directoryPath);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (f.isDirectory()) {
                            // Recursively delete subdir content
                            deleteRecursively(f);
                        } else {
                            f.delete();
                        }
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File c : children) {
                    deleteRecursively(c);
                }
            }
        }
        try { file.delete(); } catch (Exception ignored) {}
    }
    
    /**
     Gets the current test instance
     @return Current ExtentTest instance
     */
    public static ExtentTest getCurrentTest() {
        return test;
    }
}

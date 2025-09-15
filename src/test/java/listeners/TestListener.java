package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import utils.ReportManager;

// TestListener for TestNG integration with ExtentReports
// Handles test execution events and updates the report
public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test in the report when a test starts
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        if (description == null || description.isEmpty()) {
            description = "Test method: " + testName;
        }
        
        ReportManager.createTest(testName, description);
        ReportManager.logInfo("Starting test: " + testName);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success
        String testName = result.getMethod().getMethodName();
        ReportManager.logPass("Test passed: " + testName);
        ReportManager.updateTestResult(result.getStatus(), result.getThrowable() != null ? result.getThrowable().getMessage() : "");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure
        String testName = result.getMethod().getMethodName();
        String errorMessage = result.getThrowable().getMessage();
        
        ReportManager.logFail("Test failed: " + testName);
        ReportManager.logFail("Error: " + errorMessage);
        
        // Add screenshot if available
        if (result.getTestClass().getRealClass().getSuperclass().getSimpleName().equals("BaseTest")) {
            try {
                // Get driver from BaseTest if possible
                Object testInstance = result.getInstance();
                java.lang.reflect.Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
                driverField.setAccessible(true);
                Object driver = driverField.get(testInstance);
                
                if (driver != null) {
                    ReportManager.addScreenshot((org.openqa.selenium.WebDriver) driver, "failure_" + testName);
                }
            } catch (Exception e) {
                ReportManager.logWarning("Could not capture screenshot: " + e.getMessage());
            }
        }
        
        ReportManager.updateTestResult(result.getStatus(), result.getThrowable() != null ? result.getThrowable().getMessage() : "");
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skip
        String testName = result.getMethod().getMethodName();
        String skipReason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Test was skipped";
        
        ReportManager.logSkip("Test skipped: " + testName);
        ReportManager.logSkip("Reason: " + skipReason);
        ReportManager.updateTestResult(result.getStatus(), result.getThrowable() != null ? result.getThrowable().getMessage() : "");
    }
    
    @Override
    public void onStart(ITestContext context) {
        // Initialize report when test suite starts
        ReportManager.getInstance();
        ReportManager.logInfo("Test suite started: " + context.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Flush report when test suite finishes
        ReportManager.flushReport();
        ReportManager.logInfo("Test suite completed: " + context.getName());
    }
}

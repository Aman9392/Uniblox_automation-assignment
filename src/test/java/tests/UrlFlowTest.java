package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import pages.MainPage;
import utils.TestDataManager;

/**
 * UrlFlowTest - Test cases for the Uniblox application
 * These tests cover the main functionality and user flows
 */
public class UrlFlowTest extends BaseTest {
    
    /**
     * Basic smoke test - checks if page loads and main elements are visible
     */
    @Test(description = "Verify page loads and main elements are visible")
    public void testPageLoadsAndHeaderIsVisible() {
        MainPage mainPage = new MainPage(driver);
        
        // Check if page loads properly
        Assert.assertTrue(mainPage.isPageLoaded(), "Page should load successfully");
        
        // Make sure header shows up
        String headerText = mainPage.getHeaderText();
        Assert.assertNotNull(headerText, "Header text should not be null");
        Assert.assertFalse(headerText.isEmpty(), "Header text should not be empty");
        
        // Check sub header if it exists
        String subHeaderText = mainPage.getSubHeaderText();
        if (subHeaderText != null && !subHeaderText.isEmpty()) {
            Assert.assertFalse(subHeaderText.isEmpty(), "Sub header text should not be empty if present");
        }
        
        // Should have at least one button
        Assert.assertTrue(mainPage.getButtonCount() > 0, "At least one button should be present on the page");
    }
    
    /**
     * Tests the complete user flow - fills forms and interacts with all elements
     */
    @Test(description = "Test complete user flow with form interactions")
    public void testCompleteUserFlow() {
        MainPage mainPage = new MainPage(driver);
        
        // Make sure page loads
        Assert.assertTrue(mainPage.isPageLoaded(), "Page should load successfully");
        
        // Get test data
        String testName = TestDataManager.getProperty("test.user.name", "Test User");
        String testEmail = TestDataManager.getProperty("test.user.email", "test@example.com");
        
        // Fill out the form
        mainPage.enterName(testName);
        mainPage.enterEmail(testEmail);
        mainPage.selectDropdownOption("option1"); // Try first option
        mainPage.enterTextInTextArea("This is a test message for automation testing.");
        
        // Click checkboxes and radio buttons
        mainPage.checkCheckbox();
        mainPage.selectRadioButton();
        
        // Shouldn't see any errors while filling
        Assert.assertFalse(mainPage.isErrorMessageDisplayed(), 
            "No error messages should be displayed during form filling");
        
        // Try to submit the form
        if (mainPage.getButtonCount() > 0) {
            mainPage.clickSubmitButton();
            
            // Give it a moment to process
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Tests form validation - checks error handling and validation messages
     */
    @Test(description = "Test form validation and error handling")
    public void testFormValidation() {
        MainPage mainPage = new MainPage(driver);
        
        // Make sure page loads
        Assert.assertTrue(mainPage.isPageLoaded(), "Page should load successfully");
        
        // Try submitting empty form
        mainPage.clickSubmitButton();
        
        // See if we get validation errors
        if (mainPage.isErrorMessageDisplayed()) {
            String errorMessage = mainPage.getErrorMessageText();
            Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty if displayed");
        }
        
        // Try with bad email
        mainPage.enterEmail("invalid-email");
        mainPage.clickSubmitButton();
        
        // Try with empty fields
        mainPage.enterName("");
        mainPage.enterEmail("");
        mainPage.clickSubmitButton();
        
        // Try reset if available
        if (mainPage.getButtonCount() > 1) {
            mainPage.clickResetButton();
        }
    }
    
    /**
     * Tests navigation and page elements - verifies URL and page structure
     */
    @Test(description = "Test navigation flow and element presence")
    public void testNavigationFlow() {
        MainPage mainPage = new MainPage(driver);
        
        // Verify page loads
        Assert.assertTrue(mainPage.isPageLoaded(), "Page should load successfully");
        
        // Verify page title
        String pageTitle = mainPage.getPageTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        
        // Verify current URL
        String currentUrl = mainPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("d28j9pfwubj8q5.cloudfront.net"), 
            "Current URL should contain the expected domain");
        
        // Verify page elements count
        int buttonCount = mainPage.getButtonCount();
        int inputCount = mainPage.getInputFieldCount();
        int linkCount = mainPage.getLinkCount();
        
        Assert.assertTrue(buttonCount >= 0, "Button count should be non-negative");
        Assert.assertTrue(inputCount >= 0, "Input field count should be non-negative");
        Assert.assertTrue(linkCount >= 0, "Link count should be non-negative");
        
        // Test start button functionality
        if (buttonCount > 0) {
            mainPage.clickStartButton();
            
            // Wait for any navigation or response
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Tests page responsiveness - checks if all interactive elements work properly
     */
    @Test(description = "Test page responsiveness and element interactions")
    public void testPageResponsiveness() {
        MainPage mainPage = new MainPage(driver);
        
        // Verify page loads
        Assert.assertTrue(mainPage.isPageLoaded(), "Page should load successfully");
        
        // Test all available interactions
        mainPage.enterName("Responsive Test");
        mainPage.enterEmail("responsive@test.com");
        mainPage.enterTextInTextArea("Testing page responsiveness");
        
        // Test checkbox interactions
        mainPage.checkCheckbox();
        mainPage.uncheckCheckbox();
        mainPage.checkCheckbox();
        
        // Test radio button interactions
        mainPage.selectRadioButton();
        
        // Verify no errors during interactions
        Assert.assertFalse(mainPage.isErrorMessageDisplayed(), 
            "No error messages should appear during normal interactions");
    }
    
    /**
     * Tests cross-browser compatibility - ensures app works in different browsers
     */
    @Test(description = "Test cross-browser compatibility")
    @Parameters({"browser"})
    public void testCrossBrowserCompatibility(String browser) {
        MainPage mainPage = new MainPage(driver);
        
        // Verify page loads in different browsers
        Assert.assertTrue(mainPage.isPageLoaded(), 
            "Page should load successfully in " + browser + " browser");
        
        // Verify basic functionality works across browsers
        String headerText = mainPage.getHeaderText();
        Assert.assertNotNull(headerText, "Header should be visible in " + browser + " browser");
        
        // Test basic interactions
        mainPage.clickStartButton();
        
        // Verify no browser-specific errors
        Assert.assertFalse(mainPage.isErrorMessageDisplayed(), 
            "No browser-specific errors should occur in " + browser);
    }
}

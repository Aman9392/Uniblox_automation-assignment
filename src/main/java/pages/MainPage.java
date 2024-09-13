package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * MainPage - Handles all interactions with the main page
 * This class contains all the elements and methods needed to test the app
 */
public class MainPage extends BasePage {
    
    // Page elements using PageFactory
    @FindBy(tagName = "h1")
    private WebElement pageHeader;
    
    @FindBy(tagName = "h2")
    private WebElement subHeader;
    
    @FindBy(css = "button")
    private WebElement startButton;
    
    @FindBy(css = "input[type='text']")
    private WebElement nameInput;
    
    @FindBy(css = "input[type='email']")
    private WebElement emailInput;
    
    @FindBy(css = "select")
    private WebElement dropdownSelect;
    
    @FindBy(css = "textarea")
    private WebElement textArea;
    
    @FindBy(css = "input[type='checkbox']")
    private WebElement checkbox;
    
    @FindBy(css = "input[type='radio']")
    private WebElement radioButton;
    
    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;
    
    @FindBy(css = "input[type='reset']")
    private WebElement resetButton;
    
    // Alternative locators using By
    private By pageTitle = By.tagName("title");
    private By allButtons = By.cssSelector("button");
    private By allInputs = By.cssSelector("input");
    private By allLinks = By.cssSelector("a");
    private By errorMessage = By.cssSelector(".error, .alert, .warning");
    private By successMessage = By.cssSelector(".success, .alert-success");
    
    /**
     * Constructor for MainPage
     * @param driver WebDriver instance
     */
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Gets the page header text
     * @return Header text
     */
    public String getHeaderText() {
        return getElementText(pageHeader);
    }
    
    /**
     * Gets the sub header text
     * @return Sub header text
     */
    public String getSubHeaderText() {
        return getElementText(subHeader);
    }
    
    /**
     * Clicks the start button
     */
    public void clickStartButton() {
        clickElement(startButton);
    }
    
    /**
     * Types name into the name field
     * @param name Name to enter
     */
    public void enterName(String name) {
        if (isElementVisible(nameInput)) {
            sendKeysToElement(nameInput, name);
        }
    }
    
    /**
     * Types email into the email field
     * @param email Email to enter
     */
    public void enterEmail(String email) {
        if (isElementVisible(emailInput)) {
            sendKeysToElement(emailInput, email);
        }
    }
    
    /**
     * Picks an option from the dropdown
     * @param optionValue Option value to select
     */
    public void selectDropdownOption(String optionValue) {
        if (isElementVisible(dropdownSelect)) {
            clickElement(dropdownSelect);
            WebElement option = driver.findElement(By.cssSelector("option[value='" + optionValue + "']"));
            clickElement(option);
        }
    }
    
    /**
     * Types text into the textarea
     * @param text Text to enter
     */
    public void enterTextInTextArea(String text) {
        if (isElementVisible(textArea)) {
            sendKeysToElement(textArea, text);
        }
    }
    
    /**
     * Ticks the checkbox
     */
    public void checkCheckbox() {
        if (isElementVisible(checkbox) && !checkbox.isSelected()) {
            clickElement(checkbox);
        }
    }
    
    /**
     * Unticks the checkbox
     */
    public void uncheckCheckbox() {
        if (isElementVisible(checkbox) && checkbox.isSelected()) {
            clickElement(checkbox);
        }
    }
    
    /**
     * Clicks the radio button
     */
    public void selectRadioButton() {
        if (isElementVisible(radioButton) && !radioButton.isSelected()) {
            clickElement(radioButton);
        }
    }
    
    /**
     * Clicks the submit button
     */
    public void clickSubmitButton() {
        clickElement(submitButton);
    }
    
    /**
     * Clicks the reset button
     */
    public void clickResetButton() {
        clickElement(resetButton);
    }
    
    /**
     * Gets the page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.findElement(pageTitle).getAttribute("textContent");
    }
    
    /**
     * Gets the number of buttons on the page
     * @return Number of buttons
     */
    public int getButtonCount() {
        return driver.findElements(allButtons).size();
    }
    
    /**
     * Gets the number of input fields on the page
     * @return Number of input fields
     */
    public int getInputFieldCount() {
        return driver.findElements(allInputs).size();
    }
    
    /**
     * Gets the number of links on the page
     * @return Number of links
     */
    public int getLinkCount() {
        return driver.findElements(allLinks).size();
    }
    
    /**
     * Checks if error message is displayed
     * @return True if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets error message text
     * @return Error message text
     */
    public String getErrorMessageText() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Checks if success message is displayed
     * @return True if success message is visible
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets success message text
     * @return Success message text
     */
    public String getSuccessMessageText() {
        try {
            return driver.findElement(successMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Verifies that the page has loaded correctly
     * @return True if page loaded successfully
     */
    public boolean isPageLoaded() {
        try {
            return isElementVisible(pageHeader) && isElementVisible(startButton);
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Performs a complete form submission with test data
     * @param name Name to enter
     * @param email Email to enter
     * @param optionValue Dropdown option value
     * @param textAreaText Text for textarea
     */
    public void fillAndSubmitForm(String name, String email, String optionValue, String textAreaText) {
        enterName(name);
        enterEmail(email);
        selectDropdownOption(optionValue);
        enterTextInTextArea(textAreaText);
        checkCheckbox();
        selectRadioButton();
        clickSubmitButton();
    }
}

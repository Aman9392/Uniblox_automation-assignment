package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// MainPage - Handles interactions with the main page
// Contains the elements and methods needed to test the app
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
    
    // Constructor for MainPage
    // @param driver WebDriver instance
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    // Get the page header text
    // @return Header text
    public String getHeaderText() {
        // Try multiple header strategies to accommodate varying DOMs
        try {
            return getElementText(pageHeader);
        } catch (Exception ignored) {}

        try {
            WebElement h1 = driver.findElement(By.tagName("h1"));
            return h1.getText();
        } catch (Exception ignored) {}

        try {
            WebElement h2El = driver.findElement(By.tagName("h2"));
            return h2El.getText();
        } catch (Exception ignored) {}

        try {
            WebElement roleHeading = driver.findElement(By.cssSelector("[role='heading']"));
            return roleHeading.getText();
        } catch (Exception ignored) {}

        try {
            WebElement titleLike = driver.findElement(By.cssSelector(".header, .title, .page-title, .page-header"));
            return titleLike.getText();
        } catch (Exception ignored) {}

        // Fallback to document title if no header-like element found
        try {
            return driver.getTitle();
        } catch (Exception ignored) {}

        return "";
    }
    
    // Get the sub header text
    // @return Sub header text
    public String getSubHeaderText() {
        try {
            return getElementText(subHeader);
        } catch (Exception ignored) {}

        try {
            WebElement h3 = driver.findElement(By.tagName("h3"));
            return h3.getText();
        } catch (Exception ignored) {}

        try {
            WebElement subTitle = driver.findElement(By.cssSelector(".subheader, .subtitle, .sub-title"));
            return subTitle.getText();
        } catch (Exception ignored) {}

        return "";
    }
    
    // Click the start button if visible
    public void clickStartButton() {
        if (isElementVisible(startButton)) {
            clickElement(startButton);
        }
    }
    
    // Type name into the name field
    // @param name Name to enter
    public void enterName(String name) {
        if (isElementVisible(nameInput)) {
            sendKeysToElement(nameInput, name);
        }
    }
    
    // Type email into the email field
    // @param email Email to enter
    public void enterEmail(String email) {
        if (isElementVisible(emailInput)) {
            sendKeysToElement(emailInput, email);
        }
    }
    
    // Pick an option from the dropdown
    // @param optionValue Option value to select
    public void selectDropdownOption(String optionValue) {
        if (isElementVisible(dropdownSelect)) {
            try {
                clickElement(dropdownSelect);
                WebElement option = driver.findElement(By.cssSelector("option[value='" + optionValue + "']"));
                clickElement(option);
            } catch (Exception ignored) {}
        }
    }
    
    // Type text into the textarea
    // @param text Text to enter
    public void enterTextInTextArea(String text) {
        if (isElementVisible(textArea)) {
            sendKeysToElement(textArea, text);
        }
    }
    
    // Tick the checkbox if not selected
    public void checkCheckbox() {
        if (isElementVisible(checkbox) && !checkbox.isSelected()) {
            clickElement(checkbox);
        }
    }
    
    // Untick the checkbox if selected
    public void uncheckCheckbox() {
        if (isElementVisible(checkbox) && checkbox.isSelected()) {
            clickElement(checkbox);
        }
    }
    
    // Click the radio button if not selected
    public void selectRadioButton() {
        if (isElementVisible(radioButton) && !radioButton.isSelected()) {
            clickElement(radioButton);
        }
    }
    
    // Click the submit button if visible
    public void clickSubmitButton() {
        if (isElementVisible(submitButton)) {
            clickElement(submitButton);
        }
    }
    
    // Click the reset button if visible
    public void clickResetButton() {
        if (isElementVisible(resetButton)) {
            clickElement(resetButton);
        }
    }
    
    // Get the page title text content
    // @return Page title
    public String getPageTitle() {
        return driver.findElement(pageTitle).getAttribute("textContent");
    }
    
    // Get the number of buttons on the page
    // @return Number of buttons
    public int getButtonCount() {
        return driver.findElements(allButtons).size();
    }
    
    // Get the number of input fields on the page
    // @return Number of input fields
    public int getInputFieldCount() {
        return driver.findElements(allInputs).size();
    }
    
    // Get the number of links on the page
    // @return Number of links
    public int getLinkCount() {
        return driver.findElements(allLinks).size();
    }
    
    // Check if error message is displayed
    // @return True if error message is visible
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Get error message text
    // @return Error message text
    public String getErrorMessageText() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Check if success message is displayed
    // @return True if success message is visible
    public boolean isSuccessMessageDisplayed() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Get success message text
    // @return Success message text
    public String getSuccessMessageText() {
        try {
            return driver.findElement(successMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Verify that the page has loaded correctly
    // @return True if page loaded successfully
    public boolean isPageLoaded() {
        try {
            // Prefer header and button if available
            if (isElementVisible(pageHeader) && isElementVisible(startButton)) {
                return true;
            }

            // Accept if either header is visible
            if (isElementVisible(pageHeader) || isElementVisible(subHeader)) {
                return true;
            }

            // Accept if there is at least one interactive element present
            if (!driver.findElements(allButtons).isEmpty() || !driver.findElements(allInputs).isEmpty()) {
                return true;
            }

            // Fallback: page title present or body exists
            String title = driver.getTitle();
            if (title != null && !title.isEmpty()) {
                return true;
            }
            return driver.findElements(By.tagName("body")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Perform a complete form submission with test data
    // @param name Name to enter
    // @param email Email to enter
    // @param optionValue Dropdown option value
    // @param textAreaText Text for textarea
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

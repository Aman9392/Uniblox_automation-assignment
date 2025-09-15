# Uniblox Automation Assignment

## Project Description
This is a Selenium-based test automation framework built for testing the Uniblox application. The framework uses Java, TestNG, and follows the Page Object Model design pattern to ensure maintainable and scalable test automation.

**Test URL**: https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg

## Project Structure

The project follows a standard Maven structure:

**Main source code (src/main/java/):**
- `pages/` - Page object classes (BasePage.java, MainPage.java)
- `utils/` - Utility classes for WebDriver, test data, and reporting

**Test code (src/test/java/):**
- `tests/` - Test classes (BaseTest.java, UrlFlowTest.java)
- `listeners/` - TestNG listener for reporting

**Configuration:**
- `src/main/resources/config.properties` - Test configuration
- `pom.xml` - Maven dependencies
- `testng.xml` - TestNG suite configuration

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome, Firefox, or Edge browser
- Internet connection

## Setup Instructions

### 1. Clone the repository

git clone https://github.com/Aman9392/Uniblox_automation-assignment.git
cd Uniblox_automation-assignment


### 2. Install dependencies

mvn clean install


### 3. Run tests

mvn clean test


## Test Cases

The framework includes the following test cases:

1. testPageLoadsAndHeaderIsVisible - Verifies the page loads correctly and main elements are visible
2. testCompleteUserFlow - Tests the complete user interaction flow with all form elements
3. testFormValidation - Tests form validation and error handling
4. testNavigationFlow - Tests navigation and page element presence
5. testPageResponsiveness - Tests page responsiveness and element interactions
6. testCrossBrowserCompatibility - Tests application compatibility across different browsers

## Configuration

### Browser Selection
Edit `src/main/resources/config.properties`:
browser=chrome  # Options: chrome, firefox, edge


### Test Data
test.user.name=Test User
test.user.email=test@example.com


### Run all tests

mvn test


### Run specific test

mvn test -Dtest=UrlFlowTest#testPageLoadsAndHeaderIsVisible


### Run with different browser

mvn test -Dbrowser=firefox



## Test Reports

Test reports are generated in the `test-output/reports/` directory after test execution. The reports include:
- Detailed test execution timeline
- Screenshots on test failures
- Test results and status
- System information

## Framework Features

- **Page Object Model**: Maintainable and reusable page objects
- **Cross-browser Support**: Chrome, Firefox, and Edge
- **ExtentReports**: Detailed HTML test reports
- **Screenshot Capture**: Automatic screenshots on failures
- **Configuration Management**: Centralized configuration through properties files
- **Error Handling**: Robust error handling and recovery mechanisms

## Assumptions Made

1. **Unidirectional Flow**: No back button functionality testing required as specified in the assignment
2. **Standard Web Elements**: Application uses standard HTML form elements
3. **Error Handling**: Application provides appropriate error messages for validation
4. **Browser Compatibility**: Modern browsers with JavaScript support


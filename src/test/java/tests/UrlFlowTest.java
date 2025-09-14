package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class UrlFlowTest extends BaseTest {
    private static final String URL = "https://d28j9pfwubj8q5.cloudfront.net/5U5PU/4oKeg";

    @Test
    public void testPageLoadsAndHeaderIsVisible() {
        driver.get(URL);

        MainPage mainPage = new MainPage(driver);

        String headerText = mainPage.getHeaderText();
        Assert.assertTrue(headerText != null && !headerText.isEmpty(), "Header should not be empty");

        // Example: simulate first action
        mainPage.clickStart();
    }
}

import io.qameta.allure.Step;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.RunWith;
import utils.Waiters;
import driverFactory.WebDriverController;
import org.junit.Test;
import pages.GoogleSearchPage;

import static org.junit.Assert.*;

public class MainTest extends BaseTest {



    @Test
    @Step("Input text to Google Search")
    public void inputTextToGoogleSearch() {
        WebDriverController.getDriver().get("https://www.google.com");
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        Waiters.waitAppearanceOfWebElement(10, googleSearchPage.googleLogo);
        String testText = "Automation Testing...";
        googleSearchPage.searchField.sendKeys(testText);
        Waiters.waitAppearanceOfWebElement(10, googleSearchPage.searchResult);
        assertTrue("Search Result Contains",googleSearchPage.searchResult.getAttribute("data-psd").contains(testText));
    }
}

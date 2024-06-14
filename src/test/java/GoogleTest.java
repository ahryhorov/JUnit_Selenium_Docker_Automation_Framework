import io.qameta.allure.Step;
import org.junit.Test;
import pages.GoogleSearchPage;

public class GoogleTest extends BaseTest {



    @Test
    @Step("Input text to Google Search")
    public void inputTextToGoogleSearch() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.navigateToGoogleHomePage();
        String testText = "Automation Testing...";
        googleSearchPage.inputTextToSearchField(testText);
        googleSearchPage.verifyThatSearchOptionContainingQueryIsDisplayed(testText);
    }
}

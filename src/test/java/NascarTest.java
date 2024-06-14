import io.qameta.allure.Step;
import org.junit.Test;
import pages.GoogleSearchPage;
import pages.NascarMainPage;

public class NascarTest extends BaseTest {



    @Test
    @Step("Navigate to Nascar.com")
    public void navigateToNascarHomePage() {
        NascarMainPage nascarMainPage = new NascarMainPage();
        nascarMainPage.navigateToNascarHomePage();
    }
}

package pages;

import driverFactory.WebDriverController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiters;

import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleSearchPage extends BasePage {
    @FindBy(xpath = ".//textarea[@id='APjFqb']")
    public WebElement searchField;

    @FindBy(xpath = ".//img[@alt='Google']")
    public WebElement googleLogo;

    @FindBy(xpath = ".//li[@data-view-type='1']")
    public WebElement searchResult;

    public void navigateToGoogleHomePage() {
        WebDriverController.getDriver().get("https://www.google.com");
        Waiters.waitAppearanceOfWebElement(10, googleLogo);
    }

    public void inputTextToSearchField(String text) {
        searchField.sendKeys(text);
    }

    public void verifyThatSearchOptionContainingQueryIsDisplayed(String searchQuery) {
        Waiters.waitAppearanceOfWebElement(10, searchResult);
        assertThat("Search Result Contains",searchResult.getAttribute("data-psd").contains(searchQuery));
    }

}

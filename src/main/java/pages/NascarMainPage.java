package pages;

import driverFactory.WebDriverController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiters;

import static org.hamcrest.MatcherAssert.assertThat;

public class NascarMainPage extends BasePage {

    @FindBy(xpath = ".//img[@class='custom-logo']")
    public WebElement nascarLogo;

    public void navigateToNascarHomePage() {
        WebDriverController.getDriver().get("https://www.nascar.com/");
        Waiters.waitAppearanceOfWebElement(10, nascarLogo);
    }

}

package pages;

import driverFactory.WebDriverController;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(WebDriverController.getDriver(), this);
    }

}

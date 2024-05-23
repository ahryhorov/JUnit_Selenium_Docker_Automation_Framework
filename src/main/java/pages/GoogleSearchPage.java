package pages;

import driverFactory.WebDriverController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends BasePage {
    @FindBy(xpath = ".//textarea[@id='APjFqb']")
    public WebElement searchField;

    @FindBy(xpath = ".//img[@alt='Google']")
    public WebElement googleLogo;

    @FindBy(xpath = ".//li[@data-view-type='1']")
    public WebElement searchResult;


}

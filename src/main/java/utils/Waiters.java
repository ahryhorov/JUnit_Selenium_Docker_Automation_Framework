package utils;

import driverFactory.WebDriverController;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {
    private static final By SPINNER_XPATH = By.xpath(".//*[@role='progressbar' and not(contains(@aria-valuemax,'')) "
            + "or (@id='DataTables_Table_0_processing' and @style='display: block;') "
            + "or (contains(@id,'loadmask') and contains(@style,'z-index') and not(contains(@style,'display: none;')))] "
            + " | .//div[@id='body_content']//div[@class='x-mask']" + " | .//div[@class='loadingOverlay ng-scope']"
            + " | .//mat-spinner"
            //                + " | .//mat-progress-bar[@role='progressbar']" //.//td[@role='gridcell'][.//mat-progress-bar[@role='progressbar']]
            + " | .//i[@class='fa fa-spinner fa-pulse fa-3x fa-fw margin-bottom']"
            + " | .//div[contains(@ng-show,'serachResultsLoading')][not(contains(@class,'ng-hide'))]"
            + " | .//div[@id='mask' and contains(@style, 'display: block;')] | .//div[@ng-show='loading' and @class!='ng-hide']"
            + "| .//div[@class='LoadingSpinner'] | .//div[contains(text(),'Loading')] | .//div[@class='blockUI blockMsg blockPage']"
            + "| .//div[@role='progressbar'] | .//div[@ng-show='loadingPageData']/i | .//div[@id='floatingCirclesG'] "
            + " | .//div[@id='pgLoader'] | .//div[@class='loading-container'] | .//div[@class='preloader'] | .//div[@id='loadmask-1033']"
            + " | .//i[contains(@class, 'far fa-spinner fa-pulse')]");

    public static void waitAppearanceOfWebElement(int timeoutInSeconds, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(WebDriverController.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class, NotFoundException.class)
                    .ignoring(NullPointerException.class)
                    .pollingEvery(Duration.ofMillis(1000)).until(ExpectedConditions.visibilityOf(webElement));
            waitForSpinnerDisappearance(getDefaultTimeout());
            waitForJStoLoad();
        } catch (TimeoutException e) {
            throw new CustomAssertionError(e);
        }
    }

    public static int getDefaultTimeout() {
        return Math.toIntExact(10); //TODO remove hardcode
    }

    public static void waitForJStoLoad() {
        waitForJStoLoad(getDefaultTimeout());
    }

    public static void waitForJStoLoad(int timeoutInSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(WebDriverController.getDriver()).withTimeout(Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.withMessage("waiting for all processes (JS, Angular, Ajax) completed")
                    .pollingEvery(Duration.ofMillis(1000)).until(Waiters::applyReadyStateComplete);
        } catch (NoSuchWindowException ignore) {
        }
    }

    private static Boolean applyReadyStateComplete(WebDriver driver) {
        try {
            return (Boolean) WebDriverController.executeScriptJS("return document.readyState == 'complete' "
                    + "&& (window.angular === undefined || angular.element(document).injector() === undefined "
                    + "|| angular.element(document).injector().get('$http').pendingRequests.length == 0)");
        } catch (NoSuchWindowException e) {
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return true;
        }
    }

    public static void waitForSpinnerDisappearance(int timeoutInSeconds) {
        WebDriverController.setImplicitlyWait(0);
        if (WebDriverController.getDriver().findElements(SPINNER_XPATH).size() > 0) {
            WebDriverWait wait = new WebDriverWait(WebDriverController.getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.withMessage("Loading spinner not disappeared after " + timeoutInSeconds + " sec waiting")
                    .pollingEvery(Duration.ofMillis(1000))
                    //                    .ignoring(NoSuchElementException.class, NotFoundException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(SPINNER_XPATH));
            waitForJStoLoad();
        }
        WebDriverController.setImplicitlyWait(getDefaultTimeout());
    }


}

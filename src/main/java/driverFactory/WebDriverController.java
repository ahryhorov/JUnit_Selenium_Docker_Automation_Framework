package driverFactory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverController {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }
    public static void startChromeDriver(){
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        driver = new ChromeDriver();
    }

    public static void startRemoteDriverForDocker() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public static void openBrowser() {
        startChromeDriver();
        driver.manage().window().maximize();
    }

    public static void initDriver(){
        String selectRunHub = System.getProperty("selectRunHub","Local");
        boolean isRemoteRun = "Docker".equals(selectRunHub);
        if(isRemoteRun){
            System.out.println("DEBUG 123");
            openBrowserRemote();
        } else {
            openBrowser();
        }
    }
    public static void openBrowserRemote() {
        startRemoteDriverForDocker();
        driver.manage().window().maximize();
    }
    public static void quitDriver() {
        driver.quit();
    }

    public static Object executeScriptJS(String script, Object... params) {
        return ((JavascriptExecutor) driver).executeScript(script, params);
    }

    public static void setImplicitlyWait(int timeout, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }

    public static void setImplicitlyWait(int seconds) {
        setImplicitlyWait(seconds, TimeUnit.SECONDS);
    }


}

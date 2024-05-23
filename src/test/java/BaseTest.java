import driverFactory.WebDriverController;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    @Before
    public void beforeEach() {
        WebDriverController.initDriver();
    }

    @After
    public void afterEach() {
        WebDriverController.quitDriver();
    }
}

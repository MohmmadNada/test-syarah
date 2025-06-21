package hooks;

import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
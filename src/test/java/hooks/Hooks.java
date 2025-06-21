package hooks;

import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import reporting.ExtentReportManager;

public class Hooks {
    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
    }
    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentReportManager.unload();
        ExtentReportManager.getExtentReports().flush();

    }
}
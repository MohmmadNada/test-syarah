package hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import reporting.ExtentReportManager;
import reporting.ScreenshotUtil;
import reporting.VideoRecorderUtil;

/**
 * Cucumber Hooks for browser, reporting, screenshot, and video management.
 */
public class Hooks {

    @Before(order=0)
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @Before(order=1)
    public void setUp(Scenario scenario) throws Exception {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        VideoRecorderUtil.startRecording(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            ExtentReportManager.getTest().info("Step Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentReportManager.getTest().info("Could not attach screenshot: " + e.getMessage());
        }
    }

    @After(order = 1)
    public void tearDown() {

        DriverFactory.quitDriver();
    }

    @After(order = 2)
    public void afterScenario() throws Exception {
        String videoPath = VideoRecorderUtil.stopRecording();
        if (videoPath != null) {
            // Attach as a clickable link in Extent Report
            ExtentReportManager.getTest().info(
                    "<a href='" + videoPath + "' target='_blank'>Watch Video</a>"
            );
        }
    }

}
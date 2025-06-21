package hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import config.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import reporting.ExtentReportManager;
import reporting.ScreenshotUtil;
import reporting.VideoRecorderUtil;

public class Hooks {
    @Before
    public void setUp(Scenario scenario) throws Exception {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        VideoRecorderUtil.startRecording(scenario.getName());
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
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

    @After
    public void tearDown() {

        DriverFactory.quitDriver();
    }

    @After
    public void afterScenario() throws Exception {
        String videoPath = VideoRecorderUtil.stopRecording();
        if (videoPath != null) {
            // Attach as a clickable link in Extent Report
            ExtentReportManager.getTest().info(
                    "<a href='" + videoPath + "' target='_blank'>Watch Video</a>"
            );
        }
        ExtentReportManager.unload();
        ExtentReportManager.getExtentReports().flush();
    }
}
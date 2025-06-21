package reporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Utility class for capturing screenshots during automated test execution.
 * <p>
 * This class provides a static method to capture a screenshot using a Selenium WebDriver instance.
 * The screenshot is saved in the "target/screenshots/" directory with a filename based on the provided scenario name and the current timestamp.
 * The returned path is relative to the report location, which allows direct embedding in HTML reports (such as Extent Reports).
 * </p>
 */
public class ScreenshotUtil {

    /**
     * Captures a screenshot of the current browser window.
     *
     * @param driver       The {@link WebDriver} instance used to capture the screenshot. Must implement {@link TakesScreenshot}.
     * @param scenarioName The name of the scenario or step for which the screenshot is being captured. Used in the screenshot filename.
     * @return A relative path (from the report directory) to the saved screenshot image, suitable for embedding in reports.
     * @throws IOException If there is an error during file creation or copying.
     */
    public static String captureScreenshot(WebDriver driver, String scenarioName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destDir = "target/screenshots/";
        new File(destDir).mkdirs();
        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis() + ".png";
        String destPath = destDir + fileName;
        File dest = new File(destPath);
        Files.copy(src.toPath(), dest.toPath());
        return "screenshots/" + fileName; // <<< return relative path for report
    }
}
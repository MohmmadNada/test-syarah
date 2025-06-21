package stepdefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import reporting.ExtentReportManager;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your .feature files
        glue = {"stepdefinitions", "hooks","reporting"}, // Package(s) for your step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

        @AfterSuite(alwaysRun = true)
        public void writeExtentReport() {
                ExtentReportManager.getExtentReports().flush();
                ExtentReportManager.unload();
        }

}
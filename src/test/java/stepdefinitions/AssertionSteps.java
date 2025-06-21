package stepdefinitions;

import config.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.SearchResultsPage;
import java.util.List;
import com.aventstack.extentreports.Status;
import reporting.ExtentReportManager;

public class AssertionSteps {
    SearchResultsPage searchResultsPage;

    @Then("validate that the applied filter tags include the brand {string} and year range {int} to {int}")
    public void validate_filter_tags(String brand, int from, int to) {
        ExtentReportManager.getTest().log(Status.INFO, String.format("Validating filter tags include brand '%s' and year range '%d - %d'.", brand, from, to));
        searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        final String expectedYearTag = from + " - " + to;
        // Wait until at least one filter tag is visible
        List<String> appliedFilters = searchResultsPage.getVisibleFilterTagTexts();
        ExtentReportManager.getTest().log(Status.INFO, "Applied filters found: " + appliedFilters);
        try {
            Assert.assertTrue(appliedFilters.contains(brand), "Expected brand filter tag '" + brand + "' not found among applied filters: " + appliedFilters);
            ExtentReportManager.getTest().log(Status.PASS, "Brand filter tag '" + brand + "' is present among applied filters.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, e.getMessage());
            throw e;
        }
        try {
            Assert.assertTrue(appliedFilters.contains(expectedYearTag), "Expected year range filter tag '" + expectedYearTag + "' not found among applied filters: " + appliedFilters);
            ExtentReportManager.getTest().log(Status.PASS, "Year range filter tag '" + expectedYearTag + "' is present among applied filters.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, e.getMessage());
            throw e;
        }
    }

    @Then("validate that all car cards in the results contain {string} in their title")
    public void validate_car_cards_brand_in_title(String brand) {
        ExtentReportManager.getTest().log(Status.INFO, String.format("Validating all car cards in the results contain '%s' in their title.", brand));
        try {
            searchResultsPage.assertAllCarTitlesContainBrand(brand);
            ExtentReportManager.getTest().log(Status.PASS, "All car cards in results contain '" + brand + "' in their title.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, "One or more car cards do not contain '" + brand + "' in their title. " + e.getMessage());
            throw e;
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.SearchResultsPage;
import java.util.List;

public class AssertionSteps {
    SearchResultsPage searchResultsPage;

    @Then("validate that the applied filter tags include the brand {string} and year range {int} to {int}")
    public void validate_filter_tags(String brand, int from, int to) {
        searchResultsPage = new SearchResultsPage(NavigationSteps.driver);
        final String expectedYearTag = from + " - " + to;
        // Wait until at least one filter tag is visible
        List<String> appliedFilters = searchResultsPage.getVisibleFilterTagTexts();
        // Validate that the applied filter tags include the brand and year range
        Assert.assertTrue(appliedFilters.contains(brand), "Expected brand filter tag '" + brand + "' not found among applied filters: " + appliedFilters);
        Assert.assertTrue(appliedFilters.contains(expectedYearTag), "Expected year range filter tag '" + expectedYearTag + "' not found among applied filters: " + appliedFilters);
    }

    @Then("validate that all car cards in the results contain {string} in their title")
    public void validate_car_cards_brand_in_title(String brand) {
        searchResultsPage.assertAllCarTitlesContainBrand(brand);
        NavigationSteps.driver.quit();
    }
}
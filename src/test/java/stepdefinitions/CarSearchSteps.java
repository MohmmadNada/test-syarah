package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;
import java.util.List;

public class CarSearchSteps {
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @Given("the user is on the Syarah Home page")
    public void user_is_on_homepage() {
        // TODO: Handle lunches and close browser in hooks
        driver = new ChromeDriver(); // Or use DriverFactory if you have one
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.visit();
    }

    @When("the user selects Toyota as the brand") // TODO: Make dynamic
    public void user_selects_brand() throws InterruptedException {
        homePage.selectToyotaBrand();
    }

    @When("the user selects year range {int} to {int}")
    public void user_selects_year_range(int from, int to) {
        searchResultsPage.selectYearRange(from, to);
    }

    @Then("the user redirected to Search Results page")
    public void user_redirected_to_search_result_page() {
        searchResultsPage = new SearchResultsPage(driver);
        WebElement filterSection = searchResultsPage.getFilterSection();
        Assert.assertTrue(filterSection.isDisplayed(), "Element "+ filterSection +" is not displayed.");
    }

    @Then("validate that the applied filter tags include the brand {string} and year range {int} to {int}")
    public void validate_filter_tags(String brand, int from, int to) {
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
        driver.quit();
    }
}
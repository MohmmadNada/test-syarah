package stepdefinitions;

import config.DriverFactory;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchResultsPage;

public class FilterActions {
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @When("the user selects Toyota as the brand")
    public void user_selects_brand() throws InterruptedException {
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.selectToyotaBrand();
    }

    @When("the user selects year range {int} to {int}")
    public void user_selects_year_range(int from, int to) {
        searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        searchResultsPage.selectYearRange(from, to);
    }
}

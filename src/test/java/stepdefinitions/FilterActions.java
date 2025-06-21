package stepdefinitions;

import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchResultsPage;

public class FilterActions {
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @When("the user selects Toyota as the brand") // TODO: Make dynamic
    public void user_selects_brand() throws InterruptedException {
        homePage = new HomePage(NavigationSteps.driver);
        homePage.selectToyotaBrand();
    }

    @When("the user selects year range {int} to {int}")
    public void user_selects_year_range(int from, int to) {
        searchResultsPage = new SearchResultsPage(NavigationSteps.driver);
        searchResultsPage.selectYearRange(from, to);
    }
}

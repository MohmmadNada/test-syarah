package stepdefinitions;

import config.DriverFactory;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchResultsPage;
import com.aventstack.extentreports.Status;
import reporting.ExtentReportManager;

public class FilterActionsSteps {
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @When("the user selects Toyota as the brand")
    public void user_selects_brand() throws InterruptedException {
        ExtentReportManager.getTest().log(Status.INFO, "Selecting 'Toyota' as the brand from Home page.");
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.selectToyotaBrand();
        ExtentReportManager.getTest().log(Status.PASS, "'Toyota' brand selected.");
    }

    @When("the user selects year range {int} to {int}")
    public void user_selects_year_range(int from, int to) {
        ExtentReportManager.getTest().log(Status.INFO, String.format("Selecting year range: %d to %d on Search Results page.", from, to));
        searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        searchResultsPage.selectYearRange(from, to);
        ExtentReportManager.getTest().log(Status.PASS, String.format("Year range %d to %d selected.", from, to));
    }
}
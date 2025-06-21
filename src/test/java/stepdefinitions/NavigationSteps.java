package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import config.DriverFactory;
import org.testng.Assert;
import pages.FinanceEligibilityPage;
import pages.HomePage;
import pages.SearchResultsPage;

public class NavigationSteps {
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    FinanceEligibilityPage financeEligibilityPage;

    @Given("the user is on the Syarah Home page")
    public void user_is_on_home_page() {
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.visit();
    }

    @Given("the user is on the Finance Eligibility page")
    public void user_is_on_finance_eligibility_page() {
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.visit();
    }

    @Then("the user redirected to Search Results page")
    public void user_redirected_to_search_result_page() {
        searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        WebElement filterSection = searchResultsPage.getFilterSection();
        Assert.assertTrue(filterSection.isDisplayed(), "Element "+ filterSection +" is not displayed.");
    }
}

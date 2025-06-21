package stepdefinitions;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import config.DriverFactory;
import org.testng.Assert;
import pages.FinanceEligibilityPage;
import pages.HomePage;
import pages.SearchResultsPage;
import reporting.ExtentReportManager;

public class NavigationSteps {
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    FinanceEligibilityPage financeEligibilityPage;

    @Given("the user is on the Syarah Home page")
    public void user_is_on_home_page() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to Syarah Home page");
        homePage = new HomePage(DriverFactory.getDriver());
        homePage.visit();
        ExtentReportManager.getTest().log(Status.PASS, "User is on the Syarah Home page");
    }

    @Given("the user is on the Finance Eligibility page")
    public void user_is_on_finance_eligibility_page() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to Finance Eligibility page from home page");
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.visit();
        ExtentReportManager.getTest().log(Status.PASS, "User is on the Finance Eligibility page");
    }

    @Then("the user redirected to Search Results page")
    public void user_redirected_to_search_result_page() {
        ExtentReportManager.getTest().log(Status.INFO, "Verifying redirection to Search Results page");
        searchResultsPage = new SearchResultsPage(DriverFactory.getDriver());
        WebElement filterSection = searchResultsPage.getFilterSection();
        try {
            Assert.assertTrue(filterSection.isDisplayed(), "Element " + filterSection + " is not displayed.");
            ExtentReportManager.getTest().log(Status.PASS, "User successfully redirected to Search Results page. Filter section is displayed.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Redirection to Search Results page failed: " + e.getMessage());
            throw e; // re-throw to let the test fail
        }
    }
}

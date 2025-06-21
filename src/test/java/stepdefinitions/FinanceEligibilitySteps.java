package stepdefinitions;

import config.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FinanceEligibilityPage;
import com.aventstack.extentreports.Status;
import reporting.ExtentReportManager;

public class FinanceEligibilitySteps {
    FinanceEligibilityPage financeEligibilityPage;

    /* Actions */
    @When("the user selects the {string} option")
    public void select_the_option_by_label(String optionText) {
        ExtentReportManager.getTest().log(Status.INFO, "Selecting the option: '" + optionText + "' on Finance Eligibility page.");
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.selectOptionByText(optionText);
        ExtentReportManager.getTest().log(Status.PASS, "Option '" + optionText + "' selected.");
    }

    @When("the user fills {string} with {string}")
    public void the_user_fills_with(String question, String value) {
        ExtentReportManager.getTest().log(Status.INFO, String.format("Filling '%s' with value '%s' on Finance Eligibility page.", question, value));
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.fillFieldByQuestion(question, value);
        ExtentReportManager.getTest().log(Status.PASS, String.format("Field '%s' filled with '%s'.", question, value));
    }

    @When("the user clicks the Continue button")
    public void the_user_clicks_the_continue_button() {
        ExtentReportManager.getTest().log(Status.INFO, "Clicking the Continue button on Finance Eligibility page.");
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.clickContinueButton();
        ExtentReportManager.getTest().log(Status.PASS, "Continue button clicked.");
    }

    @When("the user clicks the Verify button")
    public void the_user_clicks_the_verify_button() {
        ExtentReportManager.getTest().log(Status.INFO, "Clicking the Verify button on Finance Eligibility page.");
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.clickVerifyButton();
        ExtentReportManager.getTest().log(Status.PASS, "Verify button clicked.");
    }

    /* Assertions */
    @Then("the authentication form should be displayed")
    public void the_authentication_form_should_be_displayed() {
        ExtentReportManager.getTest().log(Status.INFO, "Verifying that the authentication form is displayed on Finance Eligibility page.");
        try {
            new FinanceEligibilityPage(DriverFactory.getDriver()).verifyAuthFormIsDisplayed();
            ExtentReportManager.getTest().log(Status.PASS, "Authentication form is displayed.");
        } catch (AssertionError e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Authentication form is not displayed. " + e.getMessage());
            throw e;
        }
    }
}
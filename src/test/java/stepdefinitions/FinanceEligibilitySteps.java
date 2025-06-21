package stepdefinitions;

import config.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FinanceEligibilityPage;

public class FinanceEligibilitySteps {
    FinanceEligibilityPage financeEligibilityPage;
    /* Actions */
    @When("the user selects the {string} option")
    public void select_the_option_by_label(String optionText) {
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.selectOptionByText(optionText);
    }

    @When("the user fills {string} with {string}")
    public void the_user_fills_with(String question, String value) {
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.fillFieldByQuestion(question, value);
    }

    @When("the user clicks the Continue button")
    public void the_user_clicks_the_continue_button() {
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.clickContinueButton();
    }

    @When("the user clicks the Verify button")
    public void the_user_clicks_the_verify_button() {
        financeEligibilityPage = new FinanceEligibilityPage(DriverFactory.getDriver());
        financeEligibilityPage.clickVerifyButton();
    }

    /* Assertions */
    @Then("the authentication form should be displayed")
    public void the_authentication_form_should_be_displayed() {
        new FinanceEligibilityPage(DriverFactory.getDriver()).verifyAuthFormIsDisplayed();
    }
}
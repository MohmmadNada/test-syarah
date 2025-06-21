package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;

public class NavigationSteps {
    public static WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;

    @Given("the user is on the Syarah Home page")
    public void user_is_on_homepage() {
        // TODO: Handle lunches and close browser in hooks
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.visit();
    }

    @Then("the user redirected to Search Results page")
    public void user_redirected_to_search_result_page() {
        searchResultsPage = new SearchResultsPage(driver);
        WebElement filterSection = searchResultsPage.getFilterSection();
        Assert.assertTrue(filterSection.isDisplayed(), "Element "+ filterSection +" is not displayed.");
    }
}

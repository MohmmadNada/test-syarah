package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinanceEligibilityPage extends BasePage {
    /* Selectors */
    private final By CONTINUE_BUTTON = By.xpath("//button[text()=\"Continue\"]");
    private final By VERIFY_BUTTON = By.xpath("//button[text()=\"Verify\"]");
    private final By AUTH_FORM = By.xpath("//form[@class=\"AuthFormV2-module__form\"]");

    /* Dynamic Selectors */
    private By getInputXpathByLabel(String labelText) {
        return By.xpath("//div[div[text()=\"" + labelText + "\"]]/following-sibling::div//input");
    }

    public FinanceEligibilityPage(WebDriver driver) {
        super(driver);
    }

    private By getOptionLabelXpath(String text) {
        return By.xpath("//div[@role=\"radiogroup\"]/label[text()=\"" + text + "\"]");
    }
    /* Page URL */
    @Override
    public String getRelativeUrl() {
        return "/en/site/finance-eligibility";
    }

    /* Actions */
    public void selectOptionByText(String text) {
        By elLocator = getOptionLabelXpath(text);
        System.out.println(elLocator);
        click(elLocator);
    }

    public void fillFieldByQuestion(String question, String value) {
        By inputEl = getInputXpathByLabel(question);
        this.clearAndType(inputEl, value);
    }

    public void clickContinueButton() {
        click(CONTINUE_BUTTON);
    }

    public void clickVerifyButton() {
        click(VERIFY_BUTTON);
    }

    /* Assertions */
    public void verifyAuthFormIsDisplayed() {
        WebElement authForm = find(AUTH_FORM);
        if (!authForm.isDisplayed()) {
            throw new AssertionError("Authentication form is not displayed");
        }
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {
    /* Locators */
    private final By YEAR_OF_MANUFACTURE_OPTION = By.xpath("//div[strong[text()=\"سنة الصنع\"]]");
    private final By FROM_YEAR_INPUT = By.xpath("//span[@class='Input-module__label' and text()='من']/preceding-sibling::input");
    private final By TO_YEAR_INPUT = By.xpath("//span[@class='Input-module__label' and text()='الى']/preceding-sibling::input");
    private final By DONE_BUTTON = By.xpath("//button[@class=\"SubMenuContainer-module__DoneBtn\"]");
    private final By APPLIED_FILTER_TAGS = By.xpath("//div[@class=\"AsideTags-module__mobWrapper\"]/span[not(contains(@class, \"reset\"))]");
    private final By CARD_TITLE = By.xpath("//div[@class=\"UnbxdCards-module__allCarsResult\"]//div/h2");
    private final By FILTER_SECTION = By.id("AsideHeightOne");

    /* Selector Sections */
    public WebElement getFilterSection() {
        return this.find(FILTER_SECTION);
    }

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    /* Actions */
    public void selectYearRange(int fromYear, int toYear) {
        // 1. Click 'سنة الصنع' (Year of Manufacture) filter from filter sidebar;
        this.click(YEAR_OF_MANUFACTURE_OPTION);

        // 2. Fill "من" input
        this.clearAndType(FROM_YEAR_INPUT, fromYear);

        // 3. Fill "إلى" (to) input
        this.clearAndType(TO_YEAR_INPUT, toYear);

        // 4. Click on Done button (adjust selector if needed)
        this.click(DONE_BUTTON);
    }

    /* Getters */
    public List<String> getVisibleFilterTagTexts() {
        List<WebElement> filterTags = this.findAll(APPLIED_FILTER_TAGS);
        return filterTags.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    private List<String> getCarListTitles() {
        List<WebElement> carTitles = this.findAll(CARD_TITLE);

        return carTitles.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public void assertAllCarTitlesContainBrand(String brand) {
        List<String> carTitles = this.getCarListTitles();
        for (String title : carTitles) {
            if (!title.contains(brand)) {
                throw new AssertionError("Car title does not contain '" + brand + "': " + title + ". All titles: " + carTitles);
            }
        }
    }

}
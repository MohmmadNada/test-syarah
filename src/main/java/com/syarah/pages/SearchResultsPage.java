package com.syarah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    /* Selectors */
    String selectedTag = "//button[@class='Tag-module__tag']";
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    /* Actions */
    public void selectYearRange(int fromYear, int toYear) throws InterruptedException {
        // 1. Click 'سنة الصنع' (Year of Manufacture) filter from filter sidebar
        WebElement yearFilter = driver.findElement(By.xpath("//div[strong[text()=\"سنة الصنع\"]]"));
        yearFilter.click();
        // 2. Fill "من" input
        WebElement fromInput = driver.findElement(By.xpath("//span[@class='Input-module__label' and text()='من']/preceding-sibling::input"));
        fromInput.click();
        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        fromInput.sendKeys(String.valueOf(fromYear));

        // 3. Fill "إلى" (to) input
        WebElement toInput = driver.findElement(By.xpath("//span[@class='Input-module__label' and text()='الى']/preceding-sibling::input"));
        toInput.click();
        toInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        toInput.sendKeys(String.valueOf(toYear));
        // 4. Click on Done button (adjust selector if needed)
        WebElement doneButton = driver.findElement(By.xpath("//button[@class=\"SubMenuContainer-module__DoneBtn\"]"));
        doneButton.click();
    }

    /* Getters */
    public int getSelectedBrandTagsCount() {
        this.waitVisibility(selectedTag);
        List<WebElement> tags = driver.findElements(By.xpath(selectedTag));
        return tags.size();
    }

    public String getSelectedBrandTagText() {
        WebElement tag = driver.findElement(By.xpath(selectedTag));
        return tag.getText();
    }

    public List<String> getVisibleFilterTagTexts() {
        List<WebElement> filterTags = driver.findElements(By.xpath("//div[@class=\"AsideTags-module__mobWrapper\"]/span[not(contains(@class, \"reset\"))]"));
        return filterTags.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public List<String> getCarListTitles() {
        List<WebElement> carTitles = driver.findElements(By.xpath("//div[@class=\"UnbxdCards-module__allCarsResult\"]//div/h2"));

        return carTitles.stream()
                .filter(WebElement::isDisplayed)
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }
}
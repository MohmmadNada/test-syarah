package com.syarah.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = "https://preprod.syarah.com";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Each page should provide its relative path (e.g., "/", "/search")
    // Default implementation: returns null, meaning "no direct URL"
    public String getRelativeUrl() {
        return null;
    }

    public void visit() {
        driver.get(BASE_URL + getRelativeUrl());
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Scrolls the specified element into the center of the viewport.
     * This is useful when an element is hidden behind overlays or footers
     * and needs to be brought into view for reliable interaction.
     *
     * @param element The WebElement to scroll into view.
     */
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    protected List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Click using a By locator
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Click using a WebElement
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void clearAndType(By locator, Object text) {
        WebElement element = find(locator);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        element.sendKeys(String.valueOf(text));
    }
}
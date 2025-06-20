package com.syarah.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = "https://preprod.syarah.com/";

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
    /* TODO
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    // Click using a By locator
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    } */

    // Click using a WebElement
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    /* TODO
    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }*/
}
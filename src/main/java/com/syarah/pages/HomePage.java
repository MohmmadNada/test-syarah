package com.syarah.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    // Locators for brand, year range, and search button will go here

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectToyotaBrand() throws InterruptedException { // TODO: Make it dynamic for all brands
        Thread.sleep(3000);// TODO: Wait for Page stability
        WebElement toyotaLink = driver.findElement(By.xpath("//a[starts-with(@href, '/autos/toyota')]")); // TODO: Selector
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", toyotaLink);
        toyotaLink.click();
    }
}
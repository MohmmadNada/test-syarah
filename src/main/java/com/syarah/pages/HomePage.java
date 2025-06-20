package com.syarah.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    /* Locators */
    private static final By TOYOTA_BRAND_OPTION = By.xpath("//a[starts-with(@href, '/autos/toyota')]"); // TODO: Make dynamic

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @Override
    public String getRelativeUrl() {
        return "/";
    }

    public void selectToyotaBrand() throws InterruptedException {
        Thread.sleep(3_000);// TODO: Wait for Page stability
        WebElement toyotaLink = this.find(TOYOTA_BRAND_OPTION);
        /* The Toyota brand option may be hidden behind the footer or not visible in the viewport.
        Scrolling is required to bring the element into the center of the visible area
        to ensure it can be reliably clicked. */
        this.scrollIntoView(toyotaLink);
        this.click(toyotaLink);
    }
}
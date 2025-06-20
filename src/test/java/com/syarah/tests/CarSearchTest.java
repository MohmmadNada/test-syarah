package com.syarah.tests;

import com.syarah.pages.HomePage;
import com.syarah.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class CarSearchTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void testToyotaCarSearchByYear() throws InterruptedException {
        driver.get("https://preprod.syarah.com/");
        homePage.selectToyotaBrand();
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        int tagCount = searchResultsPage.getSelectedBrandTagsCount();
        Assert.assertEquals(tagCount, 1, "Expected 1 brand tag to be shown");
        String tagText = searchResultsPage.getSelectedBrandTagText();
        Assert.assertEquals(tagText, "تويوتا", "Brand tag text does not match expected value");
        searchResultsPage.selectYearRange(2022, 2025);
        // Validate Filters
        Thread.sleep(5_000); // TODO: Make it dynamic - Wait length more than Zero
        List<String> appliedFilters = searchResultsPage.getVisibleFilterTagTexts();
        System.out.println("Filters applied are: " + appliedFilters);// TODO: To Remove
        Assert.assertTrue(appliedFilters.contains("تويوتا"), "Brand tag not found");
        Assert.assertTrue(appliedFilters.contains("2022 - 2025"), "Year range tag not found");
        // Validate Search result - Car Cards
        List<String> carTitles = searchResultsPage.getCarListTitles();
        for (String title : carTitles) {
            Assert.assertTrue(title.contains("تويوتا"), "Car title does not contain 'تويوتا': " + title);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
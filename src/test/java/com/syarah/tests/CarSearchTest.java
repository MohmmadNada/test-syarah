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
        final int FROM_YEAR = 2022;
        final int TO_YEAR = 2025;
        final String TOYOTA_BRAND = "تويوتا";
        String expectedYearTag = FROM_YEAR + " - " + TO_YEAR;

        // 1. Navigate to the Home Page
        homePage.visit();
        // 2. Select "Toyota" brand
        homePage.selectToyotaBrand();
        // 3. On the Search Results Page, select the year range 2022 - 2025
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.selectYearRange(FROM_YEAR, TO_YEAR);
        // 4. Wait until at least one filter tag is visible
        List<String> appliedFilters = searchResultsPage.getVisibleFilterTagTexts();
        // 5. Validate that the applied filter tags include the brand and year range
        Assert.assertTrue(appliedFilters.contains(TOYOTA_BRAND), "Expected brand filter tag '" + TOYOTA_BRAND + "' not found among applied filters: " + appliedFilters);
        Assert.assertTrue(appliedFilters.contains(expectedYearTag), "Expected year range filter tag '" + expectedYearTag + "' not found among applied filters: " + appliedFilters);
        // 6. Validate that all car cards in the results contain 'تويوتا' in their title
        List<String> carTitles = searchResultsPage.getCarListTitles();
        for (String title : carTitles) {
            Assert.assertTrue(title.contains(TOYOTA_BRAND), "Car title does not contain '" + TOYOTA_BRAND + "': " + title + ". All titles: " + carTitles);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
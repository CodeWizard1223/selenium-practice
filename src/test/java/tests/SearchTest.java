package tests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    private WebDriver driver;

    private HomePage homePage;

    private ProductPage productPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    void searchProduct() {
        homePage.open();
        homePage.removeOverlays();
        assertTrue(homePage.isHomeVisible());
        homePage.clickProducts();
        productPage = new ProductPage(driver);
        productPage.removeOverlays();
        assertTrue(productPage.isAllProductsHeaderVisible());
        productPage.searchForProduct("Tshirt");
        assertTrue(productPage.isSearchedProductsVisible());
        assertTrue(productPage.isAllProductsVisible());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

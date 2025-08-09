package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    void addToCart() {
        // Home page
        homePage.open();
        homePage.removeOverlays();
        assertTrue(homePage.isHomeVisible(), "Home page is not visible");

        // Products page
        homePage.clickProducts();
        productPage = new ProductPage(driver);
        productPage.removeOverlays();
        assertTrue(productPage.isAllProductsHeaderVisible(), "'All Products' header is not visible");

        // 1) Add first product
        productPage.clickToAddToCartProductByIndex(0);   // hover + JS click + waits for modal
        productPage.clickToContinueShoppingButton();     // click "Continue Shopping" + waits for backdrop to disappear

        // 2) Add second product
        productPage.clickToAddToCartProductByIndex(1);   // again hover + JS click + waits for modal (inside the method)

        // Go to cart
        productPage.clickToViewCart();
        cartPage = new CartPage(driver);
        assertTrue(cartPage.isCartTableVisible(), "Cart table is not visible");

        // Verify products and details
        assertTrue(cartPage.areProductsInCart("Blue Top", "Men Tshirt"), "Expected products are not in the cart");
        assertTrue(cartPage.verifyProductDetails("Blue Top", "Rs. 500", "1", "Rs. 500"), "'Blue Top' details do not match");
        assertTrue(cartPage.verifyProductDetails("Men Tshirt", "Rs. 400", "1", "Rs. 400"), "'Men Tshirt' details do not match");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

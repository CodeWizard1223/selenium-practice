package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    // Main cart table element
    private final By cartTable = By.cssSelector("table#cart_info_table");

    // Example: specific product row (Blue Top)
    By productRow = By.xpath("//tr[td[contains(text(), 'Blue Top')]]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /** Checks if the cart table is visible on the page. */
    public boolean isCartTableVisible() {
        return driver.findElement(cartTable).isDisplayed();
    }

    /**
     * Verifies that all given products exist in the cart.
     * @param expectedProductNames List of product names that should be present in the cart.
     * @return true if all products are found, false otherwise.
     */
    public boolean areProductsInCart(String... expectedProductNames) {
        for (String productName : expectedProductNames) {
            boolean productFound = !driver.findElements(
                    By.xpath("//td[@class='cart_description']//a[contains(text(),'" + productName + "')]")
            ).isEmpty();

            if (!productFound) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies the price, quantity, and total for a given product in the cart.
     * Prints debug information if the values don't match.
     *
     * @param productName       Name of the product to verify
     * @param expectedPrice     Expected price text (e.g., "Rs. 500")
     * @param expectedQuantity  Expected quantity (e.g., "1")
     * @param expectedTotal     Expected total (e.g., "Rs. 500")
     * @return true if all values match, false otherwise
     */
    public boolean verifyProductDetails(String productName, String expectedPrice, String expectedQuantity, String expectedTotal) {
        By productRow = By.xpath("//tr[td[@class='cart_description']//a[contains(text(),'" + productName + "')]]");

        if (driver.findElements(productRow).isEmpty()) {
            System.out.println("❌ Product not found: " + productName);
            return false;
        }

        var rowElement = driver.findElement(productRow);

        String actualPrice = rowElement.findElement(By.cssSelector("td.cart_price > p")).getText().trim();
        String actualQuantity = rowElement.findElement(By.cssSelector("td.cart_quantity > button")).getText().trim();
        String actualTotal = rowElement.findElement(By.cssSelector("td.cart_total > p")).getText().trim();

        System.out.println("✅ Checking details for: " + productName);
        System.out.println("Expected → Price: " + expectedPrice + ", Quantity: " + expectedQuantity + ", Total: " + expectedTotal);
        System.out.println("Actual   → Price: " + actualPrice + ", Quantity: " + actualQuantity + ", Total: " + actualTotal);

        return actualPrice.equals(expectedPrice)
                && actualQuantity.equals(expectedQuantity)
                && actualTotal.equals(expectedTotal);
    }
}

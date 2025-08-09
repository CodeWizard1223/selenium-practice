package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    private final By allProductsHeader = By.xpath("//h2[contains(text(), 'All Products')]");
    private final By searchedProducts = By.xpath("//h2[contains(text(), 'Searched Products')]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");

    private final By productInfo = By.cssSelector(".productinfo.text-center");
    private final By productItem = By.cssSelector(".single-products");
    private final By productOverlay = By.cssSelector(".product-overlay"); // ⬅️ overlay that appears after hover
    private final By overlayAddToCart = By.cssSelector(".product-overlay a.add-to-cart"); // ⬅️ click HERE

    // Header / navigation
    private final By headerViewCartLink = By.cssSelector("a[href='/view_cart']");

    // Modal and buttons inside it
    private final By cartModal = By.cssSelector("#cartModal"); // ⬅️ strict selector for the specific modal
    private final By continueShoppingButton = By.cssSelector("#cartModal button.btn.btn-success.close-modal.btn-block");
    private final By modalViewCartButton = By.cssSelector("#cartModal a[href='/view_cart']");
    private final By modalBackdrop = By.cssSelector(".modal-backdrop");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /* -------------------- Helpers -------------------- */

    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    private void realHover(WebElement el) {
        new Actions(driver).moveToElement(el).pause(Duration.ofMillis(400)).perform();
        // safety – also trigger JS mouseover (some UIs require it)
        String js = "var ev=new MouseEvent('mouseover',{bubbles:true});arguments[0].dispatchEvent(ev);";
        ((JavascriptExecutor) driver).executeScript(js, el);
    }

    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    private WebElement waitVisible(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitForCartModalVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(cartModal));
    }

    public void waitForModalToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.invisibilityOfElementLocated(modalBackdrop));
    }

    /* -------------------- Page API -------------------- */

    public boolean isAllProductsHeaderVisible() {
        return driver.findElement(allProductsHeader).isDisplayed();
    }

    public boolean isSearchedProductsVisible() {
        return driver.findElement(searchedProducts).isDisplayed();
    }

    public void searchForProduct(String product) {
        driver.findElement(searchInput).sendKeys(product);
        driver.findElement(searchButton).click();
    }

    public boolean isAllProductsVisible() {
        return !driver.findElements(productInfo).isEmpty();
    }

    public void hoverOverProductByIndex(int index) {
        List<WebElement> products = driver.findElements(productItem);
        if (products.size() <= index) {
            throw new RuntimeException("Product with index " + index +
                    " was not found. Found count: " + products.size());
        }
        WebElement product = products.get(index);
        scrollIntoView(product);
        realHover(product);
    }

    public boolean isAddToCartButtonVisibleForProduct(int index) {
        try {
            List<WebElement> products = driver.findElements(productItem);
            if (products.size() <= index) return false;
            WebElement product = products.get(index);
            scrollIntoView(product);
            realHover(product);

            // wait until the overlay for THIS product is actually displayed
            WebElement overlay = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> {
                        WebElement o = product.findElement(productOverlay);
                        return o.isDisplayed() ? o : null;
                    });

            WebElement btn = product.findElement(overlayAddToCart);
            return btn.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    /**
     * Robustly adds a product to the cart (by index in the grid).
     * Scrolls, hovers, waits for the overlay to be displayed, clicks the overlay "Add to cart" button, waits for modal.
     * Retries up to 3 times for flaky UIs.
     */
    public void clickToAddToCartProductByIndex(int index) {
        List<WebElement> products = driver.findElements(productItem);
        if (products.size() <= index) {
            throw new RuntimeException("Product with index " + index +
                    " was not found. Found count: " + products.size());
        }

        WebElement product = products.get(index);

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                scrollIntoView(product);
                realHover(product);

                // wait for overlay inside this product card
                WebElement overlay = new WebDriverWait(driver, Duration.ofSeconds(6))
                        .until(d -> {
                            WebElement o = product.findElement(productOverlay);
                            return o.isDisplayed() ? o : null;
                        });

                WebElement addBtn = product.findElement(overlayAddToCart);

                // small wait for clickability (sometimes overlay is still animating)
                new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.elementToBeClickable(addBtn));

                jsClick(addBtn);              // JS click is most reliable for overlay elements
                waitForCartModalVisible();    // confirm that click was successful
                return;
            } catch (Exception e) {
                if (attempt == 3) {
                    throw new RuntimeException("Could not add product with index " + index +
                            " even after 3 attempts.", e);
                }
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
    }

    /** Clicks "Continue Shopping" in the modal + waits for backdrop to disappear. */
    public void clickToContinueShoppingButton() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(cartModal));

        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.presenceOfElementLocated(continueShoppingButton));
        jsClick(button);

        waitForModalToDisappear();
    }

    /** Prefers "View Cart" in modal; if modal is not open, uses header link. */
    public void clickToViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        // 1) Try the "View Cart" button in the modal (after adding a product)
        try {
            WebElement modalBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(modalViewCartButton));
            jsClick(modalBtn);
            return;
        } catch (TimeoutException ignore) {
            // modal is not open, try the header link
        }

        // 2) Fallback: "Cart" link in the header (/view_cart)
        WebElement headerLink = wait.until(ExpectedConditions.presenceOfElementLocated(headerViewCartLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", headerLink);
        wait.until(ExpectedConditions.elementToBeClickable(headerLink));
        headerLink.click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    private final By allProductsHeader = By.xpath("//h2[contains(text(), 'All Products')]");

    private final By searchedProducts = By.xpath("//h2[contains(text(), 'Searched Products')]");

    private final By searchInput = By.id("search_product");

    private final By searchButton = By.id("submit_search");

    private final By productInfo = By.cssSelector(".productinfo.text-center");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

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
        List<WebElement> products = driver.findElements(productInfo);
        return !products.isEmpty();
    }
}

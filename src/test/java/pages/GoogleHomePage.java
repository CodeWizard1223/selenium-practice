package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends BasePage {

    private By searchBox = By.name("q");

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.google.com");
    }

    public void search(String query) {
        WebElement box = waitForElement(searchBox);
        box.sendKeys(query);
        box.sendKeys(Keys.ENTER);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}

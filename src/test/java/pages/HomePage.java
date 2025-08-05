package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");

    private final By homeLink = By.xpath("//a[contains(text(),'Home')]");

    private final By productLink = By.xpath("//a[contains(text(),'Products')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://automationexercise.com/");
    }

    public boolean isHomeVisible() {
        return driver.findElement(homeLink).isDisplayed();
    }

    public void clickSignupLogin() {
        click(signupLoginLink);
    }

    public void clickProducts() {
        click(productLink);
    }
}

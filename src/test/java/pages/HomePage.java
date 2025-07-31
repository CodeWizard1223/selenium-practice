package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://automationexercise.com/");
    }

    public void removeOverlays() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("let overlay = document.querySelector('.fc-dialog-overlay'); if (overlay) overlay.remove();");
        js.executeScript("let root = document.querySelector('.fc-consent-root'); if (root) root.remove();");
    }

    public void clickSignupLogin() {
        click(signupLoginLink);
    }
}

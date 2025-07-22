package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSignupLoginLink() {
        return driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
    }

    public void clickSignupLogin() {
        getSignupLoginLink().click();
    }
}

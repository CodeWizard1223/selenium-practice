package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

public class SignupLoginTest {

    @Test
    public void testClickLoginRedirectsToLoginPage() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        HomePage homePage = new HomePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            js.executeScript("let overlay = document.querySelector('.fc-dialog-overlay'); if (overlay) overlay.remove();");
            js.executeScript("let root = document.querySelector('.fc-consent-root'); if (root) root.remove();");

            System.out.println("Overlay removed");

            Thread.sleep(1000);

            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Signup / Login')]")));
            homePage.clickSignupLogin();

            Thread.sleep(1000);

            String url = driver.getCurrentUrl();
            Assertions.assertTrue(url.contains("login"));

        } catch (Exception e) {
            System.out.println("Test failed " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

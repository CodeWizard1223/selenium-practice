package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AutomationExerciseTest {

    @Test
    public void TestClickSignupLogin() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Skry overlay a consent popup
            js.executeScript("let overlay = document.querySelector('.fc-dialog-overlay'); if (overlay) overlay.remove();");
            js.executeScript("let root = document.querySelector('.fc-consent-root'); if (root) root.remove();");

            System.out.println("Overlay removed.");

            // Čakaj kým overlay zmizne aj v DOM (pre istotu)
            Thread.sleep(1000);

            // Teraz klikni na Signup/Login
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Signup / Login')]")));
            loginLink.click();
            System.out.println("Clicked on login.");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

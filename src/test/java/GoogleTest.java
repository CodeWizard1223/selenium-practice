import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/sabinajuhasova/Desktop/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Title is: " + title);
        assertTrue(title.contains("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

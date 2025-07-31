package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleHomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleTest {

    private WebDriver driver;
    private GoogleHomePage google;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/sabinajuhasova/Desktop/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        google = new GoogleHomePage(driver);
    }

    @Test
    void testGoogleTitle() {
        google.open();
        String title = google.getTitle();
        System.out.println("Title is: " + title);
        assertTrue(title.contains("Google"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

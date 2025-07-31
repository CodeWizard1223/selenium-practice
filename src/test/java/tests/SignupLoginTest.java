package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignupLoginTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    void testClickLoginRedirectsToLoginPage() {
        homePage.open();
        homePage.removeOverlays();
        homePage.clickSignupLogin();

        String url = driver.getCurrentUrl();
        assertTrue(url.contains("login"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

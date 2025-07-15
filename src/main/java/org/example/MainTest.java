package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainTest {
    public static void main(String[] args) {
        // Nastavenie cesty k chromedriveru
        System.setProperty("webdriver.chrome.driver", "/Users/sabinajuhasova/Desktop/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        System.out.println("Title stránky: " + driver.getTitle());

        driver.quit();
    }
}
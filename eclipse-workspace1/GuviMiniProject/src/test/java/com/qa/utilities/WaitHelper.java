package com.qa.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitHelper {

	  // Static WebDriver and WebDriverWait
    public static WebDriver driver;
    public static WebDriverWait wait;

    // Initialize the static WebDriver and WebDriverWait
    public static void init(WebDriver driver) {
        WaitHelper.driver = driver;  // Set the WebDriver
        WaitHelper.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Set WebDriverWait
    }

    // Explicit wait method for WebElement
    public static void explicitWait(WebDriver driver, int seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    // Method for Implicit Wait
    public static void implicitWait(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}

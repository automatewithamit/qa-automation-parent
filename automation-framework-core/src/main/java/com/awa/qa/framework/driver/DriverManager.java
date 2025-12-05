package com.awa.qa.framework.driver;

import com.awa.qa.framework.config.ConfigManager;
import com.awa.qa.framework.exceptions.FrameworkException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void initDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browserName = ConfigManager.get("browser");
        BrowserType browserType;
        try {
            browserType = BrowserType.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new FrameworkException("Invalid browser configured: " + browserName, ex);
        }

        WebDriver driver = WebDriverFactory.createInstance(browserType);

        driver.manage().window().maximize();
        int implicitWait = ConfigManager.getInt("implicitWait");
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(implicitWait));

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new FrameworkException("WebDriver is not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}

package com.awa.qa.framework.base;

import com.awa.qa.framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {



    /**
     * Application-specific base URL.
     * Each application test module (Yatra, MakeMyTrip etc.)
     * will provide its own implementation.
     */
    protected abstract String getBaseUrl();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();

        String baseUrl = getBaseUrl();
        if (baseUrl != null && !baseUrl.isBlank()) {
            DriverManager.getDriver().get(baseUrl);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

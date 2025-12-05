package com.awa.qa.framework.driver;

import com.awa.qa.framework.exceptions.FrameworkException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver createInstance(BrowserType browserType) {
        return switch (browserType) {
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            case CHROME -> new ChromeDriver(); // default
            default -> throw new FrameworkException("Unsupported browser type: " + browserType);
        };
    }
}

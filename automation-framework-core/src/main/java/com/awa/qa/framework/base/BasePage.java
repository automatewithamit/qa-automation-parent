package com.awa.qa.framework.base;

import com.awa.qa.framework.config.ConfigManager;
import com.awa.qa.framework.driver.DriverManager;
import com.awa.qa.framework.utils.Locator;
import com.awa.qa.framework.utils.LocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();   // thread-safe
        int timeout = ConfigManager.getInt("explicitWait");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // Convert our Locator → Selenium By (Selenium stays in core)
    private By toBy(Locator locator) {
        return switch (locator.getType()) {
            case ID -> By.id(locator.getValue());
            case NAME -> By.name(locator.getValue());
            case CSS -> By.cssSelector(locator.getValue());
            case XPATH -> By.xpath(locator.getValue());
            case CLASS_NAME -> By.className(locator.getValue());
            case TAG_NAME -> By.tagName(locator.getValue());
            case LINK_TEXT -> By.linkText(locator.getValue());
            case PARTIAL_LINK_TEXT -> By.partialLinkText(locator.getValue());
        };
    }

    protected WebElement waitForVisible(Locator locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(toBy(locator))
        );
    }

    protected WebElement waitForClickable(Locator locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(toBy(locator))
        );
    }

    protected void click(Locator locator) {
        waitForClickable(locator).click();
    }

    protected void type(Locator locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(Locator locator) {
        return waitForVisible(locator).getText();
    }
    protected String getPageTitle() {
        return driver.getTitle();
    }
}

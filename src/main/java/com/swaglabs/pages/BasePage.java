package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        waitForElement(locator).click();
        try {
            Thread.sleep(1000); // Wait 1 second after click
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.click(); // focus first so React registers onFocus/onChange
        element.clear();
        element.sendKeys(text);
        try {
            Thread.sleep(500); // Wait 0.5 seconds after typing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void acceptAlertIfPresent() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {
        }
    }
}

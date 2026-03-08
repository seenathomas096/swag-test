package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {
    
    private By pageTitle = By.className("title");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnStep1() {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }

    public boolean isOnStep2() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public boolean isOnCompletePage() {
        return isElementDisplayed(completeHeader);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, postalCode);
    }

    public void clickContinue() {
        WebElement btn = waitForElement(continueButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public void clickFinish() {
        WebElement btn = waitForElement(finishButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getCompleteMessage() {
        return getText(completeHeader);
    }

    public void clickCancel() {
        click(By.id("cancel"));
    }

    public String getOrderTotal() {
        return getText(By.className("summary_total_label"));
    }
}

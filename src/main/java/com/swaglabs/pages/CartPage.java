package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    
    private By pageTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(cartItemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    public void removeItem(String productName) {
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        By removeLocator = By.id(buttonId);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeLocator));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeLocator));
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

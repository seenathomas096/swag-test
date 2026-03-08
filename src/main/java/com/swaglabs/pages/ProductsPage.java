package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {
    
    private By pageTitle = By.className("title");
    private By productItems = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductsPage() {
        return isElementDisplayed(pageTitle) && getText(pageTitle).equals("Products");
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductPrices() {
        return driver.findElements(productPrices)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void sortBy(String option) {
        Select select = new Select(waitForElement(sortDropdown));
        select.selectByVisibleText(option);
        try {
            Thread.sleep(500); // Wait 0.5 seconds after sorting for visibility
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getCartCount() {
        java.util.List<org.openqa.selenium.WebElement> badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? "0" : badges.get(0).getText();
    }

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(By.id(buttonId));
    }

    public void clickCart() {
        WebElement cartEl = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(cartLink));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", cartEl);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("cart.html"));
    }

    public void logout() {
        click(By.id("react-burger-menu-btn"));
        click(By.id("logout_sidebar_link"));
    }

    public void clickProductName(String displayName) {
        driver.findElements(productNames).stream()
                .filter(e -> e.getText().equals(displayName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + displayName))
                .click();
        try { Thread.sleep(1000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
    }

    public boolean isProductDetailDisplayed() {
        return isElementDisplayed(By.className("inventory_details_name"));
    }
}

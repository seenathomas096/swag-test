package com.swaglabs.tests;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);
        productsPage = new ProductsPage(driver);
    }

    @Test(description = "TC-004: Verify products are displayed on the page")
    public void testProductsDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(productsPage.isOnProductsPage(), "Should be on Products page");
        Assert.assertEquals(productsPage.getProductCount(), 6, "SauceDemo should display 6 products");
    }

    @Test(description = "TC-005: Verify products can be sorted by Price low to high")
    public void testSortByPriceLowToHigh() throws InterruptedException {
        productsPage.sortBy("Price (low to high)");
        Thread.sleep(1000);
        List<String> prices = productsPage.getProductPrices();
        Assert.assertFalse(prices.isEmpty(), "Price list should not be empty");
        double first = Double.parseDouble(prices.get(0).replace("$", ""));
        double last  = Double.parseDouble(prices.get(prices.size() - 1).replace("$", ""));
        Assert.assertTrue(first <= last, "Prices should be in ascending order");
    }

}


package com.swaglabs.tests;

import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);
        productsPage = new ProductsPage(driver);
    }

    @Test(description = "TC-006: Verify adding a single product to cart updates badge")
    public void testAddToCart() throws InterruptedException {
        productsPage.addProductToCart("sauce-labs-backpack");
        Thread.sleep(1000);
        Assert.assertEquals(productsPage.getCartCount(), "1", "Cart badge should show 1");
    }

    @Test(description = "TC-007: Verify viewing cart contents after adding a product")
    public void testViewCart() throws InterruptedException {
        productsPage.addProductToCart("sauce-labs-backpack");
        Thread.sleep(1000);
        productsPage.clickCart();
        Thread.sleep(1000);
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isOnCartPage(), "Should be on Cart page");
        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should have 1 item");
    }

    @Test(description = "TC-008: Verify removing item from cart empties the cart")
    public void testRemoveFromCart() throws InterruptedException {
        productsPage.addProductToCart("sauce-labs-backpack");
        Thread.sleep(1000);
        productsPage.clickCart();
        Thread.sleep(1000);
        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem("sauce-labs-backpack");
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.getItemCount(), 0, "Cart should be empty after removal");
    }

}


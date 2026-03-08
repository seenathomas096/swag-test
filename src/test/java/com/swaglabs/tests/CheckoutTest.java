package com.swaglabs.tests;

import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.CheckoutPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setupCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart("sauce-labs-backpack");
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        productsPage.clickCart();
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } // wait for cart page

        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(description = "TC-009: Verify checkout info validation shows error on empty fields")
    public void testCheckoutValidation() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.isOnStep1(), "Should be on Checkout step 1");
        checkoutPage.clickContinue();
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Validation error should be shown for empty fields");
    }

    @Test(description = "TC-010: Verify complete end-to-end purchase flow")
    public void testCompletePurchase() throws InterruptedException {
        checkoutPage.fillInformation("John", "Doe", "12345");
        Thread.sleep(1000);
        checkoutPage.clickContinue();
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.isOnStep2(), "Should be on Checkout overview (step 2)");
        Assert.assertFalse(checkoutPage.getOrderTotal().isEmpty(), "Order total should be displayed");
        checkoutPage.clickFinish();
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.isOnCompletePage(), "Should see order confirmation");
        Assert.assertEquals(checkoutPage.getCompleteMessage(), "Thank you for your order!");
    }

}


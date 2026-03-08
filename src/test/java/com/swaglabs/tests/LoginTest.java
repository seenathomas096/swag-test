package com.swaglabs.tests;

import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC-001: Verify successful login with valid credentials")
    public void testValidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);
        Thread.sleep(1000);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isOnProductsPage(), "Should be on Products page after valid login");
    }

    @Test(description = "TC-002: Verify login fails with invalid credentials")
    public void testInvalidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "invalid_password");
        Thread.sleep(1000);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
    }

    @Test(description = "TC-003: Verify locked out user cannot login")
    public void testLockedOutUser() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", VALID_PASSWORD);
        Thread.sleep(1000);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Locked out error should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("locked out"),
                "Error should mention locked out");
    }

}


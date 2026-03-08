package com.swaglabs.tests;

import com.swaglabs.utils.DriverManager;
import com.swaglabs.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    protected static final String VALID_USER = "standard_user";
    protected static final String VALID_PASSWORD = "secret_sauce";

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(BASE_URL);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); } // Wait 1 second after page load
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TestUtils.captureScreenshot(driver, result.getName());
        }
        DriverManager.quitDriver();
    }
}

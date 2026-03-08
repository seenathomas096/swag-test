package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String screenshotPath = "reports/screenshots/" + fileName;
            
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotPath);
            
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + screenshotPath);
            
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
            return null;
        }
    }
}

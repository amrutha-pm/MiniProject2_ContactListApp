package com.qa.utils;

import com.aventstack.extentreports.Status;
import com.qa.base.ProjectSpecificationMethods;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends ProjectSpecificationMethods implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if (test != null) {
            test.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(Status.PASS, "✅ " + result.getMethod().getMethodName() + " passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.fail(result.getThrowable()); // Log the error for the failed test
        }

        if (driver == null) {
            if (test != null) {
                test.log(Status.FAIL, "Driver not initialized. Screenshot skipped.");
            }
            return; // Exit if driver is not initialized
        }

        try {
            // Capture a screenshot on failure
            String screenshotPath = screenShot(result.getMethod().getMethodName());
            if (test != null && screenshotPath != null) {
                test.fail("❌ Failure Screenshot:").addScreenCaptureFromPath(screenshotPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (test != null) {
                test.log(Status.FAIL, "Screenshot capture failed: " + e.getMessage());
            }
        }

        // Handling alerts (if any)
        if (isAlertPresent()) {
            String alertText = getAlertText();
            handleAlert(); // Accept the alert or handle as needed
            if (test != null) {
                test.log(Status.INFO, "Alert handled: " + alertText);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test != null) {
            test.log(Status.SKIP, "⚠️ Test skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional setup
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // Ensure the Extent report is written out at the end
        }
    }

    // Check if an alert is present
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert(); // Attempt to switch to the alert
            return true; // Alert is present
        } catch (NoAlertPresentException e) {
            return false; // No alert is present
        }
    }

    // Get the alert message
    private String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText(); // Return the alert text
    }

    // Handle the alert (accept, dismiss, etc.)
    private void handleAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept(); // Accept the alert, you can also use alert.dismiss() or alert.sendKeys()
    }
}

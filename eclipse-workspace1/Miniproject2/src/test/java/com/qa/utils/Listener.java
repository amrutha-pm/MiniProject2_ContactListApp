package com.qa.utils;
import com.qa.utils.Listener;  // Make sure the import is correct

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.qa.base.ProjectSpecificationMethods;

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
            test.fail(result.getThrowable());
        }

        if (driver == null) {
            if (test != null) {
                test.log(Status.FAIL, "Driver not initialized. Screenshot skipped.");
            }
            return;
        }

        try {
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
        extent.flush();
    }

//Check if an alert is present
private boolean isAlertPresent() {
 try {
     driver.switchTo().alert(); // Attempt to switch to the alert
     return true; // Alert is present
 } catch (NoAlertPresentException e) {
     return false; // No alert is present
 }
}

//Get the alert message
private String getAlertText() {
 Alert alert = driver.switchTo().alert();
 return alert.getText(); // Return the alert text
}

//Handle the alert (accept, dismiss, etc.)
private void handleAlert() {
 Alert alert = driver.switchTo().alert();
 alert.accept(); // Accept the alert, you can also use alert.dismiss() or alert.sendKeys()
}

}
	


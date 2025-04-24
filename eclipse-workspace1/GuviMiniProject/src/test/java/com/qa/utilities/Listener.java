/*
package com.qa.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.qa.base.ProjectSpecificationMethods;

public class Listener extends ProjectSpecificationMethods implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success in the report
        test.log(Status.PASS, "Test case passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure in the report
        test.fail(result.getThrowable());

        // Check if driver is initialized, if not, launch the browser
        if (driver == null) {
            launchBrowser("chrome", "http://your-base-url.com");  // Provide default values for browser and URL
        }

        // Capture screenshot on failure
        String screenShot = "";
        try {
            if (driver != null) {
                // If driver is initialized, capture screenshot
                screenShot = screenShot(result.getMethod().getMethodName());
            } else {
                test.log(Status.FAIL, "Driver is not initialized, cannot capture screenshot");
            }
        } catch (IOException e) {
            // Log the exception if screenshot capture fails
            e.printStackTrace();
            test.log(Status.FAIL, "Screenshot capture failed: " + e.getMessage());
        }

        // Add the screenshot to the report
        if (!screenShot.isEmpty()) {
            test.addScreenCaptureFromPath(screenShot, "Failure screenshot");
        } else {
            test.log(Status.INFO, "No screenshot available for failure");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Called when the test starts
        test.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Called when the test is skipped
        test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // Called before any test method is invoked
    }

    @Override
    public void onFinish(ITestContext context) {
        // Called after all test methods are executed
    }
}


*/

package com.qa.utilities;

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

	
 /*   @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(Status.PASS, "✅ Test passed");
        }
    }*/
    
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


 /*   @Override
    public void onTestSuccess(ITestResult result) {
        // Log success in the report
        test.log(Status.PASS, "Test case passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        if (driver == null) {
            launchBrowser("chrome", "http://your-base-url.com");
        }

        String screenShot = "";
        try {
            if (driver != null) {
                screenShot = screenShot(result.getMethod().getMethodName());
            } else {
                test.log(Status.FAIL, "Driver is not initialized, cannot capture screenshot");
            }
        } catch (IOException e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Screenshot capture failed: " + e.getMessage());
        }

        if (!screenShot.isEmpty()) {
            // ✅ Updated line for proper display
            test.fail("Failure Screenshot:").addScreenCaptureFromPath(screenShot);
            // or: test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());
        } else {
            test.log(Status.INFO, "No screenshot available for failure");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Called when the test starts
        test.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Called when the test is skipped
        test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // Called before any test method is invoked
    }

    @Override
    public void onFinish(ITestContext context) {
        // Called after all test methods are executed
    }
}
*/



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
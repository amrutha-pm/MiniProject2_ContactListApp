package com.qa.tests;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_001_LoginPageTest extends ProjectSpecificationMethods {

    //LoginPage loginPage;

    @BeforeTest
    public void setup() {
        sheetname = "Login";  // This is the sheet name you are reading from the Excel file
        testname = "Login Test";
        testdescription = "Testing the Login functionality with valid and invalid data";
        testauthor = "Amrutha";
        testcategory = "Regression";
    }
    
    // Constructor to initialize the LoginPage
    public  TC_001_LoginPageTest() {
    	System.out.println("TC_001:LoginPage testcases are executing ");
    LoginPage    loginPage = new LoginPage(driver); // Pass the driver to LoginPage
    }

    @Test
    public void verifyLoginButtonVisibility() {
    	LoginPage loginPage = new LoginPage(driver);
        boolean isVisible = loginPage.isLoginButtonVisible();
        Assert.assertTrue(isVisible, "Login button should be visible on the login page.");
        System.out.println("✅ Login Button is visible");
    }

 /*   @Test
    public void verifyLoginFunctionality() throws InterruptedException {
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();

        // Verifying login success
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "AdactIn.com - Search Hotel", "Login failed. Title mismatch!");
    }
*/
    
    @Test(dataProvider = "readData")
    public void verifyLoginFunctionality(String username, String password, String testType, String expectedMessage) throws InterruptedException {
    LoginPage  loginPage = new LoginPage(driver);

        // Enter username and password from the data provider
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        Thread.sleep(2000); // Add a small delay for actions to complete
        loginPage.clickLoginButton();

        // Capture the actual page title after login attempt
        String actualTitle = driver.getTitle();
        
        // Perform assertions based on the test type and expected message
        if(testType.equalsIgnoreCase("Valid data")) {
            // Valid login scenario
            Assert.assertEquals(actualTitle, expectedMessage, "Login failed for valid data.");
        } else if(testType.equalsIgnoreCase("Invalid username and password")) {
            // Invalid credentials scenario
            Assert.assertTrue(driver.getPageSource().contains(expectedMessage), "Error message mismatch for invalid credentials.");
        } else if(testType.equalsIgnoreCase("EmptyPassword")) {
            // Empty password scenario
            Assert.assertTrue(driver.getPageSource().contains(expectedMessage), "Error message for empty password mismatch.");
        } else if(testType.equalsIgnoreCase("EmptyUsername")) {
            // Empty username scenario
            Assert.assertTrue(driver.getPageSource().contains(expectedMessage), "Error message for empty username mismatch.");
        }
    }
}

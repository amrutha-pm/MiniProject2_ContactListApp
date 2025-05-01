package com.qa.tests;

import java.util.NoSuchElementException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class TC_002_LoginTest extends ProjectSpecificationMethods {

	@BeforeTest // data connection is reading data here
	public void setup() {
		sheetname = "LoginTestSheet"; // sheetname variable is common ,so utility class need to declare it as public
		testname = "Login Test";
		testdescription = "Testing the Login functionality with valid invalid data";
		testauthor = "Amrutha";
		testcategory = "Regression";
	}

	@Test(priority = 0)
	public void verifyLoginButtonVisibility() {
		LoginPage loginPage = new LoginPage(driver);
		boolean isVisible = loginPage.isLoginButtonVisible();
		
		Assert.assertTrue(isVisible, "Login button should be visible on the login page.");
		System.out.println("✅ Login Button is visible");
	}
	
/*	
	 // Test method that uses the DataProvider
    @Test(dataProvider = "readData")
    public void verifyLoginMessage(String email, String password, String testtype,String expectedmessage) {
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.enterEmailId(email)
                .enterpassword(password)
                .clickSubmitbtn();

if (loginPage.isContactListHeaderVisible()) {
    // Get the actual header text
    String actualHeader = loginPage.getHeaderInfo();
    
    // Compare actual header text with the expected header text
    if (actualHeader.equals(expectedmessage)) {
        System.out.println("✅ Page redirect is correct. The header text is: " + actualHeader);
        Assert.assertTrue(true); // Pass the test if header matches expected
    } else {
        // If the header text doesn't match expected, print mismatch message and fail the test
        System.out.println("❌ Header text mismatch. Expected: " + expectedmessage + ", but found: " + actualHeader);
        Assert.fail("Header text does not match expected. Expected: " + expectedmessage + ", but found: " + actualHeader);
    }
} else {
    // In case the header is not visible, check if the failure was expected (i.e., invalid login)
    if (!expectedmessage.equalsIgnoreCase("Contact List")) {
        // If expected message is not "Contact List", this means login failure was expected
        System.out.println("❌ Login failed as expected. Expected message: " + expectedmessage);
        Assert.assertTrue(true); // Test passes because login failure was expected
    } else {
        // If login failed unexpectedly, print the error and fail the test
        System.out.println("❌ Login failed unexpectedly. Expected header: " + expectedmessage);
        Assert.fail("Login should have succeeded, but header was not visible.");
    }
}
}
    catch (NoSuchElementException e) {
        // Handle the case where the header element is not found
        System.out.println("❌ NoSuchElementException: Unable to locate the header element. Login might have failed.");
        if (!expectedmessage.equalsIgnoreCase("Contact List")) {
            System.out.println("❌ Login failed as expected. Expected message: " + expectedmessage);
            Assert.assertTrue(true); // Test passes because login failure was expected
        } else {
            System.out.println("❌ Unexpected login failure. Expected header: " + expectedmessage);
            Assert.fail("Login should have succeeded, but no header element found.");
        }
    } catch (Exception e) {
        // Handle any other unexpected exceptions
        System.out.println("❌ Unexpected error occurred: " + e.getMessage());
        Assert.fail("An unexpected error occurred: " + e.getMessage());
    }
}*/
	
	// Test method that uses the DataProvider
	@Test(dataProvider = "readData")
	public void verifyLoginMessage(String email, String password, String testtype, String expectedmessage) {
	    try {
	        LoginPage loginPage = new LoginPage(driver);

	        // Perform login
	        loginPage.enterEmailId(email)
	                .enterpassword(password)
	                .clickSubmitbtn();

	        // Check if the contact list header is visible after login
	        if (loginPage.isContactListHeaderVisible()) {
	            // Get the actual header text
	            String actualHeader = loginPage.getHeaderInfo();

	            // Compare actual header text with the expected header text
	            if (actualHeader.equals(expectedmessage)) {
	                System.out.println("✅ Page redirect is correct. The header text is: " + actualHeader);
	                System.out.println(" ✅ Login successfull");
	                Assert.assertTrue(true); // Pass the test if header matches expected
	            } else {
	                // If the header text doesn't match expected, print mismatch message and fail the test
	                System.out.println("❌ Header text mismatch. Expected: " + expectedmessage + ", but found: " + actualHeader);
	                Assert.fail("Header text does not match expected. Expected: " + expectedmessage + ", but found: " + actualHeader);
	            }
	        } else {
	            // In case the header is not visible, check if the failure was expected (i.e., invalid login)
	            if (!expectedmessage.equalsIgnoreCase("Contact List")) {
	                // If expected message is not "Contact List", this means login failure was expected
	                System.out.println("❌ Login failed as expected. Expected message: " + expectedmessage);
	                Assert.assertTrue(true); // Test passes because login failure was expected
	            } else {
	                // If login failed unexpectedly, print the error and fail the test
	                System.out.println("❌ Login failed unexpectedly. Expected header: " + expectedmessage);
	                Assert.fail("Login should have succeeded, but header was not visible.");
	            }
	        }

	    } catch (NoSuchElementException e) {
	        // Handle the case where the header element is not found
	        System.out.println("❌ NoSuchElementException: Unable to locate the header element. Login might have failed.");
	        if (!expectedmessage.equalsIgnoreCase("Contact List")) {
	            System.out.println("❌ Login failed as expected. Expected message: " + expectedmessage);
	            Assert.assertTrue(true); // Test passes because login failure was expected
	        } else {
	            System.out.println("❌ Unexpected login failure. Expected header: " + expectedmessage);
	            Assert.fail("Login should have succeeded, but no header element found.");
	        }
	    } catch (Exception e) {
	        // Handle any other unexpected exceptions
	        System.out.println("❌ Unexpected error occurred: ❌ Login Failed " + e.getMessage());
	        Assert.fail("An unexpected error occurred: " + e.getMessage());
	    }
	}
	

}
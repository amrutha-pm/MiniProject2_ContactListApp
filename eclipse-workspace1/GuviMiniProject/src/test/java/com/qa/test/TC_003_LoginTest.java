package com.qa.test;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utilities.UtilityClass;

public class TC_003_LoginTest extends ProjectSpecificationMethods{

	
	@BeforeTest
	public void setup() {
		
		sheetname="LoginData";
		testname="login Test";
		testdescription="Testing the Login page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
	}
/*	 @Test(priority=1)
	    public void verifyLoginWithModalIsDisplayed() {    
	        // Step 1: Click on the "Log in" button on the homepage
	        // Step 2: Assert the login modal appears correctly
		 
		 HomePage homePageobj = new HomePage();
		   LoginPage loginPage = homePageobj.clickLogin();    //clicks on login

	        Assert.assertTrue(
	            loginPage.isLoginModalDisplayed(),"❌ Login Modal did not appear or the title did not match 'Log in'.");
//loginModal.isDisplayed()
	        System.out.println("✅ Login modal is displayed with correct title.");
	    }
	 */
	
	
	
	@Test(priority = 1)
	public void verifyLoginWithModalIsDisplayed() {    
	    // Step 1: Click on the "Log in" button on the homepage
	    HomePage homePageobj = new HomePage();
	    LoginPage loginPage = homePageobj.clickLogin();  // Clicks on login

	    // Step 2: Assert that the login modal is displayed
	    boolean isModalDisplayed = loginPage.isLoginModalDisplayed();  // Assuming this method checks modal visibility

	    Assert.assertTrue(isModalDisplayed, "❌ Login Modal did not appear or the title did not match 'Log in'.");
	    
	    // Step 3: Optionally, check the title of the modal (if needed)
	    String modalTitle = loginPage.getLoginModalTitle();  // Assuming this method gets the title of the modal
	    Assert.assertEquals(modalTitle, "Log in", "❌ The modal title is incorrect.");

	    System.out.println("✅ Login modal is displayed with correct title.");
	}

	
/*@Test(dataProvider = "readData1")
	 
	 public void verifyLoginTest(String username,String password,String TestType,String ExpectedMessage) throws TimeoutException, IOException {
	    // Initialize homepage and navigate to login
	    HomePage homePageobj = new HomePage();
	    LoginPage loginPage = homePageobj.clickLogin();
loginPage. enterUsername(username)
		        .enterPassword(password)
		        .clickSubmitButton();

		    // Click login using JS if necessary
		 //   UtilityClass.JSClick(driver, loginButton);

		    // Take screenshot after clicking login
		    UtilityClass.screenShot("LoginAlert_" + username);
		    System.out.println("Screenshot taken for: " + username);

		    // Wait for alert
		    Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
		            .until(ExpectedConditions.alertIsPresent());

		    String alertText = alert.getText();
		    System.out.println("Alert text: " + alertText);

		    // Validate alert content
		    if (ExpectedMessage.equalsIgnoreCase("Login Success") && alertText.contains("Welcome")) {
		        System.out.println("✅ Login successful for: " + username);
		    } else if (ExpectedMessage.equalsIgnoreCase("Login Failed") && alertText.toLowerCase().contains("wrong") || alertText.toLowerCase().contains("invalid")) {
		        System.out.println("✅ Login failed as expected for: " + username);
		    } else {
		        System.out.println("❌ Unexpected result for: " + username);
		    }

		    alert.accept(); // Close the alert

		    // Final validation
		    Assert.assertTrue(alertText.contains(ExpectedMessage), "❌ Alert message mismatch!");
		}
}
	

*/

	 @Test(dataProvider = "readData1")
	    public void verifyLoginTest(String username, String password, String TestType, String ExpectedMessage) throws TimeoutException, IOException, InterruptedException {
	        // Initialize homepage and navigate to login
	        HomePage homePageobj = new HomePage();
	        LoginPage loginPage = homePageobj.clickLogin();  // Assuming clickLogin navigates to the login page
Thread.sleep(3000);
	        // Perform login actions
	        loginPage.enterUsername(username)
	                 .enterPassword(password)
	                 .clickSubmitButton();  // Assumes loginPage has methods for these actions

	        // Wait for the alert to appear and validate the alert message
	        String actualAlertMessage = loginPage.validateLogin(TestType, ExpectedMessage);  // Assuming validateLogin method is implemented in LoginPage

	        // Log the result of the alert validation
	        System.out.println("Alert message validated: " + actualAlertMessage);
	        test.info("Alert Message Shown: " + actualAlertMessage);  // Assuming you're using a test logger

	        // Check if the actual alert message matches the expected one
	        Assert.assertEquals(actualAlertMessage, ExpectedMessage, "Login message doesn't match the expected one!");

	        // If login is successful, we expect a successful login message
	        if (actualAlertMessage.equals("Login successful")) {
	            System.out.println("✅ Login was successful for TestType: " + TestType + ExpectedMessage);
	            test.info("Login successful for TestType: " + TestType);
	        }
	        // If login failed, we expect an error message (this might be different based on your application)
	        else if (actualAlertMessage.equals("Invalid username or password")) {
	            System.out.println("❌ Login failed for TestType: " + TestType + ExpectedMessage);
	            test.info("Login failed for TestType: " + TestType);
	        } else {
	            // Handle other cases, such as unexpected messages or timeouts
	            System.out.println("❌ Unexpected alert message: " + actualAlertMessage);
	            test.info("Unexpected alert message: " + actualAlertMessage);
	        }
	    }
	}
	/*working set data
	 *  @Test(dataProvider = "readData1")
	 
	 public void LverifyoginTest(String username,String password,String TestType,String ExpectedMessage) throws TimeoutException, IOException {
		 HomePage homePageobj = new HomePage();
		   LoginPage loginPage = homePageobj.clickLogin();    //clicks on login

		// Enter credentials and click on the login button
	        loginPage.enterUsername(username)
	                 .enterPassword(password)
	              .clickLoginButton();
// Click login using JS if necessary
		    UtilityClass.JSClick(driver, loginButton);

		    // Take screenshot after clicking login
		    UtilityClass.screenShot("LoginAlert_" + username);
		    System.out.println("Screenshot taken for: " + username);


			// Capture a screenshot before accepting the alert
			UtilityClass.screenShot("LoginAlert_" + username);  // Call your screenshot utility method
			System.out.println("Screenshot taken before accepting the alert.");

			// Wait for the alert to be present
			Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
			                .until(ExpectedConditions.alertIsPresent());

			// Get the alert text
			String alertText = alert.getText();
	        System.out.println("Alert text: " + alertText);

			// Handle alert based on expected message
			if (ExpectedMessage.equals("Login Success") && alertText.contains("Welcome")) {
			    System.out.println("✅ Login successful.");
			    alert.accept(); // Accept the alert if login is successful
			} else if (ExpectedMessage.equals("Login Failed") && alertText.contains("Invalid credentials")) {
			    System.out.println("✅ Login failed as expected.");
			    alert.accept(); // Accept the alert if login failed
			} else {
			    System.out.println("❌ Unexpected alert message: " + alertText);
			    alert.accept(); // Accept the alert even if unexpected
			}
			 Assert.assertTrue(alertText.contains(ExpectedMessage), "Alert message mismatch!");

		        // Log final status to console
		        System.out.println("Test for user " + username + " completed with result: " + ExpectedMessage);
	 }
}*/

package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.LogoutPage;

public class TC_003_LogoutTest extends ProjectSpecificationMethods{

	@BeforeTest // data connection is reading data here
	public void setup() {
		sheetname = "LoginTestSheet"; // sheetname variable is common ,so utility class need to declare it as public
		testname = "Login Test";
		testdescription = "Testing the Login functionality with valid invalid data";
		testauthor = "Amrutha";
		testcategory = "Regression";
	}
	
	@Test  //(dataProvider = "readData")

	public void verifyLogoutRedirectsToLoginPage() {   //(String email, String password, String testtype, String expectedmessage) {
	    // Assume you are already logged in
	  HomePage homePage = new HomePage(driver);
       LoginPage loginPage = new LoginPage(driver);

       // Perform login
       loginPage.enterEmailId("simiram@gmail.com")
               .enterpassword("simi123456")
               .clickSubmitbtn();

	LogoutPage logoutPage = new LogoutPage(driver);
	
	    // Ensure logout button is visible
	    Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "Logout button is not visible");

	    // Click on logout
	   logoutPage.clickLogout();

	    // Verify login button is visible again
	    Assert.assertTrue(logoutPage.isLoginButtonVisible(), "Page did not redirect to login page after logout");
	    System.out.println("✅ Successfully redirected to login page after logout.");
	}
}

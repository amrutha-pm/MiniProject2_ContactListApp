package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.LogoutPage;
import com.qa.pages.ProductInformationPage;
import com.qa.utilities.WaitHelper;

public class TC_007_LogoutTest extends ProjectSpecificationMethods{
	@BeforeTest
	public void setup() {
	
		testname="Purchase Test";
		testdescription="Testing the Payment and purchase user page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
		   WaitHelper.init(driver);
	}
	@Test
	public void verifyLogoutFunction() throws InterruptedException {
		 HomePage homePage = new HomePage();
		    LoginPage loginPage = homePage.clickLogin();

		    loginPage.enterUsername("Neelima@gmail.com");
		    loginPage.enterPassword("workbook");
		    loginPage.clickSubmitButton();

	      
		    
		    ProductInformationPage detailPage = new   ProductInformationPage();

	            Assert.assertTrue(detailPage.isWelcomeUserDisplayed(), "Login failed!");
		        Thread.sleep(5000);  // Wait for the login 
	
		        LogoutPage logoutPage = new LogoutPage(driver);
		       
		
		    // ✅ Assert that logout button is visible
		    Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "❌ Logout button is not visible");

		    System.out.println("✅ Logout button is visible.");

		    // 🖱 Click the logout button
		    logoutPage.clickLogoutButton();

		    // ✅ Assert that user is redirected to home page
		    Assert.assertTrue(logoutPage.isRedirectedToHomePage(), "❌ Logout failed - User is not redirected to Home Page");

		    System.out.println("✅ Logout successful - Redirected to Home Page");
		    
	}
}


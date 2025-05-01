package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;

public class TC_001_SignUpTest extends ProjectSpecificationMethods{

	
	@BeforeTest  //data connection is reading data here
	public void setup() {
		sheetname="SignUp"; //sheetname variable is common ,so utility class need to declare it as public 
	testname="SignUp Test";
	testdescription="Testing the signup functionality with valid invalid data";
	testauthor="Amrutha";
	  testcategory = "Regression"; // 🔴 This line was missing
	}
	
	
	@Test(priority=1)
	public void verifySignUpButtonNavigation() {
		HomePage homePage = new HomePage(driver);
		homePage.clickSignup();  // navigate to AddUserPage

		boolean isVisible = homePage.isSignUpButtonVisible();
		Assert.assertTrue(isVisible, "Sign Up button should be visible on Add User page.");
		
	}
	
	@Test(dataProvider = "readData")
	public void signupTest(String firstname,String lastname,String email,String password,String testtype,String expectedmessage) throws InterruptedException {
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		HomePage obj1 = new HomePage(driver);
		//System.out.println("Redirecting to Add user Page");
		obj1.clickSignup()
       

		.enterFirstName(firstname)  //insted of passing values like this am using data driven testing to read values from excel
		.enterLastName(lastname)
		.enterEmailId(email)
		.enterpassword(password)
		.clickSubmitbtn()
		
		.validateSignUp(testtype, expectedmessage);
		
	}
}



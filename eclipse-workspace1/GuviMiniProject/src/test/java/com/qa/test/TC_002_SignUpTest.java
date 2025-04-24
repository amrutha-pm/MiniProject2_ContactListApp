package com.qa.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.SignUp;
import com.qa.utilities.UtilityClass;

public class TC_002_SignUpTest extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() {
		
		sheetname="SignUp";
		testname="SignUp Test";
		testdescription="Testing the SignUp page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
		
	}
	
	 
	
	
@Test(dataProvider = "readData")
public void signUpTest(String username,String password,String TestType,String ExpectedMessage) {   //(reading test data from Excel and verifying the alert)
	 // 2. Print current URL to debug redirection
   
	HomePage homePageobj = new HomePage();
 SignUp signUp = homePageobj.clickSignUp();
   signUp.enterUsername(username)
       .enterPassword(password)
         .clickSubmitButton()
   	     .validateSignUp(TestType, ExpectedMessage);

   //String alertMessage = signUpPage.validateSignUp(TestType, ExpectedMessage);
   //System.out.println("Alert message validated: " + alertMessage);
   
   String actualAlertMessage = signUp.validateSignUp(TestType, ExpectedMessage);  //used object of signup page --doubt?
   test.info("Alert Message Shown: " + actualAlertMessage);

   System.out.println("✅ Alert validated for TestType: " + TestType);
   
}}
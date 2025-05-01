package com.qa.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.AddContactPage;
import com.qa.pages.ContactListPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class TC_004_ContactListTest extends ProjectSpecificationMethods{

	@BeforeTest  //data connection is reading data here
	public void setup() {
		//sheetname="SignUp"; //sheetname variable is common ,so utility class need to declare it as public 
	testname="SignUp Test";
	testdescription="Testing the signup functionality with valid invalid data";
	testauthor="Amrutha";
	  testcategory = "Regression"; // 🔴 This line was missing
	}
	
		@Test(priority=1)
		public void verifyAddnewcontactNavigation() throws InterruptedException {
		    // Step 1: Login
		    LoginPage loginPage = new LoginPage(driver);
		    loginPage.enterEmailId("simiram@gmail.com")
		             .enterpassword("simi123456")
		             .clickSubmitbtn();

		    // Step 2: Navigate to HomePage (after login)
		    ContactListPage contactPage = new ContactListPage(driver);

		    // Step 3: Verify the "Add New Contact" button is visible
		    Assert.assertTrue(contactPage.isAddNewContactButtonVisible(), "❌ 'Add New Contact' button is not visible.");

		    // Step 4: Click on "Add New Contact" and go to AddContactPage
		    AddContactPage addContactPage = contactPage.clickAddNewContactButton();
Thread.sleep(2000);
		    // Step 5: Assert that you're on AddContactPage by checking for a unique element
		    Assert.assertTrue(addContactPage.isContactFormVisible(), "❌ Failed to navigate to Add Contact Page.");
		    System.out.println("✅ Successfully navigated to Add Contact Page.");
		}
		
		
		
	/*	@Test(priority = 2)
		public void testContactsLastnameAlphabeticalOrder() throws InterruptedException {
		    // Log in first (you might need to adjust this depending on your app's login process)
		    LoginPage loginPage = new LoginPage(driver);
		    loginPage.enterEmailId("simiram@gmail.com")
		             .enterpassword("simi123456")
		             .clickSubmitbtn();

		    // Navigate to the contact list page
		    ContactListPage contactPage = new ContactListPage(driver);

		    // Get the list of last names from the contact list
		    List<String> lastNames = contactPage.getLastNames();

		    // Log last names for debugging
		    System.out.println("🔍 Last names from UI:");
		    for (String name : lastNames) {
		        System.out.println(" - " + name);
		    }

		    // Ensure that the list of last names is not empty
		    Assert.assertFalse(lastNames.isEmpty(), "❌ No last names found in the contact list.");

		    // Step 4: Create a copy and sort alphabetically
		    List<String> sortedLastNames = new ArrayList<>(lastNames);
		    Collections.sort(sortedLastNames);

		    // Log the sorted last names for debugging
		    System.out.println("✅ Expected alphabetical order:");
		    for (String name : sortedLastNames) {
		        System.out.println(" - " + name);
		    }

		    // Step 5: Assertion to check if the names are sorted alphabetically
		    Assert.assertEquals(lastNames, sortedLastNames, "❌ Contacts are not sorted alphabetically by last name.");

		    System.out.println("✅ [PASS] Contacts are sorted correctly.");

		    // Optionally, wait for 3 seconds to observe the results (for debugging)
		    Thread.sleep(3000);
		}*/
		@Test(priority = 2)
		public void testContactsAlphabeticalOrder() throws InterruptedException {
		    // Step 1: Setup WebDriver and navigate to the page
		    setup();

		    // Step 2: Login
		    LoginPage loginPage = new LoginPage(driver);
		    loginPage.enterEmailId("simiram@gmail.com")
		             .enterpassword("simi123456")
		             .clickSubmitbtn();

		    // Step 3: Go to Contact List
		    ContactListPage contactPage = new ContactListPage(driver);

		    // Step 4: Get full names from the UI and clean them
		    List<String> fullNames = contactPage.getFullNames().stream()
		        .map(String::trim)
		        .collect(Collectors.toList());

		    System.out.println("🔍 Full names from UI:");
		    fullNames.forEach(name -> System.out.println(" - " + name));

		    // Step 5: Create a sorted copy
		    List<String> sortedFullNames = new ArrayList<>(fullNames);
		    sortedFullNames.sort(String.CASE_INSENSITIVE_ORDER);  // Case-insensitive sort

		    System.out.println("✅ Expected alphabetical order:");
		    sortedFullNames.forEach(name -> System.out.println(" - " + name));

		    // Step 6: Assertion
		    Assert.assertEquals(fullNames, sortedFullNames, "❌ Contacts are not sorted alphabetically by full name.");

		    System.out.println("✅ [PASS] Contacts are sorted correctly by full name.");

		    // Step 7: Clean up
		    Thread.sleep(3000);
		    driver.quit();
		}

}

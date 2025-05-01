package com.qa.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.AddContactPage;
import com.qa.pages.ContactDetailsPage;
import com.qa.pages.ContactListPage;
import com.qa.pages.LoginPage;

public class TC_005_AddContactTest extends ProjectSpecificationMethods {

	@BeforeTest // data connection is reading data here
	public void setup() {
		sheetname = "AddContactSheet"; // sheetname variable is common ,so utility class need to declare it as public
		testname = "AddContact Test";
		testdescription = "Testing the  functionality of adding contacts with valid invalid data";
		testauthor = "Amrutha";
		testcategory = "Regression"; // 🔴 This line was missing
	}

	
	@Test(dataProvider = "readData")
	public void verifyAddingContactPage(String FirstName, String LastName, String DateofBirth, String Email,
			String Phone, String StreetAddress1, String StreetAddress2, String City, String StateorProvince,
			String PostalCode, String Country, String testtype, String expectedmessage) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId("simiram@gmail.com").enterpassword("simi123456").clickSubmitbtn();

		// Step 2: Navigate to HomePage (after login)
		ContactListPage contactPage = new ContactListPage(driver);

		// Step 3: Verify the "Add New Contact" button is visible
		Assert.assertTrue(contactPage.isAddNewContactButtonVisible(), "❌ 'Add New Contact' button is not visible.");

		// Step 4: Click on "Add New Contact" and go to AddContactPage
		AddContactPage addContactPage = contactPage.clickAddNewContactButton();
		Thread.sleep(2000);

		addContactPage.enterFirstName(FirstName).enterLastName(LastName).enterDateOfBirth(DateofBirth).enterEmail(Email)
				.enterPhone(Phone).enterStreetAddress1(StreetAddress1).enterStreetAddress2(StreetAddress2)
				.enterCity(City).enterStateOrProvince(StateorProvince).enterPostalCode(PostalCode).enterCountry(Country)

				.clickSubmit(); // it returns to contact list lsiting

//Step 7: After submission, verify redirection or success
		Thread.sleep(1500); // Wait for redirection


		if (testtype.equalsIgnoreCase("All Valid Data")) {
		    // ✅ Valid Data: Should redirect to contact list
		    Assert.assertTrue(contactPage.isContactListHeaderVisible(),
		            "❌ [Valid Data] Expected contact list header not visible.");
		    System.out.println("✅ [PASS] Contact added successfully. Message: " + expectedmessage);
		} else {
		    // ❌ Invalid Data Scenarios: Form should remain and error should be shown
		    Assert.assertTrue(addContactPage.isContactFormVisible(),
		            "❌ [Invalid Data] Contact form is not visible after submission.");

		    // Get actual error message from the UI
		    String actualError = addContactPage.getErrorMessage();
		    System.out.println("🔍 [INFO] Actual error message: " + actualError);

		    // Fail if no error is shown
		    if (actualError == null || actualError.trim().isEmpty()) {
		        Assert.fail("❌ No validation message shown. Expected: " + expectedmessage);
		    }

		    // Normalize and trim for reliable comparison
		    String normalizedExpected = expectedmessage.trim().toLowerCase();
		    String normalizedActual = actualError.trim().toLowerCase();

		    switch (testtype.trim().toLowerCase()) {
		        case "without mandatory filelds such as first and last names":
		            Assert.assertTrue(normalizedActual.contains("firstname") && normalizedActual.contains("lastname"),
		                    "❌ [Mandatory Fields] Expected first and last name validation. Actual: " + actualError);
		            
		            break;

		        case "already saved name, dob, and email":
                    // If data already exists, we expect a specific message about duplicates.
                 
                    Assert.assertTrue(normalizedActual.contains("data already exists"),
                            "❌ [Duplicate Data] Expected 'data already exists' message. Actual: " + actualError);
                    System.out.println("❌ [BUG] Duplicate Data is allowing. Please investigate the duplicate check functionality.");

                    break;

		        case "invalid dob format":
		            Assert.assertTrue(normalizedActual.contains("birthdate is invalid"),
		                    "❌ [DOB Format] Expected DOB format error. Actual: " + actualError);
		            break;

		        default:
		            Assert.assertTrue(normalizedActual.contains(normalizedExpected),
		                    "❌ [Generic Validation] Expected: " + expectedmessage + "\nActual: " + actualError);
		    }

		    System.out.println("✅ [PASS] Validation message matched for: " + testtype);
		    System.out.println("***********************");
		}
	}
	
		@Test(priority=1)
	    public void verifyAddingContactAndDisplayInList() throws InterruptedException {
	        // Step 1: Login
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.enterEmailId("simiram@gmail.com").enterpassword("simi123456").clickSubmitbtn();

	        // Step 2: Navigate to Add Contact Page
	        ContactListPage contactPage = new ContactListPage(driver);
	        AddContactPage addContactPage = contactPage.clickAddNewContactButton();

	        // Step 3: Add new contact
	        String firstName = "NithyaSree";
	        String lastName = "pm";
	        String dob = "1995-04-01";
	        String email = "sree@gmail.com";
	        String phone = "97867656";
            String StreetAddress1 = "D1-04";
String StreetAddress2 = "IIT";
String City = "chennai";
String State ="Chennai";
String postalcode ="670331";
String Country = "India";


	        addContactPage.enterFirstName(firstName)
	                .enterLastName(lastName)
	                .enterDateOfBirth(dob)
	                .enterEmail(email)
	                .enterPhone(phone)
                    .enterStreetAddress1(StreetAddress1)
                    .enterStreetAddress2(StreetAddress2)
                    .enterCity(City)
                    .enterStateOrProvince(State)
                    .enterPostalCode(postalcode)
                    .enterCountry(Country)
	        // Step 4: Submit the form and navigate to contact list page
	      //  contactPage = addContactPage.clickSubmit();
                   .clickSubmit();
	       // addContactPage.clickSubmit();
	     //  contactPage = new ContactListPage(driver); // Now you're properly setting the page object ,    // Step 4: Re-initialize the contact list page
           // Step 5: Verify the contact is added and displayed on the contact list page
	    //    Thread.sleep(2000); // Wait for page load (Optional)
	        driver.navigate().refresh();
	        contactPage = new ContactListPage(driver);//Step 4: Re-initialize the contact list page
	        // Step 4: Wait for page redirection (explicit wait)
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));

	        
	        // Check if the contact is present by email
	        boolean isContactPresent = contactPage.isContactPresentByEmail(email);
	        Thread.sleep(2000);
	        Assert.assertTrue(isContactPresent, "❌ Contact was not added successfully to the list.");

	        System.out.println("✅ [PASS] Contact added and displayed on the contact list.");
	        
	        // Optional Step 6: Verify clicking on the email navigates to the Contact Details page
	        ContactDetailsPage contactDetailsPage = contactPage.clickContactByEmail(email); // Click on the email to open details
	        Assert.assertTrue(contactDetailsPage.isHeaderVisible(), "❌ Contact Details page header not visible.");
	        System.out.println("✅ [PASS] Successfully navigated to the Contact Details page.");
	   
	        // Stay on the Contact Details page for 3 seconds
	        Thread.sleep(3000); // Wait for 3 seconds before the test finishes
	        System.out.println("✅ [PASS] Stayed on Contact Details page for 3 seconds.");
	    }
}
package com.qa.tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
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

public class TC_006_ContactDetailPageTest  extends ProjectSpecificationMethods {

	@BeforeTest // data connection is reading data here
	public void setup() {
		sheetname = "AddContactSheet"; // sheetname variable is common ,so utility class need to declare it as public
		testname = "ContactDetailPage Test";
		testdescription = "Testing the  functionality of  detail page naviagtion from contact list ,edit,add,deletion of the detailpage data";
		testauthor = "Amrutha";
		testcategory = "Regression"; // 🔴 This line was missing
	}
	
/*	@Test(priority = 1)
	public void verifyDeletingData() throws InterruptedException {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterEmailId("simiram@gmail.com")
	             .enterpassword("simi123456")
	             .clickSubmitbtn();

	    ContactListPage contactPage = new ContactListPage(driver);
	    AddContactPage addContactPage = contactPage.clickAddNewContactButton();

	    // Contact details
	    String firstName = "Haritha";
	    String lastName = "pm";
	    String dob = "1995-04-01";
	    String email = "gugulus@gmail.com";
	    String phone = "97867656";
	    String street1 = "D1-04", street2 = "IIT", city = "Chennai", state = "Chennai", postal = "670331", country = "India";

	    addContactPage.enterFirstName(firstName)
	                  .enterLastName(lastName)
	                  .enterDateOfBirth(dob)
	                  .enterEmail(email)
	                  .enterPhone(phone)
	                  .enterStreetAddress1(street1)
	                  .enterStreetAddress2(street2)
	                  .enterCity(city)
	                  .enterStateOrProvince(state)
	                  .enterPostalCode(postal)
	                  .enterCountry(country)
	                  .clickSubmit();
Thread.sleep(2000);
	    // Refresh and verify contact added
	  //  driver.navigate().refresh();
	    contactPage = new ContactListPage(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));

	    Assert.assertTrue(contactPage.isContactPresentByEmail(email), "❌ Contact was not added.");
	    System.out.println("✅ [PASS] Contact added.");

	    // Navigate to Contact Details
	    ContactDetailsPage contactDetailsPage = contactPage.clickContactByEmail(email);
	    Assert.assertTrue(contactDetailsPage.isHeaderVisible(), "❌ Contact Details page header not visible.");

	    // Step: Click Delete
	    contactDetailsPage.clickDeleteContactButton();

	    // Step: Handle Alert
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	    Assert.assertEquals(alert.getText(), "Are you sure you want to delete?", "❌ Unexpected alert text.");

	    System.out.println("✅ [PASS] Delete confirmation alert displayed.");
	    alert.accept(); // Confirm deletion

	    // Wait for redirection or update
	    driver.navigate().refresh();
	    contactPage = new ContactListPage(driver);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));

	    // Verify the contact is no longer present
	    boolean isDeleted = !contactPage.isContactPresentByEmail(email);
	    Assert.assertTrue(isDeleted, "❌ Contact was not deleted.");
	    System.out.println("✅ [PASS] Contact deleted successfully.");
	}
}*/
	  @Test(priority = 1)
	    public void verifyDeletingData() throws InterruptedException {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.enterEmailId("simiram@gmail.com")
	                 .enterpassword("simi123456")
	                 .clickSubmitbtn();

	        ContactListPage contactPage = new ContactListPage(driver);
	        AddContactPage addContactPage = contactPage.clickAddNewContactButton();

	        // Contact details
	        String firstName = "Haritha";
	        String lastName = "pm";
	        String dob = "1995-04-01";
	        String email = "gugulus@gmail.com";
	        String phone = "97867656";
	        String street1 = "D1-04", street2 = "IIT", city = "Chennai", state = "Chennai", postal = "670331", country = "India";

	        addContactPage.enterFirstName(firstName)
	                      .enterLastName(lastName)
	                      .enterDateOfBirth(dob)
	                      .enterEmail(email)
	                      .enterPhone(phone)
	                      .enterStreetAddress1(street1)
	                      .enterStreetAddress2(street2)
	                      .enterCity(city)
	                      .enterStateOrProvince(state)
	                      .enterPostalCode(postal)
	                      .enterCountry(country)
	                      .clickSubmit();
	        Thread.sleep(2000); // Wait for the contact to be added

	        // Refresh and verify contact added
	        contactPage = new ContactListPage(driver);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));

	        Assert.assertTrue(contactPage.isContactPresentByEmail(email), "❌ Contact was not added.");
	        System.out.println("✅ [PASS] Contact added.");

	        // Navigate to Contact Details
	        ContactDetailsPage contactDetailsPage = contactPage.clickContactByEmail(email);
	        Assert.assertTrue(contactDetailsPage.isHeaderVisible(), "❌ Contact Details page header not visible.");

	        // Step: Click Delete
	        contactDetailsPage.clickDeleteContactButton();
	        
	        // Step: Handle Alert
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        System.out.println("Alert Text: " + alert.getText()); // Print alert text for debugging
	        Assert.assertEquals(alert.getText(), "Are you sure you want to delete?", "❌ Unexpected alert text.");
	        System.out.println("✅ [PASS] Delete confirmation alert displayed.");
	        alert.accept(); // Confirm deletion
Thread.sleep(2000);
	        // Wait for redirection or update
	     //   driver.navigate().refresh();
driver.navigate().refresh();
	        contactPage = new ContactListPage(driver);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Contact List']")));

	        // Verify the contact is no longer present
	        boolean isDeleted = !contactPage.isContactPresentByEmail(email);
	        System.out.println("Is contact deleted? " + isDeleted); // Debugging line
	        Assert.assertTrue(isDeleted, "❌ Contact was not deleted.");
	        System.out.println("✅ [PASS] Contact deleted successfully.");
	    }
	}

package com.qa.tests;

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
import com.qa.pages.EditContactPage;
import com.qa.pages.LoginPage;

public class TC_007_EditContactDetailsTest  extends ProjectSpecificationMethods{

	
	@BeforeTest // data connection is reading data here
	public void setup() {
		//sheetname = "AddContactSheet"; // sheetname variable is common ,so utility class need to declare it as public
		testname = "EditContactDetailPage Test";
		testdescription = "Testing the  functionality of ,edit,add,deletion of the detailpage data";
		testauthor = "Amrutha";
		testcategory = "Regression"; // 🔴 This line was missing
	}
	/*@Test
    public void verifytEditContactDetailsAndRedirect() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailId("simiram@gmail.com").enterpassword("simi123456").clickSubmitbtn();

		// Step 2: Navigate to HomePage (after login)
		ContactListPage contactPage = new ContactListPage(driver);

		
	//	setup();
		
		   
        // Navigate to the edit contact page
        EditContactPage editContactPage = new EditContactPage(driver);

        // Define new contact details
        String newEmail = "newemail@example.com";
        String newPhone = "+91 9876543210";
        String newName = "John Doe";

        // Update the contact details
        editContactPage.updateContactDetails(newEmail, newPhone, newName);

        // Wait for the page to redirect (or use an explicit wait for an element that appears on the contact detail page)
        // Example: WebDriverWait wait = new WebDriverWait(driver, 10); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactDetailHeader")));

        // Verify that the page has redirected to the contact detail page and updated details are displayed
        boolean isUpdated = editContactPage.verifyUpdatedContactDetails(newEmail, newPhone, newName);

        // Assert the updated details are shown on the contact detail page
        Assert.assertTrue(isUpdated, "❌ Contact details are not updated or redirection failed.");

        System.out.println("✅ Contact details updated and redirected successfully.");

        driver.quit();
   
		
		
	}*/
	@Test
	public void verifyEditContactDetailsAndRedirect() throws InterruptedException {
	    // Step 1: Log in
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterEmailId("simiram@gmail.com")
	             .enterpassword("simi123456")
	             .clickSubmitbtn();

	    // Step 2: Navigate to Contact List
	    ContactListPage contactPage = new ContactListPage(driver);

	    // Step 3: Click the first contact in the list to go to its detail page
	    ContactDetailsPage detailPage = contactPage.clickFirstContactByName();


	    // Step 4: From detail page, navigate to the edit page
	    EditContactPage editContactPage = detailPage.clickEditContactButton();

	    // Step 5: Update contact details
	    String newEmail = "newemail@example.com";
	    String newPhone = "+91 98765432";
	    String newName = "John Doe";
	    editContactPage.updateContactDetails(newEmail, newPhone, newName);

	    // Step 6: Verify redirection and updated info on the detail page
	    boolean isUpdated = editContactPage.verifyUpdatedContactDetails(newEmail, newPhone, newName);

	    // Assert
	    Assert.assertTrue(isUpdated, "❌ Contact details not updated or not redirected properly.");
	    System.out.println("✅ Contact details updated and redirected successfully.");
	}

}

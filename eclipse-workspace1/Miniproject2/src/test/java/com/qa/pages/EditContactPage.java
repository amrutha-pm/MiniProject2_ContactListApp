package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.utils.UtilityClass;

public class EditContactPage extends ProjectSpecificationMethods{
	
	
	@FindBy(id="logout")
	WebElement LogOutButton;
	
	@FindBy(id="submit")
			WebElement EditsubmitButton;
	
	@FindBy(id="cancel")
	WebElement EditCancelButton;
	
	// --- Elements (Assume same IDs as Add Contact for this example) ---
    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "lastName") private WebElement lastNameInput;
    @FindBy(id = "birthdate") private WebElement birthdateInput;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "phone") private WebElement phoneInput;
    @FindBy(id = "street1") private WebElement street1Input;
    @FindBy(id = "street2") private WebElement street2Input;
    @FindBy(id = "city") private WebElement cityInput;
    @FindBy(id = "stateProvince") private WebElement stateInput;
    @FindBy(id = "postalCode") private WebElement postalCodeInput;
    @FindBy(id = "country") private WebElement countryInput;
    @FindBy(id = "submit") private WebElement submitButton; // May have different ID/text like "Save Changes"
    @FindBy(id = "cancel") private WebElement cancelButton;

	
	public EditContactPage(WebDriver driver) {
		this.driver = driver;
		// initialize PageFactory elements
		PageFactory.initElements(driver, this);
	}
	public void clickOnLogout() {

		LogOutButton.click();
	}
	public ContactDetailsPage clickEditSubmitButton() {
        EditsubmitButton.click();
        // Assuming successful edit returns to Contact Details page
        return new ContactDetailsPage(driver);
    }
	public ContactDetailsPage clickCancelButton() {
		EditCancelButton.click();
		 return new ContactDetailsPage(driver);
		
	}
	
	  // Method to clear the email field
    public void clearEmailField() {
        WebElement emailField = driver.findElement(By.id("email"));  // Replace with the correct locator
        UtilityClass.clearElement(emailField);
    }

    // Method to update email (for demonstration)
    public void updateEmail(String newEmail) {
        WebElement emailField = driver.findElement(By.id("email"));  // Replace with the correct locator
        clearEmailField();  // Clear the existing email
        emailField.sendKeys(newEmail);  // Enter the new email
    }
    
    // Method to clear a field
    public void clearField(WebElement field) {
        UtilityClass.clearElement(field);
    }
 // Method to update email, phone, and name

    public void updateContactDetails(String newEmail, String newPhone, String newName) throws InterruptedException {
        WebElement emailField = driver.findElement(By.id("email"));  // Replace with the correct locator
        WebElement phoneField = driver.findElement(By.id("phone"));  // Replace with the correct locator
        WebElement nameField = driver.findElement(By.id("name"));    // Replace with the correct locator

        // Clear existing values
        clearField(emailField);
        clearField(phoneField);
        clearField(nameField);

        // Update with new values
        emailField.sendKeys(newEmail);
        phoneField.sendKeys(newPhone);
        nameField.sendKeys(newName);

        // Submit the form (Assuming there is a Save or Submit button)
      //  WebElement saveButton = driver.findElement(By.id("saveButton"));  // Replace with correct locator
        //saveButton.click();
        clickEditSubmitButton();
        Thread.sleep(2000);
    }

    // Method to verify that the updated contact details are shown on the details page
    public boolean verifyUpdatedContactDetails(String expectedEmail, String expectedPhone, String expectedName) {
        // Wait for redirection to the contact detail page
        WebElement detailEmail = driver.findElement(By.id("contactEmail"));  // Replace with correct locator
        WebElement detailPhone = driver.findElement(By.id("contactPhone"));  // Replace with correct locator
       WebElement detailName = driver.findElement(By.id("contactName"));    // Replace with correct locator

        // Check if the updated contact details are visible on the contact detail page
        return detailEmail.getText().equals(expectedEmail) &&
               detailPhone.getText().equals(expectedPhone) &&
             detailName.getText().equals(expectedName);
    }
}

package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;

public class ContactDetailsPage  extends ProjectSpecificationMethods{

	
	@FindBy(id = "logout")
	WebElement logout;
	
	@FindBy(xpath="/html/body/div[1]/header/h1")
	WebElement headerTitle;
	
	@FindBy(xpath="//*[@id=\"edit-contact\"]")
	WebElement editContactButton;
	
	@FindBy(xpath="//*[@id=\"delete\"]")
	WebElement deleteContactButton;
	
	@FindBy(xpath="//*[@id=\"return\"]")
	WebElement returnToListButton;
	
	
	  // Locators for displaying details (adjust IDs/classes based on actual HTML)
    @FindBy(id = "firstName") 
    WebElement displayFirstName;
    @FindBy(id = "lastName") 
    WebElement displayLastName;
    @FindBy(id = "birthdate") 
    WebElement displayBirthdate;
    @FindBy(id = "email") 
    WebElement displayEmail;
    @FindBy(id = "phone") 
    WebElement displayPhone;
    @FindBy(id = "street1") 
    WebElement displayStreet1;
    @FindBy(id = "street2") 
    WebElement displayStreet2;
    @FindBy(id = "city")
    WebElement displayCity;
    @FindBy(id = "stateProvince") 
    WebElement displayState;
    @FindBy(id = "postalCode") 
    WebElement displayPostalCode;
    @FindBy(id = "country") 
    WebElement displayCountry;

	
	public ContactDetailsPage(WebDriver driver) {
		this.driver = driver;
		// initialize PageFactory elements
		PageFactory.initElements(driver, this);
	}
	
	  // Check if the header is visible on the page
    public boolean isHeaderVisible() {
        return headerTitle.isDisplayed();  // Check if header element is displayed
    }

    // Method to click on the logout button
    public void clickLogout() {
        logout.click();
    }

    // --- Methods ---
    public EditContactPage clickEditContactButton() {
        editContactButton.click();
        return new EditContactPage(driver);
    }
    
  /*  public void clickDeleteContactButton() {
        deleteContactButton.click();
        // Alert handling will be done in the test method
    }*/

    public ContactListPage clickReturnToList() {
    	returnToListButton.click();
		return new ContactListPage(driver);
    }
    public void clickDeleteContactButton() {
        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteButton.click();
    }
   


}


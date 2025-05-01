package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;

public class AddContactPage extends ProjectSpecificationMethods {
	
@FindBy(xpath="/html/body/div[1]/header/h1")
WebElement AddContactFormHeading;

@FindBy(id = "firstName") 
WebElement firstNameInput;

@FindBy(id = "lastName") 
WebElement lastNameInput;

@FindBy(id = "birthdate") 
WebElement birthdateInput; // Type="date"

@FindBy(id = "email") 
WebElement emailInput;

@FindBy(id = "phone") 
WebElement phoneInput;

@FindBy(id = "street1") 
WebElement street1Input;

@FindBy(id = "street2") 
WebElement street2Input;

@FindBy(id = "city") 
WebElement cityInput;

@FindBy(id = "stateProvince") 
WebElement stateInput;

@FindBy(id = "postalCode") 
WebElement postalCodeInput;

@FindBy(id = "country") 
WebElement countryInput;

@FindBy(id = "submit") 
WebElement submitButton;

@FindBy(id = "cancel") 
WebElement cancelButton;

//@FindBy(id = "error") // Assuming generic error message element
//WebElement errorMessage;

	 public AddContactPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	 public boolean isContactFormVisible() {
		    try {
		        return AddContactFormHeading.isDisplayed();
		    } catch (Exception e) {
		        return false;
		    }
		}
	 public String getContactFormHeadingText() {
		    return AddContactFormHeading.getText();
		}

	 public ContactListPage clickSubmit() {
	        submitButton.click();
	        // Assuming successful add returns to Contact List
	        return new ContactListPage(driver);   //returning and listing in contact list page
	    }

	    public ContactListPage clickCancel() {
	        cancelButton.click();
	        return new ContactListPage(driver);
	    }
	    

		public AddContactPage enterFirstName(String FirstName) {
			firstNameInput.sendKeys(FirstName);
			return this;
		}
		
	 	public AddContactPage enterLastName(String LastName) {
      		
	 		lastNameInput.sendKeys(LastName);
      		return this;
      	}
	 	public AddContactPage enterDateOfBirth(String DateofBirth) {
	 	
	 		birthdateInput.sendKeys(DateofBirth);
			return this;
}
	 	public AddContactPage enterEmail(String Email) {
		 	
	 		emailInput.sendKeys(Email);
			return this;
			
}
public AddContactPage enterPhone(String Phone) {
		 	
	 		phoneInput.sendKeys(Phone);
			return this;
			
}

public AddContactPage enterStreetAddress1(String StreetAddress1) {
 	
	street1Input.sendKeys(StreetAddress1);
	return this;
	
}
public AddContactPage enterStreetAddress2(String StreetAddress2) {
 	
	street2Input.sendKeys(StreetAddress2);
	return this;
	
}	

public AddContactPage enterCity(String City) {
 	
	cityInput.sendKeys(City);
	return this;
	
}

public AddContactPage enterStateOrProvince(String StateorProvince) {
 	
	stateInput.sendKeys(StateorProvince);
	return this;
	
}

public AddContactPage enterPostalCode(String PostalCode) {
 	
	postalCodeInput.sendKeys(PostalCode);
	return this;
	
}

public AddContactPage enterCountry(String Country) {
 	
	countryInput.sendKeys(Country);
	return this;
	
}
//@FindBy(xpath = "//div[contains(@class,'error')]") // Update this based on your actual page

/*@FindBy(id = "error") // Assuming generic error message element
WebElement errorMessage;

public String getErrorMessage() {
    try {
        return errorMessage.getText();
    } catch (NoSuchElementException e) {
        return "";
    }
}
*/

//@FindBy(xpath = "//div[contains(@class, 'error') or contains(text(),'validation')]")
@FindBy(xpath="//*[@id=\"error\"]")
WebElement errorBox;

public String getErrorMessage() {
    try {
        return errorBox.getText().trim();
    } catch (Exception e) {
        return "";
    }
}


}
package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.ProjectSpecificationMethods;

public class AddUserPage extends ProjectSpecificationMethods{

	@FindBy(id="firstName")
	WebElement firstname;
	
	@FindBy(id="lastName")
	WebElement lastname;
	
	@FindBy(id="email")
	WebElement emailId;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//span[text()='User validation failed: email: Email is invalid']")
	WebElement errorMsg;
	
	@FindBy(xpath="//h1[text()='Contact List']")
	WebElement contactList;
	
	@FindBy(id="logout")
	WebElement Logout;
	
	
	
	
	public AddUserPage(WebDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(driver,this);
	
	
}
	
	
	public AddUserPage enterFirstName(String fn) {
		firstname.sendKeys(fn);
		return this;
	}
	
public AddUserPage enterLastName(String ln) {
		
		lastname.sendKeys(ln);
		return this;
	}
	
	public AddUserPage enterEmailId(String id) {
		
		emailId.sendKeys(id);
		return this;
	}
	
	public AddUserPage enterpassword(String password) {
		
		pass.sendKeys(password);
		return this;
	}
	
	public AddUserPage clickSubmitbtn() {
		
		submitBtn.click();
		return this;
	}
	
/*
		public void validateSignUp(String testtype, String expectedmessage) {
	    String actualMessage = "";

	    switch (testType.trim().toLowerCase()) {
	        case "validcredentials":
	            actualMessage = contactList.getText().trim();
	            break;

	        case "Valida Data":
	        case "Invalid Data:AlreadyLogned user":
	        case "Empty Fields Data":
	        case "Empty Firstname Filed":
	        case "Empty Lastname Field":
	        case "Empty email Field":
	        case "Invalid Email Format":
	            actualMessage = errorMsg.getText().trim();
	            break;

	        default:
	            throw new IllegalArgumentException("Unsupported test type: " + testType);
	    }

	    Assert.assertEquals(actualMessage, expectedMessage.trim(), "Validation message mismatch for testType: " + testType);
	}
}
*/
	public void validateSignUp(String testType, String expectedMessage) {
	    String actualMessage;

	    if (testType.equalsIgnoreCase("Valid Data") && Logout.isDisplayed()) {
	        actualMessage = "Logout :SignUp scuccessfull :Test Passed";  // Your expected success message
	    } else {
	        actualMessage = errorMsg.getText().trim();
	    }

	    Assert.assertEquals(actualMessage, expectedMessage.trim(), "Validation failed for test type: " + testType);
	}}
package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.utilities.UtilityClass;

public class SignUp extends ProjectSpecificationMethods{

	   @FindBy(id = "sign-username")
		WebElement userName;
		
		@FindBy(id = "sign-password")
		WebElement passWord;
		
		@FindBy(xpath = "//button[text()='Sign up']")
		WebElement signUpBtn;
		
		@FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[1]")
		WebElement closeBtn;
		


@FindBy(id = "signin2")
WebElement signUpLink;

@FindBy(id = "signInModal")
public WebElement signUpModal;

@FindBy(xpath = "//h5[@id='signInModalLabel']")
WebElement signUpModalTitle;

	
	// Constructor to initialize page elements
    public SignUp() {    //commented WebDriver driver and Webdriver driver;bcoz already static driver in utility class    // this.driver = UtilityClass.driver;  // Accessing the static driver from UtilityClass -Doubt ?
   //  this.driver = driver;  //-commented becose of static driver using  --doubt ?
        PageFactory.initElements(driver, this);
    }
    
 // Checks if modal is visible
    public boolean isSignUpModalDisplayed() {  
        try {
            return signUpModal.isDisplayed() && signUpModalTitle.getText().contains("Sign up");
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    public SignUp enterUsername(String usrnm)
	{
		userName.clear();   //uName is mentioned above
		//username.sendKeys(usrnm);
		//username.sendKeys(usrnm);
		UtilityClass.clearAndSendKeys(userName, usrnm);
		//this method inside utility class
		
		System.out.println("Username:"+usrnm);  // prints username to console

		return this;  //return to the same page
		
	}
	public SignUp enterPassword(String pwrd)
	{
	
		UtilityClass.clearAndSendKeys(passWord, pwrd);
		System.out.println("Password:"+pwrd); //print password to console
		return this;  //return to the same page
	}
	
	public SignUp clickSubmitButton()
	{
		signUpBtn.click(); //actions performing
		return this;  //return to the same page
	}
	
	public SignUp clickCloseButton() {
		closeBtn.click();
		return this;
	}
	
	
	
	public  String validateSignUp(String TestType, String ExpectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
	        // Wait up to 10 seconds for the alert to appear
	        wait.until(ExpectedConditions.alertIsPresent());

	        Alert alert = driver.switchTo().alert();
	        String actualMessage = alert.getText().trim();
	        alert.accept();

	        System.out.println("Alert message from site: " + actualMessage);

	        if (actualMessage.equals(ExpectedMessage.trim())) {
	            System.out.println("✅ Validation Passed. Expected: '" + ExpectedMessage + "', Actual: '" + actualMessage + "'");
	            test.info("SignUp Successfull");
	        } else {
	            System.out.println("❌ Validation Failed. Expected: '" + ExpectedMessage + "', Actual: '" + actualMessage + "'");
	            test.info("SignUp Not Completed");
	        }

	        Assert.assertEquals(actualMessage, ExpectedMessage.trim());
	        return actualMessage;

	    } catch (NoAlertPresentException e) {
	        Assert.fail("❌ No alert present. Validation cannot be performed.");
            test.info("SignUp Not Completed");

	        return null;
	    } catch (Exception e) {
	        Assert.fail("❌ Exception in validateSignUp: " + e.getMessage());
            test.info("SignUp Not Completed");

	        return null;
	    }
	}

	/*
	public String validateSignUp(String testType, String expectedMessage) {
	    // Wait for alert to appear and capture the alert message
	    Alert alert = driver.switchTo().alert();
	    String alertMessage = alert.getText();
	    
	    // Log the alert message
	    test.info("Alert Message: " + alertMessage);
	    
	    // If it's a success message
	    if (alertMessage.contains("success")) {
	        alert.accept(); // Accept the alert
	        return alertMessage; // Returning the message for validation
	    }
	    
	    // If it's a failure message
	    else if (alertMessage.contains("failure")) {
	        alert.accept(); // Accept the alert
	        return alertMessage; // Returning the message for validation
	    }

	    // Default case for any unexpected alert
	    alert.accept();
	    return "Unexpected Alert: " + alertMessage;
	}//according to this chnage ontestfailture also changed
	
	*/
	
	public void clickSignUp() {
		// TODO Auto-generated method stub
		
	}
}

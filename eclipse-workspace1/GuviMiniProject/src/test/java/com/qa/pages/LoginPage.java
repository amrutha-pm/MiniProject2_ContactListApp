package com.qa.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.utilities.UtilityClass;

public class LoginPage extends ProjectSpecificationMethods{

	

	@FindBy(id="loginusername")
	WebElement loginUserName;
	
	@FindBy(id="loginpassword")
	WebElement loginPassword;
	
	@FindBy(xpath = "//button[text()='Log in']")
	WebElement loginButton;
	
	@FindBy(xpath = "//button[text()='Log in']//preceding-sibling::button")
	WebElement closeButton;
	
	// Login modal container
	@FindBy(id = "logInModal")
	private WebElement loginModal;

	// Modal title (inside the login modal)
	@FindBy(xpath = "//div[@id='logInModal']//h5[@class='modal-title']")
     WebElement loginModalTitle;
	
	@FindBy(id = "login2")
	WebElement loginLink;
	  @FindBy(id = "nameofuser")
	    private WebElement welcomeUser;

	public LoginPage()
	{
		
		 PageFactory.initElements(driver, this);
	}
	
	public boolean isLoginModalDisplayed() {
	    try {
	        return loginModal.isDisplayed() && loginModalTitle.getText().contains("Log in");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while checking login modal: " + e.getMessage());
	        return false;
	    }
	}
	
	  public LoginPage  enterUsername(String usrnm)
		{
		  loginUserName.clear();   //uName is mentioned above
			//username.sendKeys(usrnm);
			//username.sendKeys(usrnm);
			UtilityClass.clearAndSendKeys(loginUserName, usrnm);
			//this method inside utility class
			
			System.out.println("Username:"+usrnm);  // prints username to console

			return this;  //return to the same page
			
		}
	  
		public  LoginPage enterPassword(String pwrd)
		{
			loginPassword.clear();
			UtilityClass.clearAndSendKeys(loginPassword, pwrd);
			System.out.println("Password:"+pwrd); //print password to console
			return this;  //return to the same page
		}
		
		
	public LoginPage clickSubmitButton()
		{
			loginButton.click(); //actions performing
			return this;  //return to the same page
		} 
	
	
		public LoginPage clickCloseButton() {
			closeButton.click();
			return this;
		}
		// Method to click the login/submit button on the modal
	/*	public LoginPage clickLoginButton() {
		  //  WebElement loginButton = driver.findElement(By.xpath("//button[text()='Log in']")); // Update locator if needed

		    // Wait and click safely
		    UtilityClass.explicitWait(driver, 10, loginButton);

		    if (loginButton.isDisplayed() && loginButton.isEnabled()) {
		        loginButton.click();
		        System.out.println("🔘 Login (submit) button clicked.");
		    } else {
		        System.out.println("⚠️ Login (submit) button not clickable, using JSClick.");
		        UtilityClass.JSClick(driver, loginButton);
		    }

		    return this;
		}*/
		
public boolean isWelcomeUserVisible() {

    return welcomeUser.isDisplayed();
}

//Method to validate login (after clicking the submit button)
public String validateLogin(String TestType, String ExpectedMessage) {
    try {
        // Wait for the alert to appear
       // wait.until(ExpectedConditions.alertIsPresent());
Thread.sleep(3000);
        // Switch to alert and get message
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText().trim();
        alert.accept();  // Accept the alert after reading the message

        // Log the result
        System.out.println("Alert message: " + actualMessage);

        // Return the actual message to be used in the test
        return actualMessage;

    } catch (NoAlertPresentException e) {
        // Handle the case when no alert appears
        System.out.println("❌ No alert present after login.");
        return "No alert present";  // Return a failure message
    } catch (Exception e) {
        // Handle any other exception
        System.out.println("❌ Error during login validation: " + e.getMessage());
        return "Exception: " + e.getMessage();  // Return exception message
    }
}

public String getLoginModalTitle() {
	loginModalTitle.getText();
	return null;
}


	public String getWelcomeMessage() {
	    // Locate the element that contains the welcome message
	    // Change the locator below to match your actual HTML structure
	    WebElement welcomeMessageElement = driver.findElement(By.id("welcomeMessage"));  // Use the correct ID or other selector
	    return welcomeMessageElement.getText();  // Return the text of the element
	}




}
		



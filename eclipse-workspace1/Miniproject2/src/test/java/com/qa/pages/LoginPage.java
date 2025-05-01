package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;

public class LoginPage  extends ProjectSpecificationMethods{
	@FindBy(id="submit")
	WebElement LoginSubmitButton;
	
	@FindBy(id="email")
	WebElement EmailId;
	
	@FindBy(id="password")
	WebElement passwordLogin;
	
	@FindBy(id="add-contact")
	WebElement addnewcontactButton;
	
	@FindBy(xpath="//h1[text()='Contact List']")
	WebElement contactList;
	
	
	@FindBy(xpath="//*[@id=\"error\"]")
	WebElement errorMessage;
	
	@FindBy(id="logout")
WebElement LogoutButton;
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public LoginPage enterEmailId(String id) {
		
		EmailId.sendKeys(id);
		return this;
	}
	
	public LoginPage enterpassword(String password) {
		
		passwordLogin.sendKeys(password);
		return this;
	}
public LoginPage clickSubmitbtn() {
		
	LoginSubmitButton.click();
		return this;
	}



public boolean isLoginButtonVisible() {
    try {
        return LoginSubmitButton.isDisplayed();
    } catch (NoSuchElementException | NullPointerException e) {
        return false;
    }
}
/*public WebElement getHeaderTitle() {
    waitTillVisible(By.xpath("//*[contains(text(),'Contact List')]"), 3);
    return driver.findElement(By.xpath("//*[contains(text(),'Contact List')]"));
}*/

public boolean isContactListHeaderVisible() {
    try {
        return contactList.isDisplayed();
    } catch (NoSuchElementException | NullPointerException e) {
        return false;
    }
}

// Modified method to check if header is visible and also return the header text
public String getHeaderInfo() {
    try {
        if (contactList.isDisplayed()) {
            return contactList.getText();  // If visible, return the header text
        }
    } catch (NoSuchElementException | NullPointerException e) {
        return "Header is not visible";  // If the header is not visible, return this message
    }
    return "Header is not visible";  // Default message if something goes wrong
}

	public boolean isLogoutButtonVisible() {
	    try {
	        return LogoutButton.isDisplayed();
	    } catch (NoSuchElementException | NullPointerException e) {
	        return false;
	    }
	}
	public LoginPage clickLogout() {
	    LogoutButton.click();
	    return new LoginPage(driver); // Navigate back to login page
	}
}


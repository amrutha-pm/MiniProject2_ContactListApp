package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;

public class LogoutPage extends ProjectSpecificationMethods{

	@FindBy(id="submit")
	WebElement LoginSubmitButton;
	@FindBy(id="email")
	WebElement EmailId;
	
	@FindBy(id="password")
	WebElement passwordLogin;
	@FindBy(id="logout")
WebElement LogoutButton;
	
	
	public LogoutPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
public LogoutPage enterEmailId(String id) {
		
		EmailId.sendKeys(id);
		return this;
	}
	
	public LogoutPage enterpassword(String password) {
		
		passwordLogin.sendKeys(password);
		return this;
	}
public LogoutPage clickSubmitbtn() {
		
	LoginSubmitButton.click();
		return this;
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
public boolean isLoginButtonVisible() {
    try {
        return LoginSubmitButton.isDisplayed();
    } catch (NoSuchElementException | NullPointerException e) {
        return false;
    }
}

}



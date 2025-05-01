package com.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods{
	 
	
	@FindBy(id="signup")
	WebElement signup;     //each page object ,each constructor driver am passing driver,in this drive ronly am looking elements
	
	@FindBy(id="submit")
	WebElement LoginSubmitButton;
	
	public HomePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AddUserPage clickSignup(){
	 signup.click();
	 //System.out.println("");
	 return new AddUserPage(driver);   //while click on signup  it will go to add user  page,next page constructor am calling here

	}
	public  boolean  isSignUpButtonVisible() {  //use this to check the naviagtion test case
	    try {
	        return signup.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	public boolean isLoginButtonVisible() {
	    try {
	        return LoginSubmitButton.isDisplayed();
	    } catch (NoSuchElementException | NullPointerException e) {
	        return false;
	    }
	}
	
}

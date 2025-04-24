package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.ProjectSpecificationMethods;

public class PaymentPage extends ProjectSpecificationMethods{

	@FindBy(id = "name")
	WebElement userName;
	
	@FindBy(id = "country")
	WebElement userCountry;
	
	@FindBy(id="city")
	WebElement userCity;
	
	@FindBy(id = "card")
	WebElement userCard;
	
	@FindBy(id = "month")
	WebElement userMonth;
	
	
	@FindBy(id = "year")
	WebElement userYear;
	
	@FindBy(xpath="//button[text()='Purchase']")
	WebElement purchaseBtn;


    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
    private WebElement confirmationMessage;

   // @FindBy(xpath = "//button[@class='btn btn-success' and text()='Place Order']")
    //private WebElement placeOrderButton;

    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement placeOrderButton;
 
    WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    
    
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click(); // ← this was missing
    }
  
    

    public boolean isUserNameFieldDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(userName));
        return userName.isDisplayed();
    }

    public boolean isCountryFieldDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(userCountry));
        return userCountry.isDisplayed();
    }

    public boolean isCityFieldDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(userCity));
        return userCity.isDisplayed();
    }

    public void enterUserDetails(String name, String country, String city) {
        userName.sendKeys(name);
        userCountry.sendKeys(country);
        userCity.sendKeys(city);
    }

    public void enterPaymentDetails(String card, String month, String year) {
        userCard.sendKeys(card);
        userMonth.sendKeys(month);
        userYear.sendKeys(year);
    }

    public void clickPurchaseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn));
        purchaseBtn.click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }
    public void enterPurchaseDetails(String name, String country, String city, String card, String month, String year) {
    	 userName.sendKeys(name);
         userCountry.sendKeys(country);
         userCity.sendKeys(city);

         userCard.sendKeys(card);
         userMonth.sendKeys(month);
         userYear.sendKeys(year);

    }



	public boolean isConfirmationDisplayed() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
	        return confirmationMessage.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
}

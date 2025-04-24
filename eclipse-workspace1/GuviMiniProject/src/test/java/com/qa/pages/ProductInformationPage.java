package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.ProjectSpecificationMethods;

public class ProductInformationPage extends ProjectSpecificationMethods{
	 WebDriverWait wait;
	 
	 
	 @FindBy(xpath = "//h2[@class='name']")
	    WebElement productTitle;

	    @FindBy(xpath = "//h3[@class='price-container']")
	    WebElement productPrice;
 
	 
	    @FindBy(xpath = "//div[@id='more-information']//p")
	    WebElement productDescription;
	    
	    @FindBy(xpath = "//a[text()='Add to cart']")
	     WebElement addToCartButton;
	    
	    @FindBy(id = "cartur")
	     WebElement cartMenu;
	    @FindBy(xpath = "//a[@id='nameofuser' and starts-with(text(), 'Welcome')]")
	     WebElement welcomeUser;
	  
	    @FindBy(xpath = "//a[text()='Phones']")
	    WebElement phonesCategory;
	    
	    @FindBy(xpath = "//a[text()='Samsung galaxy s6']")
	     WebElement productLink; 
	    
	    @FindBy(xpath="//*[@id=\"tbodyid\"]/div[2]/div/div/h4/a")
	    WebElement NokiaPhone;    //Nokia lumia 1520
	    
	  
	    public ProductInformationPage() {    //commented WebDriver driver and Webdriver driver;bcoz already static driver in utility class    // this.driver = UtilityClass.driver;  // Accessing the static driver from UtilityClass -Doubt ?
	    	 
	    	        PageFactory.initElements(driver, this);
	    	        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	    	    }
	    public boolean isWelcomeUserDisplayed() {
	        return welcomeUser.isDisplayed();
	    }
	    
	    public String getProductTitle() {
	        wait.until(ExpectedConditions.visibilityOf(productTitle));
	        return productTitle.getText();
	    }

	    public String getProductPrice() {
	        wait.until(ExpectedConditions.visibilityOf(productPrice));
	        return productPrice.getText();
	    }

	    public String getProductDescription() {
	        wait.until(ExpectedConditions.visibilityOf(productDescription));
	        return productDescription.getText();
	    }

	    public void clickAddToCart() {
	        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
	    }

	    public void clickCartMenu() {
	        wait.until(ExpectedConditions.elementToBeClickable(cartMenu)).click();
	    }

	    public String getCartCount() {
	        wait.until(ExpectedConditions.visibilityOf(cartMenu)); 
	        String cartText = cartMenu.getText();
	        return cartText.replaceAll("[^0-9]", "");  
	    }
	    // Method to get the alert
	    public Alert getAlertElement() {
	        return driver.switchTo().alert();  // Returns the alert present on the screen
	    }
	  
	    	public WebElement getAddToCartButton() {
	    	    return addToCartButton;
	    	}

	    
	 // Method to click on a product using its name (or any other attribute)
	    public void clickOnProduct(String productName) {
	        // Assuming each product is a clickable element with a link or button
	        // Use a dynamic XPath to locate the product by its name or title
	        String productXpath = "//a[text()='" + productName + "']";  // Modify this based on your app's structure
	        
	        try {
	            // Wait until the product is clickable
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productXpath)));
	            Thread.sleep(2000);
	            // Click the product
	            driver.findElement(By.xpath(productXpath)).click();
	            
	            System.out.println("✅ Product '" + productName + "' clicked successfully.");
	        } catch (Exception e) {
	            System.out.println("❌ Failed to click on the product: " + productName);
	            e.printStackTrace();
	        }
	    }
	
	}
	    



package com.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.ProjectSpecificationMethods;

public class CartFunctionsPage extends ProjectSpecificationMethods {

	 WebDriver driver;
	    WebDriverWait wait;
	    @FindBy(xpath = "//button[text()='Place Order']")
	     WebElement placeOrderButton;
	    
	    @FindBy(xpath = "//a[text()='Add to cart']")
		WebElement addToCartBtn;
	    
	    @FindBy(id = "cartur")
	     WebElement cartMenu;

		@FindBy(xpath = "(//td[2])[1]")
		WebElement FirstItem;  
		
		@FindBy(xpath = "(//a[text()='Delete'])[1]")
		WebElement deleteFirstItem;
		
		
		
	//	@FindBy(id = "totalp")
		@FindBy(xpath = "//*[@id=\"totalp\"]")
		WebElement total;
		
		
	/*    public CartFunctionsPage() { //  i had put wait helper in test class setup    -got error 
	      //  this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }*/
		public CartFunctionsPage(WebDriver driver) { // Accept WebDriver as a parameter
		    this.driver = driver; // Initialize the driver with the passed instance
		    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait with driver
		    PageFactory.initElements(driver, this); // Initialize page elements
		}

		
		public void waitForCartToLoad() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
	    }

	    public List<WebElement> getCartItems() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid"))); //This <tbody id="tbodyid"> contains all the rows (<tr>) of items you've added to the shopping cart on demoblaze.com
	        return driver.findElements(By.xpath("//tr"));
	    }

	    public String getTotalPrice() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
	        return driver.findElement(By.id("totalp")).getText();
	    }

	    public void deleteFirstProduct() {
	        WebElement deleteLink = driver.findElement(By.xpath("//a[text()='Delete']"));
	        wait.until(ExpectedConditions.elementToBeClickable(deleteLink));
	        deleteLink.click();
	        wait.until(ExpectedConditions.stalenessOf(deleteLink));
	    }
	    
	  
	    public void clickOnPlaceOrder() {
	    	placeOrderButton.click(); 
	 }

	    public void goToCart() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        // Assuming 'cartMenu' is a defined @FindBy element already pointing to the Cart link
	        wait.until(ExpectedConditions.elementToBeClickable(cartMenu)).click();

	        // Optionally wait for the cart content to load
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));

	        System.out.println("✅ Navigated to the Cart page.");
	    }

	/*    public void addProductToCart(String productName) { // am calling this method in payment test to add products to cart
	        // This method should go to product page and click "Add to cart" for the given product
	        WebElement productLink = driver.findElement(By.linkText(productName));
	        productLink.click();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
	        addToCartBtn.click();

	        // Handle alert
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.accept();

	        driver.navigate().back(); // Optional: go back to home page
	    }

	    // New method to wait for the Place Order button to be clickable
	    public void waitForPlaceOrderButtonToBeClickable() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait  10 seconds
	        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
	    }
*/
	    
	    public void addProductToCart(String productName) {
	        try {
	            WebElement productLink = driver.findElement(By.linkText(productName));
	            productLink.click();

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
	            addToCartButton.click();

	            wait.until(ExpectedConditions.alertIsPresent());
	            driver.switchTo().alert().accept();

	            driver.navigate().back();
	            System.out.println("✅ Product added to cart: " + productName);

	        } catch (StaleElementReferenceException e) {
	            System.out.println("⚠️ Retrying due to stale element: " + e.getMessage());
	            addProductToCart(productName); // Retry once recursively
	        }
	    }

	}
	    


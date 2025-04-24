package com.qa.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.CartFunctionsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.PaymentPage;
import com.qa.utilities.WaitHelper;

public class TC_006_PaymentTest extends ProjectSpecificationMethods{

	
	@BeforeTest
	public void setup() {
		
		sheetname="purchaseUserData";
		testname="Purchase Test";
		testdescription="Testing the Payment and purchase user page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
		   WaitHelper.init(driver);
	}
	CartFunctionsPage cartPage;
    LoginPage loginPage;  // Declare LoginPage object
    PaymentPage payPage;
    
    
	@BeforeMethod
	public void set()
	{
		test.info("Initiating Cart Page Constructor");
		payPage = new PaymentPage(driver);  //so i kept driver inside boc of unresolved complilatione rror
  
	    cartPage = new CartFunctionsPage(driver);
	   // loginPage = new LoginPage(); // Optional not using it here

	}
    
	/*@Test
	public void verifyPaymentProcess() throws InterruptedException{
		//go to login first before purchase
		
		 HomePage homePageobj = new HomePage();
		   LoginPage loginPage = homePageobj.clickLogin();
		 
		   loginPage.enterUsername("Neelima@gmail.com");
		   loginPage.enterPassword("workbook");
		   loginPage.clickSubmitButton();	
		   ProductInformationPage detailPage = new   ProductInformationPage();

		   Assert.assertTrue(detailPage.isWelcomeUserDisplayed(), "Login failed!");
	        Thread.sleep(5000);  // Wait for the login to complete (you can replace this with WebDriverWait)

	        test.info("Go to Cart Page");
			cartPage.waitForCartToLoad();
			cartPage.goToCart();
			
			Thread.sleep(5000);
			
			  payPage.clickPlaceOrder();
				Thread.sleep(5000);
		
		   
	} */
	@Test
	public void verifyPaymentProcess() throws InterruptedException {
	    HomePage homePage = new HomePage();
	    LoginPage loginPage = homePage.clickLogin();

	    loginPage.enterUsername("Neelima@gmail.com");
	    loginPage.enterPassword("workbook");
	    loginPage.clickSubmitButton();

	    // 🛒 Add an item to the cart
	    cartPage.addProductToCart("Nokia lumia 1520");
System.out.println("product added to cart");
	    // ✅ Navigate to cart
	    cartPage.goToCart();
	    Assert.assertFalse(cartPage.getCartItems().isEmpty(), "Cart is empty — cannot proceed to purchase!");

	    
     //  cartPage.waitForPlaceOrderButtonToBeClickable();  // Use an explicit wait for the button to be clickable
    
		  
    //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Place Order']")));

	    // 💰 Proceed to place order
	   cartPage.clickOnPlaceOrder();
Thread.sleep(4000);
	   
	   // ✍️ Enter Purchase Details
     //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))); // Ensure form is visible
	   
	    payPage.enterPurchaseDetails("Neelima", "India", "Hyderabad", "1234567812345678", "04", "2026");
Thread.sleep(3000);
	    payPage.clickPurchaseButton();

	    // ✅ Validate purchase
	    Assert.assertTrue(payPage.isConfirmationDisplayed(), "Purchase confirmation not shown!");
	    System.out.println("✅ Purchase successful!");

	}

	}
	

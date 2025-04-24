package com.qa.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.CartFunctionsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utilities.WaitHelper;

public class TC_005_CartFunctionsTest extends  ProjectSpecificationMethods{

	@BeforeTest
	public void setup() {
		
		//sheetname="SignUp";
		testname="CartPage Test";
		testdescription="Testing the Cart page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
		   WaitHelper.init(driver);
	}
	CartFunctionsPage cartPage;
    LoginPage loginPage;  // Declare LoginPage object

	@BeforeMethod
	public void set()
	{
		test.info("Initiating Cart Page Constructor");
		cartPage = new CartFunctionsPage(driver);  //so i kept driver inside boc of unresolved complilatione rror
      //  loginPage = new LoginPage();  // Initialize LoginPage object

	}
	
/*	@Test(priority = 0)
	public void goToCart() throws Exception
	{
		test.info("Go to Cart Page");
		cartPage.goToCart();
		Thread.sleep(5000);
	}
	*/
	@Test(priority=0)
	public void goToCart() throws InterruptedException
	{
        // Step 1: Login before navigating to the cart
        test.info("Logging in with valid credentials");
        
        HomePage homePageobj = new HomePage();
		   LoginPage loginPage = homePageobj.clickLogin();
		   loginPage.enterUsername("Neelima@gmail.com");
		   loginPage.enterPassword("workbook");
		   loginPage.clickSubmitButton();

        // Add an explicit wait or sleep to make sure the login completes
        Thread.sleep(5000);  // Wait for the login to complete (you can replace this with WebDriverWait)
		
		
		test.info("Go to Cart Page");
		cartPage.waitForCartToLoad();
		cartPage.goToCart();
	
	
	String priceBefore = cartPage.getTotalPrice();
	  System.out.println("Total price before deletion: $" + priceBefore);	
	
	  cartPage.deleteFirstProduct();
      System.out.println("Product has been deleted from the cart.");
	
      cartPage.waitForCartToLoad();
	
      try {
          String priceAfter = cartPage.getTotalPrice();
          System.out.println("Total price after deletion: $" + priceAfter);

          if (priceAfter.trim().isEmpty() || priceAfter.trim().equals("0")) {
              System.out.println("Your cart is now empty.");
          } else {
              System.out.println("Cart still has items.");
          }
      } catch (Exception e) {
          System.out.println("Your cart is now empty (no total price displayed).");
      }
	}
}
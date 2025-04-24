package com.qa.test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductInformationPage;
import com.qa.utilities.WaitHelper;

public class TC_004_ProductInformationTest extends ProjectSpecificationMethods{
   
	@BeforeTest
	public void setup() {
		
		//sheetname="LoginData";
		testname="ProductInformationTest";
		testdescription="Testing the Product detail page functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";

		   WaitHelper.init(driver);
	}


@Test
	  public void verifyProductDetailAndAddToCart() {
		 HomePage homePageobj = new HomePage();
		   LoginPage loginPage = homePageobj.clickLogin();
		   loginPage.enterUsername("Neelima@gmail.com");
		   loginPage.enterPassword("workbook");
		   loginPage.clickSubmitButton();
		   ProductInformationPage detailPage = new   ProductInformationPage();
		   boolean isWelcomeUserDisplayed = detailPage.isWelcomeUserDisplayed();
	        Assert.assertTrue(isWelcomeUserDisplayed, "❌ Login failed - Welcome message not displayed");
	        if (isWelcomeUserDisplayed) {
	            System.out.println("Welcome user is displayed");
	        }
	        
	        detailPage.clickOnProduct("Samsung galaxy s6");
	    
	        String title = detailPage.getProductTitle();
	        String price = detailPage.getProductPrice();
	        String description = detailPage.getProductDescription();

	        System.out.println("Title: " + title);
	        System.out.println("Price: " + price);
	        System.out.println("Description: " + description);
	      
	        Assert.assertTrue(title.toLowerCase().contains("samsung"), "Title mismatch");
	        Assert.assertTrue(price.contains("$"), "Price format incorrect");
	        Assert.assertFalse(description.isEmpty(), "Description is empty");
	
	        
	        WaitHelper.wait.until(ExpectedConditions.elementToBeClickable(detailPage.getAddToCartButton()));

	        detailPage.clickAddToCart();                      //************ i want to add 2 more products to check items in cart increased or that count 

	       /* wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	        */
	        
	        try {
	            wait.until(ExpectedConditions.alertIsPresent());
	            Alert alert = driver.switchTo().alert();
	            System.out.println("Alert: " + alert.getText());
	            alert.accept();
	        } catch (Exception e) {
	            System.out.println("No alert present after adding product.");
	        }

	        detailPage.clickCartMenu();
	        System.out.println("Product is successfully added to the cart!");
	}

}

	
	
	
	
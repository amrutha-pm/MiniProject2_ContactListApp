package com.qa.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.SignUp;
import com.qa.utilities.UtilityClass;

public class TC_001_HomePageTest extends ProjectSpecificationMethods{
	
	    HomePage homePage;
	@BeforeTest
	public void setup() {
		
		//sheetname="SignUp";
		testname="HomePage Test";
		testdescription="Testing the HomePage functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Amrutha pm";
	}
	

	
	@BeforeMethod
	public void set()
	{
		homePage = new HomePage();
		test.info("Initiating HomePage Contructor");
	}

	  // Test case to verify the application URL
    @Test(priority=1)

    public void verifyAppUrl() {
        // Verify that the current URL matches the expected URL
        boolean isUrlValid = homePage.isExpectedUrl();

        // Assert the URL validation result
        Assert.assertTrue(isUrlValid, "❌ The current URL is incorrect. Expected: https://www.demoblaze.com/");
        test.info("URL matched!");
    }
    
    @Test(priority=2)
    public void verifySignUpButtonIsVisibleOnHomePage() {
        boolean isButtonVisible = homePage.isSignUpButtonVisible();
        Assert.assertTrue(isButtonVisible, "❌ Sign Up button is not visible on the Home Page!");
        test.info("SignUp Link is Available!");
    }
    @Test(priority = 3)
	public void verifySignUpWithModalOpens() {   // DOBUT********************
	   homePage.clickSignUp();
        // Optionally wait for the modal to load
        UtilityClass.explicitWait(driver,10, homePage.signUpModal);  // Use UtilityClass to wait for modal   *****************doubt why driver?????????

	    Assert.assertTrue(homePage.isSignUpModalDisplayed(), "❌ Sign Up modal did not appear.");
	    System.out.println("✅ Sign Up modal is displayed successfully.");
        test.info("SignUp modal is Opening!");

	}
	
  /*  @Test(priority = 4)
    public void verifyLoginIsActive() {
       
        // Click the login button, which opens the login modal
        homePage.clickLogin(); // This will return the LoginPage object
        
        // Verify the login modal is displayed
        boolean isModalDisplayed = homePage.isLoginModalDisplayed();
        
        // Assert that the login modal is displayed
        Assert.assertTrue(isModalDisplayed, "❌ Login modal did not appear or the modal is inactive.");
        
        // Log the success message for confirmation
        if (isModalDisplayed) {
            System.out.println("✅ Login modal is displayed successfully.");
        }
        test.info("Login Link is Active!");

    }*/
    
    @Test(priority = 4)
    public void verifyLoginIsActive1() throws InterruptedException {
        // Define a username for the login process (e.g., "testUser")
        String username = "Neelima@gmail.com"; // Replace with the actual username for the test
        String password = "workbook"; // Replace with the actual password for the test

        // Step 1: Click the login button, which opens the login modal
        LoginPage loginPage = homePage.clickLogin(); // Updated to return LoginPage
        Thread.sleep(2000);
        // Step 2: Verify the login modal is displayed
        boolean isModalDisplayed = loginPage.isLoginModalDisplayed(); // Should be checked on LoginPage
        Assert.assertTrue(isModalDisplayed, "❌ Login modal did not appear or the modal is inactive.");

        // Log the success message for confirmation
        if (isModalDisplayed) {
            System.out.println("✅ Login modal is displayed successfully.");
        }
        test.info("Login Link is Active!");

        // Step 3: Enter login credentials and submit
        loginPage.enterUsername(username); // Pass the username here
        loginPage.enterPassword(password); // Pass the password here
        LoginPage updatedHomePage = loginPage.clickSubmitButton(); // Redirects back to homepage

        // Step 4: Verify welcome message is displayed with the username
        String welcomeText = updatedHomePage.getWelcomeMessage(); // e.g., "Welcome, testUser!"
        
        // Assert that the welcome message contains the username
        Assert.assertTrue(welcomeText.contains("Welcome, " + username), 
            "❌ Welcome message does not contain the expected username.");
        
        System.out.println("✅ Welcome message displayed: " + welcomeText);
        test.pass("Welcome message verified after login.");
    }

   /*
    @Test(priority = 4)
    public void verifyLoginIsActive() {
        // Define a username for the login process (e.g., "testUser")
        String username = "Neelima@gmail.com"; // Replace with the actual username for the test
        String password = "workbook"; // Replace with the actual password for the test

        // Step 1: Click the login button, which opens the login modal
        LoginPage loginPage = homePage.clickLogin(); // Updated to return LoginPage
        
        // Wait for the login modal to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isModalDisplayed = wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginModal())).isDisplayed();
        
        // Assert that the login modal is displayed
        Assert.assertTrue(isModalDisplayed, "❌ Login modal did not appear or the modal is inactive.");
        System.out.println("✅ Login modal is displayed successfully.");
        test.info("Login Link is Active!");

        // Step 2: Enter login credentials and submit
        loginPage.enterUsername(username); // Pass the username here
        loginPage.enterPassword(password); // Pass the password here
        HomePage updatedHomePage = HomePage.clickSubmitButton(); // Redirects back to homepage

        // Step 3: Wait for the homepage to load and the welcome message to be visible
        wait.until(ExpectedConditions.visibilityOf(updatedHomePage.getWelcomeMessage()));

        // Step 4: Verify welcome message is displayed with the username
        String welcomeText = updatedHomePage.getWelcomeMessage(); // e.g., "Welcome, testUser!"
        
        // Assert that the welcome message contains the username
        Assert.assertTrue(welcomeText.contains("Welcome, " + username), 
            "❌ Welcome message does not contain the expected username.");
        
        System.out.println("✅ Welcome message displayed: " + welcomeText);
        test.pass("Welcome message verified after login.");
    }

    */
    
    
    
    
    
	  @Test(priority=5)
	    public void verifyHomePageTitle() {
	        String actualTitle = homePage.validateHomePageTitle();
	        String expectedTitle = "PRODUCT STORE";

	        Assert.assertEquals(actualTitle, expectedTitle, "❌ Home Page title text doesn't match!");
	        //Assert.assertTrue(actualTitle.trim().equalsIgnoreCase(expectedTitle.trim()),  "❌ Home Page title text doesn't match (case-insensitive check)!");
	        test.info("Results Matched!");

	    }

	  @Test(priority=6)

	    public void verifyLogoIsDisplayed() {
	        Assert.assertTrue(homePage.isLogoDisplayed(), "❌ Homepage logo is not displayed!");
	        test.info("Logo Matched!");

	    }
	   @Test(priority = 7)
	    public void verifyTotalCategories() {
	        homePage = new HomePage();  // Make sure this initializes your driver properly
	        int expectedCategoryCount = 3; // You can change based on your UI
	        int actualCategoryCount = homePage.getTotalCategories();

	        Assert.assertEquals(actualCategoryCount, expectedCategoryCount, "❌ Category count mismatch!");
	        System.out.println("✅ Total categories verified: " + actualCategoryCount);
	    }

	/*    @Test(priority = 8)
	    public void verifyCategoryNames() {
	        List<String> actualCategoryNames = homePage.getCategoryNames();

	        // You can update this based on your app's actual category names
	        List<String> expectedCategories = List.of("Phones", "Laptops", "Monitors");

	        Assert.assertEquals(actualCategoryNames, expectedCategories, "❌ Category names mismatch!");
	        System.out.println("✅ Category names verified: " + actualCategoryNames);
	    } */
	   
	   
	   @Test(priority = 8)
	    public void verifyCategoryNames() {
	   // Get the list of category names from the page
       List<String> actualCategoryNames = homePage.getCategoryNames();

       // Expected list of category names (adjust according to your UI)
       List<String> expectedCategoryNames = List.of("Phones", "Laptops", "Monitors");

       // Assert that the actual list of categories matches the expected one
       Assert.assertEquals(actualCategoryNames.size(), expectedCategoryNames.size(), "❌ Category count mismatch!");
       
       // Assert that all expected categories are present in the actual list
       for (String expectedCategory : expectedCategoryNames) {
           Assert.assertTrue(actualCategoryNames.contains(expectedCategory), 
                             "❌ Expected category '" + expectedCategory + "' not found!");
       }

       // Print success message
       System.out.println("✅ Category names validated successfully: " + actualCategoryNames);
   }

	   
	   
	   
	    @Test(priority = 8)
	    public void testMenuItemsAreDisplayed() {
	       
	        Assert.assertTrue(homePage.isHomeMenuVisible(), "Home menu should be visible.");
	        Assert.assertTrue(homePage.isContactMenuVisible(), "Contact menu should be visible.");
	        Assert.assertTrue(homePage.isAboutUsMenuVisible(), "Contact menu should be visible.");
	    }
	  /*  public boolean verifyCarouselImage(){
	        return carouselImage1.isDisplayed();
	    }
	    public void clickNextButtonCarousel(){
	        nextButtonCarousel.click();
	    }
	    public boolean verifyNextButtonCarousel(){
	        return carouselImage2.isDisplayed();
	    }
	    public void clickPrevButtonCarousel(){
	        prevButtonCarousel.click();
	    }
	    public boolean verifyPrevButtonCarousel(){
	        return carouselImage3.isDisplayed();
	    }*/
	    
	    @Test
	    public void verifyAllCarouselImagesAreDisplayed() {
	        boolean allImagesVisible = homePage.validateAllCarouselImagesByClicking();
	        Assert.assertTrue(allImagesVisible, "All carousel images should be displayed.");
	    }
	    @Test
	    public void verifySecondImageAfterClickingNext() {
	        boolean isSecondImageVisible = homePage.clickNextAndValidateSecondImage();
	        Assert.assertTrue(isSecondImageVisible, "Second image should be visible after clicking next.");
	    }
	    @Test
	    public void verifyPrevButtonDisplaysFirstImage() {
	        boolean isFirstImageVisible = homePage.clickPrevButtonCarouselAndvalidateImage();
	        Assert.assertTrue(isFirstImageVisible, "First carousel image should be visible after clicking Prev.");
	    }
	    @Test
	    public void verifyPrevButtonDisplaysImage3() {
	        boolean isImage3Visible = homePage.clickPrevButtonCarouselAndValidateImage();
	        Assert.assertTrue(isImage3Visible, "The third carousel image should be visible after clicking the Prev button.");
	    }
	    @Test
	    public void verifyPhoneCategoriesNavigation() {
	        homePage.clickPhoneCategories();
	        
	        Assert.assertTrue(homePage.isPhoneCategoriesScreenDisplayed(),
	            "Failed to navigate to Phone Categories screen.");
	       homePage. clickOnSamsungGalaxyS6();
	        System.out.println("SamsungGalagyPhone selected");
	        homePage.clickHomeButton();

	     // Optional: Verify you're back on Home screen
	     Assert.assertTrue(homePage.isHomeScreenDisplayed(), "❌ Failed to return to Home screen.");
	     System.out.println("✅ Navigated back to Home screen.");
	    }
@Test
	    public void testApplicationLogoIsDisplayed() {
	        
	        Assert.assertTrue(homePage.isApplicationLogoVisible(), "Application logo should be visible.");
	    }
	}
	
	    
	    
	    
	    
	    
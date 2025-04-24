package com.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.ProjectSpecificationMethods;
import com.qa.utilities.UtilityClass;


public class HomePage extends ProjectSpecificationMethods{

@FindBy(xpath = "//img[@src='bm.png']")
WebElement logo;
@FindBy(xpath = "//a[text()='Phones']")
WebElement phones;
@FindBy(xpath = "//a[text()='HTC One M9']")
WebElement htcMob;
@FindBy(xpath = "//a[text()='Laptops']")
WebElement laptops;
@FindBy(xpath = "//a[text()='MacBook air']")
WebElement MacLappy;

@FindBy(xpath = "//a[text()='Monitors']")
WebElement monitors;
@FindBy(xpath = "//a[text()='ASUS Full HD']")
WebElement asusDesktop ;
@FindBy(xpath = "//a[@class='nav-link' and text()='Home ']")
 WebElement homeMenu;    //to check home page navigation
@FindBy(xpath = "//a[@class='nav-link' and text()='Contact']")
WebElement contactMenu;

@FindBy(xpath = "//*[@id=\"navbarExample\"]/ul/li[3]/a")
WebElement Aboutus;


@FindBy(xpath = "//a[@id='itemc' and text()='Phones']")
 WebElement phonesCategory;

@FindBy(xpath = "//a[@id='itemc' and text()='Laptops']")
WebElement laptopsCategory;


@FindBy(xpath = "//a[@id='itemc' and text()='Monitors']")
WebElement monitorsCategory;




@FindBy(id = "signin2")
WebElement signUpLink;

@FindBy(id = "signInModal")
public WebElement signUpModal;


@FindBy(xpath = "//h5[@id='signInModalLabel']")
WebElement signUpModalTitle;

@FindBy(id = "login2")
WebElement loginLink;

//Login modal container
	@FindBy(id = "logInModal")
	private WebElement loginModal;

	// Modal title (inside the login modal)
	@FindBy(xpath = "//div[@id='logInModal']//h5[@class='modal-title']")
  WebElement loginModalTitle;
	
	
	// List of category elements (like Phones, Laptops, Monitors)
    @FindBy(xpath = "//div[@class='list-group']/a")
    List<WebElement> options;
	
  //  @FindBy(xpath = "//a[.='Phones']")  
    
  @FindBy(xpath = "//*[@id=\"itemc\"][1]")
   WebElement phoneCategories;
    
    @FindBy(xpath = "//a[.='Monitors']")
    private WebElement monitorCategories;
    
    @FindBy(xpath = "//div[@id='tbodyid']/div[contains(.,'Samsung galaxy s6$360The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsun')]")
    private WebElement phoneItem;
    
    @FindBy(xpath = "//div[@id='tbodyid']/div[contains(.,'ASUS Full HD$230ASUS VS247H-P 23.6- Inch Full HD')]")
    private WebElement monitorItem;

  

    
    @FindBy(xpath = "//img[@alt='First slide']")
    WebElement carouselImage1;
    @FindBy(xpath = "//img[@alt='Second slide']")
   WebElement carouselImage2;
    @FindBy(xpath = "//img[@alt='Third slide']")
   WebElement carouselImage3;
    @FindBy(xpath = "//span[@class='carousel-control-next-icon']")
    WebElement nextButtonCarousel;
    @FindBy(xpath = "//span[@class='carousel-control-prev-icon']")
    WebElement prevButtonCarousel;
    
 @FindBy(xpath = "//*[@id=\"carouselExampleIndicators\"]")
 WebElement carosalIndicator;
 @FindBy(xpath="//a[contains(text(),'Samsung galaxy s6')]")
 WebElement samsungGalaxyPhone;
//@FindBy(xpath="//a[text()='Home']")

@FindBy(xpath="//*[@id=\"navbarExample\"]/ul/li[1]/a")
WebElement homeButton;

//@FindBy(xpath = "//img[@alt='STORE']")
@FindBy(xpath="//*[@id=\"nava\"]/img")
private WebElement applicationLogo;


public HomePage() {   //removing WebDriver driver,bcoz driver is static in utilities
	
	PageFactory.initElements(driver, this);
}

public boolean isSignUpButtonVisible() {  //use this to check the naviagtion test case
    try {
        return signUpLink.isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public SignUp clickSignUp() {     // Clicks the Sign Up button
	signUpLink.click();
	return new SignUp();
	
}

public LoginPage clickLogin() {
    WebElement loginLink = driver.findElement(By.id("login2"));
        UtilityClass.explicitWait(driver, 10, loginLink);

        // Use normal click
        loginLink.click();

    return new LoginPage();
}


//Checks if modal is visible
public boolean isSignUpModalDisplayed() {  
 try {
     return signUpModal.isDisplayed() && signUpModalTitle.getText().contains("Sign up");
 } catch (Exception e) {
     return false;
 }
}

    public String validateHomePageTitle() {
        String expectedTitle = "PRODUCT STORE";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement productstore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nava")));
        String actualTitle = productstore.getText();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Expected Title is matched: " + actualTitle);
        } else {
            System.out.println("Expected Title is NOT matched. Expected: " + expectedTitle + ", but got: " + actualTitle);
        }

        return actualTitle;
    }
    public  void clickHomeButton() {
        
        homeButton.click();
	
    }
    
	public boolean isLogoDisplayed() {
		   System.out.println("Verify that Homepage Logo is Displayed or not.");

	   //     UtilityClass.explicitWait(driver,10, logo);  // 👈 using your wait method here   ***********doubt why driver?????/

	        return logo.isDisplayed();
	
	}
	

	 // Method to get the current application URL
    public String getAppUrl() {
        return driver.getCurrentUrl(); // Retrieves the current URL
    }
    // Method to validate if the current URL matches the expected URL
    public boolean isExpectedUrl() {
		String expectedUrl = "https://www.demoblaze.com/";  // This is the expected URL
        String actualUrl = getAppUrl();  // Get the current URL of the page
        return actualUrl.equals(expectedUrl);  // Compare actual URL with expected U
	
	}
    // Method to retrieve the welcome message
    public String getWelcomeMessage() {
        // Assuming the welcome message is inside an element with id "welcomeMessage"
        WebElement welcomeMessageElement = driver.findElement(By.id("welcomeMessage"));  // Adjust the selector
        return welcomeMessageElement.getText();  // Get the text of the element
    }
        public ProductInformationPage getPhone()
	{
		System.out.println("Verify that whether able to click on product category Phones and navigates to product categories page and navigate back to home or not");
		UtilityClass.clickOn(driver, phones);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		jse.executeScript("arguments[0].scrollIntoView(true);", htcMob);
		
		UtilityClass.JSClick(driver, htcMob);
		
		return new ProductInformationPage();
	}

	public boolean isLoginModalDisplayed() {
		 try {
	        return loginModal.isDisplayed() && loginModalTitle.getText().contains("Log in");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while checking login modal: " + e.getMessage());
	        return false;
	    }
	}
	
	public int getTotalCategories() {
	    // Initialize WebDriverWait with 10 seconds timeout
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    try {
	        // Wait until the categories are present in the DOM (ensure they are visible)
	        List<WebElement> options = wait.until(
	            ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".category-list .category")) // Update this selector based on your page structure
	        );

	        // Print category names or just the count for debugging
	        System.out.println("Total number of categories are: " + options.size());

	        // Return the number of categories
	        return options.size();
	    } catch (TimeoutException e) {
	        System.out.println("❌ Timeout occurred: Categories not loaded within the given time.");
	        return 0; // Return 0 if categories are not found within the timeout
	    }
	}

	
	    /* Returns the list of category names using UtilityClass method.
	     
	    public List<String> getCategoryNames() {
	        return UtilityClass.getTextList(options);
	    }
	*/
	// Method to get category names
    public List<String> getCategoryNames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Find the category elements (e.g., by class name or CSS selector)
        List<WebElement> options = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".category-list .category"))
        );
        
        // Use UtilityClass to extract text from the elements and return as a list
        return UtilityClass.getTextList(options);
    }
	
	
	
	
	    
	    public boolean isHomeMenuVisible() {
	        return homeMenu.isDisplayed();
	    }

	    public boolean isContactMenuVisible() {
	        return contactMenu.isDisplayed();
	    }
	    public boolean isAboutUsMenuVisible() {
	        return Aboutus.isDisplayed();
	    }
	    public void clickPhoneCategories(){
	        phoneCategories.click();
	        UtilityClass.explicitWait(driver, 5, phoneCategories); // Wait for phone categories screen to
	    }
	    public boolean isPhoneCategoriesScreenDisplayed() {
	    	 UtilityClass.explicitWait(driver, 5, phoneCategories); 
	        return phoneCategories.isDisplayed();
	    }
	    
	   // Checks if the first image is displayed
	    public boolean isFirstCarouselImageDisplayed() throws InterruptedException {
	    	Thread.sleep(2000);
	        return carouselImage1.isDisplayed();
	    }
	    public boolean isSecondCarouselImageDisplayed() {
	    
	    return carouselImage2.isDisplayed();
}
	  /*  public boolean validateAllCarouselImagesDisplayed() { //all together
	        // Optional: Add explicit waits for stability
	        UtilityClass.explicitWait(driver, 5, carouselImage1);
	        UtilityClass.explicitWait(driver, 5, carouselImage2);
	        UtilityClass.explicitWait(driver, 5, carouselImage3);

	        return carouselImage1.isDisplayed() &&
	               carouselImage2.isDisplayed() &&
	               carouselImage3.isDisplayed();
	    }*/
	    
	    
	    public boolean validateAllCarouselImagesByClicking() {
	        try {
	            WebElement nextBtn = driver.findElement(By.className("carousel-control-next"));
	            List<WebElement> slides = driver.findElements(By.cssSelector(".carousel-item"));
	            int totalSlides = slides.size();

	            for (int i = 0; i < totalSlides; i++) {
	                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                WebElement activeImage = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(
	                        By.cssSelector(".carousel-item.active img")
	                    )
	                );

	                if (!activeImage.isDisplayed()) {
	                    System.out.println("❌ Image not displayed at slide index: " + i);
	                    return false;
	                } else {
	                    System.out.println("✅ Image displayed: " + activeImage.getAttribute("src"));
	                }

	                // Click next to go to the next image (unless it's the last)
	                if (i < totalSlides - 1) {
	                    nextBtn.click();
	                    Thread.sleep(1000); // short pause for transition (can be replaced with WebDriverWait)
	                }
	            }

	            return true;

	        } catch (Exception e) {
	            System.out.println("❌ Exception while cycling through carousel: " + e.getMessage());
	            return false;
	        }
	    }

	    
	    
	    
	    
	    public boolean clickNextAndValidateSecondImage() {
	        nextButtonCarousel.click();
	        
	        // Use your utility method for explicit wait
	        UtilityClass.explicitWait(driver, 5, carouselImage2);

	        return carouselImage2.isDisplayed();
	    }
	    public boolean clickPrevButtonCarouselAndvalidateImage(){
	        prevButtonCarousel.click();
	     // Use your utility method for explicit wait
	        UtilityClass.explicitWait(driver, 10, carouselImage2);

	        return carouselImage1.isDisplayed();
	    }
	    public boolean clickPrevButtonCarouselAndValidateImage(){
	    	prevButtonCarousel.click();
		     // Use your utility method for explicit wait
		       
	        return carouselImage3.isDisplayed();
	    }
	    public boolean isHomeScreenDisplayed() {
	        // Check that home screen's carousel is visible
	        return driver.findElement(By.id("carouselExampleIndicators")).isDisplayed();
	    }
	    public void clickOnSamsungGalaxyS6()
		 {
			 samsungGalaxyPhone.click();
		 }

		public static HomePage clickSubmitButton() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isApplicationLogoVisible() {
		    return applicationLogo.isDisplayed();
		}


	}
	

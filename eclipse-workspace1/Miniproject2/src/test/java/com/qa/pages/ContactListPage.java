package com.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.ProjectSpecificationMethods;

public class ContactListPage extends ProjectSpecificationMethods {

	@FindBy(id = "logout")
	WebElement logout;

	@FindBy(id = "add-contact")
	WebElement addNewContactButton;
	
	@FindBy(xpath = "//h1[text()='Contact List']")
	WebElement contactListHeader;


	@FindBy(xpath = "//div[@class='contact-list']")
	WebElement contactList;

	@FindBy(xpath = "//div[@class='contact-list']//span[@class='contact-email']")
	WebElement contactEmail;
	
	 // Locator for a contact (assuming you're clicking by email in the list)
    @FindBy(xpath = "//*[@id=\"myTable\"]/tr[2]/td[4]")   //nirmal@gmail.com
    WebElement specificContactEmail1;  // Modify the XPath to target a contact

	@FindBy(xpath="//*[@id=\"myTable\"]/tr[2]/td[4]")
	WebElement specificContactEmail;

	public ContactListPage(WebDriver driver) {
		this.driver = driver;
		// initialize PageFactory elements
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogout() {
		return logout;
	}

	public void clickLogout() {

		logout.click();
	}

	public boolean isAddNewContactButtonVisible() {
		try {
			return addNewContactButton.isDisplayed();
		} catch (NoSuchElementException | NullPointerException e) {
			return false;
		}
	}

	public AddContactPage clickAddNewContactButton() {
		addNewContactButton.click();
		return new AddContactPage(driver);
	}

	
	public boolean isContactListHeaderVisible() {
		try {
			return contactListHeader.isDisplayed();
		} catch (NoSuchElementException | NullPointerException e) {
			return false;
		}
	}

	// Method to verify if the contact list is visible
    public boolean isContactListVisible() {
        return contactList.isDisplayed();
    }
    
 

   // 🔍 Updated method to verify if contact is in list by email
    public boolean isContactPresentByEmail(String email) {
        // Adjust XPath based on your actual HTML table structure
        String emailXPath = "//*[contains(text(),'" + email + "')]";
        List<WebElement> matches = driver.findElements(By.xpath(emailXPath));

        System.out.println("🔍 Email search results for '" + email + "': " + matches.size());

        for (WebElement element : matches) {
            System.out.println("→ Found: " + element.getText());
        }

        return !matches.isEmpty();
    }
   
    public ContactDetailsPage clickContactByEmail(String email) {
        // XPath to locate the email element in the contact list
        String emailXPath = "//*[contains(text(),'" + email + "')]";

        // Wait until the email is visible to interact with
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(emailXPath)));
        
        // Click on the found email (this will typically redirect to the contact details page)
        contactEmail.click();
        
        // Return a new instance of ContactDetailsPage
        return new ContactDetailsPage(driver);
    }

    
    
    public void waitForContactListToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
    }
    
    // Method to get last names from the contact list
    public List<String> getLastNames() {
        // Wait for the table to be visible before interacting with it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

        // Now, get the list of name elements (assuming last names are in the first column)
        List<WebElement> nameElements = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
        List<String> lastNames = new ArrayList<>();

        for (WebElement el : nameElements) {
            String fullName = el.getText().trim();
            System.out.println("📌 Raw Name Element Text: '" + fullName + "'"); // Debugging output
            if (!fullName.isEmpty()) {
                String[] parts = fullName.split(" ");
                if (parts.length > 1) {
                    lastNames.add(parts[1]); // Assuming "First Last"
                } else {
                    lastNames.add(parts[0]); // Fallback
                }
            }
        }

        // Check the last names list for debugging purposes
        System.out.println("🔍 Last names found: " + lastNames.size());
        return lastNames;
    }
    public List<String> getFullNames() {
        List<WebElement> nameElements = driver.findElements(By.xpath("//*[@id='myTable']/tr/td[2]"));
        
        List<String> fullNames = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            String name = nameElement.getText().trim(); // Clean whitespace
            if (!name.isEmpty()) {
                fullNames.add(name);
            }
        }

        return fullNames;
    }
    public EditContactPage navigateToEditContactPage() {
        WebElement editButton = driver.findElement(By.id("editButton"));  // Replace with the actual locator for the edit button
        editButton.click();
        return new EditContactPage(driver);
    }
    // Method to click the first contact's link or button to navigate to the detail page
    public ContactDetailsPage clickFirstContactByName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement firstContactCell = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//*[@id='myTable']/tr[1]/td[2]") )); 
        		// Clicks second column of the first row
       
        firstContactCell.click();
        return new ContactDetailsPage(driver);
    }
  /*  public boolean isContactPresentByEmail(String email) {
        List<WebElement> elements = driver.findElements(By.xpath("//td[text()='" + email + "']"));
        return elements.size() > 0;
    }*/
}    
    
    
    
 // Method to click on the specific known contact
  /*  public ContactDetailsPage clickSpecificContact(String email) {
        specificContactEmail.click();
        return new ContactDetailsPage(driver); // After clicking, navigate to Contact Details Page
    }

    */
    
   /* public ContactDetailsPage clickContactByEmail(String email) {  //This is for dynamic data
        // Use a dynamic XPath to locate the email cell in the table, no matter its row/column position
        String contactXPath = "//table[@id='myTable']//td[contains(text(), '" + email + "')]";

        // Wait for the email cell to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailCell = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(contactXPath)));

        // Click the email cell
        emailCell.click();

        // Return the ContactDetailsPage after the click
        return new ContactDetailsPage(driver);
    }
*/
    
    
    
    

package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class UtilityClass 
{
public static WebDriver driver;
public String sheetname;
public static ExtentReports extent;
  public static ExtentTest test;
 public String testname,testdescription,testcategory,testauthor;
  
  //In data provide r am calling this read method,so data provider will be common,so it is indside projectspecification class
public static void launchBrowser(String browser,String url) { //calling this in projectspecificationmethod

	if(browser.equalsIgnoreCase("chrome"))
	{
	
		driver =  (WebDriver) new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("edge")) {
		driver = (WebDriver) new EdgeDriver();
	}
	else {
		driver = (WebDriver) new ChromeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	
	driver.get("https://thinking-tester-contact-list.herokuapp.com/");
	
}


public void closeBrowser() { //calling this in projectspecificationmethod
	driver.close();
}



public static String[][] readExcel(String sheetname) throws IOException {
    FileInputStream fis = new FileInputStream("C:\\Users\\ADMIN\\eclipse-workspace1\\Miniproject2\\src\\test\\resources\\Data\\ContactListAppTestData.xlsx");
    XSSFWorkbook book = new XSSFWorkbook(fis);
    XSSFSheet sheet = book.getSheet(sheetname);
    int rowcount = sheet.getLastRowNum();
    short columncount = sheet.getRow(0).getLastCellNum();

    String[][] data = new String[rowcount][columncount];
    DataFormatter formatter = new DataFormatter();

    for (int i = 1; i <= rowcount; i++) {
        XSSFRow row = sheet.getRow(i);
        for (int j = 0; j < columncount; j++) {
            XSSFCell cell = row.getCell(j);
            String cellValue = (cell != null) ? formatter.formatCellValue(cell) : "";
            data[i - 1][j] = (cellValue != null) ? cellValue : "";
        }
    }

    book.close();
    fis.close();
    return data;
}

/*public static String screenShot(String name) throws IOException {
	
	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	String path="C:\\Users\\ADMIN\\eclipse-workspace1\\Miniproject2\\src\\test\\resources\\Snap\\"+name+timestamp+".png";
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File dest = new File(path); //TakeScreenshot is the interface 
	FileUtils.copyFile(src, dest);
	return path;
} */

public static String screenShot(String name) throws IOException {
	if (driver == null) {
		throw new IllegalStateException("WebDriver is not initialized.");
	}
	// Create timestamp
	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

	// Path to Snap folder inside src/test/resources/
	String folderPath = System.getProperty("user.dir") + "/src/test/resources/Snap/";

	// File name with timestamp
	String fileName = name + "_" + timestamp + ".png";

	// Full path where screenshot will be stored
	String fullPath = folderPath + fileName;

	// Create Snap folder if it doesn't exist
	File folder = new File(folderPath);
	if (!folder.exists()) {
		folder.mkdirs(); // Create the folder if it doesn't exist
	}

	// Capture screenshot
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File dest = new File(fullPath);

	// Copy screenshot to the destination path
	FileUtils.copyFile(src, dest);

	// Return relative path for reporting purposes
	return "Snap/" + fileName; // Relative path for reports to find the image
}


// Utility method to clear input elements (e.g., text fields, text areas)
public static void clearElement(WebDriver driver, By locator) {
    WebElement element = driver.findElement(locator);
    
    // Ensure the element is interactable (not hidden, not disabled, etc.)
    if (element.isEnabled() && element.isDisplayed()) {
        element.clear();  // Clear the text in the input field
    } else {
        System.out.println("❌ Element is not interactable or visible: " + locator);
    }
}

// Overloaded method to clear input element using WebElement directly
public static void clearElement(WebElement element) {
    if (element.isEnabled() && element.isDisplayed()) {
        element.clear();  // Clear the text in the input field
    } else {
        System.out.println("❌ Element is not interactable or visible: " + element);
    }
}
}



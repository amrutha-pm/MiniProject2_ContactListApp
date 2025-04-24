package com.qa.base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.utilities.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass{
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testname, testdescription, testCategory, testAuthor; 
	
	@BeforeSuite
	public void reportInitialization() {
		
		//To create report in the given location
		ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\eclipse-workspace1\\GuviMiniProject\\src\\test\\resources\\Report\\DemoblazeExtendreport.html");
		reporter.config().setReportName("Demoblaze App Report");
		
		//To capture the test data
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails() {
		
		test=extent.createTest(testname,testdescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
		
	}
	
	@Parameters({"browser","url"})
	@BeforeMethod
	public void launchingAndLoadingURL(String browser, String url) {
		
		launchBrowser(browser,url);
		
	}
	
/*	@AfterMethod
	public void browserClose() {
		
		closeBrowser();
	}
	*/
	@AfterMethod
	public void browserClose() {
	    if (driver != null) {
	        driver.quit(); // instead of close(), which keeps session active
	        driver = null;
	    }
	}

	
	
	
	
	

	
	
	/* @DataProvider(name = "readData")
	    public Object[][] signUpData() throws Exception {
	        return UtilityClass.readExcel("SignUp");  // Data for SignUp
	    }

	    @DataProvider(name = "readData1")
	    public Object[][] loginData() throws IOException {
	        return UtilityClass.readExcel("Login");  // Data for Login
	    }
*/
	
	
/*	@DataProvider(name = "readData")
	public Object[][] signUpData() throws Exception {
	  //  String filePath = System.getProperty("user.dir") + "C:/Users/ADMIN/eclipse-workspace1/GuviMiniProject/src/test/resources/Data/SignUpTestData.xlsx";
		String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Data" + File.separator + "SignUpTestData.xlsx";

		return UtilityClass.readExcel("SignUp", filePath); // Passing both sheet name and file path
	}

	@DataProvider(name = "readData1")
	public Object[][] loginData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "C:/Users/ADMIN/eclipse-workspace1/GuviMiniProject/src/test/resources/Data/LoginTestData.xlsx";
	    return UtilityClass.readExcel("Login", filePath); // Passing both sheet name and file path
	}
	
	*/
	
	
	// Data Provider to read data from the Excel sheet for SignUp
    @DataProvider(name = "readData")
    public Object[][] signUpData() throws Exception {
     //   String filePath = "src/test/resources/Data/SignUpTestData.xlsx";  // Relative file path
        String filePath = "SignUpTestData.xlsx";  // Relative file path

        return UtilityClass.readExcel("SignUp", filePath);  // Passing sheet name and file path
    }

    // Data Provider to read data from the Excel sheet for Login
    @DataProvider(name = "readData1")
    public Object[][] loginData() throws IOException {
     //   String filePath = "src/test/resources/Data/LoginTestData.xlsx";  // Relative file path
        String filePath = "LoginTestData.xlsx";  // Relative file path

        return UtilityClass.readExcel("Login", filePath);  // Passing sheet name and file path
    }

	
	
	@AfterSuite
	public void reportClose() {
		
		extent.flush();  // Mandatory if missed report will not be created.
	}

}
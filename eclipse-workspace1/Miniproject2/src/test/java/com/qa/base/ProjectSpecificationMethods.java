package com.qa.base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass{

@BeforeSuite
public void reportInitialization() {
	
	//To create report in the given location
	ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\eclipse-workspace1\\Miniproject2\\src\\test\\resources\\Report\\ContactListApp.html");
	reporter.config().setReportName("Contact List App Report");
	
	//To capture the test data
	 extent = new ExtentReports();
	extent.attachReporter(reporter);
}

@BeforeClass
public void testDetails() {
	
	test=extent.createTest(testname,testdescription);
	test.assignCategory(testcategory);
	test.assignAuthor(testauthor);
	
}
@Parameters({"browser","url"})
@BeforeMethod
public void launchingAndLoadingURL(String browser, String url) {
	
	launchBrowser(browser,url);
	
}
	/*@AfterMethod
	public void browserClose() {
		closeBrowser();
	}*/

	@AfterMethod
	public void browserClose() {
	    if (driver != null) {
	        driver.quit(); // instead of close(), which keeps session active
	        driver = null;
	    }
	}
	
	@DataProvider(name="readData")
	public String[][] readData() throws IOException {
		String [][] data = readExcel(sheetname);
		return data;
	}

	
/*	 @DataProvider(name = "readData")
	    public Object[][] signUpData() throws Exception {
	     //   String filePath = "src/test/resources/Data/SignUpTestData.xlsx";  // Relative file path
	        String filePath = "ContactListAppTestData.xlsx";  // Relative file path

	        return UtilityClass.readExcel("SignUp", filePath);  // Passing sheet name and file path
	    }	*/
}

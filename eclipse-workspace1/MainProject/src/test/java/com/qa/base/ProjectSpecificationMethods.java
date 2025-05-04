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
import com.qa.utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass{


    public static ExtentReports extent;
    public static ExtentTest test;
	
	@BeforeSuite
	public void reportInitialization() {
		  if (extent == null) {
	            // Set up the report path
		//To create report in the given location
		
		ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\ADMIN\\eclipse-workspace1\\MainProject\\src\\test\\resources\\Report\\HotelApp.html");
		reporter.config().setReportName("Hotel App Report");
		
		//To capture the test data
		
		 extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
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
	    
		
		
		@AfterSuite
		public void reportClose() {
			
			extent.flush();  // Mandatory if missed report will not be created.
		}
	}


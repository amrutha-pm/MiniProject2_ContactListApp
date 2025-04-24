package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityClass {
	public static WebDriver driver;
	public String sheetname;
	public static WebDriverWait wait;

	public void launchBrowser(String browser, String url) {
		if (driver == null) { // Ensure that driver is only initialized once
			if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(url);
		}
	}

	public void closeBrowser() {

		driver.close();
	}

	public static Object[][] readExcel(String sheetName, String filePath) throws IOException {
		// Dynamically construct the correct file path (cross-platform)
		String completeFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "Data" + File.separator + filePath;

		File file = new File(completeFilePath);

		// Check if the file exists
		if (!file.exists()) {
			System.out.println("File does not exist at: " + completeFilePath);
			throw new IOException("File not found: " + completeFilePath);
		}

		// Open the file and read the workbook
		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			// Get the specified sheet
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// If the sheet does not exist, throw an exception
			if (sheet == null) {
				throw new IOException("Sheet not found: " + sheetName);
			}

			// Get the row count and column count
			int rowCount = sheet.getPhysicalNumberOfRows(); // accurate row count
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // accurate column count

			// Prepare the 2D array to hold the data (excluding header row)
			String[][] data = new String[rowCount - 1][colCount]; // excluding header row

			// Iterate through the rows (starting from 1 to skip the header row)
			for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					XSSFCell cell = row.getCell(j);
					String cellValue = "";

					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							cellValue = cell.getStringCellValue();
							break;
						case NUMERIC:
							cellValue = String.valueOf((long) cell.getNumericCellValue());
							break;
						case BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case FORMULA:
							cellValue = cell.getCellFormula();
							break;
						case BLANK:
						default:
							cellValue = "";
							break;
						}
					}

					// Do not log here bcoz the data is stored in the 2D array.
					data[i - 1][j] = cellValue.trim(); // Store data for all rows starting from index 1
				}
			}

			return data; // Return the 2D data array

		} catch (IOException e) {
			// Detailed logging for IO exception
			System.out.println("Error occurred while reading the file: " + e.getMessage());
			throw e; // Rethrow the exception for handling at a higher level
		}
	}

	// Just kept the commented for future reference,please consider********
	/*
	 * public static Object[][] readExcel(String sheetName, String filePath) throws
	 * IOException { // Dynamically construct the correct file path (cross-platform)
	 * String completeFilePath = System.getProperty("user.dir") + File.separator +
	 * "src" + File.separator + "test" + File.separator + "resources" +
	 * File.separator + "Data" + File.separator + filePath;
	 * 
	 * File file = new File(completeFilePath);
	 * 
	 * // Check if the file exists if (!file.exists()) {
	 * System.out.println("File does not exist at: " + completeFilePath); throw new
	 * IOException("File not found: " + completeFilePath); }
	 * 
	 * // Open the file and read the workbook try (FileInputStream fis = new
	 * FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	 * 
	 * // Get the specified sheet XSSFSheet sheet = workbook.getSheet(sheetName);
	 * 
	 * // If the sheet does not exist, throw an exception if (sheet == null) { throw
	 * new IOException("Sheet not found: " + sheetName); }
	 * 
	 * // Get the row count and column count int rowCount =
	 * sheet.getPhysicalNumberOfRows(); // accurate row count int colCount =
	 * sheet.getRow(0).getPhysicalNumberOfCells(); // accurate column count
	 * 
	 * // Prepare the 2D array to hold the data String[][] data = new
	 * String[rowCount - 1][colCount]; // excluding header row
	 * 
	 * // Iterate through the rows (starting from 1 to skip the header row) for (int
	 * i = 1; i < rowCount; i++) { XSSFRow row = sheet.getRow(i); for (int j = 0; j
	 * < colCount; j++) { XSSFCell cell = row.getCell(j); String cellValue = "";
	 * 
	 * if (cell != null) { switch (cell.getCellType()) { case STRING: cellValue =
	 * cell.getStringCellValue(); break; case NUMERIC: cellValue =
	 * String.valueOf((long) cell.getNumericCellValue()); break; case BOOLEAN:
	 * cellValue = String.valueOf(cell.getBooleanCellValue()); break; case FORMULA:
	 * cellValue = cell.getCellFormula(); break; case BLANK: default: cellValue =
	 * ""; break; } }
	 * 
	 * // Log the value for debugging purposes System.out.println("Row " + i +
	 * ", Column " + j + ": " + cellValue);
	 * 
	 * // Store the data in the 2D array data[i - 1][j] = cellValue.trim(); } }
	 * 
	 * return data; // Return the 2D data array
	 * 
	 * } catch (IOException e) { // Detailed logging for IO exception
	 * System.out.println("Error occurred while reading the file: " +
	 * e.getMessage()); throw e; // Rethrow the exception for handling at a higher
	 * level } }
	 * 
	 */

	/*
	 * this is working with new update public static Object[][] readExcel(String
	 * sheetName, String filePath) throws IOException { // Dynamically construct the
	 * file path (cross-platform) // String filePath =
	 * System.getProperty("user.dir") + File.separator + "src" + File.separator +
	 * "test" + File.separator + "resources" + File.separator + "Data" +
	 * File.separator + "SignUpTestData.xlsx"; File file = new File(filePath);
	 * 
	 * // Check if the file exists if (!file.exists()) {
	 * System.out.println("File does not exist at: " + filePath); throw new
	 * IOException("File not found: " + filePath); }
	 * 
	 * // Open the file and read the workbook try (FileInputStream fis = new
	 * FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	 * 
	 * // Get the specified sheet XSSFSheet sheet = workbook.getSheet(sheetName);
	 * 
	 * // If the sheet does not exist, throw an exception if (sheet == null) { throw
	 * new IOException("Sheet not found: " + sheetName); }
	 * 
	 * // Get the row count and column count int rowCount =
	 * sheet.getPhysicalNumberOfRows(); // accurate row count int colCount =
	 * sheet.getRow(0).getPhysicalNumberOfCells(); // accurate column count
	 * 
	 * // Prepare the 2D array to hold the data String[][] data = new
	 * String[rowCount - 1][colCount]; // excluding header row
	 * 
	 * // Iterate through the rows (starting from 1 to skip the header row) for (int
	 * i = 1; i < rowCount; i++) { XSSFRow row = sheet.getRow(i); for (int j = 0; j
	 * < colCount; j++) { XSSFCell cell = row.getCell(j); String cellValue = "";
	 * 
	 * if (cell != null) { switch (cell.getCellType()) { case STRING: cellValue =
	 * cell.getStringCellValue(); break; case NUMERIC: cellValue =
	 * String.valueOf((long) cell.getNumericCellValue()); break; case BOOLEAN:
	 * cellValue = String.valueOf(cell.getBooleanCellValue()); break; case FORMULA:
	 * cellValue = cell.getCellFormula(); break; case BLANK: default: cellValue =
	 * ""; break; } }
	 * 
	 * // Log the value for debugging purposes System.out.println("Row " + i +
	 * ", Column " + j + ": " + cellValue);
	 * 
	 * // Store the data in the 2D array data[i - 1][j] = cellValue.trim(); } }
	 * 
	 * return data; // Return the 2D data array
	 * 
	 * } catch (IOException e) { // Detailed logging for IO exception
	 * System.out.println("Error occurred while reading the file: " +
	 * e.getMessage()); throw e; // Rethrow the exception for handling at a higher
	 * level } }
	 * 
	 */

	/*
	 * public static Object[][] readExcel(String sheetName) throws IOException {
	 * String filePath =
	 * "C:\\Users\\ADMIN\\eclipse-workspace1\\GuviMiniProject\\src\\test\\resources\\Data\\SignUpTestData.xlsx";
	 * File file = new File(filePath); FileInputStream fis = new
	 * FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis);
	 * XSSFSheet sheet = workbook.getSheet(sheetName);
	 * 
	 * int rowCount = sheet.getLastRowNum(); // total rows excluding header int
	 * colCount = sheet.getRow(0).getLastCellNum(); // total columns
	 * 
	 * String[][] data = new String[rowCount][colCount];
	 * 
	 * for (int i = 1; i <= rowCount; i++) { XSSFRow row = sheet.getRow(i); for (int
	 * j = 0; j < colCount; j++) { XSSFCell cell = row.getCell(j); String cellValue
	 * = "";
	 * 
	 * if (cell != null) { switch (cell.getCellType()) { case STRING: cellValue =
	 * cell.getStringCellValue(); break; case NUMERIC: cellValue =
	 * String.valueOf((long) cell.getNumericCellValue()); break; case BOOLEAN:
	 * cellValue = String.valueOf(cell.getBooleanCellValue()); break; case FORMULA:
	 * cellValue = cell.getCellFormula(); break; case BLANK: default: cellValue =
	 * ""; break; } } data[i - 1][j] = cellValue.trim(); } }
	 * 
	 * workbook.close(); fis.close(); return data; }
	 * 
	 */

	/*
	 * public static String screenShot(String name) throws IOException {
	 * 
	 * String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new
	 * Date()); String
	 * path="C:\\Users\\ADMIN\\eclipse-workspace1\\GuviMiniProject\\src\\test\\resources\\Snap\\" + name + timestamp + "
	 * .png"; File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * File dest = new File(path); FileUtils.copyFile(src, dest); return path; }
	 */
	public static void clearAndSendKeys(WebElement element, String value) {
		element.clear(); // Clears any pre-filled value
		element.sendKeys(value); // Sends new value
	}

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

	public static void explicitWaitToClick(WebDriver driver, int timeout, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	public static void explicitWait(WebDriver driver, int Seconds, WebElement ele) { // Use WebDriverWait with proper
																						// constructor

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void JSClick(WebDriver driver, WebElement ele) {
		UtilityClass.explicitWait(driver, 10, ele);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", ele);

	}

	public static void clickOn(WebDriver driver, WebElement ele) {
		UtilityClass.explicitWait(driver, 10, ele);
		ele.click();
	}
	/*
	 * public static List<String> getTextList(List<WebElement> elements) {
	 * List<String> textList = new ArrayList<>();
	 * 
	 * for (WebElement ele : elements) { String text = ele.getText().trim(); // trim
	 * to remove extra spaces if (!text.isEmpty()) { textList.add(text); } }
	 * 
	 * return textList; }
	 */

	// Utility method to extract text from a list of WebElements
	public static List<String> getTextList(List<WebElement> elements) {
		return elements.stream().map(WebElement::getText).collect(Collectors.toList());
	}
}

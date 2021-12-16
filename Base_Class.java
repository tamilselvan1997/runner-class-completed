package com.Mini_Project_Dec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Base_Class {

	public static WebDriver driver;
	
	public static String value;

	public static WebDriver getbrowser(String type) {

		if (type.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Driver\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (type.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\Driver\\Driver\\gecko.exe");

			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();

		return driver;
	}

	public static void url(String url) {
		driver.get(url);
	}

	public static void clickOnElement(WebElement element) {
		element.click();
	}

	public static void inputValueElement(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void thread(int seconds) throws InterruptedException {
		Thread.sleep(seconds);

	}

	public static void screenshot(String pathname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File destination = new File(pathname);

		FileUtils.copyFile(source, destination);

	}

	public static void javascript(String type, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (type.equalsIgnoreCase("scrollintoview")) {
			js.executeScript("arguments[0].scrollIntoView();", element);
		}

	}

	public static void scroll(String type) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (type.equalsIgnoreCase("scrollby")) {
			js.executeScript("window.scrollBy(0,-500);");

		}

	}

	public static void frame(WebElement element) {
		driver.switchTo().frame(element);

	}

	public static void defaultcontent() {
		driver.switchTo().defaultContent();
	}

	public static void dropdown(String type, WebElement element, String value) {

		Select s = new Select(element);

		if (type.equalsIgnoreCase("value")) {

			s.selectByValue(value);
		}

		else if (type.equalsIgnoreCase("index")) {

			int index = Integer.parseInt(value);

			s.selectByIndex(index);

		} else if (type.equalsIgnoreCase("text")) {
			s.selectByVisibleText(value);

		}

	}
	public static String particular_data(String path,int row_index,int cell_index) throws IOException {
		
		File f = new File(path);
		
		FileInputStream fis = new FileInputStream(f);
		
		Workbook wb = new XSSFWorkbook(fis);
		
		Sheet sheetAt = wb.getSheetAt(0);
		
		Row row = sheetAt.getRow(row_index);
		
		Cell cell = row.getCell(cell_index);
		
		CellType cellType = cell.getCellType();
		
		if (cellType.equals(cellType.STRING)) {
			
			value=cell.getStringCellValue();
			
		}
		else if (cellType.equals(cellType.NUMERIC)) {
			
			double cellValue = cell.getNumericCellValue();
			
			 value =String.valueOf(cellValue);
			
		}
		return value;
		

	}

}

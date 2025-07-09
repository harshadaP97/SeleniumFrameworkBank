package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

    // Timeout values for page load and implicit waits
    static public long page_load_timeout = 20;
    static public long implicit_wait = 10;
    
    static Workbook book;
    static org.apache.poi.ss.usermodel.Sheet sheet;
    
    // Reads test data from Excel sheet and returns it as a 2D Object array
    public static Object[][] getUserDataFromExcel(String sheetName) {
        FileInputStream fis = null;
        try {
            // Relative path to the Excel file inside project directory
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/crm/qa/testdata/Book1.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            book = WorkbookFactory.create(fis);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        
        sheet = book.getSheet(sheetName);
        
        // Initialize 2D array to hold data (excluding header row)
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        
        // Iterate through rows and cells to populate the data array
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        
        return data;
    }
    
    // Captures and saves a screenshot with a timestamped filename (used manually)
    public static void takeScreenshot() {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/src/test/resources/Screenshot"
                    + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(scrFile, new File(path));
        return path;
    }

    
    
    
}

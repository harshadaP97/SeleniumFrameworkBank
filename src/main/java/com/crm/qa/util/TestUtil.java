package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	static public long page_load_timeout=20;
	static public long implicit_wait=10;
	static Workbook book;
	static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public static Object[][] getUserDataFRomExcel(String s)
	{
		FileInputStream fis =null;
		try {
			fis= new FileInputStream("E:\\HarshadaStudy\\Selenium\\SeleniumFrameworkTest\\src\\main\\java\\com\\crm\\qa\\testdata\\Book1.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 try {
		book = WorkbookFactory.create(fis);
	} catch (InvalidFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 sheet = book.getSheet(s);
	 
	 Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	 
	 for(int i=0;i<sheet.getLastRowNum();i++)
	 {
		 for(int k=0;k<sheet.getRow(0).getLastCellNum();k++)
		 {
			 data[i][k]=sheet.getRow(i+1).getCell(k).toString();
		 }
	 }
	 
	 return data;
	}
	
	public static void takeSceenshot()
	{
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f,new File("E:\\HarshadaStudy\\Selenium\\SeleniumFrameworkTest\\src\\test\\resources\\Screenshot"
			+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	
	
}

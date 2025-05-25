package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.crm.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;

	public TestBase()   
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void init()
	{
		String bName = prop.getProperty("browser");
		String driverPath = "E:\\HarshadaStudy\\Selenium\\chromedriver.exe";

		if(bName.equals("chrome"))
		{
			System.out.println(driverPath);
			System.setProperty("webdriver.chrome.driver",driverPath);
			
			driver = new ChromeDriver();
		}
		else if(bName.equals("FF"))
		{
			//System.setProperty("webdriver.gecho.driver","/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");
			driver= new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	

@AfterMethod
public void tearDown(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
        try {
            TestUtil.takeScreenshotAtEndOfTest();
            System.out.println("Screenshot taken on failure.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    driver.close();
}

}

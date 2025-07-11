/* Ms. Harshada Patil */

package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.crm.qa.util.TestUtil;

public class TestBase {

    // WebDriver instance used across tests
    public static WebDriver driver;

    // Properties object to load config data
    public static Properties prop;

    // Constructor to load configuration properties from file
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties"
            );
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize WebDriver based on browser specified in properties
    public static void init() {
        String bName = prop.getProperty("browser");

        if (bName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (bName.equalsIgnoreCase("FF")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(); // FirefoxOptions can be added similarly
        }

        // Set up browser window and timeouts
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);

        // Navigate to the URL from config
        driver.get(prop.getProperty("url"));
    }

    // Tear down method to quit driver and capture screenshot on test failure
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

        if (driver != null) {
            driver.quit();
        }
    }
}

package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



public class LogUtil implements IReporter {

	public static String logsPath = System.getProperty("user.dir") + "\\logs\\RunLogs\\";

	public static String fullClassName, className, methodName;
	public static int genericNumber, lineNumber;
	public static String application_url, defaultItemOption, monthlyPayment, erroMsg, Testcase, persona, sheetName,
			path, screenshotPath, stream, skip = null;
	


	/************************
	 * @Purpose- To create day wise log folder
	 *******************************/

	static {

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy_MM_dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String date = dateFormat1.format(new Date());
		String dateTime = dateFormat2.format(new Date());
		String currentDayLogFolder = "Logs_" + date;
		String currentRunLogFolder = currentDayLogFolder + "\\Log_" + dateTime;
		String currentDayLogFolderPath = logsPath + currentDayLogFolder;
		String currentRunLogFolderPath = logsPath + currentRunLogFolder;

		File file1 = new File(currentDayLogFolderPath);
		boolean folderGenerated = file1.mkdir();
		try {
			if (!file1.exists()) {
				if (folderGenerated) {
					System.out.println("Log folder for day created successfully");
				} else {
					System.out.println("Couldn�t create new log folder of a day");
				}
			} else {
				System.out.println("Log folder for day already exists");
			}
			File file2 = new File(currentRunLogFolderPath);
			folderGenerated = file2.mkdir();
			if (folderGenerated) {
				System.out.println("Run log folder created successfully");
			} else {
				System.out.println("Couldn't create new run log folder");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.setProperty("LOG_FOLDER", currentRunLogFolder);
		System.setProperty("current.date.time", dateTime);

	}

	public static Logger log4j(String name) {
		Logger logger = Logger.getLogger(name);
		return logger;
	}

	/************************
	 * @Purpose- Handling logs
	 *******************************/

	public static void logInfo(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.info(print);
	}

	public static void logError(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.error(print);
	}

	public static void logWarn(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.warn(print);
	}

	public static void logDebug(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.debug(print);
	}

	public static void logFatal(String message) {
		fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		Logger logger = LogManager.getLogger(className);
		String print = lineNumber + " - " + message;
		logger.fatal(print);
	}
}

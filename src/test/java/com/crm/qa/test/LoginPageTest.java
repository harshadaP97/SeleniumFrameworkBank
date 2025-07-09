package com.crm.qa.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.apache.log4j.Logger;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
    Logger log = Logger.getLogger(LoginPage.class);

	
	// Constructor to initialize properties from TestBase
	public LoginPageTest() {
		super();
	}
	
	// Setup method to initialize browser and page objects before each test
	@BeforeMethod
	public void setup() {
		init();
		loginPage = new LoginPage();
	}
	
	// Test to validate successful login and verify home page content
	@Test()
	public void validateLogin() {
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals("Accounts Overview", homePage.validateHomePage());
        log.info("Login successfully with user: " + prop.getProperty("username"));

	}
	
	// Disabled test: validate login page title
	@Test(enabled=false)
	public void validateLoginPageTitle() {
		Assert.assertEquals(loginPage.validatePageTile(), "ParaBank | Welcome | Online Banking");
	}
	
	// Disabled test: validate navigation to register page
	@Test(enabled=false)
	public void validateGotoRegister() {
		Assert.assertEquals(loginPage.gotoRegisterUser(), "Signing up is easy!");
	}
	
	// DataProvider to fetch user registration data from Excel
	@DataProvider
	public Object[][] getUserData() {
		Object[][] data = TestUtil.getUserDataFromExcel("User");
		return data;
	}
	
	// Test to register new users with data from Excel and verify welcome message
	@Test(dataProvider = "getUserData")
	public void registerNewUser(String f, String l , String a, String c, String s, String z, String p, 
			String ssn, String u, String pwds) {
		loginPage.gotoRegisterUser();
		String welcomeText = loginPage.registerUser(f, l, a, c, s, z, p, ssn, u, pwds);
		Assert.assertEquals(welcomeText, "Welcome "+u);
	}
	
	// Demo method (can be removed or used for testing purpose)
	public void logintestdemo() {
		System.out.println("From loginpage");
	}
}

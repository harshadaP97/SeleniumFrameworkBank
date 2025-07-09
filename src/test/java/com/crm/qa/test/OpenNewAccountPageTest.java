package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.OpenNewAccountPage;

public class OpenNewAccountPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	OpenNewAccountPage openNew;
	
	// Constructor to load properties from TestBase
	public OpenNewAccountPageTest() {
		super();
	}
	
	// Setup method to initialize browser and navigate to Open New Account page before each test
	@BeforeMethod
	public void setUp() {
		init();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		openNew = homePage.gotoOpenNewAccount();
	}
	
	// Test to verify the Open New Account page title
	@Test
	public void validateOpenAccountPage() {
		Assert.assertEquals("Open New Account", openNew.validateopenAccountPage());
	}
	
}

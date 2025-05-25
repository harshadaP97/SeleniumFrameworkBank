package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
	
	public OpenNewAccountPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		init();
		
		loginPage=new LoginPage();
		homePage = loginPage.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		openNew = homePage.gotoOpenNewAccount();
		
	}
	
	@Test
	public void validateOpenAccountPage()
	{
		
		Assert.assertEquals("Open New Account", openNew.validateopenAccountPage());
	}
	
}

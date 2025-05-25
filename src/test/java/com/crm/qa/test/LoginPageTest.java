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

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		init();
		loginPage=new LoginPage();
	}
	
	@Test()
	public void validateLogin()
	{
		homePage= loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals("Accounts Overview", homePage.validateHomePage());
			
	}
	
	@Test(enabled=false)
	public void validateLoginPageTitle()
	{
		Assert.assertEquals(loginPage.validatePageTile(),"ParaBank | Welcome | Online Banking");
	}
	
	@Test(enabled=false)
	public void validateGotoRegister()
	{
		Assert.assertEquals(loginPage.gotoRegisterUser(),"Signing up is easy!");
	}
	
	@DataProvider
	public Object[][] getUserData()
	{
		Object[][] data=TestUtil.getUserDataFRomExcel("User");
		return data;
	}
	
	@Test(dataProvider = "getUserData")
	public void registerNewUser(String f, String l , String a, String c, String s, String z, String p, 
			String ssn, String u, String pwds)
	{
		loginPage.gotoRegisterUser();
		String v=loginPage.registerUser(f,  l ,  a,  c,  s,  z,  p, 
				 ssn,  u,  pwds);
		Assert.assertEquals(v, "Welcome "+u);
	
	}
	
	
	public void logintestdemo()
	{
		System.out.println("FRom loginpage");
	}
}

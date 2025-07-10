package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.OpenNewAccountPage;

public class HomePageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    OpenNewAccountPage openNew;

    // Constructor calls TestBase constructor to load properties
    public HomePageTest() {
        super();
    }

    // Setup before each test: initialize browser and login
    @BeforeMethod
    public void setup() {
        init();
        loginPage = new LoginPage();
        homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    // Test logout functionality and verify page title after logout
    @Test
    public void validateLogout() {
        loginPage = homePage.validateLogout();
        Assert.assertEquals("ParaBank | Welcome | Online Banking", loginPage.validatePageTile());
    }

    // Test navigation to Open New Account page
    @Test
    public void validateGotoNewAccount() {
        openNew = homePage.gotoOpenNewAccount();
    }

}

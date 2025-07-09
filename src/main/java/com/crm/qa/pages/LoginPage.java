package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.WaitUtil;

public class LoginPage extends TestBase {
	
    WaitUtil waitUtil;

    // Login page fields
    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginButton;

    // Register link/button
    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;

    // Registration form fields
    @FindBy(css = "input#customer\\.firstName")
    WebElement fName;

    @FindBy(css = "input#customer\\.lastName")
    WebElement lName;

    @FindBy(id = "customer.address.street")
    WebElement address;

    @FindBy(id = "customer.address.city")
    WebElement city;

    @FindBy(id = "customer.address.state")
    WebElement state;

    @FindBy(id = "customer.address.zipCode")
    WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    WebElement phone;

    @FindBy(id = "customer.ssn")
    WebElement ssnNumber;

    @FindBy(id = "customer.username")
    WebElement uname;

    @FindBy(id = "customer.password")
    WebElement pwd;

    @FindBy(id = "repeatedPassword")
    WebElement cPwd;

    // Registration page title element
    @FindBy(xpath = "//h1[@class='title' and contains(text(),'Signing up is easy!')]")
    WebElement registerTitle;

    // Register user submit button
    @FindBy(xpath = "//input[@class='button' and @value='Register']")
    WebElement registerUserButton;

    // Constructor initializes WebElements
    public LoginPage() {
        PageFactory.initElements(driver, this);
        waitUtil = new WaitUtil(driver); 

    }

    // Returns the page title
    public String validatePageTile() {
        return driver.getTitle();
    }

    // Performs login and returns HomePage object
    public HomePage validateLogin(String un, String pwd) {
    	waitUtil.waitForElementVisible(username, 10).sendKeys(un);
         waitUtil.waitForElementVisible(password, 10).sendKeys(pwd);
         waitUtil.waitForElementClickable(loginButton, 10).click();
         return new HomePage();
    }

    // Navigates to register page and returns the register page title text
    public String gotoRegisterUser() {
        registerButton.click();
        return registerTitle.getText();
    }

    // Fills the registration form, submits and returns confirmation text
    public String registerUser(String f, String l, String a, String c, String s, String z, String p,
                               String ssn, String u, String pwds) {
        fName.sendKeys(f);
        lName.sendKeys(l);
        address.sendKeys(a);
        city.sendKeys(c);
        state.sendKeys(s);
        zipCode.sendKeys(z);
        phone.sendKeys(p);
        ssnNumber.sendKeys(ssn);
        uname.sendKeys(u);
        pwd.sendKeys(pwds);
        cPwd.sendKeys(pwds);
        registerUserButton.click();

        // Returns dynamic confirmation header containing the username
        return driver.findElement(By.xpath("//h1[@class='title' and contains(text(),'" + u + "')]")).getText();
    }
}

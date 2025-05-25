package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerButton;
	
	@FindBy(css = "input#customer\\.firstName")
	WebElement fName;
	
	@FindBy(css="input#customer\\.lastName")
	WebElement lName;
	
	@FindBy(id="customer.address.street")
	WebElement address;
	
	@FindBy(id="customer.address.city")
	WebElement city;
	
	@FindBy(id="customer.address.state")
	WebElement state;
	
	@FindBy(id="customer.address.zipCode")
	WebElement zipCode;
	
	@FindBy(id="customer.phoneNumber")
	WebElement phone;
	
	@FindBy(id="customer.ssn")
	WebElement ssnNumber;
	
	@FindBy(id="customer.username")
	WebElement uname;
	
	@FindBy(id="customer.password")
	WebElement pwd;
		
	@FindBy(id="repeatedPassword")
	WebElement cPwd;
	
	@FindBy(xpath="//h1[@class='title' and contains(text(),'Signing up is easy!')]")
	WebElement registerTitle;
	
	@FindBy(xpath="	//input[@class='button' and @value='Register']")
	WebElement registerUserButton;
	
	
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validatePageTile()
	{
		return driver.getTitle();
	}
	
	public HomePage validateLogin(String un,String pwd)
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		return new HomePage();
	}
	
	public String gotoRegisterUser()
	{
		registerButton.click();
		return registerTitle.getText();
		
	}
	
	public String registerUser(String f, String l , String a, String c, String s, String z, String p, 
			String ssn, String u, String pwds)
	{
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
		return driver.findElement(By.xpath("//h1[@class='title' and contains(text(),'"+u+"')]")).getText();
	}
	
	
	
}

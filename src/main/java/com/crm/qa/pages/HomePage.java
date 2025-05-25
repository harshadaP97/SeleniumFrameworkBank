package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//a[text()='Log Out']")
	WebElement logout;
	
	@FindBy(xpath="//div[@id='showOverview']//h1")
	WebElement accountOverview;
	
	@FindBy(xpath="//a[text()='Open New Account']")
	WebElement openNew;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
		
	}
	
	public LoginPage validateLogout()
	{
		logout.click();
		return new LoginPage();
	}
	
	public String validateHomePage()
	{
		return accountOverview.getText();
	}
	
	public OpenNewAccountPage gotoOpenNewAccount()
	{
		openNew.click();
		return new OpenNewAccountPage();
	}
	
}

package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class OpenNewAccountPage extends TestBase {

	@FindBy(xpath=" //h1[text()='Open New Account']")
	WebElement openAccounttitle;
	
	public OpenNewAccountPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateopenAccountPage()
	{
		return openAccounttitle.getText();
	}
}

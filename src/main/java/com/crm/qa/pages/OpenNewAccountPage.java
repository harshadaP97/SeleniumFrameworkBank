package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class OpenNewAccountPage extends TestBase {

    // Locator for the page title
    @FindBy(xpath = "//h1[text()='Open New Account']")
    WebElement openAccounttitle;

    // Constructor initializes WebElements
    public OpenNewAccountPage() {
        PageFactory.initElements(driver, this);
    }

    // Returns the title text of the Open New Account page
    public String validateopenAccountPage() {
        return openAccounttitle.getText();
    }
}

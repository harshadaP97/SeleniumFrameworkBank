package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

    // Locator for the logout link/button
    @FindBy(xpath = "//a[text()='Log Out']")
    WebElement logout;

    // Locator for account overview heading text
    @FindBy(xpath = "//div[@id='showOverview']//h1")
    WebElement accountOverview;

    // Locator for "Open New Account" link
    @FindBy(xpath = "//a[text()='Open New Account']")
    WebElement openNew;

    // Constructor initializes WebElements using PageFactory
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // Clicks logout and returns LoginPage object
    public LoginPage validateLogout() {
        logout.click();
        return new LoginPage();
    }

    // Returns the text from the account overview heading
    public String validateHomePage() {
        return accountOverview.getText();
    }

    // Navigates to Open New Account page and returns its page object
    public OpenNewAccountPage gotoOpenNewAccount() {
        openNew.click();
        return new OpenNewAccountPage();
    }
}

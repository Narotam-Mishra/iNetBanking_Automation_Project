package com.iNetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver lwd;
	
	public LoginPage(WebDriver rwd)
	{
		lwd = rwd;
		PageFactory.initElements(rwd, this);
	}

	@FindBy(xpath="//input[@name='uid']")
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='btnLogin']")
	WebElement btnLogin;
		
	@FindBy(xpath="//a[normalize-space()='Log out']")
	WebElement logoutLink;
	
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);	
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmitButton() throws InterruptedException {
		btnLogin.click();
		Thread.sleep(2000);
	}
	
	public void clickLogout()
	{
		logoutLink.click();
	}
}

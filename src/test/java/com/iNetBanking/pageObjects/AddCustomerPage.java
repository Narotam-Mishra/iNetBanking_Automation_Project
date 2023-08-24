package com.iNetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("unused")
public class AddCustomerPage {

	WebDriver lwd;

	public AddCustomerPage(WebDriver rwd) {
		lwd = rwd;
		PageFactory.initElements(rwd, this);
	}

	@FindBy(xpath = "//a[normalize-space()='New Customer']")
	@CacheLookup
	WebElement lnkAddNewCustomer;

	@FindBy(xpath = "//input[@name='name']")
	@CacheLookup
	WebElement txtCustomerName;

	@FindBy(xpath = "//input[@value='m']")
	@CacheLookup
	WebElement rdGender;

	@FindBy(xpath = "//input[@id='dob']")
	@CacheLookup
	WebElement txtdob;

	@FindBy(xpath = "//textarea[@name='addr']")
	@CacheLookup
	WebElement txtAddress;

	@FindBy(xpath = "//input[@name='city']")
	@CacheLookup
	WebElement txtCity;

	@FindBy(xpath = "//input[@name='state']")
	@CacheLookup
	WebElement txtState;

	@FindBy(xpath = "//input[@name='pinno']")
	@CacheLookup
	WebElement txtPinNo;

	@FindBy(xpath = "//input[@name='telephoneno']")
	@CacheLookup
	WebElement txtMobileNo;

	@FindBy(xpath = "//input[@name='emailid']")
	@CacheLookup
	WebElement txtEmailId;

	@FindBy(xpath = "//input[@name='password']")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath = "//input[@name='sub']")
	@CacheLookup
	WebElement btnSubmit;
	
	@FindBy(id = "google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0")
	@CacheLookup
	WebElement f1;
	
	@FindBy(id = "ad_iframe")
	@CacheLookup
	WebElement f2;
	
	@FindBy(xpath = "//div[@id='dismiss-button']/div/span")
	@CacheLookup
	WebElement dismissBtn;


	// action methods for each web element
	
	public void closeAdsContent() throws InterruptedException {
//		WebElement frame1 = lwd.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
//		lwd.switchTo().frame(frame1);
//		WebElement frame2 = lwd.findElement(By.id("ad_iframe"));
//		lwd.switchTo().frame(frame2);
//		lwd.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
//		lwd.switchTo().defaultContent();
//		Thread.sleep(3000);
		
		lwd.switchTo().frame(f1);
		lwd.switchTo().frame(f2);
		dismissBtn.click();
		lwd.switchTo().defaultContent();
		Thread.sleep(3000);
	}

	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}

	public void custName(String cname) {
		txtCustomerName.sendKeys(cname);
	}

	public void custGender(String cgender) {
		rdGender.click();
	}

	public void custDob(String mm, String dd, String yy) {
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
	}

	public void custAddress(String cAdd) {
		txtAddress.sendKeys(cAdd);
	}

	public void custCity(String ccity) {
		txtCity.sendKeys(ccity);
	}

	public void cState(String stateName) {
		txtState.sendKeys(stateName);
	}

	public void cPinNo(String pinno) {
		txtPinNo.sendKeys(String.valueOf(pinno));
	}

	public void cMobileNo(String mobileNo) {
		txtMobileNo.sendKeys(mobileNo);
	}

	public void cEmailId(String email) {
		txtEmailId.sendKeys(email);
	}

	public void cPassword(String pass) {
		txtPassword.sendKeys(pass);
	}

	public void custClickSubmitBtn() {
		btnSubmit.click();
	}
}

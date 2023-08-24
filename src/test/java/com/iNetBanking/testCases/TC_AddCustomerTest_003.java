package com.iNetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking.pageObjects.AddCustomerPage;
import com.iNetBanking.pageObjects.LoginPage;

@SuppressWarnings("unused")
public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(wd);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmitButton();
		logg.info("Login successful for adding new customer");
				
		AddCustomerPage addCust = new AddCustomerPage(wd);
		addCust.clickAddNewCustomer();
		
		//remove ads
		addCust.closeAdsContent();
		String genName = generateRandomString();
		addCust.custName(genName);
		addCust.custGender("female");
		addCust.custDob("11", "13", "1998");
		addCust.custAddress("India");
		addCust.custCity("Pune");
		addCust.cState("Maharastra");
		addCust.cPinNo("562037");
		addCust.cMobileNo("9876421034");
		
		//generate random string using generateRandomString()
		String genEmail = generateRandomString()+"@gmail.com";
		addCust.cEmailId(genEmail);
		
		//generate random password 
		String genPassword = generateRandomString() + "@dev" +generateAlphaNumeric();
		addCust.cPassword(genPassword);
		
		addCust.custClickSubmitBtn();
		Thread.sleep(2000);
		
		logg.info("Validation for Add Customer test started");
		boolean res = wd.getPageSource().contains("Customer Registered Successfully!!!");
	    
		if(res == true) {
			Assert.assertTrue(true);
			logg.info("Add Customer test passed!!!");
		}else {
			logg.info("Add Customer test failed!");
			captureScreen(wd, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}

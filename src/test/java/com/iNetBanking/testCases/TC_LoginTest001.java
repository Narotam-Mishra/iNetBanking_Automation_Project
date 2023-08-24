package com.iNetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking.pageObjects.LoginPage;

public class TC_LoginTest001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException
	{
		logg.info("URL is opened");
		
		LoginPage lp = new LoginPage(wd);
		lp.setUserName(username);
		logg.info("entered username");
		
		lp.setPassword(password);
		logg.info("entered password");
		
		lp.clickSubmitButton();
		logg.info("submit button clicked");
		
		if(wd.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logg.info("login Test passed!!!");
		}
		else {
			captureScreen(wd, "loginTest");
			Assert.assertTrue(false);
			logg.info("login Test failed!");
		}
	}
}

package com.iNetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.iNetBanking.pageObjects.LoginPage;
import com.iNetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(wd);
		lp.setUserName(user);
		logg.info("username entered from excel");
		
		lp.setPassword(pwd);
		logg.info("password entered from excel");
		
		lp.clickSubmitButton();
		
		Thread.sleep(3000);
		
		if(isAlertPresent() == true) {
			wd.switchTo().alert().accept(); //close alert
			wd.switchTo().defaultContent();
			Assert.assertTrue(false);
			logg.warn("Login failed");
		}
		else {
			Assert.assertTrue(true);
			logg.info("Login test passed!");
			lp.clickLogout();
			Thread.sleep(2000);
			wd.switchTo().alert().accept(); //close logout alert
		}
	}
	
	//check alert is present or not
	public boolean isAlertPresent()
	{
		try {
			wd.switchTo().alert();
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/iNetBanking/testData/LoginData.xlsx";
	    
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j); //1 0	
			}
		}
		return loginData;
	}
}

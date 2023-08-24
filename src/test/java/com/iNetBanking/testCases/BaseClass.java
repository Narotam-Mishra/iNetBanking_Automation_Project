package com.iNetBanking.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.iNetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class BaseClass {
	
//	public String baseURL = "https://demo.guru99.com/v4/";
//	public String username = "mngr514503";
//	public String password = "adUvYmy";

	ReadConfig rconfig = new ReadConfig();
	public String baseURL = rconfig.getApplicationURL();
	public String username = rconfig.getUsername();
	public String password = rconfig.getPassword();
		
	public static WebDriver wd;
	public static Logger logg;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browserName) 
	{
		logg = LogManager.getLogger(BaseClass.class);
		PropertyConfigurator.configure("Log4j.properties");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", rconfig.getChromePath());
			wd = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver", rconfig.getEdgePath());
			wd = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", rconfig.getFirefoxPath());
			//WebDriverManager.firefoxdriver().setup();
			wd = new FirefoxDriver();
		}
		wd.get(baseURL);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown()
	{
		wd.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File tar = new File(System.getProperty("user.dir")+ "/Screenshots/" + tname +".png");
		FileUtils.copyFile(src, tar);
		System.out.println("Screenshot captured!!");
	}
	
	public String generateRandomString()
	{
		String genString = RandomStringUtils.randomAlphabetic(7);
		return genString;
	}
	
	public String generateAlphaNumeric()
	{
		String getAlphaNum = RandomStringUtils.randomAlphanumeric(4);
		return getAlphaNum;
	}
}

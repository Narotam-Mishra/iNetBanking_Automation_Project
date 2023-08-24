package com.iNetBanking.utilities;

/*
 * this is a Listener class used to generate Extent reports
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlRepo;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext textContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //for time stamp
		String repName = "Test-Report"+timeStamp+".html";
		
		htmlRepo = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName); //specify location
		htmlRepo.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
	
		extent = new ExtentReports();
		extent.attachReporter(htmlRepo);
		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "narmishr");
		
		htmlRepo.config().setDocumentTitle("i-Netbanking Test Project"); //title of report
		htmlRepo.config().setReportName("Functional Test Automation Report"); //name of the report
		htmlRepo.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlRepo.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); //send the passed information
		
		String ssPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName() + ".png";
		
		File f = new File(ssPath);
		
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(ssPath));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
			
}

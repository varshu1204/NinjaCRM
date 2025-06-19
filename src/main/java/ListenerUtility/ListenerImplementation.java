package ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassConfig.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener{

	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		
		Reporter.log("Report configuration",true);
		Date d=new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");	
		 spark=new ExtentSparkReporter("./AdvanceReports/report_"+newDate+".html");
		 spark.config().setDocumentTitle("NinzaCRM Test Suite Results");
		 spark.config().setReportName("CRM Report");
		 spark.config().setTheme(Theme.DARK);
		 
		 report=new ExtentReports();
		 report.attachReporter(spark);
		 report.setSystemInfo("OS","Windows 11");
		 report.setSystemInfo("Browser", "Edge");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		
		report.flush();
		Reporter.log("Report backup",true);
	}

	@Override
	public void onTestStart(ITestResult result) {

		 test = report.createTest(result.getMethod().getMethodName());
		 test.log(Status.INFO,"===="+result.getMethod().getMethodName()+"Execution STARTED====");


	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS,"====="+result.getMethod().getMethodName()+"SUCCESS=====");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		Date d=new Date();
		String newDate = d.toString().replace(" ","_").replace(":","_");		
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src,testName+newDate);

		test.log(Status.FAIL,"====="+testName+" FAILURE=====");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP,"====="+result.getMethod().getMethodName()+" SKIPPED=====");

	}

	
	
	
	
	
}

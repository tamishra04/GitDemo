package tamishrasenapati.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import tamishrasenapati.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);//will assign a unique thread id.
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test passed.");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		//screenshot and attach it
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath);
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

	
	

}

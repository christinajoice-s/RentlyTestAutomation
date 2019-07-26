package browserFactory;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReportClass {
	ExtentReports extent;
	static ExtentReports report;
	static ExtentTest test;
	static ExtentTest logger;
	FileReader reader;
	static Properties p;

	public ExtendReportClass() {
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void startReport(){
		
		
		 ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/automation.html");
		 reporter.loadXMLConfig(new File("/home/rently/gitFramework/RentlyTestAutomation/extent-config.xml"));
		 
		 report = new ExtentReports();
		 report.attachReporter(reporter);
		 if(p.getProperty("BROWSER").equalsIgnoreCase("chrome"))
		 {	 
		report.setSystemInfo("Browser", p.getProperty("chromeversion"));
		 }
		 else if(p.getProperty("BROWSER").equalsIgnoreCase("firefox"))
		 {	 
		report.setSystemInfo("Browser", p.getProperty("firefoxversion"));
		 }
		 report.setSystemInfo("URL", p.getProperty("URL"));
		 report.setSystemInfo("Version",p.getProperty("version"));
		
		
		
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		//extent = new ExtentReports ();
		//extent.addSystemInfo("Environment","Environment Name")
		//extent
//                .addSystemInfo("Host Name", "SoftwareTestingMaterial")
//                .addSystemInfo("Environment", "Automation Testing")
//                .addSystemInfo("User Name", "Rajkumar SM");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
             //   report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
		
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 File finalDestination = new File(destination);
	//	 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }
	@Test
	public void passTest(String TestCaseName,String Description){
		report.createTest(TestCaseName, Description);
		//TestCaseName – Name of the test
		//Description – Description of the test
		//Starting test
		logger = report.createTest("passTest");
		Assert.assertTrue(true);
		//To generate the log when the test case is passed
		logger.log(Status.PASS, "Test Case Passed is passTest");
	}
	
	@Test
	public void failTest(String TestCaseName,String Description){
		logger = extent.createTest(TestCaseName);
		Assert.assertTrue(false);
		logger.log(Status.FAIL, "Test Case Failed");
	}
	
	@Test
	public void skipTest(){
		logger = extent.createTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}
	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			logger.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		report.flush();
	}
	@AfterTest
	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
                report.flush();
                //Call close() at the very end of your session to clear all resources. 
                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
                //Once this method is called, calling any Extent method will throw an error.
                //close() - To close all the operation
              //  report.close();
    }
}

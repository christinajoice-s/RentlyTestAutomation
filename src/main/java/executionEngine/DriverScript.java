package executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import browserFactory.Mail;
import browserFactory.ReusableLibrary;



//import org.apache.log4j.xml.DOMConfigurator;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;


public class DriverScript {

	public static Properties OR;
	public static  ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];

	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static String browser;
	public static String condition;
	public static String precondition;
	public static String postcondition;
	public static String status;
	public static String existingResult;
	public static boolean bResult;
	public static String TestDescription;
	public static String TestStepDescription;
	static ExtentReports report;

	static ExtentTest test;
	static ExtentTest logger;
	FileReader reader;
	static Properties p;

	@BeforeTest
	public void DriverScriptS() throws NoSuchMethodException, SecurityException, IOException{
	

		 reader=new FileReader(System.getProperty("user.dir")+"/src/main/resources/framework.properties");  
	     p=new Properties();  
	     p.load(reader);
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
	

	}
	
@Test

    public static void Rently() throws Exception {
		
    	ExcelUtils.setExcelFile(p.getProperty("TestData"));
      	DriverScript startEngine = new DriverScript();
		startEngine.execute_TestCase();

    }

    //@SuppressWarnings("rawtypes")
	private void execute_TestCase() throws Exception {
    	 
            condition = p.getProperty("Precondition");
            int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        
            
            for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
				bResult = true;
				sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
				sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
				precondition = ExcelUtils.getCellData(iTestcase, Constants.Col_Precondition, Constants.Sheet_TestCases);
				postcondition = ExcelUtils.getCellData(iTestcase, Constants.col_Postcondition, Constants.Sheet_TestCases);
				status = ExcelUtils.getCellData(ExcelUtils.getRowContains(precondition, Constants.Col_TestCaseID, Constants.Sheet_TestCases), Constants.Col_Result,  Constants.Sheet_TestCases);
				browser = p.getProperty("BROWSER");
				existingResult = ExcelUtils.getCellData(iTestcase, Constants.Col_Result, Constants.Sheet_TestCases); 
			
				if (sRunMode.equals("Yes")){
					 test = report.createTest(sTestCaseID);
					
			if(iTestcase>1)
			{
				String result = ExcelUtils.getCellData(iTestcase-1, Constants.Col_Result, Constants.Sheet_TestCases);
				if(result.equalsIgnoreCase("Fail")&&condition.equalsIgnoreCase("StopOnFail"))
				{
				
					break;
				}
				else
				{
					
					if(precondition!=null&&precondition!="")
					{
						actionKeywords = new ActionKeywords();
						method = actionKeywords.getClass().getMethods(); 
						iTestStep = ExcelUtils.getRowContains(precondition, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
						iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, precondition, iTestStep);
						bResult=true;
						System.out.println(bResult);
						for (;iTestStep<iTestLastStep;iTestStep++){
				    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
				    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
				    	
				    		execute_Actions();
							if(bResult==false){
							
								ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
							
								break;
								}						
							}
						if(bResult==true){
							
						ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
						
							}
					}
					actionKeywords = new ActionKeywords();
					method = actionKeywords.getClass().getMethods(); 
					iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
					iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
					bResult=true;
					
					for (;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
			    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
			    		execute_Actions();
						if(bResult==false){
							
							ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
							break;
							}						
						}
					if(bResult==true){
						
					ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
						}
					 if(postcondition!=null&&postcondition!="")
						{
							actionKeywords = new ActionKeywords();
							method = actionKeywords.getClass().getMethods(); 
							iTestStep = ExcelUtils.getRowContains(postcondition, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
							iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, postcondition, iTestStep);
							bResult=true;
							System.out.println(bResult);
							for (;iTestStep<iTestLastStep;iTestStep++){
					    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
					    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
					    	
					    		execute_Actions();
								if(bResult==false){
								
									ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
							
									break;
									}						
								}
							if(bResult==true){
								
							ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
							
								}
						}
				}
				}
			else
			{
			
			 if(precondition!=null&&precondition!="")
				{
				 	actionKeywords = new ActionKeywords();
					method = actionKeywords.getClass().getMethods();
				 	iTestStep = ExcelUtils.getRowContains(precondition, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
					iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, precondition, iTestStep);
					bResult=true;
					System.out.println(bResult);
					for (;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
			    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
			    	
			    		execute_Actions();
						if(bResult==false){
						
							ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
						
							break;
							}						
						}
					if(bResult==true){
						
					ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
						}
				}
			actionKeywords = new ActionKeywords();
			method = actionKeywords.getClass().getMethods();
			iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
			iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
			bResult=true;
		
			for (;iTestStep<iTestLastStep;iTestStep++){
	    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
	    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
	    	
	    		execute_Actions();
				if(bResult==false){
				
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
					break;
					}						
				}
			if(bResult==true){
				
			ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
			
				}
			
			 if(postcondition!=null&&postcondition!="")
				{
				 	actionKeywords = new ActionKeywords();
					method = actionKeywords.getClass().getMethods();
					iTestStep = ExcelUtils.getRowContains(postcondition, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
					iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, postcondition, iTestStep);
					bResult=true;
					System.out.println(bResult);
					for (;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
			    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
			    	
			    		execute_Actions();
						if(bResult==false){
						
							ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
							break;
							}						
						}
					if(bResult==true){
						
					ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
						}
				}
			
			
			}
			}

				}
    		}	

     private static void execute_Actions() throws Exception {

		for(int i=0;i<method.length;i++){

			if(method[i].getName().equals(sActionKeyword)){
		
				method[i].invoke(actionKeywords,browser);
				if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					test.pass(TestStepDescription);
					
					break;
				}else{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					test.fail(TestStepDescription);
					
					break;
					}
				}
			}

		
     }
     
     private static void execute(String testcasename) throws Exception {

    		iTestStep = ExcelUtils.getRowContains(testcasename, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
			iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
			bResult=true;
			
			for (;iTestStep<iTestLastStep;iTestStep++){
	    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
	    		TestStepDescription = ExcelUtils.getCellData(iTestStep, Constants.Col_TeststepDescription,Constants.Sheet_TestSteps);
	    
	    		for(int i=0;i<method.length;i++){

	    			if(method[i].getName().equals(sActionKeyword)){
	    				System.out.println(bResult);
	    				method[i].invoke(actionKeywords,browser);
	    				if(bResult==true){
	    					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
	    					test.pass(TestStepDescription);
	    					break;
	    				}else{
	    					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
	    					test.fail(TestStepDescription);
	    					
	    					break;
	    					}
	    				}
	    			}
	    		
			}
	}

	@AfterMethod
 	public void tearDown(ITestResult result) throws IOException
 	{		
 		report.flush();
 //		Mail.newgeneratemail();
 		
   	}

}
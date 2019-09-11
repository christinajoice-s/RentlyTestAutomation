package config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import executionEngine.DriverScript;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import browserFactory.DriverManager;
import browserFactory.DriverManagerFactory;
import browserFactory.DriverType;
import browserFactory.ReusableLibrary;
import pages.Devices;
import pages.Login;
import pages.Reservation;
import pages.SendInvite;


public class ActionKeywords extends ReusableLibrary{
	DriverManager driverManager;
    protected WebDriver driver;
    static ExtentReports report;
    static ExtentTest test;
    static ExtentTest logger;
	public ActionKeywords() throws IOException {
		String data = "value";
		beforeTest(data);
		beforeMethod(data) ;
	}
	
    public void beforeTest(String Data) throws IOException {
    	String BrowserName =readPropertyFile("BROWSER");
    	if(BrowserName.equalsIgnoreCase("Chrome"))
    	{
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    	}
    	else if(BrowserName.equalsIgnoreCase("Firefox"))
    	{
        driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
    	}
    }

   
    public void beforeMethod(String Data) {
        driver = driverManager.getDriver();
        
    }
    
    public void afterMethod(String Data) {
        driverManager.quitDriver();
    }
   
	 public void managerLogin(String Data) throws IOException, ParseException {
		   Login page = new Login(driver);
	       page.LoginAuthentication();
	                
	    }
	 public void loginNegativeTestcase(String Data) throws FileNotFoundException, IOException, ParseException
	 {
		  Login page = new Login(driver);
	       page.loginNegative();
	       driver.quit();
	 }
	 public void invite(String data) throws Exception {
		 SendInvite s1 = new SendInvite(driver);
	     s1.residentClick();
	 }
	 public void reserve(String data)throws Exception{
		 Reservation r1=new Reservation(driver);
		 r1.reservations();
	 }
    
    public void addDevicetoProperty(String data) throws Exception
    {
    	Devices page = new Devices(driver);
    	page.addDevice();
    }
    public void quitedriver(String data)
    {
    	driver.quit();
  
    }
   public static   void closeBrowser(String result) {
//		ReusableLibrary page = new ReusableLibrary();
		
	}

   
}

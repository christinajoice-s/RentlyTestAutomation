import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import browserFactory.*;
import pageFragments.Login;

public class FactoryPatternTest {

    DriverManager driverManager;
    protected WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }

    @Test
    public void launchRently() throws IOException {
      //  driver.get("http://rently.rentlypurple.com/");
      
       Login page = new Login(driver);
       page.LoginAuthentication();
     //  Runtime rt = Runtime.getRuntime();
       //Process pr = rt.exec("allure serve /home/rently/eclipse-workspace/testing/allure-results");
          afterMethod();            
    }

   

}

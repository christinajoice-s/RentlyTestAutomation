/**
 * 
 */
package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import browserFactory.*;
import browserFactory.ReusableLibrary;
//import browserFactory.ScriptHelper;
import executionEngine.DriverScript;


/**
 * @author rently
 *
 */

public class Login extends ReusableLibrary {
	
	    protected WebDriver driver;
	    protected String firstName,password,signin,image,name,passwords,popup,termsandConditions;
	    String jsonPath,jsonData;
	    public Login(WebDriver driver)
	    {
	    	super(driver);
	    	this.driver = driver;

	    	jsonPath = getObjectfile(this.getClass().getSimpleName());
	    	jsonData = getDatatfile(this.getClass().getSimpleName());
	    }
	    
	public void LoginAuthentication() throws IOException, ParseException {
		
		JSONParser jsonParserObject = new JSONParser();
		JSONParser jsonParserData = new JSONParser();
	    try (FileReader reader = new FileReader(jsonPath))
        {
            Object obj = jsonParserObject.parse(reader);
            JSONArray userlist = (JSONArray) obj;
            userlist.forEach( emp -> {
				
					try {
						parselogin( (JSONObject) emp );
					} catch (IOException e) {
						e.printStackTrace();
					}
				
			} );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (FileReader data = new FileReader(jsonData))
        {
            Object obj = jsonParserData.parse(data);
            JSONArray datalist = (JSONArray) obj;
        
            datalist.forEach( emp -> parselogins( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
       
	}
	 
	
	private void parselogin(JSONObject employee) throws IOException
    {
		 System.out.println("");
		 getApplication(readPropertyFile("URL"));
		 JSONObject employeeObject = (JSONObject) employee.get("userObject");
         firstName = (String)employeeObject.get("name_textbox")				;
         password = (String) employeeObject.get("password_textbox"); 
         signin = (String) employeeObject.get("signin_button");
         image = (String) employeeObject.get("propertypage_header");
         popup = (String) employeeObject.get("confirmPopup_button");
         termsandConditions = (String) employeeObject.get("termsAndCondition_button");
    }
	
	private void parselogins(JSONObject employee)
    {
        JSONObject employeeData= (JSONObject) employee.get("user");
        String name = (String) employeeData.get("name");   
        String passwords = (String) employeeData.get("password");
        
        EnterTextWithJS(locatorParser(firstName),name);
        EnterTextWithJS(locatorParser(password),passwords);
        ClickJSElement(locatorParser(signin),"SignIn");
        ImplicitWaitSwitch(100);
        //isDisplayed(locatorParser(image),"Manager Portal");
                 
    }
	public void loginNegative() throws FileNotFoundException, IOException, ParseException {
		getApplication(readPropertyFile("URL"));
		ImplicitWaitSwitch(40);
		EnterTextWithJS(locatorParser(jsonParser(jsonPath,"userObject","name_textbox")),jsonParser(jsonData,"user","name_prod"));
        EnterTextWithJS(locatorParser(jsonParser(jsonPath,"userObject","password_textbox")),jsonParser(jsonData,"user","password_prod"));
        ClickJSElement(locatorParser(jsonParser(jsonPath,"userObject","signin_button")),"SignIn");
        ImplicitWaitSwitch(100);
        if( isDisplayed(locatorParser(jsonParser(jsonPath,"userObject","propertypage_header")),"Manager Portal"))
        {
    		DriverScript.bResult = true;
        }
       else
       {
    	   DriverScript.bResult = false;
    	  
       }
		
	}
}

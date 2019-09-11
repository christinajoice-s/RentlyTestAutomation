package browserFactory;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
//import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;



import browserFactory.*;
import executionEngine.DriverScript;



public  class ReusableLibrary extends DriverManager{

	    protected WebDriver driver;
	    String value;
	    public ReusableLibrary(WebDriver driver)
	    {
	    	this.driver = driver;
    	
	    }
	
	public ReusableLibrary() {

		}

	public void getApplication(String appUrl) {
				
		try {
			driver.get(appUrl);
			driver.manage().window().maximize();
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	
     }
	
	public void EnterText(By strobj, String text)
	{
		System.out.println();
		try {
			WebElement element = driver.findElement(strobj);
			element.sendKeys(text);
			System.out.printf("EnterText", text + " is entered ");
		} catch (Exception e) {
			System.out.printf("EnterText", text + " is not entered");
		
		}
		
	}
	public void ClickElement(By strobj, String strButtonName) {

		try {
			
			WebElement element = driver.findElement(strobj);
			element.click();
			
			System.out.printf("Click Button", strButtonName + " clicked");

		} catch (Exception e) {
			System.out.printf("Click Button", strButtonName + " not clicked");
			
		}
	}
	public void ClickJSElement(By strobj, String strButtonName) {
		
		try {
			WebElement element = driver.findElement(strobj);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			System.out.printf("Click Button", strButtonName + " clicked");
			} catch (Exception e) {
			DriverScript.bResult = false;
			System.out.printf("Click Button", strButtonName + " not clicked. </ br> " + e);
		}
		
	}
	
	public void EnterTextWithJS(By strobj, String text) {
		try {
		System.out.println(strobj);
			WebElement element = driver.findElement(strobj);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='" + text + "'", element);
			System.out.printf("EnterText", text + " is entered in " );

		} catch (Exception e) {
			System.out.println(e);
			System.out.printf("EnterText", text + " is not entered in " );
			
		}
	}
	public boolean isDisplayed(By ObjId, String objdesc) {
		Boolean displayed = false;
		try {
			ImplicitWaitSwitch(30);
			displayed = driver.findElement(ObjId).isDisplayed();
			if (displayed) {
				System.out.println("Element Verification"+ objdesc + " is Displayed");
			
				ImplicitWaitSwitch(30);
			} else {
				System.out.println("Element Verification"+ objdesc + " is not Displayed");
			//	DriverScript.bResult = false;
				ImplicitWaitSwitch(30);
			}
			return displayed;
		} catch (Exception e) {
			System.out.printf("Element Verification"+ objdesc + " is not Displayed");
			ImplicitWaitSwitch(30);
		///	DriverScript.bResult = false;
			return displayed;
		}
	}
	public void SwitchtoFrame(String frameId)
	{
		driver.switchTo().frame(frameId);
	}
	public void SwitchtoActive()
	{
		driver.switchTo().activeElement();
	}
	List<WebElement> allOptions;
	public List<WebElement> returnWebElements(By ObjName)
	{
		allOptions = driver.findElements(ObjName);
		return allOptions;
	}
	public String getText(By Objname)
	{
		return driver.findElement(Objname).getText();
	}
	public void ImplicitWaitSwitch(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			
			
		}
	}
	public void SelectText(By objName, String strValue) {
		
		try {
			
			driver.findElement(objName).click();
			Select select = new Select(driver.findElement(objName));
			if (strValue != null && strValue != "") {
				ImplicitWaitSwitch(30);
				select.selectByVisibleText(strValue);
				System.out.printf("Select Value", strValue + " is selected ");
				ImplicitWaitSwitch(30);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			//DriverScript.bResult = false;
		}
	}
	public void SelectAction(By objName) throws InterruptedException
	{
		WebElement select = driver.findElement(objName);
		Actions builders = new Actions(driver);
		Action seriesOfAction = builders
			.moveToElement(select)
			.click()
			.sendKeys(Keys.DOWN)
			.click()
			.build();
		
		seriesOfAction.perform() ;
		Thread.sleep(1000);
	}
	public void selectOption(String option) {
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
		String script =
	            "function selectOption(s) {\r\n" +
	                    "   var sel = document.querySelector('.custom-select-options');\r\n" +
	                    "   for (var i = 0; i < sel.options.length; i++)\r\n" +
	                    "   {\r\n" +
	                    "       if (sel.options[i].text.indexOf(s) > -1)\r\n" +
	                    "       {\r\n" +
	                    "           sel.options[i].selected = true;\r\n" +
	                    "           break;\r\n" +
	                    "       }\r\n" +
	                    "   }\r\n" +
	                    "}\r\n" +
	                    "return selectOption('" + option + "');";

	 
		executor.executeScript(script);
	}
	public void getJSDropdown(String dropDown, String elementID)throws Exception{

		 JavascriptExecutor executor = (JavascriptExecutor)driver;
	     String dropdownScript = "var select = document.getElementByName('" + 
	    		 dropDown +
	             "'); for(var i = 0; i < select.options.length; i++){if(select.options[i].text == '" +
	             elementID +
	             "'){ select.options[i].selected = true; } }";

	     Thread.sleep(2000);
	     executor.executeScript(dropdownScript);
	 }
	public String readPropertyFile(String name) throws IOException
	{
		FileReader reader=new FileReader(System.getProperty("user.dir")+"/src/main/resources/framework.properties");  
        Properties p=new Properties();  
        p.load(reader);
        return p.getProperty(name);
        
	}
	public String jsonParser(String filename, String Object, String variable) throws FileNotFoundException, IOException, ParseException 
	{
		
		JSONParser jsonParserObject = new JSONParser();
		try (FileReader reader = new FileReader(filename))
        {
            Object obj = jsonParserObject.parse(reader);
            JSONArray userlist = (JSONArray) obj;
          userlist.forEach( emp -> {
        	  try {
					parselogin( (JSONObject) emp ,Object,variable);} 
				catch (IOException e) {
					e.printStackTrace();
				}
			} );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return value; 
		
	}
	
	
	
	private String parselogin(JSONObject employee, String Object,String variable) throws IOException
    {
		 
		JSONObject employeeObject = (JSONObject) employee.get(Object);
        value= (String)employeeObject.get(variable)	;
        return value;
        
    }
	
	
	public static By locatorParser(String locator) {
		
		By loc = By.id(locator.replaceAll("'","").trim());
		
		 String[] arrSplit = locator.split("By.");
		    for (int i=0; i < arrSplit.length; i++)
		    {
		    //  System.out.println(arrSplit[i]);
		    }
		    char identifier = arrSplit[1].charAt(0);
		   String message = arrSplit[1].substring(0, 1);
	
		if (message.contains("i"))
			{
				String  loc1 = locator.substring(locator.indexOf("(") + 1,
		        locator.length() -1);
				loc=By.id(loc1.replaceAll("'","").trim());
			}
				
		else if (message.contains("n"))
			{
				String  loc2  = locator.substring(locator.indexOf("(") + 1,
		        locator.length() - 1);
				loc=By.name(loc2.replaceAll("'","").trim());
			}
		
			
		else if (message.contains("x"))
		{
		  String loc3 = locator.substring(locator.indexOf("(") + 1,
		        locator.length() - 1);
		String result=  loc3.substring(1, loc3.length()-1);
		loc=By.xpath(result);
		}
		return loc;
		
	}
	public String getObjectfile(String classname)
	{
		String path;
		path = System.getProperty("user.dir")+"/src/main/java/objects/"+classname+".json";
		return path;
	}
	public String getDatatfile(String classname)
	{
		String path;
		path = System.getProperty("user.dir")+"//src/main/resources/testData/"+classname+"Data"+".json";
		return path;
	}
	
	public void closedriver(String data)
	{
		driver.close();
	}
	
	public void sendKeysByAction(By obj,String key)
	{
		WebElement element = driver.findElement(obj);
		Actions builder = new Actions(driver);
		Action seriesOfActions = builder
			.moveToElement(element)
			.click()
			.sendKeys(element,key)
			.build();
			
		seriesOfActions.perform() ;
	}
	public void sendNumberByAction(By obj,String key)
	{
		WebElement zipcode = driver.findElement(obj);
		
		Actions builders4 = new Actions(driver);
		Action seriesOfAction4 = builders4
			.moveToElement(zipcode)
			.click()
			.sendKeys(zipcode,key)
			.build();
		
		seriesOfAction4.perform() ;
	}
	public void clickByAction(By obj)
	{
		WebElement next = driver.findElement(obj);
		Actions builder = new Actions(driver);
		Action seriesOfActions = builder
			.moveToElement(next)
			.click()
			.build();
		seriesOfActions.perform() ;
	}
	public void switchToAlert() {
		
		   try{
			    Alert alert = driver.switchTo().alert();
			    System.out.println(alert.getText()+" Alert is Displayed"); 
			    }
			    catch(NoAlertPresentException ex){
			    System.out.println("Alert is NOT Displayed");
			    DriverScript.bResult = false;
			    }
			    
	}

	@Override
	protected void startService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stopService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		
	}
	public int jsonParserArray(String filename) throws FileNotFoundException, IOException, ParseException 
	{
		int len=0;
		JSONParser jsonParserObject = new JSONParser();
		try (FileReader reader = new FileReader(filename))
        {
            Object obj = jsonParserObject.parse(reader);
            JSONArray userlist = (JSONArray) obj;
            JSONArray userlist1= (JSONArray) userlist;
            len=userlist1.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return len; 
		
	}
	public int jsonParserArray1(String filename,JSONArray arr) throws FileNotFoundException, IOException, ParseException 
	{
		int len=0;
		try (FileReader reader = new FileReader(filename))
        {
          len=arr.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return len; 
		
	}
	public int jsonArrayLength(String filename, String Object) throws FileNotFoundException, IOException, ParseException 
	{


	JSONParser jsonParserObject = new JSONParser();
	int len = 0;


	       try (FileReader reader = new FileReader(filename))
	       {
	           Object obj = jsonParserObject.parse(reader);
	           JSONObject jo = (JSONObject) obj;
	           JSONArray ja = (JSONArray) jo.get(Object); 
	          len=ja.size();
	           System.out.println(len);
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       }
	return len; 
	}
	

	
}
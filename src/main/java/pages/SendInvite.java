package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import browserFactory.ReusableLibrary;

public class SendInvite extends ReusableLibrary 
{	

	protected WebDriver driver;
	String jsonPath,jsonData;
	public SendInvite(WebDriver driver) throws IOException, ParseException 
	{
		
		super(driver);
    	this.driver = driver;
    	jsonPath = getObjectfile(this.getClass().getSimpleName());
    	jsonData = getDatatfile(this.getClass().getSimpleName());
    	
	}

	public void residentClick() throws Exception
	{	
		int len=jsonParserArray(jsonPath);
		System.out.println("length of array"+len);
		for(int i=1;i<=2;i++)
		{   
			
			WebDriverWait wait = new WebDriverWait(driver, 20);	
			WebElement element1=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","send_invite"))));//ImplicitWaitSwitch(30);
			if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","send_invite")),"send_invite"))
	        {
				ClickElement(locatorParser(jsonParser(jsonPath,"Invite","send_invite")),"send_invite");
	        }
			Thread.sleep(1000);
			WebElement element2=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","choose_btn"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","choose_btn")),"choose_btn"))
	        {
	        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","choose_btn")),"choose_btn");
	        }
	        Thread.sleep(1000);
	        WebElement element3=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","choose_text"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","choose_text")),"choose_text"))
	        {	
	        	WebElement choosetxt = driver.findElement(locatorParser(jsonParser(jsonPath,"Invite","choose_text")));
	        	choosetxt.sendKeys("Test (32095)");
	        	choosetxt.sendKeys(Keys.ENTER);
	        }
	        WebElement element4=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","first_name"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","first_name")),"first_name"))
	        {
	        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","first_name")),jsonParser(jsonData,"InviteData"+i,"fname"));
	   	    }
	        Thread.sleep(1000);
	        WebElement element5=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","last_name"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","last_name")),"last_name"))
	        {
	        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","last_name")),jsonParser(jsonData,"InviteData"+i,"lname"));
		    }
	        Thread.sleep(1000);
	        WebElement element6=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","from_date"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","from_date")),"from_date"))
	        {
	        	WebElement dateBox = driver.findElement(locatorParser(jsonParser(jsonPath,"Invite","from_date")));
	        	dateBox.sendKeys("09252013");
	        	//sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","from_date")),jsonParser(jsonData,"InviteData"+i,"fdate"));
		    }
	        Thread.sleep(1000);
	        WebElement element7=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","mail"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","mail")),"mail"))
	        {
	        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","mail")),jsonParser(jsonData,"InviteData"+i,"email"));
		    }
	        Thread.sleep(1000);
	        WebElement element8=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","phno"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","phno")),"phno"))
	        {
	        	sendNumberByAction(locatorParser(jsonParser(jsonPath,"Invite","phno")),jsonParser(jsonData,"InviteData"+i,"phno"));
	        	
		    }
	        Thread.sleep(1000);
	        WebElement element9=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","doorcode"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","doorcode")),"code"))
	        {
	        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","doorcode")),"code");
		    }
	        Thread.sleep(1000);
	        WebElement element10=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"Invite","invite_btn"))));
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","invite_btn")),"invite_btn"))
	        {
	        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","invite_btn")),"invite_btn");
		    }
	        Thread.sleep(4000);
		    /*
		    if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","moveout_btn")),"moveout_btn"))
	        {
				ClickElement(locatorParser(jsonParser(jsonPath,"Invite","moveout_btn")),"moveout_btn");
				Thread.sleep(3000);
				driver.switchTo().alert().accept();
		    }
		    */
	        
		}
		
	}
		
}

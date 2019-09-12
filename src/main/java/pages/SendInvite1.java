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

public class SendInvite1 extends ReusableLibrary 
{	

	protected WebDriver driver;
	String jsonPath,jsonData;
	public SendInvite1(WebDriver driver) throws IOException, ParseException 
	{
		
		super(driver);
    	this.driver = driver;
    	jsonPath = getObjectfile(this.getClass().getSimpleName());
    	jsonData = getDatatfile(this.getClass().getSimpleName());
    	
	}

	public void residentClick() throws Exception
	{	WebDriverWait wait=new WebDriverWait(driver,20);
		ImplicitWaitSwitch(30);
		Thread.sleep(5000);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														//ImplicitWaitSwitch(30);
		if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","send_invite")),"send_invite"))
        {
			ClickElement(locatorParser(jsonParser(jsonPath,"Invite","send_invite")),"send_invite");
        }
		ImplicitWaitSwitch(30);
        Thread.sleep(1000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","choose_btn")),"choose_btn"))
        {
        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","choose_btn")),"choose_btn");
        }
        Thread.sleep(2000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","choose_text")),"choose_text"))
        {	//sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","choose_text")),jsonParser(jsonData,"InviteData","choose_property"));
        	//EnterText(locatorParser(jsonParser(jsonPath,"Invite","choose_text")),jsonParser(jsonData,"InviteData","choose_property"));
        	WebElement choosetxt = driver.findElement(By.xpath("//*[@data-id='choose_hub']//following-sibling::div//div//input[@aria-label='Search']"));
        	choosetxt.sendKeys("Test (32095)");
        	choosetxt.sendKeys(Keys.ENTER);
        }
        
        
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invitation_first_name")));
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","first_name")),"first_name"))
        {
        	
        	EnterText(locatorParser(jsonParser(jsonPath,"Invite","first_name")),jsonParser(jsonData,"InviteData","fname"));
	    }
        Thread.sleep(2000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","last_name")),"last_name"))
        {
        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","last_name")),jsonParser(jsonData,"InviteData","lname"));
	    }
        Thread.sleep(2000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","from_date")),"from_date"))
        {
        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","from_date")),jsonParser(jsonData,"InviteData","fdate"));
	    }
        Thread.sleep(2000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","mail")),"mail"))
        {
        	sendKeysByAction(locatorParser(jsonParser(jsonPath,"Invite","mail")),jsonParser(jsonData,"InviteData","email"));
	    }
        Thread.sleep(1000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","phno")),"phno"))
        {
        	sendNumberByAction(locatorParser(jsonParser(jsonPath,"Invite","phno")),jsonParser(jsonData,"InviteData","phno"));
        	
	    }
        Thread.sleep(1000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","doorcode")),"code"))
        {
        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","doorcode")),"code");
	    }
        Thread.sleep(2000);
        if(isDisplayed(locatorParser(jsonParser(jsonPath,"Invite","invite_btn")),"invite_btn"))
        {
        	ClickElement(locatorParser(jsonParser(jsonPath,"Invite","invite_btn")),"invite_btn");
	    }
        Thread.sleep(8000);
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

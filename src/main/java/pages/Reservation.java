package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

import browserFactory.ReusableLibrary;

public class Reservation extends ReusableLibrary {

	protected WebDriver driver;
	String jsonPath,jsonData;
	public Reservation(WebDriver driver) {
		
		super(driver);
    	this.driver = driver;
    	jsonPath = getObjectfile(this.getClass().getSimpleName());
    	jsonData = getDatatfile(this.getClass().getSimpleName());
		
	}

	public void reservations() throws Exception
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//WebElement element1=wait.until(ExpectedConditions.visibilityOfElementLocated(locatorParser(jsonParser(jsonPath,"reserve","create_reserve"))));
		if(isDisplayed(locatorParser(jsonParser(jsonPath,"reserve","create_reserve")),"reservebtn"))
		{
			ClickElement(locatorParser(jsonParser(jsonPath,"reserve","create_reserve")),"reservebtn");
			
		}
	    else
		{
			driver.navigate().refresh();
		}
		
	}
	
	
	
}

package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import browserFactory.ReusableLibrary;

public class Devices extends ReusableLibrary{
	protected WebDriver driver;
	String jsonPath,jsonData;
	public Devices(WebDriver driver) {
		
		super(driver);
    	this.driver = driver;
    	jsonPath = getObjectfile(this.getClass().getSimpleName());
    	jsonData = getDatatfile(this.getClass().getSimpleName());
		
	}

	public void addDevice() throws Exception
	{
		Thread.sleep(5000);
		
		 if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"Properties"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"confirmPopup");
	        }
		 ImplicitWaitSwitch(30);
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition");
	        }
		SwitchtoFrame("proprepoIframe")	;
		
		ClickJSElement(locatorParser(jsonParser(jsonPath,"device","device_button")),"devices");
		Thread.sleep(5000);
	
		 if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"Properties"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"confirmPopup");
	        }
		 ImplicitWaitSwitch(30);
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition");
	        }
	        Thread.sleep(5000);
	    	
			 if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"Properties"))
		        {
		        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","confirmPopup_button")),"confirmPopup");
		        }
			 ImplicitWaitSwitch(30);
		        if(isDisplayed(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition"))
		        {
		        	ClickJSElement(locatorParser(jsonParser(jsonPath,"device","termsAndCondition_button")),"termsAndCondition");
		        }
		SelectAction(locatorParser(jsonParser(jsonPath,"device","deviceList_select")));
		List<WebElement> allOptions = returnWebElements(locatorParser(jsonParser(jsonPath,"device","deviceList_properties")));
		int size = allOptions.size();
		int i=1 ;
		Thread.sleep(2000);
		 for (WebElement element: allOptions) 
		 {
			element.click();
			ClickJSElement(By.xpath("//label[text()='Properties']/..//input"),"device");

			WebElement property = driver.findElement(By.xpath("//label[text()='Properties']/..//input"));
																																																																																																																																																																																																									
			Actions builders = new Actions(driver);
			Action seriesOfAction = builders
				.moveToElement(property)
				.pause(8000)
				.sendKeys(Keys.DOWN)
				.sendKeys(Keys.DOWN)
				.contextClick()
				.contextClick()
				.build();
			seriesOfAction.perform() ;
			Thread.sleep(5000);
			
			//switchToAlert();
			break;

		 }
		
	}

}

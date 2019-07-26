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

public class Properties extends ReusableLibrary {

	protected WebDriver driver;
	String jsonPath,jsonData;
	public Properties(WebDriver driver) {
		
		super(driver);
    	this.driver = driver;
    	jsonPath = getObjectfile(this.getClass().getSimpleName());
    	jsonData = getDatatfile(this.getClass().getSimpleName());
		
	}

	public void addProperty() throws Exception
	{
		ClickJSElement(locatorParser(jsonParser(jsonPath,"add","properties_tab")),"Properties");
		ImplicitWaitSwitch(30);
		//ImplicitWaitSwitch(30);
		 if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"Properties"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"confirmPopup");
	        }
		 ImplicitWaitSwitch(30);
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition");
	        }
		SwitchtoFrame("proprepoIframe")	;
		String parent = driver.getWindowHandle();
		if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","addProperty_button")),"Add Property"))
		{
			ImplicitWaitSwitch(30);
		ClickJSElement(locatorParser(jsonParser(jsonPath,"add","addProperty_button")),"Add Property");
		ImplicitWaitSwitch(30);
		ImplicitWaitSwitch(30);
		if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","manual_button")),"Manual"))
		{
		ClickJSElement(locatorParser(jsonParser(jsonPath,"add","manual_button")),"Manual");
		ImplicitWaitSwitch(30);
		}
		if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","onMarket_button")),"ON Market"))
		{
		ClickJSElement(locatorParser(jsonParser(jsonPath,"add","onMarket_button")),"ON Market");
		ImplicitWaitSwitch(30);
		}
		

		Thread.sleep(5000);

		Thread.sleep(5000);
		
		WebElement property_type = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[@name='basic_property_type']"));
		
		Actions builders = new Actions(driver);
		Action seriesOfAction = builders
			.moveToElement(property_type)
			.click()
			.sendKeys(Keys.DOWN)
			
			.build();
		
		seriesOfAction.perform() ;
		Thread.sleep(5000);
		
WebElement address = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[text()='Address*']/..//input"));
		
		Actions addBuild = new Actions(driver);
		Action seriesOfActionBuild = addBuild
			.moveToElement(address)
			.click()
			.sendKeys(address,"240 Market Street, Philadelphia, PA 19106")
			.sendKeys(Keys.DOWN)
			.click()
			.build();
		seriesOfActionBuild.perform() ;
		Thread.sleep(5000);
		

		Thread.sleep(5000);
		clickByAction(locatorParser(jsonParser(jsonPath,"add","basicNext_button")));
		Thread.sleep(5000);
		WebElement Element = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[@name='marketing_price']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
	
		 js.executeScript("arguments[0].scrollIntoView();", Element);
			Thread.sleep(5000);
			js.executeScript("window.scrollBy(0,1000)");
			
			Actions price = new Actions(driver);
			Action series = price
				.sendKeys(Keys.PAGE_UP)
				.moveToElement(Element)
				.click()
				.sendKeys(Keys.DOWN)
				.build();
			
			series.perform() ;
			Thread.sleep(5000);
			
		sendNumberByAction(locatorParser(jsonParser(jsonPath,"add","price_input")),jsonParser(jsonData,"add","price"));
		clickByAction(locatorParser(jsonParser(jsonPath,"add","sameAsPrice_Select")));
		sendKeysByAction(locatorParser(jsonParser(jsonPath,"add","sameAsPrice_Select")),jsonParser(jsonData,"add","price"));
		sendKeysByAction(locatorParser(jsonParser(jsonPath,"add","title_input")),jsonParser(jsonData,"add","title"));
		Thread.sleep(5000);
		sendKeysByAction(locatorParser(jsonParser(jsonPath,"add","bed_input")),jsonParser(jsonData,"add","bed"));
		sendKeysByAction(locatorParser(jsonParser(jsonPath,"add","bath_input")),jsonParser(jsonData,"add","bath"));
		sendKeysByAction(locatorParser(jsonParser(jsonPath,"add","sqft_input")),jsonParser(jsonData,"add","sqft"));
		Thread.sleep(5000);
		clickByAction(locatorParser(jsonParser(jsonPath,"add","PetsDog_checkbox")));
		clickByAction(locatorParser(jsonParser(jsonPath,"add","marketingNext_button")));
		Thread.sleep(5000);
		
		clickByAction(locatorParser(jsonParser(jsonPath,"add","mediaNext_checkbox")));
		Thread.sleep(5000);
		
		WebElement moveto = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//input[@value='vacant']"));
		
		Actions move = new Actions(driver);
		Action serie = move
			.sendKeys(Keys.PAGE_UP)
			.sendKeys(Keys.PAGE_UP)
			.moveToElement(moveto)
			.click()
			.build();
		
		serie.perform() ;
		Thread.sleep(5000);
		
		
		clickByAction(locatorParser(jsonParser(jsonPath,"add","vacant_checkbox")));
		Thread.sleep(5000);
		clickByAction(locatorParser(jsonParser(jsonPath,"add","autoShow_checkbox")));
		Thread.sleep(5000);
		clickByAction(locatorParser(jsonParser(jsonPath,"add","agentShow_checkbox")));
				
		
	WebElement showing = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[@name='scheduler_showing_id']"));
		
		Actions buil = new Actions(driver);
		Action Actions = buil
			.moveToElement(showing)
			.click()
			.sendKeys(Keys.DOWN)
			
			.build();
		
		Actions.perform() ;
		Thread.sleep(5000);
		
		
		
		
		
		WebElement mainLock = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[@class='lockbox-field']"));
		
		Actions build = new Actions(driver);
		Action Action = build
			.moveToElement(mainLock)
			.click()
			.sendKeys(Keys.DOWN)
			
			.build();
		
		Action.perform() ;
		Thread.sleep(5000);
		
		
		
		
	WebElement instruction = driver.findElement(By.xpath("//*[@class='add-property-modal modal-dialog']//*[@class='lockbox-field']"));
		
		Actions inst = new Actions(driver);
		Action Actioninst = inst
			.moveToElement(instruction)
			.click()
			.sendKeys(Keys.DOWN)
			.sendKeys(instruction,"lock device")
			.build();
		
		Actioninst.perform() ;
		Thread.sleep(5000);
		
		clickByAction(locatorParser(jsonParser(jsonPath,"add","done_button")));
		
		
		Thread.sleep(10000);	
		
		}
		ImplicitWaitSwitch(30);
   }
	public void selectProperty() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		ImplicitWaitSwitch(30);
		Thread.sleep(5000);
		 if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"Properties"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"confirmPopup");
	        }
		 ImplicitWaitSwitch(30);
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition");
	        }
		SwitchtoFrame("proprepoIframe")	;
		SwitchtoActive();
		List<WebElement> allOptions = returnWebElements(locatorParser(jsonParser(jsonPath,"add","propertyCard")));
		int size = allOptions.size();
		int i=1 ;
		 for (WebElement element: allOptions) 
		 {
			String PropertyName = driver.findElement(By.xpath("//div[@class='property_card']["+i+"]//div[@class='headline']")).getText(); 
			element.click();
			driver.findElement(By.xpath("//div[@class='property_card']["+i+"]")).click();
			String MarketStatusOriginal = driver.findElement(By.xpath("//div[@class='property_card']["+i+"]//div[@class='statuslist']/span")).getText(); 
			ImplicitWaitSwitch(30);
			Thread.sleep(3000);
			ClickJSElement(locatorParser(jsonParser(jsonPath,"add","marketStatus")),"marketStatus");
			ImplicitWaitSwitch(30);
			Thread.sleep(3000);
			String MarketStatusEditMode = driver.findElement(By.xpath("//label[text()='LISTING']/../..//select/option[1]")).getText();
			if(MarketStatusOriginal.equalsIgnoreCase(MarketStatusEditMode))
			{
				System.out.println("\nPASS------"+PropertyName+"..."+MarketStatusOriginal+"..."+MarketStatusEditMode);
				break;
			}
			else
			{
				System.out.println("FAIL-------"+PropertyName+"..."+MarketStatusOriginal+"..."+MarketStatusEditMode);
				break;
			}
				
		 //i++;
		 }
	}
	public void editView() throws InterruptedException, FileNotFoundException, IOException, ParseException
	{
		ImplicitWaitSwitch(30);
		Thread.sleep(3000);
		 if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"Properties"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","confirmPopup_button")),"confirmPopup");
	        }
		 ImplicitWaitSwitch(30);
	        if(isDisplayed(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition"))
	        {
	        	ClickJSElement(locatorParser(jsonParser(jsonPath,"add","termsAndCondition_button")),"termsAndCondition");
	        }
		SwitchtoFrame("proprepoIframe")	;
		SwitchtoActive();
		List<WebElement> allOptions = returnWebElements(locatorParser(jsonParser(jsonPath,"add","propertyCard")));
		int size = allOptions.size();
		int i=1 ;
		 for (WebElement element: allOptions) 
		 {
			String PropertyName = driver.findElement(By.xpath("//div[@class='property_card']["+0+"]//div[@class='headline']")).getText(); 
			element.click();
			driver.findElement(By.xpath("//div[@class='property_card']["+0+"]")).click();
			String MarketStatusOriginal = driver.findElement(By.xpath("//div[@class='property_card']["+0+"]//div[@class='statuslist']/span")).getText(); 
			ImplicitWaitSwitch(30);
			Thread.sleep(3000);
			ClickJSElement(locatorParser(jsonParser(jsonPath,"add","editView")),"EditView");
			
		 }
		
	}
	
	
	
}

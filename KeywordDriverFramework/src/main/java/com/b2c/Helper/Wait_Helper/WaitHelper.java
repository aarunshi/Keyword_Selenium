package com.b2c.Helper.Wait_Helper;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.b2c.Helper.Config_Reader_Helper.ObjectRepo;
import  com.b2c.Helper.DropDown_Helper.DropDownHelper;
import com.b2c.Helper.Logger_Helper.*;
///not complete
public class WaitHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class.getName());

	public WaitHelper(WebDriver driver)
	{
		
		this.driver = driver;
		log.debug("WaitHelper: "+ this.hashCode());//to debug if correct driver is being picked
	}
	
	public WebElement WaitForElement(WebDriver driver,long timeout,WebElement element)
	{//this is ok
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForElement(WebDriver driver,WebElement element,long time)
	{//this is ok
		WebDriverWait wait = new WebDriverWait(driver,time);
		 wait.until(ExpectedConditions.visibilityOf(element));
		// log.info("element found : "+ element.getText());
	}
	public WebElement waitForElementWithPolingInterval(WebDriver driver,long time,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void setImplicitWait( )
	{
		long time = ObjectRepo.reader.getImplicitWait();
		
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	
	}

}

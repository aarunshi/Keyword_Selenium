package com.b2c.Helper.JavaScript_Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.b2c.Helper.DropDown_Helper.DropDownHelper;
import com.b2c.Helper.Logger_Helper.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class.getName());
	
	public JavaScriptHelper(WebDriver driver)
	{
		
		this.driver = driver;
		log.debug("JavaScriptHelper: "+ this.hashCode());//to debug if correct driver is being picked
	}
	
	public Object executeScript(String script)
	{
		JavascriptExecutor exe =(JavascriptExecutor)driver;
		log.info("script exectuted : "+script);
		return exe.executeScript(script);
		
	}
	
	public Object executeScript(String script,Object ...args)
	{
		JavascriptExecutor exe =(JavascriptExecutor)driver;
		log.info("script exectuted : "+script);
		return exe.executeScript(script,args);
		
	}
	//scrolls to specific cordinates
	public void scrollToElement(WebElement element)
	{
		executeScript("window.scrollTo(arguments[0],arguments[1]",element.getLocation().x ,element.getLocation().y);
		log.info("Scroll to : " +element);
	}
	public void scrollToElementAndClick( WebElement element)
	{
		scrollToElement(element);
		element.click();
		log.info("Element clicked : "+ element);
	}
	//to get the element veiw 
	
	public void scrollToView(WebElement element)
	{
		executeScript("arguments[0].scrollIntoView();",element);
		log.info("Scroll to : " +element);
	}
	public void scrollToViewAndClick(WebElement element)
	{
		executeScript("arguments[0].scrollIntoView();",element);
		element.click();
		log.info("Scroll & click to : " +element);
	}
	
	public void scrollDownVertical()
	{
		executeScript("window.scrollTo(0,docuemnt.body.scrollheight");
		log.info("scroll up vertically");
		
	}
	public void scrollUpVertical()
	{
		executeScript("window.scrollTo(0,-docuemnt.body.scrollheight");
		log.info("Scroll down vertically");
	}
	public void scrollDownByPixel()
	{
		executeScript("window.scrollTo(0,-1500t");
		log.info("Scroll down vertically");
	}
	public void scrollUpByPixel()
	{
		executeScript("window.scrollTo(0,1500");
		log.info("scroll up vertically");	
	}
	
	public void zoomByPercentage()
	{
		executeScript("docuemnt.body.style.zoom='40%'");
		log.info("Zoom by 40%");	
	}
	public void zoomBy100Percentage()
	{
		executeScript("docuemnt.body.style.zoom='100%'");
		log.info("Zoom By 100%");	
	}
	
	public void radiobuttonClick(WebElement element)
	{
	    executeScript("arguments[0].click();", element);
		log.info("Radio button click to : " +element);
	}
	
}

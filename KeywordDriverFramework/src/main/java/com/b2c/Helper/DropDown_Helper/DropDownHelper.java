package com.b2c.Helper.DropDown_Helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.b2c.Helper.Logger_Helper.*;

public class DropDownHelper {
	
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class.getName());

	public DropDownHelper(WebDriver driver)
	{
		
		this.driver = driver;
		log.debug("DropDownHelper: "+ this.hashCode());//to debug if correct driver is being picked
	}
	public void selectUsingVisibleText(WebElement element,String visibleValue)
	{
		Select select = new Select(element);
		
		select.selectByVisibleText(visibleValue);
		log.info("Locator : "+element +" Value : "+visibleValue );
	}
	
	public String getSelectedValue(WebElement element)
	{
		Select select = new Select(element);
		String value = select.getFirstSelectedOption().getText();
		log.info("Locator : "+element+"Selected value : "+ value);
		return value;
		
	}
	
	public List<String> getAllDropDownValue(WebElement element)
	{
		Select select = new Select(element);
		List<String> stringList = new LinkedList<String>();
		List<WebElement> elementList = select.getOptions();
		for( WebElement Delement:elementList )
		{
			log.info(Delement.getText());
			stringList.add(Delement.getText());
		
		}
		return stringList;
	}

	
	public void selectUsingIndex(WebElement element,int index)
	{
		Select select = new Select(element);	
		select.selectByIndex(index);
		log.info("Locator : "+element+" value : "+ index);
	}
}

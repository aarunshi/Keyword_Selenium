package com.b2c.Helper.Assertions_Helper;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.b2c.Helper.DropDown_Helper.*;
import com.b2c.Helper.JavaScript_Helper.*;
import com.b2c.Helper.Logger_Helper.*;

public class VerificationHelper {
	
	
	private static Logger log = LoggerHelper.getLogger(VerificationHelper.class.getName());
	
	public static synchronized boolean verifyElementPresent(WebElement element)
	{
		
		
		boolean displayed =false;
		try
		{
			displayed = element.isDisplayed();
		}
		catch(Exception ex)
		{
			log.error("element not present: "+ ex);
		}
			return 	displayed;
		
	}
	
	public static synchronized boolean verifyElementNotPresent(WebElement element)
	{
		
		
		boolean displayed =false;
		try
		{
			 element.isDisplayed();
		}
		catch(Exception ex)
		{
			displayed =true;
			log.error("element not present: "+ ex);
		}
			return 	displayed;
		
	}
	
	public static synchronized boolean verifyRadiobuttonIsSeclcted(WebElement element)
	{
		
		boolean selected = false;
		
		try
		{
			 selected=element.isSelected();
			 
		}
		catch(Exception ex)
		{
			
			log.error("element not selected: "+ ex);
			
		}
			return 	selected;
		
	}
	
	
	public static synchronized boolean verifyTextEqual(WebElement element , String Expectedtext)
	{
		if (verifyElementPresent(element))
		{
			String actualValue = element.getText();
			if (actualValue.equals(Expectedtext))
			{
				return true;
			}
			else
				return false;
			}
		else
			return false;
	}
}

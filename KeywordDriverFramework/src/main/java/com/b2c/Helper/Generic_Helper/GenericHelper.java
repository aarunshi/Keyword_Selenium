package com.b2c.Helper.Generic_Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.b2c.Helper.Assertions_Helper.*;
import com.b2c.Helper.DropDown_Helper.*;
import com.b2c.Helper.Logger_Helper.*;

public class GenericHelper {
	private Logger log = LoggerHelper.getLogger(GenericHelper.class.getName());
	
	public String readValueFromElement(WebElement element)
	{//for element label text
		if (element==null)
		{
			log.info("Element is Null ");
			return null;
			
		}
		if(VerificationHelper.verifyElementPresent(element))
		{
			String text =element.getText();
			log.info("webelement textvalue is : "+ text);
			return text;
		}
		else
			return null;
	}
	
	public String readValueFromInput(WebElement element)
	{//for element value from value tag in html
		if (element==null)
		{
			log.info("Element is Null ");
			return null;
			
		}
		if(VerificationHelper.verifyElementPresent(element))
		{
			String text =element.getAttribute("value");
			log.info("webelement value is : "+ text);
			return text;
		}
		else
			return null;
		
	}
	
	
	

}

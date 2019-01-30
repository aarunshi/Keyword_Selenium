package com.b2c.Helper.Alerts_Helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.b2c.Helper.JavaScript_Helper.*;
import com.b2c.Helper.Logger_Helper.*;
//popup messages for info,confirmation or prompt
public class AlertHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class.getName());
	
	public AlertHelper(WebDriver driver)
	{
		
		this.driver = driver;
		log.debug("AlertHelper: "+ this.hashCode());//to debug if correct driver is being picked
	}

	public Alert getAlert()
	{
	
		return driver.switchTo().alert();
	}
	public void acceptAlert()
	{
		getAlert().accept();
	}
	public void dismissAlert()
	{
		getAlert().dismiss();
	}
	public String getAlertText()
	{
		String text =getAlert().getText();
		log.info(text);
		return text;
	}
	
	public Boolean isAlertPresent()
	{
		try
		{
			getAlert();
			log.info("AlertPresent");
			return true;
			
		}
		catch(NoAlertPresentException e)
		{
			log.info("No Alert Present");
			return false;
		}
	}
	public void acceptAlertIfPresent()
	{
		if(isAlertPresent())
		{
			acceptAlert();
			log.info("Alert accepted");
		}
		else
		{
			log.info("No alert");
		}
	}
	
	public void dismissAlertIfPresent()
	{
		

		if(isAlertPresent())
		{
			dismissAlert();
			log.info("Alert dismissed");
		}
		else
		{
			log.info("No alert");
		}
	}
	
	public void acceptPromt(String text)
	{

		if(isAlertPresent())
		{
			Alert alert = getAlert();
			alert.sendKeys(text);
			acceptAlert();
			log.info("prompt accepted: "+text);
		}
		else
		{
			log.info("No alert");
		}
		
	}
	
	
	}


package com.b2c.Helper.CustomerListerners_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.b2c.Helper.Utility_Helper.ResourceHelper;

public class ScreenshotCapture {
	WebDriver driver;
	public ScreenshotCapture(WebDriver driver)
	{
		this.driver =driver;
	}

	public String getScreenShot(String imageName) throws IOException
	{
		
		if (imageName.equals(""))
		{
			imageName ="Blank";
		}
		File image =((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
		String imageLocation =ResourceHelper.getResourcePath("/src/main/java/com/B2C/Framework/Screenshots/");
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		String actualImageName =imageLocation+imageName+"_"+formater.format(calender.getTime())+".png";
		File desFile = new File (actualImageName);
		FileHandler.copy(image, desFile);
		return actualImageName;
		
	}

}

package com.b2c.Browser;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.b2c.Helper.Utility_Helper.ResourceHelper;
//set the required browser with initial set up
public class IExplorerBrowser {
	
	public Capabilities getIExplorerCapabilities()
	{
		
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		cap.setJavascriptEnabled(true);
		
		//for zoom issue
		cap.setCapability("EnableNativeEvents", false);
		cap.setCapability("ignoreZoomSetting", true);
		//run below line after above line
		//WebDriver driver = new InternetExplorerDriver(caps);
		//\
	//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
		return cap;
	}

	public WebDriver getIExplorerDriver(Capabilities cap)

	{
		InternetExplorerDriverService service =new InternetExplorerDriverService.Builder().usingDriverExecutable(new File(ResourceHelper.getResourcePath("src/main/resources/Drivers/IEDriverServer.exe"))).usingAnyFreePort().build();
		InternetExplorerOptions options =new InternetExplorerOptions();
		options.merge(cap);
	
		System.setProperty("webdriver.IExplorer.driver",ResourceHelper.getResourcePath("Drivers/IEDriverServer.exe") );
		return new InternetExplorerDriver(service,options);
	}


}

package com.b2c.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.b2c.Helper.Assertions_Helper.VerificationHelper;
import com.b2c.Helper.Config_Reader_Helper.ObjectRepo;
import com.b2c.Helper.DropDown_Helper.DropDownHelper;
import com.b2c.Helper.Generic_Helper.GenericHelper;
import com.b2c.Helper.JavaScript_Helper.JavaScriptHelper;
import com.b2c.Helper.Logger_Helper.LoggerHelper;
import com.b2c.Helper.Wait_Helper.WaitHelper;
import com.b2c.Utils.Resources;
import com.b2c.uiActions.Dashboard;
import com.b2c.uiActions.Password;
import com.b2c.uiActions.RegistrationPage;

import com.b2c.uiActions.Verificationlinkcapture_ICICI;
import com.b2c.uiActions.vcfo;
import com.google.common.base.Function;

public class Keywords extends Resources {
	public static final Logger log = LoggerHelper.getLogger(Keywords.class.getName());
 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for browser navigation
  */
 public static String Navigate() {
 log.info("Navigate is called");
  driver.get(webElement);
  return "Pass";
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for selecting value from Radio buton
  */
 public static String selectRadioButton() {
  log.info("selectRadioButton");
  try {
   getWebElement(webElement).click();
  } catch (Throwable t) {
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for input value in text feild
  */

 public static String InputText() {
  log.info("InputText is called");
  try {
   getWebElement(webElement).sendKeys(TestData);
  
  } catch (Throwable t) {
  
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for clicking on a link
  */

 public static String ClickOnLink() {
  log.info("ClickOnLink is called");
  try {

   getWebElement(webElement).click();
  

  } catch (Throwable t) {
   t.printStackTrace();
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }
  /*
  * Author : Deepti Date Created : 25 Jan 2019 
  * Description : Function for file upload
  */
 public static String fileupload() throws FileNotFoundException {
	 StringSelection ss = new StringSelection(TestData);
	 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	 Robot robot;
	try {
		robot = new Robot();
	
	 robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000); 
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	 catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return "pass";
	
 }
 
 

 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for clear text
  */
 public static String ClearText() {
  log.info("Clear Text is called");
  try {
   getWebElement(webElement).clear();
  } catch (Throwable t) {
   t.printStackTrace();
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function verifying particular text in a webelement
  */

 public static String VerifyText() {
  log.info("VerifyText is called");
  try {

   String ActualText = getWebElement(webElement).getText();

   log.info("Actual Text is" + ActualText);
   if (!ActualText.equals(TestData)) {
    return "Failed - Actual text " + ActualText + " is not equal to to expected text " + TestData;
   }

  } catch (Throwable t) {
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }

 // public static String VerifyAppText() {
 // System.out.println("VerifyText is called");
 // try {
 // String ActualText= getWebElement(webElement).getText();
 // if(!ActualText.equals(AppText.getProperty(webElement))) {
 // return "Failed - Actual text "+ActualText+" is not equal to to expected text
 // "+AppText.getProperty(webElement);
 // }
 // }catch (Throwable t) {
 // return "Failed - Element not found "+webElement;
 // }
 // return "Pass";
 // }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function to find webelement
  */

 public static WebElement getLocator(String locator) throws Exception {
  String[] split = locator.split(":", 2);
  String locatorType = split[0];
  String locatorValue = split[1];
  log.info("in locator"+locator);
  if (locatorType.toLowerCase().equals("id"))
   return driver.findElement(By.id(locatorValue));
  else if (locatorType.toLowerCase().equals("name"))
   return driver.findElement(By.name(locatorValue));
  else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
   return driver.findElement(By.className(locatorValue));
  else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
   return driver.findElement(By.className(locatorValue));
  else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
   return driver.findElement(By.linkText(locatorValue));
  else if (locatorType.toLowerCase().equals("partiallinktext"))
   return driver.findElement(By.partialLinkText(locatorValue));
  else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
   return driver.findElement(By.cssSelector(locatorValue));
  else if (locatorType.toLowerCase().equals("xpath"))
	     return driver.findElement(By.xpath(locatorValue));
  else if (locatorType.equals("Dxpath")){
	   log.info("dloc value is" + locatorValue);
	   locatorValue = locatorValue.replace("+Data+", TestData);
	   log.info("dloc value is" + locatorValue);
	   return driver.findElement(By.xpath(locatorValue));
	  } else if (locatorType.equals("Dxpatharr")) {
	      log.info("dloc value is" + locatorValue);
	      locatorValue = locatorValue.replace("+Data+", arrTestData.get(1));
	      log.info("dloc value is" + locatorValue);
	      return driver.findElement(By.xpath(locatorValue));
	}  
	  else

   throw new Exception("Unknown locator type '" + locatorType + "'");
 }
 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function to find list of webelement
  */

 public static List < WebElement > getLocators(String locator) throws Exception {
  String[] split = locator.split(":", 2);
  String locatorType = split[0];
  String locatorValue = split[1];

  if (locatorType.toLowerCase().equals("id"))
   return driver.findElements(By.id(locatorValue));
  else if (locatorType.toLowerCase().equals("name"))
   return driver.findElements(By.name(locatorValue));
  else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
   return driver.findElements(By.className(locatorValue));
  else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
   return driver.findElements(By.className(locatorValue));
  else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
   return driver.findElements(By.linkText(locatorValue));
  else if (locatorType.toLowerCase().equals("partiallinktext"))
   return driver.findElements(By.partialLinkText(locatorValue));
  else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
   return driver.findElements(By.cssSelector(locatorValue));
  else if (locatorType.toLowerCase().equals("xpath"))
   return driver.findElements(By.xpath(locatorValue));
  else
   throw new Exception("Unknown locator type '" + locatorType + "'");
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function to get a webelement
  */

 public static WebElement getWebElement(String locator) throws Exception {
  log.info("locator data is :-" + locator + "is---" + Repository.getProperty(locator));

  return getLocator(Repository.getProperty(locator));
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function to get a list of webelement
  */
 public static List < WebElement > getWebElements(String locator) throws Exception {
  return getLocators(Repository.getProperty(locator));
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for explicit wait , wait time to be set in config
  */
 public static String expliciteWait() throws Exception {
  long time = ObjectRepo.reader.getExplicitWait();
  WebDriverWait wait = new WebDriverWait(driver, time);
  try {
   wait.until(ExpectedConditions.visibilityOf(getWebElement(webElement)));
   return "Pass";
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return "Failed - Element not found " + webElement;
  }

 }
 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for SwitchOnIframe
  */

 public static String SwitchOnIframe() {
  log.info("SwitchOnIframe is called");
  WebElement WE;
  try {
   WE = getWebElement(webElement);
   driver.switchTo().frame(WE);
  } catch (Throwable t) {
   t.printStackTrace();
   return "Failed - Element not found " + webElement;
  }
  return "Pass";
 }
 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for SwitchOnDefaultwidnow
  */

 public static String SwitchOnDefaultwidnow() {
  log.info("SwitchOnDefaultwidnow is called");
  try {
   driver.switchTo().defaultContent();
   Thread.sleep(5000);
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return "Pass";
 }

 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for non existance of web element
  */
 public static String expliciteWaitNonExistenece() throws Exception {
  WebDriverWait wait = new WebDriverWait(driver, 10000);
  try {
   wait.until(ExpectedConditions.invisibilityOf(getWebElement(webElement)));

   return "Pass";
  } catch (Exception e) {

   return "Failed - Element not found " + webElement;
  }
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for explicit wait , wait time to be set in config
  */

 public static String clickWhenReady(By locator, int timeout) {
  WebElement element = null;
  WebDriverWait wait = new WebDriverWait(driver, timeout);
  element = wait.until(ExpectedConditions.elementToBeClickable(locator));
  element.click();
  return "Pass";

 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for hard wait
  */

 public static String waitFor() throws InterruptedException {
  try {
   Thread.sleep(60000);
   log.info("Thread Wait is called");

  } catch (InterruptedException e) {
   return "Failed - unable to load the page";
  }
  return "Pass";
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function closing open browser
  */

 public static void closeBrowser() {
 // driver.quit();
 }

 /*
  * Author : Aarunshi Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for reading value from a web element ..to be called
  * internally not from Test controller
  */

 // **********Functions From Generic Helper Starts******************
 public static String readValueFromInput() {
  WebElement WE;
  try {
   WE = getWebElement(webElement);
   String value = new GenericHelper().readValueFromInput(WE);
   return value;
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return "Failed - Element not found " + webElement;
  }

 }
 // **********Functions From Generic Helper Ends********************

 // **********Functions From JavaScript Helper Starts******************
 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for scroll in to view of particular web element
  */
 public static String scrollToView() {
  WebElement WE;

  log.info("scrollToView is called");
  try {
   WE = getWebElement(webElement);
   JavaScriptHelper js = new JavaScriptHelper(driver);
   js.scrollToView(WE);

  } catch (Exception e) {

   e.printStackTrace();
   return "Failed - Element not found " + webElement;
  }

  return "Pass";
 }

 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for scroll in to view of particular web element and
  * click
  */
 public static String scrollToViewAndClick() {
  WebElement WE;

 log.info("scrollToViewAndClick is called");
  try {
   WE = getWebElement(webElement);
   JavaScriptHelper js = new JavaScriptHelper(driver);
   js.scrollToViewAndClick(WE);

  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return "Failed - Element not found " + webElement;
  }

  return "Pass";
 }

 // **********Functions From JavaScript Helper Ends********************

 // **********Functions From DropDown Helper Starts******************
 /*
  * Author : Deepti Date Created : 1 Oct 2018 Date Modified : 1 Nov 2018
  * Description : Function for selecting a value from dropdown
  */
 public static String selectUsingVisibleText() {
  WebElement WE;
  try {
   WE = getWebElement(webElement);
   new DropDownHelper(driver).selectUsingVisibleText(WE, TestData);

  } catch (Exception e) {

   return "Failed - Element not found " + webElement;
  }

  return "Pass";
 }
 
 public static String InputTextArr() {
	  log.info("InputText is called");
	  try {
	   getWebElement(webElement).sendKeys(arrTestData.get(0));
	  
	  } catch (Throwable t) {
	  
	   return "Failed - Element not found " + webElement;
	  }
	  return "Pass";
	 }
 
 public static String selectUsingVisibleTextArr() {
	  WebElement WE;
	  try {
	   WE = getWebElement(webElement);
	   new DropDownHelper(driver).selectUsingVisibleText(WE, arrTestData.get(0));

	  } catch (Exception e) {

	   return "Failed - Element not found " + webElement;
	  }

	  return "Pass";
	 }
  // Dashboard keyword functions started ------
          public static String verifycashflowpoint()
          { 
        	  int actual =0;
        	  try {
        		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
        	  }catch(Exception e)
        	  {	
        		  e.printStackTrace();
        		  return "Failed - cashflow Actual point show error:" + e;
        	  }
        	String response = Dashboard.verifycashflowpoint(actual, arrTestData.get(0), arrTestData.get(1));
        	
          return response;
          }
          public static String verifycashflowtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
			} catch (Exception e) {
				return "Failed - cashflow Actual text is showing error:" + e;
			}
        	  String response = (Dashboard.verifycashflowtext(actualtext, arrTestData.get(0), arrTestData.get(1))).trim();
           return response;
          }
          public static String verifycashflowanalysisandrecomtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				} catch (Exception e) {
				return "Failed - cashflow analysis or recommendation text is showing error:" + e;
			}
			String response = Dashboard.verifycashflowanalysisandrecomtext(arrTestData.get(0), arrTestData.get(1), arrTestData.get(2), actualtext, DB_Sec, DB_Sec_UserName, DB_Sec_Password);
			return response;
          }
//          public static String verifycashflowrecomtext()
//          { 
//        	  String actualtext;
//			try {
//				actualtext = (getWebElement(webElement).getText()).trim();
//				} catch (Exception e) {
//				return "cashflow recommendation strong text is showing error:" + e;
//			}
//			String conditiontype = (String) Dashboard.getcashflowBApoints(arrTestData.get(0), arrTestData.get(1)).get(3);
//        	String response = null;
//			try {
//				 response = (Dashboard.getanalysisandrecommendationtext("CASH", conditiontype, DB_Sec,DB_Sec_UserName,DB_Sec_Password).get(1)).trim();
//				if (response.equalsIgnoreCase(actualtext))
//				{
//					log.info("Cash Flow recommendation strong text has matched.");
//					return "Pass";
//				}
//			} catch (ClassNotFoundException e) {
//				
//				return "Cash flow recommendation has not matched" + e;
//			}
//           return "Failed recommendation text not same as Expected Text : " + response +" Actual text :" +actualtext;
//          }
          public static String verifycashflowcolor()
          {
        	  String color;
        	  String getcolorvalue = (String) Dashboard.getcashflowBApoints(arrTestData.get(0), arrTestData.get(1)).get(2);
  			try {
  				color = (getWebElement(webElement).getCssValue("color")).trim();
  				} catch (Exception e) {
  				return "Failed - cashflow Color text is showing error:" + e;
  				}
  			String response = Dashboard.verifycolortohex(color,getcolorvalue);
  			return response;
          }
        
          // Time in Business
          public static String verifytimeinbusinesspoint()
          { 
        	  int actual = 0;
        	  try {
    		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return "Failed - Time in Business Actual point show error:" + e;
    	  }
        	 String response = Dashboard.verifytimeinbusinesspoint(TestData, actual);
        	
          return response;
          }
          public static String verifytimeinbusinesstext()
          { 
        	  String actualtext;
        	  try {
				actualtext = (getWebElement(webElement).getText()).trim();
			} catch (Exception e) {
				return "Failed - Time In Business Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifytimeinbusinesstext(TestData,actualtext);
           return response;
          }
          public static String verifytimeinbusinessanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				} catch (Exception e) {
				return "Failed - Time in Business  text is showing error:" + e;
			}
			String response = Dashboard.verifytimeinbusinessanalysisandrecommtext(arrTestData.get(0), arrTestData.get(1), actualtext, DB_Sec, DB_Sec_UserName, DB_Sec_Password);
			return response;
          }
         public static String verifytimeinbusinesscolor()
          {
        	  String color;
        	  String getcolorvalue = (String) Dashboard.getageofbusinessBApoints(TestData).get(2);
    			try {
    				color = (getWebElement(webElement).getCssValue("color")).trim();
    				} catch (Exception e) {
    				return "Failed - Time in business Color text is showing error:" + e;
    				}
    			String response = Dashboard.verifycolortohex(color,getcolorvalue);
    			return response;
          }
          // Annual Revenue
          public static String verifyannualrevenuepoint()
          { 
        	  int actual = 0;
        	  try {
    		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return "Failed - Annual Revenue Actual point show error:" + e;
    	  }
        	 String response = Dashboard.verifyannualrevenuepoint(TestData, actual);
        	
          return response;
          }
          public static String verifyannualrevenuetext()
          { 
        	  String actualtext;
        	  try {
				actualtext = (getWebElement(webElement).getText()).trim();
			} catch (Exception e) {
				return "Failed - Annual Revenue Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifyannualrevenuetext(TestData,actualtext);
           return response;
          }

          public static String verifyannualrevenueanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				} catch (Exception e) {
				return "Failed - Annual Revenue  text is showing error:" + e;
			}
			String response = Dashboard.verifyannualrevenueanalysisandrecommtext(arrTestData.get(0), arrTestData.get(1), actualtext, DB_Sec, DB_Sec_UserName, DB_Sec_Password);
			return response;
          }
          public static String verifyannualrevenuecolor()
          {
        	  String color;
        	  String getcolorvalue = (String) Dashboard.getrevenueBApoints(TestData).get(2);
    			try {
    				color = (getWebElement(webElement).getCssValue("color")).trim();
    				} catch (Exception e) {
    				return "Failed - Annual Revenue Color text is showing error:" + e;
    				}
    			String response = Dashboard.verifycolortohex(color,getcolorvalue);
    			return response;
          }
      // Corporate Risk
          public static String verifycorporateriskpoint()
          { 
        	  int actual = 0;
        	  try {
    		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return "Failed - Corporate Risk Actual point show error:" + e;
    	  }
        	 
        	 String response = Dashboard.verifycorporateriskpoint(TestData, actual,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
        	
          return response;
          }
          public static String verifycorporaterisktext()
          { 
        	  String actualtext;
        	  try {
				actualtext = (getWebElement(webElement).getText()).trim();
			} catch (Exception e) {
				return "Failed - Corporate Risk Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifycorporaterisktext(TestData,actualtext,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
           return response;
          }
          public static String verifycorporateriskanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
			}catch (Exception e) {
				return "Failed - Corporate Risk analysis or recommendation text is showing error:" + e;
			}
			String response = Dashboard.verifycorporateriskanalysisandrecommtext(arrTestData.get(0),arrTestData.get(1), actualtext,DB_Prim,DB_Prim_UserName,DB_Prim_Password,DB_Sec,DB_Sec_UserName,DB_Sec_Password);
			return response;
		  }
          public static String verifycorporateriskcolor()
          {
        	  String color, getcolorvalue;
        	 try {
  				color = (getWebElement(webElement).getCssValue("color")).trim();
  				getcolorvalue = (String) Dashboard.getcorporateriskBApoints(TestData, DB_Prim,DB_Prim_UserName,DB_Prim_Password).get(2);
  				} catch (Exception e) {
  				return "Failed - Corporate Risk Color text is showing error:" + e;
  				}
  			String response = Dashboard.verifycolortohex(color,getcolorvalue);
  			return response;
          }
       // Industry Risk
          public static String verifyindustryriskpoint()
          { 
        	  int actual = 0;
        	  try {
    		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return "Failed - Industry Risk Actual point show error:" + e;
    	  }
        	 String response = Dashboard.verifyindustryriskpoint(arrTestData.get(0), arrTestData.get(1), actual,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
        	
          return response;
          }
          public static String verifyindustryrisktext()
          { 
        	  String actualtext;
        	  try {
				actualtext = (getWebElement(webElement).getText()).trim();
			} catch (Exception e) {
				return "Failed - Industry Risk Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifyindustryrisktext(arrTestData.get(0), arrTestData.get(1), actualtext,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
           return response;
          }
          public static String verifyindustryriskanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				} catch (Exception e) {
				return "Failed - Industry Risk analysis or recommendation text is showing error:" + e;
			}
			String response = Dashboard.verifyindustryriskanalysisandrecommtext(arrTestData.get(0), arrTestData.get(1), arrTestData.get(2), actualtext, DB_Prim,DB_Prim_UserName,DB_Prim_Password,DB_Sec,DB_Sec_UserName,DB_Sec_Password);
			return response;
          }
          public static String verifyindustryriskcolor()
          {
        	  String color, getcolorvalue;
        	 try {
  				color = (getWebElement(webElement).getCssValue("color")).trim();
  				getcolorvalue = (String) Dashboard.getindustryriskBApoints(arrTestData.get(0), arrTestData.get(1),DB_Prim,DB_Prim_UserName,DB_Prim_Password).get(2);
  				} catch (Exception e) {
  				return "Failed - Industry Risk Color text is showing error:" + e;
  				}
  			String response = Dashboard.verifycolortohex(color,getcolorvalue);
  			return response;
          }
          // Personal Credit Score
          public static String verifypersonalcreditscorepoint()
          { 
        	  int actual = 0;
        	  try {
        		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return " Failed - Personal Credit Score Actual point show error";
    	  }
        	  
        	 String response = Dashboard.verifypersonalcreditscorepoint(TestData, actual,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
        	
          return response;
          }
          public static String verifypersonalcreditscoretext()
          { 
        	  String actualtext;
        	  try {
				actualtext = getWebElement(webElement).getText();
			} catch (Exception e) {
				return "Failed - Personal credit Score Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifypersonalcreditscoretext(TestData, actualtext,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
           return response;
          }
          public static String verifypercreditscoreanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				
				} catch (Exception e) {
				return "Failed - Personal Credit Score analysis text is showing error:" + e;
			}
			
			String response = Dashboard.verifypercreditscoreanalysisandrecommtext(arrTestData.get(0), arrTestData.get(1), actualtext, DB_Prim,DB_Prim_UserName,DB_Prim_Password,DB_Sec,DB_Sec_UserName,DB_Sec_Password);
			return response;	
          }
          public static String verifypersonalcreditscorecolor()
          {
        	  String color, getcolorvalue;
        	 try {
  				color = (getWebElement(webElement).getCssValue("color")).trim();
  				getcolorvalue = (String) Dashboard.getpersonalcreditBApoints(TestData, DB_Prim,DB_Prim_UserName,DB_Prim_Password).get(2);
  				} catch (Exception e) {
  				return " Failed - Personal Credit Score Color text is showing error:" + e;
  				}
  			String response = Dashboard.verifycolortohex(color,getcolorvalue);
  			return response;
          }
          // Debt To Income
          public static String verifydebttoincomepoint()
          { 
        	  int actual = 0;
        	  try {
    		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
    	  }catch(Exception e)
    	  {
    		  return "Failed - Debt To Income Actual point show error:" + e;
    	  }
        	 String response = Dashboard.verifydebttoincomepoint(arrTestData.get(0), arrTestData.get(1), actual);
        	
          return response;
          }
          public static String verifydebttoincometext()
          { 
        	  String actualtext;
        	  try {
				actualtext = getWebElement(webElement).getText();
			} catch (Exception e) {
				return " Failed - Debt To Income Actual text is showing error:" + e;
			}
        	  String response = Dashboard.verifydebttoincometext(arrTestData.get(0), arrTestData.get(1), actualtext);
           return response;
          }
          public static String verifydebttoincomeanalysisandrecommtext()
          { 
        	  String actualtext;
			try {
				actualtext = (getWebElement(webElement).getText()).trim();
				} catch (Exception e) {
				return "Failed - Debt To Income analysis or recommendation text is showing error:" + e;
			}
			String response = Dashboard.verifydebttoincomeanalysisandrecommtext(arrTestData.get(0), arrTestData.get(1), arrTestData.get(2), actualtext, DB_Sec,DB_Sec_UserName,DB_Sec_Password);
			return response;
           }
         public static String verifydebttoincomecolor()
          {
        	  String color, getcolorvalue;
        	 try {
  				color = (getWebElement(webElement).getCssValue("color")).trim();
  				getcolorvalue = (String) Dashboard.getdebttoincomeBApoints(arrTestData.get(0), arrTestData.get(1)).get(2);
  				} catch (Exception e) {
  				return " Failed - Debt To Income Color text is showing error:" + e;
  				}
  			String response = Dashboard.verifycolortohex(color,getcolorvalue);
  			return response;
          }
          // BA Score calculation
          public static String verifybascore()
			{
        	  int actual = 0;
        	  try {
        		  actual = (int) Double.parseDouble(getWebElement(webElement).getText());
        	  }catch(Exception e)
        	  {
        		  return " Failed - BA Score Actual point show error:" + e;
        	  }
        	  //verifybascore( actual, useremail,  uiageofbusiness,  annualrevenue,  operatingexpense,  industryname,  subindustryname)
            	 String response = Dashboard.verifybascore(actual,arrTestData.get(0), arrTestData.get(1),arrTestData.get(2),arrTestData.get(3),arrTestData.get(4),arrTestData.get(5),DB_Sec,DB_Sec_UserName,DB_Sec_Password ,DB_Prim,DB_Prim_UserName,DB_Prim_Password);
            	
              return response;
			}


      /*
  * Author : Neha Date Created : 1 Oct 2018 Date Modified : 5 Nov 2018
  * Description : Function for capture b2c lead id
  */
 public static String verifyleadid () 
 {
	String response =vcfo.leadgenerate(arrTestData.get(0), arrTestData.get(1),DB_Prim, DB_Prim_UserName,DB_Prim_Password,DB_Sec,DB_Sec_UserName,DB_Sec_Password);
			
		return response;
 }

public static String readValueFromElement(){
	  
	  WebElement WE;
	  try {
		  WE = getWebElement(webElement);
		   String value = new GenericHelper().readValueFromElement(WE);
		   System.out.println(webElement);
		   return value;
		   
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   return "Failed - Element not found " + webElement;
		  }
	
}

/*
 * Author : Neha Date Created : 12 nov 2018 Date Modified : 13 Nov 2018
 * Description : Function for capture Password
 */
public static String passwordcapture() {
	   
	 // WebDriverWait wait = new WebDriverWait(driver, time);
	  try {
      System.out.println("asddasads " + arrTestData.get(0)) ;
	    String NewPassword = Password.passwordcapture(arrTestData.get(0));
	    return NewPassword;
      } catch (Exception e) {
	   // TODO Auto-generated catch block
	     //e.printStackTrace();
	     return "Failed - Element not found " + webElement;
	  }	
}
/*
 * Author : Neha Date Created : 15 nov 2018 Date Modified : 15 Nov 2018
 * Description : Function for Fluentwait
 */
public static String FluentWait() throws Exception {
	 long time = ObjectRepo.reader.getExplicitWait();
	 int flag=0;
	    WebElement WE;
	    {
	    	
	    	if(flag<=3)
	  try {
		  WE = getWebElement(webElement);
		  
		  
		  new WaitHelper(driver).waitForElementWithPolingInterval(driver, time, WE);
		  
		
	  } 
	    	catch (Exception e) {
	   // TODO Auto-generated catch block
	  // e.printStackTrace();
		 
	   
	  }
	  flag=flag+1;
}
return "Pass";
	    
	    }
	 


/*
 * Author : Shiv Date Created : 15 nov 2018 Date Modified : 15 Nov 2018
 * Description : Function for capture reset Url
 */

public static String urlcapture() {
	try {
	//System.out.println("asddasads " + arrTestData.get(o)) ;
	String url = Password.urlcapture(arrTestData.get(0));
((JavascriptExecutor)driver).executeScript("window.open();");
		  System.out.println("OpenNewTab");
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		  driver.switchTo().window(tabs.get(1));
	driver.get(url);
	
	return url; 
	 } catch (Exception e) {
		   // TODO Auto-generated catch block
		     //e.printStackTrace();
		     return "Failed - Element not found " + webElement;
		  }	
	
}

/*
 * Author : Neha Date Created for ICICI: 4jan2019 Date Modified : 5jan2019
 * Description : Function for capture verification URL
 */

public static String Captureverificationurl() {
	try {
	String url = Verificationlinkcapture_ICICI.Captureverificationurl(arrTestData.get(0),arrTestData.get(1),arrTestData.get(2));
    return url; 
	 } catch (Exception e) {
		   // TODO Auto-generated catch block
		     //e.printStackTrace();
		     return "Failed - not able to capture URL " + webElement;
		  }	
	
}

/*
 * Author : Neha Date Created for ICICI : 4jan2019 Date Modified : 5jan2019
 * Description : Function for switch to new tab
 */
public static String SwitchOnnewtab() {
	  log.info("SwitchOnnewtab is called");
	  try {
		// String oldTab = driver.getWindowHandle();
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		 int tabnumber =Integer.valueOf(TestData);
	     int newtabnumber=Integer.valueOf(TestData);
	    
		  driver.switchTo().window(tabs.get(tabnumber));
	      System.out.println("switched on new tab");
	    // tabs.remove(oldTab);
	   driver.switchTo().window(tabs.get(newtabnumber));
	   System.out.println("switched on second tab");
	  
	     return "Pass";
	      
	     } catch (Exception e) {
	   // TODO Auto-generated catch block
	         e.printStackTrace();
	         return "Failed - to switch on new tab " + webElement;
	  }
	
	 }


/*
 * Author : Neha Date Created for ICICI : 4jan2019 Date Modified : 5jan2019
 * Description : Function for switch to new tab
 */
public static String SwitchOnprevioustab() {
	  log.info("SwitchOnprevioustab is called");
	  try {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		  int defaulttabnumber=Integer.valueOf(TestData);
		 driver.switchTo().window(tabs.get(defaulttabnumber));
	    System.out.println("switched on previous tab");
	      return "Pass";
	      
	     } catch (Exception e) {
	   // TODO Auto-generated catch block
	         e.printStackTrace();
	         return "Failed - to switch on previous tab " + webElement;
	  }
	
	 }
/*
 * Author :Neha Date Created for ICICI : 11jan2019 Date Modified : 11jan2019
 * Description : Function for click on radio button
 */

public static String radioclick() {
	  WebElement WE;

	 log.info("radioclick is called");
	  try {
	   WE = getWebElement(webElement);
	   JavaScriptHelper js = new JavaScriptHelper(driver);
	   js.radiobuttonClick(WE);

	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	   return "Failed - Element not clickable " + webElement;
	  }

	  return "Pass";
	 }

/*
 * Author : Deepti Date Created for ICICI : 11jan2019 Date Modified : 11jan2019
 * Description : Function for verify radio button text is selected
 */

public static String verifyRadiobuttonselected() throws Exception
{
 WebElement WE;
 log.info("verifyRadiobuttonselected is called");
  try {
   WE = getWebElement(webElement);
   VerificationHelper vh = new VerificationHelper();
   boolean radiobutton=vh.verifyRadiobuttonIsSeclcted(WE);
   System.out.println("radiobutton is selected" +radiobutton);
   

  } catch (Exception e) {

   e.printStackTrace();
   return "Failed -  Element not selected " + webElement;
  }

  return "Pass";
 }


	


public static String verifyElementNotPresent() throws Exception
{
 WebElement WE;
 log.info("verifyElementNotPresent is called");
  try {
   WE = getWebElement(webElement);
   VerificationHelper vh = new VerificationHelper();
   boolean element=vh.verifyElementNotPresent(WE);
   
   System.out.println("element is present" +element);
   

  } catch (Exception e) {

   e.printStackTrace();
   return "Pass -  Element is Not present " + webElement;
  }

  return "Fail";
 }
 public static String verifyElementPresent() throws Exception
{
	 WebElement WE;
	 log.info("verifyElementPresent is called");
	  try {
	   WE = getWebElement(webElement);
	   VerificationHelper vh = new VerificationHelper();
	   boolean element=vh.verifyElementPresent(WE);
	   System.out.println("element is present"  +element);
	   

	  } catch (Exception e) {

	   e.printStackTrace();
	   return "Failed -  Element is Not present " + webElement;
	  }

	  return "Pass";
	 }
          
    // ---- Dashboard function has been finished ------

 // **********Functions From DropDown Helper Ends********************

 // **********Functions From Logger Helper Starts******************
 // **********Functions From Logger Helper Ends********************

 // **********Functions From Utility Helper Starts******************
 // **********Functions From Utility Helper Ends********************

 // **********Functions From Wait Helper Starts******************
 // **********Functions From Wait Helper Ends********************
 // **********Functions From Alert Helper Starts******************

 // **********Functions From Alert Helper Ends********************

 // **********Functions From Assertions Helper Starts******************
 // **********Functions From Assertions Helper Ends********************

 // **********Functions From Config_Reader Helper Starts******************
 // **********Functions From Config_Reader Helper Ends********************

 // **********Functions From CustomerListerners Helper Starts******************
 // **********Functions From CustomerListerners Helper Ends********************

 // **********Functions From DataBase Helper Starts******************
 // **********Functions From DataBase Helper Ends********************

 // **********Functions From ExcelReader Helper Starts******************
 // **********Functions From ExcelReader Helper Ends********************

}
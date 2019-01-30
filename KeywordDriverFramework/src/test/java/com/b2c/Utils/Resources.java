package com.b2c.Utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.b2c.Data.Xls_Reader;
import com.b2c.Helper.Config_Reader_Helper.ObjectRepo;
import com.b2c.Helper.Config_Reader_Helper.PropertyFileReader;
import com.b2c.Helper.Logger_Helper.LoggerHelper;
import com.b2c.Helper.Utility_Helper.ResourceHelper;
import com.b2c.Helper.Wait_Helper.WaitHelper;


public class Resources {
       
       
       public static WebDriver dr;
       public static WebDriver driver;
       public static Properties Repository = new Properties();
       public static Properties Config = new Properties();
       public static Properties AppText = new Properties();
       public static Xls_Reader SuiteData;
       public static Xls_Reader TestStepData;
       
       public static String keyword;
       public static String TCTestDataRow;
       public static String webElement;
       public static String TestDataField;
       public static String TestData;
       public static String   DB_Prim;
       public static String DB_Sec;
       public static List<String> arrTestData = new ArrayList();
       public static String ProceedOnFail;
       public static String TSID;
       public static String Description;
       public static File f ;
       public static FileInputStream FI;
        public static String  Erecipient ;
       public static String Esender ;
       public static String Ehost ;
       public static String EPassword ;
       //Platform changes start
       public static String   DB_Prim_UserName;
       public static String   DB_Prim_Password;
       public static String DB_Sec_UserName;
       public static String DB_Sec_Password;
       public static String Bank;
       public static String TC_DBRow_value;
       //Platform changes end
      
       public static final Logger log = LoggerHelper.getLogger(Resources.class.getName());//To get log object of Resource class
       
   	/* Author : Aarunshi
   	 * Date Created : 1 Oct 2018
   	 * Date Modified : 1 Nov 2018
   	 * Description : Function to load all the property files and get the object of Test suite and Test data excel
   	 */
       public static void Initialize() throws IOException {
              ObjectRepo.reader = new PropertyFileReader();
              Bank = ObjectRepo.reader.getBank();
              DB_Prim=ObjectRepo.reader.getPrimDB();
              DB_Sec=ObjectRepo.reader.getSecDB();
 Erecipient=ObjectRepo.reader.getErecipient() ;
              Esender=ObjectRepo.reader.getEsender() ;
              Ehost =ObjectRepo.reader.getEhost();
              EPassword=ObjectRepo.reader.getEPassword() ;
              
              		  DB_Prim_UserName=ObjectRepo.reader.getDB_Prim_UserName();
            		  DB_Prim_Password=ObjectRepo.reader.getDB_Prim_Password();
            		  DB_Sec_UserName=ObjectRepo.reader.getDB_Sec_UserName();
            		  DB_Sec_Password=ObjectRepo.reader.getDB_Sec_Password();
            
              //Get Excel obj of Test suites
              TestStepData = new Xls_Reader(System.getProperty("user.dir")+"//src//main//java//com//b2c//Data//"+Bank+"_TestSuite1Data.xlsx");
              SuiteData = new Xls_Reader(System.getProperty("user.dir")+"//src//main//java//com//b2c//Data//"+Bank+"_TestSuite1.xlsx");
              //Upload Property Files
              List<String> fileNames = new ArrayList<String>();
              DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(System.getProperty("user.dir")+"//src//main//java//com//b2c//repository"));
              for (Path path : directoryStream)
              {
                fileNames.add(path.toString());
                              }
              for ( int i=0; i<fileNames.size();i++)
              {
            	  f = new File(fileNames.get(i));
                  FI = new FileInputStream(f);
                  Repository.load(FI);
             	 
              }
              
              log.info("inside resources");

       }
       	/* Author : Aarunshi
      	 * Date Created : 1 Oct 2018
      	 * Date Modified : 1 Nov 2018
      	 * Description : launching the browser as per browser selected in conf files
      	 */
       	public void lounchBrowser() 
       	{

              ObjectRepo.reader = new PropertyFileReader();
              getBrowser(ObjectRepo.reader.getBrowser());
              setUpDriver(ObjectRepo.reader.getBrowser());
             

       }
    	/* Author : Aarunshi
      	 * Date Created : 1 Oct 2018
      	 * Date Modified : 1 Nov 2018
      	 * Description : Function to set the wait and browser maximize
      	 */
       public void setUpDriver(String bType) {

              driver.manage() .timeouts().pageLoadTimeout(ObjectRepo.reader.getPageLoadTime(),TimeUnit.SECONDS);
              driver.manage().timeouts() .implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
              driver.manage().window().maximize();

       }
   		/* Author : Aarunshi
     	 * Date Created : 1 Oct 2018
     	 * Date Modified : 1 Nov 2018
     	 * Description : Function to open the browser and platform as per conf selection
     	 */
       public void getBrowser(String bType) {
              if (System.getProperty("os.name").contains("Window"))// to support different OS
                                                                                                            
              {
                     if (bType.equals("FireFox")) {
                     System.setProperty("webdriver.gecko.driver",ResourceHelper.getResourcePath("/Drivers/geckodriver.exe"));
                           driver = new FirefoxDriver();

                     }
                     if (bType.equals("Chrome")) {
                     System.setProperty("webdriver.chrome.driver",ResourceHelper.getResourcePath("/Drivers/chromedriver.exe"));
                           driver = new ChromeDriver();
                          
                     }
              }
              
              
       }
       

       
}



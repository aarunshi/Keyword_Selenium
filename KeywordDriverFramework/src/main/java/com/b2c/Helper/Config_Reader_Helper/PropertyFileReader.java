package com.b2c.Helper.Config_Reader_Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;


import com.b2c.Browser.*;
import  com.b2c.Helper.Utility_Helper.*;
//To Read from config property file
public class PropertyFileReader implements ConfigReader {
	
	
		private Properties OR;
		 public PropertyFileReader() 
		{
			OR = new Properties();
									 		 
			 try {
				 //Loag Config proprties
				OR.load(ResourceHelper.getResourcePathInputStream("src/main/resources/Configuration/config.properties"));
				// System.out.println(ResourceHelper.getResourcePathInputStream("src/main/resources/configfile/config.properties"));
				//load log4jsrc\main\resources\Configuration\config.properties
				PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/resources/Log4J/log4j.properties"));
			 
			 } catch (IOException e) {
				
				e.printStackTrace();
			}			
		}
		
		public String getDB_Prim_UserName()
		{
			return OR.getProperty("DB_Prim_UserName");
		}
		public String getDB_Prim_Password()
		{
			return OR.getProperty("DB_Prim_Password");
		}
		public String getDB_Sec_UserName()
		{
			return OR.getProperty("DB_Sec_UserName");
		}
		public String getDB_Sec_Password()
		{
			return OR.getProperty("DB_Sec_Password");
		}
		public String getRealse()
		{
			return OR.getProperty("Realse");
		}
		public String getEnvironment()
		{
			return OR.getProperty("Environment");
		}
		public String getBank()
		{
			return OR.getProperty("Bank");
		}
		public String getBrowser()
		{
			return OR.getProperty("Browser");
			//switch(OR.getProperty("Browser"))
//			{
//			case "Iexplorer":
//				return BrowserType.Iexplorer;
//				
//			case "firefox":
//				return BrowserType.Firefox;
//			case "Chrome":
//				return BrowserType.Chrome;
//	
//			}		
//			return null;
	
		}
		
		public String getPrimDB()
		{
			
			return OR.getProperty("DB_Prim");
	
		}
		public String getSecDB()
		{
			
			return OR.getProperty("DB_Sec");
	
		}
		public String getWebSite()
		{
			return OR.getProperty("WebSite");
		}
		public int getPageLoadTime()
		{
			//System.out.println(OR.getProperty("PageLoadTime")+21);
			//return Integer.valueOf(OR.getProperty("PageLoadTime"));
			return Integer.parseInt(OR.getProperty("PageLoadTime"));
			//to convert string to integer
			
		}
		public int getImplicitWait()
		{
			return Integer.parseInt(OR.getProperty("ImplicitWait"));
		}
		public int getExplicitWait()
		{
			return Integer.parseInt(OR.getProperty("ExplicitWait"));
		}
		public String getLoggerLevel()		
		{
			return OR.getProperty("Logger.level");
			//switch( OR.getProperty("Logger.level"))
//			{
//			case "INFO":
//				return Level.INFO;
//			case "DEBUG":
//				return Level.DEBUG;
//			case "WARN":
//				return Level.WARN;
//			case "ERROR":
//				return Level.ERROR;
//			case "FATAL":
//				return Level.FATAL;
//				
//			}
			//return Level.ALL;
				
		}
		public String getDBType()
		{
			return OR.getProperty("DB.Type");
		}
		public String getDBConnString()
		{
			return OR.getProperty("DB.ConnectionString");
		}
	public String getErecipient() {
			return OR.getProperty("Erecipient");
		}

		public String getEhost() {
			return OR.getProperty("Ehost");
		}

		public String getEsender() {
			return OR.getProperty("Esender");
		}

		public String getEPassword() {
			return OR.getProperty("EPassword");
		}
		
	/*	public static void main(String[] args)
		{
			PropertyFileReader p = new PropertyFileReader();
			p.getPageLoadTime();
		}*/

	}




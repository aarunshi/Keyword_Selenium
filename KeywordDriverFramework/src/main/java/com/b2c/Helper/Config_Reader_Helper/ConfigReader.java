package com.b2c.Helper.Config_Reader_Helper;

import org.apache.log4j.Level;

import  com.b2c.Browser.BrowserType;

	public interface ConfigReader {
	public String getDB_Prim_UserName();
	public String getDB_Sec_UserName();
	public String getBrowser();
	public String getWebSite();
	public int getPageLoadTime();
	public int getImplicitWait();
	public int getExplicitWait();
	public String getLoggerLevel();
	public String getDBType();
	public String getDBConnString();
	public String getBank();
	public String getEnvironment();
	public String getRealse();
	public String getPrimDB();
	public String getSecDB();
	public String getDB_Prim_Password();
	public String getDB_Sec_Password();
	public String getErecipient();
	public String getEhost();
	public String getEsender();
	public String getEPassword();
	
	

}

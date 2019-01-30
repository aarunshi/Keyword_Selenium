package com.b2c.Helper.Logger_Helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.b2c.Helper.Utility_Helper.*;

public class LoggerHelper {
	
	public static boolean root = false;
	public static Logger getLogger(String ClassName)
	{
	if (root=true){
		
	}
	 root=true;
	PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/resources/Log4J/log4j.properties"));
	//System.out.println(ResourceHelper.getResourcePath("src/main/resources/configfile/log4j.properties"));
	return Logger.getLogger(ClassName);
	//return Logger.getLogger(clas);
	
}
//	public static void main(String[] args)
//	{
//		
//		
//		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/configfile/log4j.properties");
//	
//		System.out.println(logger.getAllAppenders());
//		
//}

}

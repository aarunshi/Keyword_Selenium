package com.b2c.Helper.Utility_Helper;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

//To get the path of any resource from target folder and convert it to fileinputstream

public class ResourceHelper {
	public static String getResourcePath(String resource)
	
	{
		
		return(getBaseResourcePath()+"/"+resource);
	}
	public static String getBaseResourcePath()
	{
		return System.getProperty("user.dir");
		//return(ResourceHelper.class.getClass().getResource("/").getPath());
	}
	
	public static FileInputStream getResourcePathInputStream(String Resource) throws FileNotFoundException
	{
		FileInputStream FinputStream = new FileInputStream(ResourceHelper.getResourcePath(Resource));
		return FinputStream;
	}
//public static void main(String[] args)
//	{
//	System.out.println(ResourceHelper.getResourcePath("src/main/resources/configfile/log4j.properties"));
//	
//	}

}

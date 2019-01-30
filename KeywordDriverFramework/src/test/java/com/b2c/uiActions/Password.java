package com.b2c.uiActions;

import org.apache.log4j.Logger;

import com.b2c.Helper.Logger_Helper.LoggerHelper;


public class Password {
	public static final Logger log = LoggerHelper.getLogger(Password.class.getName());
public static String passwordcapture(String mailbodycontent) {
	String password=null;
	
	String msg[]=mailbodycontent.split("Password:");
	System.out.println("asdsd" + msg[1]);
	String passwordtext[] = msg[1].split("LOGIN TO THE DASHBOARD");
	password = passwordtext[0].trim();
	log.info("Password is : " + password);
	return password;
		
}

public static String urlcapture(String mailbodycontent) {
	String url=null;
	
	String msg[]=mailbodycontent.split(" reset it.");
	System.out.println("asdsd" + msg[1]);
	String passwordtext[] = msg[1].split("If clicking");
	url = passwordtext[0].trim();
	log.info("url is : " + url);
	return url;
		
}

}

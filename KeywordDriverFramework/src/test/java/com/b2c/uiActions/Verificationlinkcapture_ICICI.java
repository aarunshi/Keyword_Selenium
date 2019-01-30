package com.b2c.uiActions;

import org.apache.log4j.Logger;

import com.b2c.Helper.Logger_Helper.LoggerHelper;

public class Verificationlinkcapture_ICICI {
	public static final Logger log = LoggerHelper.getLogger(Verificationlinkcapture_ICICI.class.getName());
	
	public static String Captureverificationurl(String mailbodycontent,String msgtext1,String msgtext2) {
		String url=null;
		String msg[]=mailbodycontent.split(msgtext1);
		System.out.println("Text to be:" + msg[1]);
		//System.out.println("text to be:" + msg[1]);
		String urllink[] = msg[1].split(msgtext2);
		url = urllink[0].trim();
		log.info("url is : " + url);
		return url;
		}
	
	
	
	}
		



package com.b2c.uiActions;

import java.sql.SQLException;

import com.b2c.Helper.DataBase_Helper.DataBase;

public class vcfo {

public static String leadgenerate(String emaildaddress, String columname,String constring, String username, String password,String secconstring, String secusername, String secpassword)
{
	String 	fetchleadid = null,compareleadid = null;
	try {
			String vcfoquery = "SELECT b2c_lead_id as leadid FROM `vcfo_users` WHERE email LIKE \"%"+emaildaddress+"%\"";
			String b2cquery =  "SELECT id as leadid FROM `jos_users`  WHERE email LIKE \"%"+emaildaddress+"%\"";
			fetchleadid = DataBase.getData(vcfoquery, columname, constring, username, password).toString();
			compareleadid = DataBase.getData(b2cquery, columname, secconstring, secusername, secpassword).toString();
		} catch (Exception e1) {
			return "Failed - Lead Id has not fetched from database";
			
		}
	  int expectedresult = (int) Double.parseDouble(fetchleadid);
	  int actualresult = (int) Double.parseDouble(compareleadid);
	  if (expectedresult == actualresult)
	  {
		  String actualresult1 = String.valueOf(actualresult);
		  return actualresult1;
	  }
	  
	return "Failed";
}
	

	
}
 
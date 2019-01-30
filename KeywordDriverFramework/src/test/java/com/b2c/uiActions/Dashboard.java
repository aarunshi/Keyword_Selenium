package com.b2c.uiActions;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.Color;
import com.b2c.Helper.DataBase_Helper.DataBase;
import com.b2c.Helper.Logger_Helper.LoggerHelper;


public class Dashboard {
	public static final Logger log = LoggerHelper.getLogger(Dashboard.class.getName());
	public  static List<Object> getpersonalcreditBApoints(String value,String constring, String username,String password) throws NumberFormatException, ClassNotFoundException
	{
		int creditscore = 0;
//		String cconstring = "jdbc:mysql://b2c-cdfi-rdsdb01.ciimbh2dtgnd.us-east-1.rds.amazonaws.com/db_vcfo_stage";
//		String cusername = "db_vcfo_stg_user";
//		String cpassword = "1q2w3e4R";
		String query = "SELECT fico_score FROM `vcfo_users` where email LIKE \"%"+value +"%\"";
		try {
			creditscore = (int) Double.parseDouble(DataBase.getData(query, "fico_score", constring, username,password));			
		} catch (SQLException e) {
			log.info( "SQL data has not been fetched for corporaterisk");
			e.printStackTrace();
		}
		List<Object> creditpoint = new ArrayList<Object>();
		
		if (creditscore >= 500 && creditscore <=575)
		{
			creditpoint.add(4);
			creditpoint.add("Status: Too High ");
			creditpoint.add("red");
			creditpoint.add("hightcredit");
			return creditpoint;
		} else if (creditscore > 575 && creditscore <=625)
		{
			creditpoint.add(8);
			creditpoint.add("Status: Average ");
			creditpoint.add("yellow");
			creditpoint.add("mediumcredit");
			return creditpoint;
		} else if (creditscore > 625 && creditscore <=660)
		{
			creditpoint.add(13);
			creditpoint.add("Status: Average ");
			creditpoint.add("yellow");
			creditpoint.add("mediumcredit");
			return creditpoint;
		} else if (creditscore > 660 && creditscore <=720)
		{
			creditpoint.add(18);
			creditpoint.add("Status: Above Average ");
			creditpoint.add("green");
			creditpoint.add("lowtcredit");
			return creditpoint;
		} else if (creditscore > 720)
		{
			creditpoint.add(21);
			creditpoint.add("Status: Above Average ");
			creditpoint.add("green");
			creditpoint.add("lowtcredit");
			return creditpoint;
		}else 
			creditpoint.add(0);
			creditpoint.add("Status: Too High ");
			creditpoint.add("red");
			creditpoint.add("hightcredit");
		
		return creditpoint;
	}
	public  static List<Object> getdebttoincomeBApoints(String annualrevenue, String operatingexpense)
	{
		float Iannualrevenue = (float)Double.parseDouble(annualrevenue);
 	   	float Ioperatingexpense = (float)Double.parseDouble(operatingexpense);
 	   	int debt2income = (int) Math.round((Ioperatingexpense/Iannualrevenue)*100);
		
		List<Object> dtpoint = new ArrayList<Object>();
		if (debt2income >= 0 && debt2income <=25)
		{
			dtpoint.add(9);
			dtpoint.add("Status: Above Average ");
			dtpoint.add("green");
			dtpoint.add("lowdebt");
			return dtpoint;
		} else if (debt2income > 25 && debt2income <=50)
		{
			dtpoint.add(7);
			dtpoint.add("Status: Average ");
			dtpoint.add("yellow");
			dtpoint.add("meddebt");
			return dtpoint;
		} else if (debt2income > 50 && debt2income <=75)
		{
			dtpoint.add(4);
			dtpoint.add("Status: Too High ");
			dtpoint.add("red");
			dtpoint.add("highdebt");
			return dtpoint;
		} else if (debt2income > 75 && debt2income <=100)
		{
			dtpoint.add(1);
			dtpoint.add("Status: Too High ");
			dtpoint.add("red");
			dtpoint.add("highdebt");
			return dtpoint;
		} else if (debt2income > 100)
			dtpoint.add(0);
			dtpoint.add("Status: Too High ");
			dtpoint.add("red");
			dtpoint.add("highdebt");
			return dtpoint;
	}
// calculate industry risk on the basis of industry and Sub industry name from GUI interface
	public  static List<Object> getindustryriskBApoints(String industryname, String subindustryname, String constring, String username, String password) throws NumberFormatException, ClassNotFoundException
	{
		List<Object> indpoint = new ArrayList<Object>();
		int industryrisk =0;
		String query = "SELECT orgcat.riskFactor as riskfactor FROM `vcfo_mst_organiztion_cat` as orgcat inner join vcfo_mst_organization as org on org.oid = orgcat.oid where org.orgtype like \"%"+industryname+"%\" AND orgcat.catname LIKE \"%"+subindustryname +"%\" LIMIT 1";
		try {
				industryrisk = (int) Double.parseDouble(DataBase.getData(query, "riskfactor",constring,username,password));			
		} catch (SQLException e) {
			log.info( "SQL data has not been fetched for industryrisk");
			e.printStackTrace();
		}
		if (industryrisk == 1 )
		{
			indpoint.add(9);
			indpoint.add("Status: Average ");
			indpoint.add("yellow");
			indpoint.add("mediumrisk");
			return indpoint;
		}
		else if (industryrisk == 2)
		{
			indpoint.add(15);
			indpoint.add("Status: Above Average ");
			indpoint.add("green");
			indpoint.add("lowrisk");
			return indpoint;
		} 
		else if (industryrisk >= 3)
		{
			indpoint.add(5);
			indpoint.add("Status: Too High ");
			indpoint.add("red");
			indpoint.add("highrisk");
		 return indpoint;
		}
		return indpoint;
	}
	// calculate age of business points	
	public static List<Object> getageofbusinessBApoints(String value)
	{
		int ageofbusiness = (int) Double.parseDouble(value);
		List<Object> agepoint = new ArrayList<Object>();
		while (ageofbusiness != 5)
		{
			if(ageofbusiness > 36)
		{
			agepoint.add(20);
			agepoint.add("Status: Above Average ");
			agepoint.add("green");
			agepoint.add("hightime");
			return agepoint;
		}
			else if (ageofbusiness >= 25 && ageofbusiness <= 35)
		{
			switch (ageofbusiness)
			{
			case 25:
			case 26:
				agepoint.add((int) Math.round((80*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 27:
			case 28:
			case 29:
				agepoint.add((int) Math.round((85*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 30:
			case 31:
			case 32:
			case 33:
				agepoint.add((int) Math.round((90*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 34:
			case 35:
				agepoint.add((int) Math.round((95*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			}
		}
		else if (ageofbusiness == 24)
		{
			agepoint.add(15);
			agepoint.add("Status: Above Average ");
			agepoint.add("green");
			agepoint.add("hightime");
			return agepoint;
		}
		else if (ageofbusiness >= 13 && ageofbusiness <= 23)
		{
			switch (ageofbusiness)
			{
			case 13:
				agepoint.add((int) Math.round((45*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 14:
			case 15:
				agepoint.add((int) Math.round((50*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 16:
			case 17:
				agepoint.add((int) Math.round((55*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 18:
			case 19:
				agepoint.add((int) Math.round((60*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 20:
			case 21:
				agepoint.add((int) Math.round((65*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			case 22:
			case 23:
				agepoint.add((int) Math.round((70*0.2)));
				agepoint.add("Status: Above Average ");
				agepoint.add("green");
				agepoint.add("hightime");
				return agepoint;
			}
		}
		else if (ageofbusiness == 12)
		{
			agepoint.add(8);
			agepoint.add("Status: Too High ");
			agepoint.add("red");
			agepoint.add("lowtime");
			return agepoint;
		}
		else if (ageofbusiness >= 1 && ageofbusiness <= 11)
		{
			switch (ageofbusiness)
			{
			case 1:
				agepoint.add((int) Math.round((10*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			case 2:
			case 3:
				agepoint.add((int) Math.round((15*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			case 4:
			case 5:
				agepoint.add((int) Math.round((20*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			case 6:
			case 7:
				agepoint.add((int) Math.round((25*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			case 8:
			case 9:
				agepoint.add((int) Math.round((30*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			case 10:
			case 11:
				agepoint.add((int) Math.round((35*0.2)));
				agepoint.add("Status: Too High ");
				agepoint.add("red");
				agepoint.add("lowtime");
				return agepoint;
			}
		}
	 }
		agepoint.add((int) Math.round((5*0.2)));
		agepoint.add("Status: Too High ");
		agepoint.add("red");
		agepoint.add("lowtime");
		return agepoint;
	}
	public  static List<Object> getrevenueBApoints(String value)
	{
		int annualrevenue = (int) Double.parseDouble(value);
		List<Object> revpoint = new ArrayList<Object>();
		if (annualrevenue >= 1 && annualrevenue <=50000)
		{
			revpoint.add((int) Math.round((11*0.18)));
			revpoint.add("Status: Too High ");
			revpoint.add("red");
			revpoint.add("lowrevenue");
			return revpoint;
		} else if (annualrevenue > 50000 && annualrevenue <=250000)
		{
			revpoint.add((int) Math.round((22*0.18)));
			revpoint.add("Status: Too High ");
			revpoint.add("red");
			revpoint.add("lowrevenue");
			return revpoint;
		} else if (annualrevenue > 250000 && annualrevenue <=500000)
		{
			revpoint.add((int) Math.round((33*0.18)));
			revpoint.add("Status: Average ");
			revpoint.add("yellow");
			revpoint.add("mediumrevenue");
			return revpoint;
		} else if (annualrevenue > 500000 && annualrevenue <=1000000)
		{
			revpoint.add((int) Math.round((56*0.18)));
			revpoint.add("Status: Average ");
			revpoint.add("yellow");
			revpoint.add("mediumrevenue");
			return revpoint;
		} else if (annualrevenue > 1000000 && annualrevenue <=2500000)
		{
			revpoint.add((int) Math.round((67*0.18)));
			revpoint.add("Status: Average ");
			revpoint.add("yellow");
			revpoint.add("mediumrevenue");
			return revpoint;
		}else if (annualrevenue > 2500000 && annualrevenue <=5000000)
		{
			revpoint.add((int) Math.round((78*0.18)));
			revpoint.add("Status: Above Average ");
			revpoint.add("green");
			revpoint.add("highrevenue");
			return revpoint;
		}else if (annualrevenue > 5000000 && annualrevenue <=10000000)
		{
			revpoint.add((int) Math.round((89*0.18)));
			revpoint.add("Status: Above Average ");
			revpoint.add("green");
			revpoint.add("highrevenue");
			return revpoint;
		}
		else if (annualrevenue > 10000000)
		{	revpoint.add((int) Math.round((100*0.18)));
			revpoint.add("Status: Above Average ");
			revpoint.add("green");
			revpoint.add("highrevenue");
			return revpoint;
			
		}else if (annualrevenue == 0)
		revpoint.add((int) Math.round((6*0.18)));
		revpoint.add("Status: Too High ");
		revpoint.add("red");
		revpoint.add("lowrevenue");
		return revpoint;
	}
	
	public static List<Object> getcorporateriskBApoints(String value,String constring, String username,String password) 
	{	
		int legalentity =0;
//		String cconstring = "jdbc:mysql://b2c-cdfi-rdsdb01.ciimbh2dtgnd.us-east-1.rds.amazonaws.com/db_vcfo_stage";
//		String cusername = "db_vcfo_stg_user";
//		String cpassword = "1q2w3e4R";
		String query = "SELECT app.legal_entity as legalentity FROM vcfo_users as u JOIN vcfo_app_business as app ON u.id = app.user_id where u.email LIKE \"%"+value +"%\" LIMIT 1";
	
		try {
			legalentity = (int) Double.parseDouble(DataBase.getData(query, "legalentity",constring, username,password));
			log.info("legalentity " + legalentity);
		} catch (Exception e) {
			log.info( "SQL data has not been fetched for corporaterisk" + e);
			
		}
		List<Object> corportatpoint = new ArrayList<Object>();
		if(legalentity ==1)
		{
			
			corportatpoint.add((int) Math.round((43*0.07)));
			corportatpoint.add("Status: Too High ");
			corportatpoint.add("red");
			corportatpoint.add("highcoprate");
			return corportatpoint;
		}else if (legalentity == 2)
		{
			corportatpoint.add((int) Math.round((71*0.07)));
			corportatpoint.add("Status: Average ");
			corportatpoint.add("yellow");
			corportatpoint.add("medcoprate");
			return corportatpoint;
		}else if (legalentity >= 3) 
		{
			corportatpoint.add((int) Math.round((100*0.07)));
			corportatpoint.add("Status: Above Average ");
			corportatpoint.add("green");
			corportatpoint.add("lowcoprate");
			return corportatpoint;
		}
		return corportatpoint;
	}
	public static List<Object> getcashflowBApoints (String annualrevenue, String operatingexpense)
	{	
		float Iannualrevenue = (float)Double.parseDouble(annualrevenue);
 	   	float Ioperatingexpense = (float)Double.parseDouble(operatingexpense);
 	   	int cashmargin = (int) Math.round(100*((0.9*Iannualrevenue)-Ioperatingexpense)/Iannualrevenue);
		List<Object> cashflowpoint = new ArrayList<Object>();
		if (cashmargin >= 1 && cashmargin <=5)
		{
			cashflowpoint.add((int) Math.round((20*0.1)));
			cashflowpoint.add("Status: Too High ");
			cashflowpoint.add("red");
			cashflowpoint.add("lowcash");
			return cashflowpoint;
		} else if (cashmargin > 5 && cashmargin <=15)
		{
			cashflowpoint.add((int) Math.round((30*0.1)));
			cashflowpoint.add("Status: Average ");
			cashflowpoint.add("yellow");
			cashflowpoint.add("mediumcash");
			return cashflowpoint;
		} else if (cashmargin > 16 && cashmargin <=25)
		{
			cashflowpoint.add((int) Math.round((40*0.1)));
			cashflowpoint.add("Status: Above Average ");
			cashflowpoint.add("green");
			cashflowpoint.add("highcash");
			return cashflowpoint;
		} else if (cashmargin > 26 && cashmargin <=50)
		{
			cashflowpoint.add((int) Math.round((60*0.1)));
			cashflowpoint.add("Status: Above Average ");
			cashflowpoint.add("green");
			cashflowpoint.add("highcash");
			return cashflowpoint;
		} else if (cashmargin > 51 && cashmargin <=75)
		{
			cashflowpoint.add((int) Math.round((80*0.1)));
			cashflowpoint.add("Status: Above Average ");
			cashflowpoint.add("green");
			cashflowpoint.add("highcash");
			return cashflowpoint;
		}else if (cashmargin > 76 && cashmargin <=100)
		{
			cashflowpoint.add((int) Math.round((100*0.1)));
			cashflowpoint.add("Status: Above Average ");
			cashflowpoint.add("green");
			cashflowpoint.add("highcash");
			return cashflowpoint;
		}else if (cashmargin == 0)
		cashflowpoint.add(0);
		cashflowpoint.add("Status: Too High ");
		cashflowpoint.add("red");
		cashflowpoint.add("lowcash");
		return cashflowpoint;
		}
	public static int calculateBAScore(String useremail, String uiageofbusiness, String annualrevenue, String operatingexpense, String industryname, String subindustryname, String constring, String username,String password, String pconstring, String pusername, String ppassword)
	{
		//System.out.println(useremail + uiageofbusiness + annualrevenue + operatingexpense + industryname + subindustryname + constring + username  + password);
		int BAscore = 0;
		try {
		
		int creditscore = (Integer) getpersonalcreditBApoints(useremail,pconstring, pusername,ppassword).get(0);
		int debtoincome = (Integer)getdebttoincomeBApoints(annualrevenue,operatingexpense).get(0);
		int industrypoint = (Integer)getindustryriskBApoints(industryname,subindustryname,pconstring, pusername,ppassword).get(0);
		int ageofbusiness = (Integer)getageofbusinessBApoints(uiageofbusiness).get(0);
		int revenue = (Integer) getrevenueBApoints(annualrevenue).get(0);
		int corporatepoint = (Integer) getcorporateriskBApoints(useremail,pconstring, pusername,ppassword).get(0);
		int cashflowpoint = (Integer) getcashflowBApoints(annualrevenue,operatingexpense).get(0);
		log.info("BA score Value "+ creditscore + " " + debtoincome + " " + industrypoint + " "+ageofbusiness + " "+corporatepoint + " " + revenue + " "+cashflowpoint);
		BAscore = creditscore+debtoincome+industrypoint+ageofbusiness+revenue+corporatepoint+cashflowpoint;
		log.info("Ba score has been calculated successfully.");
		}catch(Exception e)
		{
			log.info("BA score has not been calculated. show error: --- " + e);
		}
		
		return BAscore;
		
	}
	// Cash flow test cases executed from here 
	
	public static String verifycashflowpoint(int actualvalue, String Annualrevenue, String operatingexpense)
	{
		int expectedcashflowpoint =  (Integer) getcashflowBApoints(Annualrevenue,operatingexpense).get(0);
        if (expectedcashflowpoint == actualvalue)
   	   	log.info("Cash Flow Score has been matched.");
   		return "Pass";
   	}
	public static String verifycashflowtext(String actualtext, String Annualrevenue, String operatingexpense)
	{
		String expectedtext = (String) getcashflowBApoints(Annualrevenue, operatingexpense).get(1);
		log.info("Expected Result:  " + expectedtext);
		log.info(expectedtext.contains(actualtext));
		boolean actualtextcont = expectedtext.contains(actualtext);
		if (actualtextcont == true)
		{
			return "Pass";
		}
		return "Failed - Cash Flow Status has not matched" + expectedtext+ "with " + actualtext;
			
	}
	public static String verifycashflowanalysisandrecomtext(String Annualrevenue, String operatingexpense, String value, String actualtext, String constring,String dbusername,String dbpassword)
	{
		String conditiontype = (String) getcashflowBApoints(Annualrevenue, operatingexpense).get(3);
		int analysisorrecom = (int) Double.parseDouble(value);
		String response = null;
		try {
			response = (Dashboard.getanalysisandrecommendationtext("Cash", conditiontype, constring,dbusername,dbpassword).get(analysisorrecom)).trim();
			if (response.equalsIgnoreCase(actualtext))
			{
				log.info("Cash Flow Analysis or Recommendation has matched.");
				return "Pass";
			}
		} catch (ClassNotFoundException e) {
			
			return "Failed - Cash flow analysis or Recommendation has not matched" + e;
		}
       return "Failed analysis or recommendation text not same as Expected Text : " + response +" Actual text :" +actualtext;
	}
	// Cash Flow test cases executed finished
	
	//time in business test cases executed from here
	public static String verifytimeinbusinesspoint(String ageofbusiness, int actualresult)
	{
		int expectedresult =  (Integer) getageofbusinessBApoints(ageofbusiness).get(0);
        if (expectedresult == actualresult)
   	   	log.info("Time in Business Score has been matched.");
   		return "Pass";
   	}
	public static String verifytimeinbusinesstext(String ageofbusiness, String actualtext)
	{
		String expectedtext = (String) getageofbusinessBApoints(ageofbusiness).get(1);
		log.info("Expected Result:  " + expectedtext);
		log.info(expectedtext.contains(actualtext));
		boolean actualtextcont = expectedtext.contains(actualtext);
		if (actualtextcont == true)
		{
			return "Pass";
		}
		return "Failed - Time in Business Status has not matched " + expectedtext+ "with " + actualtext;
			
	}
	public static String verifytimeinbusinessanalysisandrecommtext(String ageofbusiness, String value, String actualtext, String constring,String dbusername,String dbpassword)
	{
		String conditiontype = (String) Dashboard.getageofbusinessBApoints(ageofbusiness).get(3);
		String response = null;
		try {
			int analysisorrecom = (int) Double.parseDouble(value);
			response = (Dashboard.getanalysisandrecommendationtext("Time", conditiontype, constring,dbusername,dbpassword).get(analysisorrecom)).trim();
			if (response.equalsIgnoreCase(actualtext))
			{
				log.info("Time In Business Analysis or Recommendation has matched.");
				return "Pass";
			}
		} catch (ClassNotFoundException e) {
			
			return "Failed - Time in Business analysis or Recommendation has not matched" + e;
		}
       return "Failed - analysis or Recommendation text not same as Expected Text : " + response +" Actual text :" +actualtext;
      }
	// Annual revenue
	public static String verifyannualrevenuepoint(String annualrevenue, int actualresult)
	{
		int expectedresult =  (Integer) getrevenueBApoints(annualrevenue).get(0);
        if (expectedresult == actualresult)
   	   	log.info("Annual Revenue Score has been matched.");
   		return "Pass";
   	}
	public static String verifyannualrevenuetext(String annualrevenue, String actualtext)
	{
		String expectedtext = (String) getrevenueBApoints(annualrevenue).get(1);
		log.info("Expected Result:  " + expectedtext);
		log.info(expectedtext.contains(actualtext));
		boolean actualtextcont = expectedtext.contains(actualtext);
		if (actualtextcont == true)
		{
			return "Pass";
		}
		return "Failed - Annual Revenue Status has not matched " + expectedtext+ "with " + actualtext;
	}
	public static String verifyannualrevenueanalysisandrecommtext(String annualrevenue, String value, String actualtext, String constring,String dbusername,String dbpassword)
	{
		String conditiontype = (String) Dashboard.getrevenueBApoints(annualrevenue).get(3);
		String response = null;
		try {
			int analysisorrecom = (int) Double.parseDouble(value);
			response = (Dashboard.getanalysisandrecommendationtext("Annual", conditiontype, constring,dbusername,dbpassword).get(analysisorrecom)).trim();
			if (response.equalsIgnoreCase(actualtext))
			{
				log.info("Time In Business Analysis or recommendation has matched.");
				return "Pass";
			}
		} catch (ClassNotFoundException e) {
			
			return "Failed - Time in Business analysis has not matched" + e;
		}
       return "Failed - analysis text not same as Expected Text : " + response +" Actual text :" +actualtext;
      }
	// Corporate Risk
		public static String verifycorporateriskpoint(String useremail, int actualresult,String constring, String username,String password)
		{
			int expectedresult = 0;
			
				try {
					expectedresult = (Integer) getcorporateriskBApoints(useremail,constring, username,password).get(0);
					} catch (Exception e) {
					e.printStackTrace();
				}
		   if (expectedresult == actualresult)
	   	   	log.info("Corporate Risk Score has been matched.");
	   		return "Pass";
	   	}
		public static String verifycorporaterisktext(String useremail, String actualtext,String constring, String username,String password)
		{
			String expectedtext = null;
			try {
				expectedtext = (String) getcorporateriskBApoints(useremail,constring,username,password).get(1);
				} catch (Exception e) {
				e.printStackTrace();
			}
			log.info("Expected Result:  " + expectedtext);
			boolean actualtextcont = expectedtext.contains(actualtext);
			if (actualtextcont == true)
			{
				return "Pass";
			}
			return "Failed - Corporate Risk Status has not matched " + expectedtext+ "with " + actualtext;
		}
		public static String verifycorporateriskanalysisandrecommtext(String emailaddress, String value, String actualtext, String constring,String dbusername,String dbpassword,String secconstring,String secdbusername,String secdbpassword)
		{
			String response = null;
			try {
				String conditiontype = (String) Dashboard.getcorporateriskBApoints(emailaddress, constring,dbusername,dbpassword).get(3);

				int analysisorrecom = (int) Double.parseDouble(value);
				
				response = (Dashboard.getanalysisandrecommendationtext("Coprate", conditiontype, secconstring,secdbusername,secdbpassword).get(analysisorrecom)).trim();
				if (response.equalsIgnoreCase(actualtext))
				{
					log.info("Corporate Risk Analysis or Recommendation has matched.");
					return "Pass";
				}
			} catch (ClassNotFoundException e) {
			return "Failed - Corporate Risk analysis or recommendation has not matched" + e;
			}
           return "Failed - analysis or recommendation text not same as Expected Text : " + response +" Actual text :" +actualtext;
	      }
		// Industry Risk
				public static String verifyindustryriskpoint(String industryname, String subindustryname, int actualresult, String constring, String username, String password)
				{
					int expectedresult = 0;
					try {
							expectedresult = (Integer) getindustryriskBApoints(industryname, subindustryname, constring, username,password).get(0);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
				   if (expectedresult == actualresult)
			   	   	log.info("Industry Risk Score has been matched.");
			   		return "Pass";
			   	}
				public static String verifyindustryrisktext(String industryname, String subindustryname, String actualtext,String constring, String username, String password)
				{
					String expectedtext = null;
					try {
						expectedtext = (String) getindustryriskBApoints(industryname, subindustryname,constring, username,password).get(1);
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					}
					log.info("Expected Result:  " + expectedtext);
					log.info(expectedtext.contains(actualtext));
					boolean actualtextcont = expectedtext.contains(actualtext);
					if (actualtextcont == true)
					{
						return "Pass";
					}
					return "Failed - Industry Risk Status has not matched " + expectedtext+ "with " + actualtext;
				}
				public static String verifyindustryriskanalysisandrecommtext(String industryname, String subindustry,String value, String actualtext, String constring,String dbusername,String dbpassword,String secconstring,String secdbusername,String secdbpassword)
				{
					String response = null;
					try {
						String conditiontype = (String) Dashboard.getindustryriskBApoints(industryname, subindustry, constring,dbusername,dbpassword).get(3);
						int analysisorrecom = (int) Double.parseDouble(value);
						response = (Dashboard.getanalysisandrecommendationtext("Industry", conditiontype, secconstring,secdbusername,secdbpassword).get(analysisorrecom)).trim();
						if (response.equalsIgnoreCase(actualtext))
						{
							log.info("Industry Risk Analysis or recommendation has matched.");
							return "Pass";
						}
					} catch (ClassNotFoundException e) {
						
						return " Failed - Industry Risk analysis recommendation has not matched" + e;
					}
		           return "Failed - analysis or recommendationtext not same as Expected Text : " + response +" Actual text :" +actualtext;
			      }
				// Personal Credit Score
				public static String verifypersonalcreditscorepoint(String useremail, int actualresult, String constring, String username,String password)
				{
					System.out.println("useremal" + useremail + " asd " + actualresult );
				
					int expectedresult = 0;
					try {
						expectedresult = (Integer) getpersonalcreditBApoints(useremail,constring, username,password).get(0);
						
					}  catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					System.out.println(expectedresult+"adsda "+ actualresult);
				   if (expectedresult == actualresult)
				   {
			   	   	log.info("Personal Credit Score has been matched.");
			   		return "Pass";
				   }
				   return "Failed - Personal Credit Score do not matched";
			   	}
				public static String verifypersonalcreditscoretext(String useremail, String actualtext,String constring, String username,String password)
				{
					String expectedtext =  null;
					try {
						expectedtext = (String) getpersonalcreditBApoints(useremail,constring, username,password).get(1);
					}  catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					log.info("Expected Result:  " + expectedtext);
					log.info(expectedtext.contains(actualtext));
					boolean actualtextcont = expectedtext.contains(actualtext);
					if (actualtextcont == true)
					{
						return "Pass";
					}
					return "Failed - Personal Credit Score Status has not matched " + expectedtext+ "with " + actualtext;
				}
				public static String verifypercreditscoreanalysisandrecommtext(String email,String value, String actualtext, String constring,String dbusername,String dbpassword,String secconstring,String secdbusername,String secdbpassword)
				{
					String response = null;
					try {
						int analysisorrecom = (int) Double.parseDouble(value);
						String conditiontype = (String) Dashboard.getpersonalcreditBApoints(email, constring,dbusername,dbpassword).get(3);
						response = (Dashboard.getanalysisandrecommendationtext("Personal", conditiontype, secconstring,secdbusername,secdbpassword).get(analysisorrecom)).trim();
						if (response.equalsIgnoreCase(actualtext))
						{
							log.info("Personal Credit Score Analysis or recommendation has matched.");
							return "Pass";
						}
					} catch (ClassNotFoundException e) {
						
						return "Failed - Personal Credit Score analysis or recommendation has not matched" + e;
					}
		           return "Failed - analysis or recommendation text not same as Expected Text : " + response +" Actual text :" +actualtext;
			      }
				// Debt To Income 
				public static String verifydebttoincomepoint(String annualrevenue,String operatingexpense, int actualresult)
				{
					int expectedresult = (Integer) getdebttoincomeBApoints(annualrevenue, operatingexpense).get(0);
					if (expectedresult == actualresult)
			   	   	log.info("Debt To Income Point has been matched.");
			   		return "Pass";
			   	}
				public static String verifydebttoincometext(String annualrevenue,String operatingexpense, String actualtext)
				{
					String expectedtext = (String) getdebttoincomeBApoints(annualrevenue, operatingexpense).get(1);
					log.info("Expected Result:  " + expectedtext);
					log.info(expectedtext.contains(actualtext));
					boolean actualtextcont = expectedtext.contains(actualtext);
					if (actualtextcont == true)
					{
						return "Pass";
					}
					return "Failed - Debt To Income Status has not matched " + expectedtext+ "with " + actualtext;
				}
				public static String verifydebttoincomeanalysisandrecommtext(String annualrevenue, String operatingexpense,String value, String actualtext, String constring,String dbusername,String dbpassword)
				{
					String response = null,expectedtext,Actual;
					try {
						int analysisorrecom = (int) Double.parseDouble(value);
						String conditiontype = (String) Dashboard.getdebttoincomeBApoints(annualrevenue, operatingexpense).get(3);
						response = (Dashboard.getanalysisandrecommendationtext("debt", conditiontype, constring,dbusername,dbpassword).get(analysisorrecom)).trim();
						expectedtext = (response).trim();
						Actual = (actualtext).trim();
						if (expectedtext.equalsIgnoreCase(Actual))
						{
							log.info("Debt To Income Analysis or recommendation has matched.");
							return "Pass";
						}
					} catch (ClassNotFoundException e) {
						
						return "Failed - Debt To Income analysis or recommendation has not matched" + e;
					}
		           return "Failed - analysis or recommendation text not same as Expected Text :" + expectedtext +" Actual text :" +Actual;
			      }
			// Verify BA Score 
				public static String verifybascore(int actual,String useremail, String uiageofbusiness, String annualrevenue, String operatingexpense, String industryname, String subindustryname,String sconstring, String susername,String spassword, String constring, String username, String password)
				{
					int expectedtext = (int) calculateBAScore(useremail, uiageofbusiness, annualrevenue, operatingexpense, industryname, subindustryname,sconstring, susername,spassword,constring,username,password);
					if (expectedtext == actual)
					{
						log.info("BA Score has been matched.");
						return "Pass";
					}
					return "Failed - bascore has not matched " + expectedtext+ " with " + actual;
				}
			//Status to databasename
				public static List<String> getanalysisandrecommendationtext( String parameter, String conditiontype, String constring, String username, String password) throws ClassNotFoundException
				{
					List<String> data = new ArrayList<String>();
					String query = "SELECT analysis, recommendation FROM jos_bizanalyzertool_data where rectype LIKE \"%"+parameter +"%\" AND conditiontype LIKE \"%"+conditiontype +"%\" AND patner = \"biz2credit\"";
					
					try {
						String analysisd = DataBase.getData(query, "analysis",constring, username,password).toString();
						data.add(analysisd);
						String[] recommendation = DataBase.getData(query, "recommendation",constring, username,password).toString().split("</strong>");
						String[] recommendationsec = recommendation[0].split("<strong>");
						String recommendationtext =recommendationsec[1].replaceAll("\\s+", " ").trim() + " " +recommendation[1].replaceAll("\\s+", " ").trim();
						data.add(recommendationtext);
						} catch (SQLException e) {
							log.info( "SQL data has not been fetched for analysisd variable");
						e.printStackTrace();
					}
					
					return data;
				}
				
		// Color text to hexcode
			public static String gethexcolorcode(String color)
			{
				String data = null;
				log.info("color is " + color);
				if (color == "red")
				{	data = "#eb1d25";
				} else if (color == "yellow")
				{
					data = "#fdae33";
				}else
				{ data = "#53b848";
				}
				log.info("color data is : " + data);
				return data;
			}
			public static String verifycolortohex(String color, String convertcolortohex)
			{
				String expectedtext = gethexcolorcode(convertcolortohex);
				String actual=  Color.fromString(color).asHex();
				log.info("Expected Result:  " + expectedtext);
				log.info(expectedtext.contains(actual));
				boolean actualtextcont = expectedtext.contains(actual);
				if (actualtextcont == true)
				{
					return "Pass";
				}
				return "Failed - Color has not matched" + expectedtext+ "with " + actual;
					
			}
	}
	
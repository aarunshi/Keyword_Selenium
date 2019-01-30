package com.b2c.ReportUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.b2c.Helper.Logger_Helper.LoggerHelper;
import com.b2c.Utils.*;


public class ReportUtil {
	// Variable Declaration
	//public static ArrayList<String> Loc = new ArrayList<String>();
	public static int scriptNumber = 1;
	public static String indexResultFilename;
	public static String currentDir;
	public static String currentSuiteName;
	public static int tcid;
	public static double passNumber;
	public static double failNumber;
	public static boolean newTest = true;
	public static ArrayList<String> description = new ArrayList<String>();
	public static ArrayList<String> keyword = new ArrayList<String>();
	public static ArrayList<String> teststatus = new ArrayList<String>();
	public static ArrayList<String> screenShotPath = new ArrayList<String>();
	public static final Logger log = LoggerHelper.getLogger(ReportUtil.class.getName());
	public static String ID1;
	public static String ID2;
	//public static int m=0;
	

	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Generates HTML Report Upper Left Table  
	 */
	public static void startTesting(String filename, String testStartTime, String env, String rel, String Bank) 
	{
		indexResultFilename = filename;
		FileWriter fstream = null;
		BufferedWriter out = null;
		
		log.info("Report File Name is :"+ indexResultFilename);
		currentDir = indexResultFilename.substring(0, indexResultFilename.lastIndexOf("//"));

		
		try {
			// Create file

			fstream = new FileWriter(filename);
			out = new BufferedWriter(fstream);
			String RUN_DATE = TestUtils.now("dd.MMMMM.yyyy").toString();
			String ENVIRONMENT = env;
			String RELEASE = rel;

			out.newLine();
			out.write("<html>\n");
			out.write("<HEAD>\n");
			out.write(" <TITLE>Automation Test Results</TITLE>\n");
			out.write("</HEAD>\n");
			out.write("<body>\n");
			out.write("<script type=text/javascript>\r\n" + "function toggle_visibility(tbid,lnkid)\r\n" + "{\r\n"
					+ "if(document.all){document.getElementById(tbid).style.display = document.getElementById(tbid).style.display == \"none\" ? \"block\" :\"none\";}\r\n"
					+ "else{document.getElementById(tbid).style.display = document.getElementById(tbid).style.display == \"none\" ? \"table\" : \"none\";}\r\n"
					+ "document.getElementById(lnkid).value = document.getElementById(lnkid).value == \"[+]\"  ? \"[-]\"  : \"[+]\" ;\r\n"
					+ "}\r\n" + "</script>");
			out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
			out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
			out.write("<tr>\n");
			out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Details :" + Bank + "</u></h4>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
			out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>" + RUN_DATE + "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");
			out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>" + testStartTime+ "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>" + ENVIRONMENT+ "</b></td>\n");
			out.write("</tr>\n");
			out.write("<tr>\n");
			out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
			out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>" + RELEASE+ "</b></td>\n");
			out.write("</tr>\n");
			out.write("</table>\n");
			out.close();
		} 
		catch (Exception e) 
		{// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} 
		finally {
			fstream = null;
			out = null;
		}
	}

	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Generates HTML Report Test Scenario Name Table with Toggle Button and Collapsed Test Case Tables  
	 */
	public static void StartScenario(String Senario) {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			String ID1 = String.valueOf(System.currentTimeMillis());
			Thread.sleep(1000);//To make 2 different timestamps
			String ID2 = String.valueOf(System.currentTimeMillis());
			out.write("<h4> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> <u>" + Senario + "  :</u>");
			out.write("<style type=text/css>\r\n" + "#" + ID1 + "{display:none;}\r\n" + "#" + ID2
					+ "{border:none;background:none;width:85px;}\r\n" + "td {FONT-SIZE: 75%; MARGIN: 0px; }\r\n"
					+ "td {FONT-FAMILY: verdana,helvetica,arial,sans-serif}\r\n" + "a {TEXT-DECORATION: none;}\r\n"
					+ "</style>\r\n" + "   <td bgcolor=#E0FFFF align=right><input id=" + ID2
					+ " type=button value=[+]  onclick=toggle_visibility('" + ID1 + "','" + ID2 + "');></td></h4><tr>");
			out.write("<table  style=\"display:none\" border=1 cellspacing=1 cellpadding=1 width=100%" + " id=" + ID1+ ">\n");
			out.write("<tr>\n");
			out.write("</tr>\n");
			out.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		} 
		finally 
		{
			fstream = null;
			out = null;
		}
	}

	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Generates HTML Report Test Case Table  
	 */
	public static void startSuite(String suiteName) {

		FileWriter fstream = null;
		BufferedWriter out = null;
		currentSuiteName = suiteName.replaceAll(" ", "_");
		tcid = 1;
		try {
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("<th> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> <u>" + suiteName + "  :</u></th>\n");
			out.write("<tr>\n");
			out.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script#</b></td>\n");
			out.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Name</b></td>\n");
			out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
			out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></td>\n");
			out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>\n");
			out.write("</tr>\n");
			out.close();
		} 
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		} 
		finally {
			fstream = null;
			out = null;
		}
	}
	
	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : End HTML Report Test Case Table  
	 */
	public static void endSuite() {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			out.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		}
		finally
		{
			fstream = null;
			out = null;
		}

	}
	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : End HTML Report Test Scenario Table  
	 */
	public static void endScenario() {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("</table>\n");
			out.close();
		} 
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		} 
		finally
		{
			fstream = null;
			out = null;
		}

	}

	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Generates HTML Report Test Step Table  in new HTML file and also update corresponding Test Case Table with status in first file
	 */
	public static void addTestCase(String testCaseName, String testCaseStartTime, String testCaseEndTime,String status)
	{
		newTest = true;
		FileWriter fstream = null;
		BufferedWriter out = null;
		

		try {
			newTest = true;
			// build the keywords page
			if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
			{
				
			}
			else 
			{
				String Location = currentDir + "//" + currentSuiteName  + "_"+ testCaseName.replaceAll(" ", "_") + "_TC" + tcid+ ".html";
//				System.out.println("loc is" + Location);
//				Loc.add(Location);
//				System.out.println("m is"+m);
//				System.out.println("loc array is" + Loc.get(m));
//				m=m+1;
				File f = new File(currentDir + "//" + currentSuiteName  + "_"+ testCaseName.replaceAll(" ", "_") + "_TC" + tcid+ ".html");
				f.createNewFile();
				fstream = new FileWriter(currentDir + "//" + currentSuiteName  + "_"+ testCaseName.replaceAll(" ", "_") + "_TC" + tcid+ ".html");
				out = new BufferedWriter(fstream);
				out.write("<html>");
				out.write("<head>");
				out.write("<title>");
				out.write(testCaseName + " Detailed Reports");
				out.write("</title>");
				out.write("</head>");
				out.write("<body>");
				out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> Detailed Report :</h4>");
				out.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
				out.write("<tr>");
				out.write("<td align=center width=10%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Step/Row#</b></td>");
				out.write("<td align=center width=50% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Description</b></td>");
				out.write("<td align=center width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Keyword</b></td>");
				out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Result</b></td>");
				out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></td>");
				out.write("</tr>");
				if (description != null) {
					for (int i = 0; i < description.size(); i++) {
						out.write("<tr> ");
						out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>TS" + (i + 1)+ "</b></td>");
						out.write("<td align=center width=50%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+ description.get(i) + "</b></td>");
						out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+ keyword.get(i) + "</b></td>");
						if (teststatus.get(i).startsWith("Pass"))
							out.write("<td width=20% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+ teststatus.get(i) + "</b></td>\n");
						else if (teststatus.get(i).startsWith("Fail"))
							out.write("<td width=20% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+ teststatus.get(i) + "</b></td>\n");

						if (screenShotPath.get(i) != null)
//						{ String[] ArrScreenshotRelPath = (screenShotPath.get(i)).split("Reports");
//						String ScreenshotRelPath =ArrScreenshotRelPath[1];
							//here to change relative path
							out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href="+ "./Screenshots/"+screenShotPath.get(i) + " target=_blank>Screen Shot</a></b></td>");
						
							else
							out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>&nbsp;</b></td>");
							out.write("</tr>");
					}
				}

				out.close();

			}

			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);

			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			out.write("<tr>\n");
			out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + scriptNumber+ "</b></td>\n");
			if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
				out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseName+ "</b></td>\n");
			else 
				out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href=./" + currentSuiteName  + "_"+ testCaseName.replaceAll(" ", "_") + "_TC" + tcid+ ".html>" + testCaseName + "</a></b></td>\n");
			tcid++;
			if (status.startsWith("Pass"))
				out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+ status + "</b></td>\n");
			else if (status.startsWith("Fail"))
				out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+ status + "</b></td>\n");
			else if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
				out.write("<td width=10% align= center  bgcolor=yellow><FONT COLOR=153E7E FACE=Arial SIZE=2><b>"+ status + "</b></td>\n");
				out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseStartTime+ "</b></td>\n");
				out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseEndTime+ "</b></td>\n");
				out.write("</tr>\n");
				scriptNumber++;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		} 
		finally 
		{
			try
			{
				out.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		description = new ArrayList<String>();
		keyword = new ArrayList<String>();
		teststatus = new ArrayList<String>();
		screenShotPath = new ArrayList<String>();
		newTest = false;
	}
	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Update Test Step Status in 2nd HTML File
	 */
	public static void addKeyword(String desc, String key, String stat, String path) {

		description.add(desc);
		keyword.add(key);
		teststatus.add(stat);
		screenShotPath.add(path);
//		if (!(path==null))
//				{
//		//System.out.println("loc is" + path);
//		Loc.add(path);
//		System.out.println("m is"+m);
//		System.out.println("loc array is" + Loc.get(m));
//		m=m+1;
//		}

	}
	/* Author : Aarunshi
	 * Date Created : 1 Oct 2018
	 * Date Modified : 1 Nov 2018
	 * Description : Final Closure of Report with Time stamp
	 */
	public static void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try {
			
			FileInputStream fstream = new FileInputStream(indexResultFilename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Read File Line By Line

			while ((strLine = br.readLine()) != null) {

				if (strLine.indexOf("END_TIME") != -1) {
					strLine = strLine.replace("END_TIME", endTime);
				}
				buf.append(strLine);

			}
			// Close the input stream
			in.close();
			FileOutputStream fos = new FileOutputStream(indexResultFilename);
			DataOutputStream output = new DataOutputStream(fos);
			output.writeBytes(buf.toString());
			fos.close();

		}
		catch (Exception e)
		{// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

}

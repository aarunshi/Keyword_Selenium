package com.b2c.Test;

import java.io.File;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.b2c.Utils.Resources;

import com.b2c.ReportUtils.ReportUtil;

import com.b2c.Test.Keywords;

import com.b2c.Utils.TestUtils;

import com.b2c.Helper.Config_Reader_Helper.ObjectRepo;

import com.b2c.Helper.Config_Reader_Helper.PropertyFileReader;

import com.b2c.Helper.CustomerListerners_Helper.WebEventListner;

import com.b2c.Helper.Email_Helper.EmailHelper;

import com.b2c.Helper.Logger_Helper.LoggerHelper;

import com.b2c.Helper.Utility_Helper.DateTimeHelper;

import com.b2c.Helper.Utility_Helper.ResourceHelper;

public class TestController extends Resources

{

	// Class Variables

	String ReportNamefile;

	String ReportName;

	public WebDriver driver;

	String ReportFolderName;

	// Log 4J Logger Initialization

	public static final Logger log = LoggerHelper.getLogger(TestController.class.getName());

	/*
	 * Author : Aarunshi
	 * 
	 * Date Created : 1 Oct 2018
	 * 
	 * Date Modified : 1 Nov 2018
	 * 
	 * Description : Before Function to Call Initialization Function Resource Class
	 * for all Resources to get loaded at the beginning.
	 * 
	 */

	@BeforeClass

	public void initBrowser() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException

	{

		Initialize();

		driver = Resources.driver;

	}

	/*
	 * Author : Aarunshi
	 * 
	 * Date Created : 1 Oct 2018
	 * 
	 * Date Modified : 1 Nov 2018
	 * 
	 * Description : After Function to close all Open Browsers
	 * 
	 */

	@AfterClass

	public void quitBrowser() {

		// Resources.driver.quit();

		EmailHelper.EmailReport(Erecipient, Esender, Ehost, EPassword, Bank, ReportFolderName, ReportName);

	}

	/*
	 * Author : Aarunshi
	 * 
	 * Date Created : 1 Oct 2018
	 * 
	 * Date Modified : 1 Nov 2018
	 * 
	 * Description : Main Test Function of Test Controller to Loop through the Test
	 * Suite and Test Data
	 * 
	 */

	@Test

	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException

	{

		// Local Variable Declaration

		String TCStatus = "Pass";

		String TScStatus = "Pass";

		int TestCaseNumber;

		int TestStepNumber;

		int TestCaseStartPos;

		int TotalTestStepCount;

		int TestStepCount;

		int flag;

		int BrowserFlag = 0;

		int dataFlag = 1;

		String TSStatus;

		String env;

		String Rel;

		int Srows;

		int TSWriteFlag = 0;

		int TSReadFlag = 0;

		int OutPutFlag = 0;

		int MultipleTestDataFlag = 0;

		String time;

		String TestScenarioID;

		String TScRunMode;

		String NoOfIteration;

		String subStr[];

		int NOI;

		int Siteration;

		String TestCaseID;

		int TC;

		int TCCount;

		int TSc;

		int rows;

		int TestDataCount;

		int TestStependPosition;

		int TS;

		int TC_DBRow_val;

		// Start Reporting : Initialize Base Report Format File

		String startTime = com.b2c.Utils.TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa");

		time = DateTimeHelper.getCurrentDateTime();

		ReportFolderName = System.getProperty("user.dir") + "//src//main//java//com//b2c//Reports//Automation_Report_"
				+ time;

		new File(ReportFolderName).mkdir();

		String ScreenshotsFolderName = ReportFolderName + "//Screenshots";

		new File(ScreenshotsFolderName).mkdir();

		ReportName = "RegressionSuit_Report_" + Bank + "_" + time + ".html";

		ReportNamefile = ReportFolderName + "//RegressionSuit_Report_" + Bank + "_" + time + ".html";

		log.info("ReportNamefile is : " + ReportNamefile);

		File Fl = new File(ReportNamefile);

		Fl.createNewFile();

		env = ObjectRepo.reader.getEnvironment();

		Rel = ObjectRepo.reader.getRealse();

		ReportUtil.startTesting(ReportNamefile, startTime, env, Rel, Bank);

		// loop for Test Module To be run

		for (int TM = 2; TM <= SuiteData.getRowCount("TestModule"); TM++)

		{

			String TestModule = SuiteData.getCellData("TestModule", "TestModule", TM);

			String TMRunMode = SuiteData.getCellData("TestModule", "TMRunMode", TM);

			if (TMRunMode.equals("Y"))

			{

				log.info("Module to be run is : " + TestModule + "And Module Run Mode is :" + TMRunMode);

				// Loop through the Test Scneraio

				for (TSc = 2; TSc <= SuiteData.getRowCount("TestScenario"); TSc++)

				{

					TestScenarioID = SuiteData.getCellData("TestScenario", "TScID", TSc);

					TScRunMode = SuiteData.getCellData("TestScenario", "TScRunMode", TSc);

					NoOfIteration = SuiteData.getCellData("TestScenario", "NoOfIteration", TSc);

					subStr = NoOfIteration.split("\\.");

					NoOfIteration = subStr[0];

					NOI = Integer.valueOf(NoOfIteration);

					TScStatus = "Pass";

					if (TScRunMode.equals("Y")) // If run mode of test scenario is yes

					{

						ReportUtil.StartScenario(TestScenarioID);// Start Making Test Scenario Table in Report

						for (Siteration = 1; Siteration <= NOI; Siteration++)

						{

							ReportUtil.startSuite("TestCaseReport_Iteration_" + "" + Siteration);// Start Making Test
																									// Case Table with
																									// Scenario
																									// Iteration in
																									// Report

							if (BrowserFlag == 1) {

								log.info("closing browser");// Closing any Previous opened browser

								Resources.driver.quit();

								BrowserFlag = 0;

							}

							if (BrowserFlag == 0) {

								log.info("opening browser" + (BrowserFlag + 1));// Open a new browser for each scenario
																				// run

								lounchBrowser();

								BrowserFlag = 1;

							}

							TestCaseNumber = 0;

							for (TCCount = 2; TCCount <= SuiteData.getRowCount("TestCases"); TCCount++)// To Check no of
																										// Test Cases
																										// under one
																										// Test scenario

							{

								if (SuiteData.getCellData("TestCases", TestScenarioID, TCCount).equals(""))// Test Cases
																											// Finished
																											// in this
																											// scenario

								{

									TestCaseNumber = TestCaseNumber + 0;

									log.info("finish test cases at row number :" + TCCount);

									break;

								}

								else

								{

									TestCaseNumber = TestCaseNumber + 1;

								}

							}

							log.info("No of test cases in this scenario are :" + TestCaseNumber);

							// loop through the test cases

							String TC_DBRow = TestScenarioID + "_DBRow";

							for (TC = 2; TC < 2 + TestCaseNumber; TC++) {

								TestCaseID = SuiteData.getCellData("TestCases", TestScenarioID, TC);

								TC_DBRow_value = SuiteData.getCellData("TestCases", TC_DBRow, TC);

								// System.out.println("h"+TC_DBRow_value);

								TCStatus = "Pass";

								// If run mode of test case is yes

								log.info("TestCaseID is: " + TestCaseID + " Test case starts with row no " + 2
										+ " and goes till row " + ((2 + TestCaseNumber) - 1));

								TotalTestStepCount = SuiteData.getRowCount("TestSteps");

								log.info("TotalTestStepCount are  : " + TotalTestStepCount);

								TestStepCount = 0;

								flag = 0;

								TestCaseStartPos = 2;

								// find the test step count under this test case

								for (int TS2 = 2; TS2 <= TotalTestStepCount; TS2++)

								{

									if ((SuiteData.getCellData("TestSteps", "TCID", TS2)).equals(TestCaseID))

									{

										log.info("found test case match");

										if (flag == 0)// To set the start position of Test Case

										{

											TestCaseStartPos = TS2;

											log.info("TestCaseStartPos is " + TestCaseStartPos);

											flag = 1;

										}

										TestStepCount = TestStepCount + 1;

										// If run mode of Test step is yes

									}

								}

								TestCaseID = SuiteData.getCellData("TestSteps", "TCID", TestCaseStartPos);

								log.info("testcaseid in teststep sheet:" + TestCaseID);

								String RunMode = SuiteData.getCellData("TestSteps", "RunMode", TestCaseStartPos);

								log.info("Test steps number under this test case are: " + TestStepCount);

								log.info("Test case starts from row: " + TestCaseStartPos + " and ends at row :"
										+ (TestCaseStartPos + TestStepCount - 1));

								TSStatus = "Pass";// Set the base status

								// ***********

								rows = TestStepData.getRowCount(TestCaseID);

								if (rows <= 2) {// To check number of rows in Test data sheet of a Test Case

									rows = 1;

									dataFlag = 0;// If only one row which is for column name then no data so data flag
													// is 0

								}

								TestDataCount = 2 + rows;

								if (RunMode.equals("Y") & TMRunMode.equals("Y") & TScRunMode.equals("Y")) {// Check if
																											// Run mode
																											// for
																											// Module &
																											// Scenario
																											// and Case
																											// are Yes

									log.info("Test Case is" + TestCaseID + "with run mode : " + RunMode);

									log.info("All run modes yes");

									int TD = Siteration + 1;// make the test data row number equal to the number of
															// iteration to be run for a scenario

									int OldTD = TD;

									TestStependPosition = TestCaseStartPos + TestStepCount;// Test Step end row number

									// loop start for test steps

									for (TS = TestCaseStartPos; TS < TestStependPosition; TS++) {

										if (SuiteData.getCellData("TestSteps", "TSRunMode", TS).equals("Y")) // Check
																												// run
																												// mode
																												// of a
																												// Test
																												// step

										{

											// Getting all the parameters from Test Suite Sheet

											MultipleTestDataFlag = 0;

											TSWriteFlag = 0;

											TSReadFlag = 0;

											OutPutFlag = 0;

											TSStatus = "Pass";

											arrTestData = new ArrayList();

											keyword = SuiteData.getCellData("TestSteps", "Keyword", TS);

											webElement = SuiteData.getCellData("TestSteps", "WebElement", TS);

											ProceedOnFail = SuiteData.getCellData("TestSteps", "ProceedOnFail", TS);

											TSID = SuiteData.getCellData("TestSteps", "TSID", TS);

											Description = SuiteData.getCellData("TestSteps", "Description", TS);

											TestDataField = SuiteData.getCellData("TestSteps", "TestDataField", TS);

											log.info("test step: " + TSID + " start position  is :" + TS);

											/// ********** Check for TestDataField column in Test Suite is blank or to
											/// be written to scenario sheet or to be read from scenario sheet
											/// starts**************
											OldTD = TD;
											if (!(TC_DBRow_value.isEmpty()))

											{

												subStr = TC_DBRow_value.split("\\.");

												TC_DBRow_value = subStr[0];

												System.out.println("TC_DBRow_value : " + TC_DBRow_value);

												TC_DBRow_val = Integer.valueOf(TC_DBRow_value);

												System.out.println("TC_DBRow_val : " + TC_DBRow_val);

												OldTD = TD;

												TD = TC_DBRow_val;

											}

											if (TestDataField.equals("")) // Blank column

											{

												log.info("No Test data feild in test suite");

											}

											else

											{

												String TestDataFeildArr[] = TestDataField.split("\\|");// check for
																										// multiple data
																										// feild sources

												int TestDataFeildArrLen = TestDataFeildArr.length;

												if (TestDataFeildArrLen > 1)

												{

													MultipleTestDataFlag = 1;

													log.info("Multiple data to read");

												}

												for (int m = 0, n = 0; n < TestDataFeildArrLen; n++)// for loop of Test
																									// Data Field
																									// reading starts

												{

													TestDataField = TestDataFeildArr[n];

													log.info((n + 1) + " TestDataFeild is : " + TestDataField);

													if (TestDataField.contains("TSW:")) /// non Blank & Check for
																						/// TestDataField column in Test
																						/// Suite is to be written in
																						/// scenario sheet or not

													{

														String subStr2[] = TestDataField.split(":", 2);

														TestDataField = subStr2[1];

														TSWriteFlag = 1;

														log.info(
																"TestDataField is to be written on test scenario sheet :  "
																		+ TestDataField);

													}

													else if (TestDataField.contains("TSR:")) /// Non Blank & Not to be
																								/// written on scenario
																								/// sheet & Check for
																								/// TestDataField column
																								/// in Test Suite is to
																								/// be read from
																								/// scenario sheet or
																								/// not

													{

														String subStr2[] = TestDataField.split(":", 2);

														TestDataField = subStr2[1];

														TSReadFlag = 1;

														log.info(
																"TestDataField is to be read from Test scenario sheet :  "
																		+ TestDataField);

													}

													else if (TestDataField.contains("TSRO:")) /// Non Blank & Not to be
																								/// written on scenario
																								/// sheet & Check for
																								/// TestDataField column
																								/// in Test Suite is to
																								/// be read from
																								/// scenario sheet or
																								/// not

													{

														String subStr2[] = TestDataField.split(":", 2);

														TestDataField = subStr2[1];

														TSReadFlag = 1;

														OutPutFlag = 1;

														log.info(
																"TestDataField is to be read from Test scenario sheet :  "
																		+ TestDataField);

														log.info("Output to be written on Test Scenario sheet");

													}

													else if (TestDataField.contains("TSWO:")) /// Non Blank & to be
																								/// written on scenario
																								/// sheet & Check for
																								/// TestDataField column
																								/// in Test Suite is to
																								/// be read from
																								/// scenario sheet or
																								/// not

													{

														String subStr2[] = TestDataField.split(":", 2);

														TestDataField = subStr2[1];

														TSWriteFlag = 1;

														OutPutFlag = 1;

														log.info(
																"TestDataField is to be read from Test scenario sheet :  "
																		+ TestDataField);

														log.info("Output to be written on Test Scenario sheet");

													}

													else {

														log.info("TestDataField is " + TestDataField); // Non Blank &
																										// Not to be
																										// written on
																										// scenario
																										// sheet & not
																										// be read from
																										// scenario
																										// sheet ,
																										// Simply read
																										// from Case
																										// sheet

													}

													/// ********** Check for TestDataField column in Test Suite is blank
													/// or to be written to scenario sheet or to be read from scenario
													/// sheet ends **************

													/// ********** Reading and Writing from Test Data suite sheet : Test
													/// case sheet or test scenario sheet
													/// starts****************************

													rows = TestStepData.getRowCount(TestCaseID); // no of rown in test
																									// data sheet

													Srows = TestStepData.getRowCount(TestScenarioID); // no of rows in
																										// test sceanrio
																										// sheet

													if (rows > 1 && TSReadFlag == 0)

													{// data exists and to be read from Test Case data sheet

														String strTestDataFieldArray[] = TestDataField.split(",");// To
																													// check
																													// if
																													// multiple
																													// data
																													// is
																													// to
																													// be
																													// read
																													// i.e
																													// comma
																													// separated

														int TestDataFieldArrayLen = strTestDataFieldArray.length;

														log.info("len is" + TestDataFieldArrayLen);

														if (TestDataFieldArrayLen > 1 || MultipleTestDataFlag == 1)

														{// Multiple data

															for (int s = 0; s < TestDataFieldArrayLen; m++, s++)

															{

																log.info("strTestDataFieldArray[m] is :"
																		+ strTestDataFieldArray[s]);

																TestData = TestStepData.getCellData(TestCaseID,
																		strTestDataFieldArray[s], TD);

																if (TestData.equals("email"))

																{

																	TestData = "test" + System.currentTimeMillis()
																			+ "@b2cdev.com";

																}

																arrTestData.add(TestData);

																log.info(
																		"TestDataFieldArraydata to be read from Test Case sheet is "
																				+ strTestDataFieldArray[s]
																				+ " and TestData is "
																				+ arrTestData.get(m));

																// Write the same data in Test Scenario sheet of current
																// running scenario

																if (TSWriteFlag == 1)

																{

																	log.info(
																			"data that has been writen in scenario sheet is :"
																					+ TestStepData.setCellData(
																							TestScenarioID,
																							strTestDataFieldArray[s],
																							OldTD, arrTestData.get(m))///////////////////
																					+ " in Scenario sheet :"
																					+ TestScenarioID);

																}

															}

														}

														else // Single data

														{

															TestData = TestStepData.getCellData(TestCaseID,
																	TestDataField, TD);

															log.info(
																	"Data to be read from Test Case sheet for data feild : "
																			+ TestDataField + " at row no: " + TD
																			+ " with TestData : " + TestData);

															if (TestData.equals(""))

															{

																log.info("data is blank");

																TestData = "blank";

															}

															else if (TestData.equals("email"))

															{

																TestData = "test" + System.currentTimeMillis()
																		+ "@b2cdev.com";

															}

															// Write the same data in Test Scenario sheet of current
															// running scenario

															if (TSWriteFlag == 1)

															{

																log.info(
																		"data that has been writen in scenario sheet is :"
																				+ TestStepData.setCellData(
																						TestScenarioID, TestDataField,
																						OldTD, TestData)/////////
																				+ " in Scenario sheet :"
																				+ TestScenarioID);

																TSWriteFlag = 0;

															}

														}

													}

													// ********************Reading from Test Scenario sheet
													// starts********************

													else if (Srows > 1 && TSReadFlag == 1)

													{

														String strTestDataFieldArray[] = TestDataField.split(",");// To
																													// check
																													// if
																													// multiple
																													// data
																													// is
																													// to
																													// be
																													// read
																													// i.e
																													// comma
																													// separated

														int TestDataFieldArrayLen = strTestDataFieldArray.length;

														log.info("len is" + TestDataFieldArrayLen);

														if (TestDataFieldArrayLen > 1 || MultipleTestDataFlag == 1)

														{

															for (int s = 0; s < TestDataFieldArrayLen; m++, s++)

															{

																log.info("strTestDataFieldArray[m] is :"
																		+ strTestDataFieldArray[s]);

																TestData = TestStepData.getCellData(TestScenarioID,
																		strTestDataFieldArray[s], OldTD);////////////////////////

																if (TestData.equals("email"))

																{

																	TestData = "test" + System.currentTimeMillis()
																			+ "@b2cdev.com";

																}

																arrTestData.add(TestData);

																log.info(
																		"TestDataFieldArraydata to be read from scenario sheet is "
																				+ strTestDataFieldArray[s]
																				+ " and TestData is "
																				+ arrTestData.get(m));

															}

														}

														else

														{

															TestData = TestStepData.getCellData(TestScenarioID,
																	TestDataField, OldTD);///////////

															if (TestData.equals("email"))

															{

																TestData = "test" + System.currentTimeMillis()
																		+ "@b2cdev.com";

															}

															if (TestData.equals(""))

															{

																log.info("data is blank");

																TestData = "blank";

															}

															else

															{

																log.info(
																		"data exist for this step has been read from Test Scenario sheet");

																log.info("TestDataField is : " + TestDataField
																		+ "and TestData is : " + TestData);

															}

														}

														TSReadFlag = 0;

													}

													// ********************Reading from Test Scenario sheet
													// ends********************

													else {

														log.info("data is blank");

														TestData = "blank";

													}

													if (TestData.equals("email")) {

														TestData = "test" + System.currentTimeMillis() + "@b2cdev.com";

													}

												}

											} // for loop of Test Data Feild reading ends

											if (MultipleTestDataFlag == 1)

											{

												log.info("keyword to call : " + keyword + " webelemt is :" + webElement
														+ " TSID is:" + TSID + " TestDataArray is :" + arrTestData);

											}

											else

											{

												log.info("keyword to call : " + keyword + " webelemt is :" + webElement
														+ " TSID is:" + TSID + " TestData is :" + TestData);

											}

											Method method = Keywords.class.getMethod(keyword);// Call Keyword function
																								// from keyword class

											TSStatus = (String) method.invoke(method);// Get status

											if (OutPutFlag == 1)

											{

												if (!(TSStatus.equals(null)))

												{

													log.info("Output that has been writen in scenario sheet : "
															+ TestScenarioID + " in Output feild is :" + TSStatus + "--"
															+ TestStepData.setCellData(TestScenarioID, "Output", OldTD,/////////////////////////////
																	TSStatus));

													TSStatus = "Pass";

													OutPutFlag = 0;

												}

											}

											if (TSStatus.contains("Failed")) {

												// take the screenshot

												String filename = TestCaseID + "_" + TSID + "_"
														+ System.currentTimeMillis();

												// String currentDir = ReportName.substring(0,
												// ReportName.lastIndexOf("//"));

												// String ScreenShotLoc = currentDir+"//" + "Screenshots" ;

												TestUtils.getScreenShot(ScreenshotsFolderName + "/" + filename);

												TCStatus = TSStatus;

												TScStatus = TCStatus;

												// report error for test step

												log.info("TestCase is :" + TestCaseID + "Test Step is:" + TSID
														+ "Failed step Status is :" + TSStatus);

												ReportUtil.addKeyword(Description, keyword, TSStatus,
														filename + ".jpg");

												if (ProceedOnFail.equals("N")) {

													break;

												}

											}

											else {// report status of Test Step

												ReportUtil.addKeyword(Description, keyword, TSStatus, null);

											}

										} // End of If Condition of Test Step if its run mode is Y

									} // loop finish of test steps under a test case ends here

									log.info("TestCase is :" + TestCaseID + "Test Step is:" + TSID + "Status is :"
											+ TCStatus);

									ReportUtil.addTestCase(TestCaseID, startTime,
											TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"), TCStatus);// Add Test Case
																									// Report status

									if (!(TC_DBRow_value.isEmpty())) {

										TD = OldTD;
									}

								} // If Condition for All the run mode as Y

								else {

									// skip the test case

									log.info("skipper id is :" + TestCaseID);

									ReportUtil.addTestCase(TestCaseID, startTime,
											TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped");

								}

							} // loop of test cases under a selected scenario ends here

							ReportUtil.endSuite(); // End the Test Case Report Table

						} // loop of selected test scenario iteration count ends here

					} // If Condition for Test Scenario run mode as Y

					ReportUtil.endScenario();// End the Test Scenario Report Table

				} // Loop of all Test scenario ends here

			} // If condition for Test Module run Mode as Y

		} // Loop of all Test Module ends here

		ReportUtil.endSuite();// End the Test Case Report Table

		ReportUtil.updateEndTime(TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"));// Update end time in report

	}// Test Controller ends here

}
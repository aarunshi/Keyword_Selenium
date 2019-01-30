//package com.b2c.ReportUtils;
//
//import com.b2c.ReportUtils.ReportUtil;
//import com.b2c.Test.*;
//import com.b2c.Utils.TestUtils;
//
//
//public class ReportTest {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String startTime = TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa");
//		ReportUtil.startTesting(System.getProperty("user.dir")+"//src//main//java//com//b2c//Reports//index.html", startTime, "Test", "1.5","Bank");
//
//		ReportUtil.startSuite("Suite1");
//
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
//
//		ReportUtil.addTestCase("TopNavigation", startTime, TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Pass");
//
//		ReportUtil.endSuite();
//		ReportUtil.updateEndTime(TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"));
//
//	}
//}

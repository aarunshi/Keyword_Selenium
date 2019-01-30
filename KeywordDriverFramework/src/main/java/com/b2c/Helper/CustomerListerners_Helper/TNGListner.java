//package  com.b2c.Helper.CustomerListerners_Helper;
//
//import java.io.IOException;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.annotations.BeforeClass;
//
//import com.b2c.*;
//import com.b2c.Test.*;
//
////import com.b2c.Test.TestController;
//
//public class TNGListner extends TestController implements ITestListener{
//	
//	
//	public  ScreenshotCapture SC; 
//	String screen;
//	
//	public void onTestStart(ITestResult result) {
//		Reporter.log("test started-->"+ result.getMethod());
//		
//	}
//
//	
//	public void onTestSuccess(ITestResult result) {
//		
//		return;
//	}
//
//
//	public void onTestFailure(ITestResult result) {
//		if (result.getStatus()==ITestResult.FAILURE)
//		 {
//			try {
//				SC =new ScreenshotCapture(driver);
//				 screen =  SC.getScreenShot(result.getName());
//			
//		 
//		//Reporter.log("<a href='"+screen+"'><img src='"+screen+"' height='100' width='100'/></a> ");
//		Reporter.log("<a href=\""+screen+"\"><img src=\"file;///"+screen+"\" alt=\"\""+"height='100'/>"+"<br/>");
//		Reporter.setCurrentTestResult(null);
//		Reporter.log(result.getName()+ " Test method failed");
//		
//			}catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		 }
//		
//		
//	}
//
//	
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void onStart(ITestContext context) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void onFinish(ITestContext context) {
//		Reporter.log("testPassed"+context.getPassedTests());
//		Reporter.log("test failed"+context.getFailedTests());
//	}
//	
//	
//
//}

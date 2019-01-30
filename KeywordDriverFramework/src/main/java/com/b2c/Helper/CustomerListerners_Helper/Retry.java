package  com.b2c.Helper.CustomerListerners_Helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	private int retryCount=0;
	private int maxRetryCount =3;
	public boolean retry(ITestResult result) {
		
		if (retryCount<maxRetryCount)
		{	
			
			System.out.println(" RetryingTest :"+ result.getName()+"with status"+result.getStatus()+" for retry count : "+retryCount);
			retryCount++;
			return true;}
		return false;
	}
	
}


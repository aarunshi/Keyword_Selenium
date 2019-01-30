package  com.b2c.Helper.CustomerListerners_Helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListner implements IAnnotationTransformer
{

	public void transform(ITestAnnotation Arg1, Class testClass,
			Constructor testConstructor, Method testMethod) {
		
		IRetryAnalyzer retry = Arg1.getRetryAnalyzer();
	if(retry==null)
	{
		Arg1.setRetryAnalyzer(Retry.class);
	}
	}

}

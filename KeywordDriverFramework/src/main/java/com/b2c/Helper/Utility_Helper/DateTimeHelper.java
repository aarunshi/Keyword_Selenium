package  com.b2c.Helper.Utility_Helper;

import java.text.SimpleDateFormat;
//to get date and time
//to get date only
import java.util.Calendar;

public class DateTimeHelper {
	
	public static String getCurrentDateTime()
	{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		String DateTime = formater.format(calender.getTime());
		return DateTime;
	}

	public static String getCurrentDate()
	{
		return(getCurrentDateTime().substring(0,10));
	}
//	public static void main(String[] args)
//	{
//		System.out.println(getCurrentDateTime());
//		System.out.println(getCurrentDate());
//	}
}

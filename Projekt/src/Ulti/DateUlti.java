package Ulti;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Thanh Tung Trinh 1320718<br>
 * tung.tt133@gmail.com
 * responsible for date manipulation
 */
public class DateUlti {
	
	
	/**
	 * @param text is the input Date
	 * @return true if the date is later than today, sooner than 2022-01-01 and is correctly written  
	 * @throws ParseException for Date values
	 */
	public static boolean dateValid(String text) throws ParseException
	{
		String sdate4 = "2022-01-01";
		boolean check = false;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 

		//check format first;
		if (text.equalsIgnoreCase("") || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
	        return false;
		else
		{
			//check date then
			java.util.Date date4 = formatter.parse(sdate4);
			java.util.Date date1 = formatter.parse(text);
			if ( ( date1.compareTo(today()) > 0) && ( date1.compareTo(date4) < 0) )
					check = true;
			else
					check = false;
	       }	
		return check;
	}
	
	
	/**
	 * 
	 * @param date is the date input
	 * @return an integer converted from input date
	 */
	public static int ConvertDateToInt(String date)
	{
		int answer = 0;
		int day = 0;
		int month = 0;
		int year = 0;
		
	    String[] values = date.split("-");
	    day = Integer.parseInt(values[2]);	
	    month = Integer.parseInt(values[1]);
	    year = Integer.parseInt(values[0]);
		
		answer = year*10000 + month*100 + day;
		return answer;
	}
	
	/**
	 * @return today as year month day
	 */
	public static Date today()
	{
		Date today = new Date();
		return today;
	}
	
	public static int thismonth()
	{
		java.util.Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(Calendar.MONTH)+1);
	}
	
}



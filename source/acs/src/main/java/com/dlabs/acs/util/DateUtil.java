package com.dlabs.acs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil
{
	
	public static final String DEFAULT_DATE_FMT = "dd-MM-yyyy";
	public static final String DEFAULT_TIME_FMT = "HH:mm";

	public static final String DEFAULT_DATE_TIME_FMT = "dd-MM-yyyy HH:mm";

	public static Date stringToDate(String strFormat)
	{
		if (strFormat == null)
		{
			return null;
		}
		DateFormat myDateFormat = new SimpleDateFormat(DEFAULT_DATE_FMT);
		Date myDate = null;
		try
		{
			myDate = myDateFormat.parse(strFormat);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
		return myDate;
	}

	public static Date stringToDateByFmt(String strFormat, String format)
	{
		if (strFormat == null)
		{
			return null;
		}
		DateFormat myDateFormat = new SimpleDateFormat(format);
		Date myDate = null;
		try
		{
			myDate = myDateFormat.parse(strFormat);
		}
		catch (ParseException e)
		{
			e.getMessage();
		}
		return myDate;
	}

	public static Date stringToDateTime(String strFormat)
	{
		return stringToDateByFmt(strFormat, DEFAULT_DATE_TIME_FMT);
	}
	
	public static String dateTimeToString(Date date)
	{
		return dateToStr(date, DEFAULT_DATE_TIME_FMT);
	}

	public static String dateToStr(Date date, String format)
	{
		if (format == null || date == null )
		{
			return null;
		}
		DateFormat myDateFormat = new SimpleDateFormat(format);
		return myDateFormat.format(date);
	}

	public static String dateToStr(Date date)
	{
		DateFormat myDateFormat = new SimpleDateFormat(DEFAULT_DATE_FMT);
		try
		{
			return myDateFormat.format(date);
		}catch(Exception e){
			return "";
		}
		
	}

	public static String dateToStr()
	{
		DateFormat myDateFormat = new SimpleDateFormat(DEFAULT_DATE_FMT);
		try
		{
			return myDateFormat.format(new Date());
		}catch(Exception e){
			return "";
		}
	}

	public static boolean isOverlap(Date startDate, Date endDate)
	{
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		startCalendar.set(Calendar.HOUR_OF_DAY, 0);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		endCalendar.set(Calendar.HOUR_OF_DAY, 0);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		return startCalendar.after(endCalendar);
	}

	public static boolean isPastDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		todayCalendar.set(Calendar.MINUTE, 0);
		todayCalendar.set(Calendar.SECOND, 0);
		todayCalendar.set(Calendar.MILLISECOND, 0);

		return calendar.before(todayCalendar);
	}
	
	
	public static boolean isPastDateTime(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

	    calendar.set(Calendar.MILLISECOND, 0);

		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.set(Calendar.MILLISECOND, 0);

		return calendar.before(todayCalendar);
	}
	
	public static boolean isWithinPeriodDate(Date startDate, Date endDate){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    
	    Calendar startCalendar = Calendar.getInstance();
	    startCalendar.setTime(startDate);
	    startCalendar.set(Calendar.HOUR_OF_DAY, 0);
	    startCalendar.set(Calendar.MINUTE, 0);
	    startCalendar.set(Calendar.SECOND, 0);
	    startCalendar.set(Calendar.MILLISECOND, 0);
	    
	    Calendar endCalendar = Calendar.getInstance();
	    endCalendar.setTime(endDate);
	    endCalendar.set(Calendar.HOUR_OF_DAY, 0);
	    endCalendar.set(Calendar.MINUTE, 0);
	    endCalendar.set(Calendar.SECOND, 0);
	    endCalendar.set(Calendar.MILLISECOND, 0);
	     
	    if(calendar.before(startCalendar) || calendar.after(endCalendar)){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	public static boolean isWithinPeriodDateTime(Date startDate, Date endDate){
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MILLISECOND, 0);
	    
	    Calendar startCalendar = Calendar.getInstance();
	    startCalendar.setTime(startDate);
	    startCalendar.set(Calendar.MILLISECOND, 0);
	    
	    Calendar endCalendar = Calendar.getInstance();
	    endCalendar.setTime(endDate);
	    endCalendar.set(Calendar.MILLISECOND, 0);
	     
	    if(calendar.before(startCalendar) || calendar.after(endCalendar)){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	public static int calculateRemainingTime(Date endTime){
		Date now = new Date();
		
		Long longTime =  (endTime.getTime() - now.getTime())/1000;
		int remainingTime =0;
		
		try{
			remainingTime = longTime.intValue();
	
		}catch(NumberFormatException ne){
			ne.printStackTrace();
		}
		
		if(remainingTime<0){
			return 0;
		}else{
			return remainingTime;
		}
		
	}

}

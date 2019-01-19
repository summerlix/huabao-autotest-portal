package com.king.service.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeUtils
{
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/**
	获取当前时间，精确到毫秒:HH:mm:ss.SSS
    */
	public static Date getMilliNowTime()                                         //获取本机当前的时间
	{
		Date date= new Date();                                                           
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Date resDate =null;
		try
		{
			resDate = sdf.parse(sdf.format(date));
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resDate;
	}
	
	
	/**
	*参数format为自定义格式
    */
	public static String getCurrentDate(String formatTime)
	{
		Date date=new Date();
		DateFormat format=new SimpleDateFormat(formatTime);
		String time=format.format(date);
		return time;
	}
	
	
	
	/**
	计算时间差,返回值格式为:X.XXXs,精确到毫秒
    */
	public  static String getTimeDiffMillisecond(Date begin,Date end)                                         //获取本机当前的时间
	{
		long between = 0;
        between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        String timeDiff = String.format("%.3f",(between/1000.000));
        
        return timeDiff;
    }
	
	/**
	*获取当前时间:yyyy-MM-dd HH:mm:ss
    */
	public static String getCurrentDate()
	{
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	
	/**
	*字符串转为日期类型           格式:yyyy-MM-dd HH:mm:ss
    */
	public static Date stringToDate(String timeStr) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date date = sdf.parse(timeStr);  
	    
		return date;
	}
	
	/**
	*日期转字符串        格式:yyyy-MM-dd HH:mm:ss
    */
	public static String  DateToString(Date date)
	{
		String dateStr="";
		if (date!=null)
		{
			dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		
		return dateStr;
	}
	
	
	/**
	*比较两个字符串日期的大小        格式:yyyy-MM-dd HH:mm:ss
    */
	public static int compare_date(String date1, String date2) 
	{ 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		java.util.Date d1 = df.parse(date1);
		java.util.Date d2 = df.parse(date2);
		if (d1.getTime() > d2.getTime())
		{
	
		return 1;
		}
		else if (d1.getTime() < d2.getTime()) 
		{
	
		return -1;
		} 
		else 
		{
		return 0;
		}
		} catch (Exception exception) {
		exception.printStackTrace();
		}
		return 0;
	}
	
	 /**  
     * 两个时间相差距离多少小时多少分多少秒  
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00  
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00  
     * @return String 返回值为：xx小时xx分xx秒  
     */    
	public static  String getDistanceTime(String str1, String str2) 
	{    
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
        Date one;    
        Date two;    
        long hour = 0;    
        long min = 0;    
        long sec = 0;    
        try {    
            one = df.parse(str1);    
            two = df.parse(str2);    
            long time1 = one.getTime();    
            long time2 = two.getTime();    
            long diff ;    
            if(time1<time2) {    
                diff = time2 - time1;    
            } else {    
                diff = time1 - time2;    
            }      
            hour = (diff / (60 * 60 * 1000));    
            min = ((diff / (60 * 1000)) - hour * 60);    
            sec = (diff/1000-hour*60*60-min*60);    
        } catch (ParseException e) {    
            e.printStackTrace();    
        }    
        return  hour + ":" + min + ":" + sec ;    
	 }  
	
	
	 /**  
     * 获取当前时间是星期几
     * @return String 返回值为：整型
     */ 
	public static int getNowWeek()
	{
		Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK)-1;
        return weekday;
	}
	
	/**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) 
    {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
	
}

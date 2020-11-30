package com.wuzhiaite.javaweb.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description 日期处理类
 *
 */
public class DateCaculatorUtil {


    /**
     *  计算间隔天数,具体到时分秒
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDay(Date start,Date end){
       return (int) ((end.getTime() - start.getTime()) / (1000*3600*24)) ;
    }

    /**
     * 计算间隔天数
     * @param start
     * @param end
     * @param format
     * @return
     * @throws ParseException
     */
    public static int getIntervalDay(Date start,Date end,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        start = sdf.parse(sdf.format(start));
        end = sdf.parse(sdf.format(end));
        return getIntervalDay(start,end);
    }

    /**
     * 日期增减天数
     * @param lastDate
     * @param dates
     * @return
     */
    public static Date getSubstractDate(Date lastDate , int dates){
        Calendar   calendar   =   new   GregorianCalendar();
        calendar.setTime(lastDate);
        calendar.add(Calendar.DATE,dates);
        return  calendar.getTime();
    }


}

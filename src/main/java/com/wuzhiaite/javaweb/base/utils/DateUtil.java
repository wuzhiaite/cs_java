package com.wuzhiaite.javaweb.base.utils;

import com.wuzhiaite.javaweb.base.enums.DateTypeEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * 日期格式化工具类
 */
public final class DateUtil {

    private volatile static SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * 获取当前时间
     * @param dateType
     * @return
     */
    public static String  getCurrentDate(DateTypeEnum dateType){
        sdf.applyPattern(dateType.format());
        return sdf.format(new Date());
    }

    /**
     * 默认格式获取当前时间
     * @return
     */
    public static String  getCurrentDate(){
        return getCurrentDate(DateTypeEnum.YMD);
    }

    /**
     * 格式化日期
     * @param date
     * @param type
     * @return
     */
    public static String formatDate(Date date , DateTypeEnum type){
        sdf.applyPattern(type.format());
        return sdf.format(date);
    }

    /**
     * 默认格式化日期
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        sdf.applyPattern(DateTypeEnum.YMD.format());
        return sdf.format(date);
    }

    /**
     * 字符串解析成日期格式
     * @param date
     * @param type
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date , DateTypeEnum type) throws ParseException {
        sdf.applyPattern(type.format());
        return sdf.parse(date);
    }

    /**
     * 默认字符串解析成日期格式
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        sdf.applyPattern(DateTypeEnum.YMD.format());
        return sdf.parse(date);
    }






    public static void main(String[] args) {
        String currentDate = DateUtil.getCurrentDate(DateTypeEnum.YMD);
        System.out.println(currentDate);

    }



}

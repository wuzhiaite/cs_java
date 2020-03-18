package com.wuzhiaite.javaweb.base.utils;

import com.wuzhiaite.javaweb.base.enums.DelimiterEnum;

/**
 * String通用判断
 */
public class StringUtil {

    /**
     * 判断空
     * @param obj
     * @return
     */
    public static boolean isBlank(String obj){
        return obj == null || "".equals(obj) ? true : false;
    }

    /**
     * 判断非空
     * @param obj
     * @return
     */
    public static boolean isNotBlank(String obj){
        return !isBlank(obj);
    }

    /**
     *
     * @param str
     * @param s
     * @param s1
     * @return
     */
    public static String replace(String str, String s, String s1) {
       return  str.replace(s,s1);
    }

    /**
     * 数组转字符串
     * @return
     */
    public static String convertArrayToString(String[] array, DelimiterEnum delimiter){
        if(array.length == 0){return "";}
        StringBuilder builder = new StringBuilder();
        for(String s : array){
            builder.append(s).append(",");
        }
        return builder.substring(0,builder.length()-2);
    }


}

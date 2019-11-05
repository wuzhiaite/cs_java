package com.wuzhiaite.javaweb.base.utils;

/**
 * String通用判断
 */
public class StringUtils {

    /**
     * 判断空
     * @param obj
     * @return
     */
    public static boolean isBlank(String obj){
        return obj == null || obj == "" ? true : false;
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
}

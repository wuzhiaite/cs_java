package com.wuzhiaite.javaweb.base.utils;

/**
 * SQL常用工具类
 */
public class SQLUtil {

    /**
     * 对拼接字符串增加单引号
     * @param str
     * @return
     */
    public static String decorateStr(String str){
        return  StringUtil.isBlank(str) ? "''" : "'"+str.trim()+"'";
    }




}

package com.wuzhiaite.javaweb.base.enums;

public enum DateTypeEnum {

    YMD("yyyy-MM-dd"),
    YMDHMS("yyy-MM-dd HH:mm:ss"),
    HMS("HH:mm:ss");

    private String format;
    DateTypeEnum(String format){
        this.format = format ;
    }
    public String format(){
        return format;
    }


}

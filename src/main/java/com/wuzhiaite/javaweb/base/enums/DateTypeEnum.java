package com.wuzhiaite.javaweb.base.enums;

public enum DateTypeEnum {
    YM("yyyy-MM"),
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

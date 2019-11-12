package com.wuzhiaite.javaweb.base.enums;

/**
 * 分隔符
 */
public enum DelimiterEnum {
    COMMA(","),
    STRINGULA("-"),
    OBLIQUE("/"),
    UDERLIN("");


    private String symble;
    DelimiterEnum(String symble){
        this.symble = symble ;
    }
    public String symble(){
        return symble;
    }


}

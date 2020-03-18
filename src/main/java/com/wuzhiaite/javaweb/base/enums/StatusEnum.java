package com.wuzhiaite.javaweb.base.enums;

/**
 * 返回状态状态码
 */
public enum StatusEnum {

    SUCCESS(1 ,"操作成功"),
    FAIL(-1 ,"操作失败");
    private Integer code;
    private String message;
    private StatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}

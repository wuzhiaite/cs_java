package com.wuzhiaite.javaweb.base.enums;

/**
 * 异常信息枚举
 */
public enum ExceptionEnum {

    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    NULL_POINT_EXCEPTION(5001, "空指针异常!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!");


    private Integer code;
    private String message;
    ExceptionEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }






}

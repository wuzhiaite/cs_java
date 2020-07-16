package com.wuzhiaite.javaweb.base.entity;

import com.wuzhiaite.javaweb.base.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

/**
 * 返回信息基本包装类
 */
@Data
public class ResultObj {

    private Object result;
    private String message;
    private Integer code;

    private ResultObj(Object result,String message,Integer code){
        this.result = result;
        this.message = message;
        this.code = code;
    }
    public static ResultObj successObj(){
        return successObj(null,StatusEnum.SUCCESS.getMessage());
    }
    public static ResultObj successObj(Object obj){
        return successObj(obj,StatusEnum.SUCCESS.getMessage());
    }
    public static ResultObj successObj(Object obj,String message){
        return new ResultObj(obj,message, StatusEnum.SUCCESS.getCode());
    }
    public static ResultObj failObj(){
        return failObj(StatusEnum.FAIL.getMessage());
    }
    public static ResultObj failObj(String message){
        return failObj(null,message);
    }
    public static ResultObj failObj(Object result,String message){
        return new ResultObj(result,message,StatusEnum.FAIL.getCode());
    }

}

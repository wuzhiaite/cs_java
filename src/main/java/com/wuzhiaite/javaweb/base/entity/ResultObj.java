package com.wuzhiaite.javaweb.base.entity;

import com.wuzhiaite.javaweb.base.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回信息基本包装类
 */
@ApiModel(value = "返回数据包装类",description = "返回属性包装类")
public class ResultObj {

    @ApiModelProperty(value="返回的数据",name="返回的数据",example = "{data:'666'}")
    private Object result;
    private String message;
    private Integer code;
    private ResultObj(){}
    private ResultObj(Object result,String message,Integer code){
        this.result = result;
        this.message = message;
        this.code = code;
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

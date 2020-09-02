package com.wuzhiaite.javaweb.base.exception;

/**
 * @description 异常信息处理接口
 */
public interface BaseExceptionInterface {

    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();

}

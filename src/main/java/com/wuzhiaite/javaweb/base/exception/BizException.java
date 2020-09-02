package com.wuzhiaite.javaweb.base.exception;

import com.wuzhiaite.javaweb.base.enums.StatusEnum;

/**
 * 自定义基础处理类
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(StatusEnum statusEnum) {
        this.errorCode = statusEnum.getCode();
        this.errorMsg = statusEnum.getMessage();
    }

    public BizException(StatusEnum statusEnum, Throwable cause) {
        this.errorCode = statusEnum.getCode();
        this.errorMsg = statusEnum.getMessage();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(Integer errorCode, String errorMsg, Throwable cause) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
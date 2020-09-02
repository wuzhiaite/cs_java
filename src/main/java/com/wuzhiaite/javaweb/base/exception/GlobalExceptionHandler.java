package com.wuzhiaite.javaweb.base.exception;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *  全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultObj bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultObj.failObj(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultObj exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ResultObj.failObj(ExceptionEnum.NULL_POINT_EXCEPTION);
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultObj exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ResultObj.failObj(e.getMessage());
    }
}

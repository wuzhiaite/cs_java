package com.wuzhiaite.javaweb.base.aspect;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 全局日志打印
 * @author lpf
 * @since 20200902
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    
    @Pointcut("execution(public * com.wuzhiaite.javaweb.common..*.*(..))")
    public void webLog() {

    }
    /**
     * 前置通知:在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
     *
     * @param joinPoint 参数
     */
    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        final JsonMapper jm = new JsonMapper();
        String request="接口："+joinPoint.getTarget().getClass().getName() + "."+ joinPoint.getSignature().getName()+"参数";
        for (Object object : joinPoint.getArgs()) {
            if (
                    object instanceof MultipartFile
                            || object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse
            ) {
                continue;
            }
            try {
                request  =request + jm.writeValueAsString(object)+" ";
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        log.debug(request);
    }

    /**
     * 返回后
     * @param response
     * @throws Throwable
     */
    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) throws Throwable {
        if (response != null) {
            log.debug("接口响应：response parameter : " + JsonMapperUtil.toString(response));
        }
    }






//    @Around("webLog()")
//    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
//        Object proceed = joinPoint.proceed();
//        log.info("METHOD : {} ; REQUEST：{} ; RESPONSE : {}",
//                joinPoint.getSignature().toShortString(),
//                JSONObject.toJSONString(joinPoint.getArgs()),
//                JSONObject.toJSONString(proceed));
//        return proceed;
//    }

}

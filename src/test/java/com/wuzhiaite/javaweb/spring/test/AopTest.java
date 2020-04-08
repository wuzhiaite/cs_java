package com.wuzhiaite.javaweb.spring.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopTest {

    @Pointcut("execution(public * com.wuzhiaite.javaweb.spring.serveice.AopServiceTest.*(..))")
    public void AopTest(){ }

    @Before("AopTest()")
    public void doBefore(){
        System.out.println("doBefore");
    }

    @After("AopTest()")
    public void doAfter(){
        System.out.println("doAfter");
    }


}

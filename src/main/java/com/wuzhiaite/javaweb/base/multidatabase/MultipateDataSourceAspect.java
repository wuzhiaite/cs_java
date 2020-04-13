package com.wuzhiaite.javaweb.base.multidatabase;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * @author lpf
 */
//@Aspect
//@Component
public class MultipateDataSourceAspect {

    @Pointcut("@within(com.wuzhiaite.javaweb.base.multidatabase.DataSource)")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint jp) throws SQLException {
        Class<?> clazz = jp.getTarget().getClass();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);
        String name;
        if(dataSource != null){
            name = dataSource.name();
        }else{
            dataSource = clazz.getAnnotation(DataSource.class);
            name = dataSource.name();
        }
        System.out.println("=================="+name+"==============");
        DynamicDataSourceContextHolder.setDataSourceKey(name);
    }

    @After("pointcut()")
    public void after(JoinPoint jp){
        DynamicDataSourceContextHolder.removeDataSource();
    }


}

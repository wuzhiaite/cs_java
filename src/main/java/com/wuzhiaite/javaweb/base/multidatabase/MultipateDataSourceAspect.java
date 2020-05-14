package com.wuzhiaite.javaweb.base.multidatabase;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;

/**
 * @author lpf
 */
@Aspect
@Component
@Order(1)//这是关键，要让该切面调用先于AbstractRoutingDataSource的determineCurrentLookupKey()
public class MultipateDataSourceAspect {

    @Pointcut("@annotation(com.wuzhiaite.javaweb.base.multidatabase.DynamciDb)")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint jp) throws Exception {
        Class<?> clazz = jp.getTarget().getClass();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        DynamciDb dataSource = method.getAnnotation(DynamciDb.class);
        String name;
        if(dataSource != null){
            name = dataSource.name();
        }else{
            dataSource = clazz.getAnnotation(DynamciDb.class);
            name = dataSource.name();
        }
        System.out.println("=================="+name+"==============");
        DynamicDataSourceContextHolder.setDataSourceKey(name);
//        DataSource dataSource = (DataSource)SpringContextUtil.getBean(targetDataSource.value());
        //---------------------修改mybatis的数据源-----------------------
        //修改MyBatis的数据源
//        SqlSessionFactoryBean sqlSessionFactoryBean = (SqlSessionFactoryBean) SpringContextUtil.getBean(SqlSessionFactoryBean.class);
//        Environment environment = sqlSessionFactoryBean.getObject().getConfiguration().getEnvironment();
//        Field dataSourceField = environment.getClass().getDeclaredField("dataSource");
//        dataSourceField.setAccessible(true);//跳过检查
//        dataSourceField.set(environment,dataSource);
    }

    @After("pointcut()")
    public void after(JoinPoint jp){
        DynamicDataSourceContextHolder.removeDataSource();
    }


}

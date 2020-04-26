package com.wuzhiaite.javaweb;


import com.wuzhiaite.javaweb.base.utils.CodeGeneratorUtil;
import com.wuzhiaite.javaweb.spring.serveice.AopServiceTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootJavawebBaseApplicationTests {

    @Autowired
    private AopServiceTest service;

    @Test
    public void aopTest1(){
        service.testMethod();
        new AopServiceTest.Inner().innerMethod();//静态内部类并不会生成代理类
    }

   @Test
   public void databaseTest(){
//       service.baseTest01();
       service.bastTest02();
//       service.baseTest03();
   }

   @Test
   public void generatorTest(){
       Map<String,Object> params = new HashMap<String,Object>();
       params.put("author","lpf");
       params.put("module","menus");
       params.put("tablenames","menus");
       CodeGeneratorUtil.generatorCode(params);
   }




}

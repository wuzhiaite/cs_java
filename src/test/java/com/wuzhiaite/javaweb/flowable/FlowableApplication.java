package com.wuzhiaite.javaweb.flowable;

import com.wuzhiaite.javaweb.flowable.controller.MyRestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class FlowableApplication {

    @Autowired
    private MyRestController controller;

    @Test
    public void flowableTest(){
//        List kermit = controller.getTasks("kermit");
//        System.out.println(kermit);
        controller.startProcessInstance();
        List kermit1 = controller.getTasks("kermit");
        System.out.println(kermit1);


    }





}

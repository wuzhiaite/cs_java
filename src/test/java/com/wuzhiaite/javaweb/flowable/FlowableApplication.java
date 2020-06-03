package com.wuzhiaite.javaweb.flowable;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class FlowableApplication {





}

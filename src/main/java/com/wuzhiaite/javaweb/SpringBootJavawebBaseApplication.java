package com.wuzhiaite.javaweb;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @description  启动类
 * @author lpf
 */
@Slf4j
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableTransactionManagement
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRabbit
@EnableElasticsearchRepositories
public class SpringBootJavawebBaseApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootJavawebBaseApplication.class, args);
        } catch (Exception e) {
            log.error(e.getMessage());

            e.printStackTrace();
        }
    }

    





}

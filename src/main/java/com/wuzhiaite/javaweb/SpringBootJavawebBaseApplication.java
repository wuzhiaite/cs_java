package com.wuzhiaite.javaweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wuzhiaite.javaweb")
@EnableTransactionManagement
public class SpringBootJavawebBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJavawebBaseApplication.class, args);
    }

}

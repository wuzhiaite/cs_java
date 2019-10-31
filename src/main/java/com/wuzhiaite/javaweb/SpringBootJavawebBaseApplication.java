package com.wuzhiaite.javaweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wuzhiaite.javaweb")
public class SpringBootJavawebBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJavawebBaseApplication.class, args);
    }

}

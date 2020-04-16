package com.wuzhiaite.javaweb;

import com.wuzhiaite.javaweb.base.multidatabase.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan(value= {"com.baomidou.mybatisplus.samples.quickstart.mapper",
        "com.wuzhiaite.javaweb"})
@EnableTransactionManagement
@EnableCaching //允许缓存
public class SpringBootJavawebBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJavawebBaseApplication.class, args);
    }

}

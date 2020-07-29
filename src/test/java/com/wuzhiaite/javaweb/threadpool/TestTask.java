package com.wuzhiaite.javaweb.threadpool;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.util.concurrent.Callable;
import java.util.zip.InflaterInputStream;

public class TestTask  implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        Thread.currentThread().interrupt();
        System.out.println("这是个测试");

    }
}

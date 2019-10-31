package com.wuzhiaite.javaweb.base.config;

import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.io.Reader;
import java.sql.*;
import java.util.Arrays;

@Configuration
public class DataSourceInitConfig implements InitializingBean {

    @Autowired
    private BaseProperties baseProperties;
    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
//        String sqlScriptPath = baseProperties.getSqlScriptPath();
//        Assert.notNull(sqlScriptPath,"sql脚本配置地址不能为空");
//
//        File filePath = new File(sqlScriptPath);
//
//        File[] files = filePath.listFiles();
//        Assert.notNull(files,"没找到初始化sql脚本文件");
//        Arrays.asList(files).forEach(file -> {
//            if(file.getName().indexOf("sql") != -1){
//                System.out.println("++++++++" + file.getName());
//
//            }
//        });






    }







}

package com.wuzhiaite.javaweb.base.config;
//
//import com.wuzhiaite.javaweb.base.multidatabase.DataSourceConfigure;
//import org.activiti.engine.ProcessEngineConfiguration;
//import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mail.MailProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description  流程配置
 * @author lpf
 */
//@Configuration
//@Component
public class ActivitiConfig {

//    /**
//     * 邮箱配置信息
//     */
//    @Autowired
//    private MailProperties mailProperties ;
//    /**
//     * 数据源头
//     */
//    @Autowired
//    private DataSourceConfigure dataSourceConfigure ;
//    /**
//     *
//     * @return
//     */
//    @Bean
//    public StandaloneProcessEngineConfiguration processEngineConfiguration(){
//        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
//        configuration.setMailServerHost(mailProperties.getHost());
//        configuration.setMailServerPort(mailProperties.getPort());
//        Map<String, Map<String, String>> dbsource = dataSourceConfigure.getDbsource();
//        Map<String, String> source = dbsource.get(DataSourceConfigure.DEFAULT_DATASOURCE);
//        configuration.setJdbcUrl(source.get("url"));
//        configuration.setJdbcUsername(source.get("username")) ;
//        configuration.setJdbcDriver(source.get("driver-class-name"));
//        configuration.setJdbcPassword(source.get("password"));
//        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        configuration.setAsyncExecutorActivate(false);
//        return configuration ;
//    }






}

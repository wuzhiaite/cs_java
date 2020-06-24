package com.wuzhiaite.javaweb.base.activiti;

import com.wuzhiaite.javaweb.base.multidatabase.DynamicDataSource;
import com.wuzhiaite.javaweb.base.multidatabase.DynamicDataSourceContextHolder;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 流程引擎配置类
 * @author lpf
 */
//@Configuration
public class AcititiEngineConfig {

    /**
     * 动态数据库
     */
    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        //监听事件
        List<ActivitiEventListener> eventListeners = new ArrayList<>();
        eventListeners.add(new MyActivitiEventListener());
        configuration.setEventListeners(eventListeners);
        //某种类型的监听事件
        Map<String, List<ActivitiEventListener>> map = new HashMap<>();
        map.put("JOB_EXECUTION_SUCCESS,JOB_EXECUTION_FAILURE",eventListeners);
        configuration.setTypedEventListeners(map);



        return configuration;
    }




}

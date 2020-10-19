package com.wuzhiaite.javaweb.base.config.activiti;

import com.wuzhiaite.javaweb.base.config.multidatabase.DynamicDataSource;
import com.wuzhiaite.javaweb.base.properties.DynamicDataSourceProperties;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

/**
 * 流程引擎配置
 * @author lpf
 */
@Configuration
@Order(2)
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
public class ActivitiProcessConfig {



    private int corePoolSize = 10;
    private int maxPoolSize = 30;
    private int keepAliveSeconds = 300;
    private int queueCapacity = 300;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Primary
    @Bean
    public ThreadPoolTaskExecutor workFlowAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("workFlowTaskExecutor-");
        executor.initialize();
        return executor;
    }

    /**
     *  流程引擎配置
     * @return
     * @throws IOException
     */
    @Bean
    @Primary
    public SpringProcessEngineConfiguration springProcessEngineConfiguration()
            throws IOException {
        // 自动部署流程的读取位置
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources(ResourceLoader.CLASSPATH_URL_PREFIX + "/processes/*.bpmn");
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        // 配置自己的数据源和事务管理器
        Map<Object, Object> dataSourceMap = DynamicDataSource.getDataSourceMap();
        DataSource dataSource = (DataSource) dataSourceMap.get(DynamicDataSourceProperties.DEFAULT_DATASOURCE);
        config.setDataSource(dataSource);
        config.setTransactionManager(transactionManager);
        // jdbc最大等待时间20秒，超出后重新连接
        config.setJdbcMaxWaitTime(2000);
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        config.setDeploymentResources(resources);
        return config;
    }


    @Bean
    @Primary
    public ProcessEngineFactoryBean processEngineFactoryBean(
            SpringProcessEngineConfiguration springProcessEngineConfiguration) throws ClassNotFoundException {

        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration);
        return processEngineFactoryBean;
    }


    @Primary
    @Bean
    public ProcessEngine processEngine(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject();
    }


    @Bean
    @Primary
    public RepositoryService repositoryService( ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    @Primary
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    @Primary
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    @Primary
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    @Primary
    public ManagementService managementService( ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    @Primary
    public IdentityService identityService( ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    @Bean
    @Primary
    public FormService formService( ProcessEngine processEngine) {
        return processEngine.getFormService();
    }



}

package com.wuzhiaite.javaweb.base.config.multidatabase;

import com.wuzhiaite.javaweb.base.properties.DynamicDataSourceProperties;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lpf
 * @description 配置数据库
 */
@Configuration
@Data
public class DataSourceConfigure {


    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    @Bean
    public DynamicDataSource  dynamicDataSource(){
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, Map<String, String>> dbsource = dynamicDataSourceProperties.getDbsource();
        for( String key : dbsource.keySet() ){
            Map<String, String> map = dbsource.get(key);
            DataSource source
                         =  DataSourceBuilder.create()
                            .driverClassName(MapUtil.getString(map,"driver-class-name"))
                            .url(MapUtil.getString(map,"url"))
                            .username(MapUtil.getString(map,"username"))
                            .password(MapUtil.getString(map,"password"))
                            .build();
            targetDataSources.put(key,source);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get(DynamicDataSourceProperties.DEFAULT_DATASOURCE));
        return dynamicDataSource ;
    }

    /**
     * 将动态数据源头添加到事务处理器中，并生成新的bean
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}

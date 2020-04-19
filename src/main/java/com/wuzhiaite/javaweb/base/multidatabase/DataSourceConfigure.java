package com.wuzhiaite.javaweb.base.multidatabase;

import com.wuzhiaite.javaweb.base.utils.MapUtil;
import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix="spring")
@Configuration
@Data
public class DataSourceConfigure {


    Map<String, Map<String,String>> dbsource = new HashMap<String, Map<String,String>>();


    @Bean
    public DynamicDataSource  dynamicDataSource(){
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSourceBuilder builder = DataSourceBuilder.create() ;
        for( String key : dbsource.keySet() ){
            Map<String, String> map = dbsource.get(key);
            DataSource source
                         = builder
                            .driverClassName(MapUtil.getString(map,"driver-class-name"))
                            .url(MapUtil.getString(map,"url"))
                            .username(MapUtil.getString(map,"username"))
                            .password(MapUtil.getString(map,"password"))
                            .build();
            targetDataSources.put(key,source);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get("master"));
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

package com.wuzhiaite.javaweb.base.config.multidatabase;

import com.wuzhiaite.javaweb.base.contextHolder.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lpf
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    private volatile static DynamicDataSource instance ;
    private static Object lock = new Object() ;

    private DynamicDataSource(){}

    /**
     * 获取当前datasource
     * @return
     */
    @Override
    protected String determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceContextHolder.getDataSource();
        return dataSource ;
    }

    /**
     * 设置目标数据库
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }


    public static Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    public static synchronized DynamicDataSource getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance=new DynamicDataSource();
                }
            }
        }
        return instance;
    }

    public static boolean isExistDataSource(String key){
        return dataSourceMap.containsKey(key);
    };


}

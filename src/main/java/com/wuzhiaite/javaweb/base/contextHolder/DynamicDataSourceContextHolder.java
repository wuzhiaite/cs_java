package com.wuzhiaite.javaweb.base.contextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @descript 当前数据配置
 * @author lpf
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();



    public static synchronized void setDataSourceKey(String key){
        contextHolder.set(key);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void removeDataSource(){
        contextHolder.remove();
    }


}

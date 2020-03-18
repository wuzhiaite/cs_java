package com.wuzhiaite.javaweb.base.utils;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map工具类
 */
public final class MapUtil {

    /**
     * @descript list集合中两列编程Map集合
     * @param params
     * @param key
     * @param value
     * @return
     */
    public static Map<String,String>  convertToMap(List<Map<String,String>> params, String key,String value){
        Assert.isNull(key,"key值列不能为空");
        Assert.isNull(key,"value值列不能为空");
        Map<String, String> map = new HashMap<>();
        params.forEach(param -> {
            map.put(param.get(key),param.get(value));
        });
        return map;
    }

    /**
     *
     * @param params
     * @param key
     * @return
     */
    public static String getString(Map<String,? super String> params ,String key){
        Assert.notNull(key,"key值不能为空");
        if(MapUtil.isNull(params)){ return "";}
        return (String)params.get(key);
    }

    /**
     *
     * @param params
     * @return
     */
    public static boolean isNull(Map<String,? extends Object> params){
        return params == null ? true : false ;
    }

    public static Integer getInteger(Map<String,? super String> params ,String key){
        Assert.notNull(key,"key值不能为空");
        if(MapUtil.isNull(params)){ return null;}
        return (Integer)params.get(key);
    }



}

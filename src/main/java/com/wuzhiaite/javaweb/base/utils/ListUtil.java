package com.wuzhiaite.javaweb.base.utils;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * list工具类
 */
public final class ListUtil{

    /**
     * 获取某一key的集合数据
     * @param params
     * @param key
     * @return
     */
    public static <T> List<T>   getValueList(List<Map<String, T >> params , String key){
        Assert.isNull(key,"key值不能为空");
        List<T> list = new ArrayList<>();
        params.forEach(param -> {
            T obj = param.get(key);
            list.add(obj);
        });
        return list;
    }

    /**
     *
     * @param params
     * @param key
     * @param clazz
     * @param <clazz>
     * @return
     */
    public static <clazz> List<clazz>   getValueList(List<Map<String, Object>> params , String key,Class clazz){
        Assert.notNull(key,"key值不能为空");
        List<clazz> list = new ArrayList<>();
        params.forEach(param -> {
            list.add((clazz) param.get(key));
        });
        return list;
    }
    /**
     * 空判断
     * @param param
     * @return
     */
    public static boolean isNull(List<? extends Object> param){
        return param == null || param.size() == 0 ? true : false;
    }

    /**
     * 非空判断
     * @param param
     * @return
     */
    public static boolean isNotNull(List<? extends Object> param){
        return !isNull(param);
    }

}

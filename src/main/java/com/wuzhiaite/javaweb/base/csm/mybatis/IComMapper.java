package com.wuzhiaite.javaweb.base.csm.mybatis;


import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;
import java.util.Map;

/**
 * 通用mapper
 * @param <T>
 */
public interface IComMapper<T> extends BaseMapper {

    <T> T get(T entity);

    List<T> findListByPage(T entity);

    List<T> findListByPage(Map<String,Object> entity);

    int insert(Object entity);

    int update(T entity);

    int delete(String pk);

    @InsertProvider(value= InsertListProvider.class,method = "insertList")
    <T> int saveBatch(List<T> list);



}

package com.wuzhiaite.javaweb.common.common;

import com.wuzhiaite.javaweb.base.dao.BaseMapper;

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

    int insert(T entity);

    int update(T entity);

    int delete(String pk);


}

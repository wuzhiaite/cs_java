package com.wuzhiaite.javaweb.module.common;

import com.github.pagehelper.PageInfo;

/**
 * 通用业务处理Service
 * @param <T>
 */
public interface IComCrudService <T> {

    T get(T entity) throws RuntimeException;

    PageInfo<T> findListByPage(Integer pageNum, Integer pageSize, T entity,String orderStr) throws RuntimeException;

    int insert(T entity) throws RuntimeException;

    int update(T entity)throws RuntimeException;

    int saveOrUpdate(T entity)throws RuntimeException ;

}

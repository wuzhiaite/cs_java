package com.wuzhiaite.javaweb.module.common;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ComCrudServiceImpl<Mapper extends IComMapper<T>,T> implements IComCrudService<T> {

    @Autowired
    private Mapper mapper ;
    private T entity;


    @Override
    public T get(T entity) throws RuntimeException {
        return null;
    }

    @Override
    public PageInfo<T> findListByPage(Integer pageNum, Integer pageSize, T entity, String orderStr) throws RuntimeException {
        return null;
    }

    @Override
    public int insert(T entity) throws RuntimeException {
        return 0;
    }

    @Override
    public int update(T entity) throws RuntimeException {
        return 0;
    }

    @Override
    public int saveOrUpdate(T entity) throws RuntimeException {
        return 0;
    }
}

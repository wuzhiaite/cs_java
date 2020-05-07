package com.wuzhiaite.javaweb.common.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.*;

/**
 * 通用service类
 * @param <Mapper>
 * @param <T>
 */
@Transactional
public class ComCrudServiceImpl<Mapper extends IComMapper<T>,T>  {

    @Autowired
    protected Mapper mapper ;

    private T entity;
    /**
     *
     * @param entity
     * @return
     * @throws RuntimeException
     */
    public T get(T entity) throws RuntimeException {
        return mapper.get(entity);
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param entity
     * @param orderStr
     * @return
     * @throws RuntimeException
     */
    public PageInfo<T> findListByPage(Integer pageNum, Integer pageSize, T entity, String orderStr) throws RuntimeException {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderStr);
        return new PageInfo<T>(mapper.findListByPage(entity));
    }

    /**
     *
     * @param entity
     * @param orderStr
     * @return
     * @throws RuntimeException
     */
    public PageInfo<T> findListByPage(Map<String,Object> entity,String orderStr) throws RuntimeException {
        Integer pageNum = MapUtil.getInteger(entity, "pageNum");
        Integer pageSize = MapUtil.getInteger(entity, "pageSize");
        pageNum = pageNum != null ? pageNum : 1 ;
        pageSize = pageSize != null ? pageSize : 10 ;
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy(orderStr);
        return new PageInfo<T>(mapper.findListByPage(entity));
    }

    /**
     *
     * @param entity
     * @return
     * @throws RuntimeException
     */
    public int insert(T entity) throws RuntimeException {
        return mapper.insert(entity);
    }

    /**
     *
     * @param entity
     * @return
     * @throws RuntimeException
     */
    public int update(T entity) throws RuntimeException {
        return mapper.update(entity);
    }

    /**
     *
     * @param entity
     * @param pk
     * @return
     * @throws RuntimeException
     */
    public int saveOrUpdate(T entity, String pk) throws RuntimeException {
        if (StringUtil.isNotEmpty(pk)) {
            T t = mapper.get(entity);
            if (t != null) {
                return mapper.update(entity);
            }
            return mapper.insert(entity);
        }
        return 0;
    }
    /**
     *
     * @param pk
     * @return
     * @throws RuntimeException
     */
    public int delete(String pk) throws RuntimeException {
        return mapper.delete(pk);
    }

    public <T> int saveBatch(List<T> list, Integer batchCount) {
        return mapper.saveBatch(list);
    }
}

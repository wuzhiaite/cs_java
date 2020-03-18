package com.wuzhiaite.javaweb.module.common;


import com.wuzhiaite.javaweb.base.dao.BaseMapper;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用于普通业务处理service
 */
@Service
@Log4j2
public class BaseService {
    /**
     *
     */
    @Autowired
    private BaseMapper baseMapper;

    /**
     * 通用get方法
     * @param tableName
     * @return
     */
    public List<Map<String,Object>> get( String tableName ){
        return this.get(tableName," 1=1 ");
    }

    /**
     * 通用get方法
     * @param tableName
     * @param condition
     * @return
     */
    public List<Map<String,Object>> get(String tableName,String condition){
        String sql = new SQL(){{
            SELECT(" * ");
            FROM(tableName);
            if(StringUtil.isNotBlank(condition)){
                WHERE(condition);
            }
        }}.toString();
        log.info(sql);
       return baseMapper.get(sql);
    }
    /**
     * 通用get方法
     * @param tableName
     * @return
     */
    public List<Map<String,Object>> get(String tableName,Map<String,Object> params){
        StringBuilder conditions = new StringBuilder();
        Set<String> keys = params.keySet();
        for( String key : keys ){
            String str = MapUtil.getString(params,key);
            conditions.append(",").append(key).append("='").append(str).append("'");
        }
        String sql = new SQL(){{
            SELECT(" * ");
            FROM(tableName);
            if(StringUtil.isBlank(conditions.toString())){
                WHERE(conditions.toString().substring(1));
            }
        }}.toString();
        log.info(sql);
        return baseMapper.get(sql);
    }
    /**
     * 插入数据
     * @param tableName
     * @param params
     * @return
     */
    private int insert(String tableName,Map<String,Object> params){
        params.put("CREATE_TIME",new Date());
        params.put("UPDATE_TIME",new Date());
        StringBuilder keySql = new StringBuilder();
        StringBuilder valueSql = new StringBuilder();
        Set<String> keys = params.keySet();
        for( String key : keys ){
            String str = MapUtil.getString(params,key);
            keySql.append(",").append(key);
            valueSql.append(",").append(str);
        }
        String sql = new SQL(){{
            INSERT_INTO(tableName);
            INTO_COLUMNS(keySql.toString().substring(1));
            INTO_VALUES(valueSql.toString().substring(1));
        }}.toString();
        log.info(sql);
        return baseMapper.insert(sql);
    }

    /**
     * 更新方法
     * @param tableName
     * @param pk
     * @param params
     * @return
     */
    private int update(String tableName, String pk, Map<String, Object> params) {
        String value = MapUtil.getString(params, pk);
        params.put("UPDATE_TIME",new Date());
        if(StringUtil.isBlank(value)) return 0;
        //条件项
        String condition = new StringBuilder(pk)
                .append(" = '").append(value).append("' ").toString();

        //设置项
        Set<String> keys = params.keySet();
        StringBuilder setSql = new StringBuilder();
        for( String key : keys ){
            String str = MapUtil.getString(params,key);
            setSql.append(",").append(key).append("='").append(str).append("'");
        }
        String sql = new SQL(){{
            UPDATE(tableName);
            SET(setSql.toString().substring(1));
            WHERE(condition);
        }}.toString();
        log.info(sql);
        return baseMapper.update(sql);
    }

    /**
     * 保存和更新
     * @param params
     * @param pk
     * @param tableName
     * @return
     */
    public int insertOrUpdate(Map<String,Object> params , String pk , String tableName){

        String value = (String) params.get(pk);
        String condition = pk + "='"+value+"'";
        List<Map<String, Object>> list = this.get(tableName, condition);
        int count = 0;
        if(list.size() > 0){
            count = this.update(tableName,pk,params);
        }else{
            count = this.insert(tableName,params);
        }
        return count;
    }

    /**
     * 删除数据
     * @param params
     * @param tableName
     * @return
     */
    public int delete(Map<String,Object> params , String tableName){
        Set<String> keys = params.keySet();
        StringBuilder condition = new StringBuilder();
        for( String key : keys ){
            String str = MapUtil.getString(params,key);
            condition.append(" , ").append(key).append("='").append(str).append("' ");
        }
        String sql = new SQL(){{
            DELETE_FROM(tableName);
            WHERE(condition.toString().substring(1));
        }}.toString();
        return baseMapper.delete(sql);
    }



}

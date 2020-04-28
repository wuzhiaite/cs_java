package com.wuzhiaite.javaweb.common.codegenerator.mapper;

import com.wuzhiaite.javaweb.common.common.IComMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author  lpf
 * @description 代码生成器mapper类
 */
@Mapper
public interface CodeGeneratorMapper extends IComMapper<Map<String,Object>> {
    /**
     * 查找表中所有的字段
     * @param tableName
     * @return
     */
    @Select("show full fields from #{tableName}")
    List<Map<String, Object>> getColumnInfo(@Param("tableName") String tableName);

    /**
     * 查找所有的表
     * @return
     */
    @Select("show table status")
    List<Map<String, Object>> getTableList();


}

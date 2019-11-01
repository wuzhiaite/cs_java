package com.wuzhiaite.javaweb.base.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseMapper {

    @Select("${sql}")
    public List<Map<String,Object>>  get(@Param("sql")String sql);

    @Insert("${sql}")
    public int  insert(@Param("sql")String sql);

    @Update("${sql}")
    public int update(@Param("sql")String sql);

    @Delete("${sql}")
    public int delete(@Param("sql")String sql);

}

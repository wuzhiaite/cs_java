package com.wuzhiaite.javaweb.base.csm.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BaseMapper {

    @Select("${sql}")
    public List<Map<String,Object>>  getBySQL(@Param("sql")String sql);

    @Select("${sql}")
    public Map<String,Object> findOneBySQL(@Param("sql")String sql);

    @Insert("${sql}")
    public int  insertBySQL(@Param("sql")String sql);

    @Update("${sql}")
    public int updateBySQL(@Param("sql")String sql);

    @Delete("${sql}")
    public int deleteBySQL(@Param("sql")String sql);




}

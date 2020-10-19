package com.wuzhiaite.javaweb.common.codegenerator.mapper;

import com.wuzhiaite.javaweb.base.csm.mybatis.IComMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author  lpf
 * @description 代码生成器mapper类
 */
@Mapper
public interface CodeGeneratorMapper extends IComMapper<Map<String,Object>> {

    /**
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> getColumnInfo(Map<String, Object> param);


}

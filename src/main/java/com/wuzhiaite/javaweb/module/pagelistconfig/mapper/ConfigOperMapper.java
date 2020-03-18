package com.wuzhiaite.javaweb.module.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigOperMapper {
    /**
     * 查找对应的
     * @param params
     * @return
     */
    public List<Map<String,Object>> getPageList(Map<String,Object> params);

    /**
     * 获取pageConf
     * @param id
     * @return
     */
    PageConf getConf(@Param("id") String id);
}

package com.wuzhiaite.javaweb.module.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.module.common.IComMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.PageConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigOperMapper extends IComMapper<Map<String,Object>> {



}

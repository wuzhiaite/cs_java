package com.wuzhiaite.javaweb.spring.mapper;


import com.wuzhiaite.javaweb.module.common.IComMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface  AopMapper extends IComMapper<Map<String,Object>> {


}

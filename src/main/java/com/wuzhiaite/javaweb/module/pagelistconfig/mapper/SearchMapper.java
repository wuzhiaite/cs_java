package com.wuzhiaite.javaweb.module.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper {

    @SelectProvider(value=SearchProvider.class,method="search")
    public List<Map<String,Object>> search(SearchFiled filed);





}

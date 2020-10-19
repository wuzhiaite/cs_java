package com.wuzhiaite.javaweb.common.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.base.csm.mybatis.BaseMapper;
import com.wuzhiaite.javaweb.common.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.common.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.common.pagelistconfig.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper extends BaseMapper {
    /**
     * 查找表数据
     * @param filed
     * @return
     */
    @SelectProvider(type=SearchProvider.class,method="search")
     List<Map<String,Object>> search(SearchFiled filed);

    /**
     * 查找列信息
     * @param table
     * @return
     */
    @SelectProvider(type=SearchProvider.class,method="getColumnInfo")
     List<Column> getColumnInfo(Table table);

    /**
     * 查找数据库中表信息
     * @param databaseName
     * @return
     */
    @SelectProvider(type=SearchProvider.class,method="getTableList")
     List<Table> getTableList(String databaseName);
}

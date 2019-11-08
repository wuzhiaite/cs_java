package com.wuzhiaite.javaweb.module.pagelistconfig.mapper;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper {
    /**
     * 查找表数据
     * @param filed
     * @return
     */
    @SelectProvider(value=SearchProvider.class,method="search")
     List<Map<String,Object>> search(SearchFiled filed);

    /**
     * 查找列信息
     * @param table
     * @return
     */
     List<Column> getColumnInfo(Table table);

    /**
     * 查找数据库中表信息
     * @param databaseName
     * @return
     */
     List<Table> getTableList(String databaseName);
}

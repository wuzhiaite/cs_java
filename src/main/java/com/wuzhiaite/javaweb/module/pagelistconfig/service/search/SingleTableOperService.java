package com.wuzhiaite.javaweb.module.pagelistconfig.service.search;

import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Table;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleTableOperService {

    /** 查询mapper*/
    @Autowired
    private SearchMapper mapper;

    @Autowired
    private BaseProperties baseProperties;

    /**
     * 查询表列信息
     * @param  table
     * @return Table
     */
    public Table getColumnInfo(Table table) throws  Exception {
        List<Column> columns = mapper.getColumnInfo(table);
        table.setColumns(columns);
        return table;
    }

    /**
     * 查找表信息
     * @return
     */
    public List<Table> getTableList() throws Exception{
        String databaseName = baseProperties.getDatabaseName();
        return mapper.getTableList(databaseName);
    }










}

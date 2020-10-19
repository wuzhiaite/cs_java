package com.wuzhiaite.javaweb.common.codegenerator.service;

import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.annonations.DynamciDb;
import com.wuzhiaite.javaweb.base.properties.DynamicDataSourceProperties;
import com.wuzhiaite.javaweb.common.codegenerator.utils.CodeGeneratorUtil;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import com.wuzhiaite.javaweb.common.codegenerator.mapper.CodeGeneratorMapper;
import com.wuzhiaite.javaweb.base.csm.service.ComCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lpf
 * @description 代码生成器处理类
 */
@Service
public class CodeGeneratorService extends ComCrudServiceImpl<CodeGeneratorMapper, Map<String,Object>> {


    /**
     * 表中所有信息
     * @param param
     * @return
     */
    public List<Map<String, Object>> getColumnInfo(Map<String, Object> param) throws SQLException {
        Map<String, Object> database = mapper.findOneBySQL("select database()");
        param.put("database",MapUtil.getString(database,"database()"));
        return mapper.getColumnInfo( param );
    }

    /**
     * 所有table
     * @return
     * @param params
     */
    @DynamciDb(name = DynamicDataSourceProperties.DEFAULT_DATASOURCE)
    public PageInfo<Map<String,Object>> getTableList(Map<String, Object> params) throws SQLException {
        Map<String, Object> database = mapper.findOneBySQL("select database()");
        params.put("database",MapUtil.getString(database,"database()"));
        String orders = MapUtil.getString(params,"orders");
        orders = StringUtil.isBlank(orders) ? " TABLE_NAME  DESC " : orders ;
        return this.findListByPage(params,orders);
    }

    /**
     * 生成代码
     * @param params
     * @throws RuntimeException
     */
    public void generatorCode(Map<String, Object> params) throws RuntimeException {
        CodeGeneratorUtil.generatorCode(params);
    }




}

package com.wuzhiaite.javaweb.common.codegenerator.service;

import com.wuzhiaite.javaweb.base.utils.CodeGeneratorUtil;
import com.wuzhiaite.javaweb.common.codegenerator.mapper.CodeGeneratorMapper;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import org.springframework.stereotype.Service;

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
     * @param tableName
     * @return
     */
    public List<Map<String, Object>> getColumnInfo(String tableName) {
        return mapper.getColumnInfo(tableName);
    }

    /**
     * 所有table
     * @return
     */
    public List<Map<String,Object>> getTableList() {
        return mapper.getTableList();
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

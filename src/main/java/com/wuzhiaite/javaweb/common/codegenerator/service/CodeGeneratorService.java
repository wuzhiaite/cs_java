package com.wuzhiaite.javaweb.common.codegenerator.service;

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


    public List<Map<String, Object>> getColumnInfo(String tableName) {

        return null;
    }
}

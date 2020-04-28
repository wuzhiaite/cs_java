package com.wuzhiaite.javaweb.common.codegenerator.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.codegenerator.service.CodeGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description 代码生成器
 * @author lpf
 */
@RestController
@RequestMapping("/api/codegenerator/")
@Slf4j
public class CodeGeneratorController {
    /**
     * 生成service
     */
    @Autowired
    private CodeGeneratorService generatorService;

    /**
     * 查找所有表信息
     * @return
     */
    @PostMapping("/getTableList")
    public ResultObj getTableList(){
        List<Map<String, Object>> tableList = null;
        try {
            tableList = generatorService.getTableList();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(tableList);
    }
    /**
     * 获取所有列信息
     * @return
     */
    @PostMapping("/getColumnsInfo")
    public ResultObj getColumnsInfo(@PathVariable String tableName){
        List<Map<String, Object>> columnInfo = null;
        try {
            columnInfo = generatorService.getColumnInfo(tableName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(columnInfo);
    }

    /**
     * 代码生成
     * @param params
     * @return
     */
    @PostMapping("/generatorCode")
    public ResultObj generatorCode(@RequestBody  Map<String,Object>  params){
        try {
            generatorService.generatorCode(params);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("生成成功");
    }








}

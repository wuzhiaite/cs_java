package com.wuzhiaite.javaweb.common.codegenerator.controller;

import com.github.pagehelper.PageInfo;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.codegenerator.service.CodeGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @description 代码生成器
 * @author lpf
 */
@RestController
@RequestMapping("/codegenerator/")
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
    public ResultObj getTableList(@RequestBody Map<String,Object> params) throws SQLException {
        PageInfo<Map<String,Object>> resObj = generatorService.getTableList(params);
        return ResultObj.successObj(resObj);
    }
    /**
     * 获取所有列信息
     * @return
     */
    @PostMapping("/getColumnsInfo")
    public ResultObj getColumnsInfo(@RequestBody Map<String,Object> params) throws SQLException {
        List<Map<String, Object>> columnInfo = generatorService.getColumnInfo(params);
        return ResultObj.successObj(columnInfo);
    }

    /**
     * 代码生成
     * @param params
     * @return
     */
    @PostMapping("/generatorCode")
    public ResultObj generatorCode(@RequestBody  Map<String,Object>  params){
        generatorService.generatorCode(params);
        return ResultObj.successObj("生成成功");
    }








}

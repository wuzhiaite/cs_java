package com.wuzhiaite.javaweb.common.pagelistconfig.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.common.pagelistconfig.entity.Table;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.SearchService;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.SingleTableOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * @author lpf
 * @since 2019-11-08
 * @serial 搜索
 *
 */
@RestController
public class SearchController {
    /** 搜索层service*/
    @Autowired
    private SearchService searchService;
    /**单表操作service*/
    @Autowired
    private SingleTableOperService singleService;
    /**
     * 搜索
     * @param searchFiled
     * @return
     */
    @RequestMapping("/search")
    public ResultObj search(@RequestBody SearchFiled searchFiled) {
        List<Map<String, Object>>  search = null;
        try {
            Assert.notNull(searchFiled,"查询参数不能为空，请重新确认");
            Assert.notNull(searchFiled.getTablename(),"查询表不能为空，请重新确认！");
            search = searchService.search(searchFiled);
        } catch (Exception e) {
            e.printStackTrace();
           return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(search);
    }

    /**
     * 查找所有表格
     * @return ResultObj
     */
    @RequestMapping("/getAllTable")
    public ResultObj getAllTable(){
        List<Table> tables = null ;
        try {
            tables = singleService.getTableList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultObj.successObj(tables);
    }

    /**
     * 查找表的列信息
     * @param table
     * @return
     */
    @RequestMapping("/getColumnInfo")
    public ResultObj getColumnInfo(@RequestBody Table table){
        try {
            table = singleService.getColumnInfo(table);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultObj.successObj(table);
    }





































}
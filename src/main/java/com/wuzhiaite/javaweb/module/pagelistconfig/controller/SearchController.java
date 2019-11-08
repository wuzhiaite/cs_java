package com.wuzhiaite.javaweb.module.pagelistconfig.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
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
    /**
     * 搜索层service
     */
    @Autowired
    private SearchService searchService;

    /**
     * 搜索
     * @param searchFiled
     * @return
     */
    @RequestMapping("/search")
    public ResultObj search(SearchFiled searchFiled) {
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










































}
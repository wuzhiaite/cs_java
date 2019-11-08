package com.wuzhiaite.javaweb.module.pagelistconfig.service;

import com.wuzhiaite.javaweb.base.properties.BaseProperties;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Table;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.DefaultCheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.CheckParam;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.ParamCheckWapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

/**
 * @author  lpf
 * @since 2019-11-08
 */
@Service
@Transactional
public class SearchService {
    /**查询mapper */
    @Autowired
    private SearchMapper mapper;
    /**默认检查链*/
    private static DefaultCheckChain checkChain ;
    /**单表业务查询*/
    private SingleTableOperService singleTableService;
    /**基础配置信息*/
    private BaseProperties baseProperties;

    /**过滤链路表配置*/
    static{
        checkChain = new DefaultCheckChain();
        //参数检查器
        ParamCheckWapper selectParamCheck = new ParamCheckWapper(new SelectParamCheck(),"SelectParamCheck");
        ParamCheckWapper groupParamCheck = new ParamCheckWapper(new GroupbyParamCheck(), "groupParamCheck");
        ParamCheckWapper conditionParamCheck = new ParamCheckWapper(new ConditionParamCheck(), "conditionParamCheck");
        //参数链表增加过滤类
        checkChain.addCheck(selectParamCheck);
        checkChain.addCheck(groupParamCheck);
        checkChain.addCheck(conditionParamCheck);
    }

    /**
     * 进行数据搜索
     * @param searchFiled
     * @return
     */
    @Transactional
    public List<Map<String,Object>> search(SearchFiled searchFiled) throws Exception{
        //获取表中列信息
        String tablename = searchFiled.getTablename();
        Table table = new Table();
        table.setName(tablename);
        table.setSchema(baseProperties.getDatabaseName());
        singleTableService.getColumnInfo(table);

        //创建param过滤对象,并进行过滤
        CheckParam param = new CheckParam();
        param.setSearchFiled(searchFiled);
        param.setTable(table);
        checkChain.doCheck(param);

        //表数据查询处理


        return null;
    }







}

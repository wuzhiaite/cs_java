package com.wuzhiaite.javaweb.module.pagelistconfig.service;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.DefaultCheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.CheckParam;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.ParamCheckWapper;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class SearchService {
    /**
     *
     */
    @Autowired
    private SearchMapper mapper;
    /**
     *
     */
    private static DefaultCheckChain checkChain ;

    /**
     * 过滤链路表配置
     */
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
    public List<Map<String,Object>> search(SearchFiled searchFiled){

        //创建param过滤对象
        CheckParam param = new CheckParam();

        checkChain.doCheck(param);

        return null;
    }



}

package com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SelectField;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckFilter;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.CheckParam;

import java.util.List;

/**
 * 对groupby的参数进行过滤
 */
public class GroupbyParamCheck implements CheckFilter<CheckParam> {

    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) {
        CheckParam checkParam = param.get();
        List<String> group = checkParam.getGroup();
        List<SelectField> selects = checkParam.getSelect();
        List<String> columnList = checkParam.getColumnList();
        SelectField  select = null;
        group.forEach( g -> {
            if(!columnList.contains(g)) throw new RuntimeException("分组列中"+g+"不能存在，请确定是否存在问题！");







        });


    }



}

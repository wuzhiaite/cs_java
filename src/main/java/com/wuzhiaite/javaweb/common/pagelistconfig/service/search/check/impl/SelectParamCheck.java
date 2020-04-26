package com.wuzhiaite.javaweb.common.pagelistconfig.service.search.check.impl;

import com.wuzhiaite.javaweb.common.pagelistconfig.entity.SelectField;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.check.CheckChain;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.check.Param;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.check.CheckFilter;
import com.wuzhiaite.javaweb.common.pagelistconfig.service.search.check.entity.CheckParam;

import java.util.List;

/**
 * select参数校验
 * @author lpf
 * @since 2019-11-08
 */
public class SelectParamCheck implements CheckFilter<CheckParam> {

    /**
     * 参数校验
     * @param param
     * @param chain
     */
    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) throws Exception{
        CheckParam checkParam = param.get();
        List<SelectField> selects = checkParam.getSelect();
        List<String> columns = checkParam.getColumnList();
        //对select参数进行校验
        selects.forEach(select -> {
            String filed = select.getFiled().toLowerCase();
            boolean flag = columns.contains(filed);
            if(!flag) throw new RuntimeException(select.getFiled()+"不存在，请刷新页面重新选择查询字段！！！");
        });

    }





}

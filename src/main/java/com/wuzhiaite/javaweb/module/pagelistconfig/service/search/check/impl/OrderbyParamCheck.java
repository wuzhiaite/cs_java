package com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.OrderField;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.CheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.CheckFilter;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity.CheckParam;

import java.util.List;

/**
 * order字段进行校验
 * @author  lpf
 */
public class OrderbyParamCheck implements CheckFilter<CheckParam> {
    /**
     * 查看排序字段是否存在
     * 查看
     * @param param
     * @param chain
     * @throws Exception
     */
    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) throws Exception {

        CheckParam  checkParam = param.get();
        List<OrderField> orderList = checkParam.getOrder();
        List<String> columnList = checkParam.getColumnList();
        orderList.stream().forEach(orderField -> {
            String field = orderField.getField();
            if(!columnList.contains(field)) throw new RuntimeException("排序列不存在，请确认");

        });

    }
}

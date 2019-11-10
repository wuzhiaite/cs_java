package com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.OrderField;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckFilter;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.CheckParam;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * order字段进行校验
 */
public class OrderbyParamCheck implements CheckFilter<CheckParam> {
    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) throws Exception {

        CheckParam  checkParam = param.get();
        List<OrderField> orderList = checkParam.getOrder();
        List<String> columnList = checkParam.getColumnList();
        Integer pageNum = checkParam.getSearchFiled().getPageNum();
        Integer pageSize = checkParam.getSearchFiled().getPageSize();
        orderList.stream().forEach(orderField -> {
            String field = orderField.getField();
            if(!columnList.contains(field)) throw new RuntimeException("排序列不存在，请确认");

        });

    }
}

package com.wuzhiaite.javaweb.module.pagelistconfig.service.check.impl;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.ConditionField;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Table;
import com.wuzhiaite.javaweb.module.pagelistconfig.enums.ColumnTypeEnum;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckChain;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.CheckFilter;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity.CheckParam;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查条件项参数
 * @since 2019-1108
 * @author  lpf
 */
public class ConditionParamCheck implements CheckFilter<CheckParam> {


    @Override
    public void checkParam(Param<CheckParam> param, CheckChain chain) {
        CheckParam checkParam = param.get();
        List<ConditionField> conditions = checkParam.getCondition();
        List<Column> columns = checkParam.getColumn();
        Map<String, ColumnTypeEnum> mapper = mapperColumn(columns);
        List<String> columnList = checkParam.getColumnList();
        conditions.forEach(condition -> {
            String filed = condition.getFiled();
            if(!columnList.contains(filed)) throw new RuntimeException("条件字段"+filed+"不存在，请重新确认！！！");
            Assert.notNull(condition.getFiled(),"过滤条件筛选字段不能为空！");
            Assert.notNull(condition.getValue(),"过滤条件值不能为空！");
            Assert.notNull(condition.getCondition(),"过滤参数判断条件不能为空");
            ColumnTypeEnum columnType = mapper.get(filed);
            condition.setType(columnType);
        });
        checkParam.getSearchFiled().setCondition(conditions);
    }

    /**
     * 参数格式化
     * @param values
     * @param type
     * @return
     */
    private List<String> format(List<String> values, String type) {
        ColumnTypeEnum columnType = ColumnTypeEnum.getEnumType(type);
        Assert.notNull(columnType,"列的数据类型不能为空，请刷新重试！");
        List<String> list = new ArrayList<>();
        values.forEach(value -> {
            String format = columnType.format(value);
            list.add(format);
        });
        return list;
    }

    /**
     * 字段和类型进行映射
     * @param columns
     * @return
     */
    private Map<String, ColumnTypeEnum> mapperColumn(List<Column> columns) {
        Map<String,ColumnTypeEnum> mapper = new HashMap<>();
        columns.forEach(column -> {
            mapper.put(column.getName(),ColumnTypeEnum.getEnumType(column.getType()));
        });
        return mapper;
    }




}

package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;

import java.util.List;

/**
 * 需要查询的字段
 */
@Data
public class SearchFiled {

    private List<SelectField> select;
    private String tablename;
    private List<ConditionField> condition;
    private List<String> group;
    private List<OrderField> order;
    private int pageSize;
    private int pageNum;




}

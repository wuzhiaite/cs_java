package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 需要查询的字段
 */
@Data
@ToString
public class SearchFiled {

    private List<SelectField> select;
    private String tablename;
    private List<ConditionField> condition;
    private List<String> group;
    private List<OrderField> order;
    private int pageSize;
    private int pageNum;
    private boolean isDistinct;


    public void setIsDistinct(boolean isDistinct){
        this.isDistinct = isDistinct;
    }

    public boolean getIsDistinct(){
        return this.isDistinct;
    }



}

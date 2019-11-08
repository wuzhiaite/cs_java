package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 需要查询的字段
 */
@Data
@ToString
@ApiModel(value="搜索字段",description = "搜索表信息所需要的字段")
public class SearchFiled {

    @ApiModelProperty(value="数值大小",name="名称")
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

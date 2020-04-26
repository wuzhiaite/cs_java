package com.wuzhiaite.javaweb.common.pagelistconfig.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.*;
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

    public Object deepClone() throws IOException, OptionalDataException,
            ClassNotFoundException {
        // 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        // 从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }


}

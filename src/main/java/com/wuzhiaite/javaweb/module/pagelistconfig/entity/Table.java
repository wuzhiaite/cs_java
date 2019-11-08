package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@ApiModel
public class Table implements Serializable {

    @ApiModelProperty(name="表名",value = "表名",dataType="varchar")
    private String name;
    private String comment;
    private String schema;
    private List<Column> columns;




}

package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import com.wuzhiaite.javaweb.module.pagelistconfig.enums.ColumnTypeEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 列信息
 * @author lpf
 */
@Data
@ToString
public class Column implements Serializable {

    private String name;
    private String comment;
    private String type;









}

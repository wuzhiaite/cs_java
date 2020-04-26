package com.wuzhiaite.javaweb.common.pagelistconfig.entity;

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

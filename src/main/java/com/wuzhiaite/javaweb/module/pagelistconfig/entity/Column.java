package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Column implements Serializable {

    private String name;
    private String comment;
    private String type;

}

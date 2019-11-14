package com.wuzhiaite.javaweb.module.pagelistconfig.service.config.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用于存放需要查找表的数据信息
 * @author lpf
 * @since 2019-11-14
 */
@Data
@ToString
public class TableField {

    private List<SelectFiled> selects;//表要查询的列
    private String tablename;//表名称
    private String alias;//别名


}

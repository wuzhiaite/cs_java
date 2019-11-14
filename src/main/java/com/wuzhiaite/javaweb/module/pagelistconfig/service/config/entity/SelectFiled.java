package com.wuzhiaite.javaweb.module.pagelistconfig.service.config.entity;


import lombok.Data;
import lombok.ToString;

/**
 * 用于存放需要查询的字段
 * @author  lpf
 * @since 2019-11-14
 */
@Data
@ToString
public class SelectFiled{


    private String name;//字段名称
    private String comment;//中文备注
    private String alias;//字段设置别名
    private String conditionName;//SQL配置中的字段设置








}

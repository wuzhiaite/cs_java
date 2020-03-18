package com.wuzhiaite.javaweb.module.pagelistconfig.enums;

import com.wuzhiaite.javaweb.base.enums.DateTypeEnum;

import java.text.SimpleDateFormat;

/**
 * 数据库列类型
 * @author lpf
 * @since 2019-11-08
 */
public enum ColumnTypeEnum {

    DATE("date"){
        @Override
        public String format(String value) {
            sdf.applyPattern(DateTypeEnum.YMD.format());
            String newValue = sdf.format(value);
            newValue = "'"+newValue+"'";
            return newValue;
        }
    },VARCHAR("varchar"){
        @Override
        public String format(String value) {
            return "'"+value+"'";
        }
    },TIMESTAMP("timestamp") {
        @Override
        public String format(String value) {
            return null;
        }
    },INT("int") {
        @Override
        public String format(String value) {
            return defaultFormat(value);
        }
    },DECIMAL("decimal"){
        @Override
        public String format(String value) {
            return defaultFormat(value);
        }
    };

    //字段类型
    private String type;
    private static SimpleDateFormat sdf = new SimpleDateFormat();


    ColumnTypeEnum(String type){
        this.type = type ;
    }
    public String type(){return type;}
    public abstract  String format(String value);
    //查找类型的枚举
    public static ColumnTypeEnum getEnumType(String type){
        for(ColumnTypeEnum typeEnum : ColumnTypeEnum.values()){
           return typeEnum;
        }
        return null;
    }

    public String defaultFormat(String value){
        return value;
    }
















}

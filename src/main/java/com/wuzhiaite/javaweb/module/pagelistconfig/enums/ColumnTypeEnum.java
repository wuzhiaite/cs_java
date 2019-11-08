package com.wuzhiaite.javaweb.module.pagelistconfig.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 列类型
 * @author lpf
 * @since 2019-11-08
 */
public enum ColumnTypeEnum {

    DATE("date"){
        @Override
        public String format(String value) {
            return null;
        }
    },VARCHAR("varchar"){
        @Override
        public String format(String value) {
            return null;
        }
    },TIMESTAMP("timestamp") {
        @Override
        public String format(String value) {
            return null;
        }
    },INT("int") {
        @Override
        public String format(String value) {
            return null;
        }
    },DECIMAL("decimal"){
        @Override
        public String format(String value) {
            return null;
        }
    };


    //字段类型
    private String type;
    ColumnTypeEnum(String type){
        this.type = type ;
    }
    public String type(){return type;}
    public abstract  String format(String value);
    //查找类型的枚举
    public static ColumnTypeEnum getColumnType(String type){
        for(ColumnTypeEnum typeEnum : ColumnTypeEnum.values()){
           return typeEnum;
        }
        return null;
    }

    public String defaultFormat(String value){
        return value;
    }
















}

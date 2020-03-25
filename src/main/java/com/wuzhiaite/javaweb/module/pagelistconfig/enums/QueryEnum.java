package com.wuzhiaite.javaweb.module.pagelistconfig.enums;

/**
 * @description 根据
 * @author lpf
 */

public enum QueryEnum {

    select("select"){
        @Override
        public void appendStr(StringBuilder sql, Object obj) {





        }
    },
    date("date"){
        @Override
        public void appendStr(StringBuilder sql, Object obj) {

        }
    },
    bol("bol"){
        @Override
        public void appendStr(StringBuilder sql, Object obj) {


        }
    },
    interval("interval"){
        @Override
        public void appendStr(StringBuilder sql, Object obj) {

        }
    },
    radioButtons("radio-button"){
        @Override
        public void appendStr(StringBuilder sql, Object obj) {

        }
    };

    private String type;
    QueryEnum(){}
    QueryEnum(String type){
        this.type = type ;
    }

    public String type(){
        return this.type;
    }
    public abstract void appendStr(StringBuilder sql, Object obj);


}

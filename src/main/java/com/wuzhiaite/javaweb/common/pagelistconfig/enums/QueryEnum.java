package com.wuzhiaite.javaweb.common.pagelistconfig.enums;

/**
 * @description 根据查询条件进行拼接
 * @author lpf
 */

public enum QueryEnum {

    select("select"){
        @Override
        public void appendStr(StringBuilder sql, String key, Object obj) {
            sql.append(key).append(" IN( '");
            String str = obj.toString();
            str.replace(",","' , '") ;
            sql.append(str).append("' )");
        }
    },
    date("date"){
        @Override
        public void appendStr(StringBuilder sql, String key, Object obj) {
            commonAppend(sql,key,obj);
        }
    },
    bol("bol"){
        @Override
        public void appendStr(StringBuilder sql, String key, Object obj) {
            commonAppend(sql,key,obj);
        }
    },
    interval("interval"){
        @Override
        public void appendStr(StringBuilder sql, String key, Object obj) {
            System.out.println(obj);
        }
    },
    radioButtons("radio-button"){
        @Override
        public void appendStr(StringBuilder sql, String key, Object obj) {
            commonAppend(sql,key,obj);
        }
    };

    private String type;
    QueryEnum(String type){
        this.type = type ;
    }

    public String type(){
        return this.type;
    }
    public abstract void appendStr(StringBuilder sql, String key, Object obj);

    protected void commonAppend(StringBuilder sql, String key, Object obj){
        sql.append(key).append("=").append(obj);
    }

}

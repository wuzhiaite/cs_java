package com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.Column;
import com.wuzhiaite.javaweb.module.pagelistconfig.entity.SearchFiled;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 需要校验的参数
 */

@ToString
@Data
public class CheckParam implements Param, Serializable {

    private SearchFiled searchFiled;
    private Column column;


    /**
     * 暂时不需要
     *
     *
     */

    /**私有化构造器*/
//    private CheckParam(){}
//
//    private CheckParam(Builder builder){
//        searchFiled = builder.searchFiled;
//        column = builder.column;
//    }

    /**
     * 类建造器
     */
//    public static class Builder{
//
//        private  SearchFiled searchFiled;
//        private  Column column;
//
//        public  Builder searchFiled(SearchFiled searchFiled){
//            this.searchFiled = searchFiled;
//            return this;
//        }
//        public  Builder column(Column column){
//            this.column = column;
//            return this;
//        }
//        public  CheckParam build(){
//            return new CheckParam(this);
//        }
//
//    }

}

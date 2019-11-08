package com.wuzhiaite.javaweb.module.pagelistconfig.service.check.entity;

import com.wuzhiaite.javaweb.module.pagelistconfig.entity.*;
import com.wuzhiaite.javaweb.module.pagelistconfig.service.check.Param;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 需要校验的参数
 */

@ToString
@Data
public class CheckParam implements Serializable, Param<CheckParam> {

    private SearchFiled searchFiled;
    private Table table;

    /** 获取当前对象 */
    @Override
    public CheckParam get() {
        return this;
    }
    /**获取列明数据*/
    public List<String> getColumnList(){
       return table.getColumnList();
    }
    /**获取查询字段*/
    public List<SelectField> getSelect(){
        return searchFiled.getSelect();
    }
    /**获取搜索条件*/
    public List<ConditionField> getCondition(){
        return searchFiled.getCondition();
    }
    /**获取分组字段*/
    public List<String>  getGroup(){
        return searchFiled.getGroup();
    }
    /**获取排序字段信息*/
    public List<OrderField> getOrder(){
        return searchFiled.getOrder();
    }
    /**表对应列数据*/
    public List<Column> getColumn(){
        return table.getColumns();
    }

}




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




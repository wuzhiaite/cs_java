package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 排序参数
 */
@Data
@ToString
public class OrderField implements Serializable {


    private String field;
    private OrderEnum orderEnum;

    public StringBuilder appendOrder(StringBuilder str) {
        return orderEnum.appendOrder(str,this);
    }

    public enum OrderEnum{
        ASC,DESC;

        public StringBuilder appendOrder(StringBuilder str, OrderField orderField) {
            return str.append(orderField.getField()).append("\t").append(orderField.getOrderEnum().name());
        }
    }


}

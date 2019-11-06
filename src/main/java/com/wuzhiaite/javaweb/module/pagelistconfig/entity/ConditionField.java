package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;

@Data
public class ConditionField {

    private String filed;
    private ConditionEnum condition;
    private String[] value;

    enum ConditionEnum{
        AND("AND"),
        OR("OR"),
        NOEQUAR("!="),
        EQUAR("="),
        BETWEEN("BETWEEN"),
        GREATER(">"),
        GOE(">="),
        LESS("<"),
        LOE("<="),
        LIKE("LIKE");

        private String symble;
        ConditionEnum(String symble){
            this.symble = symble;
        }




    }

}

package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConditionField implements Serializable {

    private String filed;
    private ConditionEnum condition;
    private List<String> value;

    public StringBuilder appendConditon(StringBuilder str) {
        return condition.appendConditon(str,this);
    }

    public enum ConditionEnum{
        AND("AND"){
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        OR("OR") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        NOEQUAR("!=") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        EQUAR("=") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        BETWEEN("BETWEEN") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                String filed = condition.getFiled();
                String con = condition.getCondition().symble();
                List<String> value = condition.getValue();
                if(value.size() < 2){
                    throw new ArithmeticException(filed+"字段传入参数或者比较方式有问题，请重新确认");
                }
                return str.append(filed).append(" ").append(con).append("  '")
                        .append(value.get(0)).append("'   AND   '").append(value.get(1)).append("'");
            }
        },
        GREATER(">") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        GOE(">=") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        LESS("<") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        LOE("<=") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                return defaultCondition(str,condition);
            }
        },
        LIKE("LIKE") {
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                String filed = condition.getFiled();
                ConditionEnum con = condition.getCondition();
                List<String> value = condition.getValue();
                Assert.notNull(value,filed+"参数不能为空！");
                str.append(filed).append("  ").append(con).append(" '%").append(value.get(0)).append("%' ");
                return str;
            }
        },
        IN("IN"){
            @Override
            StringBuilder appendConditon(StringBuilder str, ConditionField condition) {
                String filed = condition.getFiled();
                String con = condition.getCondition().symble();
                List<String> value = condition.getValue();
                String valueStr = value.stream().collect(Collectors.joining("' , '"));
                return str.append(filed).append(" \t ").append(con).append("\t  ('")
                        .append(valueStr).append("')  \n");
            }
        };

        private String symble;
        ConditionEnum(String symble){
            this.symble = symble;
        }
        public String symble(){return symble;}

        abstract StringBuilder appendConditon(StringBuilder str,ConditionField condition);

        StringBuilder defaultCondition(StringBuilder str,ConditionField condition){
            String filed = condition.getFiled();
            String con = condition.getCondition().symble();
            List<String> value = condition.getValue();
            Assert.notNull(value,filed+"的值不能为空！");
            return str.append(filed).append(" \t ").append(con).append(" \t '").append(value.get(0)).append("'  ");
        }



    }

}

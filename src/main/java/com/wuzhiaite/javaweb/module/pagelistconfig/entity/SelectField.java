package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectField  implements Serializable {

    private String Filed;
    private String Alias;
    private SelectEnum type;
    /***/
    public StringBuilder appendCol(StringBuilder str){
        return type.appendCol(str,this);
    }

    /**筛选字段统计枚举*/
    public enum SelectEnum{
        DEFAULT(""){
            @Override
            StringBuilder appendCol(StringBuilder str, SelectField field) {
                return str.append(field.getFiled()).append("  AS  ").append(field.getAlias());
            }
        },
        COUNT("COUNT"){
            @Override
            StringBuilder appendCol(StringBuilder str, SelectField field) {
                return defaultAppendCol(str,field);
            }
        },
        SUM("SUM") {
            @Override
            StringBuilder appendCol(StringBuilder str, SelectField field) {
                return defaultAppendCol(str,field);
            }
        },
        AVG("AVG") {
            @Override
            StringBuilder appendCol(StringBuilder str, SelectField field) {
                return defaultAppendCol(str,field);
            }
        };
        private String type;
        SelectEnum(String type){ this.type = type;}
        public String type(){return type;}
        //自定义字符串拼接方法
        abstract StringBuilder appendCol(StringBuilder str,SelectField field);

        /**字段进行拼接默认方法**/
        public StringBuilder defaultAppendCol(StringBuilder str,SelectField field) {
            str.append(this.type).append("(").append(field.Filed).append(") AS ").append(field.getAlias());
            return str;
        }
    }

}

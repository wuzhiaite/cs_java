package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;

@Data
public class SelectField {

    private String Filed;
    private String Alias;
    private SelectEnum type;

    enum SelectEnum{
        COUNT("COUNT"),
        SUM("SUM"),
        AVG("AVG");
        private String type;
        SelectEnum(String type){
            this.type = type;
        }
        public String type(){
            return type;
        }
    }

}

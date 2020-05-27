package com.wuzhiaite.javaweb.common.authority.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private String id ;
    private String roleLabel ;
    private String roleValue ;
    private Boolean isVlidate ;
    private String  permissions ;



}

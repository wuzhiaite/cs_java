package com.wuzhiaite.javaweb.module.authority.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private String id ;
    private String roleLabel ;
    private String roleValue ;
    private Boolean isVlidate ;



}

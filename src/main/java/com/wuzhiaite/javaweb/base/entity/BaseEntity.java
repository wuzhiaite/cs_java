package com.wuzhiaite.javaweb.base.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors
public class BaseEntity {
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;




}

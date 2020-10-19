package com.wuzhiaite.javaweb.base.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private Date createTime;
    private Date updateTime;


}

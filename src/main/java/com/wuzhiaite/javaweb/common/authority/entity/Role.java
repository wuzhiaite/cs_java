package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private String id ;
    private String roleLabel ;
    private String roleValue ;
    private Boolean isVlidate ;
    private String permissions ;
    @TableField(exist = false)
    private String search ;



}

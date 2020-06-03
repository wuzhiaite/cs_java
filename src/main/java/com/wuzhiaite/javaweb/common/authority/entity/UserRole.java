package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lpf
 */
@Data
public class UserRole implements Serializable {
    private String id ;
    private String roleLabel ;
    @TableField(value="role_label",insertStrategy= FieldStrategy.IGNORED,updateStrategy = FieldStrategy.IGNORED)
    private String label;
    @TableField(value="role_value",insertStrategy= FieldStrategy.IGNORED,updateStrategy = FieldStrategy.IGNORED)
    private String value;
    private String roleValue ;
    @TableField("isValidate")
    private Boolean isValidate ;
    private String permissions ;
    @TableField(exist = false)
    private String search ;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;


}

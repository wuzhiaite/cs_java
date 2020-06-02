package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wuzhiaite.javaweb.base.entity.BaseEntity;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author lpf
 */
@Data
public class UserRole extends BaseEntity implements Serializable {
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

}

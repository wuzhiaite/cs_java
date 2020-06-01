package com.wuzhiaite.javaweb.common.authority.entity;

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
    private String roleValue ;
    @TableField("isValidate")
    private Boolean isValidate ;
    private String permissions ;
    @TableField(exist = false)
    private String search ;

}

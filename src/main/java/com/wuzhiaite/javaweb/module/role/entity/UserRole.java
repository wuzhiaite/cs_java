package com.wuzhiaite.javaweb.module.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.ToString;
import com.wuzhiaite.javaweb.base.entity.BaseEntity;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-05-31
*/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserRole对象", description="")
public class UserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

        @TableField("ID")
    private String id;

        @TableField("ROLE_LABEL")
    private String roleLabel;

        @TableField("ROLE_VALUE")
    private String roleValue;

        @TableField("ISVALIDATE")
    private String isvalidate;


}
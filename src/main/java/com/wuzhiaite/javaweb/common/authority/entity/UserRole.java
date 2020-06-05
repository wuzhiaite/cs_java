package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lpf
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserRole对象", description="")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {

    private String id ;
    private String roleLabel ;
    private String roleValue ;
    @TableField("isValidate")
    private Boolean isValidate ;
    private String permissions ;

    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;

    @TableField(exist=false)
    private String label;
    @TableField(exist=false)
    private String value;
    @TableField(exist = false)
    private String search ;

    public void setId(String id) {
        this.id = id;
        this.value = id;
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel = roleLabel;
        this.label = roleLabel;
    }
}

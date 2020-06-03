package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
* @since 2020-06-03
*/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserRoleInfo对象", description="")
@Builder
public class UserRoleInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;


}

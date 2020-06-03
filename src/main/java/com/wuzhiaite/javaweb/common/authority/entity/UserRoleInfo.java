package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.ToString;

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
public class UserRoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;

    private String id;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;



}

package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

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
@AllArgsConstructor
@NoArgsConstructor
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

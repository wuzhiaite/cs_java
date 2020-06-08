package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lpf
 * @since 2020-06-04
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserInfo对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

   private static final long serialVersionUID = 1L;

   private String id;

   private String userId;

   private String username;

   private String password;

   @TableField("isValidate")
   private Boolean isValidate;

   private Date createTime;

   private String createUser;

   private Date updateTime;

   private String updateUser;
   @TableField(exist = false)
   private List<UserRoleInfo> roleInfo ;
   @TableField(exist = false)
   private UserDepartmentInfo departmentInfo;
   @TableField(exist = false)
   private List<UserPermission> permissionInfo;






}

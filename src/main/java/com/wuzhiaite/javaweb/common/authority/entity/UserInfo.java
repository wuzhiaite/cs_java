package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.ToString;
import lombok.Builder;

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
public class UserInfo implements Serializable {

   private static final long serialVersionUID = 1L;

   @TableId("ID")
   private String id;

   private String userId;

   private String username;

   private String password;

   private String telephone;

   @TableField("isValidate")
   private String isValidate;

   private LocalDateTime createTime;

   private String createUser;

   private LocalDateTime updateTime;

   private String updateUser;
   @TableField(exist = false)
   private List<UserRoleInfo> roleInfo ;
   @TableField(exist = false)
   private UserDepartmentInfo departmentInfo;






}

package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wuzhiaite.javaweb.base.entity.BaseEntity;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author lpf
 */
@Data
public class User extends BaseEntity implements Serializable {
   private String  id ;
   private String  userId ;
   private String  username ;
   private String  password ;
   private String  telephone ;
   private String  profilePhoto ;
   private Boolean isValidate ;
   @TableField(exist = false)
   private List<UserRole> roles ;


}

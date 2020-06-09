package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-06-08
*/
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserPermission对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class UserMenusPermission extends TreeEntity<UserMenusPermission>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String menuId;

    private String name ;

    private Boolean canAdd = true ;

    private Boolean canEdit = true ;

    private Boolean canDelete = true ;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}

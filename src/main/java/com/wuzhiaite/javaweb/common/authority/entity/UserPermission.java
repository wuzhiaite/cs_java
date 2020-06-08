package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class UserPermission extends Menus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String menuId;

    private String add;

    private String edit;

    private String delete;



}

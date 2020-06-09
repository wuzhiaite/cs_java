package com.wuzhiaite.javaweb.module.per.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-06-09
*/
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DepartmentMenusPermission对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentMenusPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String departmentId;

    private String menuId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;


}

package com.wuzhiaite.javaweb.common.authority.entity;

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
@ApiModel(value="UserDepartmentInfo对象", description="")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartmentInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String departmentId;

    private String id;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;

}

package com.wuzhiaite.javaweb.common.authority.entity;

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
@ApiModel(value="UserDepartmentInfo对象", description="")
@Builder
public class UserDepartmentInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String departmentId;


}

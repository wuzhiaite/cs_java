package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author lpf
* @since 2020-06-03
*/
@Mapper
public interface UserDepartmentInfoMapper extends BaseMapper<UserDepartmentInfo> {

    UserDepartment getUserDepartment(@Param("userId") String id);

}

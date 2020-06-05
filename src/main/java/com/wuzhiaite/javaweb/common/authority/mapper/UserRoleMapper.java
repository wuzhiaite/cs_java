package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author lpf
* @since 2020-05-31
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> getRoleList(@Param("username") String username);
}

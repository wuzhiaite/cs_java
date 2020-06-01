package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {


    List<UserRole> getRoles(@Param("username") String username);


    User getUser(@Param("username") String username);


}

package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.Role;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {


    List<Role> getRoles(@Param("username") String username);


    User getUser(@Param("username") String username);


}

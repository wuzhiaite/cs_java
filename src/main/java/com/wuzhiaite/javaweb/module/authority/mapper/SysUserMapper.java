package com.wuzhiaite.javaweb.module.authority.mapper;

import com.wuzhiaite.javaweb.module.authority.entity.Role;
import com.wuzhiaite.javaweb.module.authority.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper {


    List<Role> getRoles(@Param("username") String username);


    User getUser(@Param("username") String username);


}

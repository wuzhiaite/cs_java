package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.common.IComMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper extends IComMapper<Map<String, Object>> {


    List<UserRole> getRoles(@Param("username") String username);


    UserInfo getUser(@Param("username") String username);


}

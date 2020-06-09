package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.UserMenusPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author lpf
* @since 2020-06-08
*/
@Mapper
public interface UserMenusPermissionMapper extends BaseMapper<UserMenusPermission> {

    List<UserMenusPermission> getMenusPermissionList(UserMenusPermission entity);
}

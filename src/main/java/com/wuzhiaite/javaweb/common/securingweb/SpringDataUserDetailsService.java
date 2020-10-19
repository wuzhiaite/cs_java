package com.wuzhiaite.javaweb.common.securingweb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzhiaite.javaweb.base.entity.SecurityUserDetails;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息处理
 */
@Component
public class SpringDataUserDetailsService implements UserDetailsService {
    /**
     *
     */
    @Autowired
    private IUserInfoService userService;
    /**
     *
     */
    @Autowired
    private IUserRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = UserInfo.builder().userId(username).build();
        user = userService.getOne(new QueryWrapper<UserInfo>(user));
        List<UserRole> userRoles = roleService.getRoleList(username);
        return new SecurityUserDetails(username,user.getPassword(), userRoles);
    }


}

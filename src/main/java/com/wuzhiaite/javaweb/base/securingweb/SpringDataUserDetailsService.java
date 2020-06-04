package com.wuzhiaite.javaweb.base.securingweb;

import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户信息处理
 */
@Component
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(userService)){
            userService = new SysUserService();
        }
        UserInfo user = userService.getUserInfo(username);
        List<UserRole> userRoles = userService.getRoles(username);
        return new SecurityUserDetails(username,user.getPassword(), userRoles);
    }


}

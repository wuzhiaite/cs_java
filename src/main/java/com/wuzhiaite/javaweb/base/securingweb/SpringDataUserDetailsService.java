package com.wuzhiaite.javaweb.base.securingweb;

import com.wuzhiaite.javaweb.module.authority.entity.Role;
import com.wuzhiaite.javaweb.module.authority.entity.User;
import com.wuzhiaite.javaweb.module.authority.service.SysUserService;
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
    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserInfo(username);
        List<Role> roles = userService.getRoles(username);
        return new SecurityUserDetails(username,user.getPassword(),roles);
    }


}

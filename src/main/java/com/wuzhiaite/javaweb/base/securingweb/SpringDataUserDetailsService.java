package com.wuzhiaite.javaweb.base.securingweb;

import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.module.authority.entity.Role;
import com.wuzhiaite.javaweb.module.authority.entity.User;
import com.wuzhiaite.javaweb.module.authority.service.SysUserService;
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
        User user = userService.getUserInfo(username);
        List<Role> roles = userService.getRoles(username);
        return new SecurityUserDetails(username,user.getPassword(),roles);
    }


}

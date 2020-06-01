package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import com.wuzhiaite.javaweb.common.authority.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *获取用户信息
 */
@Service
public class SysUserService  {

    @Autowired
    private SysUserMapper mapper ;
    @Autowired
    private RedisUtil redisUtil ;

    public List<UserRole>  getRoles(String username){
        List<UserRole> userRoles = null;
        userRoles = mapper.getRoles(username);
        return userRoles;
    }


    public User getUserInfo(String username) {
        User user = null;
        user = mapper.getUser(username) ;
        return user ;
    }
}

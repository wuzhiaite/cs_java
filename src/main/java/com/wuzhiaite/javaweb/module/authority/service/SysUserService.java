package com.wuzhiaite.javaweb.module.authority.service;

import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.module.authority.entity.Role;
import com.wuzhiaite.javaweb.module.authority.entity.User;
import com.wuzhiaite.javaweb.module.authority.mapper.SysUserMapper;
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

    public List<Role>  getRoles(String username){
        List<Role> roles = null;
        if(redisUtil.hget("userinfo",username) != null){
            roles = (List<Role>) redisUtil.hget("roles",username);
        }else{
            roles = mapper.getRoles(username);
        }
        return roles;
    }


    public User getUserInfo(String username) {
        User user = null;
        if(redisUtil.hget("userinfo",username) != null){
            user = (User) redisUtil.hget("userinfo",username);
        }else{
           user = mapper.getUser(username) ;
        }
        return user ;
    }
}

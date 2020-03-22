package com.wuzhiaite.javaweb.module.authority.service;

import com.wuzhiaite.javaweb.module.authority.entity.Role;
import com.wuzhiaite.javaweb.module.authority.entity.User;
import com.wuzhiaite.javaweb.module.authority.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysUserService  {

    @Autowired
    private SysUserMapper mapper ;

    public List<Role>  getRoles(String username){
        return mapper.getRoles(username);
    }


    public User getUserInfo(String username) {
        return mapper.getUser(username) ;
    }
}

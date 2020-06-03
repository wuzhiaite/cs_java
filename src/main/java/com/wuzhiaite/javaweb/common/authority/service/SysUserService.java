package com.wuzhiaite.javaweb.common.authority.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import com.wuzhiaite.javaweb.common.authority.mapper.SysUserMapper;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 *获取用户信息
 */
@Service
@Slf4j
public class SysUserService extends ComCrudServiceImpl<SysUserMapper,Map<String,Object>> {


    @Autowired
    private RedisUtil redisUtil ;

    public List<UserRole>  getRoles(String username){
        List<UserRole> userRoles = null;
        userRoles = mapper.getRoles(username);
        return userRoles;
    }

    /**
     *
     * @param username
     * @return
     */
    public User getUserInfo(String username) {
        User user = null;
        user = mapper.getUser(username) ;
        return user ;
    }




}

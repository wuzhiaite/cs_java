package com.wuzhiaite.javaweb.common.authority.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import com.wuzhiaite.javaweb.common.authority.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    /**
     *  对用户权限进行设置
     * @param message
     * @throws UnsupportedEncodingException
     */
    @RabbitListener(queues="user.permission")
    public void setUserPermission(Message message) throws UnsupportedEncodingException {
        byte[] body = message.getBody();
        String str = new String(body, "UTF-8");
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> obj = jsonObject.getObject(str, Map.class);


    }


}

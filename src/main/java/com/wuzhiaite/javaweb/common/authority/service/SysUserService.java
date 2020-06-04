package com.wuzhiaite.javaweb.common.authority.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.mapper.SysUserMapper;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
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
    public UserInfo getUserInfo(String username) {
        UserInfo user = null;
        user = mapper.getUser(username) ;
        return user ;
    }

    /**
     * 暂时先这样写。。。更新是否有效
     * @param body
     * @return
     */
    public boolean updateValidate(Map<String, Object> body) {
        String sql = new SQL() {{
            UPDATE("user_info");
            SET("isValidate="+body.get("isValidate"));
            WHERE("id="+MapUtil.getString(body, "id"))
            .AND()
            .WHERE("user_id=" + MapUtil.getString(body, "userId"));
        }}.toString();
        return mapper.updateBySQL(sql) == 1;
    }
}

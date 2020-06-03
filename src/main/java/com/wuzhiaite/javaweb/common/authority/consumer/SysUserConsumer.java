package com.wuzhiaite.javaweb.common.authority.consumer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRoleInfo;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lpf
 */
@Component
public class SysUserConsumer {

    /**
     * 用户角色关联service
     */
    @Autowired
    private IUserRoleInfoService roleInfoService ;
    /**
     * 用户部门关联service
     */
    @Autowired
    private IUserDepartmentInfoService departmentInfoService ;
    /**
     *  对用户权限进行设置
     * @param message
     * @throws UnsupportedEncodingException
     */
    @RabbitListener(queues="user.permission")
    public void setUserPermission(Message message) throws UnsupportedEncodingException {
        try {

            Map<String,String> body = RabbitUtil.getMessageBody(message,Map.class);
            String userId = MapUtil.getString(body, "userId");
            String departmentId = MapUtil.getString(body, "departmentId");
            String roleId = MapUtil.getString(body, "roleId");
            if(!StringUtils.isEmpty(roleId)){
                UserRoleInfo roleInfo = UserRoleInfo.builder()
                                            .roleId(roleId).userId(userId).build();
                roleInfoService.saveOrUpdate(roleInfo);
            }
            if(!StringUtils.isEmpty(departmentId)){
                UserDepartmentInfo departmentInfo = UserDepartmentInfo.builder()
                                                    .userId(userId).departmentId(departmentId).build();
                departmentInfoService.saveOrUpdate(departmentInfo);
            }
        } catch (RuntimeException | JsonProcessingException e) {
            e.printStackTrace();
        } finally {


        }


    }



}

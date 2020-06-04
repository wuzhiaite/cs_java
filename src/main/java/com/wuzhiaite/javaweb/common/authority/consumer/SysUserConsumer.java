package com.wuzhiaite.javaweb.common.authority.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRoleInfo;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
import com.wuzhiaite.javaweb.common.authority.service.SysUserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author lpf
 */
@Component
public class SysUserConsumer {
    /**
     * 用户service
     */
    @Autowired
    private IUserInfoService userInfoService;
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
    public void setUserPermission(Message message) {
        try {
            UserInfo user = RabbitUtil.getMessageBody(message, UserInfo.class);
            UserDepartmentInfo departmentInfo = user.getDepartmentInfo();
            departmentInfoService.saveOrUpdate(departmentInfo);
            List<UserRoleInfo> roleInfo = user.getRoleInfo();
            roleInfoService.saveOrUpdateBatch(roleInfo);
            userInfoService.updateById(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
        }


    }



}

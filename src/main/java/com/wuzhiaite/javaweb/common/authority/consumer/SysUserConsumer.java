package com.wuzhiaite.javaweb.common.authority.consumer;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.common.authority.entity.*;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserPermissionService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lpf
 */
@Component
@Slf4j
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
    @Autowired
    private IUserPermissionService permissionService ;
    /**
     *  对用户权限进行设置
     * @param message
     * @throws UnsupportedEncodingException
     */
    @RabbitListener(queues="user.permission")
    public void setUserPermission(Message message, Channel channel) throws IOException {
        try {
            UserInfo user = RabbitUtil.getMessageBody(message, UserInfo.class);
            //先对部门关联数据进行删除，再重新插入，
            QueryWrapper<UserDepartmentInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getId());
            departmentInfoService.remove(wrapper);
            UserDepartmentInfo departmentInfo = user.getDepartmentInfo();
            departmentInfoService.saveOrUpdate(departmentInfo);
            //先对角色关联数据删除，再重新插入数据
            QueryWrapper<UserRoleInfo> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq("user_id",user.getId());
            roleInfoService.remove(roleWrapper);
            List<UserRoleInfo> roleInfo = user.getRoleInfo();
            roleInfoService.saveOrUpdateBatch(roleInfo);

            List<UserPermission> permissionInfo = user.getPermissionInfo();
            QueryWrapper<UserPermission> permissionWrapper = new QueryWrapper<>();
            permissionWrapper.eq("user_id",user.getId());
            permissionService.remove(permissionWrapper);
            permissionService.saveBatch(permissionInfo);

            userInfoService.updateById(user);

        }  catch (IOException e) {
            log.error("消费方法{}，爆出错误信息：{}","setUserPermission",e.getMessage());
        } finally {
            //告诉MQ删除这一条消息，若是true，则是删除所有小于tags的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }


    }



}

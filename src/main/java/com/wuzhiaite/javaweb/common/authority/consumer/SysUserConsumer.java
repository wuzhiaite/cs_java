package com.wuzhiaite.javaweb.common.authority.consumer;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRoleInfo;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
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
            userInfoService.updateById(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


        }


    }



}

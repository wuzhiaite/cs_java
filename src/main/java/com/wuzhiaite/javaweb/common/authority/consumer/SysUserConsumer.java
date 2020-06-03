package com.wuzhiaite.javaweb.common.authority.consumer;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RabbitUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRoleInfo;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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
            Map<String,Object> body = RabbitUtil.getMessageBody(message, Map.class);
            String userId = MapUtil.getString(body, "userId");
            Assert.notNull(body,userId);
            String departmentId = MapUtil.getString(body, "departmentId");
            String roleId = MapUtil.getString(body, "roleId");
            if(!StringUtils.isEmpty(roleId)){
                UserRoleInfo roleInfo = UserRoleInfo.builder().roleId(roleId).userId(userId).build();
                UpdateWrapper<UserRoleInfo> roleInfoWrapper = new UpdateWrapper<>(roleInfo);
                roleInfoWrapper.eq("user_id",userId);
                roleInfoService.update(roleInfoWrapper);
            }

            if(!StringUtils.isEmpty(departmentId)){
                UserDepartmentInfo departmentInfo = UserDepartmentInfo.builder().userId(userId).departmentId(departmentId).build();
                UpdateWrapper<UserDepartmentInfo> departmentWrapper = new UpdateWrapper<>(departmentInfo);
                departmentWrapper.eq("user_id",userId);
                departmentInfoService.update(departmentWrapper);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {


        }


    }



}

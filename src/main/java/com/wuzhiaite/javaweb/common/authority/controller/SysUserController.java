package com.wuzhiaite.javaweb.common.authority.controller;

import cn.hutool.core.lang.Assert;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.config.rabbitmq.RabbitSender;
import com.wuzhiaite.javaweb.base.entity.RabbitSenderEntity;
import com.wuzhiaite.javaweb.base.entity.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import com.wuzhiaite.javaweb.common.authority.entity.*;
import com.wuzhiaite.javaweb.common.authority.service.*;
import com.wuzhiaite.javaweb.base.csm.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 用户注册，登录退出处理类
 * @author lpf
 * @since 2020-04-11
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class SysUserController extends BaseController {

    /**
     * 
     */
    @Autowired
    private IUserInfoService userInfoService ;
    /**
     *
     */
    @Autowired
    private RabbitSender sender ;
    /**
     *
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     *
     */
    @Autowired
    private IUserRoleService roleService ;
    @Autowired
    private IUserDepartmentService departmentService;



    @Autowired
    private IUserPermissionService permissionService ;

    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping("/login")
    public ResultObj login(@RequestBody Map<String,String> params,
                           HttpServletRequest request) throws Exception {
        Map<String,Object> map = userInfoService.login(params);
        return ResultObj.successObj(map ,"登录成功");
    }

    @RequestMapping("/user/userinfo")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public ResultObj userInfo(){
        SecurityUserDetails principal =
                (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultObj.successObj("this is user base info");
    }

    /**
     *
     * @return
     */
    @RequestMapping("/logout")
    public ResultObj logout(){
        Map<String,Object> map = new HashMap<>();
        SecurityUserDetails principal =
                (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultObj.successObj(map ,"登出成功");
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping("/setUserPermission")
    public ResultObj setUserPermission(@RequestBody UserInfo user){
        try {
            Assert.notNull(user);
            RabbitSenderEntity entity = RabbitSenderEntity.builder()
                                              .exchange("cs.user.topic")
                                              .routeKey("user.permission")
                                              .params(JsonMapperUtil.toString(user)).build();
            sender.convertAndSend(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("权限设置成功");
    }



    @PostMapping("/getPermission/{id}")
    public ResultObj getUserPermission(@PathVariable String id){
        UserInfo userInfo = userInfoService.getUserPermission(id);
        return ResultObj.successObj(userInfo);
    }




}

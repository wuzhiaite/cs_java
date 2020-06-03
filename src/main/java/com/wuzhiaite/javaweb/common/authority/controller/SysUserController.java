package com.wuzhiaite.javaweb.common.authority.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.rabbitmq.RabbitSender;
import com.wuzhiaite.javaweb.base.rabbitmq.RabbitSenderEntity;
import com.wuzhiaite.javaweb.base.securingweb.JwtTokenUtil;
import com.wuzhiaite.javaweb.base.securingweb.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.RedisUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.User;
import com.wuzhiaite.javaweb.common.authority.service.SysUserService;
import com.wuzhiaite.javaweb.common.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 用户注册，登录退出处理类
 * @author lpf
 * @since 2020-04-11
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class SysUserController extends BaseController {
    /**
     *
     */
    @Autowired
    private SysUserService userService;
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
    private RedisUtil redisUtil;
    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping("/login")
    public ResultObj login(@RequestBody Map<String,String> params,
                           HttpServletRequest request) throws Exception {
         Map<String,Object> map = new HashMap<>();
        //用户验证
        try {
            String username = MapUtil.getString(params, "username");

            String password = MapUtil.getString(params, "password");
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(token);
            //存储认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //获取token信息
            SecurityUserDetails principal = (SecurityUserDetails) authentication.getPrincipal();
            String str = JwtTokenUtil.generateToken(principal.getUsername());
            User user = userService.getUserInfo(username) ;
            List<UserRole> userRoles = userService.getRoles(username);
            map.put("token",str);
            map.put("username",principal.getUsername());
            map.put("authorities",principal.getAuthorities());
            map.put("user",user );
            map.put("roles", userRoles);

            log.info(String.valueOf(map));
        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResultObj.failObj(e.getMessage());
        }
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
        try {
            SecurityUserDetails principal =
                    (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(map ,"登出成功");
    }

    @RequestMapping("/setUserPermission")
    public ResultObj setUserPermission(@RequestBody Map<String,Object> params){
        try {
            if(MapUtil.isNull(params)){
                throw new RuntimeException("参数不能为空，请重新确认");
            }
            RabbitSenderEntity entity = RabbitSenderEntity.builder()
                                              .exchange("cs.user.topic")
                                              .routeKey("user.permission")
                                              .params(params).build();
            sender.convertAndSend(entity);

        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj("权限设置成功");
    }

}

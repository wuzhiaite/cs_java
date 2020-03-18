package com.wuzhiaite.javaweb.module.authority.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.base.securingweb.JwtTokenUtil;
import com.wuzhiaite.javaweb.base.securingweb.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.module.authority.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 用户注册，登录退出处理类
 * @author lpf
 */
@RestController
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 登录
     * @param
     * @return
     */
    @PostMapping("/user/login")
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
            map.put("token",str);
            map.put("username",principal.getUsername());
            map.put("authorities",principal.getAuthorities());
            map.put("user",userService.getUserInfo(username));
            log.info(String.valueOf(map));
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            return  ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(map ,"登录成功");
    }

    @RequestMapping("/user/userinfo")
    public ResultObj userInfo(){
        SecurityUserDetails principal =
                (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultObj.successObj("this is user base info");
    }





}

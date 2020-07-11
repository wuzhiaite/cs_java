package com.wuzhiaite.javaweb.base.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *  springscurity 工具类
 * @author lpf
 * @since 20200710
 */
public class SpringSecurityUtil {
    /**
     * 查询当前用户的名称
     * @return
     */
    public static String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }










}

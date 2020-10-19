package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzhiaite.javaweb.base.utils.JwtTokenUtil;
import com.wuzhiaite.javaweb.base.entity.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.entity.UserRoleInfo;
import com.wuzhiaite.javaweb.common.authority.mapper.UserInfoMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleInfoService;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-06-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     *
     */
    @Autowired
    private IUserRoleService roleService ;
    /**
     *
     */
    @Autowired
    private IUserRoleInfoService roleInfoService;

    @Autowired
    private IUserDepartmentInfoService departmentInfoService;




    @Override
    public Map<String, Object> login(Map<String, String> params) {
        Map<String,Object> map = new HashMap<>();
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
        UserInfo userInfo = UserInfo.builder().userId(username).build();
        userInfo = this.getOne(new QueryWrapper<>(userInfo));
        List<UserRole> userRoles = roleService.getRoleList(username);
        map.put("token",str);
        map.put("username",principal.getUsername());
        map.put("authorities",principal.getAuthorities());
        map.put("user",userInfo );
        map.put("roles", userRoles);
        return map;
    }

    @Override
    public UserInfo getUserPermission(String id) {
        UserInfo userInfo = this.getById(id);
        UserRoleInfo roleInfo = UserRoleInfo.builder().userId(id).build();
        List<UserRoleInfo> roleInfoList = roleInfoService.list(new QueryWrapper<>(roleInfo));
        userInfo.setRoleInfo(roleInfoList);
        UserDepartmentInfo departmentInfo = UserDepartmentInfo.builder().userId(id).build();
        departmentInfo = departmentInfoService.getOne(new QueryWrapper<UserDepartmentInfo>(departmentInfo));
        userInfo.setDepartmentInfo(departmentInfo);
        return userInfo;
    }


}

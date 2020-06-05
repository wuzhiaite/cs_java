package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import com.wuzhiaite.javaweb.common.authority.mapper.UserRoleMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-05-31
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper mapper;

    @Override
    public List<UserRole> getRoleList(String username) {
        return mapper.getRoleList(username);
    }
}

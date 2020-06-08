package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhiaite.javaweb.base.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.UserPermission;
import com.wuzhiaite.javaweb.common.authority.mapper.UserPermissionMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-06-08
 */
@Service
public class UserPermissionServiceImpl  extends TreeService<UserPermissionMapper, UserPermission>
        implements IUserPermissionService {

    @Override
    public List<UserPermission> menusPermisisonList(UserPermission entity) {
        List<UserPermission> perList = baseMapper.getMenusPermissionList(entity);
        perList = this.getTree(perList);
        return perList;
    }


}

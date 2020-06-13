package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.wuzhiaite.javaweb.base.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.UserMenusPermission;
import com.wuzhiaite.javaweb.common.authority.mapper.UserMenusPermissionMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserMenusPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  用户菜单页面按钮权限
 * </p>
 *
 * @author lpf
 * @since 2020-06-08
 */
@Service
public class UserMenusPermissionServiceImpl extends TreeService<UserMenusPermissionMapper, UserMenusPermission>
        implements IUserMenusPermissionService {
    /**
     *
     * @param entity
     * @return
     */
    @Override
    public List<UserMenusPermission> menusPermisison(UserMenusPermission entity) {
        List<UserMenusPermission> list = baseMapper.getMenusPermissionList(entity);
        return this.getTree(list);
    }

    /**
     *  获取父类
     * @param userMenusPermission
     * @param list
     */
    @Override
    protected void getFather(UserMenusPermission userMenusPermission, List<UserMenusPermission> list) {
        for(UserMenusPermission permission : list){
            if(userMenusPermission.getFatherId().equals(permission.getMenuId())){
                List<UserMenusPermission> childrens = permission.getChildren();
                if(StringUtils.isEmpty(childrens)){
                    childrens = new ArrayList();
                }
                childrens.add(userMenusPermission);
                permission.setChildren(childrens);
                break;
            }else{
                continue ;
            }
        }
    }
}

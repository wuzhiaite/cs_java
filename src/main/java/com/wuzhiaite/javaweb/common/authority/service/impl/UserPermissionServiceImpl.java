package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhiaite.javaweb.base.service.ITree;
import com.wuzhiaite.javaweb.base.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.UserPermission;
import com.wuzhiaite.javaweb.common.authority.mapper.UserPermissionMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
    /**
     *
     * @param entity
     * @return
     */
    @Override
    public List<UserPermission> menusPermisison(UserPermission entity) {
        List<UserPermission> list = baseMapper.getMenusPermissionList(entity);
        return this.getTree(list);
    }

    /**
     *  获取父类
     * @param userPermission
     * @param list
     */
    @Override
    protected void getFather(UserPermission userPermission, List<UserPermission> list) {
        for(UserPermission permission : list){
            if(userPermission.getFatherId().equals(permission.getMenuId())){
                List<UserPermission> childrens = permission.getChildren();
                if(StringUtils.isEmpty(childrens)){
                    childrens = new ArrayList();
                }
                childrens.add(userPermission);
                permission.setChildren(childrens);
                break;
            }else{
                continue ;
            }
        }
    }
}

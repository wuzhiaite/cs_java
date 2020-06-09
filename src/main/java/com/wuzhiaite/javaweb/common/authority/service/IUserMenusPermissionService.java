package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.base.service.ITree;
import com.wuzhiaite.javaweb.common.authority.entity.UserMenusPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-06-08
 */
public interface IUserMenusPermissionService extends ITree<UserMenusPermission> {

    List<UserMenusPermission> menusPermisison(UserMenusPermission entity);
}

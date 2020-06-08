package com.wuzhiaite.javaweb.common.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzhiaite.javaweb.base.service.ITree;
import com.wuzhiaite.javaweb.common.authority.entity.UserPermission;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-06-08
 */
public interface IUserPermissionService extends ITree<UserPermission> {

    List<UserPermission> menusPermisison(UserPermission entity);
}

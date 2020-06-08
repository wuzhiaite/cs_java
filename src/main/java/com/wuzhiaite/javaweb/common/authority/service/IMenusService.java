package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.base.service.ITree;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;
import com.wuzhiaite.javaweb.common.authority.entity.UserPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-04-28
 */
public interface IMenusService extends ITree<Menus> {

    List<Menus> menuslist(Menus entity);

}

package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.base.csm.service.ITree;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;

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
    /**
     *
     * @param entity
     * @return
     */
    List<Menus> menuslist(Menus entity);

    /**
     *
     * @return
     */
    List<Menus> getUserMenuList();
}

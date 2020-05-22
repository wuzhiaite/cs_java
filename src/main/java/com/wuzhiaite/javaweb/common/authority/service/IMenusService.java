package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-04-28
 */
public interface IMenusService extends IService<Menus> {

    List<Menus> menuslist(Menus entity);

}

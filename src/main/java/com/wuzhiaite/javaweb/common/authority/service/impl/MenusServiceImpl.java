package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzhiaite.javaweb.base.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.mapper.MenusMapper;
import com.wuzhiaite.javaweb.common.authority.service.IMenusService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-04-28
 */
@Service
public class MenusServiceImpl  extends TreeService<MenusMapper, Menus>
             implements IMenusService {

    /**
     * 获取菜单列表
     * @param
     * @return
     */
    @Override
    public List<Menus> menuslist(Menus entity) {
        QueryWrapper<Menus> wrapper = new QueryWrapper<>(entity);
        wrapper.orderByAsc("orderBy");
        List<Menus> list = getBaseMapper().selectList(wrapper);
        list = this.getTree(list);
        return list;
    }




}

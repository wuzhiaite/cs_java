package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.mapper.MenusMapper;
import com.wuzhiaite.javaweb.common.authority.service.IMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus> implements IMenusService {

    /**
     * 获取菜单列表
     * @param
     * @return
     */
    @Override
    public List<Menus> menuslist(Menus entity) {
        List<Menus> menus = baseMapper.getMenuList(entity);
        List<Menus> temp = new ArrayList<>();
        for(Menus menu : menus ){
            String fatherId = menu.getFatherId();
            if(!StringUtils.isEmpty(fatherId)){
                getMenuFather(menu,menus);
            }else{
                continue ;
            }
        }
        for(Menus m : menus){
            String fatherId = m.getFatherId();
            if(StringUtils.isEmpty(fatherId)){
                temp.add(m);
            }
        }
        return temp;
    }

    /**
     * 关联子菜单和父菜单
     * @param menu
     * @param menus
     */
    private void getMenuFather(Menus menu, List<Menus> menus) {
        for(Menus m : menus){
            if(menu.getFatherId().equals(m.getId())){
                List<Menus> childrens = m.getChildren();
                if(StringUtils.isEmpty(childrens)){
                    childrens = new ArrayList();
                }
                childrens.add(menu);
                m.setChildren(childrens);
                break;
            }else{
                continue ;
            }
        }
    }


}

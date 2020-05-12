package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.wuzhiaite.javaweb.common.authority.mapper.MenusMapper;
import com.wuzhiaite.javaweb.common.authority.mapper.UserMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, Menus> implements IUserService {

    /**
     * 获取菜单列表
     * @param queryWrapper
     * @return
     */
    @Override
    public List<Menus> list(Wrapper<Menus> queryWrapper) {
        List<Menus> menus = baseMapper.selectList(queryWrapper);
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

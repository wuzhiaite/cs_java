package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.securingweb.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.service.impl.TreeService;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import com.wuzhiaite.javaweb.common.authority.entity.*;
import com.wuzhiaite.javaweb.common.authority.mapper.MenusMapper;
import com.wuzhiaite.javaweb.common.authority.mapper.UserMenusPermissionMapper;
import com.wuzhiaite.javaweb.common.authority.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserMenusPermissionMapper userMenusPermissionMapper;
    @Autowired
    private IUserDepartmentInfoService departmentInfoService;
    /**
     *
     */
    @Autowired
    private IUserInfoService userInfoService ;
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

    /**
     * 获取用户菜单列表
     * @return
     */
    @Override
    public List<Menus> getUserMenuList() {
        SecurityUserDetails principal =
                (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        UserInfo userInfo = UserInfo.builder().userId(username).build();
        userInfo = userInfoService.getOne(new QueryWrapper<>(userInfo));
        String id = userInfo.getId();
        UserDepartment department = departmentInfoService.getUserDepartment(id);
        if(StringUtils.isEmpty(department)) {
            return null ;
        }
        List<String> list = department.getMenuList();
        if(StringUtils.isEmpty(list)) {
            return null;
        }
        List<Menus> menus = baseMapper.getUserMenuList(id,list);
        menus.stream().forEach(menu->{
            List<String> temp = new ArrayList<>();
            if(menu.getCanAdd()){
                temp.add("can_add");
            }
            if(menu.getCanDelete()){
                temp.add("can_delete");
            }
            if(menu.getCanEdit()){
                temp.add("can_edit");
            }
            Map<String,Object> meta = new HashMap<>();
            meta.put("permissions",temp);
            menu.setMeta(meta);
        });
        return this.getTree(menus);
    }


}

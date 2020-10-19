package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzhiaite.javaweb.base.entity.SecurityUserDetails;
import com.wuzhiaite.javaweb.base.csm.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.*;
import com.wuzhiaite.javaweb.common.authority.mapper.MenusMapper;
import com.wuzhiaite.javaweb.common.authority.mapper.UserMenusPermissionMapper;
import com.wuzhiaite.javaweb.common.authority.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
@Slf4j
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
        log.info("================{}=========",menus);

        return this.getTree(menus);
    }


}

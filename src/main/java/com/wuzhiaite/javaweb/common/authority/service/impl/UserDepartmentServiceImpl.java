package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuzhiaite.javaweb.base.csm.service.impl.TreeService;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;
import com.wuzhiaite.javaweb.common.authority.mapper.UserDepartmentMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-06-01
 */
@Service
public class UserDepartmentServiceImpl extends TreeService<UserDepartmentMapper, UserDepartment>
        implements IUserDepartmentService{

    /**
     * 获取菜单列表
     * @param
     * @return
     */
    @Override
    public List<UserDepartment> depList(UserDepartment entity) {
        QueryWrapper<UserDepartment> wrapper = new QueryWrapper<>(entity);
        wrapper.orderByAsc("orderBy");
        List<UserDepartment> list = getBaseMapper().selectList(wrapper);
        list = this.getTree(list);
        return list;
    }




}


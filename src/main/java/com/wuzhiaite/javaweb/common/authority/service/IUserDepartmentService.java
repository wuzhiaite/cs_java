package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.base.csm.service.ITree;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-06-01
 */
public interface IUserDepartmentService extends ITree<UserDepartment> {
    /**
     *
     * @param entity
     * @return
     */
    public List<UserDepartment> depList(UserDepartment entity);
}

package com.wuzhiaite.javaweb.common.authority.service;

import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-06-03
 */
public interface IUserDepartmentInfoService extends IService<UserDepartmentInfo> {

    /**
     *  获取用户部门信息
     * @param id
     * @return
     */
    UserDepartment getUserDepartment(String id);



}

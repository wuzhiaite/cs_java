package com.wuzhiaite.javaweb.common.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-06-04
 */
public interface IUserInfoService extends IService<UserInfo> {

    Map<String, Object> login(Map<String, String> params);

    UserInfo getUserPermission(String id);

}

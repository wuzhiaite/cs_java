package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import com.wuzhiaite.javaweb.common.authority.mapper.UserInfoMapper;
import com.wuzhiaite.javaweb.common.authority.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-06-04
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}

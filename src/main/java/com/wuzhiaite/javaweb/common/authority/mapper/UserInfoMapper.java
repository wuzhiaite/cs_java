package com.wuzhiaite.javaweb.common.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuzhiaite.javaweb.common.authority.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author lpf
* @since 2020-06-04
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}

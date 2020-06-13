package com.wuzhiaite.javaweb.common.authority.mapper;

import com.wuzhiaite.javaweb.common.authority.entity.Menus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* <p>
*  Mapper 接口
* </p>
*
* @author lpf
* @since 2020-04-28
*/
@Mapper
public interface MenusMapper extends BaseMapper<Menus> {

    List<Menus> getUserMenuList(@Param("userId") String id,List<String> menuIds);
}

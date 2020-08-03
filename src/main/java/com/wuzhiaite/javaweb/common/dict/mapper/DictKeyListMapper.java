package com.wuzhiaite.javaweb.common.dict.mapper;

import com.wuzhiaite.javaweb.common.dict.entity.DictEntity;
import com.wuzhiaite.javaweb.common.dict.entity.DictKeyList;
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
* @since 2020-05-16
*/
@Mapper
public interface DictKeyListMapper extends BaseMapper<DictKeyList> {

    List<DictEntity> getDictByName(@Param("dictName") String dictName);
}

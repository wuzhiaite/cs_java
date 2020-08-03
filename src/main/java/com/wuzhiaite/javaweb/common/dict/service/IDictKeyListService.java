package com.wuzhiaite.javaweb.common.dict.service;

import com.wuzhiaite.javaweb.common.dict.entity.DictEntity;
import com.wuzhiaite.javaweb.common.dict.entity.DictKeyList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpf
 * @since 2020-05-16
 */
public interface IDictKeyListService extends IService<DictKeyList> {
    /**
     *  查找 dictName对应的数据
     * @param dictName
     * @return
     */
    List<DictEntity> getDictByName(String dictName);
}

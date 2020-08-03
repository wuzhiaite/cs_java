package com.wuzhiaite.javaweb.common.dict.service.impl;

import com.wuzhiaite.javaweb.common.dict.entity.DictEntity;
import com.wuzhiaite.javaweb.common.dict.entity.DictKeyList;
import com.wuzhiaite.javaweb.common.dict.mapper.DictKeyListMapper;
import com.wuzhiaite.javaweb.common.dict.service.IDictKeyListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpf
 * @since 2020-05-16
 */
@Service
public class DictKeyListServiceImpl extends ServiceImpl<DictKeyListMapper, DictKeyList>
        implements IDictKeyListService {

    /**
     *
     * @param dictName
     * @return
     */
    @Override
    public List<DictEntity> getDictByName(String dictName) {
        return baseMapper.getDictByName(dictName);
    }
}

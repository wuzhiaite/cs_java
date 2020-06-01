package com.wuzhiaite.javaweb.common.common.service.impl;

import com.wuzhiaite.javaweb.common.common.BaseEntity;
import com.wuzhiaite.javaweb.common.common.service.ITree;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lenovo
 */
@Component
public class TreeService<T extends BaseEntity> implements ITree<T> {


    @Override
    public List<T> getTree(List<T> list) {
        T t1 = list.get(0);
        return null;
    }
}

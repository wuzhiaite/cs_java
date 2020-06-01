package com.wuzhiaite.javaweb.common.common.service;

import com.wuzhiaite.javaweb.common.common.BaseEntity;

import java.util.List;

/**
 * @author lpf
 */
public interface ITree<T extends BaseEntity> {
    /**
     *
     * @param t
     * @return
     */
   public List<T> getTree(List<T> t);

}

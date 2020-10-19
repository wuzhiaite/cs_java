package com.wuzhiaite.javaweb.base.csm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;

import java.util.List;

/**
 *
 * @author lpf
 */
public interface ITree<T extends TreeEntity>  extends IService<T> {

    /**
     * 获取树状数据
     * @param list
     * @return
     */
   public List<T> getTree(List<T> list) ;

}

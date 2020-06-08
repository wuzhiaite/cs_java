package com.wuzhiaite.javaweb.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;
import com.wuzhiaite.javaweb.base.service.ITree;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树状图数据格式化
 * @author lpf
 */
//@Service
public class TreeService< M extends BaseMapper<T>,T extends TreeEntity>
                                        extends ServiceImpl<M ,T> implements ITree<T> {
    /**
     *
     * @param list
     * @return
     */
    @Override
    public List<T> getTree(List<T> list) {
        List<T> temp = new ArrayList<>();
        for(T t : list ){
            String fatherId = t.getFatherId();
            if(!StringUtils.isEmpty(fatherId)){
                getFather(t,list);
            }else{
                continue ;
            }
        }
        for(T t : list){
            String fatherId = t.getFatherId();
            if(StringUtils.isEmpty(fatherId)){
                temp.add(t);
            }
        }
        return temp;
    }

    /**
     * 关联子和父
     */
    protected void getFather(T t, List<T> list) {
        for(T m : list){
            if(t.getFatherId().equals(m.getId())){
                List<T> childrens = m.getChildren();
                if(StringUtils.isEmpty(childrens)){
                    childrens = new ArrayList();
                }
                childrens.add(t);
                m.setChildren(childrens);
                break;
            }else{
                continue ;
            }
        }
    }





}

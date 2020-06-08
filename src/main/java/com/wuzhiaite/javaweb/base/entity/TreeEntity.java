package com.wuzhiaite.javaweb.base.entity;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 *
 * @author lpf
 */
@Data
public class TreeEntity<T extends TreeEntity> {

    private String fatherId;
    @TableField(exist = false)
    private List<T> children;
    @TableField("orderBy")
    private String orderBy ;
    private String id;
    @TableField(exist = false)
    private Boolean hasChildrens ;

    public void setChildren(List<T> children) {
        this.children = children;
        hasChildrens = !StringUtils.isEmpty(children) ? true : false;
    }


}

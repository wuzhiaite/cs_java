package com.wuzhiaite.javaweb.base.entity;

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





}

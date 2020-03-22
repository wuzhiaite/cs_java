package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import com.wuzhiaite.javaweb.module.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 页面配置信息
 * @author lpf
 * @since 20200317
 */
@Data
public class PageConf extends BaseEntity implements Serializable {

    private String id ;
    private String sqlForm ;
    private String columnForm ;
    private String pageDesignForm ;
    private String pageParam ;


}

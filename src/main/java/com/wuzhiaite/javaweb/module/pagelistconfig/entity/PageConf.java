package com.wuzhiaite.javaweb.module.pagelistconfig.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @descript 页面配置信息
 * @author lpf
 * @since 20200317
 */
@Data
public class PageConf implements Serializable {

    private String id ;
    private String sqlForm ;
    private String columnForm ;
    private String pageDesignForm ;
    private String pageParam ;
    private Date createTime ;
    private Date updateTime ;


}

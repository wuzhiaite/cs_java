package com.wuzhiaite.javaweb.common.pagelistconfig.entity;

import com.wuzhiaite.javaweb.common.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @descript 配置细节
 * @author lpf
 */
@Data
public class PageDetail extends BaseEntity implements Serializable {

    private String id;
    private String searchSql ;
    private String showColumns ;
    private String configName;
    private String searchFileds ;
    private String conditionFileds ;


}

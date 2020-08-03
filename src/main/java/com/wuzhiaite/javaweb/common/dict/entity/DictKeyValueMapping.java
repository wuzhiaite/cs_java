package com.wuzhiaite.javaweb.common.dict.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-05-16
*/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DictKeyValueMapping对象", description="")
public class DictKeyValueMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dictId;

    private String dictKey;

    private String dictValue;

    private String bz;

    private String id;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;



}

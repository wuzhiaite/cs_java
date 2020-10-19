package com.wuzhiaite.javaweb.base.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 发送数据实体类
 * @author lpf
 */
@Accessors
@Data
@Builder
public class RabbitSenderEntity implements Serializable {
    private String exchange;
    private String routeKey;
    private Object params;
}

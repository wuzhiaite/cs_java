package com.wuzhiaite.javaweb.base.rabbitmq;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送数据实体类
 * @author lpf
 */
@Accessors
@Data
@Builder
public class RabbitSenderEntity {
    private String exchange;
    private String routeKey;
    private Object params;
}

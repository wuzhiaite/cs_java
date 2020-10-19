package com.wuzhiaite.javaweb.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * rabbitmq配置实体类
 * @author lpf
 */
@Data
public class RabbitEntity implements Serializable {
    private String exchange ;
    private String type ;
    private String bindingKey ;
    private List<String> queues ;
}

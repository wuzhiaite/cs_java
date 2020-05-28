package com.wuzhiaite.javaweb.base.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description 配置rabbitmq的基础信息数据
 * @author lpf
 */
@ConfigurationProperties("rabbit-init")
@Data
public class RabbitMQInitProperty {


    private List<RabbitEntity> list = new ArrayList<>();


}

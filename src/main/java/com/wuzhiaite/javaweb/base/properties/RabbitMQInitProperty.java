package com.wuzhiaite.javaweb.base.properties;

import com.wuzhiaite.javaweb.base.entity.RabbitEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 配置rabbitmq的基础信息数据
 * @author lpf
 */
@ConfigurationProperties("rabbit-init")
@Data
public class RabbitMQInitProperty {


    private List<RabbitEntity> list = new ArrayList<>();


}

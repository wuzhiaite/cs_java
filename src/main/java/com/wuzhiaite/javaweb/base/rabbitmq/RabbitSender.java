package com.wuzhiaite.javaweb.base.rabbitmq;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * @description 发送数据
 * @author lpf
 */
@Component
public class RabbitSender {

    /**
     * 自动注入RabbitTemplate模板类
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @return
     */
    private CorrelationData getCorrelation(){
        return new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     *
     * @param exchange
     * @param routingKey
     * @param message
     */
    public  void convertAndSend(String exchange,String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message, getCorrelation());
    }

    /**
     *
     * @param entity
     */
    public void convertAndSend(RabbitSenderEntity entity) {
        rabbitTemplate.convertAndSend(entity.getExchange(), entity.getRouteKey(), entity.getParams(), getCorrelation());
    }
}

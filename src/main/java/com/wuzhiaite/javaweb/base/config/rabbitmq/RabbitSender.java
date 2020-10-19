package com.wuzhiaite.javaweb.base.config.rabbitmq;


import com.wuzhiaite.javaweb.base.entity.RabbitSenderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * @description 发送数据
 * @author lpf
 */
@Slf4j
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
        CorrelationData correlation = getCorrelation();
        log.info("correlation:{},exchange:{},routekey:{},params:{}",correlation.toString(),exchange,
                routingKey,message.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlation);
    }

    /**
     *
     * @param entity
     */
    public void convertAndSend(RabbitSenderEntity entity) {
        CorrelationData correlation = getCorrelation();
        log.info("correlation:{},exchange:{},routekey:{},params:{}",correlation.toString(),entity.getExchange(),
                 entity.getRouteKey(),entity.getParams());
        rabbitTemplate.convertAndSend(entity.getExchange(), entity.getRouteKey(), entity.getParams(), correlation);
    }
}

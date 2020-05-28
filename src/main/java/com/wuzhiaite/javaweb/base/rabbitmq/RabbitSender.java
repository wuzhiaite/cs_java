package com.wuzhiaite.javaweb.base.rabbitmq;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
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



    public  CorrelationData  getCorrelationData(){
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        return data ;
    }

    /**
     * 发送消息方法调用: 构建Message消息
     */
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.convertAndSend("exchange-1", "springboot.abc", msg, getCorrelationData());
    }

}

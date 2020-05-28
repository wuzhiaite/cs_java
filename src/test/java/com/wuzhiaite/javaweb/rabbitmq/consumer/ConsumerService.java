package com.wuzhiaite.javaweb.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConsumerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues="queue_lpf")
    public void getMessage(Map message){
        System.out.println("********************************************************");
        System.out.println(message);
        System.out.println("********************************************************");
    }



}

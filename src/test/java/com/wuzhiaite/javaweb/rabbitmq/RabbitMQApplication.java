package com.wuzhiaite.javaweb.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.wuzhiaite.javaweb.rabbitmq.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableRabbit
@Configuration
public class RabbitMQApplication {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;


    @Test
    public void getRabbitQueue(){
        String exchange = rabbitTemplate.getExchange();
        System.out.println(exchange);

    }


    @Test
    public void buildRabbit(){
        //交换机
        Exchange exchange = new ExchangeBuilder("exchange_direct", ExchangeTypes.DIRECT).durable(true).build();
        rabbitAdmin.declareExchange(exchange);
        //队列
        Queue queue = QueueBuilder.durable("queue_lpf").build();
        rabbitAdmin.declareQueue(queue);
        //绑定
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("mq_key").noargs();
        rabbitAdmin.declareBinding(binding);

        String beanName = rabbitAdmin.getBeanName();
        System.out.println("buildRabbit"+beanName);
    }


    @Test
    public void sendRabbitInfo() {
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        Book book = new Book("红楼梦","曹雪芹");
//		rabbitTemplate.convertAndSend("exchange_direct", "mq_key", book,data);
        byte[] sendMessage = Base64Utils.encode(JSONObject.toJSONString(book).getBytes());
        rabbitTemplate.convertAndSend("exchange_direct", "mq_key", sendMessage,data);

    }



}

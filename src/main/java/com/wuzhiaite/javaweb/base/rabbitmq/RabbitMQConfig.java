package com.wuzhiaite.javaweb.base.rabbitmq;

import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.StringUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description rabbitmq基本配置
 * @author lpf
 */
@Configuration
@Component
public class RabbitMQConfig implements RabbitListenerConfigurer {

    /**
     * 回调函数: confirm确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if(!ack){
                //可以进行日志记录、异常处理、补偿处理等
                System.err.println("异常处理...."+cause);
            }else {
                //更新数据库，可靠性投递机制
            }
        }
    };
    /**
     * 回调函数: return返回
     */
    public final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };
    /**
     * rabbitmq 初始配置
     */
    @Autowired
    private RabbitMQInitProperty property ;
    /**
     *
     */
    @Autowired
    private ConnectionFactory connectionFactory;
    /**
     * 增加rabbitTemplate回调函数
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        return rabbitTemplate;
    }

    /**
     *
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(rabbitTemplate());
    }

    @Bean
    public RabbitMQInitProperty getRabbitMQProperty(RabbitAdmin rabbitAdmin){
        List<RabbitEntity> list = property.getList();
        if(StringUtils.isEmpty(list)) {
            return null ;
        }
        list.stream().forEach(entity -> {
            List<String> queues = entity.getQueues();
            String binding = entity.getBindingKey();
            String exchange = entity.getExchange();
            String type = !StringUtils.isEmpty(entity.getType())? entity.getType() : ExchangeTypes.DIRECT;
            if(StringUtils.isEmpty(queues) || StringUtils.isEmpty(binding)
                        || StringUtils.isEmpty(exchange)
                        || StringUtils.isEmpty(type)){
               return;
            }
            Exchange exchangeTempt= new ExchangeBuilder(exchange, type).durable(true).build();
            rabbitAdmin.declareExchange(exchangeTempt);
            for(String str : queues){
                Queue queue = QueueBuilder.durable(str).build();
                rabbitAdmin.declareQueue(queue);
                Binding bind = BindingBuilder.bind(queue).to(exchangeTempt).with(binding).noargs();
                rabbitAdmin.declareBinding(bind);
            }
        });
        return this.property ;
    }

    /**
     *  对象数据格式化
     * @return
     */
    @Bean
    public MessageConverter messagetConverter() {
        MessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
//        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }


}

package com.sisheng.yygh.common.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bobochang
 * @description
 * @created 2022/7/16-15:51
 **/
@Configuration
public class MQConfig {
    // 将发送到RabbitMQ中的pojo对象自动转换为json格式存储
    // 同时，消费者消费消息时，自动把json转换为pojo对象
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

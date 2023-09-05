package com.swapnil.config;

import com.swapnil.constants.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitConfig {

    @Value("${rabbit.order.queue}")
    private String orderQueue;

    @Value("${rabbit.order.exchange}")
    private String orderExchange;

    @Value("${rabbit.order.routingkey}")
    private String orderRoutingKey;

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueue);
    }

    @Bean
    public Exchange orderDelayedExchange() {
        return ExchangeBuilder.directExchange(orderExchange)
                .delayed()
                .withArgument(RabbitMQConstant.X_DELAY_HEADER,RabbitMQConstant.DIRECT)
                .build();
    }

    @Bean
    Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderDelayedExchange())
                .with(orderRoutingKey).noargs();
    }
}

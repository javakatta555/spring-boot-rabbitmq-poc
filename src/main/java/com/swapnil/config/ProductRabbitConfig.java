package com.swapnil.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitConfig {

    @Value("${rabbit.product.queue}")
    private String productQueue;

    @Value("${rabbit.product.exchange}")
    private String productExchange;

    @Value("${rabbit.product.routingkey}")
    private String productRoutingKey;

    @Bean
    public Queue productQueue() {
        return new Queue(productQueue);
    }

    @Bean
    public Exchange productExchange() {
        return ExchangeBuilder.directExchange(productExchange).build();
    }

    @Bean
    Binding productBinding() {
        return BindingBuilder.bind(productQueue()).to(productExchange())
                .with(productRoutingKey).noargs();
    }
}

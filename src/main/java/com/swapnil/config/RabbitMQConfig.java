package com.swapnil.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.port}")
    private int port;

    @Value("${rabbit.auth.username}")
    private String username;

    @Value("${rabbit.auth.password}")
    private String password;


    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory c =new CachingConnectionFactory(host);
        c.setPort(port);
        c.setUsername(username);
        c.setPassword(password);
        return c;
    }


    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

}

package com.swapnil.publisher;

import com.fasterxml.jackson.databind.JsonNode;
import com.swapnil.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class ProductPublisher {

    @Value("${rabbit.product.routingkey}")
    private String          routingKey;

    @Value("${rabbit.product.exchange}")
    private String          exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishOnRabbit(final String message) {
        log.info("[publishOnRabbit] message {}", message);
        if(message == null) {
            return;
        }
        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(5);
        log.info("[publishOnRabbit] Publishing to exchange {} with routing key {}", exchange, routingKey);
        rabbitTemplate.send(exchange,routingKey,new Message(message.getBytes(),messageProperties));
    }
}

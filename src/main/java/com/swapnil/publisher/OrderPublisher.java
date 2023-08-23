package com.swapnil.publisher;

import com.swapnil.Util.JsonUtils;
import com.swapnil.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderPublisher {

    @Value("${rabbit.order.routingkey}")
    private String          routingKey;

    @Value("${rabbit.order.exchange}")
    private String          exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishOrderOnRabbit(final Order order) {
        String message = JsonUtils.asJsonString(order);
        log.info("[publishOrderOnRabbit] message {}", message);
        if(message == null) {
            return;
        }
        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDelay(5);
        log.info("[publishOrderOnRabbit] Publishing to exchange {} with routing key {}", exchange, routingKey);
        rabbitTemplate.send(exchange,routingKey,new Message(message.getBytes(),messageProperties));
    }
}

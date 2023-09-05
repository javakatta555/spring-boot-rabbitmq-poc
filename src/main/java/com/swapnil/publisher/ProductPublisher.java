package com.swapnil.publisher;

import com.swapnil.Util.JsonUtils;
import com.swapnil.constants.RabbitMQConstant;
import com.swapnil.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductPublisher {

    @Value("${rabbit.product.routingkey}")
    private String          routingKey;

    @Value("${rabbit.product.exchange}")
    private String          exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishOnRabbit(final Product product)  {
        String message = "";
        message = JsonUtils.asJsonString(product);
        log.info("[publishOnRabbit] message {}", message);
        if(message == null) {
            return;
        }
        Message productMessage = MessageBuilder.withBody(message.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                //.setHeader(RabbitMQConstant.X_DELAY_HEADER, 15000)
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        log.info("[publishOnRabbit] Publishing to exchange {} with routing key {}", exchange, routingKey);
        rabbitTemplate.send(exchange,routingKey, productMessage);
    }
}

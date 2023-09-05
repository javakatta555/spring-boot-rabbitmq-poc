package com.swapnil.consumer;

import com.swapnil.Util.JsonUtils;
import com.swapnil.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = {"${rabbit.order.queue}"})
    public void consume(String message){
        log.info(String.format("Received message -> %s", message));
        Order order = JsonUtils.parseObject(message,Order.class);
        log.info(String.format("Received order -> %s", order));
    }
}

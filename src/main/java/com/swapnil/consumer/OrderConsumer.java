package com.swapnil.consumer;

import com.swapnil.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {

    @RabbitListener(queues = {"${rabbit.order.queue}"})
    public void consume(Order order){
        log.info(String.format("Received order -> %s", order));
    }
}

package com.swapnil.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductConsumer {

    @RabbitListener(queues = {"${rabbit.product.queue}"})
    public void consume(String message){
        log.info(String.format("Received message -> %s", message));
    }
}

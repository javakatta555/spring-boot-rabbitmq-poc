package com.swapnil.consumer;

import com.swapnil.Util.JsonUtils;
import com.swapnil.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductConsumer {

    @RabbitListener(queues = {"${rabbit.product.queue}"},containerFactory = "productRabbitListenerContainerFactory")
    public void consume(String message){
        Product product = JsonUtils.parseObject(message, Product.class);
        log.info(String.format("Received message -> %s", message));
        log.info(String.format("Received message -> %s", product));
    }
}

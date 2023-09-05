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
    public void consume(Product product){
        log.info(String.format("Received message -> %s", product));
    }
}

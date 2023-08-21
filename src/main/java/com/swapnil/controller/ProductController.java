package com.swapnil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.model.Product;
import com.swapnil.publisher.ProductPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductPublisher productPublisher;
    @GetMapping("/health")
    public String healthCheck(){
        return "I am Healthy!";
    }

    @PostMapping("/product")
    public Product publishProduct(@RequestBody Product product) throws JsonProcessingException {
        productPublisher.publishOnRabbit(product.toString());
        return product;
    }
}

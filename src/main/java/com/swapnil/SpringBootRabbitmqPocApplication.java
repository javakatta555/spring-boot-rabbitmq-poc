package com.swapnil;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRabbitmqPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitmqPocApplication.class, args);
	}

}

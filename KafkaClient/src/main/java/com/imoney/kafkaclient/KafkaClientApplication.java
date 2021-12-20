package com.imoney.kafkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.imoney.kafkaclient.*")
public class KafkaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaClientApplication.class, args);
	}

}

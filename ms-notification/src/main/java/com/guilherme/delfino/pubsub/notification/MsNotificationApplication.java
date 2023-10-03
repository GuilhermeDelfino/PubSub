package com.guilherme.delfino.pubsub.notification;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRabbit
@EnableRedisRepositories
public class MsNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificationApplication.class, args);
	}

}

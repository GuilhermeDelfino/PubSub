package com.guilherme.delfino.pubsub.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableDiscoveryClient
@ComponentScan("com.guilherme.delfino.pubsub")
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class MsEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEventApplication.class, args);
	}

}


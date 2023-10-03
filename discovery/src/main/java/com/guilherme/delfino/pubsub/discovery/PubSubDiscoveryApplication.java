package com.guilherme.delfino.pubsub.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PubSubDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubSubDiscoveryApplication.class, args);
	}

}

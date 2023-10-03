package com.guilherme.delfino.pubsub.subscribers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableCaching
@EnableDiscoveryClient
@ComponentScan(basePackages = [ "com.guilherme.delfino.pubsub.token", "com.guilherme.delfino.pubsub.subscribers" ])
@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class MsSubscribersApplication

fun main(args: Array<String>) {
	runApplication<MsSubscribersApplication>(*args)
}

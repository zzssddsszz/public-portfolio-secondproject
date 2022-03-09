package com.javatechie.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class CloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigServerApplication.class, args);
	}

}

// the purpose of spring cloud config in microservice architecture is to store
// serve and distributing configuration across multiple application

/*
Spring Cloud Config provides server-side and client-side support for externatlised configuration in a distributed system.
With the Config Server, you have a central place to manage external properties for applications across all environments.

*/

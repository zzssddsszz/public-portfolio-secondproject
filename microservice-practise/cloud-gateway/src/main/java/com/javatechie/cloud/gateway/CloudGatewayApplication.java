package com.javatechie.cloud.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

//@Controller
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
//@EnableZuulProxy	// enabled the service to be a Zuul server
//@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
public class CloudGatewayApplication {
// https://dzone.com/articles/how-to-use-spring-cloud-gateway-with-oauth-20-patt
//	https://github.com/oktadev/okta-spring-cloud-gateway-example
	@Autowired
	private TokenRelayGatewayFilterFactory filterFactory;
//
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		//@formatter:off
		return builder.routes()
				.route("user-service", r -> r.path("/user/**")
						.filters(f -> f.filter(filterFactory.apply()))
						.uri("lb://USER-SERVICE"))
				.build();
		//@formatter:on
	}


	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}





// https://ch4njun.tistory.com/237
}

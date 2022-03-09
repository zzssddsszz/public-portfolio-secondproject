package com.javatechie.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallback(){ // A Mono is a stream of 0 to 1 element,
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceFallback(){
        return Mono.just("Payment Service is taking too long to response or is down. Please try again later");
    }
}
